package click.nullpointer.tplayout.util;

import static click.nullpointer.tplayout.WatchedSystemProperties.globalInstance;
import static click.nullpointer.tplayout.WatchedSystemProperties.p;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import click.nullpointer.tplayout.graph.Node;
import click.nullpointer.tplayout.graph.Point;
import click.nullpointer.tplayout.graph.Point.PointType;
import click.nullpointer.tplayout.graph.Track;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;

public class GraphVisualizer {

	static {
		GraphVizProppertyKeys.initialize();
	}

	public static BufferedImage drawGrid(List<Node> nodes) {
		Graphics2D dummy = (Graphics2D) new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics();
		dummy.setFont(p(GraphVizProppertyKeys.GRID_NAME_FONT));
		FontMetrics fm = dummy.getFontMetrics();

		int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
		int cellWidth = 0;
		for (Node n : nodes) {
			minX = Math.min(minX, n.x);
			minY = Math.min(minY, n.y);
			maxX = Math.max(maxX, n.x);
			maxY = Math.max(maxY, n.y);
			cellWidth = Math.max(cellWidth, fm.stringWidth(n.getName()));
		}
		int cellHeight = 10 + fm.getHeight() / 2;

		int imgWidth = cellWidth * ((maxX - minX) + 1);
		int imgHeight = cellHeight * ((maxY - minY) + 1);

		BufferedImage img = new BufferedImage(imgWidth + 1, imgHeight + 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D) img.getGraphics();
		g2d.setFont(p(GraphVizProppertyKeys.GRID_NAME_FONT));
		g2d.setColor(p(GraphVizProppertyKeys.GRID_BACKGROUND_COLOR));
		g2d.fillRect(0, 0, img.getWidth(), img.getHeight());
		g2d.setColor(p(GraphVizProppertyKeys.GRID_COLOR));

		for (int i = 0; i <= (maxX - minX) + 1; i++)
			g2d.drawLine(i * cellWidth, 0, i * cellWidth, imgHeight - 1);

		for (int i = 0; i <= (maxY - minY) + 1; i++)
			g2d.drawLine(0, i * cellHeight, imgWidth - 1, i * cellHeight);

		for (Node n : nodes) {
			int gx = (n.x - minX) * cellWidth + 2;
			int gy = ((n.y - minY) * cellHeight) + cellHeight - 2;
			g2d.setColor(randomColor());
			g2d.drawString(n.getName(), gx, gy);
		}

		return img;
	}

	public static BufferedImage draw(Collection<Node> nodes) {
		// Normalize coordinates
		int minx = Integer.MAX_VALUE, miny = Integer.MAX_VALUE;
		for (Node a : nodes) {
			minx = Math.min(minx, a.x);
			miny = Math.min(miny, a.y);
		}
		// Make them start at 1,1 not 0,0
		minx--;
		miny--;

		Object2LongOpenHashMap<Node> n = new Object2LongOpenHashMap<Node>();
		for (Node a : nodes) {
			n.put(a, GraphUtilities.composeCoordinates(a.x - minx, a.y - miny));
		}
		int w = Integer.MIN_VALUE, h = Integer.MIN_VALUE;
		for (Node a : n.keySet()) {
			int[] c = GraphUtilities.decomposeCoordinates(n.getLong(a));
			w = Math.max(c[0], w);
			h = Math.max(c[1], h);
		}
		w = (w + 2) * p(GraphVizProppertyKeys.TRACK_WIDTH, Integer.class);
		h = (h + 2) * p(GraphVizProppertyKeys.TRACK_VERTICAL_DISTANCE, Integer.class);

		BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D) img.getGraphics();
		g2d.setColor(p(GraphVizProppertyKeys.BACKGROUND_COLOR));
		g2d.fillRect(0, 0, w, h);
		draw(g2d, nodes, n);
		return img;
	}

	public static void draw(Graphics2D g2d, Collection<Node> nodes, Object2LongOpenHashMap<Node> coord) {
		Collection<Node> cl = nodes;

		if (p(GraphVizProppertyKeys.PREVENT_POINT_REVERSE_OVER_OVAL, Boolean.class)) {
			cl = new ArrayList<Node>(nodes);
			Collections.sort((ArrayList<Node>) cl, (a, b) -> {
				return a instanceof Point ? -1 : 1;
			});
		}

		for (Node n : cl) {
			int[] c = GraphUtilities.decomposeCoordinates(coord.getLong(n));
			drawNode(n, c[0], c[1], g2d);

		}
	}

	private static void drawNode(Node a, int x, int y, Graphics2D g) {
		int width = p(GraphVizProppertyKeys.TRACK_WIDTH), height = p(GraphVizProppertyKeys.TRACK_VERTICAL_DISTANCE);
		int gx = x * width;
		int gy = y * height;

		int endX = gx + width;

		if (a instanceof Track) {
			drawTrack(gx, gy, endX, gy, true, g, trackColor());
		} else if (a instanceof Point) {
			Color c = pointColor();
			drawTrack(gx, gy, endX, gy, true, g, c);
			PointType t = ((Point) a).getType();
			int fx = (gx + endX) / 2;
			int rx, ry;
			if (t.isRight()) {
				rx = gx + width;
			} else
				rx = gx;

			if (t.isBottom()) {
				ry = gy - height;
			} else
				ry = gy + height;

			drawTrack(fx, gy, rx, ry, false, g, c);

		} else {
			throw new UnsupportedOperationException("Unsupported node");
		}
		drawName(a.getName(), gx, gy, g);
	}

	private static void drawTrack(int sx, int sy, int fx, int fy, boolean doOvals, Graphics2D g, Color color) {
		g.setColor(color);
		g.setStroke(p(GraphVizProppertyKeys.TRACK_LINE_STROKE));
		g.drawLine(sx, sy, fx, fy);
		if (doOvals) {
			g.setColor(p(GraphVizProppertyKeys.TRACK_END_OVAL_COLOR));
			int ovalSize = (int) (p(GraphVizProppertyKeys.NODE_ELIPSE_SIZE, Integer.class)
					* p(GraphVizProppertyKeys.ZOOM_FACTOR, Float.class));
			int halfSize = ovalSize / 2;
			g.fillOval(sx - halfSize, sy - halfSize, ovalSize, ovalSize);
			g.fillOval(fx - halfSize, fy - halfSize, ovalSize, ovalSize);
		}
	}

	private static void drawName(String name, int gx, int gy, Graphics2D g) {
		g.setColor(decideLabelColor());
		g.setFont(p(GraphVizProppertyKeys.NODE_NAME_LABEL_FONT));
		int size = g.getFont().getSize();
		g.drawString(name, gx + p(GraphVizProppertyKeys.NODE_NAME_X_OFFSET, Integer.class),
				gy + size + p(GraphVizProppertyKeys.NODE_NAME_Y_OFFSET, Integer.class));
	}

	private static Color trackColor() {
		if (p(GraphVizProppertyKeys.COLOR_NODES_RANDOMLY, Boolean.class))
			return randomColor();
		return p(GraphVizProppertyKeys.TRACK_COLOR);
	}

	private static Color pointColor() {
		if (p(GraphVizProppertyKeys.COLOR_NODES_RANDOMLY, Boolean.class))
			return randomColor();
		return p(GraphVizProppertyKeys.POINT_COLOR);
	}

	private static Color decideLabelColor() {
		if (p(GraphVizProppertyKeys.COLOR_NAME_LABELS_RANDOMLY, Boolean.class))
			return randomColor();
		return p(GraphVizProppertyKeys.NODE_NAME_LABEL_COLOR);
	}

	private static final Color randomColor() {
		ThreadLocalRandom r = ThreadLocalRandom.current();
		Color c = Color.getHSBColor(r.nextFloat(), r.nextFloat() + 0.2F, r.nextFloat() + 0.3F);
		return c;
	}

	public static enum GraphVizProppertyKeys {
		ZOOM_FACTOR(0.79F), NODE_ELIPSE_SIZE(20), TRACK_LINE_STROKE(new BasicStroke(6)), NODE_NAME_LABEL_FONT(
				new Font(Font.MONOSPACED, Font.BOLD, 20)), NODE_NAME_X_OFFSET(20), NODE_NAME_Y_OFFSET(5), POINT_COLOR(
						new Color(141, 160, 203)), POINT_REVERSE_TRACK_COLOR(Color.RED), NODE_NAME_LABEL_COLOR(
								Color.CYAN), TRACK_COLOR(new Color(102, 194, 165)), COLOR_NAME_LABELS_RANDOMLY(
										true), COLOR_NODES_RANDOMLY(true), RED_CIRCLE_END_NODES(true), BACKGROUND_COLOR(
												Color.BLACK), TRACK_WIDTH(100), TRACK_VERTICAL_DISTANCE(
														60), GRID_NAME_FONT(new Font(Font.SERIF, Font.BOLD,
																22)), GRID_BACKGROUND_COLOR(
																		Color.LIGHT_GRAY), GRID_COLOR(
																				Color.DARK_GRAY), TRACK_END_OVAL_COLOR(
																						new Color(252, 141,
																								98)), PREVENT_POINT_REVERSE_OVER_OVAL(
																										false);

		private final Object defaultValue;
		private final Class<?> expectedType;

		private GraphVizProppertyKeys(Object defaultValue) {
			this(defaultValue, defaultValue.getClass());
		}

		private GraphVizProppertyKeys(Object defaultValue, Class<?> expectedType) {
			this.defaultValue = defaultValue;
			this.expectedType = expectedType;
		}

		private Class<?> getExpectedType() {
			return expectedType;
		}

		@SuppressWarnings("unchecked")
		public <E> E getDefaultValue() {
			return (E) defaultValue;
		}

		@Override
		public String toString() {
			return getClass().getSimpleName() + "." + super.toString();

		}

		public static void initialize() {
			Arrays.stream(GraphVizProppertyKeys.values()).forEach(a -> {
				if (globalInstance().containsKey(a)) {
					globalInstance().watchType(a, a.getExpectedType());
				} else
					globalInstance().initialize(a, a.getDefaultValue(), a.getExpectedType());

			});
		}

	}

}
