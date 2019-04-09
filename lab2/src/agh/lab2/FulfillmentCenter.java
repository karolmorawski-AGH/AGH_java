package agh.lab2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;



public class FulfillmentCenter {
    private String centername;
    private List<Item> itemList;
    private double currentcapacity;
    private double maxcapacity;


    public FulfillmentCenter(String centername, double currentcapacity, double maxcapacity) {
        ArrayList<Item> temp = new ArrayList<>();
        this.centername = centername;
        this.currentcapacity = currentcapacity;
        this.maxcapacity = maxcapacity;
        this.itemList = temp;
    }

    public FulfillmentCenter(String centername, List<Item> itemList, double maxcapacity) {
        this.currentcapacity = 0.0;
        this.maxcapacity = maxcapacity;
        this.centername = centername;
        this.itemList = itemList;
    }

    public double getCurrentcapacity() {
        return currentcapacity;
    }

    public String getCentername() {
        return centername;
    }

    public void setCentername(String centername) {
        this.centername = centername;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public double getMaxcapacity() {
        return maxcapacity;
    }

    public void setMaxcapacity(double maxcapacity) {
        this.maxcapacity = maxcapacity;
    }

    void addProduct(Item product) {
        //capacity check with adding
        if ((product.getMass() * product.getCount() + currentcapacity) > maxcapacity) {
            System.err.println("Maximum capacity has been reached");
            return;
        }
            Iterator<Item> itemIterator = itemList.iterator();
            while (itemIterator.hasNext()) {
                Item current = itemIterator.next();
                if ((product.getName().equals(current.getName())) && (product.getCondition() == current.getCondition())) {
                    current.setCount(current.getCount() + product.getCount());
                    currentcapacity = currentcapacity + current.getCount() * current.getMass();
                    return;
                }
        }
            itemList.add(product);
            currentcapacity = currentcapacity + product.getMass();

    }


    public Item getProduct(Item item) {
        //position in list
        int pos = itemList.indexOf(item);

        //if no item found
        if((pos < 0) || (itemList.isEmpty())) {
            return null;
        }
        Item newItem = itemList.get(pos);
        Item oldItem = newItem;
        if( newItem.getCount() == 1){
            itemList.remove(pos);
        } else {
            oldItem.setCount(newItem.getCount() - 1);
            itemList.set(pos,newItem);
        }
        currentcapacity -= newItem.getMass();
        return newItem;
    }

    public void removeProduct(Item item) {
        currentcapacity = currentcapacity - item.getMass()*item.getCount();
        itemList.remove(item);
    }

    //TODO FIX -   USE COMPARATOR
    public Item search(String name) {

        if(name.length() == 0){
            return null;
        }

        Iterator<Item> itemIterator  = itemList.iterator();
        while(itemIterator.hasNext()) {
        if(itemIterator.next().getName().equals(name)) {
            return itemIterator.next();
        }
        }
        return null;

    }

    public ArrayList<Item> searchPartial(String itemName) {
        ArrayList<Item> itemArray = new ArrayList<>();

        Iterator<Item> itemIterator  = itemList.iterator();
        while(itemIterator.hasNext()) {
            Item current = itemIterator.next();
            if(current.getName().contains(itemName)) {
                itemArray.add(current);
            }
        }
        return itemArray;
    }

    public int countByCondition(ItemCondition condition) {
        int counter = 0;

        Iterator<Item> itemIterator  = itemList.iterator();
        while(itemIterator.hasNext()) {
            Item current = itemIterator.next();
            if(current.getCondition() ==condition) {
                counter = counter + current.getCount();;
            }
        }
        return counter;
    }

    public void summary() {
        Iterator<Item> itemIterator  = itemList.iterator();

        double tempMass = 0.0;
        while(itemIterator.hasNext()) {
            tempMass = tempMass + itemIterator.next().getMass();
        }
        System.out.println("Location name: " + centername + "\nCurrent capacity: " + tempMass + "\nMax capacity: " + maxcapacity + '\n');

        itemIterator  = itemList.iterator();
        while(itemIterator.hasNext()) {
            itemIterator.next().print();
            System.out.println('\n');
        }
    }

    public List<Item> sortByName() {
        List<Item> temp = new ArrayList<>(itemList);
        temp.sort(new ItemNameComparator());
        return temp;
    }

    public List<Item> sortByAmount() {
        List<Item> temp = new ArrayList<>(itemList);
        temp.sort(new ItemAmountComparator());
        return temp;
    }

    public Item max() throws Exception {
        if(itemList.isEmpty()){
            throw new Exception("empty list");
        }
        return Collections.max(itemList, new ItemAmountComparatorProper());
    }



}
