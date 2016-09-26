package uk.ac.swanseacoventry.cmt.ontrack.diagram.view;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
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
import org.osgi.service.prefs.Preferences;

import uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.Point;
import uk.ac.swanseacoventry.cmt.ontrack.Signal;
import uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.custom.Util;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.TrackPlanSelectSubCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackDiagramEditorPlugin;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.preferences.custom.PreferenceConstants;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.view.listeners.PartListener2Impl;
import uk.ac.swanseacoventry.cmt.ontrack.dsl2csp.DSL2CSP;
public class CSPTableViewer extends ViewPart {
	private Table table;
	private Listener focusListener;
	private SubTrackPlan highLighted;
	
	private Map<SubTrackPlan,String> modelPaths = new HashMap<SubTrackPlan,String>();
	private String fullModelPath = "";

	
	public CSPTableViewer() {
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
		refreshCSPTableFrom(trackplan);
		
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

	protected String generateForTrackPlan(TrackPlan tp, boolean exp){
		// exp = false;
		DSL2CSP tool = new DSL2CSP(tp, exp);
		tool.generateCSP("Railway.csp");
		if (exp) {
			tool.generateCSP("Data.csp");
			tool.generateCSP("RailEvent.csp");
			tool.generateCSP("Util.csp");
			tool.generateCSP("Track.csp");
			tool.generateCSP("Point.csp");
			tool.generateCSP("Signal.csp");
			tool.generateCSP("Train.csp");
			tool.generateCSP("Safety.csp");
			//tool.generateCSP("Railway.csp");
		}
		String generatedModelPath = tool.getOutputFolder();
		if (tp.getSelectedSubTrackPlan()!=null) 
			modelPaths.put(tp.getSelectedSubTrackPlan(), generatedModelPath);
		else
			fullModelPath = generatedModelPath;
		return generatedModelPath;
	}
	
	protected String getFDR3(){
		IPreferenceStore store = OntrackDiagramEditorPlugin.getInstance().getPreferenceStore();
		return store.getString(PreferenceConstants.FDR3_PATH);
	}
	
	protected void callFDR3(String path){
		try{
			// copy csp file to have the sanme name with the interlocking
			// java.nio.file.Path csp = FileSystems.getDefault().getPath(path + "/Railway.csp");
			String os = System.getProperty("os.name").toLowerCase();
			String line;
			String fdr3path = getFDR3();
			if (fdr3path.endsWith(".app")) fdr3path += "/Contents/MacOS/fdr3";
			String[] cmd = {fdr3path,"Railway.csp"};
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
		catch(IOException e){
			MessageBox msg = new MessageBox(table.getShell());
			msg.setMessage("Cannot find FDR3 installation. Please (re)configure path to FDR3.");
			msg.open();
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}
	
	boolean experimental = true;
	void createToolBar(){
		 IToolBarManager mgr = getViewSite().getActionBars().getToolBarManager();
		 mgr.add(new Action("Refresh", AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui.ide", "icons/full/elcl16/refresh_nav.png")){
			 public void run(){
					DiagramEditPart diagramEditPart = Util.getDiagramEP();
					if (diagramEditPart==null) return;

					TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
					refreshCSPTableFrom(trackplan);
			 }
		 });
		 mgr.add(new Action("Generate", AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui.cheatsheets", "icons/elcl16/start_cheatsheet.gif")){
			 public void run(){
				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				if (diagramEditPart==null) return;
				
				for(TableItem item : table.getSelection()){
					Object tp = item.getData();
					if (tp instanceof TrackPlan){
						TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
						CompoundCommand cc = new CompoundCommand();
						cc.add(new ICommandProxy(new TrackPlanSelectSubCommand(diagramEditPart, null)));
						cc.execute();
						// Util.getEditorPart().doSave(((WorkbenchWindow)Util.getWorkbenchWindow()).getStatusLineManager().getProgressMonitor());
						generateForTrackPlan(trackplan, experimental);
					} else if (tp instanceof SubTrackPlan && experimental) {
						// save the submodel to a file
						TrackPlan trackplan = (TrackPlan)((View)diagramEditPart.getModel()).getElement();
						CompoundCommand cc = new CompoundCommand();
						cc.add(new ICommandProxy(new TrackPlanSelectSubCommand(diagramEditPart, (SubTrackPlan)tp)));
						cc.execute();
						// Util.getEditorPart().doSave(((WorkbenchWindow)Util.getWorkbenchWindow()).getStatusLineManager().getProgressMonitor());
						generateForTrackPlan(trackplan, experimental);
					}
				}
					
			 }
		 });
		 mgr.add(new Action("FDR3"){
			 public void run(){
				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				if (diagramEditPart==null) return;
				
				for(TableItem item : table.getSelection()){
					Object tp = item.getData();
					if (tp instanceof TrackPlan){
						if (!fullModelPath.equals("")){
							callFDR3(fullModelPath);
						}
					} else if (tp instanceof SubTrackPlan) {
						if (modelPaths.containsKey(tp)){
							callFDR3(modelPaths.get(tp));
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
				
				refreshCSPTableFrom(trackplan);
			}

		});
	}

	
	void refreshCSPTableFrom(TrackPlan trackplan){
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
