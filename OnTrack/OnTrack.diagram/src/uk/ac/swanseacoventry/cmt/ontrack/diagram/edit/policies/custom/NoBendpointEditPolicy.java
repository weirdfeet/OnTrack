package uk.ac.swanseacoventry.cmt.ontrack.diagram.edit.policies.custom;

import org.eclipse.draw2d.Connection;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ConnectionBendpointEditPolicy;
import org.eclipse.gmf.runtime.notation.Edge;

public class NoBendpointEditPolicy extends ConnectionBendpointEditPolicy {

	// in the following method:
	// - disallow adding middle points to the connection in-between
	@Override
	protected Command getBendpointsChangedCommand(Connection connection, Edge edge) {
		if (connection.getPoints().size() >= 2) {
			// disallow middle point addition
			return null;
		}
		return super.getBendpointsChangedCommand(connection, edge);
	}
}
