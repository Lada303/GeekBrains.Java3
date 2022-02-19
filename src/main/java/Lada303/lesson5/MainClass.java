package Lada303.lesson5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static AtomicBoolean isWinner = new AtomicBoolean();

    public static void main(String[] args) throws InterruptedException {
        isWinner.set(false);
        Car.setCb(new CyclicBarrier(CARS_COUNT));
        final CountDownLatch cdlAllCarReady = new CountDownLatch(CARS_COUNT);
        Car.setCdlAllCarReady(cdlAllCarReady);
        final CountDownLatch cdlRaceOver = new CountDownLatch(CARS_COUNT);
        Car.setCdlRaceOver(cdlRaceOver);

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(CARS_COUNT), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        cdlAllCarReady.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        cdlRaceOver.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}

