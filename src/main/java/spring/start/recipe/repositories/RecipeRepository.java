package spring.start.recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.start.recipe.domain.Recipe;

import java.util.Optional;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {

    Recipe save(Recipe recipe);

}
