package ru.job4j.tracker;

import java.util.function.Consumer;

public class FindByIdAction implements UserAction {
    @Override
    public String name() {
        return "=== Find item by id ===";
    }

    @Override
    public boolean execute(Input input, Store sqlTracker, Consumer<String> output) {
        output.accept("Enter id: ");
        String id = input.askStr("");
        output.accept("Here is your item: " + sqlTracker.findById(id).getName()
                + System.lineSeparator());
    return true;
    }
}
