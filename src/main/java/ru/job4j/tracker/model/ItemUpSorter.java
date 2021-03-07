package ru.job4j.tracker.model;

import java.util.Comparator;

public class ItemUpSorter implements Comparator<Item> {
    @Override
    public int compare(Item first, Item second) {
        return (first.getId().compareTo(second.getId()));
    }
}
