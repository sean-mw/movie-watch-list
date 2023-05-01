package model;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventLogTest {

    @Test
    public void testLogEvent() {
        EventLog eventLog = EventLog.getInstance();
        eventLog.clear();
        Event event = new Event("test");
        eventLog.logEvent(event);
        for (Event e : eventLog) {
            if (!Objects.equals(e.getDescription(), "Event log cleared.")) {
                assertEquals(e, event);
            }
        }
    }

    @Test
    public void testClear() {
        EventLog eventLog = EventLog.getInstance();
        eventLog.clear();
        eventLog.logEvent(new Event("test"));
        eventLog.logEvent(new Event("test"));
        eventLog.logEvent(new Event("test"));
        eventLog.clear();
        int i = 0;
        for (Event e : eventLog) {
            if (!Objects.equals(e.getDescription(), "Event log cleared.")) {
                i++;
            }
        }
        assertEquals(i, 0);
    }

    @Test
    public void testIterator() {
        EventLog eventLog = EventLog.getInstance();
        eventLog.clear();
        Event event = new Event("test");
        eventLog.logEvent(event);
        eventLog.logEvent(event);
        eventLog.logEvent(event);
        eventLog.logEvent(event);
        for (Event e : eventLog) {
            if (!Objects.equals(e.getDescription(), "Event log cleared.")) {
                assertEquals(e, event);
            }
        }
    }
}
