package spring.start.recipe.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import spring.start.recipe.domain.*;
import spring.start.recipe.repositories.CategoryRepository;
import spring.start.recipe.repositories.RecipeRepository;
import spring.start.recipe.repositories.UnitOfMeasureRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
       // System.out.println("#########//////#########     "+recipeRepository.count());

        if(recipeRepository.count() == 0) {
        recipeRepository.saveAll(loadData());
        //System.out.println("#########$$$$$$$$$#########     "+recipeRepository.count());
       }
        log.debug("Loading Bootstrap Data");
    }

    private List<Recipe> loadData() {

        List<Recipe> recipes = new ArrayList<>(2);
        //get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!tableSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(!teaSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");

        if(!dashUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");

        if(!pintUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if(!cupsUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaspoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();
        //get Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> spanishCategoryOptional = categoryRepository.findByDescription("Spanish");

        if(!spanishCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();
        Category spanishCategory = spanishCategoryOptional.get();

        Recipe soupRecipe = new Recipe();
        soupRecipe.setDescription("Tomato Soup");
        soupRecipe.setPrepTime(20);
        soupRecipe.setCookTime(20);
        soupRecipe.setDifficulty(Difficulty.EASY);
        soupRecipe.setDirections("1 Sauté the onions and garlic: In a Dutch oven set over medium heat, add the butter. Once it starts to foam, add the onion, garlic, salt, and pepper. Stir until both have softened and are fragrant, about 7 minutes." +
                "\n" +
                "2 Make the roux: Add flour. Stir to make a paste. Continue stirring until the flour has taken on a light brown color. Stir and scrape with wooden spoon, 5-7 minutes. The onion paste should look golden with a little brown caramelization." +
                "\n" +
                "3 Add tomatoes and stock: Add crushed tomatoes, tomato sauce, chicken stock, and sugar. Stir to combine. Cover the pot and bring to a boil over medium high heat, then reduce heat to medium and let simmer uncovered for about 20 minutes.\n" +
                "4 Purée the soup with an immersion blender. Alternatively, fill a countertop blender less than halfway with the hot soup. Remove the center lid insert. This allows the hot steam to escape. Cover the hole with a folded kitchen towel and place your hand over the top. Blend. " +
                "Repeat as necessary until all of the soup has been puréed.\n" +
                "\n" +
                "Ladle into bowls, topped with freshly cracked pepper, slivered basil, and of course, grilled cheese sandwiches."+"\n"+
                "\n" +
                "Read more: https://www.simplyrecipes.com/recipes/tomato_soup/");

        Notes soupNotes = new Notes();
        soupNotes.setRecipeNotes("This soup uses flour to help thicken it a bit. If you are gluten-free, skip the flour, and instead mix together 2 tablespoons of cornstarch and 1 tablespoon of cold water."+
                "\n"+"Stir it into the soup when you bring it to a boil, and let it simmer." +
                "\n" +
                "\n" +
                "Read more: https://www.simplyrecipes.com/recipes/tomato_soup/");
        soupRecipe.setNotes(soupNotes);

        soupRecipe.addIngredient(new Ingredient("tablespoons butter", new BigDecimal(5), tableSpoonUom, soupRecipe));
        soupRecipe.addIngredient(new Ingredient("large onion, yellow or white, dicedt", new BigDecimal("1"), eachUom, soupRecipe));
        soupRecipe.addIngredient(new Ingredient("large cloves garlic, minced", new BigDecimal(4), eachUom, soupRecipe));
        soupRecipe.addIngredient(new Ingredient("teaspoons kosher salt", new BigDecimal(1.5), teaspoonUom, soupRecipe));
        soupRecipe.addIngredient(new Ingredient("freshly cracked pepper", new BigDecimal(1.5), teaspoonUom, soupRecipe));
        soupRecipe.addIngredient(new Ingredient("flour", new BigDecimal(3), tableSpoonUom, soupRecipe));
        soupRecipe.addIngredient(new Ingredient("can crushed or diced tomatoes", new BigDecimal(1), eachUom, soupRecipe));
        soupRecipe.addIngredient(new Ingredient("can tomato sauce", new BigDecimal("1"), eachUom, soupRecipe));
        soupRecipe.addIngredient(new Ingredient("low sodium chicken stock", new BigDecimal(4), cupsUom, soupRecipe));
        soupRecipe.addIngredient(new Ingredient("sugar", new BigDecimal("1"), tableSpoonUom, soupRecipe));

        soupRecipe.getCategories().add(americanCategory);
        soupRecipe.getCategories().add(spanishCategory);

        //americanCategory.getRecipes().add(soupRecipe);
        //spanishCategory.getRecipes().add(soupRecipe);
        //add to return list
        recipes.add(soupRecipe);
        //Yummy Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");

        tacosRecipe.setNotes(tacoNotes);


        tacosRecipe.addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tableSpoonUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), teaspoonUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), teaspoonUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1), teaspoonUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(".5"), teaspoonUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("Clove of Garlic, Choppedr", new BigDecimal(1), eachUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("finely grated orange zestr", new BigDecimal(1), tableSpoonUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tableSpoonUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("Olive Oil", new BigDecimal(2), tableSpoonUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("boneless chicken thighs", new BigDecimal(4), tableSpoonUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("small corn tortillasr", new BigDecimal(8), eachUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3), cupsUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("medium ripe avocados, slic", new BigDecimal(2), eachUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pintUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), eachUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), eachUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cupsUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(4), eachUom, tacosRecipe));

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);

        //mexicanCategory.getRecipes().add(tacosRecipe);
        //add to return list
        recipes.add(tacosRecipe);
        return  recipes;
    }
}
