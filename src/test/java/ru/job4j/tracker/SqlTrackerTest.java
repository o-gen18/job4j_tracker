package ru.job4j.tracker;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SqlTrackerTest {

    public Connection init() {
        try (InputStream in = SqlTracker.class.getClassLoader()
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

    @Test
    public void whenCreateItemThenSize1() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("Oleg"));
            assertThat(tracker.findByName("Oleg").size(), is(1));
        }
    }

    @Test
    public void whenDeleteItemThenSize0() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item first = tracker.add(new Item("Oleg"));
            String id = first.getId();
            tracker.delete(id);
            assertThat(tracker.findByName("Oleg").size(), is(0));
        }
    }

    @Test
    public void whenReplaceItemThenSameIdButDiffName() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item first = tracker.add(new Item("Oleg"));
            String id = first.getId();
            tracker.replace(id, new Item("James"));
            assertThat(tracker.findById(id).getName(), is("James"));
        }
    }

    /**
     * Tests FindAll method. Requires the table to be empty.
     * @throws Exception
     */

    @Test
    public void whenFindAllThenSizeEqualsNumberOfAdded() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("Oleg"));
            tracker.add(new Item("Petr"));
            tracker.add(new Item("Ivan"));
            assertThat(tracker.findAll().size(), is(3));
        }
    }

    @Test
    public void whenFindByIdThenWorksGood() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item first = tracker.add(new Item("Oleg"));
            String id = first.getId();
            assertThat(tracker.findById(id).getName(), is("Oleg"));
        }
    }

    @Test
    public void whenFindByNameThenWorksGood() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("Oleg"));
            tracker.add(new Item("Ivan"));
            tracker.add(new Item("Oleg"));
            tracker.add(new Item("Sergei"));
            tracker.add(new Item("Oleg"));
            assertThat(tracker.findByName("Oleg").size(), is(3));
        }
    }
}
