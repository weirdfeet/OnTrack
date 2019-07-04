package click.nullpointer.tplayout.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import click.nullpointer.tplayout.graph.Node;
import click.nullpointer.tplayout.graph.Point;
import click.nullpointer.tplayout.graph.Track;
import click.nullpointer.tplayout.graph.Point.PointType;

public class SAGraphIO {

	public static void saveGraph(Node start, File to)
			throws IOException, ParserConfigurationException, TransformerException {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("graph");
		doc.appendChild(rootElement);

		Element staff = doc.createElement("Nodes");
		rootElement.appendChild(staff);

		GraphUtilities.traverse(start, a -> {
			Element e = doc.createElement(a.getClass().getSimpleName());
			if (a instanceof Point) {
				savePoint((Point) a, e);
			} else if (a instanceof Track) {
				saveTrack((Track) a, e);
			} else
				throw new IllegalStateException("Unsupported Node implementation!");
			staff.appendChild(e);
			return true;
		});

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

		StreamResult result = new StreamResult(to);
		DOMSource source = new DOMSource(doc);
		transformer.transform(source, result);
		System.out.println("File saved!");
	}

	public static Node load(File f) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(f);
		doc.getDocumentElement().normalize();
		Element root = doc.getDocumentElement();
		Element nodes = (Element) root.getElementsByTagName("Nodes").item(0);

		NodeList l = nodes.getChildNodes();
		Map<Node, Element> nodeObj = new HashMap<Node, Element>();
		Map<String, Node> nameMap = new HashMap<String, Node>();
		for (int i = 0; i < l.getLength(); i++) {
			if (l.item(i).getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
				Element e = (Element) l.item(i);
				if (e.getNodeName().equalsIgnoreCase("point")) {
					Point p = parsePoint(e);
					nodeObj.put(p, e);
					nameMap.put(p.getName(), p);
				} else if (e.getNodeName().equalsIgnoreCase("node")) {// TODO change to track?
					Node p = parseTrack(e);
					nodeObj.put(p, e);
					nameMap.put(p.getName(), p);
				} else {
					System.err.println("Invalid element type '" + e.getNodeName() + "'!");
				}
			}
		}

		for (Node n : nodeObj.keySet()) {
			if (n instanceof Point) {
				Point o = (Point) n;
				Element e = nodeObj.get(n);
				Node entry = nameMap.get(e.getAttribute("Entry"));
				Node normal = nameMap.get(e.getAttribute("Normal"));
				Node reverse = nameMap.get(e.getAttribute("Reverse"));
				o.setEntry(entry);
				o.setNormal(normal);
				o.setReverse(reverse);
			} else {
				Track t = (Track) n;
				String bfor = nodeObj.get(n).getAttribute("C1");
				String aftr = nodeObj.get(n).getAttribute("C2");
				Node before = nameMap.get(bfor);
				Node after = nameMap.get(aftr);
				t.setConnectionA(before);
				t.setConnectionB(after);
			}
		}
		System.err.println("Warn: Hotfix in SAGraphIO");
		for (Node b : nameMap.values())
			if (b instanceof Point)
				((Point) b).setType(PointType.RIGHT_ENTRY_TOP_ER);
		
		return GraphUtilities.findStartingNode(nameMap.values().iterator().next());
	}

	private static void savePoint(Point a, Element e) {
		e.setAttribute("Name", a.getName());
		e.setAttribute("y", String.valueOf(a.y));
		e.setAttribute("x", String.valueOf(a.x));
		e.setAttribute("Normal", a.getNormal() == null ? "null" : a.getNormal().getName());
		e.setAttribute("Entry", a.getEntry() == null ? "null" : a.getEntry().getName());// TODO general
																						// connections!
		e.setAttribute("Reverse", a.getReverse() == null ? "null" : a.getReverse().getName());
		e.setAttribute("PointType", a.getType() == null ? "null" : a.getType().toString());
	}

	private static Point parsePoint(Element e) {
		Point result = null;
		try {
			Constructor<Point> c = Point.class.getDeclaredConstructor();
			c.setAccessible(true);
			result = c.newInstance();
		} catch (ReflectiveOperationException xe) {
			xe.printStackTrace();
		}
		result.setName(e.getAttribute("Name"));
		result.x = (Integer.parseInt(attributeOrDefault("x", e, "0")));
		result.y = (Integer.parseInt(attributeOrDefault("y", e, "0")));
		result.setType(PointType.fromString(attributeOrDefault("PointType", e, null)));
		return result;
	}

	private static String attributeOrDefault(String key, Element e, String defaul) {
		String v = e.getAttribute(key);
		if (v.isEmpty())
			return defaul;
		return v;
	}

	private static void saveTrack(Track a, Element e) {
		e.setAttribute("Name", a.getName());
		e.setAttribute("y", String.valueOf(a.y));
		e.setAttribute("x", String.valueOf(a.x));
		e.setAttribute("C1", a.getConnectionB() == null ? "null" : a.getConnectionB().getName());
		e.setAttribute("C2", a.getConnectionA() == null ? "null" : a.getConnectionA().getName());// TODO general
		// connections!
	}

	private static Track parseTrack(Element e) {
		Track result = null;
		try {
			Constructor<Track> c = Track.class.getDeclaredConstructor();
			c.setAccessible(true);
			result = c.newInstance();
		} catch (ReflectiveOperationException ex) {
			ex.printStackTrace();
		}
		result.setName(e.getAttribute("Name"));
		result.x = (Integer.parseInt(attributeOrDefault("x", e, "0")));
		result.y = (Integer.parseInt(attributeOrDefault("y", e, "0")));
		return result;
	}

	public static interface XMLSerializable {
		public void toXML(Element e);

		public void fromXML(Element e, Map<String, Node> nodes);
	}

}
