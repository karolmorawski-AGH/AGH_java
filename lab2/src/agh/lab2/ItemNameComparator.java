package agh.lab2;

import java.util.Comparator;

public class ItemNameComparator implements  Comparator<Item> {
	@Override
	public int compare(Item o1, Item o2) {
		String a1 = o1.getName();
		String b1 = o2.getName();
		return a1.compareToIgnoreCase(b1);
	}
}
