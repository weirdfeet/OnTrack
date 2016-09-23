package uk.ac.swanseacoventry.cmt.ontrack.diagram.util;

import uk.ac.swanseacoventry.cmt.ontrack.Track;

import java.util.ArrayList;
import java.util.HashSet;

import uk.ac.swanseacoventry.cmt.ontrack.Connector;
import uk.ac.swanseacoventry.cmt.ontrack.Crossing;
import uk.ac.swanseacoventry.cmt.ontrack.Point;
import uk.ac.swanseacoventry.cmt.ontrack.Signal;

public class ErrorDetectionHelper {
	
	public static boolean errorPointDetected(Track normalTrack) {
		Point point = normalTrack.getPoint();
		Track reverseTrack = point.getReverseTrack();
		/*
		 *         c2
		 *       /
		 * c1---------c3
		 */
		Connector c1 = null;
		Connector c2 = null;
		Connector c3 = null;
		if (reverseTrack.getC1()==normalTrack.getC1()) {
			c1 = reverseTrack.getC1();
			c2 = reverseTrack.getC2();
			c3 = normalTrack.getC2();
		}
		if (reverseTrack.getC1()==normalTrack.getC2()) {
			c1 = reverseTrack.getC1();
			c2 = reverseTrack.getC2();
			c3 = normalTrack.getC1();
		}
		if (reverseTrack.getC2()==normalTrack.getC1()) {
			c1 = reverseTrack.getC2();
			c2 = reverseTrack.getC1();
			c3 = normalTrack.getC2();
		}
		if (reverseTrack.getC2()==normalTrack.getC2()) {
			c1 = reverseTrack.getC2();
			c2 = reverseTrack.getC1();
			c3 = normalTrack.getC1();
		}
		
		return (c1!=null && c2!=null && c3!=null) ? (c1!=c2 && c2!=c3 && c3!=c1)  : false;
	}


	public static boolean errorCrossingDetected(Track track2) {
		Crossing crossing = track2.getCrossing();
		Track track1 = crossing.getTrack1();
		/*
		 *    c3
		 *      \
		 * c1---------c2
		 *        \
		 *         c4
		 */
		Connector c1 = track1.getC1();
		Connector c2 = track1.getC2();
		Connector c3 = track2.getC1();
		Connector c4 = track2.getC2();
		
		return (c1!=null && c2!=null && c3!=null && c3!=null) ? (c1!=c2 && c1!=c3 && c1!=c4 && c2!=c3 && c2!=c4 && c3!=c4)  : false;
	}

	public static boolean errorEntrancePlacementDetected(Connector c) {
		if (c.getTrack1s().size() + c.getTrack2s().size() > 2) return false;
		
		if (c.getTrack1s().size() + c.getTrack2s().size() <= 1) return true;
		
		ArrayList<Track> ts = new ArrayList<Track>();
		ts.addAll(c.getTrack1s());
		ts.addAll(c.getTrack2s());
		Track t1 = ts.get(0);
		Track t2 = ts.get(1);
		
		return (t1.getPoint()!=null && t2.getPoint()!=null) ? t1.getPoint()==t2.getPoint() : false;
	}

	public static boolean errorTerminalPlacementDetected(Connector c) {
		if (!errorEntrancePlacementDetected(c)) return false;
		
		return c.getEntrances().size() <= 0 && c.getExits().size() <= 0;
	}

	public static boolean errorSignalDirectionDetected(Signal s, Connector c) {
		return s.getTrack()!=null && (s.getTrack().getC1()==c || s.getTrack().getC2()==c);
	}

	public static boolean errorTrackDetected(Connector c) {
		if (c.getId()==10) {
			int i = 0;
			i += 1;
		}
		
		if (c.getTrack1s().size() + c.getTrack2s().size() > 4) return false;
		
		if (c.getTrack1s().size() + c.getTrack2s().size() <= 2) return true;
		
		HashSet<Track> ts = new HashSet<Track>();
		ts.addAll(c.getTrack1s());
		ts.addAll(c.getTrack2s());
		HashSet<Point> ps = new HashSet<Point>();
		for(Track t : ts){
			if (t.getPoint()!=null) ps.add(t.getPoint());
		}
		
		return ts.size()==3 ? ps.size() >= 1 : ps.size() >= 2; 
	}
	
	public static boolean errorTrackDetected(Track t) {
		return t.getC1()!=t.getC2(); 
	}

}
