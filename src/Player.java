/**
 * File Creator: Oliver Greenwald
 * File name: Player
 * File Purpose:
 *      This specific Player class represents that data (position, name, iD, salary, etc.) of players from the NFL.
 *      This class is used in the file "Output" which creates players and sorts them strategically.
 * Date Created: 10/18/17
 * Collaborators: Michael Chiang
 */

public class Player {

    //Instance Data
    private String position;
    private String name;
    private int iD;
    private int salary;
    private String team;
    private String opponent;
    private double projectionDFN;
    private double ratio;

    //Constructor (Setting Default Values For Player Data)
    public Player(){
        position = null;
        name = null;
        iD = 0;
        salary = 0;
        team = null;
        opponent = null;
        projectionDFN = 0.0;
    }

    //Getters (Get Player Data)
    public String getPosition(){
        return position;
    }
    public String getName(){
        return name;
    }
    public int getSalary(){
        return salary;
    }
    public int getID(){
        return iD;
    }
    public String getTeam(){
        return team;
    }
    public String getOpponent(){
        return opponent;
    }
    public double getProjectionDFN(){
        return projectionDFN;
    }
    public double getRatio(){return ratio;}

    //Setters (Set Player Data)
    public void setPosition(String position){
        this.position = position;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setSalary(int salary){
        this.salary = salary;
    }
    public void setID(int iD){
        this.iD = iD;
    }
    public void setTeam(String team){
        this.team = team;
    }
    public void setOpponent(String opponent){
        this.opponent = opponent;
    }
    public void setProjectionDFN(double projectionDFN){
        this.projectionDFN = projectionDFN;
    }
    //The setRatio() method is used to create a "ratio" for a player based on their salary and projected points.
    //The best ratios are found from a player with a low salary and a high projected points
    //The players will be cut in half (based ont these ratios) and the bottom half will be cut.
    public void setRatio(){this.ratio = this.getSalary()/this.getProjectionDFN();}

    //toString (Prints Player Data In A Nice Way To Read)
    @Override
    public String toString(){
        return "\nposition="+getPosition()+ ", " +
                "name"+getName()+ ", " +
                "iD="+getID()+ ", " +
                "salary="+getSalary()+ ", " +
                "team="+getTeam()+ ", " +
                "opponent="+getOpponent()+ ", " +
                "projectionDFN="+getProjectionDFN()+ ", " +
                "Ratio="+getRatio();
    }

}
