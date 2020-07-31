package ru.job4j.tracker;

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
