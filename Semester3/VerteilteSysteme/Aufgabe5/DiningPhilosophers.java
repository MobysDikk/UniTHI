package Aufgabe5;

import java.util.concurrent.Semaphore;

public class DiningPhilosophers {

	public static void main(String[] args) {
		Semaphore fork1, fork2, fork3, fork4, fork5;
		fork1 = new Semaphore(1);
		fork2 = new Semaphore(1);
		fork3 = new Semaphore(1);
		fork4 = new Semaphore(1);
		fork5 = new Semaphore(1);
		Philosopher phil1, phil2, phil3, phil4, phil5;
		phil1 = new Philosopher(fork1, fork2);
		phil2 = new Philosopher(fork2, fork3);
		phil3 = new Philosopher(fork3, fork4);
		phil4 = new Philosopher(fork4, fork5);
		phil5 = new Philosopher(fork1, fork5);
		phil1.start();
		phil2.start();
		phil3.start();
		phil4.start();
		phil5.start();
	}

}
