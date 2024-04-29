package ForestrySimulationProject;
import java.io.*;
import java.util.ArrayList;
import java.io.IOException;



public class Forest {

    private  static String forestName;
    private  static ArrayList<Tree> trees;


    public Forest(){
        forestName = null;
        trees = null;
    }
    public Forest(String forestName) {
        Forest.forestName = forestName;
        trees = new ArrayList<>();

    }

    public static String getName() {
        return forestName;
    }

    public static ArrayList<Tree> getTrees() {
        return trees;
    }

    public static void addTree(Tree tree) {
        trees.add(Tree.generateRandomTree());
    }


    public static void cutTree(int index) {
        if (index >= 0 && index < trees.size()) {
            trees.remove(index);
        } else {
            System.out.println("Invalid tree index.");
        }
    }

    public static void reapTrees(double heightRange) {
        ArrayList<Tree> newTrees = new ArrayList<>();
        for (Tree tree : trees) {
            if (tree.getHeight() > heightRange) {
                System.out.println("Reaping the tall tree " + tree);
                newTrees.add(Tree.generateRandomTree());
                System.out.println("Replacing with new tree: " +newTrees);
            } else {
                newTrees.add(tree);
            }
        }
        trees = newTrees;
    }

    public static double calculateAverageHeight() {
        double totalHeight = 0;
        for (Tree tree : trees) {
            totalHeight += tree.getHeight();
        }
        return totalHeight / trees.size();
    }
    public static Forest loadForest(String fileName) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName +  ".csv"))) {
            ArrayList<Tree> aTreeData = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                Tree.Species species = Tree.Species.valueOf(data[0].toUpperCase());
                int yearPlanted = Integer.parseInt(data[1]);
                double treeHeight = Double.parseDouble(data[2]);
                double growthRatePerYear = Double.parseDouble(data[3]);
                aTreeData.add(new Tree(species, yearPlanted, treeHeight, growthRatePerYear));
            }
            Forest newForest = new Forest();
            newForest.forestName = fileName;
            newForest.trees = aTreeData;
            return newForest;
        }catch (FileNotFoundException e){
            System.out.println("Error reading file" + fileName);
            return null;
        }
    }

    public static void printForest() {
        System.out.println("Forest name: " + getName());
        for (int i = 0; i < trees.size(); i++) {
            Tree tree = trees.get(i);
            System.out.printf("    %d %s   %d   %.2f'  %.1f%%\n", i, tree.getSpecies(), tree.getYearPlanted(),
                    tree.getHeight(), tree.getGrowthRate());
        }
        System.out.printf("There are %d trees, with an average height of %.2f\n", trees.size(), calculateAverageHeight());
    }




    public static void saveModifications() {
        String fileName = forestName + ".db";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Tree tree : trees) {
                writer.write(tree.getSpecies() + "," + tree.getYearPlanted() + "," +
                        tree.getHeight() + "," + tree.getGrowthRate());
                writer.newLine();
            }
            System.out.println("Forest saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving forest: " + e.getMessage());
        }
    }



}




