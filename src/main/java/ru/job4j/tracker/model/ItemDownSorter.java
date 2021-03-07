package ru.job4j.tracker.model;

import java.util.Comparator;

public class ItemDownSorter implements Comparator<Item> {
    @Override
    public int compare(Item first, Item second) {
        return (second.getId().compareTo(first.getId()));
    }
}
