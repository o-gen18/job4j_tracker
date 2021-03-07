package ru.job4j.tracker.action;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.store.Store;

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
            output.accept("Item: " + element.getName() + "  -  Id: " + element.getId());
        }
        return true;
    }
}
