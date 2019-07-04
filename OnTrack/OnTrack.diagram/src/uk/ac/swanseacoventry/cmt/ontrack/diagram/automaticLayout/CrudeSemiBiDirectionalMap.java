package uk.ac.swanseacoventry.cmt.ontrack.diagram.automaticLayout;

import java.util.HashMap;

public class CrudeSemiBiDirectionalMap<K, V> extends HashMap<K, V> {
	private static final long serialVersionUID = 4979810893650746868L;
	private final HashMap<V, K> reverse = new HashMap<>();

	public K keyOf(V value) {
		return reverse.get(value);
	}
	
	@Override
	public boolean containsValue(Object value) {
		return reverse.containsKey(value);
	}
	
	@Override
	public V put(K key, V value) {
		reverse.put(value, key);
		return super.put(key, value);
	}

}
