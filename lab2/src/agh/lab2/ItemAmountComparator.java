package agh.lab2;

import java.util.Comparator;

public class ItemAmountComparator implements Comparator<Item> {
	@Override
	//descending
	public int compare(Item o1, Item o2) {
		return o2.getCount() - o1.getCount();
	}
}
