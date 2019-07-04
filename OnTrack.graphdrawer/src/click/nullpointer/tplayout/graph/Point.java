package click.nullpointer.tplayout.graph;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A Point Node part of a Railway Graph.s
 */
public class Point extends Node {

	/**
	 * The type of this point.
	 */
	private PointType type;

	/**
	 * The reverse, entry and normal nodes connected to
	 * this point.
	 */
	private Node reverse, entry, normal;
	
	/**
	 * Cached connections array used to avoid the constant
	 * creation and destrction of arrays.
	 */
	private Node[] connected;

	/**
	 * Construct a Point with a given name and connections
	 * @param name The name of the Point
	 * @param entry The entry node connected to this Point
	 * @param normal The normal node connected to this Point
	 * @param reverse The reverse node connected to this Point
	 */
	public Point(String name, Node entry, Node normal, Node reverse) {
		super(name);
		this.reverse = reverse;
		this.entry = entry;
		this.normal = normal;
	}

	/**
	 * Construct an unnamed and disconnected point.
	 * This constructor must be used with care.
	 */
	protected Point() {}

	/**
	 * Set the reverse of this Point.
	 * @param reverse The new reverse.
	 */
	public void setReverse(Node reverse) {
		this.reverse = reverse;
		this.connected = null;
	}

	/**
	 * Set the entry of this Point.
	 * @param entry The new entry.
	 */
	public void setEntry(Node entry) {
		this.entry = entry;
		this.connected = null;
	}


	/**
	 * Set the normal of this Point.
	 * @param normal The new normal.
	 */
	public void setNormal(Node normal) {
		this.normal = normal;
		this.connected = null;
	}

	/**
	 * Get the entry node conencted to this Point.
	 * @return The entry node.
	 */
	public Node getEntry() {
		return entry;
	}

	/**
	 * Get the normal node conencted to this Point.
	 * @return The normal node.
	 */
	public Node getNormal() {
		return normal;
	}

	/**
	 * Get the reverse node conencted to this Point.
	 * @return The reverse node.
	 */
	public Node getReverse() {
		return reverse;
	}

	@Override
	public Node[] getConnections() {
		if (connected == null)
			connected = new Node[] { getNormal(), getEntry(), getReverse() };
		return connected;
	}

	/**
	 * Get the node sitting opposite of the entry node (graphically on the same 
	 * y coordinate as the entry node) determined by the type of this Point.
	 * If no type is specified for this Point an exception will be raised.
	 * @return The node opposite of the entry node on this Point.
	 */
	public Node getOppositeOfEntryNode() { // Normal if EN or Reverse if ER
		if (type.isNormalSameTrackAsEntry())
			return getNormal();
		return getReverse();
	}

	/**
	 * Get the node that is part of the branch of this point. (The node that graphically
	 * has the same X coordinate as the opposite of the entry node but different y) determined 
	 * by the type of this point. 
	 * If not type is specified for this Point an exception will be raised.
	 * @return The node at the branch of this Point.s
	 */
	public Node getBranchNode() {// Gets the node at an angle to the line formed by the other two
		if (type.isNormalSameTrackAsEntry())
			return getReverse();
		return getNormal();
	}

	/**
	 * Get the type of this Point.
	 * @return The type of this point or null if one is not set.
	 */
	public PointType getType() {
		return type;
	}

	/**
	 * Set the type of this Point.
	 * @param other the new type of this point.
	 */
	public void setType(PointType other) {
		this.type = other;
	}
	
	/**
	 * An enum to encode the differnet types of a Point.
	 * The constants of this enum can be broken down to two sets:
	 * 	- The left-facing point types
	 * 	- The right-facing point types
	 */
	public static enum PointType {
		// Left entry means that the entry point is on the left, and the fork is open to
		// the right EN means that Entry and Normal (Or Reverse if it is ER) live on the
		// same track which is not the one at an angle.
		LEFT_ENTRY_TOP_EN, // LETEN
		LEFT_ENTRY_TOP_ER, // LETER
		LEFT_ENTRY_BOTTOM_EN, // LEBEN
		LEFT_ENTRY_BOTTOM_ER, // LEBER
		//
		RIGHT_ENTRY_TOP_EN, // RETEN
		RIGHT_ENTRY_TOP_ER, // RETER
		RIGHT_ENTRY_BOTTOM_EN, // REBEN
		RIGHT_ENTRY_BOTTOM_ER; // REBER

		/**
		 * All point types that face left.
		 */
		private static final PointType[] ALL_LEFT_ENTRIES = { LEFT_ENTRY_BOTTOM_EN, LEFT_ENTRY_BOTTOM_ER,
				LEFT_ENTRY_TOP_EN, LEFT_ENTRY_TOP_ER };
		/**
		 * All point types that face right.
		 */
		private static final PointType[] ALL_RIGHT_ENTRIES = { RIGHT_ENTRY_BOTTOM_EN, RIGHT_ENTRY_BOTTOM_ER,
				RIGHT_ENTRY_TOP_EN, RIGHT_ENTRY_TOP_ER };

		/**
		 * Boolean indicating if this instance is right-facing (true if so).
		 */
		private final boolean right;
		
		/**
		 * Boolean indicating if the branch of this instance is placed underneath the main track 
		 * of the point (true if so).
		 */		
		private final boolean bottom;
		
		/**
		 * Boolean indicating if the entry is on the same track as the normal (that is, if the entry
		 * is not the branch of this point).
		 */
		private final boolean en;

		private PointType() {
			right = !(super.toString().startsWith("RIGHT"));
			bottom = (super.toString().contains("BOTTOM"));
			en = (super.toString().endsWith("_EN"));
		}

		/**
		 * Check if this type is right-facing.
		 * @return true if this type is right facing, false otherwise.
		 */
		public boolean isRight() {
			return right;
		}

		/**
		 * Check if this type has the branch placed graphically bellow the 
		 * track formed between the entry and it's opposite node. 
		 * @return true if the branch is bellow, false otherwise.s
		 */
		public boolean isBottom() {
			return bottom;
		}

		/**
		 * Check if the entry is on the same track (graphically on the same level) as
		 * the normal. 
		 * @return True if the normal is on the same level as the entry, false otherwise.s
		 */
		public boolean isNormalSameTrackAsEntry() {
			return en;
		}

		/**
		 * Select a random left-entry type. 
		 * @return a PointType facing right.
		 */
		public PointType randomLeftEntryType() {
			return ALL_LEFT_ENTRIES[ThreadLocalRandom.current().nextInt(ALL_LEFT_ENTRIES.length)];
		}
		
		/**
		 * Select a random right-entry type.
		 * @return a PointType facing left.
		 */
		public PointType randomRightEntryType() {
			return ALL_RIGHT_ENTRIES[ThreadLocalRandom.current().nextInt(ALL_RIGHT_ENTRIES.length)];
		}

		/**
		 * Select a random type different to this, that faces the same way as this type.
		 * @return A point type.
		 */
		public PointType other() {
			if (this.isRight()) {
				int random;
				do {
					random = ThreadLocalRandom.current().nextInt(ALL_LEFT_ENTRIES.length);
				} while (this == ALL_LEFT_ENTRIES[random]);
				return ALL_LEFT_ENTRIES[random];
			} else {
				int random;
				do {
					random = ThreadLocalRandom.current().nextInt(ALL_RIGHT_ENTRIES.length);
				} while (this == ALL_RIGHT_ENTRIES[random]);
				return ALL_RIGHT_ENTRIES[random];
			}
		}

		/**
		 * Convert this type to a short String.
		 * @return A short String representation of this type.s
		 */
		public String toSimpleString() {
			return toString().replaceAll("([A-Z])[A-Z]+(_|$)", "$1") + toString().charAt(toString().length() - 1);
		}

		/**
		 * Parse a String into a PointType.
		 * @param s the String to parse.
		 * @return A PointType matching the parameter String or null if the parameter is "null" 
		 * (case insensitive) or it is null.
		 */
		public static PointType fromString(String s) {
			if (s == null || s.equalsIgnoreCase("Null"))
				return null;
			return valueOf(s);
		}

	}

	@Override
	public String toDetailedString() {
		String typestr = type == null ? "null" : type + "";
		return super.toDetailedString() + ", of type: " + typestr + ".";
	}

}
