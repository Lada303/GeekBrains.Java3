package Lada303.lesson7;

public class ClassTest {

    @BeforeSuite
    public void beforeAll() {
        System.out.println("Before All");
    }

    @Test ()
    public static void test1() {
        System.out.println("test1");
    }

    @Test (priority = 2)
    public void test2() {
        System.out.println("test2");
    }

    @Test (priority = 3)
    public static void test3() {
        System.out.println("test3");
    }

    @Test (priority = 3)
    public static void test4() {
        System.out.println("test4");
    }

    @Test (priority = 2)
    public static void test5() {
        System.out.println("test5");
    }

    @AfterSuite
    public void afterAll() {
        System.out.println("After All");
    }

}
