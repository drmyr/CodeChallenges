package design;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import static java.util.Collections.unmodifiableMap;
import static java.util.Collections.unmodifiableSet;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Collectors.toUnmodifiableSet;

/**
 * https://leetcode.com/discuss/interview-question/object-oriented-design/1031034/Design-a-Coffee-Machine
 * https://letcodify.in/2021/02/14/design-a-coffee-machine/
 *
 *     It will be serving some beverages.
 *     Each beverage will be made using some ingredients.
 *     The quantity of ingredients used for each beverage can vary. Also, the same ingredient (ex: water) can be used for multiple beverages.
 *     Any beverage can be served only if all the ingredients are available in terms of quantity.
 *     There would be an indicator that would show which all ingredients are running low. We need some methods to refill them.
 */
public class CoffeeMachineImpl implements CoffeeMachine {

    private final Map<Ingredient, Double> currentIngredientQuantities;
    private final Set<BeverageRecipe> recipes;
    private final Set<BeverageRecipe> recipesThatCanCurrentlyBeMade;
    private final Set<Ingredient> runningLow;

    public CoffeeMachineImpl(final Set<BeverageRecipe> recipes) {
        this.currentIngredientQuantities = recipes.stream().flatMap(recipe -> recipe.getIngredients().stream()).collect(toMap(
            ingredient -> ingredient,
            ingredient -> 0d
        ));
        this.recipes = unmodifiableSet(recipes);
        this.recipesThatCanCurrentlyBeMade = new HashSet<>();
        this.runningLow = this.recipes.stream().flatMap(recipe -> recipe.getIngredients().stream()).collect(toSet());
    }

    /**
     * Returns a menu of options based on what can currently be made.
     * @return
     */
    @Override
    public BeverageMenu getBeverageMenu() {
        return new BeverageMenu() {
            @Override
            public Set<BeverageOption> getOptions() {
                return CoffeeMachineImpl.this.recipesThatCanCurrentlyBeMade.stream().map(recipe -> new BeverageOption() {
                    @Override
                    public BeverageRecipe getBeverageRecipe() {
                        return recipe;
                    }
                }).collect(toUnmodifiableSet());
            }
        };
    }

    /**
     * Makes a beverage from a given option
     * @param beverageOption
     * @return
     */
    @Override
    public Beverage make(final BeverageMenu.BeverageOption beverageOption) {
        final BeverageRecipe beverageRecipe = beverageOption.getBeverageRecipe();
        for(final Map.Entry<Ingredient, Double> entry : beverageRecipe.getIngredientsAndQuantity().entrySet()) {
            final double newQuantity = this.currentIngredientQuantities.get(entry.getKey()) - entry.getValue();
            if(newQuantity < getMinimumThresholdForIngredient(entry.getKey())) {
                this.runningLow.add(entry.getKey());
            }
            this.currentIngredientQuantities.put(entry.getKey(), newQuantity);
        }
        this.recipesThatCanCurrentlyBeMade.removeAll(getRecipeSubset(recipe -> !recipe.canMake(this.currentIngredientQuantities)));

        return new Beverage() {
            @Override
            public String getBeverageName() {
                return beverageRecipe.getBeverageName();
            }
        };
    }

    /**
     * Reports what ingredients are currently low
     * @return
     */
    @Override
    public Set<Ingredient> runningLow() {
        return unmodifiableSet(runningLow);
    }

    /**
     * Adds more ingredient to the Coffee Machine
     * @param ingredient
     * @param additionalQuantity
     */
    @Override
    public void replenish(final Ingredient ingredient, final double additionalQuantity) {
        final double newQuantity = this.currentIngredientQuantities.getOrDefault(ingredient, 0d) + additionalQuantity;
        if(newQuantity > getMinimumThresholdForIngredient(ingredient)) {
            this.runningLow.remove(ingredient);
        }
        this.currentIngredientQuantities.put(ingredient, newQuantity);
        this.recipesThatCanCurrentlyBeMade.addAll(getRecipeSubset(recipe -> recipe.canMake(this.currentIngredientQuantities)));
    }

    private Set<BeverageRecipe> getRecipeSubset(final Predicate<BeverageRecipe> filter) {
        return this.recipes.stream().filter(filter).collect(toSet());
    }

    private double getMinimumThresholdForIngredient(final Ingredient ingredient) {
        throw new UnsupportedOperationException("TBD");
    }
}

/**
 * Basic operations of a Coffee Machine
 */
interface CoffeeMachine {

    BeverageMenu getBeverageMenu();

    Beverage make(final BeverageMenu.BeverageOption beverageOption);

    Set<Ingredient> runningLow();

    void replenish(final Ingredient ingredient, final double additionalQuantity);

}

/**
 * Provides a menu of beverage options based on current availability
 */
interface BeverageMenu {
    Set<BeverageOption> getOptions();

    interface BeverageOption {
        BeverageRecipe getBeverageRecipe();
    }
}

/**
 * Identifies a beverage
 */
abstract class Beverage {

    abstract String getBeverageName();

}

/**
 * A recipe that informs how much of which ingredients are required to create a given Beverage
 */
class BeverageRecipe {

    private final Map<Ingredient, Double> ingredientsAndQuantity;
    private final Beverage beverage;

    public BeverageRecipe(final Beverage beverage, final Map<Ingredient, Double> ingredientsAndQuantity) {
        this.beverage = beverage;
        this.ingredientsAndQuantity = ingredientsAndQuantity;
    }

    public boolean canMake(final Map<Ingredient, Double> availableIngredients) {
        for(final Map.Entry<Ingredient, Double> ingredients : availableIngredients.entrySet()) {
            if(this.ingredientsAndQuantity.getOrDefault(ingredients.getKey(), 0d) > ingredients.getValue()) {
                return false;
            }
        }
        return true;
    }

    public String getBeverageName() {
        return this.beverage.getBeverageName();
    }

    public Set<Ingredient> getIngredients() {
        return this.ingredientsAndQuantity.keySet();
    }

    public Map<Ingredient, Double> getIngredientsAndQuantity() {
        return unmodifiableMap(this.ingredientsAndQuantity);
    }
}

/**
 * Identifies an ingredient
 */
abstract class Ingredient {

    abstract String getName();

}
