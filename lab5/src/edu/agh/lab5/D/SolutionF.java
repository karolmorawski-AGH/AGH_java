package edu.agh.lab5.D;

import java.util.HashMap;
import java.util.Map;

public class SolutionF {

	public int[] solution(float[] arr, float target) throws EmptyArrayTargetException {

		if(arr == null ) { throw new EmptyArrayTargetException(); }

		Map<Float, Integer> hashlist = new HashMap<>();

		for(int i=0; i<arr.length; i++) {
			hashlist.put(arr[i], i);
		}

		for(int i=0; i<arr.length; i++) {
			if(hashlist.get(target-arr[i]) != null) {
				//get second
				int a = hashlist.get(target-arr[i]);
				int[] res = {i, a};
				return res;
			}
		}

		return null;
	}
}

