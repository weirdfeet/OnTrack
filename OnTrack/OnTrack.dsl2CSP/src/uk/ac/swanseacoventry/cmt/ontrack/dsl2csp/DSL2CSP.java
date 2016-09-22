package uk.ac.swanseacoventry.cmt.ontrack.dsl2csp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath; 
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.epsilon.emc.emf.EmfModel;

import uk.ac.swanseacoventry.cmt.ontrack.OntrackPackage;
import uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.dsl2m.TrackSchemeEGL;
import uk.ac.swanseacoventry.cmt.ontrack.dsl2m.TrackSchemeETL;
import uk.ac.swanseacoventry.cmt.ontrack.dsl2m.TrackSchemeEpsilon;

/**
 * A class to generate CSPB scripts for railway verification based on a supplied track plan and control table
 *
 */
public class DSL2CSP {

	// Constants
	private static final String VERSION = "0.1";
	
	// File structure elements
	private final Date date;
	private final String outputFolder; // Generated files are placed in a unique date_time folder within OUTPUT_DIR
	private final String eglOutputFolder;
	
	// Epsilon Directories
	private String ETL_SOURCE_DIR = "platform:/plugin/OnTrack.dsl2CSP/etl/";
	private String TEMPLATES_DIR = "platform:/plugin/OnTrack.dsl2CSP/templates/";
	private static final String META_MODELS_DIR = "platform:/plugin/OnTrack.dsl2CSP/model/"; // All model files are assumed to be in this folder

	//private String EGL_OUTPUT_DIR = "platform:/plugin/SafeTrack.diagram/output/egl/";
	//private String OUTPUT_DIR = "platform:/plugin/SafeTrack.diagram/output/";
	
	// Input Model
	private static final String SAFETRACK_MODEL_NAME = "safetrack";
	private static final String SAFETRACK_META_MODEL = "ontrack.ecore";
	private final String SAFETRACK_MODEL; // The input model produced by the graphical editor
	private EmfModel inputModel; // Created in constructor once input model is provided
	
	// Output Models
	private static final String CSP_MODEL_NAME = "CSP";
	private static final String CSP_MODEL = "csp.model";
	private static final String CSP_META_MODEL = "CSP.ecore";
	private final EmfModel csp = TrackSchemeEpsilon.createEmfModel(CSP_MODEL_NAME, META_MODELS_DIR + CSP_MODEL, META_MODELS_DIR + CSP_META_MODEL);

	// private EmfModel[] outputModels = {csp, bMachine}; // {csp, bMachine};
	
	/**
	 * Main method
	 * Creates an instance of the class in order to generate files
	 * @param args
	 * @throws Exception
	 */
//	public static void main(String[] args) {
//			
//		TrackScheme2CSPB tool = new TrackScheme2CSPB("Copy.bjoernercomplete");
//		
//		// Process all files in TEMPLATES_DIR
//		for (String template : new File(TEMPLATES_DIR).list()) {
//			tool.generate(template);
//		}
//	}
	
	public String getOutputFolder(){
		return outputFolder;
	}
	
	String getSubFolderName(EList<Track> criticals){
		List<String> cs = new ArrayList<String>();
		for(Track t : criticals){
			cs.add(t.getName());
		}
		return String.join("_", cs);
	}
	
	/**
	 * Constructor
	 * Initialises input model, sets numTrains and creates output folders
	 * @param model the input model from the graphical editor
	 */
	SubTrackPlan subplan;
	TrackPlan trackplan;
	boolean experimental;
	boolean overlap;
	String SafetyAssertions;
	public DSL2CSP(TrackPlan tp, boolean exp) {
		subplan = tp.getSelectedSubTrackPlan();
		experimental = exp;
		overlap = tp.isOverlapped();
		trackplan = tp;
		
		if (exp) {
			ETL_SOURCE_DIR += "exp/";
			TEMPLATES_DIR += "exp2/";
		}
		
		if (experimental){
			SafetyAssertions = "";
			if (subplan==null) {
				SafetyAssertions += "assert NoCollision [T= System \\ diff(Events,NoCollisionAlpha)\n";
				SafetyAssertions += "assert NoDerailment [T= System \\ diff(Events,NoDerailmentAlpha)\n";
				SafetyAssertions += "assert NoRunThru [T= System \\ diff(Events,NoRunThruAlpha)	\n";			
			} else {
				HashSet<Track> ts = new HashSet<Track>();
				ts.addAll(subplan.getCriticals());
				for(Track t : ts){
					if (t.getPointReverse()!=null) continue;
					if (t.getCrossing2()!=null) continue;
					SafetyAssertions += "assert NoCollisionTrack(" + t.getName() + ") [T= System \\ diff(Events,NoCollisionTrackAlpha(" + t.getName() + "))\n";
				}
				for(Track t : ts){
					if (t.getPointNormal()==null) continue;
					SafetyAssertions += "assert NoDerailmentPoint(" + t.getPointNormal().getName() + ") [T= System \\ diff(Events,NoDerailmentPointAlpha(" + t.getPointNormal().getName() + "))\n";
					SafetyAssertions += "assert NoRunThruPoint(" + t.getPointNormal().getName() + ") [T= System \\ diff(Events,NoRunThruPointAlpha(" + t.getPointNormal().getName() + "))\n";
				}
			}
		}
		
		// create input model
		SAFETRACK_MODEL = trackplan.eResource().getURI().toString();
		// inputModel = TrackSchemeEpsilon.createEmfModel(SAFETRACK_MODEL_NAME, trackplan);//TrackSchemeEpsilon.createEmfModel(SAFETRACK_MODEL_NAME, SAFETRACK_MODEL, META_MODELS_DIR + SAFETRACK_META_MODEL, false, false);
		
		//IPath projectPath = ResourcesPlugin.getWorkspace().getRoot().getFolder(new Path(inputModel.getModelFileUri().toPlatformString(false))).getProject().getLocation();
		IPath projectPath = ResourcesPlugin.getWorkspace().getRoot().getFolder(new Path(trackplan.eResource().getURI().toPlatformString(false))).getProject().getLocation();
		
		// get date
		date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy_kk.mm.ss");
		
		// create output folders
		IPath outputPath = projectPath.append("output");
		boolean ret = new File(outputPath.toOSString()).mkdir();
		outputPath = outputPath.append("CSP");
		ret = new File(outputPath.toOSString()).mkdir();
		outputPath = outputPath.append(sdf.format(date));
		ret = new File(outputPath.toOSString()).mkdir();

		if (subplan==null)
			outputPath = outputPath.append("Full");
		else
			outputPath = outputPath.append(getSubFolderName(subplan.getCriticals()));
		ret = new File(outputPath.toOSString()).mkdir();

		outputFolder = outputPath.toOSString();
		outputPath = outputPath.append("egl");
		eglOutputFolder = outputPath.toOSString();
		ret = new File(eglOutputFolder).mkdir();
		
	}
	
	public void generateCSP(String template) {
		generate(csp, template);
	}

	/**
	 * Processes a template to produce an output file of the same name in OUTPUT_DIR for each number of trains available (1 to numTrains)
	 * Each template must have its own ETL file stored in ETL_SOURCE_DIR with the same name as the template (but with the extension .etl)
	 * The Apache Velocity template engine is used to populate some parts of the template before EGL is processed
	 * @param template the filename of the template file for generation. The file should be placed in TEMPLATES_DIR
	 */
	public void generate(EmfModel outputmodel, String template) {
		
		EmfModel[] outputModels = {outputmodel};
		
		// Identify template's ETL source and EGL output filenames
		int dot = template.lastIndexOf(".");
		String etlSource = ETL_SOURCE_DIR + template.substring(0,dot) + ".etl";
		String eglOutput = eglOutputFolder + File.separator + template.substring(0,dot) + ".egl";
		
		// Use ETL to produce output models
		inputModel = TrackSchemeEpsilon.createEmfModel(SAFETRACK_MODEL_NAME, trackplan);
		new TrackSchemeETL(etlSource, inputModel,outputModels).execute(true);
		
		// Initialise Apache Velocity template engine
		Velocity.init();
		// Velocity.setProperty(RuntimeConstants, value);
		
		// Setup variables for template
		VelocityContext context = new VelocityContext();
		context.put("date", date.toString());
		context.put("model", SAFETRACK_MODEL);
		context.put("version", VERSION);
		if (experimental){
			context.put("overlap", overlap ? "true" : "false");
			context.put("safetychecks", SafetyAssertions);
		}
		
		try {

			// Prepare output EGL file
			BufferedWriter writer = new BufferedWriter(new FileWriter(eglOutput));

			// Get the template
			// Template velocityTemplate = Velocity.getTemplate(TEMPLATES_DIR + template);
			InputStream input = new java.net.URI(TEMPLATES_DIR + template).toURL().openStream();
			InputStreamReader reader = new InputStreamReader(input);

			// Process template
			//velocityTemplate.merge(context, writer);
			Velocity.evaluate(context, writer, TEMPLATES_DIR + template, input);

			// Finish writing to file
			writer.flush();
			writer.close();
		}
		catch (Exception e) {

			System.out.println("Error writing to " + eglOutput);
			e.printStackTrace();
		}
		
		// configure output filename
		String outputFile = outputFolder + File.separator + template;

		// process EGL
		new TrackSchemeEGL(outputFile, eglOutput, outputModels).execute(false);
	}
	
	public void clearInputModel(){
		inputModel = null;
	}
	
}
