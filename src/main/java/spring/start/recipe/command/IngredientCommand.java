package spring.start.recipe.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.start.recipe.domain.Recipe;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {

    private Long id;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand unitOfMeasure;
}
