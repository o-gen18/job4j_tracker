package ru.job4j.tracker;

import java.util.function.Consumer;

public class CreateAction implements UserAction {
    @Override
    public String name() {
        return "=== Create a new Item ===";
    }

    @Override
    public boolean execute(Input input, Store sqlTracker, Consumer<String> output) {
        output.accept("Enter name: ");
        String name = input.askStr("");
        Item item = new Item(name);
        sqlTracker.add(item);
        output.accept("Your Item has been created. Get your id: "
                + item.getId() + System.lineSeparator());
        return true;
    }
}
