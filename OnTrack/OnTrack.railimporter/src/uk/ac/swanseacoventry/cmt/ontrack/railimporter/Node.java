package uk.ac.swanseacoventry.cmt.ontrack.railimporter;

public class Node
{
	private String name;
	private double location;
	private double paths;
	private String id;
	private String description;
	
	protected Node(String name, double location, double paths, String id, String description){
		this.name = name;
		this.location = location;
		this.paths = paths;
		this.id = id;
		this.description = description;
	}

	
	public String getName(){
		return name;
	}
	
	public double getLocation() {
		return location;
	}

	public double getPaths() {
		return paths;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString(){
		return toStringAux().toString();
	}
	
	public StringBuilder toStringAux(){
		StringBuilder result = new StringBuilder();
		result.append("Node Name: ");
		result.append(name);
		result.append(" Node Type: ");
		result.append(this.getClass().getName());
		result.append(" Location: ");
		result.append(location);
		result.append(" Paths: ");
		result.append(paths);
		result.append(" ID: ");
		result.append(id);
		result.append(" Description: ");
		result.append(description);
		return result;
	}
	
    public static class NodeBuilder
    {
      	private String name;
		private double location;
		private double paths;
		private String id;
		private String description;

        public NodeBuilder(String name) 
        {
			this.name = name;
			// TODO: initialize?
		}
        
        protected NodeBuilder(Node n) 
        {
        	this.name = n.getName();
        	this.location = n.getLocation();
    		this.paths = n.getPaths();
    		this.id = n.getId();
    		this.description = n.getDescription();
    	}
		
		public NodeBuilder setLocation(double location)
		{
			this.location = location;
			return this;
		}
		
		public NodeBuilder setPaths(double paths)
		{
			this.paths = paths;
			return this;
		}
	
		public NodeBuilder setId(String id)
		{
			this.id = id;
			return this;
		}

		public NodeBuilder setDescription(String description)
		{
			this.description = description;
			return this;
		}
		
		public String getName(){
			return name;
		}
		
		public double getLocation() {
			return location;
		}

		public double getPaths() {
			return paths;
		}

		public String getId() {
			return id;
		}

		public String getDescription() {
			return description;
		}

		public Node createNode()
		{
         return new Node(name, location, paths, id, description);
		}
    }
	
	
}