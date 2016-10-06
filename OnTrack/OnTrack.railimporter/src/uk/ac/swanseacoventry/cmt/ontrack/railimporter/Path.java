package uk.ac.swanseacoventry.cmt.ontrack.railimporter;

public class Path
{
	private String name;
	private Node startNode;
	private Node endNode;
	private double length;
	private double speedLimitUp;
	private double speedLimitDown;
	private double gradient;
	
	private Path(String name, Node startNode, Node endNode, double length, double speedLimitUp, double speedLimitDown, double gradient){
		this.name = name;
		this.startNode = startNode;
		this.endNode = endNode;
		this.length = length;
		this.speedLimitUp = speedLimitDown;
		this.gradient = gradient;
	}
	
	public String getName() {
		return name;
	}

	public Node getStartNode() {
		return startNode;
	}

	public Node getEndNode() {
		return endNode;
	}

	public double getLength() {
		return length;
	}

	public double getSpeedLimitUp() {
		return speedLimitUp;
	}

	public double getSpeedLimitDown() {
		return speedLimitDown;
	}

	public double getGradient() {
		return gradient;
	}

	@Override
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append("Path Name: ");
		result.append(name);
		result.append(" Start Node: ");
		result.append(startNode.getName());
		result.append(" End Node: ");
		result.append(endNode.getName());
		result.append(" Length: ");
		result.append(length);
		result.append(" Speed Limit Up: ");
		result.append(speedLimitUp);
		result.append(" Speed Limit Down: ");
		result.append(speedLimitDown);
		result.append(" Gradient: ");
		result.append(gradient); 
		return result.toString();
	}
	
    public static class PathBuilder
    {
      	private String name;
		private Node startNode;
		private Node endNode;
		private double length;
		private double speedLimitUp;
		private double speedLimitDown;
		private double gradient;

        public PathBuilder(String name) 
        {
			this.name = name;
		}
		
		public PathBuilder setStartNode(Node startNode)
		{
			this.startNode = startNode;
			return this;
		}
		
		public PathBuilder setEndNode(Node endNode)
		{
			this.endNode = endNode;
			return this;
		}
	
		public PathBuilder setLength(double length)
		{
			this.length = length;
			return this;
		}

		public PathBuilder setSpeedLimitUp(double speedLimitUp)
		{
			this.speedLimitUp = speedLimitUp;
			return this;
		}
		
		public PathBuilder setSpeedLimitDown(double speedLimitDown	)
		{
			this.speedLimitDown = speedLimitDown;
			return this;
		}

		public PathBuilder setGradient(double gradient)
		{
			this.gradient = gradient;
			return this;
		}

		public Path createPath()
		{
         return new Path(name, startNode, endNode, length, speedLimitUp, speedLimitDown, gradient);
		}
   }
	
	
}