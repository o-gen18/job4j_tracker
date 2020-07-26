package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Properties;
import java.util.function.Consumer;

public class StartUI {

    private Connection initConnection() {
        try (InputStream in = StartUI.class.getClassLoader()
             .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private void init(Input input, Store sqlTracker, ArrayList<UserAction> actions,
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
        try (Store sqlTracker = new SqlTracker(start.initConnection())) {
            sqlTracker.init();
            start.init(validate, sqlTracker, actions, System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}