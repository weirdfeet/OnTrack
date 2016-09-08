package uk.ac.swanseacoventry.cmt.ontrack.dsl2m;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.epsilon.egl.EglTemplateFactory;
import org.eclipse.epsilon.egl.EglTemplateFactoryModuleAdapter;
import org.eclipse.epsilon.eol.IEolExecutableModule;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.models.IModel;

public class TrackSchemeEGL extends TrackSchemeEpsilon {
	
	private List<IModel> models;
	private final String source;
	private final String output;

	public TrackSchemeEGL(String output, String source, IModel... models) {
		
		this.output = output;
		this.source = source;

		// Set output model properties
		for (IModel model : models) {
			model.setReadOnLoad(true);
			model.setStoredOnDisposal(false);
			try {
				model.load();
			} catch (EolModelLoadingException e) {
				System.out.println("Error loading model");
				e.printStackTrace();
			}
		}

		// Add to models
		this.models = new ArrayList<IModel>();
		for (IModel model : models) {
			this.models.add(model);
		}	
	}

	@Override
	public IEolExecutableModule createModule() {
		return new EglTemplateFactoryModuleAdapter(new EglTemplateFactory());
	}

	@Override
	public String getSource() {
		return source;
	}

	@Override
	public List<IModel> getModels() {
		return models;
	}
	
	@Override
	public void postProcess() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(output));
			writer.write(result.toString());
			writer.flush();
			writer.close();
			System.out.println(output + " created");
		}
		catch (IOException e) {
			System.out.println("Error writing to " + output);
			e.printStackTrace();
		}
	}

}
