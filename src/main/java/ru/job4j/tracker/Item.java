package ru.job4j.tracker;

import java.util.Objects;

public class Item {
    private String id;
    private String name;

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Item{name='" + name + '\'' + ", id=" + id + '}';
    }

//    @Override
//    public int compareTo(Item another) {
//        long first = Long.parseLong(id);
//        long second = Long.parseLong(another.id);
//        return Long.compare(first, second);
//    }
}