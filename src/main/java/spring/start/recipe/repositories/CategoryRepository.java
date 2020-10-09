package spring.start.recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.start.recipe.domain.Category;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}
