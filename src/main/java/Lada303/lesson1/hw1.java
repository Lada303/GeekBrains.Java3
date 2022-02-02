package Lada303.lesson1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class hw1{

    public void run(){
        //номер1
        Integer[] arr = new Integer[]{1,2,3,4,5,6,7};
        System.out.println("    arr: " + Arrays.toString(arr));
        swap(arr, 2,3);
        System.out.println("new arr: " + Arrays.toString(arr));
        swap(arr, 5,6);
        //номер2
        List<Integer> list = arrayToArrayList(arr);
        System.out.println(list.getClass() + ": " + list);
        System.out.println();
        //номер3
        System.out.println("Задача с фруктами и коробками");
        Box<Apple> appleBox1 = new Box<>();
        Box<Apple> appleBox2 = new Box<>();
        Box<Orange> orangeBox1 = new Box<>();
        Box<Orange> orangeBox2 = new Box<>();
        appleBox1.addFruitToBox(new Apple());
        appleBox1.addFruitToBox(new Apple());
        appleBox1.addFruitToBox(new Apple());
        orangeBox1.addFruitToBox(new Orange());
        orangeBox1.addFruitToBox(new Orange());
        for (int i = 0; i < 10; i++) {
            appleBox2.addFruitToBox(new Apple());
            orangeBox2.addFruitToBox(new Orange());
        }
        System.out.println("appleBox1 " + appleBox1.getWeight());
        System.out.println("appleBox2 " + appleBox2.getWeight());
        System.out.println("orangeBox1 " + orangeBox1.getWeight());
        System.out.println("orangeBox2 " + orangeBox2.getWeight());

        System.out.println("appleBox1 == orangeBox1 ? " + appleBox1.compare(orangeBox1));
        System.out.println("appleBox2 == orangeBox2 ? " + appleBox2.compare(orangeBox2));

        appleBox1.moveAllFruitsToThisBox(appleBox2);
        System.out.println("appleBox1 и appleBox2 после перемещения");
        System.out.println(appleBox1.getWeight() + " " + appleBox2.getWeight());

    }

    private static <T> void swap(T[] array, int index1, int index2) {
        T cash = array[index1];
        array[index1] = array[index2];
        array[index2] = cash;
    }

    private static <T> ArrayList<T> arrayToArrayList(T[] arr) {
        ArrayList<T> list = new ArrayList<>();
        Collections.addAll(list, arr);
        return list;
    }
}

