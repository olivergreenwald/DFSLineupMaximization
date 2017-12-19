//imports
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * File Creator: Oliver Greenwald
 * File Name: Output
 * File Purpose:
 *      The Output file is responsible for reading in the Defenses and Offensive_Players files, taking that data, creating a
 *      Player Class instance for each set of data, strategically sorting the Players, and then finally printing the results.
 * Date Created: 10/18/17
 * Collaborators: Michael Chiang
 */

public class Output {

    /**
     * Knapsack Problem
     * Purpose:
     *      The purpose of the Knapsack problem is to create the best Fantasy Football lineup given a list of players
     *      from the NFL. This algorithm takes in to consideration constraints (in this case salary, positions, and
     *      projected points) to create the best possible lineup of players.
     */

    //A method that returns the maximum of two integers (used to keep or discard players in the recursion process)
    static double max(double a, double b) { return (a > b)? a : b; }

    //Returns the maximum projected points so that can be put in a knapsack of capacity W
    static double knapSack(double W, ArrayList<Integer> salary, ArrayList<String> playerNames, ArrayList<Double> projectedPoints, Integer numPlayersToOutput, ArrayList<ArrayList> soFar)
    {
        //Base Case
        if (numPlayersToOutput == 0 || W == 0){return 0;}

        //If weight of the nth item is more than Knapsack capacity W, then
        //this item cannot be included in the optimal solution
        if (salary.get(numPlayersToOutput-1) > W)
            return knapSack(W, salary, playerNames, projectedPoints, numPlayersToOutput-1, soFar);

        //Return the maximum of two cases:
        //(1) nth item included
        //(2) not included
        else{

            //Creates a mini array that looks like this --> [Name, Value, Weight]
            ArrayList<Object> miniArray = new ArrayList();
            miniArray.add(playerNames.get(numPlayersToOutput-1));
            miniArray.add(projectedPoints.get(numPlayersToOutput-1));
            miniArray.add((1.0 * (salary.get(numPlayersToOutput-1))));

            //Adds an anonymous ArrayList to the "notAdded" ArrayList and fills it with data from the "soFar" Array
            ArrayList<ArrayList> notAdded = new ArrayList<>();
            for (int i = 0; i < soFar.size(); i++) {
                notAdded.add(new ArrayList());
                for (int j = 0; j < soFar.get(i).size(); j++) {
                    notAdded.get(i).add(soFar.get(i).get(j));
                }
            }

            //Adds the mini Array to the Array "soFar"
            soFar.add(miniArray);

            //Picks the winner (player who we will keep in the recursion) or loser (player who we will NOT keep in the
            // recursion) of the problem and then continues to recurse.
            return max(projectedPoints.get(numPlayersToOutput-1) + knapSack(W-salary.get(numPlayersToOutput-1), salary, playerNames, projectedPoints, numPlayersToOutput-1, soFar),
                knapSack(W, salary, playerNames, projectedPoints, numPlayersToOutput-1, notAdded));
        }
    }

    //Driver
    public static void main(String[] args) throws IOException{

        //Reads in the file "Offensive_Players"
        BufferedReader reader = new BufferedReader(new FileReader("/Users/olivergreenwald/FantasyFootball/src/Offensive_Players"));

        //Sets dad as null and 0 and constructs the allOffensivePlayers, projectedPoints, and playerSalary Arrays
        //to add player data to
        String line = null;
        Scanner scanner = null;
        int index = 0;
        double avRatio = 0.0;
        List<Player> allOffensivePlayers = new ArrayList<>();
        ArrayList<Double> projectedPoints = new ArrayList<Double>();
        ArrayList<Integer> playerSalary = new ArrayList<Integer>();
        ArrayList<String> playerName = new ArrayList<String>();

        //Takes data from the file "Offensive_Players", creates a Player and adds data to it, and doesn't stop until
        //there is not more data to read in
        while ((line = reader.readLine()) != null) {

            //Constructs an individual player, and gets rid of all the commas in the file "Offensive_Players" so it can
            //easily be parsed and put into a Player
            Player IndividualOffensivePlayer = new Player();
            scanner = new Scanner(line);
            scanner.useDelimiter(",");

            //Reads through each line of data, picks out the relevant pieces/statistics, and then sets those items to
            //the correct part of the Player class
            while (scanner.hasNext()) {
                    String data = scanner.next();

                    //If the index of the data line is "0", that means it is a position for a player
                    //Then whatever String is at index "0" is inserted into a Player
                    if (index == 0)
                        IndividualOffensivePlayer.setPosition(data);

                    //If the index of the data line is "2", that means it is a name for a player
                    //Then whatever String is at index "2" is inserted into a Player
                    else if (index == 2) {
                        IndividualOffensivePlayer.setName(data);
                        playerName.add(data);
                    }

                    //If the index of the data line is "3", that means it is an ID for a player
                    //Then whatever int is at index "3" is inserted into a Player
                    else if (index == 3)
                        IndividualOffensivePlayer.setID(Integer.parseInt(data));

                    //If the index of the data line is "4", that means it is a Salary for a player
                    //Then whatever int is at index "4" is inserted into a Player
                    else if (index == 4) {
                        IndividualOffensivePlayer.setSalary(Integer.parseInt(data));
                        playerSalary.add(Integer.parseInt(data));
                    }

                    //If the index of the data line is "6", that means it is the team of a player
                    //Then whatever String is at index "6" is inserted into a Player
                    else if (index == 6)
                        IndividualOffensivePlayer.setTeam(data);

                    //If the index of the data line is "9", that means it is the opposing team for a player (who they will play)
                    //Then whatever String is at index "9" is inserted into a Player
                    else if (index == 9)
                        IndividualOffensivePlayer.setOpponent(data);

                    //If the index of the data line is "11", that means it is the projected points for a player
                    //Then whatever double is at index "11" is inserted into a Player
                    else if (index == 11) {
                        IndividualOffensivePlayer.setProjectionDFN(Double.parseDouble(data));
                        projectedPoints.add(Double.parseDouble(data));
                    }

                    //Creates the Ratio for a player (Salary/Projected Points)
                    IndividualOffensivePlayer.setRatio();

                    //Moves to the next data line
                    index++;
            }
            index = 0;

            //Only adds people to the allOffensivePlayers array if their projected points is NOT 0
            //This deletes the injured, suspended, etc. players from the options
            if ((IndividualOffensivePlayer.getProjectionDFN()) != (0.0)){
                allOffensivePlayers.add(IndividualOffensivePlayer);
            }
        }

        //close reader
        reader.close();

        System.out.println("allOffensivePlayers size: " + allOffensivePlayers.size());

        //Adds all the Player's ratios to the avRatio variable, as well as the forMedian ArrayList
        List<Integer> forMedian = new ArrayList<>();
        for (int i = 0; i < allOffensivePlayers.size(); i++) {
            avRatio += allOffensivePlayers.get(i).getRatio();
            forMedian.add(new Integer((int)allOffensivePlayers.get(i).getRatio()));
        }
        avRatio = avRatio/(1.0 * allOffensivePlayers.size());
        List<Player> temp = new ArrayList<>();

        //Initializing player counters to 0 (these variables will keep track of how many positions are left after
        //we cut down players based on ratios
        int QBs = 0;
        int RBs = 0;
        int WRs = 0;
        int TEs = 0;

        //Collections.sort sorts the forMedian Array from least to greatest values
        Collections.sort(forMedian);
        System.out.println("forMedian size: " + forMedian.size());

        //Locates the median of the array so that we can cut players above that ratio
        double halfofArray = forMedian.size()/2;
        System.out.println("half of array: " + halfofArray);
        double median = forMedian.get((int) halfofArray);
        System.out.println("Median: " + median);

        //Cuts all players above the median and counts the positions that are present in the new allOffensivePlayers Array
        for (int i = 0; i < allOffensivePlayers.size(); i++) {
            if (allOffensivePlayers.get(i).getRatio() <= median){
                System.out.print("I am now adding " + allOffensivePlayers.get(i).getRatio());
                System.out.println(" with a position of " + allOffensivePlayers.get(i).getPosition());
                //If the position of the player being added is QB, add the value of 1 to the QB counter
                if (allOffensivePlayers.get(i).getPosition().equals("\"QB\"")){
                    QBs++;
                //If the position of the player being added is RB, add the value of 1 to the RB counter
                } else if (allOffensivePlayers.get(i).getPosition().equals("\"RB\"")){
                    RBs++;
                //If the position of the player being added is WR, add the value of 1 to the WR counter
                } else if (allOffensivePlayers.get(i).getPosition().equals("\"WR\"")){
                    WRs++;
                //If the position of the player being added is none of the above, add the value of 1 to the TE counter
                } else {
                    TEs++;
                }
                temp.add(allOffensivePlayers.get(i));
            }
        }

        System.out.println("ForMedian: " + forMedian);
        System.out.println("#QBs: " + QBs);
        System.out.println("#RBs: " + RBs);
        System.out.println("#WRs: " + WRs);
        System.out.println("#TEs: " + TEs);
        System.out.println("Total players left: " + (QBs+RBs+WRs+TEs));
        System.out.println();
        System.out.println("Temp: " + temp);
        System.out.println();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //BELOW IS MOSTLY THE SAME CODE AS ABOVE, EXCEPT FOR DEFENSES

        //The code below for defenses, however, is not "cut down" using ratios like code above for offensive players
        //so that whoever takes over the code, can decide if cutting them down is needed. A defense is cut down, however,
        //if they are projected to get 0 points (this most likely means that they are on a BYE week).

        //It does accomplish creating a Player for each defense and adding that player to the "allDefenses" Array
        BufferedReader reader2 = new BufferedReader(new FileReader(
                "/Users/olivergreenwald/FantasyFootball/src/Defenses"));
        String line2 = null;
        Scanner scanner2 = null;
        int index2 = 0;
        List<Player> allDefenses= new ArrayList<>();
        while ((line2 = reader2.readLine()) != null) {
            Player IndividualDefense = new Player();
            scanner2 = new Scanner(line2);
            scanner2.useDelimiter(",");
            while (scanner2.hasNext()) {
                String data2 = scanner2.next();
                if (index2 == 0)
                    IndividualDefense.setPosition(data2);
                else if (index2 == 2)
                    IndividualDefense.setName(data2);
                else if (index2 == 3)
                    IndividualDefense.setID(Integer.parseInt(data2));
                else if (index2 == 4) {
                    IndividualDefense.setSalary(Integer.parseInt(data2));
                    playerSalary.add(Integer.parseInt(data2));
                }
                else if (index2 == 7)
                    IndividualDefense.setTeam(data2);
                else if (index2 == 9)
                    IndividualDefense.setOpponent(data2);
                else if (index2 == 11) {
                    IndividualDefense.setProjectionDFN(Double.parseDouble(data2));
                    projectedPoints.add(Double.parseDouble(data2));
                }
                IndividualDefense.setRatio();
                index2++;
            }
            index2 = 0;
            if ((IndividualDefense.getProjectionDFN()) != (0)){
                allDefenses.add(IndividualDefense);
            }

        }
        reader.close();
        System.out.println(allDefenses);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //The commented out code below is from a KnapSack example problem. This code sets up variables
        // (like players used in my code above) and then maximizes the model to find the best solution
        // like my code does as well.

        //The code is what I started with (not related to Fantasy Football), and is here in case whoever takes over
        //this project would like to see the origin of the KnapSack problem created in this code.

//      Integer projectedPoints[] = new Integer[]{new Integer(60), new Integer(100), new Integer(120)};
//      Integer playerSalary[] = new Integer[]{new Integer(10), new Integer(20), new Integer(30)};
//      ArrayList<ArrayList> soFar = new ArrayList<ArrayList>();
//      int totalSalary = 40;
//      int n = projectedPoints.length;
//      System.out.println(knapSack(totalSalary, playerSalary, projectedPoints, n, soFar));
//      System.out.println(soFar);

        //Initializes the constraints for the model, runs the knapSack method, and then prints the output (maximized model)
        ArrayList<ArrayList> soFar = new ArrayList<ArrayList>();
        int totalSalary = 50000;
        int numPlayersToOutput = 9;
        knapSack(totalSalary, playerSalary, playerName, projectedPoints, numPlayersToOutput, soFar);
        System.out.println("____________________________________________________________________________________________");
        System.out.println();
        System.out.println("For Class Demo:");
        System.out.println();
        double pointsTotal = 0;
        for (int i = 0; i < soFar.size(); i++) {
            pointsTotal+=(double)soFar.get(i).get(1);
        }
        System.out.println("Total Projected Points this lineup creates: " + pointsTotal + " points");
        double salaryTotal = 0;
        for (int i = 0; i < soFar.size(); i++) {
            salaryTotal+=(double)soFar.get(i).get(2);
        }
        System.out.println("Total Salary this lineup uses: $" + salaryTotal);
        System.out.println();
        System.out.println("Below is the Array of Players that were the final output. Each mini Array below ›looks like this: [Name, Projected Points, Salary]");
        for (int i = 0; i < soFar.size(); i++) {
            System.out.println(soFar.get(i));
        }
    }
}