package uk.ac.swanseacoventry.cmt.ontrack.diagram.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
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
import uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.custom.Util;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.SimulationAddCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.TrackPlanSelectSubCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.commands.custom.VerificationResultUpdateCommand;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackDiagramEditorPlugin;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.preferences.custom.PreferenceConstants;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.view.listeners.PartListener2Impl;
import uk.ac.swanseacoventry.cmt.ontrack.dsl2csp.DSL2CSP;

public class CSPTableViewer extends ViewPart {
	private Table table;
	private Listener focusListener;
	private SubTrackPlan highLighted;

	private Map<SubTrackPlan, String> modelPaths = new HashMap<SubTrackPlan, String>();
	private String fullModelPath = "";

	public CSPTableViewer() {
		super();
	}

	public void setFocus() {
		table.setFocus();
	}

	public void createPartControl(Composite parent) {
		table = new Table(parent, SWT.VIRTUAL | SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);

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

		table.addListener(SWT.MouseDown, new Listener() {
			public void handleEvent(Event event) {
				org.eclipse.swt.graphics.Point pt = new org.eclipse.swt.graphics.Point(event.x, event.y);
				TableItem item = table.getItem(pt);
				if (item != null) {
					if (item.getData() instanceof SubTrackPlan)
						highLight((SubTrackPlan) item.getData());
					else
						unhighLight();
				}
			}
		});
		DiagramEditPart diagramEditPart = Util.getDiagramEP();
		if (diagramEditPart == null)
			return;
		TrackPlan trackplan = (TrackPlan) ((View) diagramEditPart.getModel()).getElement();
		refreshCSPTableFrom(trackplan);

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

	void highLight(SubTrackPlan i) {
		if (highLighted != null)
			unhighLight();
		highLighted = i;
		java.util.List<Track> tracks = new ArrayList<Track>();
		for (Track t : highLighted.getTracks()) {
			tracks.add(t);
		}
		Util.highLightTracks(tracks, true);
	}

	void unhighLight() {
		if (highLighted == null)
			return;
		java.util.List<Track> tracks = new ArrayList<Track>();
		for (Track t : highLighted.getTracks()) {
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

	protected String generateForTrackPlan(TrackPlan tp, boolean exp) {
		// exp = false; // uncomment to use Xu's modelling
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
			// tool.generateCSP("Railway.csp");
		}
		String generatedModelPath = tool.getOutputFolder();
		if (tp.getSelectedSubTrackPlan() != null)
			modelPaths.put(tp.getSelectedSubTrackPlan(), generatedModelPath);
		else
			fullModelPath = generatedModelPath;
		return generatedModelPath;
	}

	protected String getFDR3() {
		IPreferenceStore store = OntrackDiagramEditorPlugin.getInstance().getPreferenceStore();
		return store.getString(PreferenceConstants.FDR3_PATH);
	}

	protected String callFDR3(String path) {
		String ret = "";
		String states = "";
		long start = System.currentTimeMillis();
		try {
			// copy csp file to have the sanme name with the interlocking
			// java.nio.file.Path csp = FileSystems.getDefault().getPath(path +
			// "/Railway.csp");
			String os = System.getProperty("os.name").toLowerCase();
			String line;
			String fdr3path = getFDR3();
			if (fdr3path.endsWith(".app"))
				fdr3path += "/Contents/MacOS/refines";
			String[] cmd = { fdr3path, "Railway.csp" };
			ProcessBuilder pb = new ProcessBuilder(cmd);
			File workingDir = new File(path);
			pb.directory(workingDir);
			Process p = pb.start();
			BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader bre = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			boolean collectTrace = false;
			String trace = "";
			while ((line = bri.readLine()) != null) {
				System.out.println(line);
				if (line.trim().equals("Result: Passed"))
					ret = "Passed";
				else if (line.trim().equals("Result: Failed"))
					ret = "Failed";
				else if (line.trim().startsWith("Visited States:")) {
					states = line.trim().split(": ")[1];
				} else if (ret.equals("Failed") && line.trim().startsWith("Implementation Debug:"))
					collectTrace = true;
				else if (collectTrace)
					trace += line.trim();
			}
			bri.close();
			while ((line = bre.readLine()) != null) {
				System.out.println(line);
			}
			bre.close();
			p.waitFor();
			if (!trace.isEmpty()) {
				ArrayList<String[]> ce = Util.extractFDRCounterExample(trace);
				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				if (diagramEditPart != null) {
					SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					String name = workingDir.getName() + " @ " + dateformat.format(new java.util.Date());
					CompoundCommand cc = new CompoundCommand();
					cc.add(new ICommandProxy(new SimulationAddCommand(diagramEditPart, name, ce)));
					cc.execute();
				}
			}
		} catch (IOException e) {
			MessageBox msg = new MessageBox(table.getShell());
			msg.setMessage("Cannot find FDR installation. Please (re)configure path to FDR.");
			msg.open();
			ret = "No FDR";
		} catch (Exception e) {
			System.out.println(e.toString());
			ret = "Error";
		}
		long end = System.currentTimeMillis();
		if (ret.isEmpty())
			ret = "?";
		if (states.isEmpty())
			states = "?";
		ret = states + "," + Double.toString((end - start) / 1000.0) + "," + ret;
		System.out.println(ret);
		return ret;
	}

	boolean experimental = true;

	void createToolBar() {
		IToolBarManager mgr = getViewSite().getActionBars().getToolBarManager();
		mgr.add(new Action("Refresh",
				AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui.ide", "icons/full/elcl16/refresh_nav.png")) {
			public void run() {
				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				if (diagramEditPart == null)
					return;

				TrackPlan trackplan = (TrackPlan) ((View) diagramEditPart.getModel()).getElement();
				refreshCSPTableFrom(trackplan);
			}
		});
		mgr.add(new Action("Generate", AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui.cheatsheets",
				"icons/elcl16/start_cheatsheet.gif")) {
			public void run() {
				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				if (diagramEditPart == null)
					return;

				for (TableItem item : table.getSelection()) {
					Object tp = item.getData();
					if (tp instanceof TrackPlan) {
						TrackPlan trackplan = (TrackPlan) ((View) diagramEditPart.getModel()).getElement();
						CompoundCommand cc = new CompoundCommand();
						cc.add(new ICommandProxy(new TrackPlanSelectSubCommand(diagramEditPart, null)));
						cc.execute();
						// Util.getEditorPart().doSave(((WorkbenchWindow)Util.getWorkbenchWindow()).getStatusLineManager().getProgressMonitor());
						generateForTrackPlan(trackplan, experimental);
					} else if (tp instanceof SubTrackPlan && experimental) {
						// save the submodel to a file
						TrackPlan trackplan = (TrackPlan) ((View) diagramEditPart.getModel()).getElement();
						CompoundCommand cc = new CompoundCommand();
						cc.add(new ICommandProxy(new TrackPlanSelectSubCommand(diagramEditPart, (SubTrackPlan) tp)));
						cc.execute();
						// Util.getEditorPart().doSave(((WorkbenchWindow)Util.getWorkbenchWindow()).getStatusLineManager().getProgressMonitor());
						generateForTrackPlan(trackplan, experimental);
					}
				}

			}
		});
		mgr.add(new Action("LMC") {
			public void run() {
				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				if (diagramEditPart == null)
					return;
				TrackPlan trackplan = (TrackPlan) ((View) diagramEditPart.getModel()).getElement();

				for (TableItem item : table.getSelection()) {
					Object tp = item.getData();
					if (tp instanceof TrackPlan) {
						if (!fullModelPath.equals("")) {
							String[] rs = callFDR3(fullModelPath).split(",");
							CompoundCommand cc = new CompoundCommand();
							cc.add(new ICommandProxy(
									new VerificationResultUpdateCommand(diagramEditPart, null, rs[0], rs[1], rs[2])));
							cc.execute();
							refreshCSPTableFrom(trackplan);
						}
					} else if (tp instanceof SubTrackPlan) {
						if (modelPaths.containsKey(tp)) {
							String[] rs = callFDR3(modelPaths.get(tp)).split(",");
							CompoundCommand cc = new CompoundCommand();
							cc.add(new ICommandProxy(new VerificationResultUpdateCommand(diagramEditPart,
									(SubTrackPlan) tp, rs[0], rs[1], rs[2])));
							cc.execute();
							refreshCSPTableFrom(trackplan);
						}
					}
				}

			}
		});

		// running FDR3 at a remote server via ssh
		// there are 2 steps: 1. upload models, 2. run refines on each model
		// mgr.add(new Action("RMC"){
		// public void run(){
		// System.out.println("Remote model checking");
		// DiagramEditPart diagramEditPart = Util.getDiagramEP();
		// if (diagramEditPart==null) return;
		//
		// RemoteFDR3Helper remote = new RemoteFDR3Helper();
		// remote.display = Display.getDefault();
		//
		// for(TableItem item : table.getSelection()){
		// remote.tableItems.add(item);
		// Object tp = item.getData();
		// if (tp instanceof TrackPlan){
		// if (!fullModelPath.equals("")){
		// // callFDR3(fullModelPath);
		// System.out.println("Model check: " + fullModelPath);
		// remote.modelFolders.add(fullModelPath);
		// }
		// } else if (tp instanceof SubTrackPlan) {
		// if (modelPaths.containsKey(tp)){
		// // callFDR3(modelPaths.get(tp));
		// System.out.println("Model check: " + modelPaths.get(tp));
		// remote.modelFolders.add(modelPaths.get(tp));
		// }
		// }
		// }
		//
		// Thread t = new Thread(remote);
		// t.start();
		//
		// }
		// });
		mgr.add(new Action("Load") { // ,
			// AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui",
			// "icons/full/elcl16/trash.png")){
			public void run() {
				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				TrackPlan trackplan = (TrackPlan) ((View) diagramEditPart.getModel()).getElement();

				FileDialog dialog = new FileDialog(table.getShell(), SWT.OPEN);
				dialog.setFilterExtensions(new String[] { "*.txt" });
				String result = dialog.open();

				BufferedReader br;
				try {
					br = new BufferedReader(new FileReader(result));
					String line = br.readLine();
					line = br.readLine(); // ignore the first line
					while (line != null) {
						SubTrackPlan subTP = null;
						Track track = null;
						String[] subs = null;

						if (!line.equals("Full")) {
							subs = line.split(",");
							String first = subs[0].split("_")[0];
							if (first.equals("ENTRY"))
								first += "_" + subs[0].split("_")[1];

							for (Track t : trackplan.getTracks()) {
								if (t.getName().equals(first)) {
									track = t;
									break;
								}
							}

							if (track != null)
								for (SubTrackPlan stp : trackplan.getSubTrackPlans()) {
									if (stp.getCriticals().contains(track)) {
										subTP = stp;
										break;
									}
								}
						}

						if (!line.equals("Full") || subTP != null) {
							CompoundCommand cc = new CompoundCommand();
							cc.add(new ICommandProxy(new VerificationResultUpdateCommand(diagramEditPart, subTP,
									subs[5].trim(), subs[6].trim(), subs[7].trim())));
							cc.execute();
						}

						line = br.readLine();
					}

					br.close();
				} catch (Exception e) {

				}

				refreshCSPTableFrom(trackplan);

			}
		});

		mgr.add(new Action("Export") { // ,
			// AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui",
			// "icons/full/elcl16/trash.png")){
			public void run() {
				DiagramEditPart diagramEditPart = Util.getDiagramEP();
				TrackPlan trackplan = (TrackPlan) ((View) diagramEditPart.getModel()).getElement();
				
				String outputPath = getOutputPath(trackplan); // get the result file name
				initialiseOutputFile(outputPath);
				try {
					ArrayList<String> rs = new ArrayList<String>();
					rs.add(Util.getActiveFile()); // name of the track plan
					rs.add(Integer.toString(trackplan.getTracks().size()-trackplan.getPoints().size())); // no tracks
 					rs.add(Integer.toString(trackplan.getPoints().size())); // no points
					rs.add(Integer.toString(trackplan.getControlTable().size())); // no routes
					int noSubplans = trackplan.getSubTrackPlans().size();
					ArrayList<SubTrackPlan> completed = getCompletedSubTrackPlans(trackplan);
					int noCompleted = completed.size();
					double completedPercentage = (100.0 * noCompleted) / noSubplans;
					rs.add(Integer.toString(noSubplans)); // no subplans
					rs.add(Integer.toString(noCompleted)); // no completed
					rs.add((new DecimalFormat("##.##")).format(completedPercentage)); // completed percentage
					String totalTime = getTotalTime(completed);
					rs.add(totalTime); // total time
					boolean passed = checkPassed(completed);
					rs.add(passed ? (noSubplans == noCompleted ? "Passed" : "Uncompleted") : "Failed"); // result: passed, failed, uncompleted
					FileWriter outputFile = new FileWriter(outputPath, true);
					outputFile.write(String.join(",", rs) + "\n");
					outputFile.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // append to it
			}

			private ArrayList<SubTrackPlan> getCompletedSubTrackPlans(TrackPlan trackplan) {
				ArrayList<SubTrackPlan> ret = new ArrayList<SubTrackPlan>();
				for(SubTrackPlan stp : trackplan.getSubTrackPlans()){
					if (stp.getVerificationResult().trim().equals("Passed") || stp.getVerificationResult().trim().equals("Failed")) {
						ret.add(stp);
					}
				}
				return ret;
			}

			private boolean checkPassed(ArrayList<SubTrackPlan> completed) {
				for(SubTrackPlan stp : completed) {
					if (stp.getVerificationResult().equals("Failed"))
						return false;
				}
				return true;
			}

			private String getTotalTime(ArrayList<SubTrackPlan> completed) {
				double total = 0;
				for(SubTrackPlan stp : completed) {
					total += convertToSeconds(stp.getVerificationTime());
				}
				return convertToTime(total);
			}

			private String convertToTime(double total) {
				String m = Integer.toString((int)total / 60);
				String s = (new DecimalFormat("##.###")).format(total % 60);
				return m + "m" + s + "s";
			}

			private double convertToSeconds(String verificationTime) {
				int indexM = verificationTime.indexOf("m");
				int indexS = verificationTime.indexOf("s");
				String m = verificationTime.substring(0, indexM);
				String s = verificationTime.substring(indexM+1, indexS);
				return Double.parseDouble(m) * 60 + Double.parseDouble(s);
			}

			private void initialiseOutputFile(String outputPath) {
				File f = new File(outputPath);
				if (!f.exists()){
					FileWriter fw;
					try {
						fw = new FileWriter(outputPath);
						fw.write("name, tracks, points, routes, subs, completed, percentage, time, result \n");
						fw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
			

			private String getOutputPath(TrackPlan trackplan) {
				IPath projectPath = ResourcesPlugin.getWorkspace().getRoot().getFolder(new Path(trackplan.eResource().getURI().toPlatformString(false))).getProject().getLocation();
				
				// create output folders
				IPath outputPath = projectPath.append("output");
				boolean ret = new File(outputPath.toOSString()).mkdir();
				outputPath = outputPath.append("results.txt");
				return outputPath.toOSString();
			}
		});
	}

	// mgr.add(new Action("Init"){
	// public void run(){
	// DiagramEditPart diagramEditPart = getDiagramEP();
	// if (diagramEditPart==null) return;
	//
	// CompoundCommand cc = new CompoundCommand();
	// cc.add(new ICommandProxy(new ReleaseTableInitCommand(diagramEditPart)));
	// cc.execute();
	//
	// TrackPlan trackplan =
	// (TrackPlan)((View)diagramEditPart.getModel()).getElement();
	//
	// refresReleaseTableFrom(trackplan);
	//
	// }
	// });
	// mgr.add(new Action("Auto"){
	// public void run(){
	// DiagramEditPart diagramEditPart = getDiagramEP();
	// if (diagramEditPart==null) return;
	//
	// CompoundCommand cc = new CompoundCommand();
	// cc.add(new ICommandProxy(new
	// ReleaseTableAutoFillCommand(diagramEditPart)));
	// cc.execute();
	//
	// TrackPlan trackplan =
	// (TrackPlan)((View)diagramEditPart.getModel()).getElement();
	//
	// refresReleaseTableFrom(trackplan);
	// }
	// });
	// mgr.add(new Action("Del"){
	// public void run(){
	// DiagramEditPart diagramEditPart = getDiagramEP();
	// if (diagramEditPart==null) return;
	//
	// for(TableItem item : table.getSelection()){
	// CompoundCommand cc = new CompoundCommand();
	// cc.add(new ICommandProxy(new
	// ReleaseTableDelItemCommand(diagramEditPart,(ReleaseTableItem)item.getData())));
	// cc.execute();
	// }
	//
	// TrackPlan trackplan =
	// (TrackPlan)((View)diagramEditPart.getModel()).getElement();
	//
	// refresReleaseTableFrom(trackplan);
	// }
	// });
	//
	// mgr.add(new Action("Clear"){
	// public void run(){
	// DiagramEditPart diagramEditPart = getDiagramEP();
	// if (diagramEditPart==null) return;
	//
	// CompoundCommand cc = new CompoundCommand();
	// cc.add(new ICommandProxy(new ReleaseTableClearCommand(diagramEditPart)));
	// cc.execute();
	//
	// TrackPlan trackplan =
	// (TrackPlan)((View)diagramEditPart.getModel()).getElement();
	//
	// refresReleaseTableFrom(trackplan);
	// }
	// });
	// }

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

				refreshCSPTableFrom(trackplan);
			}

		});
	}

	void refreshCSPTableFrom(TrackPlan trackplan) {
		table.removeAll();

		// for the full track plan
		int routes = 0;
		int hroutes = 0;
		for (ControlTableItem cti : trackplan.getControlTable()) {
			if (cti.getSignal() != null)
				routes++;
			else
				hroutes++;
		}
		TableItem tit = new TableItem(table, SWT.NONE);
		tit.setText(new String[] { "Full",
				Integer.toString(
						trackplan.getTracks().size() - trackplan.getPoints().size() - trackplan.getCrossings().size()),
				Integer.toString(trackplan.getPoints().size()), Integer.toString(trackplan.getCrossings().size()),
				Integer.toString(trackplan.getSignals().size()),
				Integer.toString(routes) + (hroutes > 0 ? "(" + Integer.toString(hroutes) + ")" : ""),
				trackplan.getVerificationTime(), trackplan.getVerificationStates(),
				trackplan.getVerificationResult() });
		tit.setData(trackplan);

		for (SubTrackPlan stp : trackplan.getSubTrackPlans()) {
			routes = 0;
			hroutes = 0;
			for (ControlTableItem cti : stp.getControlTable()) {
				if (cti.getSignal() != null)
					routes++;
				else
					hroutes++;
			}
			tit = new TableItem(table, SWT.NONE);
			tit.setText(new String[] { stp.getName(),
					Integer.toString(stp.getTracks().size() - stp.getPoints().size() - stp.getCrossings().size()),
					Integer.toString(stp.getPoints().size()), Integer.toString(stp.getCrossings().size()),
					Integer.toString(stp.getSignals().size()),
					Integer.toString(routes) + (hroutes > 0 ? "(" + Integer.toString(hroutes) + ")" : ""),
					stp.getVerificationStates(), 
					stp.getVerificationTime(),
					stp.getVerificationResult() });
			tit.setData(stp);
		}
	}

}
