package uk.ac.swanseacoventry.cmt.ontrack.diagram.automaticLayout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

class InputParsing {

	private static final List<IInputParser> INPUT_PARSERS = new ArrayList<>();

	static {
		INPUT_PARSERS.add(new IInputParser() {

			@SuppressWarnings("unchecked")
			@Override
			public <T> Optional<T> parse(String in, Class<T> expected) {
				if (in == null || !in.matches("-?[0-9]+(.[0-9]+)?(E-?[0-9]+)?")
						|| !Number.class.isAssignableFrom(expected))
					return Optional.empty();
				try {
					BigDecimal d = new BigDecimal(in);
					if (expected.equals(Integer.class))
						return Optional.of((T) Integer.valueOf(d.intValue()));
					if (expected.equals(Float.class))
						return Optional.of((T) Float.valueOf(d.floatValue()));
					if (expected.equals(Double.class))
						return Optional.of((T) Double.valueOf(d.doubleValue()));
					if (expected.equals(Long.class))
						return Optional.of((T) Long.valueOf(d.longValue()));
					if (expected.equals(Short.class))
						return Optional.of((T) Short.valueOf(d.shortValue()));
					if (expected.equals(Byte.class))
						return Optional.of((T) Byte.valueOf(d.byteValue()));
				} catch (NumberFormatException e) {
				}
				return Optional.ofNullable(null);
			}
		});

		INPUT_PARSERS.add(new IInputParser() {

			private final Pattern TRUE = Pattern.compile("(ye?s?)|(tr?u?e?)");
			private final Pattern FALSE = Pattern.compile("(no?)|(fa?l?s?e?)");

			@Override
			public <T> Optional<T> parse(String in, Class<T> expected) {
				String s = in.toLowerCase().trim();
				if (Boolean.class.isAssignableFrom(expected) && in != null && (TRUE.matcher(s).find())
						|| FALSE.matcher(s).find()) {
					if (TRUE.matcher(s).find())
						return Optional.of((T) Boolean.TRUE);
					if (FALSE.matcher(s).find())
						return Optional.of((T) Boolean.FALSE);
					return Optional.ofNullable(null);
				}
				return Optional.empty();
			}
		});

	}

	public static interface IInputParser {
		// Empty = illegal input. Null = Can't parse (failed)
		public <T> Optional<T> parse(String in, Class<T> expected);
	}

	public static Optional<IInputParser> getParser(Class<?> valType, String input) {
		return INPUT_PARSERS.stream().filter(a -> a.parse(input, valType).isPresent()).findFirst();
	}
}
