package uk.ac.swanseacoventry.cmt.ontrack.diagram.automaticLayout;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import click.nullpointer.tplayout.IProgressObserver;
import click.nullpointer.tplayout.LayoutAlgorithm;
import click.nullpointer.tplayout.WatchedSystemProperties;
import click.nullpointer.tplayout.graph.Node;
import click.nullpointer.tplayout.graph.Point.PointType;
import click.nullpointer.tplayout.implementations.SimulatedAnnealing;
import click.nullpointer.tplayout.util.GraphUtilities;
import uk.ac.swanseacoventry.cmt.ontrack.Connector;
import uk.ac.swanseacoventry.cmt.ontrack.Point;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.Unit;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.automaticLayout.InputParsing.IInputParser;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.custom.Util;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.ConnectorUpdateLocationCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.ConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TrackPlanEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackDiagramEditor;

public class TrackLayoutConfigurationDialog extends Dialog {

	private static final String TEMPORARY_NODE_NAME_PREFIX = TrackLayoutConfigurationDialog.class.toString()
			+ "?TEMPORARY_NAME";
	private static final int COMPONENT_WIDTH = 80;
	private static final int COMPONENT_HEIGHT = 40;

	private Table table;
	// private Label nodeCount, pointCount, planName;
	private Button btnStart,
			// btnStop,
			btnClose;
	private IProgressObserver progressObs;
	private Thread operation;

	public TrackLayoutConfigurationDialog(Shell shell) {
		super(shell);
	}

	@Override
	protected Control createDialogArea(Composite parent) {

		Composite par = (Composite) super.createDialogArea(parent);
		par.setLayout(new BorderLayout());

		Composite upperPane = new Composite(par, SWT.NONE);
		upperPane.setLayoutData(new BorderLayout.BorderData(BorderLayout.NORTH));
		upperPane.setLayout(new FillLayout(SWT.HORIZONTAL));

		Label trackLbl = new Label(upperPane, SWT.RIGHT);
		trackLbl.setText("Tracks: ");

		Label nodeCount = new Label(upperPane, SWT.LEFT);

		Label lblPoints = new Label(upperPane, SWT.RIGHT);
		lblPoints.setText("Points: ");

		Label pointCount = new Label(upperPane, SWT.LEFT);

		Label lblPlan = new Label(upperPane, SWT.RIGHT);
		lblPlan.setText("Plan: ");

		Label planName = new Label(upperPane, SWT.LEFT);

		TrackPlan tp = getCurrentTrackPlan();
		nodeCount.setText("" + tp.getTracks().size());
		pointCount.setText("" + tp.getPoints().size());
		planName.setText("" + Util.getActiveFile());

		Composite lowerPane = new Composite(par, SWT.NONE);
		lowerPane.setLayoutData(new BorderLayout.BorderData(BorderLayout.SOUTH));
		lowerPane.setLayout(new FillLayout(SWT.VERTICAL));

		Composite composite = new Composite(lowerPane, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		btnStart = new Button(composite, SWT.NONE);
		btnStart.setText("Start");
		btnStart.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				if ((e.stateMask & SWT.CTRL) != 0) {
					System.out.println("Ctrl");
					DEMO_PURPOSES_DELETE_THIS_scramblePlan();
				} else
					start();
			}
		});

		// Button demoOnlyScramblePlan = new Button(composite, SWT.LEFT);
		// demoOnlyScramblePlan.setText("(Demo) Scramble Plan");
		// demoOnlyScramblePlan.addListener(SWT.Selection, new Listener() {
		//
		// @Override
		// public void handleEvent(Event event) {
		// DEMO_PURPOSES_DELETE_THIS_scramblePlan();
		// }
		// });

		// btnStop = new Button(composite, SWT.NONE);
		// btnStop.setText("Stop");
		// btnStop.addListener(SWT.Selection, new Listener() {
		// @Override
		// public void handleEvent(Event e) {
		// stop();
		// }
		// });

		btnClose = new Button(composite, SWT.NONE);
		btnClose.setText("Close");

		btnClose.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				close();
			}
		});

		Label progressLabel = new Label(lowerPane, SWT.NONE);
		progressLabel.setText("Ready.");
		ProgressBar progressBar = new ProgressBar(lowerPane, SWT.BORDER | SWT.SMOOTH);

		progressObs = new IProgressObserver() {

			@Override
			public void worked(int ammount) {
				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						progressBar.setSelection(progressBar.getSelection() + ammount);
					}
				});
			}

			@Override
			public void done() {
				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						progressBar.setSelection(progressBar.getMaximum());
						progressLabel.setText("");
					}
				});
			}

			@Override
			public void beginTask(String name, int work) {
				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						progressBar.setMaximum(work);
						progressLabel.setText(name);
					}
				});

			}
		};

		TabFolder middlePane = new TabFolder(par, SWT.NONE);
		middlePane.setLayoutData(new BorderLayout.BorderData(BorderLayout.CENTER));

		TabItem settings = new TabItem(middlePane, SWT.NONE);
		settings.setText("Settings");

		ScrolledComposite scrollingPaneSettings = new ScrolledComposite(middlePane,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		settings.setControl(scrollingPaneSettings);
		scrollingPaneSettings.setExpandHorizontal(true);
		scrollingPaneSettings.setExpandVertical(true);

		table = new Table(scrollingPaneSettings, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		scrollingPaneSettings.setContent(table);
		scrollingPaneSettings.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		addProppertiesToTable(table);
		makeTableModifiable(table);
		par.pack(true);
		par.layout();
		return par;

	}

	private void start() {
		if (operation != null)
			return;
		updateButtons(false);
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				operation = Thread.currentThread();
				progressObs.beginTask("Transforming TrackPlan to RailwayGraph", 4);
				TrackPlan tp = getCurrentTrackPlan();
				progressObs.worked(1);
				CrudeSemiBiDirectionalMap<String, Unit> nameMap = getNamedUnits(tp);
				progressObs.worked(1);
				Node start = railwayGraphFromTrackPlan(tp, nameMap);
				progressObs.worked(1);
				LayoutAlgorithm l = new SimulatedAnnealing(start);
				progressObs.worked(1);
				l.performLayout(progressObs);
				progressObs.done();
				Node result = l.getResult().orElse(start);
				applyNewLayout(result, tp, nameMap);
				progressObs.done();
				progressObs.beginTask("Done.", 0);
				operation = null;
				updateButtons(false);
			}
		});
	}

	private void updateButtons(boolean started) {
		btnStart.setEnabled(!started);
		btnClose.setEnabled(!started);
		// btnStop.setEnabled(started);
	}

	private void applyNewLayout(Node result, TrackPlan tp, CrudeSemiBiDirectionalMap<String, Unit> nameMap) {
		progressObs.beginTask("Applying layout to TrackPlan...",
				tp.getTracks().size() + (tp.getPoints().size() * 2) + 3);
		List<Node> nodes = new LinkedList<>();
		GraphUtilities.traverse(result, a -> {
			nodes.add(a);
			progressObs.worked(1);
			return true;
		});
		progressObs.worked(1);
		Map<Connector, XYPoint> conn = getCoordinates(nodes, nameMap);
		for (Connector c : conn.keySet()) {
			progressObs.worked(1);
			System.out.println(c);
			IGraphicalEditPart g = (IGraphicalEditPart) Util.findEditPartBySemantics(c, ConnectorEditPart.class);
			System.out.println(g);
			teleport(g, conn.get(c).x * COMPONENT_WIDTH, conn.get(c).y * COMPONENT_HEIGHT);
			System.out.println(c + " at " + conn.get(c).x + "," + conn.get(c).y);
		}
	}

	private class XYPoint {
		int x, y;

		XYPoint(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private Map<Connector, XYPoint> getCoordinates(List<Node> nodes, Map<String, Unit> units) {
		Map<Connector, XYPoint> points = new HashMap<>();
		for (Node n : nodes) {
			if (n instanceof click.nullpointer.tplayout.graph.Point) {
				click.nullpointer.tplayout.graph.Point p = ((click.nullpointer.tplayout.graph.Point) n);
				PointType t = p.getType();
				Point point = (Point) units.get(n.getName());
				Connector entry = getEntryConnector(point);
				Connector normal = getNormalConnector(point);
				Connector reverse = getReverseConnector(point);
				int entryX, entryY;
				int normalX, normalY;
				int reverseX, reverseY;
				if (t.isRight()) {
					entryX = p.x;
					entryY = p.y;
					normalX = p.x + 1;
					normalY = p.y;
					reverseX = p.x + 1;
					reverseY = p.y + (t.isBottom() ? -1 : 1);
					if (!t.isNormalSameTrackAsEntry()) {
						int tx = normalX, ty = normalY;
						normalX = reverseX;
						reverseX = tx;
						normalY = reverseY;
						reverseY = ty;
					}
				} else {
					entryX = (p.x + 1);
					entryY = (p.y);
					normalX = p.x;
					normalY = p.y;
					reverseX = p.x;
					reverseY = p.y + (t.isBottom() ? -1 : 1);
					if (!t.isNormalSameTrackAsEntry()) {
						int tx = normalX, ty = normalY;
						normalX = reverseX;
						reverseX = tx;
						normalY = reverseY;
						reverseY = ty;
					}

				}
				// Implicitly done by recursive call.
				// points.put(entry, new XYPoint(entryX, entryY));
				// points.put(normal, new XYPoint(normalX, normalY));
				// points.put(reverse, new XYPoint(reverseX, reverseY));
				recursivelyUpdateConnectorPositionsPleaseSendHelp(points, entryX, entryY, entry, !t.isRight());
				recursivelyUpdateConnectorPositionsPleaseSendHelp(points, normalX, normalY, normal, t.isRight());
				recursivelyUpdateConnectorPositionsPleaseSendHelp(points, reverseX, reverseY, reverse, t.isRight());
			}
		}
		// normalize points
		int minx = Integer.MAX_VALUE, miny = Integer.MAX_VALUE;
		for (Connector c : points.keySet()) {
			XYPoint p = points.get(c);
			minx = Math.min(p.x, minx);
			miny = Math.min(p.y, miny);
		}
		for (Connector c : points.keySet()) {
			XYPoint p = points.get(c);
			p.x -= minx;
			p.y -= miny;
		}
		return points;
	}

	private void recursivelyUpdateConnectorPositionsPleaseSendHelp(Map<Connector, XYPoint> positions, int x,
			final int y, Connector current, boolean goRight) {
		positions.put(current, new XYPoint(x, y));
		for (Track t : current.getTracks()) {
			if (t.getPoint() != null)
				continue;
			Connector c1 = t.getC1();
			Connector c2 = t.getC2();
			if (!positions.containsKey(c1))
				recursivelyUpdateConnectorPositionsPleaseSendHelp(positions, x + (goRight ? 1 : -1), y, c1, goRight);
			if (!positions.containsKey(c2))
				recursivelyUpdateConnectorPositionsPleaseSendHelp(positions, x + (goRight ? 1 : -1), y, c2, goRight);
		}
	}

	private void teleport(IGraphicalEditPart gr, int x, int y) {
		CompoundCommand cc = new CompoundCommand();
		cc.add(new ICommandProxy(new ConnectorUpdateLocationCommand(gr, x, y, false)));
		cc.execute();
	}

	private void DEMO_PURPOSES_DELETE_THIS_scramblePlan() {
		int maxX = 700, maxY = 400;
		TrackPlan tp = getCurrentTrackPlan();
		for (Connector c : tp.getConnectors()) {
			int x = ThreadLocalRandom.current().nextInt(10, maxX);
			int y = ThreadLocalRandom.current().nextInt(10, maxY);
			IGraphicalEditPart e = (IGraphicalEditPart) Util.findEditPartBySemantics(c, ConnectorEditPart.class);
			teleport(e, x, y);
		}
	}

	private TrackPlan getCurrentTrackPlan() {
		OntrackDiagramEditor editor = (OntrackDiagramEditor) Util.getEditorPart();
		RootEditPart rootTP = editor.getDiagramGraphicalViewer().getRootEditPart();
		TrackPlanEditPart tpPart = (TrackPlanEditPart) rootTP.getChildren().get(0);
		TrackPlan tp = (TrackPlan) ((Diagram) tpPart.getModel()).getElement();
		return tp;
	}

	private CrudeSemiBiDirectionalMap<String, Unit> getNamedUnits(TrackPlan tp) {
		// PATCH. Some units may not have names (possible and valid) so they must be
		// renamed internally.
		CrudeSemiBiDirectionalMap<String, Unit> unitNames = new CrudeSemiBiDirectionalMap<String, Unit>();
		long counter = 0;
		// Put points and tracks in an array to iterate over. Better ways exist but this
		// isn't too bad.
		// TODO ignore existing names and rename all anyway. Sorts issue of duplicate
		// names.
		Unit[] all = new Unit[tp.getTracks().size() + tp.getPoints().size()];
		for (int i = 0; i < tp.getTracks().size(); i++)
			all[i] = tp.getTracks().get(i);
		int off = tp.getTracks().size();
		for (int i = off; i < off + tp.getPoints().size(); i++)
			all[i] = tp.getPoints().get(i - off);

		for (Unit u : all) {
			// Some Tracks are composites of Points. Ignore those!
			if (u == null || (u instanceof Track && ((Track) u).getPoint() != null))
				continue;
			// Unit has no name. Name it appropriately.
			if (u.getName() == null || u.getName().trim().isEmpty()) {
				unitNames.put(TEMPORARY_NODE_NAME_PREFIX + (counter++), u);
				System.out.println("Temporarily named " + u + " "
						+ ((u instanceof Point) ? ((Point) u).getNormalTrack().getName() + "" : "") + " as "
						+ TEMPORARY_NODE_NAME_PREFIX + (counter - 1));
			} else if (unitNames.containsKey(u.getName())) {// Two units with the same name where found!
				fail(true, "Non unique name " + u.getName()
						+ " found! Two or more components have the same name. All components must have unique names");
				return null;
			} else
				unitNames.put(u.getName(), u);
		}
		return unitNames;
	}

	private Node railwayGraphFromTrackPlan(TrackPlan tp, CrudeSemiBiDirectionalMap<String, Unit> unitNames) {
		// ASSERT THAT THERE IS NO DISCONNECTIONS.
		// Name, Clicknode
		Map<String, Node> nodes = new HashMap<>();
		// Unit,Unitarr.
		Map<Unit, Unit[]> connections = new HashMap<>();
		Map<String, Unit> namedUnits = new HashMap<>();
		for (Track t : tp.getTracks()) {
			// this track is part of a point. It will be handled as a point later on
			if (t.getPoint() != null) {
				System.out.println("ignore " + t);
				continue;
			}
			Unit oc1 = getOtherTrack(t.getC1(), t);
			Unit oc2 = getOtherTrack(t.getC2(), t);
			String name = unitNames.keyOf(t);
			connections.put(t, new Unit[] { oc1, oc2 });
			nodes.put(name, new click.nullpointer.tplayout.graph.Track(name, null, null));
			namedUnits.put(name, t);
		}

		for (Point p : tp.getPoints()) {
			Connector entry = getEntryConnector(p);
			Connector normal = getNormalConnector(p);
			Connector reverse = getReverseConnector(p);

			Unit ent = getOtherTrack(entry, p.getNormalTrack(), p.getReverseTrack());
			Unit rev = getOtherTrack(reverse, p.getReverseTrack());
			Unit nor = getOtherTrack(normal, p.getNormalTrack());
			String name = unitNames.keyOf(p);
			connections.put(p, new Unit[] { nor, ent, rev });
			nodes.put(name, new click.nullpointer.tplayout.graph.Point(name, null, null, null));
			namedUnits.put(name, p);
		}

		for (Node n : nodes.values()) {
			if (n instanceof click.nullpointer.tplayout.graph.Track) {
				click.nullpointer.tplayout.graph.Track t = (click.nullpointer.tplayout.graph.Track) n;
				Unit corresponding = namedUnits.get(t.getName());
				assert corresponding != null;

				Unit[] twoConns = connections.get(corresponding);
				assert twoConns != null && twoConns.length == 2;
				if (twoConns[0] != null) {
					Node c1 = nodes.get(unitNames.keyOf(twoConns[0]));
					assert c1 != null : "Connection " + 0 + " not found!";
					t.setConnectionA(c1);
				}
				if (twoConns[1] != null) {
					Node c2 = nodes.get(unitNames.keyOf(twoConns[1]));
					assert c2 != null : "Connection " + 1 + " not found!";
					t.setConnectionB(c2);
				}
			} else if (n instanceof click.nullpointer.tplayout.graph.Point) {
				click.nullpointer.tplayout.graph.Point p = (click.nullpointer.tplayout.graph.Point) n;
				Unit corresponding = namedUnits.get(p.getName());
				assert corresponding != null;

				Unit[] cons = connections.get(corresponding);
				assert cons != null && cons.length == 3;
				Node nor = cons[0] == null ? null : nodes.get(unitNames.keyOf(cons[0]));
				Node ent = cons[1] == null ? null : nodes.get(unitNames.keyOf(cons[1]));
				Node rev = cons[2] == null ? null : nodes.get(unitNames.keyOf(cons[2]));
				p.setEntry(ent);
				p.setNormal(nor);
				p.setReverse(rev);
			} else {
				throw new UnsupportedOperationException(n.getClass().getSimpleName() + " not supported!");
			}
		}

		// TODO re-apply names but apply configuration before
		Node start = null;
		for (Node n : nodes.values()) {
			int nonNull = 0;
			for (Node con : n.getConnections())
				if (con != null)
					nonNull++;
			if (nonNull > 0 && nonNull < n.getConnections().length) {
				// Connected but not completely
				start = n;
				break;
			}
		}

		if (start == null) {
			fail(true,
					"Failed to find a starting unit to determine the layout. Make sure the trackplan is fully and correctly connected, and that there is at least one unit with one connection unpopulated.");
			return null;
		} else {
			System.out.println("Success!");
			return start;
		}
	}

	private void fail(boolean miserable, String reason) {
		MessageDialog.openError(getShell(), "Failed", reason);
	}

	private Connector getReverseConnector(Point p) {
		return p.getReverseTrack().getC1() == getEntryConnector(p) ? p.getReverseTrack().getC2()
				: p.getReverseTrack().getC1();
	}

	private Connector getNormalConnector(Point p) {
		return p.getNormalTrack().getC1() == getEntryConnector(p) ? p.getNormalTrack().getC2()
				: p.getNormalTrack().getC1();
	}

	private Connector getEntryConnector(Point p) {
		Connector c1n = p.getNormalTrack().getC1();
		Connector c1r = p.getReverseTrack().getC1();
		Connector c2n = p.getNormalTrack().getC2();
		Connector c2r = p.getReverseTrack().getC2();

		if (c1r == c1n || c1r == c2n)
			return c1r;
		if (c2r == c1n || c2r == c2n)
			return c2r;
		return null;
	}

	private Unit getOtherTrack(Connector c, Track... t) {
		Unit res = null;
		for (Track r : c.getTracks()) {
			for (Track x : t)
				if (r != x) {
					res = r;
					break;
				}
		}
		if (res instanceof Track && ((Track) res).getPoint() != null)
			res = ((Track) res).getPoint();
		return res;
	}

	// private void stop() {
	// System.out.println("Stop");
	// operation = null;
	// updateButtons(false);
	// }

	@Override
	public boolean close() {
		// stop();
		// if (operation != null)
		// fail(false, "Cannot close window whilst a layout operation is running!");
		return super.close();
	}

	@Override
	protected org.eclipse.swt.graphics.Point getInitialSize() {
		return new org.eclipse.swt.graphics.Point(500, 600);
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
	}

	private void addProppertiesToTable(Table table) {
		table.removeAll();
		String[] titles = { "Propperty Key", "Value" };
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NULL);
			column.setText(titles[i]);
		}

		SimulatedAnnealing.SAProperties.initialize();
		int i = 0;
		for (Entry<Object, Object> objs : WatchedSystemProperties.globalInstance().entrySet()) {
			TableItem item = new TableItem(table, SWT.NULL, i);
			item.setText(0, objs.getKey().toString());
			item.setText(1, objs.getValue().toString());
			item.setData(objs.getKey());
		}
		table.getColumn(1).pack();
		table.getColumn(0).pack();
	}

	private void makeTableModifiable(Table table) {
		table.addListener(SWT.MouseDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (table.getSelection().length == 1) {
					TableItem sel = table.getSelection()[0];
					Object key = sel.getData();
					Class<?> valType = WatchedSystemProperties.globalInstance().get(key).getClass();
					Optional<IInputParser> p = InputParsing.getParser(valType,
							WatchedSystemProperties.globalInstance().get(key).toString());
					if (!p.isPresent()) {
						event.doit = false;
					} else {
						InputDialog d = new InputDialog(table.getShell(), "Change Value",
								"Enter the new value for propperty: " + key, "", new IInputValidator() {

									@Override
									public String isValid(String newText) {
										Optional<?> val = p.get().parse(newText, valType);
										if (!val.isPresent())
											return "Failed to parse input as " + valType.getSimpleName();
										return null;
									}
								});
						if (d.open() == InputDialog.OK) {
							Optional<?> val = p.get().parse(d.getValue(), valType);
							try {
								WatchedSystemProperties.globalInstance().put(key, val.get());
								addProppertiesToTable(table);
							} catch (Exception e) {
								fail(false, e.getMessage());
							}
						}
					}
				}
			}
		});
	}

}

class BorderLayout extends Layout {
	// Region constants.
	public static final int NORTH = 0;
	public static final int SOUTH = 1;
	public static final int CENTER = 2;
	public static final int EAST = 3;
	public static final int WEST = 4;

	/**
	 * Indicates the region that a control belongs to.
	 * 
	 */
	public static class BorderData {
		public int region = CENTER; // default.

		public BorderData() {
		}

		public BorderData(int region) {
			this.region = region;
		}
	}

	// Controls in all the regions.
	public Control[] controls = new Control[5];

	// Cached sizes.
	org.eclipse.swt.graphics.Point[] sizes;

	// Preferred width and height
	int width;
	int height;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.swt.widgets.Layout#computeSize(org.eclipse.swt.widgets.Composite,
	 * int, int, boolean)
	 */
	protected org.eclipse.swt.graphics.Point computeSize(Composite composite, int wHint, int hHint,
			boolean flushCache) {

		if (sizes == null || flushCache == true)
			refreshSizes(composite.getChildren());
		int w = wHint;
		int h = hHint;
		if (w == SWT.DEFAULT)
			w = width;
		if (h == SWT.DEFAULT)
			h = height;

		return new org.eclipse.swt.graphics.Point(w, h);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.widgets.Layout#layout(org.eclipse.swt.widgets.Composite,
	 * boolean)
	 */
	protected void layout(Composite composite, boolean flushCache) {
		if (flushCache || sizes == null)
			refreshSizes(composite.getChildren());

		Rectangle clientArea = composite.getClientArea();

		// Enough space for all.
		if (controls[NORTH] != null) {
			controls[NORTH].setBounds(clientArea.x, clientArea.y, clientArea.width, sizes[NORTH].y);
		}
		if (controls[SOUTH] != null) {
			controls[SOUTH].setBounds(clientArea.x, clientArea.y + clientArea.height - sizes[SOUTH].y, clientArea.width,
					sizes[SOUTH].y);
		}
		if (controls[WEST] != null) {
			controls[WEST].setBounds(clientArea.x, clientArea.y + sizes[NORTH].y, sizes[WEST].x,
					clientArea.height - sizes[NORTH].y - sizes[SOUTH].y);
		}
		if (controls[EAST] != null) {
			controls[EAST].setBounds(clientArea.x + clientArea.width - sizes[EAST].x, clientArea.y + sizes[NORTH].y,
					sizes[EAST].x, clientArea.height - sizes[NORTH].y - sizes[SOUTH].y);
		}
		if (controls[CENTER] != null) {
			controls[CENTER].setBounds(clientArea.x + sizes[WEST].x, clientArea.y + sizes[NORTH].y,
					clientArea.width - sizes[WEST].x - sizes[EAST].x,
					clientArea.height - sizes[NORTH].y - sizes[SOUTH].y);
		}

	}

	private void refreshSizes(Control[] children) {
		for (int i = 0; i < children.length; i++) {
			Object layoutData = children[i].getLayoutData();
			if (layoutData == null || (!(layoutData instanceof BorderData)))
				continue;
			BorderData borderData = (BorderData) layoutData;
			if (borderData.region < 0 || borderData.region > 4) // Invalid.
				continue;
			controls[borderData.region] = children[i];
		}

		width = 0;
		height = 0;

		if (sizes == null)
			sizes = new org.eclipse.swt.graphics.Point[5];

		for (int i = 0; i < controls.length; i++) {
			Control control = controls[i];
			if (control == null) {
				sizes[i] = new org.eclipse.swt.graphics.Point(0, 0);
			} else {
				sizes[i] = control.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
			}
		}

		width = Math.max(width, sizes[NORTH].x);
		width = Math.max(width, sizes[WEST].x + sizes[CENTER].x + sizes[EAST].x);
		width = Math.max(width, sizes[SOUTH].x);

		height = Math.max(Math.max(sizes[WEST].y, sizes[EAST].y), sizes[CENTER].y) + sizes[NORTH].y + sizes[SOUTH].y;

	}

}
