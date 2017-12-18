# DFS Lineup Maximization

## Terms

1. NFL = National Football League
2. DFS = Daily Fantasy Sports
3. 
4.

## Project Description

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
    This file uses a Gurobi example found online, but modified to use the Player class in hopes to maximize the data.

## How to Run the Project

1. #### IntelliJ IDEA CE

2. #### Gurobi

## Bugs

1. #### 9 Person Lineup

## Unimplemented Features

1. #### Positions

2. #### Defenses, Weather, etc.
