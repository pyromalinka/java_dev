package task1;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Random;

public class WriterThread implements Runnable {
    private final CopyOnWriteArrayList<Integer> list;
    private final Random random = new Random();

    public WriterThread(CopyOnWriteArrayList<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            int number = random.nextInt(100);
            list.add(number);
            System.out.println("Записано число: " + number);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
