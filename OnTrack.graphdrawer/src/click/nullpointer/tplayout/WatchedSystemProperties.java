package click.nullpointer.tplayout;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Predicate;

/**
 * A specialized implementation of system properties that can hold
 * arbitrary Object keys and values, and introduces type checking for values
 * associated with a specific key upon first initialization for easy runtime 
 * property modification accross the running program.
 */
public class WatchedSystemProperties extends Properties {
	private static final long serialVersionUID = 9115541803949913612L;

	/**
	 * Association of keys to the type of the expected value. 
	 * The type is given as a Class
	 */
	private final Map<Object, Class<?>> typeWatch = new HashMap<>();

	/**
	 * Validators associated with keys used to validate values before associating
	 * them with a given key. 
	 */
	private final Map<Object, Predicate<Object>> validators = new HashMap<>();

	/**
	 * Global instance of properties.
	 */
	private static final WatchedSystemProperties GLOBAL = new WatchedSystemProperties();

	/**
	 * Initialize some property
	 * @param key The key of the property.
	 * @param value The initial or default value of this property.
	 * @param type The type of this property for type watching, if null the type won't be watched.
	 */
	public void initialize(Object key, Object value, Class<?> type) {
		if (contains(key))
			throw new IllegalArgumentException("Already initialized!");
		put(key, value);
		if (type != null)
			watchType(key, type);
	}

	/** WARNING: Doesn't look at compute() functions. */
	/**
	 * Specify the highest (hierarchically) type that can be associated with
	 * a property for type watching. Upon execution, if the propert is known to the system
	 * the type of it's current value will be checked and if it does not match an exception
	 * will be raised.
	 * @param key The property to watch.
	 * @param type The expected type.
	 */
	public void watchType(Object key, Class<?> type) {
		typeWatch.put(key, type);
		if (containsKey(key))
			if (!p(key).getClass().isAssignableFrom(type))
				throw new IllegalArgumentException("Type watch detected existing field with wrong type!");
	}

	/**
	 * Enable validation for a property. If the property is known to the system, upon execution of 
	 * this method, the current known associated value will be passed through the parameter validator
	 * and if the test fails, an exception will be raised.
	 * @param key The key of the property.
	 * @param val The validator predicate.
	 */
	public void enableValidation(Object key, Predicate<Object> val) {
		if (validators.containsKey(key))
			throw new IllegalArgumentException("A validator already exists for this key");
		validators.put(key, val);
		if (containsKey(key)) {
			if (!val.test(get(key)))
				throw new IllegalStateException("Validation failed for the value asociated with key: " + key);
		}
	}

	///// Overwritten methods to introduce type checking/////
	
	@Override
	public synchronized void clear() {
		validators.clear();
		typeWatch.clear();
		super.clear();
	}
	@Override
	public synchronized Object putIfAbsent(Object key, Object value) {
		if (!contains(key))
			return put(key, value);
		return null;
	}

	@Override
	public synchronized Object put(Object key, Object value) {
		if (key != null && value != null) {
			if (typeWatch.containsKey(key) && !typeWatch.get(key).isAssignableFrom(value.getClass())) {
				throw new IllegalArgumentException(
						"The type {" + value.getClass() + "} of the value, does not comply with the type restriction {"
								+ typeWatch.get(key) + "} for this propperty with key {" + key + "}");
			}
			if (validators.containsKey(key) && !validators.get(key).test(value)) {
				throw new IllegalArgumentException("The value {" + value + "} fails validity checking!");
			}
		}
		return super.put(key, value);
	}

	
	@Override
	public synchronized Object get(Object key) {
		//If somehow a type exists that fails type checking issue a warning.
		Object val = super.get(key);
		if (typeWatch.containsKey(key) && !typeWatch.get(key).isAssignableFrom(val.getClass()))
			System.err.println("Potentially incorrect type for " + key + "!");
		return val;
	}

	/**
	 * Get the global instance of {@link WatchedSystemProperties}.
	 */
	public static WatchedSystemProperties globalInstance() {
		return GLOBAL;
	}

	/**
	 * Shorthand method to retreive a property value of a specific type.
	 * @param key The propperty key to retreive from.
	 * @param type The expected type of the returned value.
	 * @return The value of the parameter type if one is associated with the key of
	 * this propperty or null otherewise.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T p(Object key, Class<T> type) {
		return (T) p(key);
	}

	/**
	 * Shorthand method to retreive a property value. This method determines the
	 * expected return type (in most cases) and requires no casting or no explicit
	 * type stating. See also: @link{WatchedSystemProperties#p(Object,Class)}
	 * @param key The propert key.
	 * @return The value associated, or null if no value exists.
	 */
	@SuppressWarnings("unchecked")
	public static <INHTYPE> INHTYPE p(Object key) {
		return (INHTYPE) WatchedSystemProperties.globalInstance().get(key);
	}

}
