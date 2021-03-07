package ru.job4j.tracker.action;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.store.Store;

import java.util.List;
import java.util.function.Consumer;

public class FindByNameAction implements UserAction {
    @Override
    public String name() {
        return "=== Find items by name ===";
    }

    @Override
    public boolean execute(Input input, Store sqlTracker, Consumer<String> output) {
        output.accept("Enter Name: ");
        String name = input.askStr("");
        List<Item> result = sqlTracker.findByName(name);
        output.accept("Here are matches found: ");
        for (Item element : result) {
            output.accept("Item: " + element.getName() + "   -   id: " + element.getId());
        }
        return true;
    }
}
