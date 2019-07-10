package org.launchcode.ticketsystem.models;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    private String site;




    // constructors---------------------------------------------

    public Ticket(){

    }
    public Ticket(String subject, String description,String requester) {
        this.subject = subject;
        this.description = description;
        this.requester = requester;


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

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
