package agh.lab2;

import java.util.Comparator;

public class ItemAmountComparatorProper implements Comparator<Item> {
	@Override
	//ascending
	public int compare(Item o1, Item o2) {
		return o1.getCount() - o2.getCount();
	}
}
