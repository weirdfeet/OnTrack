package uk.ac.swanseacoventry.cmt.ontrack.railimporter;

public class Point extends Node
{
	private int switchTime;
		
	private Point(String name, double location, double paths, String id, String description, int switchTime){
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
	
	public static class PointBuilder extends Node.NodeBuilder
    {
		private int switchTime;
		
        public PointBuilder(String name) 
        {
			super(name);
		}
		
        public PointBuilder(Node n) 
        {
        	super(n);
		}
        
        public PointBuilder(Point p) 
        {
        	super(p);
        	this.switchTime = p.getSwitchTime();
		}
        
		public PointBuilder setSwitchTime(int switchTime)
		{
			this.switchTime = switchTime;
			return this;
		}

		public Point createPoint()
		{
			return new Point(getName(), getLocation(), getPaths(), getId(), getDescription(), switchTime);
		}
   }
	
	
}