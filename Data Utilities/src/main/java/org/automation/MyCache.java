package org.automation;

import java.util.HashMap;


public class MyCache {

	private static final ThreadLocal<HashMap<String, Object>> cacheSpaceHolder = new ThreadLocal<HashMap<String, Object>>() {

		HashMap<String, Object> imc = new HashMap<>();

		@Override
		public HashMap<String, Object> initialValue() {
			return imc;
		}
	};

	public MyCache() {
	}

	public void put(String key, Object value) {
		getCacheDB().put(key, value);
	}

	public Object get(String key) {
		return getCacheDB().get(key);
	}

	public Object replace(String key, Object value) {
		return getCacheDB().replace(key, value);
	}

	public void delete(String key) {
		getCacheDB().remove(key);
	}

	private static HashMap<String, Object> getCacheDB() {
		return cacheSpaceHolder.get();
	}

}
