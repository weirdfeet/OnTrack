package uk.ac.swanseacoventry.cmt.ontrack.diagram.custom;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.parts.TrackEditPart;
import uk.ac.swanseacoventry.cmt.ontrack.diagram.part.OntrackDiagramEditor;

public class Util {
	
	public static IWorkbenchWindow getWorkbenchWindow() {
		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
		
		return win;
	}
	
	public static IWorkbenchPage getActivePage(){
		return getWorkbenchWindow().getActivePage();
	}
	
	public static IEditorPart getEditorPart() {
		IWorkbenchPage page = getActivePage();

		if (page == null)
			return null;

		IEditorPart editor = page.getActiveEditor();
		return editor;
	}
	
	public static EditPart findEditPartBySemantics(EObject sem, Class editPartClass){
		IEditorPart editorPart = getEditorPart();

		if (editorPart instanceof OntrackDiagramEditor) {
			List editParts = ((OntrackDiagramEditor) editorPart).getDiagramGraphicalViewer()
					.findEditPartsForElement(EMFCoreUtil.getProxyID(sem), editPartClass);
			if (editParts.size() >= 1) {
				return (EditPart) editParts.get(0);
			}
		}
		return null;
	}
	
	public static DiagramEditPart getDiagramEP(){
		IEditorPart editor = getEditorPart();
		if (editor == null) return null;
		
		if (! (editor instanceof DiagramDocumentEditor)) return null;
		
		DiagramDocumentEditor diagramEditor = (DiagramDocumentEditor) editor;
		DiagramEditPart diagramEditPart = diagramEditor.getDiagramEditPart();
		return diagramEditPart;
	}

	public static void highLightTracks(List<Track> tracks, boolean hl){
		for(Track t : tracks){
			TrackEditPart tep = (TrackEditPart)Util.findEditPartBySemantics(t, TrackEditPart.class);
			if (tep!=null) if (hl) tep.highLight(); else tep.unhighLight();
			if (t.getPoint()!=null) {
				tep = (TrackEditPart)Util.findEditPartBySemantics(t.getPoint().getNormalTrack(), TrackEditPart.class);
				if (tep!=null) if (hl) tep.highLight(); else tep.unhighLight();
				tep = (TrackEditPart)Util.findEditPartBySemantics(t.getPoint().getReverseTrack(), TrackEditPart.class);
				if (tep!=null) if (hl) tep.highLight(); else tep.unhighLight();
			}
			if (t.getCrossing()!=null) {
				tep = (TrackEditPart)Util.findEditPartBySemantics(t.getCrossing().getTrack1(), TrackEditPart.class);
				if (tep!=null) if (hl) tep.highLight(); else tep.unhighLight();
				tep = (TrackEditPart)Util.findEditPartBySemantics(t.getCrossing().getTrack2(), TrackEditPart.class);
				if (tep!=null) if (hl) tep.highLight(); else tep.unhighLight();
			}
		}

	}

	public static void multiHighLightTracks(Hashtable<Track, Color> highLighted) {
		for(Track t : highLighted.keySet()){
			TrackEditPart tep = (TrackEditPart)Util.findEditPartBySemantics(t, TrackEditPart.class);
			if (tep!=null) tep.highLightWithColor(highLighted.get(t)); 
		}
	}

	public static ArrayList<String[]> extractFDRCounterExample(String text) {
		ArrayList<String[]> ret = new ArrayList<String[]>();
		int open = text.indexOf("<");
		int close = text.lastIndexOf(">");
		int error = text.lastIndexOf("Error Event: ");
		String errorEvent = text.substring(error + "Error Event: ".length());
		String events = text.substring(open + 1, close) + "," + errorEvent;
		String[] trace = events.split(",");
		for(int i = 0; i < trace.length; i++) {
			String s = trace[i].trim();
			String[] act = s.split("\\.");
			ret.add(act);
		}
		
		return ret;
	}
	
	public static String getActiveFile(){
		IEditorPart ep = getEditorPart();
		if (ep!=null) {
			IEditorInput ei = ep.getEditorInput();
			if (ei!=null) {
				IPath path = ((FileEditorInput)ei).getPath();
				if (path!=null) {
					int c = path.segmentCount();
					String name = path.segment(c-1);
					int dot = name.lastIndexOf(".");
					return dot>=0 ? name.substring(0, dot) : name;
				}
			}
		}
		return null;
	}

}
