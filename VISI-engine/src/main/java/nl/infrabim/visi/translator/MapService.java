package nl.infrabim.visi.translator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MapService<T> {
	private Map<String, T> map;

	public MapService() {
		map = new HashMap<>();
	}

	public T get(String id) {
		return map.get(id);
	}

	public T put(String key, T value) {
		map.put(key, value);
		return value;
	}

	public List<String> getNames() {
		ArrayList<String> nameList = new ArrayList<>(map.keySet());
		Collections.sort(nameList);
		return nameList;
	}

}
