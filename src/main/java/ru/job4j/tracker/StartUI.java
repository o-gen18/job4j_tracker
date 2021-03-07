package ru.job4j.tracker;

import ru.job4j.tracker.action.*;
import ru.job4j.tracker.input.ConsoleInput;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.ValidateInput;
import ru.job4j.tracker.sqlconnection.PSQLConnection;
import ru.job4j.tracker.store.MemTracker;
import ru.job4j.tracker.store.SqlTracker;
import ru.job4j.tracker.store.Store;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.function.Consumer;

public class StartUI {

    public void init(Input input, Store sqlTracker, ArrayList<UserAction> actions,
                      Consumer<String> output) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ", actions.size());
            UserAction action = actions.get(select);
            run = action.execute(input, sqlTracker, output);
        }
    }

    private void showMenu(ArrayList<UserAction> actions) {
        System.out.println("Menu.");
        for (UserAction element : actions) {
            System.out.println(actions.indexOf(element) + ". " + element.name());
        }
    }

    private Class<? extends Store> chooseStorage(Input input) {
        String choice = "Please, choose storage mode: "
        + System.lineSeparator()
        + "0 - RAM" + System.lineSeparator()
        + "1 - Database" + System.lineSeparator();
        int select = input.askInt(choice, 2);
        if (select == 0) {
            return MemTracker.class;
        } else {
            return SqlTracker.class;
        }
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Input validate = new ValidateInput(input);
        ArrayList<UserAction> actions = new ArrayList<UserAction>();
        actions.add(new CreateAction());
        actions.add(new ShowAllAction());
        actions.add(new EditAction());
        actions.add(new DeleteAction());
        actions.add(new FindByIdAction());
        actions.add(new FindByNameAction());
        actions.add(new ExitAction());
        StartUI start = new StartUI();
        Class<? extends Store> storeClass = start.chooseStorage(validate);
        Store store;
        if (storeClass == MemTracker.class) {
            store = new MemTracker();
            System.out.println(String.format("You've chosen %s for storage!", store.getName()));
            start.init(validate, store, actions, System.out::println);
        } else {
            try (Connection connection = PSQLConnection.instOf().getConnection()) {
                store = new SqlTracker(connection);
                System.out.println(String.format("You've chosen %s for storage!", store.getName()));
                start.init(validate, store, actions, System.out::println);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}