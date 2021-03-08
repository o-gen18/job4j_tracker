package ru.job4j.tracker.action;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.store.Store;

import java.util.function.Consumer;

public class FindByIdAction implements UserAction {
    @Override
    public String name() {
        return "=== Find item by id ===";
    }

    @Override
    public boolean execute(Input input, Store sqlTracker, Consumer<String> output) {
        output.accept("Enter id: ");
        Integer id = input.askInt("");
        output.accept("Here is your item: " + sqlTracker.findById(id).getName());
    return true;
    }
}
