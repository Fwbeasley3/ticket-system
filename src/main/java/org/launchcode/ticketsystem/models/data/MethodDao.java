package org.launchcode.ticketsystem.models.data;

import org.launchcode.ticketsystem.models.Method;
import org.springframework.data.repository.CrudRepository;

public interface MethodDao extends CrudRepository<Method, Integer> {
}
