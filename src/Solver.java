//imports
import gurobi.*;

/**
 * File Creator: Oliver Greenwald
 * File Name: Solver
 * File Purpose:
 *      This file is EXPERIMENTAL and does not have any effect on the Output class (which runs the code for this project).
 *      This file uses a Gurobi example found online, but modified to use the Player class in hopes to maximize the data.
 * Date Created: 10/18/17
 * Collaborators: Michael Chiang
 */

public class Solver extends Output{
    public static void main(String[] args) {

        Player PlayerQB = new Player();
        Player PlayerRB1 = new Player();
        Player PlayerRB2 = new Player();
        Player PlayerWR1 = new Player();
        Player PlayerWR2 = new Player();
        Player PlayerWR3 = new Player();
        Player PlayerFLEX = new Player();
        Player PlayerTE = new Player();
        Player PlayerD = new Player();
        Player p = new Player();

        try {
            GRBEnv env = new GRBEnv("mip1.log");
            GRBModel model = new GRBModel(env);
            GRBVar QBOptions = model.addVar(0.0, p.getSalary(), 0.0, GRB.BINARY, "" + p.getPosition());

            // Create variables
            GRBVar QB = model.addVar(0.0, PlayerQB.getSalary(), 0.0, GRB.BINARY, "QB");
            GRBVar RB1 = model.addVar(0.0, PlayerRB1.getSalary(), 0.0, GRB.BINARY, "RB");
            GRBVar RB2 = model.addVar(0.0, PlayerRB2.getSalary(), 0.0, GRB.BINARY, "RB");
            GRBVar WR1 = model.addVar(0.0, PlayerWR1.getSalary(), 0.0, GRB.BINARY, "WR");
            GRBVar WR2 = model.addVar(0.0, PlayerWR2.getSalary(), 0.0, GRB.BINARY, "WR");
            GRBVar WR3 = model.addVar(0.0, PlayerWR3.getSalary(), 0.0, GRB.BINARY, "WR");
            GRBVar FLEX = model.addVar(0.0, PlayerFLEX.getSalary(), 0.0, GRB.BINARY, "FLEX");
            GRBVar TE = model.addVar(0.0, PlayerTE.getSalary(), 0.0, GRB.BINARY, "TE");
            GRBVar D = model.addVar(0.0, PlayerD.getSalary(), 0.0, GRB.BINARY, "D");

            // Set objective: maximize x + y + 2 z
            GRBLinExpr expr = new GRBLinExpr();
            expr.addTerm(1.0, QB); expr.addTerm(1.0, RB1); expr.addTerm(1.0, RB2); expr.addTerm(1.0, WR1); expr.addTerm(1.0, WR2); expr.addTerm(1.0, WR3); expr.addTerm(1.0, TE); expr.addTerm(1.0, FLEX); expr.addTerm(1.0, D);
            model.setObjective(expr, GRB.MAXIMIZE);

            // Add constraint: x + 2 y + 3 z <= 4
            expr = new GRBLinExpr();
            expr.addTerm(1.0, QB); expr.addTerm(1.0, RB1); expr.addTerm(1.0, RB2); expr.addTerm(1.0, WR1); expr.addTerm(1.0, WR2); expr.addTerm(1.0, WR3); expr.addTerm(1.0, FLEX); expr.addTerm(1.0, TE); expr.addTerm(1.0, D);
            model.addConstr(expr, GRB.LESS_EQUAL, 50000.0, "salary");

            // Optimize model
            model.optimize();
            System.out.println(QB.get(GRB.StringAttr.VarName)
                    + " " +QB.get(GRB.DoubleAttr.X));
            System.out.println(WR1.get(GRB.StringAttr.VarName)
                    + " " +WR1.get(GRB.DoubleAttr.X));
            System.out.println(WR2.get(GRB.StringAttr.VarName)
                    + " " +WR2.get(GRB.DoubleAttr.X));
            System.out.println(WR3.get(GRB.StringAttr.VarName)
                    + " " +WR3.get(GRB.DoubleAttr.X));
            System.out.println(RB1.get(GRB.StringAttr.VarName)
                    + " " +RB1.get(GRB.DoubleAttr.X));
            System.out.println(RB2.get(GRB.StringAttr.VarName)
                    + " " +RB2.get(GRB.DoubleAttr.X));
            System.out.println(TE.get(GRB.StringAttr.VarName)
                    + " " +TE.get(GRB.DoubleAttr.X));
            System.out.println(FLEX.get(GRB.StringAttr.VarName)
                    + " " +FLEX.get(GRB.DoubleAttr.X));
            System.out.println(D.get(GRB.StringAttr.VarName)
                    + " " +D.get(GRB.DoubleAttr.X));
            System.out.println("Obj: " + model.get(GRB.DoubleAttr.ObjVal));

            // Dispose of model and environment
            model.dispose();
            env.dispose();

        } catch (GRBException e) {
            System.out.println("Error code: " + e.getErrorCode() + ". " +
                    e.getMessage());
        }
    }
}