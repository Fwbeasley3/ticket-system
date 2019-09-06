package org.launchcode.ticketsystem.models.data;

import org.launchcode.ticketsystem.models.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository  //tells Spring that this interface that this is a repository
@Transactional  //all methods in this interface should be wrapped by a database transaction = interaction with db
public interface StatusDao extends CrudRepository<Status, Integer> {
}