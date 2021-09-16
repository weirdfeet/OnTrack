package uk.ac.swanseacoventry.cmt.ontrack.railimporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import uk.ac.swanseacoventry.cmt.ontrack.Connector;
import uk.ac.swanseacoventry.cmt.ontrack.ControlTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.Crossing;
import uk.ac.swanseacoventry.cmt.ontrack.DirectedTrack;
import uk.ac.swanseacoventry.cmt.ontrack.Entrance;
import uk.ac.swanseacoventry.cmt.ontrack.Exit;
import uk.ac.swanseacoventry.cmt.ontrack.OntrackFactory;
import uk.ac.swanseacoventry.cmt.ontrack.ReleaseTableItem;
import uk.ac.swanseacoventry.cmt.ontrack.Track;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;

public class TopologyCalculator {
    RailParser rp = new RailParser();
    private static final boolean HAERBIN_MODE = true;
    ArrayList<String> entrySignals = new ArrayList<String>();
    ArrayList<String> exitSignals = new ArrayList<String>();

    /**
     * Get the path of the directory containing a file
     * 
     * @param inputFile Path to the file
     * @return Path of the containing directory
     */
    private String getInputFolder(String inputFile) {
        int lastSep = inputFile.lastIndexOf(File.separator);
        if (lastSep >= 0)
            inputFile = inputFile.substring(0, lastSep);
        return inputFile;
    }

    public boolean isInputFolderValid(String inputFolder) {
        inputFolder = getInputFolder(inputFolder);
        File f = new File(inputFolder);
        if (!f.isDirectory())
            return false;

        String[] requiredFiles = { "Nodes.csv", "Points.csv", "Signals.csv", "Paths.csv",
                "TrackCircuits.csv", "Routes.csv" };
        for (String rqfile : requiredFiles) {
            f = new File(inputFolder + File.separator + rqfile);
            if (!f.exists() || !f.isFile())
                return false;
        }

        return true;
    }

    HashMap<TrackCircuit, Track> createdTracks = new HashMap<TrackCircuit, Track>();
    HashMap<Node, Connector> createdConnectors = new HashMap<Node, Connector>();
    HashMap<Signal, uk.ac.swanseacoventry.cmt.ontrack.Signal> createdSignals = new HashMap<Signal, uk.ac.swanseacoventry.cmt.ontrack.Signal>();
    HashMap<Point, uk.ac.swanseacoventry.cmt.ontrack.Point> createdPoints = new HashMap<Point, uk.ac.swanseacoventry.cmt.ontrack.Point>();
    HashMap<Diamond, Crossing> createdCrossings = new HashMap<Diamond, Crossing>();
    HashMap<Connector, HashSet<Track>> normalPointTracks = new HashMap<Connector, HashSet<Track>>();
    HashMap<Connector, HashSet<Track>> reversePointTracks = new HashMap<Connector, HashSet<Track>>();
    // HashMap<Point, HashSet<ControlTableItem>> normalPointRoutes = new
    // HashMap<Point, HashSet<ControlTableItem>>();
    // HashMap<Point, HashSet<ControlTableItem>> reversePointRoutes = new
    // HashMap<Point, HashSet<ControlTableItem>>();

    int conNum = 1;
    TrackPlan trackPlan = OntrackFactory.eINSTANCE.createTrackPlan(); 
    
    
    private void saveConnectorPositions(String outputFile) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.createdConnectors.size());
        sb.append("\n");
        for (Node n : this.createdConnectors.keySet()) {
            Connector c = this.createdConnectors.get(n);
            sb.append(c.getId());
            sb.append(",");
            sb.append(n.getLocationX());
            sb.append(",");
            sb.append(n.getLocationY());
            sb.append("\n");
        }
        sb.append(this.createdPoints.size());
        sb.append("\n");
        for (Point n : this.createdPoints.keySet()) {
            uk.ac.swanseacoventry.cmt.ontrack.Point p = this.createdPoints.get(n);
            sb.append(p.getName());
            sb.append(",");
            sb.append(n.getLocationX());
            sb.append(",");
            sb.append(n.getLocationY());
            sb.append("\n");
        }
        sb.append(this.createdCrossings.size());
        sb.append("\n");
        for (Diamond n : this.createdCrossings.keySet()) {
            uk.ac.swanseacoventry.cmt.ontrack.Crossing p = this.createdCrossings.get(n);
            sb.append(p.getName());
            sb.append(",");
            sb.append(n.getLocationX());
            sb.append(",");
            sb.append(n.getLocationY());
            sb.append("\n");
        }
        PrintWriter f;
        try {
            f = new PrintWriter(outputFile);
            f.print(sb.toString());
            f.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    uk.ac.swanseacoventry.cmt.ontrack.Signal getOnTrackSignal(Signal n) {
        uk.ac.swanseacoventry.cmt.ontrack.Signal s = this.createdSignals.get(n);
        if (s == null) {
            s = OntrackFactory.eINSTANCE.createSignal();
            s.setName(n.getName());
            trackPlan.getSignals().add(s);
            this.createdSignals.put(n, s);
        }

        Connector c = getOnTrackConnector(n);
        s.setConnector(c);
        c.getSignals().add(s);

        return s;
    }

    uk.ac.swanseacoventry.cmt.ontrack.Connector getOnTrackConnector(Node n) {
        Connector c = this.createdConnectors.get(n);
        if (c == null) {
            c = OntrackFactory.eINSTANCE.createConnector();
            c.setId(conNum++);
            trackPlan.getConnectors().add(c);
            this.createdConnectors.put(n, c);
        }
        return c;
    }

    private uk.ac.swanseacoventry.cmt.ontrack.Point getOnTrackPoint(final Point bravePoint) {
        uk.ac.swanseacoventry.cmt.ontrack.Point otPoint = this.createdPoints.get(bravePoint);
        if (otPoint == null) {
            if (HAERBIN_MODE) {
                Path enterPath = this.rp.getPath(bravePoint.getEnterPath());
                Path branchPath = this.rp.getPath(bravePoint.getBranchPath());

                if (!enterPath.getTracks().equals(branchPath.getTracks())) {
                    throw new IllegalArgumentException("Haerbin mode enabled but representation of point is incorrect");
                }

                otPoint = OntrackFactory.eINSTANCE.createPoint();
                otPoint.setName(bravePoint.getName());
                trackPlan.getPoints().add(otPoint);
                this.createdPoints.put(bravePoint, otPoint);

                Track normalTrack = this.createdTracks.get(enterPath.getTrack(0));
                String pointTrackName = normalTrack.getName();

                normalTrack.setName(pointTrackName);
                Track reverseTrack = OntrackFactory.eINSTANCE.createTrack();
                reverseTrack.setName(pointTrackName);
                trackPlan.getTracks().add(reverseTrack);

                otPoint.setNormalTrack(normalTrack);
                otPoint.setReverseTrack(reverseTrack);
                normalTrack.setPointNormal(otPoint);
                reverseTrack.setPointReverse(otPoint);
                
                Connector c1 = normalTrack.getC1();
                Connector c2 = normalTrack.getC2();
                // Some functional magic to get the connector at the other (outer) end of branchPath
                Connector c3 = branchPath.getTrack(0).endNodes
                        .stream().filter(
                            node -> (!getOnTrackConnector(node).equals(c1)) 
                                    && (!getOnTrackConnector(node).equals(c2))
                        )
                        .map(node -> getOnTrackConnector(node))
                        .findFirst().get();
                        
                normalTrack.setC1(c1);
                normalTrack.setC2(c2);
                reverseTrack.setC1(c1);
                reverseTrack.setC2(c3);
                
                c1.getTrack1s().add(normalTrack);
                c1.getTrack1s().add(reverseTrack);
                c2.getTrack2s().add(normalTrack);
                c3.getTrack2s().add(reverseTrack);
            } else {
                otPoint = OntrackFactory.eINSTANCE.createPoint();
                otPoint.setName(bravePoint.getName());
                trackPlan.getPoints().add(otPoint);
                this.createdPoints.put(bravePoint, otPoint);

                String pointTrackName = "TC" + bravePoint.getName();

                Connector c1 = OntrackFactory.eINSTANCE.createConnector();
                c1.setId(conNum++);
                trackPlan.getConnectors().add(c1);
                Connector c2 = OntrackFactory.eINSTANCE.createConnector();
                c2.setId(conNum++);
                trackPlan.getConnectors().add(c2);
                Connector c3 = OntrackFactory.eINSTANCE.createConnector();
                c3.setId(conNum++);
                trackPlan.getConnectors().add(c3);

                Track normalOtTrack = OntrackFactory.eINSTANCE.createTrack();
                normalOtTrack.setName(pointTrackName);
                trackPlan.getTracks().add(normalOtTrack);

                Track reverseOtTrack = OntrackFactory.eINSTANCE.createTrack();
                reverseOtTrack.setName(pointTrackName);
                trackPlan.getTracks().add(reverseOtTrack);

                otPoint.setNormalTrack(normalOtTrack);
                otPoint.setReverseTrack(reverseOtTrack);
                normalOtTrack.setPointNormal(otPoint);
                normalOtTrack.setC1(c1);
                normalOtTrack.setC2(c2);
                reverseOtTrack.setPointReverse(otPoint);
                reverseOtTrack.setC1(c1);
                reverseOtTrack.setC2(c3);
                c1.getTrack1s().add(normalOtTrack);
                c1.getTrack1s().add(reverseOtTrack);
                c2.getTrack2s().add(normalOtTrack);
                c3.getTrack2s().add(reverseOtTrack);
            }
        }
        return otPoint;
    }

    Crossing getOnTrackCrossing(Diamond n) {
        Crossing p = this.createdCrossings.get(n);
        if (p == null) {

            p = OntrackFactory.eINSTANCE.createCrossing();
            p.setName(n.getName());
            trackPlan.getCrossings().add(p);
            this.createdCrossings.put(n, p);

            Path mainEnterPath = rp.getPath(n.getMainEnter());
            Path mainExitPath = rp.getPath(n.getMainExit());
            Path branchEnterPath = rp.getPath(n.getBranchEnter());
            Path branchExitPath = rp.getPath(n.getBranchExit());
            TrackCircuit tc = mainEnterPath.getTrack(0);

            String crossingTrackName = tc.getName();

            Connector c1 = getOnTrackConnector(getEndNodeOfDiamond(n, tc, mainEnterPath));
            Connector c2 = getOnTrackConnector(getEndNodeOfDiamond(n, tc, mainExitPath));
            Connector c3 = getOnTrackConnector(getEndNodeOfDiamond(n, tc, branchEnterPath));
            Connector c4 = getOnTrackConnector(getEndNodeOfDiamond(n, tc, branchExitPath));

            Track mt = OntrackFactory.eINSTANCE.createTrack(); // main track
            mt.setName(crossingTrackName);
            trackPlan.getTracks().add(mt);

            Track bt = OntrackFactory.eINSTANCE.createTrack(); // branch track
            bt.setName(crossingTrackName);
            trackPlan.getTracks().add(bt);

            p.setTrack1(mt);
            p.setTrack2(bt);
            mt.setCrossing1(p);
            bt.setCrossing2(p);
            mt.setC1(c1);
            mt.setC2(c2);
            bt.setC1(c3);
            bt.setC2(c4);
            c1.getTrack1s().add(mt);
            c2.getTrack2s().add(mt);
            c3.getTrack1s().add(bt);
            c4.getTrack2s().add(bt);

            // add the main track as representation of the track circuit of the crossing
            this.createdTracks.put(tc, mt);
        }
        return p;
    }

    private Node getEndNodeOfDiamond(Node n, TrackCircuit tc, Path path) {
        while (!tc.endNodes.contains(n) && path != null && tc.getPaths().contains(path)) {
            n = path.getStartNode() == n ? path.getEndNode() : path.getStartNode();
            if (n.numPaths() == 2) {
                Path path0 = rp.getPath(n.getPathByIndex(0));
                Path path1 = rp.getPath(n.getPathByIndex(1));
                path = path0 == path ? path1 : path0;
            }
        }
        return n;
    }

    uk.ac.swanseacoventry.cmt.ontrack.Track getOnTrackTrack(TrackCircuit tc) {
        uk.ac.swanseacoventry.cmt.ontrack.Track t = this.createdTracks.get(tc);
        if (t == null) {
            for (Node n : tc.endNodes) {
                getOnTrackConnector(n);
            }
            if (tc.numberOfCrossings >= 1 || tc.numberOfPoints > 2)
                return null;

            t = OntrackFactory.eINSTANCE.createTrack();
            t.setName(tc.getName());
            trackPlan.getTracks().add(t);
            this.createdTracks.put(tc, t);

            Connector c1 = getOnTrackConnector(tc.endNodes.get(0));
            Connector c2 = getOnTrackConnector(tc.endNodes.get(1));
            t.setC1(c1);
            t.setC2(c2);
            c1.getTrack1s().add(t);
            c2.getTrack2s().add(t);
        }
        return t;
    }

    public void rememberNormalPointInCtrlTable(Node n, Track t, ControlTableItem ct) {
        Connector c = getOnTrackConnector(n);
        HashSet<Track> ts = normalPointTracks.get(c);
        if (ts == null) {
            ts = new HashSet<Track>();
            normalPointTracks.put(c, ts);
        }
        ts.add(t);
    }

    public void rememberReversePointInCtrlTable(Node n, Track t, ControlTableItem ct) {
        Connector c = getOnTrackConnector(n);
        HashSet<Track> ts = reversePointTracks.get(c);
        if (ts == null) {
            ts = new HashSet<Track>();
            reversePointTracks.put(c, ts);
        }
        ts.add(t);
    }

    /**
     * Import the entry and exit signals from a CSV file.
     * @param filename The CSV file containing the boundaries of this track plan
     * @throws IOException
     */
    private void parseBoundaries(String filename) throws IOException {
        this.entrySignals.clear();
        this.exitSignals.clear();

        File f = new File(filename);
        Scanner scanner = new Scanner(f);

        // read the list of entry signal node names (before the first #)
        String line = scanner.nextLine();
        String data = line.split("#", -1)[0];
        String[] parts = data.split(",");
        for (String p : parts)
            this.entrySignals.add(p.trim());

        // read the list of exit signal node names (before the first #)
        line = scanner.nextLine();
        data = line.split("#", -1)[0];
        parts = data.split(",");
        for (String p : parts)
            this.exitSignals.add(p.trim());

        // output result for debugging
        System.out.println("Entry signals to import: " + String.join(", ", this.entrySignals));
        System.out.println("Exit signals to import: " + String.join(", ", this.exitSignals));

        // Do not forget to close the scanner
        scanner.close();
    }

    private void replaceTrackConnector(Track t, Connector oldCon, Connector newCon) {
        if (t.getC1() == oldCon) {
            t.setC1(newCon);
            newCon.getTrack1s().add(t);
            oldCon.getTrack1s().remove(t);
        } else {
            t.setC2(newCon);
            newCon.getTrack2s().add(t);
            oldCon.getTrack2s().remove(t);
        }
        for (Node n : this.createdConnectors.keySet()) {
            Connector c = this.createdConnectors.get(n);
            if (c == oldCon) {
                this.createdConnectors.put(n, newCon);
            }
        }
    }

    /**
     * 
     * @param boundaryFile  Path to the CSV file that describes the boundaries of the track plan.
     * @param outputFile Path to the OnTrack domain model file to be produced.
     * @return
     */
    public File importBraveData(String boundaryFile, String outputFile) {

        readBraveCsvFiles(boundaryFile);
        // buildTopologicalRoutes();

        ResourceSet resourceSet = new ResourceSetImpl();

        String tempFile = System.getProperty("java.io.tmpdir") + File.separator
                + "temp_imported_ontrack.xmi";

        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi",
                new XMIResourceFactoryImpl());
        Resource myModel = resourceSet.createResource(URI.createFileURI(tempFile));

        ArrayDeque<Node> nodesToProcess = new ArrayDeque<Node>();
        HashSet<String> boundarySignals = new HashSet<String>(); // node surrounding the region
        boundarySignals.addAll(this.entrySignals);
        boundarySignals.addAll(this.exitSignals);

        HashSet<String> ignorePaths = computeIgnoredPaths(nodesToProcess);

        HashSet<Node> visitedNodes = visitNodes(nodesToProcess, ignorePaths);
        System.out.println("Number of Nodes imported: " + visitedNodes.size());

        importTracks(visitedNodes);

        for (TrackCircuit tc : this.createdTracks.keySet()) {
            double length = 0;
            for (Path p : tc.getPaths())
                length += p.getLength();
            System.out.println(tc.getName() + " : " + length + " m.");
        }

        System.out.println("Num of Imported Tracks: " + this.createdTracks.size());

        // expand all point point nodes to points and add their two corresponding tracks
        // node that we need to connect their connectors later
        for (Node n : visitedNodes) {
            if (n instanceof Point) {
                Point bravePoint = (Point) n;
                // TODO: You are here ~dedbepole
                if (HAERBIN_MODE) {
                    convertHaerbinPoint(bravePoint);
                } else {
                    convertPoint(bravePoint);
                }
            } else if (n instanceof Diamond) {
                Diamond bravep = (Diamond) n;
                Crossing p = getOnTrackCrossing(bravep);
                Path mainEnter = rp.getPath(bravep.getMainEnter());
                Path mainExit = rp.getPath(bravep.getMainExit());
                Path branchEnter = rp.getPath(bravep.getBranchEnter());
                Path branchExit = rp.getPath(bravep.getBranchExit());

                // replace the connector of surrounding tracks of this point with the connectors
                // of the point
                Connector c = getOnTrackConnector(bravep);
                ArrayList<Track> tracks = new ArrayList<Track>();
                tracks.addAll(c.getTracks());
                for (Track t : tracks) {
                    String tname = t.getName();
                    TrackCircuit tc = rp.getTrack(tname);
                    if (tc.getPaths().contains(mainEnter)) {
                        replaceTrackConnector(t, c, p.getTrack1().getC1());
                    } else if (tc.getPaths().contains(mainExit)) {
                        replaceTrackConnector(t, c, p.getTrack1().getC2());
                    } else if (tc.getPaths().contains(branchEnter)) {
                        replaceTrackConnector(t, c, p.getTrack2().getC1());
                    } else if (tc.getPaths().contains(branchExit)) {
                        replaceTrackConnector(t, c, p.getTrack2().getC2());
                    }
                }
            } else if (n instanceof Signal) {
                if (!exitSignals.contains(n.getName())) { // exit signals are not added in
                    Signal braves = (Signal) n;
                    uk.ac.swanseacoventry.cmt.ontrack.Signal s = getOnTrackSignal(braves);
                    Path beforePath = rp.getPath(braves.getDirPath());
                    Track t = this.createdTracks.get(beforePath.getTrack(0));
                    if (t != null) {
                        s.setTrack(t);
                        t.getSignals().add(s);
                    }
                }
            } else if (n instanceof Terminal) {
                Connector c = getOnTrackConnector(n);
                uk.ac.swanseacoventry.cmt.ontrack.Terminal ter = OntrackFactory.eINSTANCE
                        .createTerminal();
                ter.setConnector(c);
                c.setTerminal(ter);
                trackPlan.getTerminals().add(ter);
            }
        }

        // collect all routes to import; it must have its signal in visited
        HashMap<String, Route> importedRoutes = new HashMap<String, Route>();
        for (String rname : rp.getRoutes().keySet()) {
            Route r = rp.getRoute(rname);
            Signal s = r.getSignal();
            if (!visitedNodes.contains(s) || exitSignals.contains(s.getName()))
                continue;
            importedRoutes.put(rname, r);
        }
        System.out.println("Number of Imported Routes: " + importedRoutes.size());

        // generate control table for the imported track plan
        for (String rn : importedRoutes.keySet()) {
            Route r = importedRoutes.get(rn);
            ArrayList<Point> nPoints = r.getNormalPoints();
            ArrayList<Point> rPoints = r.getReversePoints();

            ControlTableItem ct = OntrackFactory.eINSTANCE.createControlTableItem();
            ct.setRoute(r.getName());

            for (Point pn : nPoints) {
                if (visitedNodes.contains(pn)) {
                    uk.ac.swanseacoventry.cmt.ontrack.Point p = getOnTrackPoint(pn);
                    ct.getNormals().add(p);
                    ct.getClears().add(p.getNormalTrack());
                }
            }

            for (Point pn : rPoints) {
                if (visitedNodes.contains(pn)) {
                    uk.ac.swanseacoventry.cmt.ontrack.Point p = getOnTrackPoint(pn);
                    ct.getReverses().add(p);
                    ct.getClears().add(p.getReverseTrack());
                }
            }

            for (TrackCircuit t : r.getTrackCircuits()) {
                Track tr = this.createdTracks.get(t);
                if (tr != null) {
                    ct.getClears().add(tr);
                    if (tr.getCrossing() != null) {
                        ct.getClears().add(tr.getCrossing().getTrack1());
                        ct.getClears().add(tr.getCrossing().getTrack2());
                    }
                }
            }

            ct.setSignal(getOnTrackSignal(r.getSignal()));
            trackPlan.getControlTable().add(ct);
        }

        // add entry track: no more needed after removing entry track 29/7
//		int entryCount = 0;
//		for(String entrySignal : entrySignals){
//			Signal braves = (Signal)rp.getNodes().get(entrySignal);
//			uk.ac.swanseacoventry.cmt.ontrack.Signal s = createdSignals.get(braves);
//			
//			Track entry = OntrackFactory.eINSTANCE.createTrack();
//			entry.setName("ENTRY_" + (entryCount++));
//			barkston.getTracks().add(entry);
//			Connector ec = OntrackFactory.eINSTANCE.createConnector();
//			ec.setId(conNum++);
//			barkston.getConnectors().add(ec);
//			
//			Connector c = getOnTrackConnector(braves);
//			
//			s.setTrack(entry);
//			
//			entry.setC1(ec);
//			entry.setC2(c);
//			entry.getSignals().add(s);
//			
//			ec.getTrack1s().add(entry);
//			c.getTrack2s().add(entry);		
//			
//		}

        for (ControlTableItem cti : trackPlan.getControlTable()) {
            // Track t = cti.getSignal().getTrack();
            Connector c = cti.getSignal().getConnector(); // t.getC1() ==
                                                          // cti.getSignal().getConnector() ?
                                                          // t.getC2() : t.getC1();
            if (c.getTracks().size() <= 1 && c.getEntrances().size() <= 0) {
                Entrance ent = OntrackFactory.eINSTANCE.createEntrance();
                ent.setConnector(c);
                c.getEntrances().add(ent);
                trackPlan.getEntrances().add(ent);
            }
        }

        for (Track t : trackPlan.getTracks()) {
            DirectedTrack dt1 = OntrackFactory.eINSTANCE.createDirectedTrack();
            dt1.setTrack(t);
            dt1.setConnector(t.getC1());
            DirectedTrack dt2 = OntrackFactory.eINSTANCE.createDirectedTrack();
            dt2.setTrack(t);
            dt2.setConnector(t.getC2());
            t.getDirectedTracks().add(dt1);
            t.getDirectedTracks().add(dt2);
        }

        // remove connectors that has no attached tracks
        HashSet<Connector> emptyConnectors = new HashSet<Connector>();
        for (Connector c : trackPlan.getConnectors()) {
            if (c.getTracks().isEmpty() & c.getSignals().isEmpty() ) {
                emptyConnectors.add(c);
            }
        }
        trackPlan.getConnectors().removeAll(emptyConnectors);

        for (Connector c : trackPlan.getConnectors()) {
            if (c.getTracks().size() > 1)
                continue;
        }

        // finally, generate release table, brave data have no such thing
        // generate exits
        for (ControlTableItem cti : trackPlan.getControlTable()) {
            ArrayList<DirectedTrack> directions = cti.guessDirections();
            for (DirectedTrack dt : directions) {
                Track t = dt.getTrack();
                Connector nc = dt.getConnector();
                if (t.getPoint() != null) {
                    for (Track pt : nc.getTracks()) {
                        if (t.getPoint().getNormalTrack() != pt
                                && t.getPoint().getReverseTrack() != pt) {
                            ReleaseTableItem rti = OntrackFactory.eINSTANCE
                                    .createReleaseTableItem();
                            rti.setRoute(cti.getRoute());
                            rti.setPoint(t.getPoint());
                            rti.setUnoccupiedTrack(t);
                            rti.setOccupiedTrack(pt);
                            trackPlan.getReleaseTable().add(rti);
                        }
                    }
                }
            }
            // generate exit
            if (directions.size() <= 0)
                continue;
            DirectedTrack last = directions.get(directions.size() - 1);
            Connector c = last.getConnector();
            if (c.getExits().size() > 0)
                continue;
            if (c.getTracks().size() <= 1) {
                Exit ex = OntrackFactory.eINSTANCE.createExit();
                ex.setConnector(c);
                c.getExits().add(ex);
                trackPlan.getExits().add(ex);
            }
        }

        // finally, check for any route/signal that are not compatible
        for (ControlTableItem cti : trackPlan.getControlTable()) {
            Track signalTrack = cti.getSignal().getTrack();
            if (signalTrack != null) {
                for (Track t : cti.getClears()) {
                    if (t.getName().equals(signalTrack.getName())) {
                        System.out.println("WARNING: Route " + cti.getRoute() + " and signal "
                                + cti.getSignal().getName() + " are not compatible!");
                    }
                }
            }
        }

        EList<EObject> ModelObjects = new BasicEList<EObject>();
        ModelObjects.add(trackPlan);
        myModel.getContents().addAll(ModelObjects);

        try {
            myModel.save(null);
            File f = new File(tempFile);
            File output_file = new File(outputFile);
            Files.move(f.toPath(), output_file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            saveConnectorPositions(boundaryFile + ".layout");
            return output_file;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Import data from Brave CSV files. <br><br>
     * <b> Side effect: </b> Populates data inside the {@link RailParser}
     * 
     * @param boundaryFile Path to a file describing the boundaries of a track plan
     */
    private void readBraveCsvFiles(String boundaryFile) {
        String inputFolder = getInputFolder(boundaryFile);
        System.out.println("Reading the Brave CSV files...");

        try {
            String nodesFilename = inputFolder + File.separator + "Nodes.csv";
            this.rp.parseNodes(nodesFilename);

//			String pointsFilename = inputFolder + File.separator + "Points.csv";
//			this.rp.parsePoints(pointsFilename);
//	
//			String signalsFilename = inputFolder + File.separator + "Signals.csv";
//			this.rp.parseSignals(signalsFilename);

            String pathsFilename = inputFolder + File.separator + "Paths.csv";
            this.rp.parsePaths(pathsFilename);

            String tracksFilename = inputFolder + File.separator + "TrackCircuits.csv";
            this.rp.parseTracks(tracksFilename);

            String routesFilename = inputFolder + File.separator + "Routes.csv";
            this.rp.parseRoutes(routesFilename);

            parseBoundaries(boundaryFile);
        } catch (IOException e) {
            System.err.println("Failed to read the Brave CSV files.");
            System.exit(1);
        }
    }
    
    /**
     * Calculate paths not to be used in the searching for nodes in the region, 
     * which are before then entry signals and after the exit signals. <br><br>
     * <b> Side effect: </b> Populates the queue passed as parameter.
     * 
     * @param nodesToProcess A queue for us to keep track of the nodes we need to process
     * @return A set containing the names of ignored paths
     */
    private HashSet<String> computeIgnoredPaths(ArrayDeque<Node> nodesToProcess) {
        HashSet<String> ignorePaths = new HashSet<String>(); // forbidden paths when BFS

        for (String signalName : this.entrySignals) {
            Node node = this.rp.getNode(signalName);
            if (node == null) {
                // TODO: no idea what this branch does
                String spair = signalName.substring(1, signalName.length() - 1);
                String[] pair = spair.split(":");
                node = rp.getNode(pair[0]);
                ignorePaths.add(pair[1]);
                nodesToProcess.add(node);
            } else {
                if (!(node instanceof Signal))
                    System.err.println(
                            "Node " + node.getName() + " is not an entry signal! Import will fail!");
                
                // Ignore paths leading to entry signals
                Signal sig = (Signal) node;
                String beforePath = sig.getDirPath();
                ignorePaths.add(beforePath);
                nodesToProcess.add(node);
            }
        }

        for (String signalName : exitSignals) {
            Node node = rp.getNode(signalName);
            if (node == null) {
                String spair = signalName.substring(1, signalName.length() - 1);
                String[] pair = spair.split(":");
                node = rp.getNode(pair[0]);
                for (String p : node.getPaths())
                    if (!p.equals(pair[1]))
                        ignorePaths.add(p);
                nodesToProcess.add(node);
            } else {
                if (!(node instanceof Signal))
                    System.err.println(
                            "Node " + node.getName() + " is not an exit signal! Import will fail!");
                
                // Ignore paths originating from an exit signal (i.e. ones not in its dirPath)
                Signal sig = (Signal) node;
                String beforePath = sig.getDirPath();
                for (String p : node.getPaths())
                    if (!p.equals(beforePath))
                        ignorePaths.add(p);
                nodesToProcess.add(node);
            }
        }
        
        return ignorePaths;
    }
    
    /**
     * Starting from the entry/exit signals, perform a BFS to visit all nodes within the track plan.
     * <br><br>
     * <b> Side effect: </b> {@code nodesToProcess} gets updated as we work through the nodes, and
     * eventually becomes empty.
     * 
     * @param nodesToProcess A queue for us to keep track of nodes we still need to visit. It should
     * contain just the entry and exit signals when passed.
     * @param ignoredPaths Contains paths leading to or originating from nodes that are outside the
     * track plan, and thus won't be visited.
     * @return A set containing all nodes in the track plan, including entry/exit signals.
     */
    private HashSet<Node> visitNodes(ArrayDeque<Node> nodesToProcess, HashSet<String> ignoredPaths) {
        HashSet<Node> visited = new HashSet<Node>(); // nodes in the region
        
        while (!nodesToProcess.isEmpty()) {
            Node node = nodesToProcess.pop();
            visited.add(node);
            
            for (String pathname : node.getPaths()) {
                if (!ignoredPaths.contains(pathname)) {
                    Path path = this.rp.getPath(pathname);
                    Node nextNode = path.getStartNode() == node ? 
                            path.getEndNode() : path.getStartNode();
                    
                    if (!visited.contains(nextNode)) {
                        nodesToProcess.add(nextNode);
                    }
                }
            }
        }
        
        return visited;
    }
    
    /**
     * Import all track circuits in the plan as proper {@link Track}s.<br><br>
     * 
     * <b> Side effect: </b> Modifies the internal collection of {@link Track} objects.
     * 
     * @param nodes Should contain all nodes in the track plan, including boundary signals.
     */
    private void importTracks(HashSet<Node> nodes) {
        // collect all paths to import; it must start and end with a visited node
        HashMap<String, Path> importedPaths = new HashMap<String, Path>();
        for (Node node : nodes) {
            for (String pathName : node.getPaths()) {
                Path path = this.rp.getPath(pathName);
                Node startNode = path.getStartNode();
                Node endNode = path.getEndNode();
                
                boolean shouldImport = 
                        nodes.contains(startNode) || nodes.contains(endNode);
                if (shouldImport) {
                    importedPaths.put(pathName, path);
                }
            }
        }

        // collect all track circuits to import; it must contains at least a path in
        // importedPaths
        for (String pathName : importedPaths.keySet()) {
            Path path = importedPaths.get(pathName);

            for (TrackCircuit t : path.getTracks()) {
                getOnTrackTrack(t);
            }
        }
    }
    
    /**
     * Convert a Brave {@link Point} to an OnTrack {@link uk.ac.swanseacoventry.cmt.ontrack.Point}.
     * <br><br>
     * <b> Side effects: </b> ask Phil
     * 
     * @param bravePoint the point in the brave representation.
     */
    private void convertPoint(Point bravePoint) {
        uk.ac.swanseacoventry.cmt.ontrack.Point otPoint = getOnTrackPoint(bravePoint);
        Path enterPath = this.rp.getPath(bravePoint.getEnterPath());
        Path exitPath = this.rp.getPath(bravePoint.getExitPath());
        Path branchPath = this.rp.getPath(bravePoint.getBranchPath());

        // replace the connector of surrounding tracks of this point with the connectors
        // of the point
        Connector otPointConnector = getOnTrackConnector(bravePoint);
        ArrayList<Track> otTracks = new ArrayList<Track>();
        otTracks.addAll(otPointConnector.getTracks());

        for (Track otTrack : otTracks) {
            String tname = otTrack.getName();
            TrackCircuit braveTrack = rp.getTrack(tname);
            if (braveTrack.getPaths().contains(enterPath)) {
                replaceTrackConnector(otTrack, otPointConnector, otPoint.getNormalTrack().getC1());
            } else if (braveTrack.getPaths().contains(exitPath)) {
                replaceTrackConnector(otTrack, otPointConnector, otPoint.getNormalTrack().getC2());
            } else if (braveTrack.getPaths().contains(branchPath)) {
                replaceTrackConnector(otTrack, otPointConnector, otPoint.getReverseTrack().getC2());
            }
        }
    }
    
    private void convertHaerbinPoint(Point bravePoint) {
        getOnTrackPoint(bravePoint);
    }
}
