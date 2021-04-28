package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.store.HbmTracker;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class HbmTrackerTest {

    @Test
    public void whenCreateItemThenSize1() {
        HbmTracker tracker = new HbmTracker();
        tracker.add(new Item("First"));
        assertThat(tracker.findAll().size(), is(1));
        assertThat(tracker.findAll().get(0).getName(), is("First"));
    }

    @Test
    public void whenDeleteItemThenSize0() {
        HbmTracker tracker = new HbmTracker();
        Item first = tracker.add(new Item("Second"));
        Integer id = first.getId();
        tracker.delete(id);
        assertThat(tracker.findByName("Second").size(), is(0));
    }

    @Test
    public void whenReplaceItemThenSameIdButDiffName() {
        HbmTracker tracker = new HbmTracker();
        Item first = tracker.add(new Item("First"));
        Integer id = first.getId();
        Item second = new Item("Second");
        second.setId(first.getId());
        tracker.replace(id, second);
        assertThat(tracker.findById(id).getName(), is("Second"));
    }

    @Test
    public void whenFindAllThenSizeEqualsNumberOfAdded() {
        HbmTracker tracker = new HbmTracker();
        List<Item> items = List.of(
                new Item("First"),
                new Item("Second"),
                new Item("Third")
        );
        items.forEach(tracker::add);
        assertThat(tracker.findAll().size(), is(3));
        assertEquals(items, tracker.findAll());
    }

    @Test
    public void whenFindByIdThenFindsAddedOne() {
        HbmTracker tracker = new HbmTracker();
        Item first = tracker.add(new Item("First"));
        Integer id = first.getId();
        assertThat(tracker.findById(id).getName(), is("First"));
    }

    @Test
    public void whenFindByNameThenWorksGood() {
        HbmTracker tracker = new HbmTracker();
        Item item = tracker.add(new Item("First"));
        assertThat(tracker.findByName("First").size(), is(1));
        assertEquals(item, tracker.findByName("First").get(0));
    }
}
