package ru.job4j.tracker.singletons;

import ru.job4j.tracker.model.Item;

public class TrackerSingleFour {
    private TrackerSingleFour() {
    }

    public static TrackerSingleFour getInstance() {
        return Holder.INSTANCE;
    }

    public Item add(Item model) {
        return model;
    }

    private static final class Holder {
        private static final TrackerSingleFour INSTANCE = new TrackerSingleFour();
    }
}
