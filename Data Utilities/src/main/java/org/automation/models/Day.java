package org.automation.models;

import java.util.HashMap;
import java.util.Map;

public enum Day {
	SUNDAY(1), MONDAY(2), TUESDAY(3), WEDNESDAY(4), THURSDAY(5), FRIDAY(6), SATURDAY(7);

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
