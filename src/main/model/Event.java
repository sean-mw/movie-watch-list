package model;

import java.util.Calendar;
import java.util.Date;

// Represents a watch list app event.
// This class was adapted from AlarmSystem: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
public class Event {
    private static final int HASH_CONSTANT = 13;
    private final Date dateLogged;
    private final String description;

    // EFFECTS: Creates an event with given description and current date/time
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    // EFFECTS: returns the date the event was logged at
    public Date getDate() {
        return this.dateLogged;
    }

    // EFFECTS: returns the event description
    public String getDescription() {
        return this.description;
    }

    @Override
    // EFFECTS: checks if this event is equal to other given event
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }

        Event otherEvent = (Event) other;

        return (this.dateLogged.equals(otherEvent.dateLogged) && this.description.equals(otherEvent.description));
    }

    @Override
    // EFFECTS: returns hashcode representation of event
    public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }

    @Override
    // EFFECTS: returns string representation of event
    public String toString() {
        return "[" + dateLogged.toString() + "] " + description;
    }
}
