package agh.lab2;

public class Item implements Comparable<Item> {
    private String name;
    private ItemCondition condition;
    private double mass;
    private int count;

    public Item(String name, ItemCondition condition, double mass, int count) {
        this.name = name;
        this.condition = condition;
        this.mass = mass;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemCondition getCondition() {
        return condition;
    }

    public void setCondition(ItemCondition condition) {
        this.condition = condition;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    void print() {
        System.out.print("Name: " + name + "\nCondition: " + condition.toString() + "\nMass: " + mass + "\nCount: " + count + "\n");
    }

    @Override
    public int compareTo(Item o) {
        int compare = this.getName().compareTo(o.getName());

        if (compare < 0) {
            return -1;
        }
        else if (compare > 0) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
