package uk.ac.swanseacoventry.cmt.ontrack.railimporter;

import java.util.ArrayList;

public class Route {
	
		private String name;
		private Signal signal;
		private ArrayList<Point> normalPoints;
		private ArrayList<Point> reversePoints;
		private ArrayList<TrackCircuit> trackCircuits;
		private ArrayList<TrackCircuit> overlaps;
		private int clearTime;
		
		protected Route(String name, Signal signal, ArrayList<Point> normalPoints, ArrayList<Point> reversePoints,
				        ArrayList<TrackCircuit> trackCircuits, ArrayList<TrackCircuit> overlaps, int clearTime){
			this.name = name;
			this.signal = signal;
			this.normalPoints = normalPoints;
			this.reversePoints = reversePoints;
			this.trackCircuits = trackCircuits;
			this.overlaps = overlaps;
			this.clearTime = clearTime;
		}
		
		@Override
		public String toString(){
			StringBuilder result = new StringBuilder();
			result.append("Name: ");
			result.append(name);
			result.append(" Signal: ");
			result.append(signal.getName());
			result.append(" Normal Points: ");
			result.append(normalPoints.toString());
			result.append(" Reverse Points: ");
			result.append(reversePoints.toString());
			result.append(" Tracks: ");
			result.append(trackCircuits.toString());
			result.append(" Overlaps: ");
			result.append(overlaps.toString());
			result.append(" Clear Time: ");
			result.append(clearTime);
			return result.toString();
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Signal getSignal() {
			return signal;
		}

		public void setSignal(Signal signal) {
			this.signal = signal;
		}

		public ArrayList<Point> getNormalPoints() {
			return normalPoints;
		}

		public void setNormalPoints(ArrayList<Point> normalPoints) {
			this.normalPoints = normalPoints;
		}

		public ArrayList<Point> getReversePoints() {
			return reversePoints;
		}

		public void setReversePoints(ArrayList<Point> reversePoints) {
			this.reversePoints = reversePoints;
		}

		public ArrayList<TrackCircuit> getTrackCircuits() {
			return trackCircuits;
		}

		public void setTrackCircuits(ArrayList<TrackCircuit> trackCircuits) {
			this.trackCircuits = trackCircuits;
		}

		public ArrayList<TrackCircuit> getOverlaps() {
			return overlaps;
		}

		public void setOverlaps(ArrayList<TrackCircuit> overlaps) {
			this.overlaps = overlaps;
		}

		public int getClearTime() {
			return clearTime;
		}

		public void setClearTime(int clearTime) {
			this.clearTime = clearTime;
		}

		public static class RouteBuilder 
	    {
			private String name;
			private Signal signal;
			private ArrayList<Point> normalPoints;
			private ArrayList<Point> reversePoints;
			private ArrayList<TrackCircuit> trackCircuits;
			private ArrayList<TrackCircuit> overlaps;
			private int clearTime;
			
	        public RouteBuilder(String name, Signal signal) 
	        {
				this.name = name;			
				this.signal = signal;
				normalPoints = new ArrayList<Point>();
				reversePoints = new ArrayList<Point>();
				trackCircuits = new ArrayList<TrackCircuit>();
				overlaps = new ArrayList<TrackCircuit>();
			}
			
	        
			public RouteBuilder setName(String name) {
				this.name = name;
				return this;
			}


			public RouteBuilder setSignal(Signal signal) {
				this.signal = signal;
				return this;
			}


			public RouteBuilder setNormalPoints(ArrayList<Point> normalPoints) {
				this.normalPoints = normalPoints;
				return this;
			}
			
			public RouteBuilder addNormalPoint(Point normalPoint) {
				this.normalPoints.add(normalPoint);
				return this;
			}

			public RouteBuilder setReversePoints(ArrayList<Point> reversePoints) {
				this.reversePoints = reversePoints;
				return this;
			}
			
			public RouteBuilder addReversePoint(Point reversePoint) {
				this.reversePoints.add(reversePoint);
				return this;
			}

			public RouteBuilder setTrackCircuits(ArrayList<TrackCircuit> trackCircuits) {
				this.trackCircuits = trackCircuits;
				return this;
			}

			public RouteBuilder addTrackCircuit(TrackCircuit trackCircuit) {
				this.trackCircuits.add(trackCircuit);
				return this;
			}

			public RouteBuilder setOverlaps(ArrayList<TrackCircuit> overlaps) {
				this.overlaps = overlaps;
				return this;
			}


			public RouteBuilder setClearTime(int clearTime) {
				this.clearTime = clearTime;
				return this;
			}

				
			public Route createRoute()
			{
				return new Route(name, signal, normalPoints, reversePoints, trackCircuits, overlaps, clearTime);
			}
	    }
}
