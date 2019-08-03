package org.launchcode.ticketsystem.models;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Ticket {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 5, max = 60)
    private String subject;         //ticket subject


    @NotNull
    private String description;//ticket description

    @NotNull
    private String requester;

    @NotNull
    private String location;

    private String resolution;

    private Date date = new Date();




    // constructors---------------------------------------------

    public Ticket(){

    }
    public Ticket(String subject, String description,String requester,String location) {
        this.subject = subject;
        this.description = description;
        this.requester = requester;
        this.location = location;



    }

    // getters and setters ---------------------------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String site) {
        this.location = site;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }
}
