package ForestrySimulationProject;
import java.io.IOException;
import java.util.Scanner;


/**
 * Forestry Simulation Class designed to simulate the growth and pruning of forests
 * @author lauramerlin
 * @see ForestrySimulationProject
 */

public class ForestrySimulationProject {

    static Forest[] forests;
    private static int currentForestIndex = -1;
    private static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("No forest names provided.");
            return;
        }
        forests = new Forest[args.length];
        for (int i = 0; i < args.length; i++) {
            forests[i] = Forest.loadForest(args[i]);
        }

        System.out.println("Welcome to the Forestry Simulation\n -----------------------------------");
        menu();
    }


    private static void menu() throws IOException {
        String userInputMenu;
        Forest forest;

        do {
            System.out.print("(P)rint, (A)dd, (C)ut, (G)row, (R)eap, (S)ave, (L)oad, (N)ext, e(X)it: ");
            userInputMenu = keyboard.next().toUpperCase();

            switch (userInputMenu) {
                case "P":
                        Forest.printForest();
                    break;
                case "A":
                    Forest.addTree(Tree.generateRandomTree());
                    break;
                case "C":
                    System.out.print("Tree number to cut down: ");
                    int treeIndex = keyboard.nextInt();
                    Forest.cutTree(treeIndex);
                    break;
                case "G":
                    for (Tree tree : Forest.getTrees()) {
                        tree.simulateGrowthRate();
                    }
                    break;
                case "R":
                    System.out.print("Height to reap from: ");
                    double heightRange = keyboard.nextDouble();
                    Forest.reapTrees(heightRange);
                    break;
                case "S":
                    Forest.saveModifications();
                    break;
                case "L":
                    System.out.print("Enter forest name: ");
                    String forestName = keyboard.next();
                    forest = Forest.loadForest(forestName);
                    break;
                case "N":
                    nextForest();
                    break;
                case "X":
                    System.out.println("Exiting Forestry Simulation");
                    break;
                default:
                    System.out.println("ERROR: Invalid option, try again:");
            }
        } while (!(userInputMenu.equals("X")));
    }
    public static void nextForest() {
        currentForestIndex++;
        if (currentForestIndex < forests.length) {
            Forest nextForest = forests[currentForestIndex];
            System.out.println("Moving to the next forest: " + nextForest.getName());
        } else {
            System.out.println("No more forests available.");
        }
    }


}// END OF FORESTRY SIMULATION PROJECT CLASS
