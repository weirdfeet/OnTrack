package uk.ac.swanseacoventry.cmt.ontrack.diagram.view;

import java.util.ArrayList;

import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
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
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.Point;
import uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.Signal;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.custom.Util;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.ReleaseTableAutoFillCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.ReleaseTableClearCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.ReleaseTableDelItemCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.ReleaseTableInitCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.ReleaseTableUpdateItemCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.view.listeners.PartListener2Impl;

public class ReleaseTableViewer extends ViewPart {
	private Table table;
	private Listener focusListener;
	private ReleaseTableItem highLighted;
	
	public ReleaseTableViewer() {
		super();
    }

	public void setFocus() {
		table.setFocus();
    }

	public void createPartControl(Composite parent) {
		table = new Table(parent, SWT.VIRTUAL | SWT.BORDER | SWT.FULL_SELECTION);

		TableColumn fromCol = new TableColumn(table, SWT.LEFT, 0);
		fromCol.setText("Route");
		fromCol.setWidth(120);

		TableColumn toCol = new TableColumn(table, SWT.LEFT, 1);
		toCol.setText("Point");
		toCol.setWidth(120);

		TableColumn seqCol = new TableColumn(table, SWT.LEFT, 2);
		seqCol.setText("Unoccupied Track");
		seqCol.setWidth(120);

		TableColumn oCol = new TableColumn(table, SWT.LEFT, 3);
		oCol.setText("Occupied Track");
		oCol.setWidth(120);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		createToolBar();
		
		table.addListener(SWT.MouseDown, new Listener(){
			public void handleEvent(Event event){
				org.eclipse.swt.graphics.Point pt = new org.eclipse.swt.graphics.Point(event.x, event.y);
			    TableItem item = table.getItem(pt);
			    if(item != null) {
			    	if (item.getBounds(2).contains(pt)) {
				    	editUnoccupiedTrack((ReleaseTableItem)item.getData());
				    }
			    	else if (item.getBounds(3).contains(pt)) {
				    	editOccupiedTrack((ReleaseTableItem)item.getData());
				    }
			    	else {
						highLight((ReleaseTableItem)item.getData());
			    	}
			    }
			}
		});
		DiagramEditPart diagramEditPart = Util.getDiagramEP();
		if (diagramEditPart==null) return;
		TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
		refreshReleaseTableFrom(trackplan);
		
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
	
	void editUnoccupiedTrack(ReleaseTableItem rti) {
    	DiagramEditPart diagramEditPart = Util.getDiagramEP();
		if (diagramEditPart==null) return;

		TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
		
		//ArrayList<Signal> inputs = new ArrayList<Signal>();
		Track[] inputs = new Track[trackplan.getTracks().size() - trackplan.getPoints().size() - trackplan.getCrossings().size()];
		int i = 0;
		for(Track t : trackplan.getTracks())
			if (t.getPointReverse()==null && t.getCrossing2()==null)
				inputs[i++] = t;
		
		ElementListSelectionDialog  dialog = new ElementListSelectionDialog(table.getShell(), new SafeTrackLabelProvider()); 
		dialog.setTitle("Select a track");
		dialog.setElements(inputs);
		dialog.open();
		CompoundCommand cc = new CompoundCommand();
		cc.add(new ICommandProxy(new ReleaseTableUpdateItemCommand((IGraphicalEditPart)diagramEditPart,rti,(Track)dialog.getFirstResult(), null)));
		cc.execute();
		refreshReleaseTableFrom(trackplan);
		
	}

	void editOccupiedTrack(ReleaseTableItem rti) {
    	DiagramEditPart diagramEditPart = Util.getDiagramEP();
		if (diagramEditPart==null) return;

		TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
		
		//ArrayList<Signal> inputs = new ArrayList<Signal>();
		Track[] inputs = new Track[trackplan.getTracks().size() - trackplan.getPoints().size() - trackplan.getCrossings().size()];
		int i = 0;
		for(Track t : trackplan.getTracks())
			if (t.getPointReverse()==null && t.getCrossing2()==null)
				inputs[i++] = t;
		
		ElementListSelectionDialog  dialog = new ElementListSelectionDialog(table.getShell(), new SafeTrackLabelProvider()); 
		dialog.setTitle("Select a track");
		dialog.setElements(inputs);
		dialog.open();
		CompoundCommand cc = new CompoundCommand();
		cc.add(new ICommandProxy(new ReleaseTableUpdateItemCommand((IGraphicalEditPart)diagramEditPart,rti,null, (Track)dialog.getFirstResult())));
		cc.execute();
		refreshReleaseTableFrom(trackplan);
		
	}

	void highLight(ReleaseTableItem i) {
		if (highLighted!=null) unhighLight();
		highLighted = i;
		java.util.List<Track> tracks = new ArrayList<Track>();
		
		Track t = highLighted.getUnoccupiedTrack();
		if (t==null) return; 
		tracks.add(t);
		if (t.getPoint()!=null) {
			tracks.add(t.getPoint().getNormalTrack());
			tracks.add(t.getPoint().getReverseTrack());
		}
		if (t.getCrossing()!=null) {
			tracks.add(t.getCrossing().getTrack1());
			tracks.add(t.getCrossing().getTrack2());
		}
		t = highLighted.getOccupiedTrack();
		if (t==null) return; 
		tracks.add(t);
		if (t.getPoint()!=null) {
			tracks.add(t.getPoint().getNormalTrack());
			tracks.add(t.getPoint().getReverseTrack());
		}
		if (t.getCrossing()!=null) {
			tracks.add(t.getCrossing().getTrack1());
			tracks.add(t.getCrossing().getTrack2());
		}
		Util.highLightTracks(tracks, true);
	}
	
	void unhighLight(){
		if (highLighted == null) return;
		java.util.List<Track> tracks = new ArrayList<Track>();
		Track t = highLighted.getUnoccupiedTrack();
		if (t==null) return; 
		tracks.add(t);
		if (t.getPoint()!=null) {
			tracks.add(t.getPoint().getNormalTrack());
			tracks.add(t.getPoint().getReverseTrack());
		}
		if (t.getCrossing()!=null) {
			tracks.add(t.getCrossing().getTrack1());
			tracks.add(t.getCrossing().getTrack2());
		}
		t = highLighted.getOccupiedTrack();
		if (t==null) return; 
		tracks.add(t);
		if (t.getPoint()!=null) {
			tracks.add(t.getPoint().getNormalTrack());
			tracks.add(t.getPoint().getReverseTrack());
		}
		if (t.getCrossing()!=null) {
			tracks.add(t.getCrossing().getTrack1());
			tracks.add(t.getCrossing().getTrack2());
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

					TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
					refreshReleaseTableFrom(trackplan);
			 }
		 });
		 mgr.add(new Action("Initialise", AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui.cheatsheets", "icons/elcl16/start_cheatsheet.gif")){
			 public void run(){
					DiagramEditPart diagramEditPart = Util.getDiagramEP();
					if (diagramEditPart==null) return;

					CompoundCommand cc = new CompoundCommand();
					cc.add(new ICommandProxy(new ReleaseTableInitCommand(diagramEditPart)));
					cc.execute();
					
					TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();

					refreshReleaseTableFrom(trackplan);
				 
			 }
		 });
		 mgr.add(new Action("Auto-fill", AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui.browser", "icons/clcl16/nav_go.gif")){
			 public void run(){
				 DiagramEditPart diagramEditPart = Util.getDiagramEP();
					if (diagramEditPart==null) return;

					CompoundCommand cc = new CompoundCommand();
					cc.add(new ICommandProxy(new ReleaseTableAutoFillCommand(diagramEditPart)));
					cc.execute();
					
					TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();

					refreshReleaseTableFrom(trackplan);
			 }
		 });
		 mgr.add(new Action("Delele", AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "icons/full/obj16/delete_obj.png")){
			 public void run(){
				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				if (diagramEditPart==null) return;
				
				for(TableItem item : table.getSelection()){
					CompoundCommand cc = new CompoundCommand();
					cc.add(new ICommandProxy(new ReleaseTableDelItemCommand(diagramEditPart,(ReleaseTableItem)item.getData())));
					cc.execute();
				}
				
				TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();

				refreshReleaseTableFrom(trackplan);
			 }
		 });
		 
		 mgr.add(new Action("Clear", AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "icons/full/elcl16/trash.png")){
			 public void run(){
				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				if (diagramEditPart==null) return;
				
				CompoundCommand cc = new CompoundCommand();
				cc.add(new ICommandProxy(new ReleaseTableClearCommand(diagramEditPart)));
				cc.execute();
				
				TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();

				refreshReleaseTableFrom(trackplan);
			 }
		 });
	}

	void refreshReleaseTableFrom(TrackPlan trackplan){
		table.removeAll();
		
		for(ReleaseTableItem rti : trackplan.getReleaseTable()){
			TableItem tit = new TableItem(table,SWT.NONE);
			tit.setText(new String[]{
					rti.getRoute(),
					rti.getPoint().getName(),
					rti.getUnoccupiedTrack()!=null ? rti.getUnoccupiedTrack().getName():"", 
					rti.getOccupiedTrack()!=null ? rti.getOccupiedTrack().getName():""}
					);
			tit.setData(rti);
		}
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
				
				
				if (! (editor instanceof DiagramDocumentEditor)) return;
				
				DiagramDocumentEditor diagramEditor = (DiagramDocumentEditor) editor;
				DiagramEditPart diagramEditPart = diagramEditor.getDiagramEditPart();
				
				TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
				
				refreshReleaseTableFrom(trackplan);
			}			
		});
	}

}
