package ru.job4j.tracker.model;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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