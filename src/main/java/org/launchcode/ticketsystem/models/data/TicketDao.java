package org.launchcode.ticketsystem.models.data;


import org.launchcode.ticketsystem.models.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TicketDao extends CrudRepository <Ticket, Integer>{
}
