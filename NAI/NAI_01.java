package NAI_01;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class NAI_01 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to kNN Classifier, please enter a k parameter: ");
        int k = sc.nextInt();

        System.out.println("Enter the name of the training set file: ");
        String trainingSetFile = "src/NAI_01/" + sc.next();

        System.out.println("Enter the name of the test set file or just a single vector (starting with '['):  ");
        String temp = sc.next();
        if(temp.startsWith("[")) {
            ArrayList<Double> vector = new ArrayList<>();
            String temp2 = temp.substring(1, temp.length() - 1);
            String[] doubles = temp2.split(",");
            for(int i = 0; i < doubles.length; i++) {
                vector.add(Double.parseDouble(doubles[i]));
            }
            System.out.println(kNN_Classifier(k, trainingSetFile, vector));
        }
        else {
            temp = "src/NAI_01/" + temp;
            HashMap<String, ArrayList<ArrayList<Double>>> testSet = loadSet(temp);
            double correct = 0;
            double counter = 0;
            for(String key : testSet.keySet()) {
                ArrayList<ArrayList<Double>> vectors = testSet.get(key);
                for(int i = 0; i < vectors.size(); i++) {
                    counter++;
                    ArrayList<Double> vector = vectors.get(i);
                    String a = kNN_Classifier(k, trainingSetFile, vector);
                    if(a.equals(key)) correct++;
                }
            }
            double accuracy = correct / counter * 100;
            System.out.println("Accuracy of the provided set: " + Math.floor(accuracy) + "%");
        }

        //makeGraphData("src/NAI_01/wdbc.data", "src/NAI_01/wdbc.test.data");

    }

    public static void makeGraphData(String trainingSetFile, String testSetFile) throws IOException {
        FileWriter fw = new FileWriter("src/NAI_01/graph_data.txt");
        fw.write("k" + '\t' + "accuracy" + '\n');

        for(int k = 1; k <=380 ; k++){
            HashMap<String, ArrayList<ArrayList<Double>>> testSet = loadSet(testSetFile);
            double correct = 0;
            double counter = 0;
            for(String key : testSet.keySet()) {
                ArrayList<ArrayList<Double>> vectors = testSet.get(key);
                for(int i = 0; i < vectors.size(); i++) {
                    counter++;
                    ArrayList<Double> vector = vectors.get(i);
                    String a = kNN_Classifier(k, trainingSetFile, vector);
                    if(a.equals(key)) correct++;
                }
            }
            double accuracy = correct / counter * 100;
            fw.write(Integer.toString(k) + '\t' + Integer.toString((int)accuracy) + '\n');

        }
        fw.close();
    }

    public static String kNN_Classifier(int k, String trainFile, ArrayList<Double> vector) {
        String result = "";
        HashMap<String, ArrayList<ArrayList<Double>>> trainSet = loadSet(trainFile);
        HashMap<String, ArrayList<Double>> distances = new HashMap<>();
        ArrayList<Double> lowestDistances = new ArrayList<>();
        for (String key : trainSet.keySet()) {
            distances.put(key, calculateDistance(trainSet.get(key), vector));
            lowestDistances.addAll(calculateDistance(trainSet.get(key), vector));
        }
        Collections.sort(lowestDistances);
        lowestDistances.subList(k, lowestDistances.size()).clear();

        int maxCount = 0;
        for (String key : distances.keySet()) {
            ArrayList<Double> temp = distances.get(key);
            int counter = 0;
            for (int i = 0; i < temp.size(); i++) {
                if (lowestDistances.contains(temp.get(i))) {
                    counter++;
                }
            }
            if (counter > maxCount) {
                maxCount = counter;
                result = key;
            }
        }
        return result;
    }

    public static ArrayList<Double> calculateDistance(ArrayList<ArrayList<Double>> testVectors, ArrayList<Double> vector) {
        ArrayList<Double> distances = new ArrayList<>();
        for (int i = 0; i < testVectors.size(); i++) {
            ArrayList<Double> testVector = testVectors.get(i);
            double distance = 0;
            for (int j = 0; j < testVector.size(); j++) {
                distance += Math.pow(testVector.get(j) - vector.get(j), 2);
            }
            distances.add(Math.sqrt(distance));
        }
        return distances;
    }

    public static HashMap<String, ArrayList<ArrayList<Double>>> loadSet(String fileName) {
        HashMap<String, ArrayList<ArrayList<Double>>> set = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] temp = line.trim().split(",");
                String key = temp[temp.length - 1];
                ArrayList<Double> values = new ArrayList<>();
                for (int i = 0; i < temp.length - 1; i++) {
                    values.add(Double.parseDouble(temp[i]));
                }
                if (!set.containsKey(key)) {
                    ArrayList<ArrayList<Double>> list = new ArrayList<>();
                    list.add(values);
                    set.put(key, list);
                } else {
                    set.get(key).add(values);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file!");
            e.printStackTrace();
        }
        return set;
    }
}