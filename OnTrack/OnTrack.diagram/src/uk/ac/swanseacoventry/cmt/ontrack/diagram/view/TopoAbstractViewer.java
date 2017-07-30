package uk.ac.swanseacoventry.cmt.ontrack.diagram.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;

import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import uk.ac.swanseacoventry.cmt.ontrack.Connector;
import uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.Crossing;
import uk.ac.swanseacoventry.cmt.ontrack.Entrance;
import uk.ac.swanseacoventry.cmt.ontrack.Point;
import uk.ac.swanseacoventry.cmt.ontrack.Signal;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.custom.Util;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.ConnectorUpdateLocationCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.SimulationAddCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.TrackPlanApplyTopoAbstractionCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.TrackPlanCoveringClearCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.view.listeners.PartListener2Impl;

import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.ConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.EntranceConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.EntranceEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.ExitConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.ExitEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.SignalConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.SignalEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.SignalTrackEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TerminalConnectorEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TerminalEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TrackEditPart;

public class TopoAbstractViewer extends ViewPart {
	private Table table;
	private Listener focusListener;
	private Track highLighted;

	private Hashtable<Track, ArrayList<Track>> topoAbs;

	public TopoAbstractViewer() {
		super();
	}

	public void setFocus() {
		table.setFocus();
	}

	public void createPartControl(Composite parent) {
		table = new Table(parent, SWT.VIRTUAL | SWT.BORDER | SWT.FULL_SELECTION);

		TableColumn nameCol = new TableColumn(table, SWT.LEFT, 0);
		nameCol.setText("Remained Track");
		nameCol.setWidth(120);

		TableColumn toCol = new TableColumn(table, SWT.LEFT, 1);
		toCol.setText("Collapsed Tracks");
		toCol.setWidth(120 * 6);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		createToolBar();

		table.addListener(SWT.MouseDown, new Listener() {
			public void handleEvent(Event event) {
				org.eclipse.swt.graphics.Point pt = new org.eclipse.swt.graphics.Point(event.x, event.y);
				TableItem item = table.getItem(pt);
				if (item != null) {
					highLight((Track) item.getData());
				}
			}
		});

		DiagramEditPart diagramEditPart = Util.getDiagramEP();
		if (diagramEditPart == null)
			return;
		// TrackPlan trackplan =
		// (TrackPlan)((View)diagramEditPart.getModel()).getElement();

		registerActivatedListener();

		focusListener = new Listener() {

			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if (event.widget != table)
					return;
				unhighLight();
			}

		};
		table.getDisplay().addFilter(SWT.FocusOut, focusListener);
		table.addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent e) {
				// TODO Auto-generated method stub
				table.getDisplay().removeFilter(SWT.FocusOut, focusListener);
			}

		});

	}

	void highLight(Track t) {
		if (highLighted != null)
			unhighLight();
		highLighted = t;
		java.util.List<Track> tracks = new ArrayList<Track>();
		tracks.add(t);
		for (Track t1 : topoAbs.get(t)) {
			tracks.add(t1);
		}
		Util.highLightTracks(tracks, true);
	}

	void unhighLight() {
		if (highLighted == null)
			return;
		java.util.List<Track> tracks = new ArrayList<Track>();
		Track t = highLighted;
		tracks.add(t);
		for (Track t1 : topoAbs.get(t)) {
			tracks.add(t1);
		}
		Util.highLightTracks(tracks, false);
		highLighted = null;
	}

	class SafeTrackLabelProvider extends LabelProvider {
		public String getText(Object track) {
			if (track instanceof Point)
				return "Point " + ((Point) track).getName();
			else if (track instanceof ControlTableItem)
				return "Route " + ((ControlTableItem) track).getRoute();
			else if (track instanceof Track)
				return "Track " + ((Track) track).getName();
			else if (track instanceof Signal)
				return "Signal " + ((Signal) track).getName();
			return "???";
		}
	}

	void createToolBar() {
		IToolBarManager mgr = getViewSite().getActionBars().getToolBarManager();
		mgr.add(new Action("Refresh",
				AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui.ide", "icons/full/elcl16/refresh_nav.png")) {
			public void run() {
				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				if (diagramEditPart == null)
					return;

				// TrackPlan trackplan =
				// (TrackPlan)((View)diagramEditPart.getModel()).getElement();
				refreshTopoAbsTable();
			}
		});
		mgr.add(new Action("Calculate",
				AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui.browser", "icons/clcl16/nav_go.gif")) {
			public void run() {
				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				if (diagramEditPart == null)
					return;

				TrackPlan trackplan = (TrackPlan) ((View) diagramEditPart.getModel()).getElement();

				Hashtable<Track, Track> mapsto = new Hashtable<Track, Track>();
				for (Track t : trackplan.getTracks()) {
					System.out.println("Consider " + t.getName());
					if (t.getPoint() != null || t.getCrossing() != null)
						continue;
					if (t.getSignals().size() > 0)
						continue;
					if (mapsto.keySet().contains(t))
						continue;
					// to the front
					Track t1 = t;
					Connector c1 = t.getC1();
					while (c1 != null) {
						if (c1.getTracks().size() != 2)
							break;
						Track t2 = c1.getTracks().get(0);
						Track t3 = c1.getTracks().get(1);
						Track pre = t2 != t1 ? t2 : t3;
						t1 = pre;
						if (pre.getPoint() != null || pre.getCrossing() != null || pre.getSignals().size() > 0) {
							c1 = null;
						} else {
							mapsto.put(pre, t);
							c1 = pre.getC1() == c1 ? pre.getC2() : pre.getC1();
						}
					}
					// to the back
					t1 = t;
					c1 = t1.getC2();
					while (c1 != null) {
						if (c1.getTracks().size() != 2)
							break;
						Track t2 = c1.getTracks().get(0);
						Track t3 = c1.getTracks().get(1);
						Track pre = t2 != t1 ? t2 : t3;
						t1 = pre;
						if (pre.getPoint() != null || pre.getCrossing() != null || pre.getSignals().size() > 0) {
							c1 = null;
						} else {
							mapsto.put(pre, t);
							c1 = pre.getC1() == c1 ? pre.getC2() : pre.getC1();
						}
					}
				}

				topoAbs = new Hashtable<Track, ArrayList<Track>>();
				for (Track t : mapsto.keySet()) {
					Track base = mapsto.get(t);
					ArrayList<Track> list = topoAbs.get(base);
					if (list == null) {
						list = new ArrayList<Track>();
						topoAbs.put(base, list);
					}
					list.add(t);
				}

				refreshTopoAbsTable();

			}
		});
		mgr.add(new Action("Apply") {
			public void run() {
				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				if (diagramEditPart == null)
					return;

				CompoundCommand cc = new CompoundCommand();
				cc.add(new ICommandProxy(new TrackPlanCoveringClearCommand(diagramEditPart)));
				cc.add(new ICommandProxy(new TrackPlanApplyTopoAbstractionCommand(diagramEditPart, topoAbs)));
				cc.execute();

				// TrackPlan trackplan =
				// (TrackPlan)((View)diagramEditPart.getModel()).getElement();

			}
		});
		
		mgr.add(new Action("Load Layout") {
			public void run() {
				Hashtable<Integer, PrecisionPoint> ht = new Hashtable<Integer, PrecisionPoint>();
				Hashtable<String, PrecisionPoint> ht2 = new Hashtable<String, PrecisionPoint>();

				// first, load the layout file contain position information from
				// brave
				FileDialog dialog = new FileDialog(table.getShell(), SWT.OPEN);
				dialog.setFilterExtensions(new String[] { "*.layout" });
				String result = dialog.open();
				if (result != null) {
					String trace = "";
					BufferedReader br;
					PrecisionPoint topleft = null;
					try {
						br = new BufferedReader(new FileReader(result)); // dialog.getFilterPath()
																			// +
																			// File.separator
																			// +
																			// result));

						// read connector pos(s)
						String line = br.readLine().trim();
						String[] data;
						int cons = Integer.parseInt(line);
						for (int i = 0; i < cons; i++) {
							line = br.readLine().trim();
							data = line.split(",");
							PrecisionPoint pp = new PrecisionPoint(Double.parseDouble(data[1]), Double.parseDouble(data[2]));
							ht.put(Integer.parseInt(data[0]), pp);
							if (topleft==null) {
								topleft = pp;
							}
							else {
								topleft = new PrecisionPoint(PrecisionPoint.min(topleft, pp));
							}
						}

						// read point pos(s)
						line = br.readLine().trim();
						cons = Integer.parseInt(line);
						for (int i = 0; i < cons; i++) {
							line = br.readLine().trim();
							data = line.split(",");
							PrecisionPoint pp = new PrecisionPoint(Double.parseDouble(data[1]), Double.parseDouble(data[2]));
							ht2.put(data[0], pp);
							if (topleft==null) {
								topleft = pp;
							}
							else {
								topleft = new PrecisionPoint(PrecisionPoint.min(topleft, pp));
							}
						}

						// read crossing pos(s)
						line = br.readLine().trim();
						cons = Integer.parseInt(line);
						for (int i = 0; i < cons; i++) {
							line = br.readLine().trim();
							data = line.split(",");
							PrecisionPoint pp = new PrecisionPoint(Double.parseDouble(data[1]), Double.parseDouble(data[2]));
							ht2.put(data[0], pp);
							if (topleft==null) {
								topleft = pp;
							}
							else {
								topleft = new PrecisionPoint(PrecisionPoint.min(topleft, pp));
							}
						}

						// read point pos(s)

						br.close();
					} catch (Exception e) {

					}

					DiagramEditPart diagramEditPart = Util.getDiagramEP();
					if (diagramEditPart == null)
						return;

					for(Object ep : diagramEditPart.getChildren()){
						if (ep instanceof ConnectorEditPart) {
							ConnectorEditPart cep = (ConnectorEditPart)ep;
							Connector con = (Connector)((View)cep.getModel()).getElement();
							int id = con.getId();
							if (ht.get(id)!=null) {
								PrecisionPoint pp = ht.get(id);
								CompoundCommand cc = new CompoundCommand();
								cc.add(new ICommandProxy(new ConnectorUpdateLocationCommand(cep, 
										(int) ((pp.preciseX() - topleft.preciseX())), 
										(int) ((pp.preciseY() - topleft.preciseY())), false)));
								cc.execute();
							}
							else {
								for(Track t : con.getTracks()) {
									if (t.getPoint()!=null) {
										Point p = t.getPoint();
										if (ht2.get(p.getName())!=null) {
											PrecisionPoint pp = ht2.get(p.getName());
											CompoundCommand cc = new CompoundCommand();
											cc.add(new ICommandProxy(new ConnectorUpdateLocationCommand(cep, 
													(int) ((pp.preciseX() - topleft.preciseX())), 
													(int) ((pp.preciseY() - topleft.preciseY())), false)));
											cc.execute();
											
										}
									}
									if (t.getCrossing()!=null) {
										Crossing p = t.getCrossing();
										if (ht2.get(p.getName())!=null) {
											PrecisionPoint pp = ht2.get(p.getName());
											CompoundCommand cc = new CompoundCommand();
											cc.add(new ICommandProxy(new ConnectorUpdateLocationCommand(cep, 
													(int) ((pp.preciseX() - topleft.preciseX())), 
													(int) ((pp.preciseY() - topleft.preciseY())), false)));
											cc.execute();
											
										}
									}
								}
							}
						}
					}
					for(Object ep : diagramEditPart.getChildren()){
						if (ep instanceof EntranceEditPart) {
							EntranceEditPart eep = (EntranceEditPart)ep;
							EntranceConnectorEditPart ecep = (EntranceConnectorEditPart)eep.getSourceConnections().get(0);
							ConnectorEditPart c1 = (ConnectorEditPart) ecep.getTarget();
							ConnectorEditPart c2 = null;
							for(Object o : c1.getTargetConnections()){
								if (o instanceof TrackEditPart) {
									TrackEditPart tep = (TrackEditPart) o;
									c2 = (ConnectorEditPart)tep.getSource();
									break;
								}
							}
							if (c2==null) {
								for(Object o : c1.getSourceConnections()){
									if (o instanceof TrackEditPart) {
										TrackEditPart tep = (TrackEditPart) o;
										c2 = (ConnectorEditPart)tep.getTarget();
										break;
									}
								}
							}
							if (c2!=null) {
								CompoundCommand cc = new CompoundCommand();
								cc.add(new ICommandProxy(new ConnectorUpdateLocationCommand(eep, 
										c2.getLocation().x, 
										c2.getLocation().y, false)));
								cc.add(new ICommandProxy(new ConnectorUpdateLocationCommand(c1, 
										c2.getLocation().x, 
										c2.getLocation().y, false)));
								cc.execute();
							}
						}
						if (ep instanceof ExitEditPart) {
							ExitEditPart eep = (ExitEditPart)ep;
							ExitConnectorEditPart ecep = (ExitConnectorEditPart)eep.getSourceConnections().get(0);
							ConnectorEditPart c1 = (ConnectorEditPart) ecep.getTarget();
							ConnectorEditPart c2 = null;
							for(Object o : c1.getTargetConnections()){
								if (o instanceof TrackEditPart) {
									TrackEditPart tep = (TrackEditPart) o;
									c2 = (ConnectorEditPart)tep.getSource();
									break;
								}
							}
							if (c2==null) {
								for(Object o : c1.getSourceConnections()){
									if (o instanceof TrackEditPart) {
										TrackEditPart tep = (TrackEditPart) o;
										c2 = (ConnectorEditPart)tep.getTarget();
										break;
									}
								}
							}
							if (c2!=null) {
								CompoundCommand cc = new CompoundCommand();
								cc.add(new ICommandProxy(new ConnectorUpdateLocationCommand(eep, 
										c2.getLocation().x, 
										c2.getLocation().y, false)));
								cc.add(new ICommandProxy(new ConnectorUpdateLocationCommand(c1, 
										c2.getLocation().x, 
										c2.getLocation().y, false)));
								cc.execute();
							}
						}
						if (ep instanceof TerminalEditPart) {
							TerminalEditPart tep = (TerminalEditPart)ep;
							TerminalConnectorEditPart tcep = (TerminalConnectorEditPart)tep.getSourceConnections().get(0);
							ConnectorEditPart c1 = (ConnectorEditPart) tcep.getTarget();
							if (c1!=null) {
								CompoundCommand cc = new CompoundCommand();
								cc.add(new ICommandProxy(new ConnectorUpdateLocationCommand(tep, 
										c1.getLocation().x, 
										c1.getLocation().y, false)));
								cc.execute();
							}
						}
					}
					for(Object ep : diagramEditPart.getChildren()){
						if (ep instanceof SignalEditPart) {
							SignalEditPart cep = (SignalEditPart)ep;
							Signal sig = (Signal)((View)cep.getModel()).getElement();
							SignalConnectorEditPart scep = null;
							// SignalTrackEditPart step = null;
							if (cep.getSourceConnections().get(0) instanceof SignalConnectorEditPart) { // assume that signalep has only two connectors
								// one for track and one for connector
								scep = (SignalConnectorEditPart) cep.getSourceConnections().get(0);
								// step = (SignalTrackEditPart) cep.getSourceConnections().get(1);
							} 
							else {
								scep = (SignalConnectorEditPart) cep.getSourceConnections().get(1);
								// step = (SignalTrackEditPart) cep.getSourceConnections().get(0);
							}
							ConnectorEditPart c1 = (ConnectorEditPart)scep.getTarget();
							// TrackEditPart t = (TrackEditPart)step.getTarget();
							// ConnectorEditPart c2 = (ConnectorEditPart) (t.getSource() == c1 ? t.getTarget() : t.getSource());
							// org.eclipse.draw2d.geometry.Point p1 = c1.getLocation();
							// org.eclipse.draw2d.geometry.Point p2 = c2.getLocation();
//							org.eclipse.draw2d.geometry.Point p = p1.getTranslated(-p2.x, -p2.y);
//							p.transpose();
//							p.setX(-p.x);
//							double ratio = 100.0/Math.sqrt(p.x*p.x + p.y*p.y); 
//							p.setX((int) (p.x * ratio));
//							p.setY((int) (p.y * ratio));
//							p = p1.getTranslated(p);
//							int id = con.getId();
							CompoundCommand cc = new CompoundCommand();
							cc.add(new ICommandProxy(new ConnectorUpdateLocationCommand(cep, c1.getLocation().x, c1.getLocation().y, false)));
							cc.execute();
						}
					}
					
					// TrackPlan trackplan =
					// (TrackPlan)((View)diagramEditPart.getModel()).getElement();
				}
			}

		});

	// mgr.add(new Action("Auto"){
	// public void run(){
	// DiagramEditPart diagramEditPart = getDiagramEP();
	// if (diagramEditPart==null) return;
	//
	// CompoundCommand cc = new CompoundCommand();
	// cc.add(new ICommandProxy(new
	// AutoFillReleaseTableCommand(diagramEditPart)));
	// cc.execute();
	//
	// TrackPlan trackplan =
	// (TrackPlan)((View)diagramEditPart.getModel()).getElement();
	//
	// refresReleaseTableFrom(trackplan);
	// }
	// });
	}

	void refreshTopoAbsTable() {
		table.removeAll();
		int reduced = 0;
		for (Track t : topoAbs.keySet()) {
			TableItem tit = new TableItem(table, SWT.NONE);
			ArrayList<String> row = new ArrayList<String>();
			for (Track t1 : topoAbs.get(t)) {
				row.add(t1.getName());
				reduced++;
			}
			tit.setText(new String[] { t.getName(), String.join(", ", row) });
			tit.setData(t);
		}
		System.out.println("Reduced: " + reduced);
	}

	void registerActivatedListener() {
		Util.getActivePage().addPartListener(new PartListener2Impl() {

			@Override
			public void partActivated(IWorkbenchPartReference partRef) {
				// TODO Auto-generated method stub
				IWorkbenchPage page = partRef.getPage();

				if (page == null)
					return;

				IEditorPart editor = page.getActiveEditor();
				if (editor == null)
					return;

				// if (! (editor instanceof DiagramDocumentEditor)) return;

				// DiagramDocumentEditor diagramEditor = (DiagramDocumentEditor)
				// editor;
				// DiagramEditPart diagramEditPart =
				// diagramEditor.getDiagramEditPart();

				// TrackPlan trackplan =
				// (TrackPlan)((View)diagramEditPart.getModel()).getElement();

			}

		});
	}

}
