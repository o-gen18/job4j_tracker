package ru.job4j.tracker; //package ru.job4j.tracker;
//
//import java.util.Collections;
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.List;
//
//public class ItemSorter implements Comparator<Item> {
//    public static void main(String[] args) {
//        Tracker tracker = new Tracker();
//        tracker.add(new Item("Petr"));
//        tracker.add(new Item("Ivan"));
//        tracker.add(new Item("Stepan"));
//        for (Item item : tracker.findAll()) {
//            System.out.println(item);
//        }
//        System.out.println(System.lineSeparator());
//        Collections.sort(tracker.findAll());
//        for (Item item : tracker.findAll()) {
//            System.out.println(item);
//        }
//        System.out.println(System.lineSeparator());
//        Collections.sort(tracker.findAll(), Collections.reverseOrder());
//        for (Item item : tracker.findAll()) {
//            System.out.println(item);
//        }
//    }
////    @Override
////    public int compare(Item first, Item second) {
////        return first.compareTo(second);
////    }

//}
