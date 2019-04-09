package agh.lab2;

import java.util.*;

public class FulfillmentCenterContainer {
	Map<String, FulfillmentCenter> centers;

	FulfillmentCenterContainer() {
		centers = new HashMap<>();
	}

	void addCenter(String name, double capacity) {
	if(centers.containsKey(name)) {
		System.err.println("ERROR: Entry with this key already exists");
		return;
	}
	ArrayList<Item> tmp = new ArrayList<>();
	FulfillmentCenter fc = new FulfillmentCenter(name, tmp, capacity);
	centers.put(name, fc);
	}

	void removeCenter(String name) {
		if(!centers.containsKey(name)) {
			System.err.println("ERROR: Entry with this key already exists");
			return;
		}
		centers.remove(name);
	}

	List<FulfillmentCenter> findEmpty() {

		ArrayList<FulfillmentCenter> temp = new ArrayList<>();

		for (Map.Entry<String, FulfillmentCenter> record : centers.entrySet())
		{
			if(record.getValue().getCurrentcapacity()==0.0) { temp.add(record.getValue()); }
		}
		return temp;
	}

	void summary() {

		//name of center +

		for (Map.Entry<String, FulfillmentCenter> record : centers.entrySet())
		{
			double var = record.getValue().getCurrentcapacity()/record.getValue().getMaxcapacity();
			System.out.println(record.getKey() + " [" + var + "%]");
		}

	}



}
