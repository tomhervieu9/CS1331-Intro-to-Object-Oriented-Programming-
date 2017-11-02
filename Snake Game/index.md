---
title: hw-snake
---

# Snake

[Download Zip](https://github.gatech.edu/cs1331-spring2016/hw-snake/archive/master.zip)

##Introduction

In this assignment, you will be building the GUI (graphical user interface) for a game called Snake! While you will need to know everything you have learned so far in this class, this homework focuses on JavaFX, anonymous inner classes, and lambda expressions.


##Problem Description

Snake is one of the oldest video games in history!

You control a snake, which is attempting to eat a bunch of apples. The more apples it eats, the longer the snake grows. If the snake hits the world boundaries or itself the game is over! The goal of the game is to maximize your score by eating as many apples as you can before dying.

If you want to play Snake, you can find a lot of Snake games in the Internet. Here's a good example: http://playsnake.org/

You will be given an incomplete Snake project. Most of the game logic has been done for you - your job is just to create a user interface and add in the appropriate game functions to make an awesome Snake game!

Note that the project is broken up into multiple packages. **Please DO NOT move the files around**. We urge you to read through each of the files provided to develop an understanding of what you need to do.

###model Package

The model package contains classes that represent concrete elements in the game.

- `GameElement.java` is an abstract class that any drawn element extends from. **You will be slightly modifying this file.**
- `Apple.java` and `SnakeSegment.java` are `GameElement`s that represent elements in the game. All these classes occupy 1 square in the game world. **You will not have to modify these files.**
- `Snake.java` is a class that holds a list of `SnakeSegment`s. The snake can move and change directions. **You will not have to modify this file.**

###engine Package

The engine package contains two classes, both which involve the game itself.

- `Difficulty.java` is an enum that represents the available difficulties in the game. **You will not have to modify this file.**
- `GameWorld.java` contains the game logic for the game. It is responsible for updating the game elements and checking whether the game is over. **You will not have to modify this class,** but there are several methods within it that you will need to use, and others that you should be familiar with.
    - `void update()` updates the game world by moving the snake and checking whether the snake has eaten an apple. The private method `eat()` checks if the snake has eaten an apple. If it has, the score is incremented, the snake grows, and the apple is placed on a different part of the map.
    - `boolean isGameOver()` returns true if the snake has died, false otherwise. The private method `isSnakeDead()` checks whether the snake has hit a wall or hit itself.
    - `void setDirection()` changes the direction the snake is facing.
    - `int getScore()` returns the current score in the game.
    - `int getDelayTime()` gets the amount of delay between game updates. This is used to determine how fast the game will be run. Easy difficulty will be slower than hard difficulty.

###snake Package

The snake package contains the driver of the class, and where the bulk of the JavaFX magic happens.

- `SnakeGame.java` creates the main JavaFX window and scenes, initializes a `GameWorld` object, and updates the game scene by running a timer that continuously updates the `GameWorld`. `SnakeGame` doesn't deal with with the actual game logic, it just starts and updates the game state, which is why the two classes are separate! There are several methods you should take note in this class. **You will need to modify this file.**
    - `void start(Stage stage)` is the "main method" of the class. The method runs by setting up all the scenes, putting them in a window, and showing the window. **You will need to edit this method.**
    - `void setupStartScene()` creates the start screen. **You will need to edit this method.**
    - `void setupGameScene()` sets up the game screen. **You will need to edit this method.**
    - `void setupScoreScene()` creates the score screen. **You will need to edit this method.**
    - `void play()` runs the game on a timer so that it will periodically update the game. The timer will stop when the `GameWorld` object determines it is game over. **You will need to edit this method.**


##Solution Description

You will modify several existing files. You will not have to create your own files. However, we **HIGHLY** recommend that you read through the provided code to understand what functions have been given to you already.

### Compiling and Running
- Since this project is in multiple packages, you will have to compile and run the program a little differently.  **Your program must compile using the following process. If your program does not compile by using the following process, you will receive a 0.**
- Use this line to compile:
```bash
javac -cp src/main/java src/main/java/snake/*.java
```
- Use this line to run the program:
```bash
java -cp src/main/java snake.SnakeGame
```

- We have also provided two new `gradle` commands for this homework only.
    - `gradle -q compile` will compile your source code (and place the class files in `build/classes`).
    - `gradle -q run` will run your program after you compile (It will actually compile for you if you haven't already).
    - **Please note!** This method of compiling and running is entirely separate from the one above and is included only for your convience. **You code must still compile and run using the first method in order to recieve credit!**


### Edit GameElement.java
- Complete the existing constructor. The constructor should set the instance variable, `graphic`, to a new [Rectangle](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/shape/Rectangle.html) object at the provided x, y coordinates. The `Rectangle` should be the same [Color](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/paint/Color.html) as the one passed into the constructor, and be of width and height `GameElement.SIDE_LENGTH` using [this constructor for  Rectangle](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/shape/Rectangle.html#Rectangle-double-double-javafx.scene.paint.Paint-) (Note that Color is a subclass of [Paint](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/paint/Paint.html)).
- Complete the `addToScene` method. This method should add the graphic `Rectangle` to the given [Scene](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html) (hint: the [Parent](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Parent.html) class's version of `getChildren()` is private, but the [Group](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Group.html) class, which extends `Parent`, has a version that is public).

### Edit SnakeGame.java

#### Implement setupStartScene
-
- Create a start [Button](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Button.html) object (with text "Start"), that when clicked will call setupGameScene, set the instance variable window's scene to `gameScene`, and call `play()`. You can create functionality for when the button is pressed with the [setOnAction](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ButtonBase.html#setOnAction-javafx.event.EventHandler-) instance method. **You must implement your button's event using an anonymous inner class.**

- Add the title `Label`, `HBox` (containing the `RadioButtons`, and start `Button` to a [VBox](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/VBox.html) object so that they are stacked nicely, one on top of the other.
- Make sure that the `VBox` and the elements it contains are centered vertically on the screen (the [setAlignment](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/VBox.html#setAlignment-javafx.geometry.Pos-) method and the [Pos](https://docs.oracle.com/javase/8/javafx/api/javafx/geometry/Pos.html) class will be useful for this).
- Add the `VBox` to a new [StackPane](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/StackPane.html) object.
- Set `startScene` equal to a new `Scene` object with its `Parent` as the new `StackPane` you just added your `VBox` to, and with width `SnakeGame.SCREEN_WIDTH` and height `SnakeGame.SCREEN_WIDTH` using [this constructor for Scene](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html#Scene-javafx.scene.Parent-double-double-).

#### Implement start
- Modify `start` to call `setupStartScene`.
- Set the `Scene` of `window` to `startScene` after it has been setup using [this method](https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html#setScene-javafx.scene.Scene-).
- Make `window` nonresizable using [this method](https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html#setResizable-boolean-).
- call the [show](https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html#show--) method on `window` to make the `window` visible.
- Compile and run your code to make sure that your start scene looks the way you want it to. Pressing the start button right now will not properly start the game; you will have to implement `setupGameScene` for it to work.

#### Implement setupGameScene
- Begin by creating a new `Group` object. This will act as the `Parent` for `gameScene`.
- Set `gameScene` equal to a new `Scene` with the `Group` you just made as the root, with dimensions `SnakeGame.SCREEN_WIDTH` by `SnakeGame.SCREEN_WIDTH`.
- Create a new `Rectangle` object the same dimensions of the scene at (x = 0, y = 0). This will act as the background for the scene. Set the fill for this rectangle to whatever color you want except for green and red, so that you can see the game elements.
- Add the background `Rectangle` to the `gameScene` using `getChildren().add(...)`.
- Add an event for `gameScene` for when a key is pressed. You can use the instance method [setOnKeyPressed()](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html#setOnKeyPressed-javafx.event.EventHandler-) for this. The event should check which key was pressed and change the direction of the snake accordingly. W or the up arrow key should change the snake's direction to up, A or the left arrow key should make the snake go left, S or the down key should make the snake go down, and D or the right key should make the snake go right. **You must implement this event with a lambda**.
- Set `world` equal to a new `GameWorld`. The first two parameters are the background `Rectangle`, and the `gameScene`. The third is the difficulty of the game. You can get the difficulty of the game by getting the `userData` of the selected toggle from the `gameMode` `ToggleGroup` using the method [getSelectedToggle()](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ToggleGroup.html#getSelectedToggle--) and the method [getUserData() from the Toggle class](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Toggle.html#getUserData--).
- Compile and run your project to verify that the game is playable and that it works with all three difficulty settings.

#### Implement setupScoreScene
- Start off by making a `Label` object to display the score of the player from the `world` instance variable. Like the title `Label` in the start `Scene`, *make sure you change the font so that it's larger than the default size.*
- Next, you need to make an actual scoreboard. The scoreboard data will be stored in the resources folder as `highScores.csv`. The scoreboard in game should show the top ten highest scoring players' usernames and scores. This should consist of two [ListView](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ListView.html) objects, one for the username of the player, and the other for their score. These `ListView` objects should be placed inside of an `HBox` so that they appear beside one another.
- The first `ListView` should be a `ListView` of [Node](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html) objects. This will allow you to add both `Label`s (for existing users) and [TextField](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TextField.html)s. The player should be able to edit their username on the scoreboard should they make it into the top 10 (the `Node` for their username should be a `TextField` instead of a `Label`).
- The second `ListView` should store `Integer` objects representing the scores of each player.
- You should read through the file (remember to read through the file data only once, as in hw-histogram!) and first add the usernames and scores of all players who scored higher than the current player, then if there is still space in the top ten, add the current player (remember to use a textfield instead of a `Label` for their username), then add players who scored less until you either have ten players on the scoreboard, or you reach the end of `highScores.csv`.
- The [getItems()](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ListView.html#getItems--) instance method for `ListView` will be useful for adding elements.
- After you have created your scoreboard, you should create a save button. This save button should write the data from the scoreboard into `highScores.csv` in the same format that you accessed them. Only the top 10 scores should be saved in this file. Once again, the `getItems()` method will be useful for iterating through your `ListView` objects. Also, both `Label` and `TextField` have `getText()` methods (although they inherit these from different classes, and `Node` has no such method). The button should also set the scene to `startScene` once it is done writing to `highScores.csv`. **You may write the event for the button using your choice of an anonymous inner class or lambda.**
- Note: you can assume that the file will be there when we test your code, but you still need to handle the `FileNotFoundException`s by way of a try-catch block. In your catch block, you should just print the message of the `Exception`. Also, in the event that the file is not found, the `ListView`s should still be displayed and added to the scene, but they should just be empty.
- As for `setupStartScene`, you should then place the score `Label` and the scoreboard `HBox` into a `VBox` so that they get stacked nicely. You should then add this `VBox` into a `StackPane` that will be the `Parent` for `scoreScene`. As always, make sure that you set the width and height of scoreScene to `SnakeGame.SCREEN_WIDTH`.

#### Implement play
- When the game is over: the game should call the method to set up the score scene, the window should set the score scene, and the timer should stop itself.
- Hint: to check if the game is over, check the instance of `GameWorld` if the game is over.
- Hint: to stop the timer, you can call `stop()` within the handle method.

### General Tips

Here are some general tips for this assignment!

- Read through the provided code and its Javadocs. A lot of the assignment is understanding what you have been given and what you should implement. Don't reinvent the wheel!
- The game logic has been provided for you already. You should be using the methods that has already been written for you.
- Make sure that you read the tutorials on the syllabus so that you have an understanding of the structure of a JavaFX project.
- This project involves working with a large number of classes, so you will need to be familiar with the Oracle Java documentation. If you need to lookup the Button class for example, searching for "java 8 javafx Button" should get you the appropriate page in the Oracle documentation.
- You do not need to create any new methods to complete the homework - everything you are required to do only requires filling out the method bodies. However, if you feel that you need private helper methods, feel free to do so.
- **Do not import anything from java.awt or javax.swing!**
- You *may not* use `break` or `continue`. If you do, you *will* lose points.
- A video demonstrating what this assignment should look like can be found [here](https://www.youtube.com/watch?v=ToAHxYyXp20).

<hr>

## Submission

You will need to have [gradle version 2.10+](http://gradle.org/gradle-download/) installed to submit your homework. Once you have it installed, submit your assignment by running from the root of your homework directory:

```bash
gradle -q submit
```

Remember to check that your files were submitted successfully! They will be located in a repository on your github.gatech.edu account with the name hw-snake after you have submitted them. You can submit as many times as you want, we will take your last submission prior to the time the assignment is due. Also note that java source files will appear inside `src/main` on GitHub - this is normal, just click on the folder name to be taken to your submitted java source files.


## Grading

###Files to Submit

This assignment will require that all files in your `src/main/java` and its sub directories are submitted. *If you have extra files that you don't use, but are submitted and do not compile, you will recieve a zero!*

### Checkstyle
* Run checkstyle from the root directory of your homework using the following command:

```bash
gradle -q checkstyle
```

* You will be graded based on the results of this command, not any separate jar!
* If you encounter trouble running checkstyle, check Piazza for a solution and/or ask a TA as soon as you can!
* There is no checkstyle cap in this homework. Therefore, **you will receive a 0 on the assignment if you receive 100+ checkstyle errors!**


### Remember!

**Submissions that do not compile will receive a zero!**

This means the entire submission. Make sure every Java file that is submitted compiles successfully!
