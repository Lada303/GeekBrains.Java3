package Lada303.lesson4;

public class Hw4 {
    private static final int COUNTER = 5;
    private final Object mon = new Object();
    private volatile char currentLetter = 'A';

    public void run() {
        Hw4 hw4 = new Hw4();
        Thread thread1 = new Thread(() -> hw4.printLetter('A', 'B'));
        Thread thread2 = new Thread(() -> hw4.printLetter('B', 'C'));
        Thread thread3 = new Thread(() -> hw4.printLetter('C', 'A'));
        thread1.start();
        thread2.start();
        thread3.start();
    }

    private void printLetter(char printLetter, char nextLetter) {
        synchronized (mon) {
            try {
                for (int i = 0; i < COUNTER; i++) {
                    while (currentLetter != printLetter) {
                        mon.wait();
                    }
                    System.out.print(printLetter);
                    currentLetter = nextLetter;
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
