package ru.job4j.tracker;
import org.junit.Test;
import ru.job4j.tracker.singletons.TrackerSingleFour;
import ru.job4j.tracker.singletons.TrackerSingleOne;
import ru.job4j.tracker.singletons.TrackerSingleThree;
import ru.job4j.tracker.singletons.TrackerSingleTwo;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class TrackerSingleTest {
    @Test
    public void whenTrackerSingleOne() {
        TrackerSingleOne tracker = TrackerSingleOne.INSTANCE;
        TrackerSingleOne tracker2 = TrackerSingleOne.INSTANCE;
        TrackerSingleOne tracker3 = TrackerSingleOne.INSTANCE;
        TrackerSingleOne expect = TrackerSingleOne.INSTANCE;
        TrackerSingleOne next = null;
        if (tracker == tracker2 && tracker2 == tracker3) {
            next = TrackerSingleOne.INSTANCE;
        }
        assertThat(next, is(expect));
    }

    @Test
    public void whenTrackerSingleTwo() {
        TrackerSingleTwo tracker = TrackerSingleTwo.getInstance();
        TrackerSingleTwo tracker2 = TrackerSingleTwo.getInstance();
        TrackerSingleTwo tracker3 = TrackerSingleTwo.getInstance();
        TrackerSingleTwo next = null;
        if (tracker == tracker2 && tracker2 == tracker3) {
            next = TrackerSingleTwo.getInstance();
        }
        assertThat(next, is(tracker));
    }

    @Test
    public void whenTrackerSingleThree() {
        TrackerSingleThree tr = TrackerSingleThree.getInstance();
        TrackerSingleThree tr2 = TrackerSingleThree.getInstance();
        TrackerSingleThree tr3 = TrackerSingleThree.getInstance();
        TrackerSingleThree next = null;
        if (tr == tr2 && tr2 == tr3) {
            next = TrackerSingleThree.getInstance();
        }
        assertThat(next, is(tr));
    }

    @Test
    public void whenTrackerSingleFour() {
        TrackerSingleFour tr = TrackerSingleFour.getInstance();
        TrackerSingleFour tr2 = TrackerSingleFour.getInstance();
        TrackerSingleFour tr3 = TrackerSingleFour.getInstance();
        TrackerSingleFour next = null;
        if (tr == tr2 && tr2 == tr3) {
            next = TrackerSingleFour.getInstance();
        }
        assertThat(next, is(tr));
    }
}
