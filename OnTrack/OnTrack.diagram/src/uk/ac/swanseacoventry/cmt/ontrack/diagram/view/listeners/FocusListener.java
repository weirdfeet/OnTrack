package uk.ac.swanseacoventry.cmt.ontrack.diagram.view.listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;

public class FocusListener implements Listener {

	Table parent = null;
	
	public FocusListener(Table tbl){
		parent = tbl;
	}
	
	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		
		if (! (event.widget == parent)) return;
		
		System.out.println("Our child is " + (event.type == SWT.FocusIn ? "focused" : "unfocused"));
		
	}

}
