/*******************************************************************************
 * Based on the Example
 ******************************************************************************/
package uk.ac.swanseacoventry.cmt.ontrack.dsl2m;

import java.io.File;
import java.net.URI;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.epsilon.common.parse.problem.ParseProblem;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;
import org.eclipse.epsilon.eol.IEolExecutableModule;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;

public abstract class TrackSchemeEpsilon {
	
	protected IEolExecutableModule module;
	
	protected Object result;
	
	public abstract IEolExecutableModule createModule();
	
	public abstract String getSource() throws Exception;
	
	public abstract List<IModel> getModels() throws Exception;
	
	public void postProcess() {};
	
	public void preProcess() {};
	
	public Object execute(boolean sourceIsURI) {
		
		module = createModule();
		try {
			if (sourceIsURI)
				module.parse(URI.create(getSource()));
			else
				module.parse(new File(getSource()));
		} catch (Exception e) {
			System.out.println("Error loading source file");
			e.printStackTrace();
		}
		
		if (module.getParseProblems().size() > 0) {
			System.err.println("Parse errors occured...");
			for (ParseProblem problem : module.getParseProblems()) {
				System.err.println(problem.toString());
			}
			System.exit(-1);
		}
		
		try {
			for (IModel model : getModels()) {
				module.getContext().getModelRepository().addModel(model);
			}
		} catch (Exception e) {
			System.out.println("Error accessing models");
			e.printStackTrace();
		}
		
		preProcess();
		try {
			result = execute(module);
		} catch (EolRuntimeException e) {
			System.out.println("Error executing module");
			e.printStackTrace();
		}
		postProcess();
		
		module.getContext().getModelRepository().dispose();
		
		return result;
	}
	
	protected Object execute(IEolExecutableModule module) throws EolRuntimeException {
		return module.execute();
	}
	
	public static EmfModel createEmfModel(String name, String model, String metamodel, boolean readOnLoad, boolean storeOnDisposal) {
		EmfModel emfModel = new EmfModel();
		StringProperties properties = new StringProperties();
		properties.put(EmfModel.PROPERTY_NAME, name);
		properties.put(EmfModel.PROPERTY_FILE_BASED_METAMODEL_URI, metamodel);
		properties.put(EmfModel.PROPERTY_MODEL_URI, model);
		properties.put(EmfModel.PROPERTY_IS_METAMODEL_FILE_BASED, "true");
		properties.put(EmfModel.PROPERTY_READONLOAD, readOnLoad + "");
		properties.put(EmfModel.PROPERTY_STOREONDISPOSAL, storeOnDisposal + "");
		try {
			emfModel.load(properties, (IRelativePathResolver)null);
		} catch (EolModelLoadingException e) {
			System.out.println("Error loading model " + name);
			e.printStackTrace();
		}
		return emfModel;
	}
	
	/**
	 * Creates an EMF model with the supplied properties
	 * @param name the name of the model
	 * @param model the instance model source
	 * @param metamodel the meta-model source
	 * @return the new EmfModel instance
	 */
	public static EmfModel createEmfModel(String name, String model, String metamodel) {
		EmfModel emfModel = new EmfModel();
		StringProperties properties = new StringProperties();
		properties.put(EmfModel.PROPERTY_NAME, name);
		properties.put(EmfModel.PROPERTY_FILE_BASED_METAMODEL_URI, metamodel);
		properties.put(EmfModel.PROPERTY_MODEL_URI, model);
		properties.put(EmfModel.PROPERTY_IS_METAMODEL_FILE_BASED, "true");
		try {
			emfModel.load(properties, (IRelativePathResolver)null);
		} catch (EolModelLoadingException e) {
			System.out.println("Error loading model " + name);
			e.printStackTrace();
		}
		return emfModel;
	}

//	public static Resource putInNewResource(EObject eObject) {
//		ResourceSet rs = new ResourceSetImpl();
//		EPackage ePackage = eObject.eClass().getEPackage();
//		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put( "*", new XMIResourceFactoryImpl());
//		rs.getPackageRegistry().put(ePackage.getNsURI(), ePackage);
//		Resource r = rs.createResource(org.eclipse.emf.common.util.URI.createFileURI(""));
//		r.getContents().add(eObject);
//		return r;
//	}
	
	public static EmfModel createEmfModel(String model_name, EObject eObject) {
		//return new InMemoryEmfModel(putInNewResource(eObject));
		EmfModel emfModel = new InMemoryEmfModel(model_name, eObject.eResource(), eObject.eClass().getEPackage());
		try {
			emfModel.load();
		}
		catch (EolModelLoadingException e) {
			System.out.println("Error loading model " + model_name);
			e.printStackTrace();
		}
		return emfModel;
	}
}
