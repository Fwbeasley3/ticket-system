package org.launchcode.ticketsystem.models;



import org.launchcode.ticketsystem.models.Method;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

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

    @ManyToOne
    private Priority priority;      //when a Ticket object is stored, this column will contain the id of its priority object. The data for the priority object itself will go in the table for the Priority class.

    @ManyToOne
    private Status status;

    @ManyToOne
    private Method method;

    @ManyToOne
    private Category category;




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

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
