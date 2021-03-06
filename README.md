# DFS Lineup Maximization

## Terms

1. NFL = National Football League
2. DFS = Daily Fantasy Sports

## Project Description

An algorithm that takes in a list of NFL players with data (i.e. position, name, salary, projected points, etc.), and         creates a lineup that is projected to produce the most fantasy points in a week. The end result should be a 9 player lineup, that produces the most possible projected fantasy points in a week.

## Files

1. #### Defenses

    The Defenses file is a text file that contains a list of all the defenses in the NFL. This file includes data for each         defense such as Name, Projected Points, Salary, etc. The purpose of this file is to take data from each defense, and           use it in the code to maximize a DFS lineups. 

2. #### Offensive_Players

    The Offensive_Players file is a text file that contains a list of all the players in the NFL. This file includes data for     each player such as Name, Projected Points, Salary, etc. The purpose of this file is to take data from each player, and       use it in the code to maximize a DFS lineups.

3. #### Output.Java

    The Output file is responsible for reading in the Defenses and Offensive_Players files, taking that data, creating a           Player Class instance for each set of data, strategically sorting the Players, and then finally printing the results.

4. #### Player.Java

    This specific Player class represents the data (position, name, iD, salary, etc.) of players from the NFL.
    This class is used in the file "Output" which creates players and sorts them strategically.  

5. #### Solver.Java

    This file is EXPERIMENTAL and does not have any effect on the Output file (which runs the code for this project).
    This file uses a Gurobi example found online, but modified to use the Player class in hopes to maximize the data               efficiently.

## How to Run the Project

1. #### IntelliJ IDEA CE

    I used the [IntelliJ](https://www.google.com) application to write and run the java code.

2. #### Gurobi

   I used the [Gurobi](http://www.gurobi.com/) software to try and use their “Optimizer” to create the best lineup. This          Optimizer solves linear algebra problems and is very efficient. The code that I used can be found in the Solver file. I        found that experimental code in [this](http://www.gurobi.com/documentation/) part of the Gurobi website. 
   
   To install Gurobi, you can get a free academic license. Once you have received the license you will find a screen that will    help you install the license to your computer using Terminal. Then install Gurobi in your project as a Dependency and          import it at the top of your code just like this: `import gurobi.*;`

## Bugs

1. #### 9 Person Lineup

    The program doesn’t always return the amount of players that you request it to. If you read through and run the Output         file, you will see that I made the variable `numPlayersToOutput` as 9. This variable should make the code return 9             players, but it only returns 6. The Total Salary that the code returns is close to $50,000, so I know that it successfully     gets as close as possible to the salary constraint. But something specific in the code needs to be added that makes it         return 9 players, even if it can get a higher projected point value without 9. 
    
    *In a Fantasy Contest, you have to start 9 players no matter what.

## Unimplemented Features

The next recommended step is to add more constraints to the model. Right now, the only constraints are that there has to be 9 players, and their salaries can’t go above $50,000.

1. #### Positions

    One constraint that should be added is “positions”. The algorithm right now ignores NFL positions entirely and so, for         example, it could have you starting 9 quarterbacks, but a Fantasy Lineup only allows you to have one quarterback. 
     
    A lineup should include 1 quarterback, 2 running backs, 3 wide receivers, 1 tight end, 1 flex (running back, wide             receiver, or tight end), and one defense. 

2. #### Defenses, Weather, etc.

   You should consider adding other inputs/constraints to the model such as:
         1. The defense of the player's opposing team
         2. The weather during each game
         3. And much much more!
