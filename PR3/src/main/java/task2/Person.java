package task2;

import java.util.List;
import java.util.Random;

public class Person implements Runnable {
    private final Category category;
    private final List<Window> windows;
    private final String id;

    // Статические счетчики для каждой категории
    private static int youngCount = 0;
    private static int oldCount = 0;
    private static int businessCount = 0;

    public Person(Category category, List<Window> windows) {
        this.category = category;
        this.windows = windows;

        this.id = switch (category) {
            case Young -> "Young №" + (++youngCount);
            case Old -> "Old №" + (++oldCount);
            case Business -> "Business №" + (++businessCount);
        };
    }

    public Category getCategory() {
        return category;
    }

    public String getId() {
        return id;
    }

    @Override
    public void run() {
        Random random = new Random();
        Window selectedWindow = windows.get(random.nextInt(windows.size()));

        if (!selectedWindow.tryService(this)) {
            System.out.println("Гражданин " + id + " сильно разозлися, накричал на всех вокруг, " +
                    "пообещал, что он пожалуется в высшие инстанции, " +
                    "и разгневанно ушел.");

            App.incrementLeftPerson(category);
        }
    }
}
