package uk.ac.swanseacoventry.cmt.ontrack.diagram.view;

import java.util.ArrayList;
import java.util.Hashtable;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
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
import uk.ac.swanseacoventry.cmt.ontrack.Point;
import uk.ac.swanseacoventry.cmt.ontrack.Signal;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.custom.Util;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.TrackPlanApplyTopoAbstractionCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.TrackPlanCoveringClearCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.view.listeners.PartListener2Impl;

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
		toCol.setWidth(120*6);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		createToolBar();
		
		table.addListener(SWT.MouseDown, new Listener(){
			public void handleEvent(Event event){
				org.eclipse.swt.graphics.Point pt = new org.eclipse.swt.graphics.Point(event.x, event.y);
			    TableItem item = table.getItem(pt);
			    if(item != null) {
			    	highLight((Track)item.getData());
			    }
			}
		});
		
		DiagramEditPart diagramEditPart = Util.getDiagramEP();
		if (diagramEditPart==null) return;
		// TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
		
		registerActivatedListener();

		focusListener = new Listener(){

			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if (event.widget != table) return;
				unhighLight();
			}
			
		};
		table.getDisplay().addFilter(SWT.FocusOut, focusListener);
		table.addDisposeListener(new DisposeListener(){

			@Override
			public void widgetDisposed(DisposeEvent e) {
				// TODO Auto-generated method stub
				table.getDisplay().removeFilter(SWT.FocusOut, focusListener);
			}
			
		});
		
	}

	void highLight(Track t) {
		if (highLighted!=null) unhighLight();
		highLighted = t;
		java.util.List<Track> tracks = new ArrayList<Track>();
		tracks.add(t);
		for(Track t1 : topoAbs.get(t)){
			tracks.add(t1);
		}
		Util.highLightTracks(tracks, true);
	}
	
	void unhighLight(){
		if (highLighted == null) return;
		java.util.List<Track> tracks = new ArrayList<Track>();
		Track t = highLighted;
		tracks.add(t);
		for(Track t1 : topoAbs.get(t)){
			tracks.add(t1);
		}
		Util.highLightTracks(tracks, false);
		highLighted = null;
	}

	class SafeTrackLabelProvider extends LabelProvider {
		public String getText(Object track){
			if (track instanceof Point)
				return "Point " + ((Point)track).getName();
			else if (track instanceof ControlTableItem)
				return "Route " + ((ControlTableItem)track).getRoute();
			else if (track instanceof Track)
				return "Track " + ((Track)track).getName();
			else if (track instanceof Signal)
				return "Signal " + ((Signal)track).getName();
			return "???";
		}
	}

	void createToolBar(){
		 IToolBarManager mgr = getViewSite().getActionBars().getToolBarManager();
		 mgr.add(new Action("Refresh", AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui.ide", "icons/full/elcl16/refresh_nav.png")){
			 public void run(){
					DiagramEditPart diagramEditPart = Util.getDiagramEP();
					if (diagramEditPart==null) return;

					// TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
					refreshTopoAbsTable();
			 }
		 });
		 mgr.add(new Action("Calculate", AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui.browser", "icons/clcl16/nav_go.gif")){
			 public void run(){
					DiagramEditPart diagramEditPart = Util.getDiagramEP();
					if (diagramEditPart==null) return;

					TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
					
					Hashtable<Track, Track> mapsto = new Hashtable<Track, Track>();
					for(Track t : trackplan.getTracks()) {
						System.out.println("Consider " + t.getName());
						if (t.getPoint()!=null || t.getCrossing()!=null) continue;
						if (t.getSignals().size() > 0) continue;
						if (mapsto.keySet().contains(t)) continue;
						// to the front
						Track t1 = t;
						Connector c1 = t.getC1();
						while(c1 != null){
							if (c1.getTracks().size() != 2)
								break;
							Track t2 = c1.getTracks().get(0);
							Track t3 = c1.getTracks().get(1);
							Track pre = t2 != t1 ? t2 : t3;
							t1 = pre;
							if (pre.getPoint()!=null || pre.getCrossing()!=null || pre.getSignals().size() > 0) {
								c1 = null;
							}
							else {
								mapsto.put(pre, t);
								c1 = pre.getC1() == c1 ? pre.getC2() : pre.getC1();
							}
						}
						// to the back
						t1 = t;
						c1 = t1.getC2();
						while(c1 != null){
							if (c1.getTracks().size() != 2)
								break;
							Track t2 = c1.getTracks().get(0);
							Track t3 = c1.getTracks().get(1);
							Track pre = t2 != t1 ? t2 : t3;
							t1 = pre;
							if (pre.getPoint()!=null || pre.getCrossing()!=null || pre.getSignals().size() > 0) {
								c1 = null;
							}
							else {
								mapsto.put(pre, t);
								c1 = pre.getC1() == c1 ? pre.getC2() : pre.getC1();
							}
						}
					}
					
					topoAbs = new Hashtable<Track, ArrayList<Track>>();
					for(Track t : mapsto.keySet()){
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
		 mgr.add(new Action("Apply"){
			 public void run(){
				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				if (diagramEditPart==null) return;
				
				CompoundCommand cc = new CompoundCommand();
				cc.add(new ICommandProxy(new TrackPlanCoveringClearCommand(diagramEditPart)));
				cc.add(new ICommandProxy(new TrackPlanApplyTopoAbstractionCommand(diagramEditPart, topoAbs)));
				cc.execute();
				
				// TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();

			 }
		 });
		 

//		 mgr.add(new Action("Auto"){
//			 public void run(){
//				 DiagramEditPart diagramEditPart = getDiagramEP();
//					if (diagramEditPart==null) return;
//
//					CompoundCommand cc = new CompoundCommand();
//					cc.add(new ICommandProxy(new AutoFillReleaseTableCommand(diagramEditPart)));
//					cc.execute();
//					
//					TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
//
//					refresReleaseTableFrom(trackplan);
//			 }
//		 });
	}

	void refreshTopoAbsTable(){
		table.removeAll();
		int reduced = 0;
		for(Track t : topoAbs.keySet()) {
			TableItem tit = new TableItem(table,SWT.NONE);
			ArrayList<String> row = new ArrayList<String>();
			for(Track t1 : topoAbs.get(t)) {
				row.add(t1.getName());
				reduced++;
			}
			tit.setText(new String[] {t.getName(), String.join(", ", row)});
			tit.setData(t);
		}
		System.out.println("Reduced: " + reduced);
	}
		
	void registerActivatedListener(){
		Util.getActivePage().addPartListener(new PartListener2Impl(){

			@Override
			public void partActivated(IWorkbenchPartReference partRef) {
				// TODO Auto-generated method stub
				IWorkbenchPage page = partRef.getPage();
				   
				if (page == null) return;
				
				IEditorPart editor = page.getActiveEditor();
				if (editor == null) return;
				
				
				// if (! (editor instanceof DiagramDocumentEditor)) return;
				
				// DiagramDocumentEditor diagramEditor = (DiagramDocumentEditor) editor;
				// DiagramEditPart diagramEditPart = diagramEditor.getDiagramEditPart();
				
				// TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
				
			}

		});
	}

}
