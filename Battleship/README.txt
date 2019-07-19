=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: sphill99
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2-D array.  This is how I make my game boards.  I have 4 2-D arrays of cells that can have different statuses. This
  is appropriate for my concept, because it is the easiest way to keep track of a game board.  The feedback said that this 
  should be good. 

  2. Recursion.  I use recursion in my AI and when I am randomly adding ships to the board.  In my AI, I use both 
  direct and indirect recursion.  I have a method for the computer that gets the best place to go.  It will call another method that
  calls itself while trying to find an open spot to attack. If it can't find one, it calls itself, then if it exhausts all places
  around the square without finding an empty one, it calls the best place to go method again with the index one higher.  I also
  randomly select places using recursion.  I randomly select a place on the board for the ship to go, and if it doesn't fit,
  then I call the method again. My feedback said they don't know how I would use it when making my AI.  I think it is the most effective
  way to create this AI. 

  3. Collections.  I use collections to store all the hits, so that I can use this to make my next attack.  
  I use a LinkedList, so I can add to the front of the list fast.  It is O(1) to add to the front of the list, 
  while that's not the case for array list. My list is never over 16, so it doesn't really affect the get method speed.  
  I use my list to find the best place to attack.  I try to attack around the last hit, and if the last two are connected, 
  I continue to attack in the direction. I also have a set of ships that I remove from every time it's hit. 
  When it is 0, the game is over. I was originally told my use of collections was the same as my 2-D array, but have since talked
  to my TA, and got this approved. 

  4. File io.  I save my highscores to a file and read them from the same file. I just save the lowest hits taken to win the game
  and the persons name. I did not originally have this in my proposal. 


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
Position.java - This is just a class that holds the X and Y coordinate of a cell. I also have methods in there
that can check if two positions are next to each other and what direction they are going, if this is the case. 
My linked list is of positions that were hits.  I use positions to determine where my AI will attack next. 

Cell.Java - My board is a 10x10 array of cells.  These cells all have a status (Hit, Miss, Empty), and whether or not 
there is a ship there.  There is a draw method in this class to print the cell.  If it is selected by the user, there 
is a slightly transparent yellow over the cell. Each cell has an X and Y, for where it is. 

GameBoard.java - This is a parent class that makes a 2-D array, that is used for my shipboard and pegboard.  They share
a draw method, and getBoard method. 

PegBoard.java - This is the child class of GameBoard. This class is where you attack, and put pegs.  There are no ships
on this board, but it corresponds to the opponents shipboard. I also make my AI in this class.  Most of the methods
are used to pick which is the best position to attack. There are also the selector methods in this class. 

ShipBoard.java - This class  is the child of GameBoard.  This is the board that has ships.  Basically, there are randomly
generated ships, and you can remove ships and reset the board. Two of the boards are these. 

Battleship.java - This is where a lot of the heavy lifting goes on.  I have my keyListener here, and depending on which mode 
it's on, it does different things.  I read and write from and to my file in this class.  I have my paint component, where
I draw everything.  It also stores 4 boards, a score, and other things. 

Main.java - I just draw different screens in this class.  I just thought it was more organized if it were just different methods
in a separate class from my paint component. 

Game.java - Where the game is run from.  This is also where I make the JPanel, and have an instance of battleship. 

KeyStroke.java, Direction.java, Status.java - These are all enums that I use to keep things more organized. 


- Were there any significant stumbling blocaks while you were implementing your
  game (related to your design, or otherwise)?

It took me a really long time to get my AI to work.  Every time I wanted to make it do something slightly more advanced, 
I needed to go back and change a lot of the other code.  Also, the recursion winded up being very complicated, and difficult 
to trace.  

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

My design has good separation of functionality.  Each class handles it's own thing, and I do a good job at 
making sure that no errors can be thrown.  The only issue I have here is my AI.  I put everything for the AI
in the PegBoard class, when it should probably be its own class, as it serves a different purpose.  I do a good
job at keeping everything that should be private, and using getters and setters for the variables that I need. 
I would re-make some methods that are overly complicated, if I had more time to refactor, especially my getBestMove method. 
If my code was more easily traceable, I would have been able to figure out when the AI didn't do what I wanted more often, 
and fix it. Also, my variable naming is pretty terrible throughout. 


========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.

  I got a picture from the cover of the XBox video game, Battleship. Microsoft - https://www.microsoft.com/en-us/store/p/battleship/bpx2dbjs60sq?activetab=pivot%3aoverviewtab
  I got a picture from the cover of Hasbro games board game, Battleship.  - https://images-na.ssl-images-amazon.com/images/I/911fxkKxfDL._SX522_.jpg