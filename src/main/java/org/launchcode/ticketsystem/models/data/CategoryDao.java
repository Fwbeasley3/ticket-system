package org.launchcode.ticketsystem.models.data;

import org.launchcode.ticketsystem.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryDao extends CrudRepository<Category, Integer> {
}
