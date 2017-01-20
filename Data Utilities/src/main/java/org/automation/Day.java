package org.automation; 
	
import java.util.HashMap;
import java.util.Map;

public enum Day {
	Sunday(1), Monday(2), Tuesday(3), Wednesday(4), Thursday(5), Friday(6), Saturday(7);

	private int value;

	private Day(int value) {
		this.value = value;
		DayLookupMap.lookupMap.put(value, this);
	}

	public int getValue() {
		return this.value;
	}

	public static Day get(int value) {
		return DayLookupMap.lookupMap.get(value);
	}

	private static final class DayLookupMap {
		private static final Map<Integer, Day> lookupMap = new HashMap<>();
	}
}
