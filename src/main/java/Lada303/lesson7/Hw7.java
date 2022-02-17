package Lada303.lesson7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class Hw7 {
    private static final int MIN_PRIORITY = 10;
    private static final int MAX_PRIORITY = 1;

    private static Method methodBefore;
    private static Method methodAfter;
    private static boolean isMethodBeforeExist;
    private static boolean isMethodAfterExist;
    private static Map<Integer, List<Method>> mapTestMethods;

    public static void start(Class<?> someClass) throws InvocationTargetException, IllegalAccessException,
            NoSuchMethodException, InstantiationException {
        analyseClass(someClass);
    }

    public static void start(String className) throws InvocationTargetException, IllegalAccessException,
            NoSuchMethodException, InstantiationException, ClassNotFoundException {
        Class<?> someClass = Class.forName(className);
        analyseClass(someClass);
    }

    private static void analyseClass(Class<?> someClass) throws InvocationTargetException,
            IllegalAccessException, NoSuchMethodException, InstantiationException {
        methodBefore = null;
        methodAfter = null;
        isMethodBeforeExist = false;
        isMethodAfterExist = false;
        mapTestMethods = new HashMap<>();
        Method[] methods = someClass.getDeclaredMethods();
        for (Method method : methods) {
            if (isBeforeMethod(method)) {
                continue;
            }
            if (isAfterMethod(method)) {
                continue;
            }
            isTestMethod(method);
        }
        runBeforeMethod(someClass);
        runTestsMethod(someClass);
        runAfterMethod(someClass);
    }

    private static boolean isBeforeMethod(Method method) {
        if (method.isAnnotationPresent(BeforeSuite.class)) {
            if (isMethodBeforeExist) {
                throw new RuntimeException("Методов с @BeforeSuite более одного");
            }
            isMethodBeforeExist = true;
            methodBefore = method;
            return true;
        }
        return false;
    }

    private static boolean isAfterMethod(Method method) {
        if (method.isAnnotationPresent(AfterSuite.class)) {
            if (isMethodAfterExist) {
                throw new RuntimeException("Методов с @AfterSuite более одного");
            }
            isMethodAfterExist = true;
            methodAfter = method;
            return true;
        }
        return false;
    }

    private static boolean isTestMethod(Method method) {
        if (method.isAnnotationPresent(Test.class)) {
            int priority = method.getAnnotation(Test.class).priority();
            List<Method> listTestMethods = mapTestMethods.getOrDefault(priority, new ArrayList<>());
            listTestMethods.add(method);
            mapTestMethods.put(priority, listTestMethods);
            return true;
        }
        return false;
    }

    private static void runBeforeMethod(Class<?> someClass) throws InvocationTargetException,
            IllegalAccessException, NoSuchMethodException, InstantiationException {
        if (isMethodBeforeExist) {
            if (Modifier.isStatic(methodBefore.getModifiers())) {
                methodBefore.invoke(someClass);
            } else {
                methodBefore.invoke(someClass.getConstructor().newInstance());
            }
        }
    }

    private static void runAfterMethod(Class<?> someClass) throws InvocationTargetException,
            IllegalAccessException, NoSuchMethodException, InstantiationException {
        if (isMethodAfterExist) {
            if (Modifier.isStatic(methodAfter.getModifiers())) {
                methodAfter.invoke(someClass);
            } else {
                methodAfter.invoke(someClass.getConstructor().newInstance());
            }
        }
    }

    private static void runTestsMethod(Class<?> someClass) throws InvocationTargetException,
            IllegalAccessException, NoSuchMethodException, InstantiationException {
        for (int priority = MAX_PRIORITY; priority < MIN_PRIORITY; priority++) {
            if (mapTestMethods.containsKey(priority)) {
                List<Method> listTestMethods = mapTestMethods.get(priority);
                for (Method listTestMethod : listTestMethods) {
                    if (Modifier.isStatic(listTestMethod.getModifiers())) {
                        listTestMethod.invoke(someClass);
                    } else {
                        listTestMethod.invoke(someClass.getConstructor().newInstance());
                    }
                }
            }
        }
    }
}
