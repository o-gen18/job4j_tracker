package ru.job4j.tracker.action;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.store.Store;

import java.util.function.Consumer;

public class EditAction implements UserAction {
    @Override
    public String name() {
        return "=== Edit item ===";
    }

    @Override
    public boolean execute(Input input, Store sqlTracker, Consumer<String> output) {
        output.accept("Enter id: ");
        Integer id = input.askInt("");
        output.accept("Enter new name: ");
        Item item = new Item(input.askStr(""));
        if (sqlTracker.replace(id, item)) {
            output.accept("The item has been replaced with this new one: " + item.getName());
        } else {
            output.accept("There has been no items found with this id: " + id);
        }
        return true;
    }
}
