package task1;

import java.util.concurrent.CopyOnWriteArrayList;

public class ReaderThread implements Runnable {
    private final CopyOnWriteArrayList<Integer> list;

    public ReaderThread(CopyOnWriteArrayList<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            if (!list.isEmpty()) {
                for (int number : list) {
                    System.out.println("Считано число: " + number);
                }
                list.clear(); //я это добавил чтобы одно и то же число не считывалось два раза в случае большой задержки
            }

            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
