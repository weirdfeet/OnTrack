package uk.ac.swanseacoventry.cmt.ontrack.dsl2m;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.epsilon.eol.IEolExecutableModule;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.etl.EtlModule;

public class TrackSchemeETL extends TrackSchemeEpsilon {

	private List<IModel> models;
	private final String source;
	
	public TrackSchemeETL (String source, IModel inputModel, IModel... outputModels) {
		
		this.source = source;
		
		// Set input model properties
		inputModel.setReadOnLoad(true);
		inputModel.setStoredOnDisposal(false);
		try {
			inputModel.load();
		} catch (EolModelLoadingException e) {
			System.out.println("Error loading model");
			e.printStackTrace();
		}
		
		// Set output model properties
		for (IModel model : outputModels) {
			model.setReadOnLoad(false);
			model.setStoredOnDisposal(false);
			try {
				model.load();
			} catch (EolModelLoadingException e) {
				System.out.println("Error loading model");
				e.printStackTrace();
			}
		}
		
		// Add to models
		models = new ArrayList<IModel>();
		models.add(inputModel);
		for (IModel model : outputModels) {
			models.add(model);
		}	
	}
	
	@Override
	public IEolExecutableModule createModule() {
		return new EtlModule();
	}

	@Override
	public String getSource() {
		return source;
	}

	@Override
	public List<IModel> getModels() {
		return models;
	}

}
