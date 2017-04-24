package uk.ac.swanseacoventry.cmt.ontrack.diagram.util;

import org.eclipse.swt.widgets.TableItem;

public class TableItemUpdater implements Runnable {

	public TableItem item;
	public int index;
	public String string;
			
	public TableItemUpdater(TableItem i, int c, String t){
		item = i;
		index = c;
		string = t;
	}
	
	@Override
	public void run() {
		
		if (item!=null && !item.isDisposed()) {
			item.setText(index, string);
		}

	}

}
