package edu.agh.lab5.B;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Solution {

	public int solution(List<Integer> array) throws EmptyListException, InvalidListSizeException {


		if(array.size()==0) {
			throw new EmptyListException();
		}

		//checking if list size exceeds certain value
		//TODO check whether its correct assumption
		if (array.size() > 100000 || Collections.max(array) > array.size()) {
			throw new InvalidListSizeException();
		}

		//removes all non-positive values (less hassle)
		Iterator<Integer> iterator = array.iterator();
		while(iterator.hasNext()) {
			Integer current = iterator.next();

			if(current <0) { iterator.remove();}
		}

		//checks if list is empty (again)
		if(array.isEmpty()) {
			return 1;
		}

		//O(n) time complexity [and O(1) space]

		//creating big array
		List<Integer>  harray = new ArrayList<Integer>();
		harray.addAll(array);

		for(int i = 0; i< harray.size(); i++) {
			int x = Math.abs(harray.get(i));
			if(x-1 < harray.size() && harray.get(x-1) > 0) {
				harray.set(x-1, -1*harray.get(x-1));
			}
		}

		int i = 0;
		iterator = harray.iterator();
		while(iterator.hasNext()) {
			Integer current = iterator.next();
			if(harray.get(i)>0) { return i+1; }
			i++;
		}


		return i+1;
	}
}
