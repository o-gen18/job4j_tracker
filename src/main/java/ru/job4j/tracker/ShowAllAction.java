package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ShowAllAction implements UserAction {
    @Override
    public String name() {
        return "=== Show all items ===";
    }

    @Override
    public boolean execute(Input input, Store sqlTracker, Consumer<String> output) {
        List<Item> result = sqlTracker.findAll();
        for (Item element : result) {
            output.accept("Item: " + element.getName() + "  -  Id: " + element.getId()
                    + System.lineSeparator());
        }
        return true;
    }
}
