package uk.ac.swanseacoventry.cmt.ontrack.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;

import uk.ac.swanseacoventry.cmt.ontrack.OntrackPackage;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.ConnectorIdEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.PointNameEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.SignalNameEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TrackNameEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.parsers.MessageFormatParser;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackVisualIDRegistry;

/**
 * @generated
 */
public class OntrackParserProvider extends AbstractProvider implements IParserProvider {

	/**
	* @generated
	*/
	private IParser connectorId_5001Parser;

	/**
	* @generated
	*/
	private IParser getConnectorId_5001Parser() {
		if (connectorId_5001Parser == null) {
			EAttribute[] features = new EAttribute[] { OntrackPackage.eINSTANCE.getConnector_Id() };
			MessageFormatParser parser = new MessageFormatParser(features);
			connectorId_5001Parser = parser;
		}
		return connectorId_5001Parser;
	}

	/**
	* @generated
	*/
	private IParser signalName_5002Parser;

	/**
	* @generated
	*/
	private IParser getSignalName_5002Parser() {
		if (signalName_5002Parser == null) {
			EAttribute[] features = new EAttribute[] { OntrackPackage.eINSTANCE.getSignal_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { OntrackPackage.eINSTANCE.getSignal_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			signalName_5002Parser = parser;
		}
		return signalName_5002Parser;
	}

	/**
	* @generated
	*/
	private IParser pointName_6001Parser;

	/**
	* @generated
	*/
	private IParser getPointName_6001Parser() {
		if (pointName_6001Parser == null) {
			EAttribute[] features = new EAttribute[] { OntrackPackage.eINSTANCE.getUnit_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { OntrackPackage.eINSTANCE.getUnit_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			pointName_6001Parser = parser;
		}
		return pointName_6001Parser;
	}

	/**
	* @generated
	*/
	private IParser trackName_6002Parser;

	/**
	* @generated
	*/
	private IParser getTrackName_6002Parser() {
		if (trackName_6002Parser == null) {
			EAttribute[] features = new EAttribute[] { OntrackPackage.eINSTANCE.getUnit_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { OntrackPackage.eINSTANCE.getUnit_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			trackName_6002Parser = parser;
		}
		return trackName_6002Parser;
	}

	/**
	* @generated
	*/
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case ConnectorIdEditPart.VISUAL_ID:
			return getConnectorId_5001Parser();
		case SignalNameEditPart.VISUAL_ID:
			return getSignalName_5002Parser();
		case PointNameEditPart.VISUAL_ID:
			return getPointName_6001Parser();
		case TrackNameEditPart.VISUAL_ID:
			return getTrackName_6002Parser();
		}
		return null;
	}

	/**
	* Utility method that consults ParserService
	* @generated
	*/
	public static IParser getParser(IElementType type, EObject object, String parserHint) {
		return ParserService.getInstance().getParser(new HintAdapter(type, object, parserHint));
	}

	/**
	* @generated
	*/
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(OntrackVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(OntrackVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	* @generated
	*/
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (OntrackElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	* @generated
	*/
	private static class HintAdapter extends ParserHintAdapter {

		/**
		* @generated
		*/
		private final IElementType elementType;

		/**
		* @generated
		*/
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		* @generated
		*/
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}

}
