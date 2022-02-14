package Lada303.lesson5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private static CountDownLatch cdlAllCarReady;
    private static CountDownLatch cdlRaceOver;
    private static CyclicBarrier cb;

    private Race race;
    private int speed;
    private String name;

    public static void setCdlAllCarReady(CountDownLatch cdl) {
        Car.cdlAllCarReady = cdl;
    }

    public static void setCdlRaceOver(CountDownLatch cdl) {
        Car.cdlRaceOver = cdl;
    }

    public static void setCb(CyclicBarrier cb) {
        Car.cb = cb;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cdlAllCarReady.countDown();
            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
            if (i == race.getStages().size() - 1 && MainClass.isWinner.compareAndSet(false, true)) {
                System.out.println(this.name + " - WIN");
            }
        }
        cdlRaceOver.countDown();
   }
}

