package domain;

import java.util.HashMap;

public class ShoppingCart extends HashMap<Movies, Integer> {

	private static final long serialVersionUID = 1L;
	public int quantity = 0;

	public Integer put(Movies key, Integer value) {
		if (super.containsKey(key))
			quantity -= super.get(key);
		Integer i = super.put(key, value);
		quantity += value;
		return i;
	}

	public Integer remove(Movies key) {
		quantity -= super.get(key);
		Integer i = super.remove(key);
		return i;
	}

}
