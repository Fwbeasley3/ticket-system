package org.launchcode.ticketsystem.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Status {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @OneToMany //each one priority will have many tickets, but each ticket can have only one priority
    @JoinColumn(name = "status_id")  //tells Hibernate to use the priority_id column of the ticket table to determine which ticket belong to a given priority
    private List<Ticket> tickets = new ArrayList<>();

    // constructors---------------------------------------------

    public Status() {};

    public Status(String name) { this.name = name; }


    // getters and setters ---------------------------------------

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }


}
