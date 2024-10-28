package task1;

import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();

        Thread writer = new Thread(new WriterThread(list));
        Thread reader = new Thread(new ReaderThread(list));

        writer.start();
        reader.start();
    }
}
