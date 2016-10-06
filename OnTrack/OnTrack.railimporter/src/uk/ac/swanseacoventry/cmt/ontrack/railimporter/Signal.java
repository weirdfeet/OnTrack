package uk.ac.swanseacoventry.cmt.ontrack.railimporter;

public class Signal extends Node
{
	private int switchTime;
		
	private Signal(String name, double location, double paths, String id, String description, int switchTime){
		super(name, location, paths, id, description);
		this.switchTime = switchTime;
	}
	
    public int getSwitchTime() {
		return switchTime;
	}

	@Override
	public String toString(){
		return toStringAux().toString();
	}
	
	public StringBuilder toStringAux(){
		StringBuilder result = super.toStringAux();
		result.append(" SwitchTime: ");
		result.append(switchTime);
		return result;
		//TODO: is there a official way?
	}
	
	public static class SignalBuilder extends Node.NodeBuilder
    {
		private int switchTime;
		
        public SignalBuilder(String name) 
        {
			super(name);
		}
		
        public SignalBuilder(Node n) 
        {
        	super(n);
		}
        
        public SignalBuilder(Signal p) 
        {
        	super(p);
        	this.switchTime = p.getSwitchTime();
		}
        
		public SignalBuilder setSwitchTime(int switchTime)
		{
			this.switchTime = switchTime;
			return this;
		}

		public Signal createSignal()
		{
			return new Signal(getName(), getLocation(), getPaths(), getId(), getDescription(), switchTime);
		}
   }
	
	
}