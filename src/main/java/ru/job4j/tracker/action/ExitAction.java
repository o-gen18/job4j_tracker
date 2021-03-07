package ru.job4j.tracker.action;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.store.Store;

import java.util.function.Consumer;

public class ExitAction implements UserAction {
    @Override
    public String name() {
        return "=== Exit programm ===";
    }

    public boolean execute(Input input, Store sqlTracker, Consumer<String> output) {
        return false;
    }
}
