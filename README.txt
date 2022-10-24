=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: 63639211
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1.Inheritance and subtyping
  The enemy characters are going to implement the GameObj class that gives a constructor with methods like move, show,
  hitbox, etc that belongs to every food/enemy character. This abstract class is also added with attack and px, py Now,
  which gives the most recent position after move(); I have also added a separate abstract class called SpecialObj
  this gives special moves to certain types of food/enemies. Donut and Pie have the ability to dodge bullets for
  certain amounts, and Marshmallow and Cake have the ability to speed up at a randomized time once they reach 1/4 of
  the entire width of the panel. SpecialObj is extended by GameObj and the abstract class is implemented through every
  enemy, which the ones that have the ability overrides the abstract method.

  (Feedback:
This sounds good, as long as your enemy characters are altering game state in sufficiently different ways. ; Good to go)

  2. (Changed) 2D Arrays
  My 2D Array involves the hit positions of each enemy stored with a type boolean. This is to store the positions that
  each object that has been hit to present it at the time when game is over. Each block represents ten pixels of both x
  and y positions of where the position has been hit, and with every true value to a cell, a red square is drawn.

  3. Collections
  ArrayLists will be to spawn bullets and enemies without a set limit.
  The bullet ArrayList will be called when the button is clicked
  and enemies ArrayList will be called after a certain time period.
  As the wave increases, there will be an increase in the call for enemies
  ArrayLists to spawn enemies. When the bullet collides with the enemy, reaches
  the right end, and when the enemy reaches the opposite end, hits the bullet,
  or hits the player, the indicies are stored temporarily for them to be removed for
  their ArrayLists respectively every time tick runs.

  (No Feedback; Good to go)

  4.



=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
    (Cake, Donut, MandM, Marshmallow, Pie) are the five classes with different sizes, speed, attack, and spawn rate.
    This is added with special moves for the foods. They are extended by GameObj, which is an abstract class that
    contains most of the private variables and methods. Bullet is also extended by GameObj and stored in a separate
    ArrayList. Its features are similar to the food/enemy classes but moves the opposite direction to hit the enemies
    and remove them. GameObj is extended by SpecialObj, which is also an abstract
    class that contains special methods for different food classes to inherit or override. Square is the user class
    that also extends GameObj with the same features to the food classes but without the remove and can be controlled
    by the user with keypresses and shoot eagle bullets. StartingImage and EndingSquares are also extended by GameObj
    for size and drawing. StartingImage presents the instructions for the game and EndingSquares provides a grid of
    every position the enemy has been hit after the game has ended. JUnitTest is to test certain methods for the game.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  I had a hard time thinking about the logic of the game, since I built the game myself. I redid the game three times
  because of it. Having to spawn different enemies in different times was also difficult to implement as well as
  when and how to make sure when an object gets hit and removed. Also finding restrictions with high-level classes,
  and hierarchies was sometimes difficult to follow because codes would sometimes be encapsulated. The hardest part was
  finding where to implement the code because certain parts ran the code, other parts painted, etc, and I had to go
  through the whole logic of how the code flowed to understand, especially because I was building my own game.


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  There are certain private states well encapsulated like the levels, scores, and highscores should not be externally
  manipulated or even the positions of each GameObj objects. I think encapsulating boolean playing was one of the most
  important because otherwise the program could shutdown externally. I would not refractor anything so far.


========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.
  https://iconarchive.com/show/noto-emoji-food-drink-icons-by-google/32421-birthday-cake-icon.html
  http://www.clipartsuggest.com/red-m-ms-lGbox1-clipart/
  https://www.kindpng.com/imgv/iwThmww_murica-png-4-png-image-america-country/
  https://www.vectorstock.com/royalty-free-vector/top-view-red-apple-pie-icon-cartoon-style-vector-34789186
  https://www.cleanpng.com/png-donut-png-10156/
  https://www.flaticon.com/free-icon/marshmallow_119515
  https://www.pngitem.com/so/eagle/
