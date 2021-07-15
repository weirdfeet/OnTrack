package uk.ac.swanseacoventry.cmt.ontrack.railimporter;

import java.io.BufferedWriter;
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
    TrackPlan barkston = OntrackFactory.eINSTANCE.createTrackPlan(); // barkston was named due to
                                                                     // historic reason, should
                                                                     // rename to trackplan

    private void saveConnectorPositions(String outputFile) {
        StringBuilder sb = new StringBuilder();
        sb.append(createdConnectors.size());
        sb.append("\n");
        for (Node n : createdConnectors.keySet()) {
            Connector c = createdConnectors.get(n);
            sb.append(c.getId());
            sb.append(",");
            sb.append(n.getLocationX());
            sb.append(",");
            sb.append(n.getLocationY());
            sb.append("\n");
        }
        sb.append(createdPoints.size());
        sb.append("\n");
        for (Point n : createdPoints.keySet()) {
            uk.ac.swanseacoventry.cmt.ontrack.Point p = createdPoints.get(n);
            sb.append(p.getName());
            sb.append(",");
            sb.append(n.getLocationX());
            sb.append(",");
            sb.append(n.getLocationY());
            sb.append("\n");
        }
        sb.append(createdCrossings.size());
        sb.append("\n");
        for (Diamond n : createdCrossings.keySet()) {
            uk.ac.swanseacoventry.cmt.ontrack.Crossing p = createdCrossings.get(n);
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
        uk.ac.swanseacoventry.cmt.ontrack.Signal s = createdSignals.get(n);
        if (s == null) {
            s = OntrackFactory.eINSTANCE.createSignal();
            s.setName(n.getName());
            barkston.getSignals().add(s);
            createdSignals.put(n, s);
        }

        Connector c = getOnTrackConnector(n);
        s.setConnector(c);
        c.getSignals().add(s);

        return s;
    }

    uk.ac.swanseacoventry.cmt.ontrack.Connector getOnTrackConnector(Node n) {
        Connector c = createdConnectors.get(n);
        if (c == null) {
            c = OntrackFactory.eINSTANCE.createConnector();
            c.setId(conNum++);
            barkston.getConnectors().add(c);
            createdConnectors.put(n, c);
        }
        return c;
    }

    uk.ac.swanseacoventry.cmt.ontrack.Point getOnTrackPoint(Point n) {
        uk.ac.swanseacoventry.cmt.ontrack.Point p = createdPoints.get(n);
        if (p == null) {
            p = OntrackFactory.eINSTANCE.createPoint();
            p.setName(n.getName());
            barkston.getPoints().add(p);
            createdPoints.put(n, p);

            String pointTrackName = "TC" + n.getName();

            Connector c1 = OntrackFactory.eINSTANCE.createConnector();
            c1.setId(conNum++);
            barkston.getConnectors().add(c1);
            Connector c2 = OntrackFactory.eINSTANCE.createConnector();
            c2.setId(conNum++);
            barkston.getConnectors().add(c2);
            Connector c3 = OntrackFactory.eINSTANCE.createConnector();
            c3.setId(conNum++);
            barkston.getConnectors().add(c3);

            Track nt = OntrackFactory.eINSTANCE.createTrack();
            nt.setName(pointTrackName);
            barkston.getTracks().add(nt);

            Track rt = OntrackFactory.eINSTANCE.createTrack();
            rt.setName(pointTrackName);
            barkston.getTracks().add(rt);

            p.setNormalTrack(nt);
            p.setReverseTrack(rt);
            nt.setPointNormal(p);
            nt.setC1(c1);
            nt.setC2(c2);
            rt.setPointReverse(p);
            rt.setC1(c1);
            rt.setC2(c3);
            c1.getTrack1s().add(nt);
            c1.getTrack1s().add(rt);
            c2.getTrack2s().add(nt);
            c3.getTrack2s().add(rt);
        }
        return p;
    }

    Crossing getOnTrackCrossing(Diamond n) {
        Crossing p = createdCrossings.get(n);
        if (p == null) {

            p = OntrackFactory.eINSTANCE.createCrossing();
            p.setName(n.getName());
            barkston.getCrossings().add(p);
            createdCrossings.put(n, p);

            Path mainEnterPath = rp.getPaths().get(n.getMainEnter());
            Path mainExitPath = rp.getPaths().get(n.getMainExit());
            Path branchEnterPath = rp.getPaths().get(n.getBranchEnter());
            Path branchExitPath = rp.getPaths().get(n.getBranchExit());
            TrackCircuit tc = mainEnterPath.getTracks().get(0);

            String crossingTrackName = tc.getName();

            Connector c1 = getOnTrackConnector(getEndNodeOfDiamond(n, tc, mainEnterPath));
            Connector c2 = getOnTrackConnector(getEndNodeOfDiamond(n, tc, mainExitPath));
            Connector c3 = getOnTrackConnector(getEndNodeOfDiamond(n, tc, branchEnterPath));
            Connector c4 = getOnTrackConnector(getEndNodeOfDiamond(n, tc, branchExitPath));

            Track mt = OntrackFactory.eINSTANCE.createTrack(); // main track
            mt.setName(crossingTrackName);
            barkston.getTracks().add(mt);

            Track bt = OntrackFactory.eINSTANCE.createTrack(); // branch track
            bt.setName(crossingTrackName);
            barkston.getTracks().add(bt);

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
            createdTracks.put(tc, mt);
        }
        return p;
    }

    private Node getEndNodeOfDiamond(Node n, TrackCircuit tc, Path path) {
        while (!tc.endNodes.contains(n) && path != null && tc.getPaths().contains(path)) {
            n = path.getStartNode() == n ? path.getEndNode() : path.getStartNode();
            if (n.getPaths().size() == 2) {
                Path path0 = rp.getPaths().get(n.getPaths().get(0));
                Path path1 = rp.getPaths().get(n.getPaths().get(1));
                path = path0 == path ? path1 : path0;
            }
        }
        return n;
    }

    uk.ac.swanseacoventry.cmt.ontrack.Track getOnTrackTrack(TrackCircuit tc) {
        uk.ac.swanseacoventry.cmt.ontrack.Track t = createdTracks.get(tc);
        if (t == null) {
            for (Node n : tc.endNodes) {
                getOnTrackConnector(n);
            }
            if (tc.numberOfCrossings >= 1 || tc.numberOfPoints > 2)
                return null;

            t = OntrackFactory.eINSTANCE.createTrack();
            t.setName(tc.getName());
            barkston.getTracks().add(t);
            createdTracks.put(tc, t);

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

    void parseBoundary(String filename) throws IOException {

        entrySignals.clear();
        exitSignals.clear();

        File f = new File(filename);
        Scanner scanner = new Scanner(f);

        // read the list of entry signal node names
        String line = scanner.nextLine();
        String data = line.split("#", -1)[0];
        String[] parts = data.split(",");
        for (String p : parts)
            entrySignals.add(p.trim());

        // read the list of exit signal node names
        line = scanner.nextLine();
        data = line.split("#", -1)[0];
        parts = data.split(",");
        for (String p : parts)
            exitSignals.add(p.trim());

        // output result for debuging
        System.out.println("Entry signals to import: " + String.join(", ", entrySignals));
        System.out.println("Exit signals to import: " + String.join(", ", exitSignals));

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
        for (Node n : createdConnectors.keySet()) {
            Connector c = createdConnectors.get(n);
            if (c == oldCon) {
                createdConnectors.put(n, newCon);
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

        ArrayDeque<Node> queue = new ArrayDeque<Node>();

        HashSet<Node> visited = new HashSet<Node>(); // nodes in the region
        HashSet<String> boundarySignals = new HashSet<String>(); // node surrounding the region
        boundarySignals.addAll(entrySignals);
        boundarySignals.addAll(exitSignals);

        // calculate paths not to be used in the searching for nodes in the region,
        // they are before then entry signals and after the exit signals
        HashSet<String> ignorePaths = new HashSet<String>(); // forbidden paths when BFS
        for (String s : entrySignals) {
            Node n = rp.getNodes().get(s);
            if (n == null) {
                String spair = s.substring(1, s.length() - 1);
                String[] pair = spair.split(":");
                n = rp.getNodes().get(pair[0]);
                ignorePaths.add(pair[1]);
                queue.add(n);
            } else {
                if (!(n instanceof Signal))
                    System.out.println(
                            "Node " + n.getName() + " is not an entry signal! Import will fail!");
                Signal sig = (Signal) n;
                String beforePath = sig.getDirPath();
                ignorePaths.add(beforePath);
                queue.add(n);
            }
        }

        for (String s : exitSignals) {
            Node n = rp.getNodes().get(s);
            if (n == null) {
                String spair = s.substring(1, s.length() - 1);
                String[] pair = spair.split(":");
                n = rp.getNodes().get(pair[0]);
                for (String p : n.getPaths())
                    if (!p.equals(pair[1]))
                        ignorePaths.add(p);
                queue.add(n);
            } else {
                if (!(n instanceof Signal))
                    System.out.println(
                            "Node " + n.getName() + " is not an exit signal! Import will fail!");
                Signal sig = (Signal) n;
                String beforePath = sig.getDirPath();
                for (String p : n.getPaths())
                    if (!p.equals(beforePath))
                        ignorePaths.add(p);
                queue.add(n);
            }
        }

        while (!queue.isEmpty()) {
            Node n = queue.pop();
            visited.add(n);
            for (String p : n.getPaths()) {
                if (!ignorePaths.contains(p)) {
                    Path path = rp.getPaths().get(p);
                    Node nextNode = path.getStartNode() == n ? path.getEndNode()
                            : path.getStartNode();
                    if (!visited.contains(nextNode)) {
                        queue.add(nextNode);
                    }
                }
            }
        }

        System.out.println("Num of Nodes imported: " + visited.size());

        // collect all paths to import; it must start and end with a visited node
        HashMap<String, Path> importedPaths = new HashMap<String, Path>();
        for (Node n : visited) {
            for (String pname : n.getPaths()) {
                Path p = rp.getPaths().get(pname);
                if (!visited.contains(p.getStartNode()))
                    continue;
                if (!visited.contains(p.getEndNode()))
                    continue;
                importedPaths.put(pname, p);
            }
        }

        // collect all track circuits to import; it must contains at least a path in
        // importedPaths
        for (String p : importedPaths.keySet()) {
            Path path = importedPaths.get(p);
            for (TrackCircuit t : path.getTracks()) {
                getOnTrackTrack(t);
            }
        }

        for (TrackCircuit tc : createdTracks.keySet()) {
            double length = 0;
            for (Path p : tc.getPaths())
                length += p.getLength();
            System.out.println(tc.getName() + " : " + length + " m.");
        }

        System.out.println("Num of Imported Tracks: " + createdTracks.size());

        // expand all point point nodes to points and add their two corresponding tracks
        // node that we need to connect their connectors later
        for (Node n : visited) {
            if (n instanceof Point) {

                Point bravep = (Point) n;
                uk.ac.swanseacoventry.cmt.ontrack.Point p = getOnTrackPoint(bravep);
                Path enterPath = rp.getPaths().get(bravep.getEnterPath());
                Path exitPath = rp.getPaths().get(bravep.getExitPath());
                Path branchPath = rp.getPaths().get(bravep.getBranchPath());

                // replace the connector of surrounding tracks of this point with the connectors
                // of the point
                Connector c = getOnTrackConnector(bravep);
                ArrayList<Track> tracks = new ArrayList<Track>();
                tracks.addAll(c.getTracks());
                for (Track t : tracks) {
                    String tname = t.getName();
                    TrackCircuit tc = rp.getTracks().get(tname);
                    if (tc.getPaths().contains(enterPath)) {
                        replaceTrackConnector(t, c, p.getNormalTrack().getC1());
                    } else if (tc.getPaths().contains(exitPath)) {
                        replaceTrackConnector(t, c, p.getNormalTrack().getC2());
                    } else if (tc.getPaths().contains(branchPath)) {
                        replaceTrackConnector(t, c, p.getReverseTrack().getC2());
                    }
                }
            } else if (n instanceof Diamond) {
                Diamond bravep = (Diamond) n;
                Crossing p = getOnTrackCrossing(bravep);
                Path mainEnter = rp.getPaths().get(bravep.getMainEnter());
                Path mainExit = rp.getPaths().get(bravep.getMainExit());
                Path branchEnter = rp.getPaths().get(bravep.getBranchEnter());
                Path branchExit = rp.getPaths().get(bravep.getBranchExit());

                // replace the connector of surrounding tracks of this point with the connectors
                // of the point
                Connector c = getOnTrackConnector(bravep);
                ArrayList<Track> tracks = new ArrayList<Track>();
                tracks.addAll(c.getTracks());
                for (Track t : tracks) {
                    String tname = t.getName();
                    TrackCircuit tc = rp.getTracks().get(tname);
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
                    Path beforePath = rp.getPaths().get(braves.getDirPath());
                    Track t = createdTracks.get(beforePath.getTracks().get(0));
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
                barkston.getTerminals().add(ter);
            }
        }

        // collect all routes to import; it must have its signal in visited
        HashMap<String, Route> importedRoutes = new HashMap<String, Route>();
        for (String rname : rp.getRoutes().keySet()) {
            Route r = rp.getRoutes().get(rname);
            Signal s = r.getSignal();
            if (!visited.contains(s) || exitSignals.contains(s.getName()))
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
                if (visited.contains(pn)) {
                    uk.ac.swanseacoventry.cmt.ontrack.Point p = getOnTrackPoint(pn);
                    ct.getNormals().add(p);
                    ct.getClears().add(p.getNormalTrack());
                }
            }

            for (Point pn : rPoints) {
                if (visited.contains(pn)) {
                    uk.ac.swanseacoventry.cmt.ontrack.Point p = getOnTrackPoint(pn);
                    ct.getReverses().add(p);
                    ct.getClears().add(p.getReverseTrack());
                }
            }

            for (TrackCircuit t : r.getTrackCircuits()) {
                Track tr = createdTracks.get(t);
                if (tr != null) {
                    ct.getClears().add(tr);
                    if (tr.getCrossing() != null) {
                        ct.getClears().add(tr.getCrossing().getTrack1());
                        ct.getClears().add(tr.getCrossing().getTrack2());
                    }
                }
            }

            ct.setSignal(getOnTrackSignal(r.getSignal()));
            barkston.getControlTable().add(ct);
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

        for (ControlTableItem cti : barkston.getControlTable()) {
            // Track t = cti.getSignal().getTrack();
            Connector c = cti.getSignal().getConnector(); // t.getC1() ==
                                                          // cti.getSignal().getConnector() ?
                                                          // t.getC2() : t.getC1();
            if (c.getTracks().size() <= 1 && c.getEntrances().size() <= 0) {
                Entrance ent = OntrackFactory.eINSTANCE.createEntrance();
                ent.setConnector(c);
                c.getEntrances().add(ent);
                barkston.getEntrances().add(ent);
            }
        }

        for (Track t : barkston.getTracks()) {
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
        for (Connector c : barkston.getConnectors()) {
            if (c.getTracks().isEmpty()) {
                emptyConnectors.add(c);
            }
        }
        barkston.getConnectors().removeAll(emptyConnectors);

        for (Connector c : barkston.getConnectors()) {
            if (c.getTracks().size() > 1)
                continue;
        }

        // finally, generate release table, brave data have no such thing
        // generate exits
        for (ControlTableItem cti : barkston.getControlTable()) {
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
                            barkston.getReleaseTable().add(rti);
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
                barkston.getExits().add(ex);
            }
        }

        // finally, check for any route/signal that are not compatible
        for (ControlTableItem cti : barkston.getControlTable()) {
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
        ModelObjects.add(barkston);
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
     * Import data from Brave CSV files.
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

            String boundaryFilename = boundaryFile;
            parseBoundary(boundaryFilename);
        } catch (IOException e) {
            System.err.println("Failed to read the Brave CSV files.");
            System.exit(1);
        }
    }
}
