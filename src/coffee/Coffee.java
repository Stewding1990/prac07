package coffee;

import java.util.ArrayList;
import coffee.CoffeeEnums.Ingredient;
import coffee.CoffeeEnums.Type;

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
        if (this.type.equals("long black")) {
            return 4.0;
        } else if (this.type.equals("flat white")) {
            return 5.0;
        } else if (this.type.equals("mocha")) {
            return 6.0;
        }
        return 0;
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