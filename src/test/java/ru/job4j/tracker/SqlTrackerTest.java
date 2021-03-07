package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.sqlconnection.ConnectionRollback;
import ru.job4j.tracker.sqlconnection.PSQLConnection;
import ru.job4j.tracker.store.SqlTracker;

import java.sql.Connection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SqlTrackerTest {

    @Test
    public void whenCreateItemThenSize1() throws Exception {
        try (Connection connection = (ConnectionRollback.create(
                PSQLConnection.instOf().getConnection()))) {
            SqlTracker tracker = new SqlTracker(connection);
            tracker.add(new Item("Oleg"));
            assertThat(tracker.findByName("Oleg").size(), is(1));
        }
    }

    @Test
    public void whenDeleteItemThenSize0() throws Exception {
        try (Connection connection = (ConnectionRollback.create(
                PSQLConnection.instOf().getConnection()))) {
            SqlTracker tracker = new SqlTracker(connection);
            Item first = tracker.add(new Item("Oleg"));
            String id = first.getId();
            tracker.delete(id);
            assertThat(tracker.findByName("Oleg").size(), is(0));
        }
    }

    @Test
    public void whenReplaceItemThenSameIdButDiffName() throws Exception {
        try (Connection connection = (ConnectionRollback.create(
                PSQLConnection.instOf().getConnection()))) {
            SqlTracker tracker = new SqlTracker(connection);
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
        try (Connection connection = (ConnectionRollback.create(
                PSQLConnection.instOf().getConnection()))) {
            SqlTracker tracker = new SqlTracker(connection);
            tracker.add(new Item("Oleg"));
            tracker.add(new Item("Petr"));
            tracker.add(new Item("Ivan"));
            assertThat(tracker.findAll().size(), is(3));
        }
    }

    @Test
    public void whenFindByIdThenWorksGood() throws Exception {
        try (Connection connection = (ConnectionRollback.create(
                PSQLConnection.instOf().getConnection()))) {
            SqlTracker tracker = new SqlTracker(connection);
            Item first = tracker.add(new Item("Oleg"));
            String id = first.getId();
            assertThat(tracker.findById(id).getName(), is("Oleg"));
        }
    }

    @Test
    public void whenFindByNameThenWorksGood() throws Exception {
        try (Connection connection = (ConnectionRollback.create(
                PSQLConnection.instOf().getConnection()))) {
            SqlTracker tracker = new SqlTracker(connection);
            tracker.add(new Item("Oleg"));
            tracker.add(new Item("Ivan"));
            tracker.add(new Item("Oleg"));
            tracker.add(new Item("Sergei"));
            tracker.add(new Item("Oleg"));
            assertThat(tracker.findByName("Oleg").size(), is(3));
        }
    }
}
