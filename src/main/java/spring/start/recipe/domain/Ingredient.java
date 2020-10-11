package spring.start.recipe.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Recipe recipe;
    private String description;
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    public Ingredient( String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        this.recipe = recipe;
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

    public Ingredient() {

    }
}
