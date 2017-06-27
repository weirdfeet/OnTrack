package uk.ac.swanseacoventry.cmt.ontrack.diagram.view;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
//import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.DirectedTrack;
import uk.ac.swanseacoventry.cmt.ontrack.OntrackPackage;
import uk.ac.swanseacoventry.cmt.ontrack.Point;
import uk.ac.swanseacoventry.cmt.ontrack.Signal;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.custom.Util;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.ControlTableAddItemCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.ControlTableAutoFillCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.ControlTableClearCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.ControlTableDelItemCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.ControlTableInitCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.ControlTableUpdateItemCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.view.listeners.PartListener2Impl;

public class ControlTableViewer extends ViewPart {
	private Table table;
	TableEditor editor;
	private Listener focusListener;
	private ControlTableItem highLighted;
	
	public ControlTableViewer() {
		super();
    }

	public void setFocus() {
		table.setFocus();
    }
	
	private boolean controlTableEditable = false;

	public void createPartControl(Composite parent) {
		table = new Table(parent, SWT.VIRTUAL | SWT.BORDER | SWT.FULL_SELECTION);

		int i = 0;
		
		TableColumn routeCol = new TableColumn(table, SWT.LEFT, i++);
		routeCol.setText("Route");
		routeCol.setWidth(60);

		TableColumn signaCol = new TableColumn(table, SWT.LEFT, i++);
		signaCol.setText("Signal");
		signaCol.setWidth(60);

		TableColumn normalsCol = new TableColumn(table, SWT.LEFT, i++);
		normalsCol.setText("Normal");
		normalsCol.setWidth(120);

		TableColumn reversesCol = new TableColumn(table, SWT.LEFT, i++);
		reversesCol.setText("Reverse");
		reversesCol.setWidth(120);

		TableColumn clearsCol = new TableColumn(table, SWT.LEFT, i++);
		clearsCol.setText("Clear");
		clearsCol.setWidth(240);
		
		TableColumn directionsCol = new TableColumn(table, SWT.LEFT, i++);
		directionsCol.setText("Direction lock");
		directionsCol.setWidth(360);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	
		createToolBar();
		
		final int NAMECOL = 0;
		final int SIGNALCOL = 1;
		final int NORMALSCOL = 2;
		final int REVERSESCOL = 3;
		final int CLEARSCOL = 4;
		final int DIRECTIONSCOL = 5;
		
		editor = new TableEditor(table);
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		editor.minimumWidth = 120-4;
		

		table.addListener(SWT.MouseDown, new Listener(){
			public void handleEvent(Event event){
			    org.eclipse.swt.graphics.Point pt = new org.eclipse.swt.graphics.Point(event.x, event.y);
			    TableItem item = table.getItem(pt);
				if (controlTableEditable){
				    if(item != null) {
				    	if (item.getBounds(NAMECOL).contains(pt)){
				        	changeName(item);
				        } else {
				        	Control oldEditor = editor.getEditor();
							if (oldEditor != null) oldEditor.dispose();
							
				        	if (item.getBounds(SIGNALCOL).contains(pt)){
					        	changeSignal((ControlTableItem)item.getData());
					        } else if (item.getBounds(NORMALSCOL).contains(pt)){
					        	selectNormals((ControlTableItem)item.getData());
					        } else if (item.getBounds(REVERSESCOL).contains(pt)) {
					        	selectReverses((ControlTableItem)item.getData());
					        } else if (item.getBounds(CLEARSCOL).contains(pt)) {
					        	selectClears((ControlTableItem)item.getData());
					        }  else if (item.getBounds(DIRECTIONSCOL).contains(pt)) {
					        	selectDirections((ControlTableItem)item.getData());
					        }
				    	}
				    }
				}
				else {
				    highLight((ControlTableItem)item.getData());
				}
			}
		});

		
//		table.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//			}
//		});

		DiagramEditPart diagramEditPart = Util.getDiagramEP();
		if (diagramEditPart==null) return;
		TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
		refreshControlTableFrom(trackplan);

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
	
	void highLight(ControlTableItem i) {
		if (highLighted!=null) unhighLight();
		highLighted = i;
		java.util.List<Track> tracks = new ArrayList<Track>();
		for(Track t : highLighted.getClears()){
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
		for(Track t : highLighted.getClears()){
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
	
	class SafeTrackLabelProvider extends LabelProvider {
		public String getText(Object track){
			if (track instanceof Point)
				return "Point " + ((Point)track).getName();
			else if (track instanceof ControlTableItem)
				return "Route " + ((ControlTableItem)track).getRoute();
			else if (track instanceof Track)
				return "Track " + ((Track)track).getName();
			else if (track instanceof DirectedTrack)
				return ((DirectedTrack)track).getOppositeConnector().getId() + " -> "+ ((DirectedTrack)track).getTrack().getName() + " -> " + ((DirectedTrack)track).getConnector().getId();
			else if (track instanceof Signal)
				return "Signal " + ((Signal)track).getName();
			return "???";
		}
	}
	
	void selectNormals(ControlTableItem cti){
		DiagramEditPart diagramEditPart = Util.getDiagramEP();
		if (diagramEditPart==null) return;

		TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
		
		ArrayList<Point> inputs = new ArrayList<Point>();
		for(Point t : trackplan.getPoints())
			inputs.add(t);
		
		FeatureEditorDialog dialog = new FeatureEditorDialog(table.getShell(), new SafeTrackLabelProvider(), 
				cti, OntrackPackage.Literals.CONTROL_TABLE_ITEM__NORMALS,"Select normal points", inputs);
		dialog.open();
		CompoundCommand cc = new CompoundCommand();
		cc.add(new ICommandProxy(new ControlTableUpdateItemCommand((IGraphicalEditPart)diagramEditPart,cti,null,null,(EList<Point>)dialog.getResult(),null,null,null)));
		cc.execute();
		refreshControlTableFrom(trackplan);
	}

	void selectReverses(ControlTableItem cti){
		DiagramEditPart diagramEditPart = Util.getDiagramEP();
		if (diagramEditPart==null) return;

		TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
		
		ArrayList<Point> inputs = new ArrayList<Point>();
		for(Point t : trackplan.getPoints())
			inputs.add(t);
		
		FeatureEditorDialog dialog = new FeatureEditorDialog(table.getShell(), new SafeTrackLabelProvider(), 
				cti, OntrackPackage.Literals.CONTROL_TABLE_ITEM__REVERSES,"Select reverse points", inputs);
		dialog.open();
		CompoundCommand cc = new CompoundCommand();
		cc.add(new ICommandProxy(new ControlTableUpdateItemCommand((IGraphicalEditPart)diagramEditPart,cti,null,null,null,(EList<Point>)dialog.getResult(),null,null)));
		cc.execute();
		refreshControlTableFrom(trackplan);
	}
	void selectClears(ControlTableItem cti){
		DiagramEditPart diagramEditPart = Util.getDiagramEP();
		if (diagramEditPart==null) return;

		TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
		
		ArrayList<Track> inputs = new ArrayList<Track>();
		for(Track t : trackplan.getTracks())
			if (t.getPointReverse()==null && t.getCrossing2()==null)
				inputs.add(t);
		
		FeatureEditorDialog dialog = new FeatureEditorDialog(table.getShell(), new SafeTrackLabelProvider(), 
				cti, OntrackPackage.Literals.CONTROL_TABLE_ITEM__CLEARS,"Select direction locks", inputs);
		dialog.open();
		CompoundCommand cc = new CompoundCommand();
		cc.add(new ICommandProxy(new ControlTableUpdateItemCommand((IGraphicalEditPart)diagramEditPart,cti,null,null,null,null,(EList<Track>)dialog.getResult(),null)));
		cc.execute();
		refreshControlTableFrom(trackplan);
	}
	void selectDirections(ControlTableItem cti){
		DiagramEditPart diagramEditPart = Util.getDiagramEP();
		if (diagramEditPart==null) return;

		TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
		
		ArrayList<DirectedTrack> inputs = new ArrayList<DirectedTrack>();
		for(Track t : trackplan.getTracks()){
			inputs.add(t.getDirectedTracks().get(0));
			inputs.add(t.getDirectedTracks().get(1));
		}
		
		FeatureEditorDialog dialog = new FeatureEditorDialog(table.getShell(), new SafeTrackLabelProvider(), 
				cti, OntrackPackage.Literals.CONTROL_TABLE_ITEM__DIRECTIONS,"Select clear tracks", inputs);
		dialog.open();
		CompoundCommand cc = new CompoundCommand();
		cc.add(new ICommandProxy(new ControlTableUpdateItemCommand((IGraphicalEditPart)diagramEditPart,cti,null,null,null,null,null,(EList<DirectedTrack>)dialog.getResult())));
		cc.execute();
		refreshControlTableFrom(trackplan);
	}
	
	void changeSignal(ControlTableItem cti){
		DiagramEditPart diagramEditPart = Util.getDiagramEP();
		if (diagramEditPart==null) return;

		TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
		
		//ArrayList<Signal> inputs = new ArrayList<Signal>();
		Signal[] inputs = new Signal[trackplan.getSignals().size()];
		int i = 0;
		for(Signal t : trackplan.getSignals())
			inputs[i++] = t;
		
		ElementListSelectionDialog  dialog = new ElementListSelectionDialog(table.getShell(), new SafeTrackLabelProvider()); 
		dialog.setTitle("Select a signal");
		dialog.setElements(inputs);
		dialog.open();
		CompoundCommand cc = new CompoundCommand();
		cc.add(new ICommandProxy(new ControlTableUpdateItemCommand((IGraphicalEditPart)diagramEditPart,cti,null,(Signal)dialog.getFirstResult(),null,null,null,null)));
		cc.execute();
		refreshControlTableFrom(trackplan);
	}

	void changeName(TableItem item){
		final ControlTableItem cti = (ControlTableItem)item.getData();
		
		Control oldEditor = editor.getEditor();
		if (oldEditor != null) oldEditor.dispose();

		final DiagramEditPart diagramEditPart = Util.getDiagramEP();
		if (diagramEditPart==null) return;
		TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();

		// The control that will be the editor must be a child of the Table
		Text newEditor = new Text(table, SWT.NONE);
		newEditor.setText(item.getText(0));
		newEditor.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent me){
				Text text = (Text)editor.getEditor();
				editor.getItem().setText(0, text.getText());
				CompoundCommand cc = new CompoundCommand();
				cc.add(new ICommandProxy(new ControlTableUpdateItemCommand((IGraphicalEditPart)diagramEditPart,cti,text.getText(),null,null,null,null,null)));
				cc.execute();
			}
		});
		newEditor.selectAll();
		newEditor.setFocus();
		editor.setEditor(newEditor, item, 0);
		refreshControlTableFrom(trackplan);

//		CompoundCommand cc = new CompoundCommand();
//		cc.add(new ICommandProxy(new UpdateControlTableItemCommand((IGraphicalEditPart)diagramEditPart,cti,null,null,null,null,(EList<Track>)dialog.getResult())));
//		cc.execute();
	}
	
	void createToolBar(){
		 IToolBarManager mgr = getViewSite().getActionBars().getToolBarManager();
		 mgr.add(new Action("Refresh", AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui.ide", "icons/full/elcl16/refresh_nav.png")){
			 public void run(){
				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				if (diagramEditPart==null) return;

				TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
				refreshControlTableFrom(trackplan);
			 }
		 });
		 mgr.add(new Action("Initialise", AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui.cheatsheets", "icons/elcl16/start_cheatsheet.gif")){
			 public void run(){
				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				if (diagramEditPart==null) return;

				CompoundCommand cc = new CompoundCommand();
				cc.add(new ICommandProxy(new ControlTableInitCommand(diagramEditPart)));
				cc.execute();
				
				TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();

				refreshControlTableFrom(trackplan);
			 }
		 });
		
		 mgr.add(new Action("Auto-fill", AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui.browser", "icons/clcl16/nav_go.gif")){
			 public void run(){
				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				if (diagramEditPart==null) return;

				CompoundCommand cc = new CompoundCommand();
				cc.add(new ICommandProxy(new ControlTableAutoFillCommand(diagramEditPart)));
				cc.execute();
				
				TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
				refreshControlTableFrom(trackplan);
			 }
		 });

		 mgr.add(new Action("Add", AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "icons/full/obj16/add_obj.png")){
			 public void run(){
				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				if (diagramEditPart==null) return;
				
				String name= JOptionPane.showInputDialog("Add new route: ");

				CompoundCommand cc = new CompoundCommand();
				cc.add(new ICommandProxy(new ControlTableAddItemCommand(diagramEditPart,name)));
				cc.execute();
				
				TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();

				refreshControlTableFrom(trackplan);
			 }
		 });
		 
		 mgr.add(new Action("Delele", AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "icons/full/obj16/delete_obj.png")){
			 public void run(){
				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				if (diagramEditPart==null) return;
				
				for(TableItem item : table.getSelection()){
					CompoundCommand cc = new CompoundCommand();
					cc.add(new ICommandProxy(new ControlTableDelItemCommand(diagramEditPart,(ControlTableItem)item.getData())));
					cc.execute();
				}
				
				TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();

				refreshControlTableFrom(trackplan);
			 }
		 });

		 
		 mgr.add(new Action("Editing",IAction.AS_CHECK_BOX){
			 {
				 setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "icons/full/etool16/editor_area.png"));
			 }
			 public void run(){
				 controlTableEditable = this.isChecked();
			 }
		 });
		 
		 mgr.add(new Action("Clear", AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "icons/full/elcl16/trash.png")){
			 public void run(){
				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				if (diagramEditPart==null) return;
				
				CompoundCommand cc = new CompoundCommand();
				cc.add(new ICommandProxy(new ControlTableClearCommand(diagramEditPart)));
				cc.execute();
				
				TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();

				refreshControlTableFrom(trackplan);
			 }
		 });
	}
	
	void refreshControlTableFrom(TrackPlan trackplan){
		table.removeAll();
		
		for(ControlTableItem cti : trackplan.getControlTable()){
			TableItem tit = new TableItem(table,SWT.NONE);
			tit.setText(new String[]{
					cti.getRoute(),
					cti.getSignal()!=null && cti.getSignal().getName()!=null ? cti.getSignal().getName() : "",
					cti.getNormalsSequence(trackplan),
					cti.getReversesSequence(trackplan),
					cti.getClearsSequence(),
					cti.getDirectionsSequence()}
					);
			tit.setData(cti);
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
				
				refreshControlTableFrom(trackplan);
			}

		});
	}

}
