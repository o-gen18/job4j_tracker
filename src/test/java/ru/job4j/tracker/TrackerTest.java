package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.action.DeleteAction;
import ru.job4j.tracker.action.FindByIdAction;
import ru.job4j.tracker.action.FindByNameAction;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.store.MemTracker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TrackerTest {
    private PrintStream def = System.out;
    private ByteArrayOutputStream out;

    @Before
    public void redirectOutputStream() {
        out = new ByteArrayOutputStream();
        def = System.out;
        System.setOut(new PrintStream(out));
    }

    @After
    public void bringBackOutputStream() {
        System.setOut(def);
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        MemTracker tracker = new MemTracker();
        Item item = new Item("Test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        MemTracker tracker = new MemTracker();
        Item previous = new Item("test1");
        //Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        //Создаём новую заявку.
        Item next = new Item("test2");
        //Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        //Обновялем заявку в трекере.
        tracker.replace(previous.getId(), next);
        //Проверяем, что заявка с таким id имеет новое имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void deleteActionTest() {
        MemTracker tracker = new MemTracker();
        Item item = new Item("First");
        tracker.add(item);
        //трекер сам генерирует id типа long.
        // Поэтому вручную ставлю id=1 после добавления.
        item.setId(1);
        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(3); //пункт меню удалить.
        when(input.askStr(any(String.class))).thenReturn("1");

        new DeleteAction().execute(input, tracker, System.out::println);

        String ln = System.lineSeparator();

        assertThat(new String(out.toByteArray()), is(
                "Enter id: " + ln + "The item has been successfully deleted" + ln + ln));
        assertThat(tracker.findAll().size(), is(0));
    }

    @Test
    public void findByIdActionTest() {
        MemTracker tracker = new MemTracker();
        Item item = new Item("First");
        tracker.add(item);
        item.setId(1);
        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(4); //пункт меню findById.
        when(input.askStr(any(String.class))).thenReturn("1");

        new FindByIdAction().execute(input, tracker, System.out::println);

        String ln = System.lineSeparator();

        assertThat(new String(out.toByteArray()), is(
                "Enter id: " + ln + "Here is your item: First" + ln + ln));
        assertThat(tracker.findAll().size(), is(1));
    }

    @Test
    public void findByNameActionTest() {
        MemTracker tracker = new MemTracker();
        Item item = new Item("First");
        tracker.add(item);
        item.setId(1);
        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(5); //пункт меню findByName.
        when(input.askStr(any(String.class))).thenReturn("First");

        new FindByNameAction().execute(input, tracker, System.out::println);

        String ln = System.lineSeparator();

        assertThat(new String(out.toByteArray()), is(
                "Enter Name: " + ln
                        + "Here are matches found: " + ln
                        + "Item: First" + "   -   id: 1" + ln + ln));
        assertThat(tracker.findAll().size(), is(1));
    }
}
