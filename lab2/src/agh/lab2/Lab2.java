package agh.lab2;

import java.util.ArrayList;
import java.util.HashMap;

public class Lab2 {
    public static void main(String[] args) throws Exception {

    	//biedronka items
	Item napoli1 = new Item("Napoli", ItemCondition.NEW, 0.05, 5);
	Item napoli2 = new Item("Napoli", ItemCondition.NEW, 0.05, 10);
	Item napoli3 = new Item("Napoli", ItemCondition.USED, 0.05, 5);
	Item pasztet = new Item("Apasztet", ItemCondition.NEW, 5, 10);

	FulfillmentCenter biedronka = new FulfillmentCenter("Biedronka chlew", 0.0, 100);
	biedronka.addProduct(napoli1);
	biedronka.addProduct(napoli2);
	biedronka.addProduct(napoli3);
	biedronka.addProduct(pasztet);

	biedronka.summary();

	biedronka.getProduct(napoli1);

	biedronka.summary();

	biedronka.removeProduct(napoli1);

	biedronka.summary();

	//TODO napraw
	//biedronka.search("Apasztet");

		//tutaj moze zeby wypisyuwal obiekt
		System.out.println(biedronka.searchPartial("Apa"));

		System.out.println(biedronka.countByCondition(ItemCondition.NEW));

		//tutaj sorty

		System.out.println(biedronka.max());





	//aliexpress items

		//amazon items

    }
}
