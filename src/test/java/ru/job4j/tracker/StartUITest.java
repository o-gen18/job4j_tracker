package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.action.FindByNameAction;
import ru.job4j.tracker.action.ShowAllAction;
import ru.job4j.tracker.action.UserAction;
import ru.job4j.tracker.input.StubInput;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.store.MemTracker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Consumer<String> output = new Consumer<>() {
        private final PrintStream stdout = new PrintStream(out);

        @Override
        public void accept(String s) {
            stdout.println(s);
        }
    };

    @Test
    public void whenPrtMenu() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        StubInput input = new StubInput(
                new String[] {"0"}
        );
        StubAction action = new StubAction();
        ArrayList<UserAction> stubAction = new ArrayList<>();
        stubAction.add(action);
        new StartUI().init(input, new MemTracker(), stubAction, output);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("Menu.")
                .add("0. Stub action")
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(def);
    }

    @Test
    public void whenShowAllAction() {
        MemTracker tracker = new MemTracker();
        Item item = new Item("Fix bug");
        tracker.add(item);
        ShowAllAction act = new ShowAllAction();
        act.execute(new StubInput(new String[] {}), tracker, output);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("Item: " + item.getName() + "  -  Id: " + item.getId())
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
    }

    @Test
    public void whenFindByNameAction() {
        MemTracker tracker = new MemTracker();
        Item item = new Item("Find all");
        Item item2 = new Item("Find all");
        Item item3 = new Item("Find all");
        tracker.add(item);
        tracker.add(item2);
        tracker.add(item3);
        FindByNameAction act = new FindByNameAction();
        act.execute(new StubInput(new String[] {item.getName()}), tracker, output);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("Enter Name: ")
                .add("Here are matches found: ")
                .add("Item: " + item.getName() + "   -   id: " + item.getId())
                .add("Item: " + item2.getName() + "   -   id: " + item2.getId())
                .add("Item: " + item3.getName() + "   -   id: " + item3.getId())
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
    }
}
