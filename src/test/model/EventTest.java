package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    public void testEquals() {
        Event event = new Event("test");
        Event event2 = new Event("test123");
        assertTrue(event.equals(event));
        assertFalse(event.equals(event2));
        assertFalse(event.equals(new Event("testing")));
        assertFalse(event.equals(null));
        assertFalse(event.equals(EventLog.getInstance()));
    }

    @Test
    public void testHashCode() {
        Event event = new Event("test");
        assertEquals(event.hashCode(), 13 * event.getDate().hashCode() + event.getDescription().hashCode());
    }

    @Test
    public void testToString() {
        Event event = new Event("test");
        assertEquals(event.toString(), event.getDate().toString() + "\n" + event.getDescription());
    }
}
