package edu.agh.lab5.C;

import java.util.HashSet;
import java.util.Set;

public class Substring {

	public int substring(String a, String b) {

	if((a==null) || (b==null)) { return -1; }

		Set<String> set = new HashSet<String>();
		for(int i =0; i<a.length(); i++) {
			String val = String.valueOf(a.charAt(i));
			set.add(val);
		}

		for(int i =0; i<b.length(); i++) {
			String bval = String.valueOf(b.charAt(i));
			if(!set.contains(bval))  { return -1; }
		}

	int count = 0;
	while(true) {
		if(a.contains(b)) {
			return count + 1;
		}
		a = a + a;
		count++;

		if(b.length() > 2*a.length()) { return -1; }
	}

	}
}
