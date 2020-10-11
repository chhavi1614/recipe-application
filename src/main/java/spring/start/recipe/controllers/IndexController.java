package spring.start.recipe.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.start.recipe.domain.Category;
import spring.start.recipe.domain.UnitOfMeasure;
import spring.start.recipe.repositories.CategoryRepository;
import spring.start.recipe.repositories.UnitOfMeasureRepository;
import spring.start.recipe.services.RecipeService;

import java.util.Optional;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){
        log.debug("Getting Index Page");
        model.addAttribute("recipes",recipeService.getRecipes());
        return "index";
    }
}
