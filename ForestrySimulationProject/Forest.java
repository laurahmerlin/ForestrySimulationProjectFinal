package ForestrySimulationProject;
import java.io.*;
import java.util.ArrayList;

public class Forest {

    private final String forestName;
    private ArrayList<Tree> trees;

    public Forest(String forestName) {
        this.forestName = forestName;
        this.trees = new ArrayList<>();

    }

    public String getName() {
        return forestName;
    }

    public ArrayList<Tree> getTrees() {
        return trees;
    }

    public void addTree(Tree trees) {
        this.trees.add(trees);
    }

    public static Forest readForestFromCSV(String fileName) {
        Forest newForest = new Forest(fileName);

        try {
            BufferedReader inFile = new BufferedReader(new FileReader(fileName +".csv"));
            String currentLine = inFile.readLine();
            while (currentLine != null) {
                String[] aTreeData = currentLine.split(",");
                Tree.Species species = Tree.Species.valueOf(aTreeData[0].toUpperCase());
                int yearPlanted = Integer.parseInt(aTreeData[1]);
                double treeHeight = Double.parseDouble(aTreeData[2]);
                double growthRatePerYear = Double.parseDouble(aTreeData[3]);

                Tree newTree = new Tree(species, yearPlanted, treeHeight, growthRatePerYear);
                newForest.addTree(newTree);
                currentLine = inFile.readLine();

            }// end of while loop
        } catch (IOException e) {
            System.out.println("Error reading forest file: " + e.getMessage());

        }
        return newForest;
    }// end of read file from csv method

    public void cutTree(int index) {
        if (index >= 0 && index < trees.size()) {
            trees.remove(index);
        } else {
            System.out.println("Invalid tree index.");
        }
    }

    public void simulateYearlyGrowth() {
        for (Tree tree : trees) {
            tree.simulateGrowthRate();
        }
    }

    public void reapTrees(double heightRange) {
        ArrayList<Tree> newTrees = new ArrayList<>();
        for (Tree tree : trees) {
            if (tree.getHeight() > heightRange) {
                newTrees.add(tree.generateRandomTree());
            } else {
                newTrees.add(tree);
            }
        }
        trees = newTrees;
    }

    public double calculateAverageHeight() {
        double totalHeight = 0;
        for (Tree tree : trees) {
            totalHeight += tree.getHeight();
        }
        return totalHeight / trees.size();
    }

    public void printForest() {
        System.out.println("Forest name: " + forestName);
        for (int i = 0; i < trees.size(); i++) {
            System.out.println("   " + i + " " + trees.get(i));
        }
        System.out.println("There are " + trees.size() + " trees, with an average height of " + String.format("%.2f", calculateAverageHeight()));
    }

    public void saveModifications() {
        String fileName = forestName + ".csv";
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(this);
            System.out.println("Forest saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving forest: " + e.getMessage());
        }
    }
    public static Forest loadForest(String fileName) {
        Forest loadedForest = null;
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            loadedForest = (Forest) objectIn.readObject();
            System.out.println("Forest loaded from " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading forest: " + e.getMessage());
        }
        return loadedForest;
    }

    public static void nextForest(ArrayList<String> forestNames) {
        int currentForestIndex = 0;
        if (currentForestIndex < forestNames.size() - 1) {
            currentForestIndex++;
            String nextForestName = forestNames.get(currentForestIndex);
            Forest nextForest = loadForest(nextForestName);
            if (nextForest != null) {
                System.out.println("Moving to the next forest: " + nextForestName);
                // Perform any other operations needed for transitioning to the next forest
            } else {
                System.out.println("Failed to load the next forest.");
            }
        } else {
            System.out.println("No more forests available.");
        }
    }

}




