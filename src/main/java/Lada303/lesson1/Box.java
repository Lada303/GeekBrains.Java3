package Lada303.lesson1;

import java.util.ArrayList;
import java.util.List;

public class Box <T extends Fruit> {

    private final List<T> list;

    public Box() {
        this.list = new ArrayList<>();
    }

    public void addFruitToBox(T fruit) {
        list.add(fruit);
    }

    public float getWeight() {
        float sum = 0;
        for (T t : list) {
            sum += t.getWeightFruit();
        }
        return sum;
    }

    public boolean compare(Box<? extends Fruit> other) {
        //return Double.isNaN(this.getWeight() - other.getWeight());
        return Math.abs(this.getWeight() - other.getWeight()) <= 0.001;
    }

    public void moveAllFruitsToThisBox(Box<T> other) {
        this.list.addAll(other.list);
        other.list.clear();
    }

}
