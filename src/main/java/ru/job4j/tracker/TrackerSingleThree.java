package ru.job4j.tracker;

public class TrackerSingleThree {
    private static final TrackerSingleThree INSTANCE = new TrackerSingleThree();

    private TrackerSingleThree() {
    }

    public static TrackerSingleThree getInstance() {
        return INSTANCE;
    }

    public Item add(Item model) {
        return model;
    }
}
