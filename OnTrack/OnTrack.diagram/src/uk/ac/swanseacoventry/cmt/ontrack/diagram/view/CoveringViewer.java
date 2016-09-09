package uk.ac.swanseacoventry.cmt.ontrack.diagram.view;

import java.util.ArrayList;

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
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.Point;
import uk.ac.swanseacoventry.cmt.ontrack.Signal;
import uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.custom.Util;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.TrackPlanCoveringClearCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.TrackPlanCoveringComputeCommand;

public class CoveringViewer extends ViewPart {
	private Table table;
	private Listener focusListener;
	private SubTrackPlan highLighted;
	
	public CoveringViewer() {
		super();
    }

	public void setFocus() {
		table.setFocus();
    }

	public void createPartControl(Composite parent) {
		table = new Table(parent, SWT.VIRTUAL | SWT.BORDER | SWT.FULL_SELECTION);

		TableColumn nameCol = new TableColumn(table, SWT.LEFT, 0);
		nameCol.setText("SubPlan");
		nameCol.setWidth(120);
		

		TableColumn toCol = new TableColumn(table, SWT.LEFT, 1);
		toCol.setText("Tracks");
		toCol.setWidth(120*6);

		TableColumn seqCol = new TableColumn(table, SWT.LEFT, 2);
		seqCol.setText("Point");
		seqCol.setWidth(120);

		TableColumn signalCol = new TableColumn(table, SWT.LEFT, 3);
		signalCol.setText("Signal");
		signalCol.setWidth(120);

		TableColumn routeCol = new TableColumn(table, SWT.LEFT, 4);
		routeCol.setText("Route");
		routeCol.setWidth(120*2);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		createToolBar();
		
		table.addListener(SWT.MouseDown, new Listener(){
			public void handleEvent(Event event){
				org.eclipse.swt.graphics.Point pt = new org.eclipse.swt.graphics.Point(event.x, event.y);
			    TableItem item = table.getItem(pt);
			    if(item != null) {
			    	highLight((SubTrackPlan)item.getData());
			    }
			}
		});
		
		DiagramEditPart diagramEditPart = Util.getDiagramEP();
		if (diagramEditPart==null) return;
		TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
		refresSubPlansFrom(trackplan);
		
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

	void highLight(SubTrackPlan i) {
		if (highLighted!=null) unhighLight();
		highLighted = i;
		java.util.List<Track> tracks = new ArrayList<Track>();
		for(Track t : highLighted.getTracks()){
			tracks.add(t);
		}
		Util.highLightTracks(tracks, true);
	}
	
	void unhighLight(){
		if (highLighted == null) return;
		java.util.List<Track> tracks = new ArrayList<Track>();
		for(Track t : highLighted.getTracks()){
			tracks.add(t);
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
		 mgr.add(new Action("Load"){
			 public void run(){
					DiagramEditPart diagramEditPart = Util.getDiagramEP();
					if (diagramEditPart==null) return;

					TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
					refresSubPlansFrom(trackplan);
			 }
		 });
		 mgr.add(new Action("Cover"){
			 public void run(){
					DiagramEditPart diagramEditPart = Util.getDiagramEP();
					if (diagramEditPart==null) return;

					CompoundCommand cc = new CompoundCommand();
					cc.add(new ICommandProxy(new TrackPlanCoveringClearCommand(diagramEditPart)));
					cc.add(new ICommandProxy(new TrackPlanCoveringComputeCommand(diagramEditPart)));
					cc.execute();
					
					TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();

					refresSubPlansFrom(trackplan);
				 
			 }
		 });
		 mgr.add(new Action("Clear"){
			 public void run(){
				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				if (diagramEditPart==null) return;
				
				CompoundCommand cc = new CompoundCommand();
				cc.add(new ICommandProxy(new TrackPlanCoveringClearCommand(diagramEditPart)));
				cc.execute();
				
				TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();

				refresSubPlansFrom(trackplan);
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

	void refresSubPlansFrom(TrackPlan trackplan){
		table.removeAll();
		
		for(SubTrackPlan stp : trackplan.getSubTrackPlans()){
			TableItem tit = new TableItem(table,SWT.NONE);
			tit.setText(new String[]{
					stp.getName(),
					stp.getTrackList(),
					stp.getPointList(),
					stp.getSignalList(),
					stp.getRouteList()}
					);
			tit.setData(stp);
		}
	}
	void registerActivatedListener(){
		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
		IWorkbenchPage page = win.getActivePage();
		page.addPartListener(new IPartListener2(){

			@Override
			public void partActivated(IWorkbenchPartReference partRef) {
				// TODO Auto-generated method stub
				partRef.getPage();
				   
				if (page == null) return;
				
				IEditorPart editor = page.getActiveEditor();
				if (editor == null) return;
				
				
				if (! (editor instanceof DiagramDocumentEditor)) return;
				
				DiagramDocumentEditor diagramEditor = (DiagramDocumentEditor) editor;
				DiagramEditPart diagramEditPart = diagramEditor.getDiagramEditPart();
				
				TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
				
				refresSubPlansFrom(trackplan);
			}

			@Override
			public void partBroughtToTop(IWorkbenchPartReference partRef) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void partClosed(IWorkbenchPartReference partRef) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void partDeactivated(IWorkbenchPartReference partRef) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void partOpened(IWorkbenchPartReference partRef) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void partHidden(IWorkbenchPartReference partRef) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void partVisible(IWorkbenchPartReference partRef) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void partInputChanged(IWorkbenchPartReference partRef) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

}