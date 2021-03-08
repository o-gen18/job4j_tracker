package ru.job4j.tracker.store;

import ru.job4j.tracker.model.Item;

import java.util.List;

public interface Store {
    String getName();

    Item add(Item item);

    boolean replace(Integer id, Item item);

    boolean delete(Integer id);

    List<Item> findAll();

    List<Item> findByName(String key);

    Item findById(Integer id);
}
