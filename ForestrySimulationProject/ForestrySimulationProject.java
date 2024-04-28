package ForestrySimulationProject;
import java.util.ArrayList;
import java.util.Scanner;



/**
 * Forestry Simulation Class designed to simulate the growth and pruning of forests
 * @author lauramerlin
 * @see ForestrySimulationProject
 */

public class ForestrySimulationProject {
    private static Forest forest;
    private ArrayList<Forest> forests = new ArrayList<>();
    private int currentForestIndex = -1;
    private static final Scanner keyboard = new Scanner(System.in);
    private static Object forestNames;




    public static void main(String[] args)  {
        String[] forestNames = {"Montane", "Acadian"};
        Forest[] forests = new Forest[forestNames.length];
        for (int i = 0; i < forestNames.length; i++) {
            forests[i] = Forest.readForestFromCSV(forestNames[i]);
            forest = forests[0];
        }

        System.out.println("Welcome to the Forestry Simulation\n -----------------------------------");
        menu();
    }


    private static void menu()  {
        String userInputMenu;

        do {
            System.out.print("(P)rint, (A)dd, (C)ut, (G)row, (R)eap, (S)ave, (L)oad, (N)ext, e(X)it: ");
            userInputMenu = keyboard.next().toUpperCase();

            switch (userInputMenu) {
                case "P":
                    forest.printForest();
                    break;
                case "A":

                    //forest.addTree(Tree trees);
                case "C":
                    int Tree = 0;
                    forest.cutTree(Tree);
                case "R":
                    double heightRange= 0 ;
                    forest.reapTrees(heightRange);
                    break;
                case "S":
                    forest.saveModifications();
                    break;
                case "L":
                    Object fileName = new Object();
                    Forest.loadForest((String) fileName);
                    break;
                case "N":
                    ArrayList<String> forestNames = new ArrayList<>();
                    Forest.nextForest(forestNames);
                case "X":
                    System.out.println("Exiting Forestry Simulation");
                    break;
                default:
                    System.out.println("ERROR: Invalid option, try again      :");
            }
        } while (!(userInputMenu.equals("X")));

    }// END MENU METHOD






}// END OF FORESTRY SIMULATION PROJECT CLASS
