package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// Represents a log of events.
// This class was adapted from AlarmSystem: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
public class EventLog implements Iterable<Event> {
    private static EventLog theLog;
    private final Collection<Event> events;

    // EFFECTS: creates new empty list of events
    private EventLog() {
        events = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: creates new event log if not already created and returns the event log
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }
        return theLog;
    }

    // MODIFIES: this
    // EFFECTS: adds event to event log
    public void logEvent(Event e) {
        this.events.add(e);
    }

    // MODIFIES: this
    // EFFECTS: clears the event list
    public void clear() {
        this.events.clear();
        this.logEvent(new Event("Event log cleared."));
    }

    @Override
    // EFFECTS: returns an iterator for the events in event log
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}