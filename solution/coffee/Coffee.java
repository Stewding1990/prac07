package coffee;

import coffee.CoffeeFactory.Ingredient;
import coffee.CoffeeFactory.Type;

import java.util.ArrayList;

public class Coffee {
    ArrayList<Ingredient> ingredients;
    Type type;

    public Coffee(ArrayList<Ingredient> ingredients, Type type) {
        this.ingredients = ingredients;
        this.type = type;
    }

    public double getCost() {
        double sum = 0;
        for (Ingredient ingredient : ingredients) {
            sum += ingredient.getCost();
        }
        return sum;
    }

    public double getPrice() {
        return this.type.getPrice();
    }

    public String listIngredients() {
        String string = "";
        for (Ingredient ingredient : ingredients) {
            string += ingredient.toString();
            string += "\n";
        }
        return string;
    }

}