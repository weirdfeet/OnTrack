package click.nullpointer.tplayout;
public interface IProgressObserver {
	/**
	 * Progress Observer instance designed to ignore any progress update calls.
	 * This instance exists to aid development.
	 */
	public static final IProgressObserver DUMMY = new IProgressObserver() {
		@Override
		public void worked(int ammount) {}		
		@Override
		public void done() {}
		@Override
		public void beginTask(String name, int work) {}
	};
	
	public void beginTask(String name, int work);
	public void worked(int ammount);
	public void done();
}