package ru.job4j.tracker;

import java.util.function.Consumer;

public class DeleteAction implements UserAction {
    @Override
    public String name() {
        return "=== Delete item ===";
    }

    @Override
    public boolean execute(Input input, Store sqlTracker, Consumer<String> output) {
        output.accept("Enter id: ");
        String id = input.askStr("");
        if (sqlTracker.delete(id)) {
            output.accept("The item has been successfully deleted" + System.lineSeparator());
        } else {
            output.accept("There has been no items found with this id: "
                    + id + System.lineSeparator());
        }
        return true;
    }
}
