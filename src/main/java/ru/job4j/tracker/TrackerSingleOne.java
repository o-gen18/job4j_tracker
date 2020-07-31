package ru.job4j.tracker;

public enum TrackerSingleOne {
    INSTANCE;
    public Item add(Item model) {
        return model;
    }
}
