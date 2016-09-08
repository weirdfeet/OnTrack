
package uk.ac.swanseacoventry.cmt.ontrack.diagram.part;

import java.util.Collections;

import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.tooling.runtime.part.DefaultLinkToolEntry;
import org.eclipse.gmf.tooling.runtime.part.DefaultNodeToolEntry;

import uk.ac.swanseacoventry.cmt.ontrack.diagram.providers.OntrackElementTypes;

/**
 * @generated
 */
public class OntrackPaletteFactory {

	/**
	* @generated
	*/
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createTrackEquipment1Group());
	}

	/**
	* Creates "Track Equipment" palette tool group
	* @generated
	*/
	private PaletteContainer createTrackEquipment1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(Messages.TrackEquipment1Group_title);
		paletteContainer.setId("createTrackEquipment1Group"); //$NON-NLS-1$
		paletteContainer.add(createConnector1CreationTool());
		paletteContainer.add(createTrack2CreationTool());
		paletteContainer.add(createNewTrack3CreationTool());
		paletteContainer.add(createPoint4CreationTool());
		paletteContainer.add(createNewPoint5CreationTool());
		paletteContainer.add(createCrossing6CreationTool());
		paletteContainer.add(createNewCrossing7CreationTool());
		paletteContainer.add(createSignal8CreationTool());
		paletteContainer.add(createSignalplacement9CreationTool());
		paletteContainer.add(createSignaldirection10CreationTool());
		paletteContainer.add(createEntrance11CreationTool());
		paletteContainer.add(createEntranceplacement12CreationTool());
		paletteContainer.add(createExit13CreationTool());
		paletteContainer.add(createExitplacement14CreationTool());
		paletteContainer.add(createTerminal15CreationTool());
		paletteContainer.add(createTerminalplacement16CreationTool());
		return paletteContainer;
	}

	/**
	* @generated
	*/
	private ToolEntry createConnector1CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.Connector1CreationTool_title,
				Messages.Connector1CreationTool_desc, Collections.singletonList(OntrackElementTypes.Connector_2001));
		entry.setId("createConnector1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj16/connector.gif")); //$NON-NLS-1$
		entry.setLargeIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj32/connector.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createTrack2CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(Messages.Track2CreationTool_title,
				Messages.Track2CreationTool_desc, Collections.singletonList(OntrackElementTypes.Track_4005));
		entry.setId("createTrack2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj16/track.gif")); //$NON-NLS-1$
		entry.setLargeIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj32/track.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createNewTrack3CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.NewTrack3CreationTool_title,
				Messages.NewTrack3CreationTool_desc, Collections.singletonList(OntrackElementTypes.NewTrack_2006));
		entry.setId("createNewTrack3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj16/track.gif")); //$NON-NLS-1$
		entry.setLargeIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj32/track.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createPoint4CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(Messages.Point4CreationTool_title,
				Messages.Point4CreationTool_desc, Collections.singletonList(OntrackElementTypes.Point_4001));
		entry.setId("createPoint4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj16/point.gif")); //$NON-NLS-1$
		entry.setLargeIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj32/point.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createNewPoint5CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.NewPoint5CreationTool_title,
				Messages.NewPoint5CreationTool_desc, Collections.singletonList(OntrackElementTypes.NewPoint_2007));
		entry.setId("createNewPoint5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj16/point.gif")); //$NON-NLS-1$
		entry.setLargeIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj32/point.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createCrossing6CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(Messages.Crossing6CreationTool_title,
				Messages.Crossing6CreationTool_desc, Collections.singletonList(OntrackElementTypes.Crossing_4002));
		entry.setId("createCrossing6CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj16/crossing.gif")); //$NON-NLS-1$
		entry.setLargeIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj32/crossing.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createNewCrossing7CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.NewCrossing7CreationTool_title,
				Messages.NewCrossing7CreationTool_desc,
				Collections.singletonList(OntrackElementTypes.NewCrossing_2008));
		entry.setId("createNewCrossing7CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj16/crossing.gif")); //$NON-NLS-1$
		entry.setLargeIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj32/crossing.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createSignal8CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.Signal8CreationTool_title,
				Messages.Signal8CreationTool_desc, Collections.singletonList(OntrackElementTypes.Signal_2002));
		entry.setId("createSignal8CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj16/signal.gif")); //$NON-NLS-1$
		entry.setLargeIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj32/signal.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createSignalplacement9CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(Messages.Signalplacement9CreationTool_title,
				Messages.Signalplacement9CreationTool_desc,
				Collections.singletonList(OntrackElementTypes.SignalTrack_4003));
		entry.setId("createSignalplacement9CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj16/setsignal.gif")); //$NON-NLS-1$
		entry.setLargeIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj32/setsignal.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createSignaldirection10CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(Messages.Signaldirection10CreationTool_title,
				Messages.Signaldirection10CreationTool_desc,
				Collections.singletonList(OntrackElementTypes.SignalConnector_4004));
		entry.setId("createSignaldirection10CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj16/setsignal.gif")); //$NON-NLS-1$
		entry.setLargeIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj32/setsignal.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createEntrance11CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.Entrance11CreationTool_title,
				Messages.Entrance11CreationTool_desc, Collections.singletonList(OntrackElementTypes.Entrance_2003));
		entry.setId("createEntrance11CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj16/entrance.gif")); //$NON-NLS-1$
		entry.setLargeIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj32/entrance.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createEntranceplacement12CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(Messages.Entranceplacement12CreationTool_title,
				Messages.Entranceplacement12CreationTool_desc,
				Collections.singletonList(OntrackElementTypes.EntranceConnector_4006));
		entry.setId("createEntranceplacement12CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj16/entrance.gif")); //$NON-NLS-1$
		entry.setLargeIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj32/entrance.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createExit13CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.Exit13CreationTool_title,
				Messages.Exit13CreationTool_desc, Collections.singletonList(OntrackElementTypes.Exit_2004));
		entry.setId("createExit13CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj16/exit.gif")); //$NON-NLS-1$
		entry.setLargeIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj32/exit.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createExitplacement14CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(Messages.Exitplacement14CreationTool_title,
				Messages.Exitplacement14CreationTool_desc,
				Collections.singletonList(OntrackElementTypes.ExitConnector_4007));
		entry.setId("createExitplacement14CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj16/exit.gif")); //$NON-NLS-1$
		entry.setLargeIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj32/exit.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createTerminal15CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.Terminal15CreationTool_title,
				Messages.Terminal15CreationTool_desc, Collections.singletonList(OntrackElementTypes.Terminal_2005));
		entry.setId("createTerminal15CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj16/terminal.gif")); //$NON-NLS-1$
		entry.setLargeIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj32/terminal.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createTerminalplacement16CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(Messages.Terminalplacement16CreationTool_title,
				Messages.Terminalplacement16CreationTool_desc,
				Collections.singletonList(OntrackElementTypes.TerminalConnector_4008));
		entry.setId("createTerminalplacement16CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj16/terminal.gif")); //$NON-NLS-1$
		entry.setLargeIcon(
				OntrackDiagramEditorPlugin.findImageDescriptor("/OnTrack.dsl.edit/icons/full/obj32/terminal.gif")); //$NON-NLS-1$
		return entry;
	}

}
