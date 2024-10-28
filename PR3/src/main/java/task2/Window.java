package task2;

import java.util.concurrent.Semaphore;

public class Window {
    private final Semaphore semaphore = new Semaphore(1);
    private final Category allowedCategory;

    public Window(Category allowedCategory) {
        this.allowedCategory = allowedCategory;
    }

    public boolean tryService(Person person) {
        if (allowedCategory != null && person.getCategory() != allowedCategory) {
            return false;
        }

        try {
            semaphore.acquire();
            System.out.println("Обслуживаем " + person.getId() + " в окне для: " +
                    (allowedCategory == null ? "всех категорий" : allowedCategory));
            Thread.sleep(3000);
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } finally {
            semaphore.release();
        }
    }
}
