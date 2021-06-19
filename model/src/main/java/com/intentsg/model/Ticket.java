package com.intentsg.model;

import java.util.Objects;

public class Ticket {
    private String id;
    private int place;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return place == ticket.place && Objects.equals(id, ticket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, place);
    }
}
