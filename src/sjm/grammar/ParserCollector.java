package sjm.grammar;

import java.util.*;

import sjm.parse.IParser;

public class ParserCollector {

	private final Collectable collectable;

	interface Collectable {
		Set<IParser> collectionFrom(IParser parser);
	}

	public ParserCollector(Collectable collectable) {
		this.collectable = collectable;
	}

	public static Set<IParser> collectAllReferencedParsers(IParser parentParser) {
		ParserCollector collector = new ParserCollector(new Collectable() {
			public Set<IParser> collectionFrom(IParser parser) {
				return parser.children();
			}
		});
		return collector.collect(parentParser);
	}

	public Set<IParser> collect(IParser root) {
		Set<IParser> collector = new HashSet<IParser>();
		collect(root, collector);
		return collector;
	}

	private void collect(IParser parent, Set<IParser> collector) {
		Set<IParser> newEntries = collectable.collectionFrom(parent);
		for (IParser each : newEntries) {
			if (collector.contains(each))
				continue;
			collector.add(each);
			collect(each, collector);
		}
	}

	public static Set<IParser> collectLeftChildren(IParser candidate) {
		ParserCollector collector = new ParserCollector(new Collectable() {
			public Set<IParser> collectionFrom(IParser parser) {
				return parser.leftChildren();
			}
		});
		return collector.collect(candidate);
	}

}
