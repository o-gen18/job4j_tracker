package ru.job4j.tracker;
import java.util.Collections;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestItemSorter {
    @Test
    public void whenUpOrder() {
        Item item1 = new Item("Petr");
        Item item2 = new Item("Ivan");
        Item item3 = new Item("Stepan");
        item1.setId("321");
        item2.setId("244");
        item3.setId("511");
        List<Item> items = Arrays.asList(item1, item2, item3);
        Collections.sort(items, new ItemUpSorter());
        List<Item> itemsSorted = Arrays.asList(item2, item1, item3);
        assertThat(items, is(itemsSorted));
    }

    @Test
    public void whenDownOrder() {
        Item item1 = new Item("Petr");
        Item item2 = new Item("Ivan");
        Item item3 = new Item("Stepan");
        item1.setId("321");
        item2.setId("244");
        item3.setId("511");
        List<Item> items = Arrays.asList(item1, item2, item3);
        Collections.sort(items, new ItemDownSorter());
        List<Item> itemsSorted = Arrays.asList(item3, item1, item2);
        assertThat(items, is(itemsSorted));
    }
}
