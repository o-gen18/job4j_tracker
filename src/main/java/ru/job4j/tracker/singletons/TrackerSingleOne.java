package ru.job4j.tracker.singletons;

import ru.job4j.tracker.model.Item;

public enum TrackerSingleOne {
    INSTANCE;
    public Item add(Item model) {
        return model;
    }
}
