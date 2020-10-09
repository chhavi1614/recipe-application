package spring.start.recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.start.recipe.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {
}
