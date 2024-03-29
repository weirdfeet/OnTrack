package uk.ac.swanseacoventry.cmt.ontrack.diagram.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.Point;
import uk.ac.swanseacoventry.cmt.ontrack.Signal;
import uk.ac.swanseacoventry.cmt.ontrack.Simulation;
import uk.ac.swanseacoventry.cmt.ontrack.SimulationAction;
import uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.custom.Util;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.SimulationAddCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.SimulationDeleteCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.view.listeners.PartListener2Impl;

public class SimulationViewer extends ViewPart {

	private static final Color RED = new Color(null, new RGB(228, 26, 28));
	private static final Color GREEN = new Color(null, new RGB(57, 185, 54));
	private static final Color BLUE = new Color(null, new RGB(35, 106, 194));
	
	private Table table;
	private Listener focusListener;
	// private SimulationAction highLightedAction;
	private Simulation currentSimulation;
	private Hashtable<Track, Color> highLighted;
	private Action btnSims;
	private SimulationMenu menu = null;
	private EventMenu eventMenu;
	private int simCount;// Index in table with simulation steps.

	public SimulationViewer() {
		super();
	}

	public void setFocus() {
		table.setFocus();
	}

	public void createPartControl(Composite parent) {
		table = new Table(parent, SWT.VIRTUAL | SWT.BORDER | SWT.FULL_SELECTION);

		TableColumn nameCol = new TableColumn(table, SWT.LEFT, 0);
		nameCol.setText("Played");
		nameCol.setWidth(60);

		TableColumn actionCol = new TableColumn(table, SWT.LEFT, 1);
		actionCol.setText("Action");
		actionCol.setWidth(120);

		TableColumn paramCol = new TableColumn(table, SWT.LEFT, 2);
		paramCol.setText("Parameters");
		paramCol.setWidth(120 * 6);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		createToolBar();

		table.addListener(SWT.MouseDown, new Listener() {
			public void handleEvent(Event event) {
				org.eclipse.swt.graphics.Point pt = new org.eclipse.swt.graphics.Point(event.x, event.y);
				TableItem item = table.getItem(pt);
				selectTableItem(item);
			}
		});

		table.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				int cur = table.getSelectionIndex();
				if (cur >= 0)
					selectTableItem(table.getItem(cur));
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				int cur = table.getSelectionIndex();
				if (cur >= 0)
					selectTableItem(table.getItem(cur));
			}

		});

		DiagramEditPart diagramEditPart = Util.getDiagramEP();
		if (diagramEditPart == null)
			return;
		TrackPlan trackplan = (TrackPlan) ((View) diagramEditPart.getModel()).getElement();

		refreshSimulationActions(trackplan);

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

	void highLight(Hashtable<Track, Color> i) {
		if (highLighted != null)
			unhighLight();
		highLighted = i;
		Util.multiHighLightTracks(highLighted);
	}

	void unhighLight() {
		if (highLighted == null)
			return;
		java.util.List<Track> tracks = new ArrayList<Track>();
		for (Track t : highLighted.keySet()) {
			tracks.add(t);
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

				TrackPlan trackplan = (TrackPlan) ((View) diagramEditPart.getModel()).getElement();
				refreshSimulationActions(trackplan);
			}
		});
		mgr.add(btnSims = new Action("Simulations", IAction.AS_DROP_DOWN_MENU) {
			{
				// setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui",
				// "icons/full/etool16/editor_area.png"));
				menu = new SimulationMenu(SimulationViewer.this);
				this.setMenuCreator(menu);
				menu.SetAction(this);
			}

			public void run() {
				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				if (diagramEditPart == null)
					return;

				TrackPlan trackplan = (TrackPlan) ((View) diagramEditPart.getModel()).getElement();
				refreshSimulationActions(trackplan);
			}

		});

		// mgr.add(new Action("Play") { // ,
		// // AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui",
		// // "icons/full/elcl16/trash.png")){
		// public void run() {
		// if (table.getItemCount() <= 0)
		// return;
		// int cur = table.getSelectionIndex();
		// TableItem last = null;
		// for (int i = 0; i <= cur; i++) {
		// table.setSelection(i);
		// TableItem item = table.getItem(i);
		// if (item.getData() == null) {
		// item.setData(calculateDiplay(last, item));
		// selectTableItem(item);
		// }
		// last = item;
		// }
		// }
		// });

		mgr.add(new Action("Play all") { // ,
			// AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui",
			// "icons/full/elcl16/trash.png")){
			public void run() {
				if (table.getItemCount() <= 0)
					return;
				for (int i = 0; i < table.getItemCount(); i++) {
					table.setSelection(i);
					TableItem item = table.getItem(i);
					if (item.getData() == null) {
						item.setData(calculateDiplay(i > 0 ? table.getItem(i - 1) : null, item));
						selectTableItem(item);
					}
				}
			}
		});

		// mgr.add(new Action("Next") { // ,
		// // AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui",
		// // "icons/full/elcl16/trash.png")){
		// public void run() {
		// if (table.getItemCount() <= 0)
		// return;
		// int cur = table.getSelectionIndex();
		// TableItem last = table.getItem(cur);
		// if (last.getData() != null) { // can only next from a calculated
		// // item
		// if (cur < table.getItemCount() - 1) {
		// table.setSelection(cur + 1);
		// TableItem item = table.getItem(cur + 1);
		// if (item.getData() == null)
		// item.setData(calculateDiplay(last, item));
		// selectTableItem(item);
		// }
		// }
		// }
		// });

		mgr.add(new Action("Del") { // ,
									// AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui",
									// "icons/full/elcl16/trash.png")){
			public void run() {
				if (currentSimulation == null)
					return;

				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				if (diagramEditPart == null)
					return;

				CompoundCommand cc = new CompoundCommand();
				cc.add(new ICommandProxy(new SimulationDeleteCommand(diagramEditPart, currentSimulation)));
				cc.execute();

				TrackPlan trackplan = (TrackPlan) ((View) diagramEditPart.getModel()).getElement();

				currentSimulation = null;
				btnSims.setText("Simulations");

				refreshSimulationActions(trackplan);
			}
		});
		eventMenu = new EventMenu(this);
		mgr.add(new Action("Filter", IAction.AS_DROP_DOWN_MENU) {
			{
				this.setMenuCreator(eventMenu);
			}
		});

		mgr.add(new Action("Next") { // ,
			public void run() {
				gotoTableItem();
			}
		});

		mgr.add(new Action("Reset") { // ,
			public void run() {
				hacky_resetSimulation();
			}
		});

		mgr.add(new Action("Load") { // ,
										// AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui",
										// "icons/full/elcl16/trash.png")){
			public void run() {
				FileDialog dialog = new FileDialog(table.getShell(), SWT.OPEN | SWT.MULTI);
				dialog.setFilterExtensions(new String[] { "*.txt" });
				if (dialog.open() != null) {
					String[] results = dialog.getFileNames();

					for (String result : results) {
						String trace = "";
						BufferedReader br;
						try {
							br = new BufferedReader(new FileReader(dialog.getFilterPath() + File.separator + result));
							String line = br.readLine();

							while (line != null) {
								trace += line.trim();
								line = br.readLine();
							}
							br.close();
						} catch (Exception e) {

						}

						if (!trace.isEmpty()) {
							ArrayList<String[]> ce = Util.extractFDRCounterExample(trace);
							DiagramEditPart diagramEditPart = Util.getDiagramEP();
							if (diagramEditPart != null) {
								File f = new File(result);
								String name = f.getName();
								int lastDot = name.lastIndexOf(".");
								if (lastDot >= 0)
									name = name.substring(0, lastDot);
								SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
								name += " loaded @ " + dateformat.format(new java.util.Date());
								CompoundCommand cc = new CompoundCommand();
								cc.add(new ICommandProxy(new SimulationAddCommand(diagramEditPart, name, ce)));
								cc.execute();
							}
						}
					}
				}
			}
		});

	}

	private void gotoTableItem() {
		if (table.getItemCount() <= 0)
			return;
		for (; simCount < table.getItemCount(); simCount++) {
			table.setSelection(simCount);
			TableItem item = table.getItem(simCount);
			if (item.getData() == null) {
				item.setData(calculateDiplay(simCount > 0 ? table.getItem(simCount - 1) : null, item));
				selectTableItem(item);
			}
			if (eventMenu.getSelectedEvents().isEmpty() || eventMenu.getSelectedEvents().contains(item.getText(1))) {
				simCount++;
				break;
			}
		}
	}

	private void hacky_resetSimulation() {
		DiagramEditPart diagramEditPart = Util.getDiagramEP();
		if (diagramEditPart == null)
			return;
		TrackPlan trackplan = (TrackPlan) ((View) diagramEditPart.getModel()).getElement();
		refreshSimulationActions(trackplan);
		simCount = 0;
		unhighLight();
	}

	protected Hashtable<Track, Color> calculateDiplay(TableItem last, TableItem item) {
		Hashtable<Track, Color> prev = null;
		Hashtable<Track, Color> ret = null;
		if (last == null)
			prev = new Hashtable<Track, Color>();
		else
			prev = (Hashtable<Track, Color>) last.getData();

		if (prev == null)
			return null;

		DiagramEditPart diagramEditPart = Util.getDiagramEP();
		if (diagramEditPart == null)
			return null;
		TrackPlan trackplan = (TrackPlan) ((View) diagramEditPart.getModel()).getElement();

		ret = new Hashtable<Track, Color>();
		ret.putAll(prev);

		String action = item.getText(1);
		String[] params = item.getText(2).split(", ");

		if (action.equals("setRoute")) {
			for (ControlTableItem cti : trackplan.getControlTable()) {
				if (cti.getRoute().equals(params[0])) {
					for (Track t : cti.getClears())
						addTrackToHT(t, GREEN, ret);
					break;
				}
			}
		} else if (action.equals("move") || action.equals("hangMove")) {
			Track t1 = null;
			Track t2 = null;
			for (Track t : trackplan.getTracks()) {
				if (t.getName().equals(params[0]))
					t1 = t;
				if (t.getName().equals(params[1]))
					t2 = t;
				if (t1 != null && t2 != null)
					break;
			}
			if (t1 != null)
				addTrackToHT(t1, ColorConstants.gray, ret);
			// ret.put(t1, ColorConstants.gray);
			if (t2 != null)
				addTrackToHT(t2, BLUE, ret);
			// ret.put(t2, ColorConstants.blue);
		} else if (action.equals("collided")) {
			Track t1 = null;
			for (Track t : trackplan.getTracks()) {
				if (t.getName().equals(params[0])) {
					t1 = t;
					break;
				}
			}
			if (t1 != null)
				addTrackToHT(t1, RED, ret);
			// ret.put(t1, ColorConstants.red);
		} else if (action.equals("ranthru") || action.equals("derailed")) {
			Track t1 = null;
			Track t2 = null;
			for (Point p : trackplan.getPoints()) {
				if (p.getName().equals(params[0])) {
					t1 = p.getNormalTrack();
					t2 = p.getReverseTrack();
					break;
				}
			}
			if (t1 != null)
				addTrackToHT(t1, RED, ret);

			// ret.put(t1, ColorConstants.red);
			if (t2 != null)
				addTrackToHT(t2, RED, ret);

			// ret.put(t2, ColorConstants.red);
		}

		item.setText(0, "P");

		return ret;
	}

	private void addTrackToHT(Track t, Color c, Hashtable<Track, Color> h) {
		h.put(t, c);
		if (t.getPoint() != null) {
			Point p = t.getPoint();
			if (p.getNormalTrack() != t)
				h.put(p.getNormalTrack(), c);
			else
				h.put(p.getReverseTrack(), c);
		}
	}

	class SimulationMenu implements IMenuCreator, SelectionListener {
		private Menu simMenu;
		private SimulationViewer viewer;
		private Action btn;

		public void SetAction(Action act) {
			btn = act;
		}

		public SimulationMenu(SimulationViewer parent) {
			super();
			this.viewer = parent;
		}

		@Override
		public void dispose() {
			if (simMenu != null)
				simMenu.dispose();
			simMenu = null;
		}

		public void reloadSimulations() {
			if (simMenu == null)
				return;

			// clear the currently loaded sims
			while (simMenu.getItemCount() > 0) {
				simMenu.getItem(0).dispose();
			}

			DiagramEditPart diagramEditPart = Util.getDiagramEP();
			if (diagramEditPart != null) {
				TrackPlan trackplan = (TrackPlan) ((View) diagramEditPart.getModel()).getElement();
				// reload them
				for (Simulation sim : trackplan.getSimulations()) {
					MenuItem sim1 = new MenuItem(simMenu, SWT.PUSH);
					String name = sim.getName() == null ? "No name" : sim.getName();
					sim1.setText(name);
					sim1.setData(sim);
					sim1.addSelectionListener(this);
				}
			}
		}

		@Override
		public Menu getMenu(Control parent) {
			System.out.println("Called");
			simMenu = new Menu(parent);
			reloadSimulations();
			return simMenu;
		}

		@Override
		public Menu getMenu(Menu parent) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void widgetSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			Simulation s = (Simulation) e.widget.getData(); // (Simulation)
															// e.data;
			selectSimulation(s);

		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			Simulation s = (Simulation) e.data;
			selectSimulation(s);
		}

		private void selectSimulation(Simulation sim) {
			System.out.println("Select -> " + sim.getName());
			viewer.updateSimulationSelection(sim);
			String name = sim.getName().split("@")[0];
			this.btn.setText(name);
			viewer.eventMenu.resetMenu(true);
		}
	}

	class EventMenu implements IMenuCreator, SelectionListener {
		private Menu events;
		private final Set<String> selectedEvents = new HashSet<>();

		public EventMenu(SimulationViewer parent) {
			super();
		}

		@Override
		public void dispose() {
			if (events != null)
				events.dispose();
			events = null;
		}

		@Override
		public Menu getMenu(Control parent) {
			if (events == null) {
				events = new Menu(parent);
				resetMenu(true);
			}
			return events;
		}

		@Override
		public Menu getMenu(Menu parent) {
			return null;
		}

		@Override
		public void widgetSelected(SelectionEvent e) {
			if (((MenuItem) e.widget).getSelection()) {
				selectedEvents.add((String) e.widget.getData());
			} else {
				selectedEvents.remove((String) e.widget.getData());
			}
			((MenuItem) e.widget).setSelection(((MenuItem) e.widget).getSelection());
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {}

		public Set<String> getSelectedEvents() {
			return Collections.unmodifiableSet(selectedEvents);
		}

		private void resetMenu(boolean hard) {
			if (events == null)
				return;
			if (currentSimulation == null) {
				System.err.println("No simulation selected!");
			} else {
				selectedEvents.clear();
				Set<String> names = new HashSet<>();
				for (SimulationAction s : currentSimulation.getSteps())
					names.add(s.getName());
				if (hard)
					for (MenuItem i : events.getItems())
						i.dispose();
				for (String s : names) {
					MenuItem i = new MenuItem(events, SWT.CHECK);
					i.addSelectionListener(this);
					i.setText(s);
					i.setData(s);
				}
			}
		}
	}

	void refreshSimulationActions(TrackPlan trackplan) {
		menu.reloadSimulations();
		table.removeAll();
		if (currentSimulation == null)
			return;

		for (SimulationAction simAct : currentSimulation.getSteps()) {
			TableItem tit = new TableItem(table, SWT.NONE);
			tit.setText(new String[] { "", simAct.getName(), String.join(", ", simAct.getParameters()) });
			tit.setData(null);
		}
		// eventMenu.resetMenu();
	}

	boolean checkSubPlanSubsumption(SubTrackPlan stp, SubTrackPlan stp2) {
		HashSet<Track> trs1 = new HashSet<Track>(stp.getTracks());
		HashSet<Track> trs2 = new HashSet<Track>(stp2.getTracks());
		return (trs2.containsAll(trs1)) && (!trs1.containsAll(trs2));
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

				if (!(editor instanceof DiagramDocumentEditor))
					return;

				DiagramDocumentEditor diagramEditor = (DiagramDocumentEditor) editor;
				DiagramEditPart diagramEditPart = diagramEditor.getDiagramEditPart();

				TrackPlan trackplan = (TrackPlan) ((View) diagramEditPart.getModel()).getElement();

				refreshSimulationActions(trackplan);
			}

		});
	}

	public void updateSimulationSelection(Simulation sim) {
		if (sim != currentSimulation) {
			currentSimulation = sim;
			eventMenu.resetMenu(true);
			DiagramEditPart diagramEditPart = Util.getDiagramEP();
			if (diagramEditPart != null) {
				TrackPlan trackplan = (TrackPlan) ((View) diagramEditPart.getModel()).getElement();
				refreshSimulationActions(trackplan);
			}
			hacky_resetSimulation();
		}
	}

	public void playUntil(TableItem ti) {

	}

	public void selectTableItem(TableItem item) {
		if (item != null) {
			if (item.getData() != null && item.getData() instanceof Hashtable)
				highLight((Hashtable<Track, Color>) item.getData());
		}
	}

}
