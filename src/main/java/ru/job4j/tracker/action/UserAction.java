package ru.job4j.tracker.action;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.store.Store;

import java.util.function.Consumer;

public interface UserAction {
    String name();

    boolean execute(Input input, Store sqlTracker, Consumer<String> output);
}
