package uk.ac.swanseacoventry.cmt.ontrack.diagram.view;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.Point;
import uk.ac.swanseacoventry.cmt.ontrack.Signal;
import uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.custom.Util;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.TrackPlanSelectSubCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.view.listeners.PartListener2Impl;
import uk.ac.swanseacoventry.cmt.ontrack.dsl2cspb.DSL2CSPB;
public class CSPBTableViewer extends ViewPart {
	private Table table;
	private Listener focusListener;
	private SubTrackPlan highLighted;
	
	private Map<SubTrackPlan,String> modelPaths = new HashMap<SubTrackPlan,String>();
	private String fullModelPath = "";

	private final String PATH_TO_PROB = "/Applications/ProB/prob"; // on mac, temporary, to be placed in eclipse preferences
	// private final String PATH_TO_PROB = "C:\\Program Files (x86)\\ProB\\ProBWin.exe"; // on win, temporary, to be placed in eclipse preferences

	
	public CSPBTableViewer() {
		super();
    }

	public void setFocus() {
		table.setFocus();
    }

	public void createPartControl(Composite parent) {
		table = new Table(parent, SWT.VIRTUAL | SWT.BORDER | SWT.FULL_SELECTION);

		int i = 0;
		TableColumn column;
		column = new TableColumn(table, SWT.LEFT, i++);
		column.setText("TrackPlan");
		column.setWidth(120);

		column = new TableColumn(table, SWT.LEFT, i++);
		column.setText("#Tracks");
		column.setWidth(120);

		column = new TableColumn(table, SWT.LEFT, i++);
		column.setText("#Points");
		column.setWidth(120);

		column = new TableColumn(table, SWT.LEFT, i++);
		column.setText("#Crossings");
		column.setWidth(120);

		column = new TableColumn(table, SWT.LEFT, i++);
		column.setText("#Signals");
		column.setWidth(120);

		column = new TableColumn(table, SWT.LEFT, i++);
		column.setText("#Routes");
		column.setWidth(120);

		column = new TableColumn(table, SWT.LEFT, i++);
		column.setText("#States");
		column.setWidth(120);

		column = new TableColumn(table, SWT.LEFT, i++);
		column.setText("Time");
		column.setWidth(120);

		column = new TableColumn(table, SWT.LEFT, i++);
		column.setText("Result");
		column.setWidth(120);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		createToolBar();
		
		table.addListener(SWT.MouseDown, new Listener(){
			public void handleEvent(Event event){
				org.eclipse.swt.graphics.Point pt = new org.eclipse.swt.graphics.Point(event.x, event.y);
			    TableItem item = table.getItem(pt);
			    if(item != null) {
			    	if (item.getData() instanceof SubTrackPlan)
			    		highLight((SubTrackPlan)item.getData());
			    	else
			    		unhighLight();
			    }
			}
		});
		DiagramEditPart diagramEditPart = Util.getDiagramEP();
		if (diagramEditPart==null) return;
		TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
		refreshCSPBTableFrom(trackplan);
		
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
	
//	private String saveSubModelToFile(String basePath, SubTrackPlan sub) {
//		IPath projectPath = ResourcesPlugin.getWorkspace().getRoot().getFolder(new Path(URI.createURI(basePath).toPlatformString(false))).getProject().getLocation();
//		
//		// create output folders
//		IPath outputPath = projectPath.append("output");
//		boolean ret = new File(outputPath.toOSString()).mkdir();
//		outputPath = outputPath.append("covering");
//		ret = new File(outputPath.toOSString()).mkdir();
//		outputPath.append("sub.model");
//		
//		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
//		Map<String, Object> m = reg.getExtensionToFactoryMap();
//		m.put("daform", new XMIResourceFactoryImpl());
//		
//		ResourceSet resSet = new ResourceSetImpl();
//		Resource resource = resSet.createResource(URI.createFileURI(outputPath.toOSString()));
//		resource.getContents().add(sub);
//		try {
//			resource.save(Collections.EMPTY_MAP);
//		}
//		catch(Exception e) {
//			return "";
//		}
//		return outputPath.toOSString();
//	}
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

	protected String generateForTrackPlan(TrackPlan tp){
		DSL2CSPB tool = new DSL2CSPB(tp);
		tool.generateCSP("CTRL.csp");
		tool.generateBMachine("Context.mch");
		tool.generateBMachine("Topology.mch");
		tool.generateBMachine("ControlTable.mch");
		tool.generateBMachine("ReleaseTable.mch");
		tool.generateBMachine("Interlocking.mch");
		String generatedModelPath = tool.getOutputFolder();
		if (tp.getSelectedSubTrackPlan()!=null) 
			modelPaths.put(tp.getSelectedSubTrackPlan(), generatedModelPath);
		else
			fullModelPath = generatedModelPath;
		return generatedModelPath;
	}
	
	protected void callProB(String path){
		try{
			// copy csp file to have the sanme name with the interlocking
			java.nio.file.Path ctrl = FileSystems.getDefault().getPath(path + "/CTRL.csp");
			java.nio.file.Path intl = FileSystems.getDefault().getPath(path + "/Interlocking.csp");
			Files.copy(ctrl, intl, REPLACE_EXISTING);
			String line;
			String[] cmd = {PATH_TO_PROB,"Interlocking.mch" + ""};
			ProcessBuilder pb = new ProcessBuilder(cmd);
			pb.directory(new File(path));								
			Process p = pb.start();
			BufferedReader bri = new BufferedReader
			(new InputStreamReader(p.getInputStream()));
			BufferedReader bre = new BufferedReader
			(new InputStreamReader(p.getErrorStream()));
			while ((line = bri.readLine()) != null) {
				System.out.println(line);
			}
			bri.close();
			while ((line = bre.readLine()) != null) {
				System.out.println(line);
			}
			bre.close();
			p.waitFor();
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}
	
	void createToolBar(){
		 IToolBarManager mgr = getViewSite().getActionBars().getToolBarManager();
		 mgr.add(new Action("Generate", AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui.cheatsheets", "icons/elcl16/start_cheatsheet.gif")){
			 public void run(){
				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				if (diagramEditPart==null) return;
				
				for(TableItem item : table.getSelection()){
					Object tp = item.getData();
					if (tp instanceof TrackPlan){
						TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
						String modelPath = trackplan.eResource().getURI().toString();
						CompoundCommand cc = new CompoundCommand();
						cc.add(new ICommandProxy(new TrackPlanSelectSubCommand(diagramEditPart, null)));
						cc.execute();
						//Util.getEditorPart().doSave(((WorkbenchWindow)Util.getWorkbenchWindow()).getStatusLineManager().getProgressMonitor());
						generateForTrackPlan(trackplan);
					} else if (tp instanceof SubTrackPlan) {
						// save the submodel to a file
						TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
						String modelPath = trackplan.eResource().getURI().toString();
						CompoundCommand cc = new CompoundCommand();
						cc.add(new ICommandProxy(new TrackPlanSelectSubCommand(diagramEditPart, (SubTrackPlan)tp)));
						cc.execute();
						//Util.getEditorPart().doSave(((WorkbenchWindow)Util.getWorkbenchWindow()).getStatusLineManager().getProgressMonitor());
						generateForTrackPlan(trackplan);
					}
				}
					
			 }
		 });
		 mgr.add(new Action("Run ProB", AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui.externaltools", "icons/full/etool16/external_tools.gif")){
			 public void run(){
				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				if (diagramEditPart==null) return;
				
				for(TableItem item : table.getSelection()){
					Object tp = item.getData();
					if (tp instanceof TrackPlan){
						if (!fullModelPath.equals("")){
							callProB(fullModelPath);
						}
					} else if (tp instanceof SubTrackPlan) {
						if (modelPaths.containsKey(tp)){
							callProB(modelPaths.get(tp));
						}
					}
				}
					
			 }
		 });
//		 mgr.add(new Action("Init"){
//			 public void run(){
//					DiagramEditPart diagramEditPart = getDiagramEP();
//					if (diagramEditPart==null) return;
//
//					CompoundCommand cc = new CompoundCommand();
//					cc.add(new ICommandProxy(new ReleaseTableInitCommand(diagramEditPart)));
//					cc.execute();
//					
//					TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
//
//					refresReleaseTableFrom(trackplan);
//				 
//			 }
//		 });
//		 mgr.add(new Action("Auto"){
//			 public void run(){
//				 DiagramEditPart diagramEditPart = getDiagramEP();
//					if (diagramEditPart==null) return;
//
//					CompoundCommand cc = new CompoundCommand();
//					cc.add(new ICommandProxy(new ReleaseTableAutoFillCommand(diagramEditPart)));
//					cc.execute();
//					
//					TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
//
//					refresReleaseTableFrom(trackplan);
//			 }
//		 });
//		 mgr.add(new Action("Del"){
//			 public void run(){
//				DiagramEditPart diagramEditPart = getDiagramEP();
//				if (diagramEditPart==null) return;
//				
//				for(TableItem item : table.getSelection()){
//					CompoundCommand cc = new CompoundCommand();
//					cc.add(new ICommandProxy(new ReleaseTableDelItemCommand(diagramEditPart,(ReleaseTableItem)item.getData())));
//					cc.execute();
//				}
//				
//				TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
//
//				refresReleaseTableFrom(trackplan);
//			 }
//		 });
//		 
//		 mgr.add(new Action("Clear"){
//			 public void run(){
//				DiagramEditPart diagramEditPart = getDiagramEP();
//				if (diagramEditPart==null) return;
//				
//				CompoundCommand cc = new CompoundCommand();
//				cc.add(new ICommandProxy(new ReleaseTableClearCommand(diagramEditPart)));
//				cc.execute();
//				
//				TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
//
//				refresReleaseTableFrom(trackplan);
//			 }
//		 });
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
				
				refreshCSPBTableFrom(trackplan);
			}

		});
	}

	
	void refreshCSPBTableFrom(TrackPlan trackplan){
		table.removeAll();
		
		// for the full track plan
		int routes = 0;
		int hroutes = 0;
		for(ControlTableItem cti : trackplan.getControlTable()){
			if (cti.getSignal()!=null) routes++;
			else hroutes++;
		}
		TableItem tit = new TableItem(table,SWT.NONE);
		tit.setText(new String[]{
				"Full",
				Integer.toString(trackplan.getTracks().size() - trackplan.getPoints().size() - trackplan.getCrossings().size()),
				Integer.toString(trackplan.getPoints().size()),
				Integer.toString(trackplan.getCrossings().size()),
				Integer.toString(trackplan.getSignals().size()),
				Integer.toString(routes) + (hroutes > 0 ? "(" + Integer.toString(hroutes) + ")" : ""),
				}
		);
		tit.setData(trackplan);
		
		
		for(SubTrackPlan stp : trackplan.getSubTrackPlans()){
			routes = 0;
			hroutes = 0;
			for(ControlTableItem cti : stp.getControlTable()){
				if (cti.getSignal()!=null) routes++;
				else hroutes++;
			}
			tit = new TableItem(table,SWT.NONE);
			tit.setText(new String[]{
					stp.getName(),
					Integer.toString(stp.getTracks().size() - stp.getPoints().size() - stp.getCrossings().size()),
					Integer.toString(stp.getPoints().size()),
					Integer.toString(stp.getCrossings().size()),
					Integer.toString(stp.getSignals().size()),
					Integer.toString(routes) + (hroutes > 0 ? "(" + Integer.toString(hroutes) + ")" : ""),
					}
			);
			tit.setData(stp);
		}
	}

}
