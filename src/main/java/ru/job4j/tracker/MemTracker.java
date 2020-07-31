package ru.job4j.tracker;
import ru.job4j.tracker.Item;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @version $Id$
 * @since 0.1
 */

public class MemTracker {
    /*
     * Массив для хранения заявок.
     */
    private List<Item> items = new ArrayList<Item>();

    /*
     * Метод, реализующий добавление заявки в хранилище.
     * @param item - новая заявка.
     *
     * Следующий метод генерирует уникальный ключ для заявки на основании времени
     * и произвольного числа.
     * System.currentMillis() возвращает число, показвыающее сколько миллисекунд
     * прошло с 1 января 1970 года.
     * Создаём объект класса Random. Его метод nextLong() вернёт произвольное число.
     */

    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

    public Item add(Item item) {
        item.setId(this.generateId());
        items.add(item);
        return item;
    }

    public boolean replace(String id, Item item) {
        boolean result = false;
        for (int index = 0; index < items.size(); index++) {
            if ((items.get(index).getId()).equals(id)) {
                item.setId(id);
                items.set(index, item);
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean delete(String id) {
        boolean result = false;
        for (Item element : items) {
            if ((element.getId()).equals(id)) {
                items.remove(element);
                result = true;
                break;
            }
        }
        return result;
    }

    public ArrayList<Item> findAll() {
        return (ArrayList<Item>) this.items;
    }

    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> sameNamedItems = new ArrayList<Item>();
        for (Item element : items) {
            if (element.getName().equals(key)) {
                sameNamedItems.add(element);
            }
        }
        return sameNamedItems;
    }

    public Item findById(String id) {
        Item result = new Item();
        result = null;
        for (Item element : items) {
            if ((element.getId()).equals(id)) {
                result = element;
                break;
            }
        }
        return result;
    }
}