CAB302 Software Development
===========================

# Week 10 - Patterns and Refactoring

The exercises for this week are all about taking patterns and using them, or taking bad code and improving it. We will begin with patterns.

## Exercise - Understanding the Mystery

The Mystery Pattern example is provided in the practical materials in the package `patterns.mystery`.
We are going to explore the code.
Note the changes to the constructor to allow us to pass a string argument, which becomes the internal state of the object.

- Refactor: Use the facilities provided by your IDE to rename the class `Mystery` to `Singleton`.
But do this in stages, using the refactoring option on the right click menu.
Don't just do a global replace.
- Run the code as a Java application and explain what is significant about the trivial result observed.
- Remove the access modifier protected from the constructor. Run the program again. What happens?
Replace it with private. Run the program again, and see what happens. Why do we choose protected?


## Exercise - Refactoring with the Factory Pattern

A cafe owner has hired a programmer to build a system for their baristas to enter customer orders and maintain inventory. One of the classes the programmer has written as part of the larger system is `Coffee` (provided).

Note that "cost" refers to the ingredient cost on the cafe's behalf, and "price" refers to the price a customer pays for a particular coffee.

This class uses a lot of bad practice. Most notably, it relies on the usage of strings. For instance, when interfacing with `Coffee`, a long black has to be constructed like:

```Java
ArrayList<String> ingredients = new ArrayList<String>();
ingredients.add("espresso");
ingredients.add("espresso");
Coffee c = new Coffee(ingredients, "long black");
```

Although this works, if one of the strings in the construction were anything but exactly what they are, there would be an error, or worse, the price or cost could be calculated incorrectly. Using enums, we can restrict the parameters to a select few. `CoffeeEnums` has been provided to help you get started. Use your IDE's refactoring tool to rename `CoffeeEnums` to `CoffeeFactory`.

With these enums, we can now change the constructor signature to:

```java
public Coffee(ArrayList<Ingredient> ingredients, Type type)
```

Notice how we are now using `Type` and `Ingredient` (the enums in `CoffeeFactory`). This will break the class and cause compiler errors which you will need to fix.

You will also need to make some additional changes:

- Change `getPrice()` to return the price based on the stored `Type`.
- Change `getCost()` to return the cost based on the stored `Ingredient` collection.
  - You can use the getter method available to you in the enum to simplify the summation.
  - Calculating the cost should be extracted from the constructor to the method `getCost()`.
- Because the ingredients are not being directly stored as strings, you will need to change `listIngredient()` while keeping the output in a new-line separated structure.

We have now refactored `Coffee` to use enums, which has made the construction and implementation of the class more logical. Previously, we had to instantiate a `Coffee` object like so:

```java
ArrayList<String> ingredients = new ArrayList<String>();
ingredients.add("espresso");
ingredients.add("milk");
Coffee flatWhite = new Coffee(ingredients, "flat white");
```

Now we can do it like so:

```java
ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
ingredients.add(Ingredient.ESPRESSO);
ingredients.add(Ingredient.MILK);
Coffee flatWhite = new Coffee(ingredients, Type.FLAT_WHITE);
```

This is better because construction is now restricted to a limited number of items that can be easily extended. There is still some room for improvement. The menu is:

- Flat White: one unit of espresso and one unit of milk.
- Long Black: two units of espresso.
- Mocha: one unit of espresso, one unit of milk, one unit of chocolate.

Rather than specifying both the `Type` and `Ingredient` list upon construction, we can use the factory pattern so that we only have to specify the `Type`, and infer the ingredients from the menu.

Create a new method in the `CoffeeFactory` class called `CreateCoffee`. `CreateCoffee` should take a coffee `Type`, and return a `Coffee` object. Return `Coffee` objects based on the `Type` argument, inferring the `Ingredient` list from the `Type`. Afterwards, we should be able to easily create coffee objects like so:

```java
Coffee flatWhite = CoffeeFactory.CreateCoffee(Type.FLAT_WHITE);
```

## Exercise - Observer Pattern

Use the observer pattern to implement a system for monitoring the radiation of a reactor. You are provided with `Subject` and `Observer` which are equivalent to the provided `Subject` and `Observer` from the practical and are already complete.

Your task is to complete the implementation of four classes:

- `RadiationSensor`  which extends `Subject`. Because we do not have a real reactor to monitor, we will simulate radiation readings using a random number generator. `readRadiation()` should set a private radiation variable to a random double between 0 and 10.
- `RadiationMonitor` which implements `Observer`. Note that `now()` is already complete, but you will need to implement the rest of the class.
- `ControlRoom` which extends `RadiationMonitor`
- `ResearchCentre` which extends `RadiationMonitor` 

The `ReactorMonitoring` class is provided to help with testing. In its current state, the expected output (when all four classes are complete) is shown below.  If you code is correct, you can expect to see this output every single time, because a seed of `10` is specified when the `RadiationSensor` is constructed.

```
2019-04-09 09:13:45 :: moving average :: 7.3043 :: Centre for Nuclear Research
2019-04-09 09:13:49 :: moving average :: 4.9412 :: Centre for Nuclear Research
2019-04-09 09:13:53 :: moving average :: 3.4915 :: Centre for Nuclear Research
2019-04-09 09:13:57 :: moving average :: 3.2289 :: Centre for Nuclear Research
2019-04-09 09:14:01 :: WARNING :: 8.1881 :: Reactor A Control Room
2019-04-09 09:14:02 :: moving average :: 4.2207 :: Centre for Nuclear Research
2019-04-09 09:14:06 :: moving average :: 4.1350 :: Centre for Nuclear Research
2019-04-09 09:14:10 :: WARNING :: 8.5628 :: Reactor A Control Room
2019-04-09 09:14:10 :: moving average :: 4.7675 :: Centre for Nuclear Research
2019-04-09 09:14:14 :: moving average :: 5.0653 :: Centre for Nuclear Research
2019-04-09 09:14:18 :: moving average :: 4.8217 :: Centre for Nuclear Research
```

Note that observations need to be logged upon update, not upon report generation. And when reported, the radiation levels must be rounded to four decimal places.


## Exercise - Assignment Refactoring

Good programmers don't write code for computers, they write code for humans.

Take at least one class from your assignment, and greatly improve it. Among other things, you should:

- Rename
- Encapsulate conditionals in methods
- Extract methods from endless line after line code
- Get rid of magic numbers (Extract constant)

Make sure you re-run your tests after each refactoring to ensure that the behaviour of the code remains the same.

