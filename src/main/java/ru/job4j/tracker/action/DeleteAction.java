package ru.job4j.tracker.action;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.store.Store;

import java.util.function.Consumer;

public class DeleteAction implements UserAction {
    @Override
    public String name() {
        return "=== Delete item ===";
    }

    @Override
    public boolean execute(Input input, Store sqlTracker, Consumer<String> output) {
        output.accept("Enter id: ");
        Integer id = input.askInt("");
        if (sqlTracker.delete(id)) {
            output.accept("The item has been successfully deleted");
        } else {
            output.accept("There has been no items found with this id: " + id);
        }
        return true;
    }
}
