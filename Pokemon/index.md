---
title: hw-pokemon
---

# Pokemon Battle

[Download Zip](https://github.gatech.edu/cs1331-spring2016/hw-pokemon/archive/master.zip)

##Introduction

In this assignment, you will be using inheritance and polymorphism to depict Pokemon and their interactions.


##Problem Description

You wanna be the very best (programmer), like no one ever was! To have great OOP design is your real test, to learn it is your cause!

Note: You don't need any prior knowledge about Pokemon to succeed with this assignment. The instructions will provide all necessary information to help you understand what needs to be done.

You will be given a GUI that represents the Pokemon world, separated into territories that help or hinder certain types of Pokemon. Within this world you will build classes that represent 5 different species of Pokemon. You will then form logic to make Pokemon species (instances) of different types interact with others.

##Solution Description

You will modify several existing files and will create a few new classes which will be similar to some of the ones given to you.

###PokeWorld

We are giving you a GUI that represents the Pokemon world. The three files that represent this are: `PokeBattle.java`, `PokeWorld.java`,
and `ControlPanel.java`. Also, we have included `Pokemon.java` with some basic methods filled out for you.

- `PokeBattle.java` is the driver for your program
- `PokeWorld.java` is the panel in which your Pokemon will be running around. Each Pokemon is represented by an image of its species, which will move around the PokeWorld
- `ControlPanel.java` is a panel with buttons that will control which Pokemon gets instantiated next onto the GUI
- `Pokemon.java` is the abstract parent class for all your Pokemon species. We are giving you the shell for this class with the a few methods already written for you.

Because we are giving you a GUI structure to work with, we will require you to name some methods and classes in a specific way. If you see the name of a class or method that you need to write mentioned in this document, you should name it as such.

*You will need to change `ControlPanel.java` (check the TODOs at lines 41, 42, 49, and 50) and `PokeWorld.java` (check the TODO at line 166) to accommodate your custom Pokemon. Other than these few TODOs, you _should not_ edit `ControlPanel.java`, `PokeWorld.java`, or `PokeBattle.java`.*

###Pokemon Hierarchy

The main purpose of this assignment is to implement the classification of Pokemon species within the Pokemon world. You will be making instances of these Pokemon that run around in your GUI and interact with other Pokemon. We will be giving you a little bit of guidance on specific methods to write, but a large part of this assignment will be you designing classes on your own!

####Pokemon class

The top level of your hierarchy should be an abstract class called `Pokemon`. We have provided you with a partially complete file for this class. This class has certain instance variables and methods that all `Pokemon` will have in common.

Here are the methods that the GUI requires from `Pokemon`. You must implement these methods for the GUI to work. You may add any helper methods and instance data you like, but they must be private (no extra getter or setter methods allowed though).

- `public void move()` will move the `Pokemon` instance in a random yet effective manner. All this means is that you have some sort of randomness in your movement and aren't simply hardcoding a circular path for the `Pokemon`, nothing super fancy is needed. To be specific we do not want something 100% predictable. Every time the instance moves, its `level` should increase by one and `health` should decrease by one. You must also make sure the instance stays within the bounds of the Pokemon world (hint: the x and y coordinate grid has 0,0 in the upper left corner of the screen and x and y *increase* as you move right and down respectively). Your `move` method should make use of the `Pokemon`'s speed variable (i.e. the higher the speed value is, the farther it moves per call to `move`).

- `public boolean collidesWithPokemon(Pokemon other)` returns whether or not the current instance of a `Pokemon` is colliding with another given instance of a `Pokemon`. This can be determined using the location and dimensions of the Pokemons' images (hint: `ImageIcon::getIconWidth()` and `ImageIcon::getIconHeight()` will be of use).

- `public abstract boolean canReproduceWithPokemon(Pokemon other)` returns whether or not the two Pokemon can reproduce. Two Pokemon can only reproduce if they are of the same Pokemon species.

- `public abstract Pokemon reproduceWithPokemon(Pokemon other)` if the two Pokemon are able to reproduce (as determined by `canReproduceWithPokemon()`) this method returns a new Pokemon of the same type and in the same location as this Pokemon (same `x` and `y`). If for some reason, re-production does not happen, null should be returned. Even if one Pokemon can reproduce with another, you should still limit the chance of reproduction somehow such that you don’t infinitely reproduce. Think about giving reproduction some random chance of occurring if the Pokemon are colliding with each other (hint: Pokemon.getRand() may be useful). Additionally each Pokemon can have *at most* two children at any one time (hint: you should check this in this method).

- `public abstract boolean isOld()` returns whether or not an instance of a Pokemon has surpassed some determined maximum level.

- `public abstract boolean canHarmPokemon(Pokemon other)` determines whether or not the current instance of a Pokemon can harm an instance of the other Pokemon through combat. This method should return `true` if it is decided that this Pokemon should harm the `other` Pokemon based on some random chance and a percentage (explained in the next section), and `false` otherwise.

- `public void harmPokemon(Pokemon other)` if the current Pokemon can harm the other Pokemon (as determined by `canHarmPokemon()`) then it decreases the other Pokemon’s health by some amount. How lethal Pokemon are is up to you! Experiment with different values to see different results in your simulation.

- `public void faint()` called when the instance faints for one reason or another; change the health of your instance to actually make faint (i.e. make it zero).

- `public boolean hasFainted()` returns whether or not the instance has fainted (i.e. health less than or equal to zero or if they have exceeded maximum level).


### Pokemon Types

The Pokemon world will be divided geometrically into four sections. The sections provide benefits to their corresponding types: the Fire section (orange), the Grass section (green), the Water section (blue), and one remaining section to be explained below (white).

Some notes about how Pokemon harm one another:

- Any Pokemon may harm any other Pokemon. As noted above, `canHarmPokemon` should return `true` or `false` based on a random chance with some probability of harming the `other` `Pokemon` (returning `true) or failing to harm the `other` `Pokemon` (returning `false`).
- You are to determine with what probability a Pokemon of some Type can harm another Pokemon of the same Type, but it must be nonzero.
- For example, a `FireType` Pokemon may have a 50% chance of harming another `FireType` Pokemon, a 30% chance of harming a `WaterType` Pokemon, and a 70% chance of harming a `GrassType` Pokemon. Again the actual percentages are up to you as long as they meet the guidelines below.

#### FireType

- When a `FireType` is in its orange quadrant (top-right), they move more quickly than they do in the other areas of the map.

- A `FireType` also has a greater chance of harming a `GrassType` and a lesser chance of harming a `WaterType`. The actual values that represent the probabilities are up to you!

#### WaterType

- When a `WaterType` is in its blue quadrant (bottom-left), they level up more rapidly than they do in the other areas of the map.

- A `WaterType` also has a greater chance of harming a `FireType` and a lesser chance of harming a `GrassType`. The actual values that represent the probabilities are up to you!

#### GrassType

- When a `GrassType` is in its green quadrant (top-left), they regain health.

- A `GrassType` also has a greater chance of harming a `WaterType` and a lesser chance of harming a `FireType`. The actual values that represent the probabilities are up to you!

#### CustomType

- **Note: The name of this class it up to you as long as it keeps with the `Type` convention of "SomethingType".**

- The harming relationship between your custom type and the other given types can be whatever you decide, but _make sure to include it when you are writing the classes for the other types!_

- When a custom type Pokemon is in its white quadrant (bottom-right), it gains any combination of the available abilities (speed boost, level up faster, and/or regain health). Just ensure that it receives at least one ability.

###Pokemon Species

Some notes about all Pokemon species:
- A Pokemon species can only reproduce with its own species.

#### Blaziken

The `FireType` Pokemon `Blaziken` is a powerful Pokemon that can vanquish its enemies.

- If a `Blaziken` is fighting a Fire type Pokemon and its level is higher than that of the Pokemon it is fighting, then it has a 90% chance of harming the other Fire type. Otherwise, it has a 12% chance. If not fighting a Fire type, Blaziken has the same chance of beating others as any other Fire type.
- `Blaziken` has the highest maximum level of any Pokemon other than `Poliwhirl`.
- `Blaziken` has the highest chance of reproduction.

#### Poliwhirl

The `WaterType` Pokemon `Poliwhirl` is titan of aquatic prowess.

- If a `Poliwhirl` is fighting another `Poliwhirl`, its chance of harming is 12% _less than_ its chance of harming any other Water type Pokemon. Otherwise, its chances of harming a Pokemon are equivalent to those of other Water type Pokemon.
- The `Poliwhirl` species can have a maximum level of 200.

#### Torterra

The `GrassType` Pokemon `Torterra` lets nothing get in its way, championing a tree on its back like a boss.

- Due to its powerful build, a `Torterra` is 10% higher chance of harming a Fire type Pokemon than another Grass type Pokemon would be. It can also harm Poliwhirls 70% of the time, but it only has normal Grass type hit rates against all other types and species.
- Torterra can reproduce with any Grass type Pokemon, but only 20% of the time.
- `Torterra` has the smallest maximum level of any Pokemon species

#### Custom Species

This Pokemon must be *of the `CustomType`* you choose to create. You can give it any additional abilities and rules, as long as it doesn't break any of the species rules provided above.

- If you are familiar with Pokemon, feel free to use a Pokemon you particularly like! If not, no worries; you can make up a Pokemon name you like and pick any old image.

- For this assignment, you will also need to add an image for your special species. Please put it in the `src/main/resources` folder with the other images we've provided. Just use [http://image.online-convert.com/convert-to-png](http://image.online-convert.com/convert-to-png) to convert it to png format, and specify an image size of 90 pixels x 90 pixels there as well!

#### One Last Custom Species

- This final species can be of any type, but it must have **no** additional abilities than what its type already is capable of.

- As with the special species, you will need to create a name and provide an image for this final species.

### General Tips

Here are some general tips for this assignment!

- Re-read this entire document. It’s long, it’s complicated, but oftentimes you will deal with complicated problem specs that may not make much sense the first time you read it. Take notes, draw pictures, and make sure you know exactly what’s going on before you start coding.
- Remember, a large part of this assignment is a good hierarchy and good OOP design, so really put a lot of time and effort into designing this in a smart way.
- We will be grading largely on functionality, although we will of course be looking at your code too. Make sure we can actually see the interactions we’ve described here between the Pokemon! If your reproduction rate is too high or too low, we might not be able to tell if your program is doing what it’s supposed to be.
- It’s ok if on a collision, both Pokemon harm one another, or both Pokemon reproduce with the same partner.
- You do not have to handle actually calling `reproduceWithPokemon`, `harmPokemon`, `faint`, etc. yourself, these will be called by the simulator.
- There are two unused images that would work perfectly for new custom Pokemon in the `src/main/resources` directory.

<hr>

## Submission

You will need to have [gradle version 2.10+](http://gradle.org/gradle-download/) installed to submit your homework. Once you have it installed, submit your assignment by running from the root of your homework directory:

```bash
gradle -q submit
```

Remember to check that your files were submitted successfully! They will be located in a repository on your github.gatech.edu account with the name hw-pokemon after you have submitted them. You can submit as many times as you want, we will take your last submission prior to the time the assignment is due. Also note that java source files will appear inside `src/main` on GitHub - this is normal, just click on the folder name to be taken to your submitted java source files.


## Grading

###Files to Submit

This assignment will require that all files in your `src/main/java` and `src/main/resources` directories are submitted. *If you have extra files that you don't use, but are submitted and do not compile, you will recieve a zero!*

### Checkstyle
* Run checkstyle from the root directory of your homework using the following command:

```bash
gradle -q checkstyle
```

* You will be graded based on the results of this command, not any separate jar!
* If you encounter trouble running checkstyle, check Piazza for a solution and/or ask a TA as soon as you can!
* Cap for this assignment:
    **50** Points


### Remember!

**Submissions that do not compile will receive a zero!**

This means the entire submission. Make sure every Java file that is submitted compiles successfully!
