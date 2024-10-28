package task2;

import java.util.List;
import java.util.Random;

public class App {
    private static int totalYoung = 0, leftYoung = 0;
    private static int totalOld = 0, leftOld = 0;
    private static int totalBusiness = 0, leftBusiness = 0;

    public static synchronized void incrementLeftPerson(Category category) {
        switch (category) {
            case Young -> leftYoung++;
            case Old -> leftOld++;
            case Business -> leftBusiness++;
        }
    }

    public static synchronized void incrementTotalPerson(Category category) {
        switch (category) {
            case Young -> totalYoung++;
            case Old -> totalOld++;
            case Business -> totalBusiness++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<Window> windows = List.of(
                new Window(null),
                new Window(Category.Old),
                new Window(Category.Business)
        );

        Random random = new Random();
        int totalClients = 30;

        for (int i = 0; i < totalClients; i++) {
            Category category = Category.values()[random.nextInt(3)];
            Person person = new Person(category, windows);
            incrementTotalPerson(category);

            new Thread(person).start();
            Thread.sleep(5000);
        }

        Thread.sleep(10000);

        System.out.println("Процент ушедших клиентов:");
        System.out.printf("Молодые: %.2f%%%n", (double) leftYoung / totalYoung * 100);
        System.out.printf("Пожилые: %.2f%%%n", (double) leftOld / totalOld * 100);
        System.out.printf("Бизнесмены: %.2f%%%n", (double) leftBusiness / totalBusiness * 100);
    }
}
