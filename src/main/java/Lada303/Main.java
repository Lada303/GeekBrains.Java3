package Lada303;

import Lada303.lesson7.ClassTest;
import Lada303.lesson7.Hw7;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException,
            IllegalAccessException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        Hw7.start(ClassTest.class);
        Hw7.start("Lada303.lesson7.ClassTest");
    }
}
