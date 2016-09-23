package uk.ac.swanseacoventry.cmt.ontrack.diagram.view;

import java.util.ArrayList;

import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import uk.ac.swanseacoventry.cmt.ontrack.DirectedTrack;
import uk.ac.swanseacoventry.cmt.ontrack.TopoRoute;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.custom.Util;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.TopoRoutesClearCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.TopoRoutesDelItemCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.TopoRoutesInitCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.TopoRoutesSetNameCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.view.listeners.PartListener2Impl;
import uk.ac.swanseacoventry.cmt.ontrack.impl.TopoRouteImpl;

public class TopoRouteViewer extends ViewPart {
	private Table table;
	private Listener focusListener;
	
	public TopoRouteViewer() {
		super();
    }

	public void setFocus() {
		table.setFocus();
    }

	public void createPartControl(Composite parent) {
		table = new Table(parent, SWT.VIRTUAL | SWT.BORDER | SWT.FULL_SELECTION);

		TableColumn fromCol = new TableColumn(table, SWT.LEFT, 0);
		fromCol.setText("From");
		fromCol.setWidth(120);

		TableColumn toCol = new TableColumn(table, SWT.LEFT, 1);
		toCol.setText("To");
		toCol.setWidth(120);

		TableColumn seqCol = new TableColumn(table, SWT.LEFT, 2);
		seqCol.setText("Detail");
		seqCol.setWidth(600);

		TableColumn nameCol = new TableColumn(table, SWT.LEFT, 3);
		nameCol.setText("Name");
		nameCol.setWidth(120);
		
		// prepare to allow direct edit route name
		final TableEditor editor = new TableEditor(table);
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		editor.minimumWidth = 120-4;

		final int NAMECOL = 3;
		

		table.addListener(SWT.MouseDown, new Listener(){
			public void handleEvent(Event event){
			    Point pt = new Point(event.x, event.y);
			    TableItem item = table.getItem(pt);
				
				if(item != null) {
			        if (item.getBounds(NAMECOL).contains(pt)){
						Control oldEditor = editor.getEditor();
						if (oldEditor != null) oldEditor.dispose();

						DiagramEditPart diagramEditPart = Util.getDiagramEP();
						if (diagramEditPart==null) return;
						TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();

						// The control that will be the editor must be a child of the Table
						Text newEditor = new Text(table, SWT.NONE);
						newEditor.setText(item.getText(NAMECOL));
						newEditor.addModifyListener(me -> {
							Text text = (Text)editor.getEditor();
							editor.getItem().setText(NAMECOL, text.getText());
							

							CompoundCommand cc = new CompoundCommand();
							cc.add(new ICommandProxy(new TopoRoutesSetNameCommand(diagramEditPart,(TopoRoute)item.getData(),text.getText().split(","))));
							cc.execute();
						});
						newEditor.selectAll();
						newEditor.setFocus();
						editor.setEditor(newEditor, item, NAMECOL);
						// refreshTopoRouteTable(trackplan);
			        } else {
			        	Control oldEditor = editor.getEditor();
						if (oldEditor != null) oldEditor.dispose();
						highLight((TopoRoute)item.getData());
			        }
			    }
			}
		});

//		table.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				// Clean up any previous editor control
//			}
//		});
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		createToolBar();
		
		DiagramEditPart diagramEditPart = Util.getDiagramEP();
		if (diagramEditPart==null) return;
		TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
		refreshTopoRouteTable(trackplan);

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
	
	private TopoRoute highLighted;
	void highLight(TopoRoute i) {
		if (highLighted!=null) unhighLight();
		highLighted = i;
		java.util.List<Track> tracks = new ArrayList<Track>();
		for(DirectedTrack dt : highLighted.getDirectedTracks()){
			Track t = dt.getTrack(); 
			tracks.add(t);
			if (t.getPoint()!=null) {
				tracks.add(t.getPoint().getNormalTrack());
				tracks.add(t.getPoint().getReverseTrack());
			}
			if (t.getCrossing()!=null) {
				tracks.add(t.getCrossing().getTrack1());
				tracks.add(t.getCrossing().getTrack2());
			}
		}
		Util.highLightTracks(tracks, true);
	}
	
	void unhighLight(){
		if (highLighted == null) return;
		java.util.List<Track> tracks = new ArrayList<Track>();
		for(DirectedTrack dt : highLighted.getDirectedTracks()){
			Track t = dt.getTrack(); 
			tracks.add(t);
			if (t.getPoint()!=null) {
				tracks.add(t.getPoint().getNormalTrack());
				tracks.add(t.getPoint().getReverseTrack());
			}
			if (t.getCrossing()!=null) {
				tracks.add(t.getCrossing().getTrack1());
				tracks.add(t.getCrossing().getTrack2());
			}
		}
		Util.highLightTracks(tracks, false);
		highLighted = null;
	}
	 void refreshTopoRouteTable(TrackPlan trackplan){
		 table.removeAll();
			for(TopoRoute tr : trackplan.getTopoRoutes()){
				TableItem tit = new TableItem(table,SWT.NONE);
				tit.setText(new String[]{
						tr.getStartSignal().getName(),
						tr.getEndSignal()!=null ? tr.getEndSignal().getName() : ((TopoRouteImpl)tr).getEndTrack().getName(),
						((TopoRouteImpl)tr).getTrackSequence(),
						String.join(",", tr.getNames())}
						);
				tit.setData(tr);
			}
	 }

	 
	void createToolBar(){
		 IToolBarManager mgr = getViewSite().getActionBars().getToolBarManager();
		 mgr.add(new Action("Refresh", AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui.ide", "icons/full/elcl16/refresh_nav.png")){
			 public void run(){
				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				if (diagramEditPart==null) return;

				TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
				refreshTopoRouteTable(trackplan);
			 }
		 });

		 mgr.add(new Action("Generate", AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui.browser", "icons/clcl16/nav_go.gif")){
			 public void run(){
				IWorkbench wb = PlatformUI.getWorkbench();
				IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
				IWorkbenchPage page = win.getActivePage();
				   
				if (page == null) return;
				
				IEditorPart editor = page.getActiveEditor();
				if (editor == null) return;
				
				if (! (editor instanceof DiagramDocumentEditor)) return;
				
				DiagramDocumentEditor diagramEditor = (DiagramDocumentEditor) editor;
				DiagramEditPart diagramEditPart = diagramEditor.getDiagramEditPart();

				CompoundCommand cc = new CompoundCommand();
				cc.add(new ICommandProxy(new TopoRoutesInitCommand(diagramEditPart)));
				cc.execute();
				
				TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
				refreshTopoRouteTable(trackplan);
			 }
		 });
		 
		 mgr.add(new Action("Delele", AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "icons/full/obj16/delete_obj.png")){
			 public void run(){
				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				if (diagramEditPart==null) return;
				
				for(TableItem item : table.getSelection()){
					CompoundCommand cc = new CompoundCommand();
					cc.add(new ICommandProxy(new TopoRoutesDelItemCommand(diagramEditPart,(TopoRoute)item.getData())));
					cc.execute();
				}
				
				TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();

				refreshTopoRouteTable(trackplan);
			 }
		 });
		 
		 mgr.add(new Action("Clear", AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "icons/full/elcl16/trash.png")){
			 public void run(){
				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				if (diagramEditPart==null) return;
				
				CompoundCommand cc = new CompoundCommand();
				cc.add(new ICommandProxy(new TopoRoutesClearCommand(diagramEditPart)));
				cc.execute();
				
				TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();

				refreshTopoRouteTable(trackplan);
			 }
		 });
	}
	
	void registerActivatedListener(){
		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
		IWorkbenchPage page = win.getActivePage();
		page.addPartListener(new PartListener2Impl(){

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
				
				refreshTopoRouteTable(trackplan);
			}
			
		});
	}


}
