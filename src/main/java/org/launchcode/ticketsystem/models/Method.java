package org.launchcode.ticketsystem.models;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Method {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @OneToMany //each one method will have many tickets, but each ticket can have only one method
    @JoinColumn (name = "method_id")  //tells Hibernate to use the method_id column of the ticket table to determine which ticket belong to a given method
    private List<Ticket> tickets = new ArrayList<>();

    // constructors---------------------------------------------

    public Method() {};

    public Method(String name) { this.name = name; }


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
