package NAI_02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class NAI_02 {
    public static HashMap<String, ArrayList<ArrayList<Double>>> set = new HashMap<>();
    public static HashMap<String, Integer> values = new HashMap<>();
    public static double bias;
    public static ArrayList<Double> weights;
    public static int vectorSize;
    public static double learningRate;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        set = loadSet("src/NAI_02/perceptron.data");
        int k = 0;
        for (String key : set.keySet()) {
            values.put(key, k);
            k++;
        }
        weights = new ArrayList<>();
        bias = Math.random();
        for (int i = 0; i < vectorSize; i++) {
            weights.add(Math.random());
        }

        System.out.println("Initial weights: " + weights);
        System.out.println("Initial bias: " + bias);
        System.out.println("Input a learning rate: ");
        learningRate = sc.nextDouble();
        System.out.println("Input a number of epoch: ");
        int epoch = sc.nextInt();
        trainPerceptron(epoch);
        System.out.println("updated weights: " + weights);
        System.out.println("updated bias: " + bias);

        HashMap<String, ArrayList<ArrayList<Double>>> testSet = loadSet("src/NAI_02/perceptron.test.data");
        double accuracy = testPerceptron(testSet);
        System.out.println("Accuracy: " + accuracy + "%");
    }

    public static void trainPerceptron(int epoch) {
        for (int i = 0; i < epoch; i++) {
            for (String key : set.keySet()) {
                int expectedOutput = values.get(key);
                for (ArrayList<Double> trainVector : set.get(key)) {
                    updateWeights(trainVector, expectedOutput);
                }
            }
        }
    }

    public static double testPerceptron(HashMap<String, ArrayList<ArrayList<Double>>> testSet) {
        int correctPredictions = 0;
        int totalPredictions = 0;

        for (String key : testSet.keySet()) {
            int expectedOutput = values.get(key);
            for (ArrayList<Double> testVector : testSet.get(key)) {
                int prediction = predict(testVector);
                if (prediction == expectedOutput) correctPredictions++;
                totalPredictions++;
            }
        }
        return (double) correctPredictions / totalPredictions * 100;
    }


    public static int predict(ArrayList<Double> testVector) {
        double net = 0;
        for (int i = 0; i < testVector.size(); i++) {
            net += weights.get(i) * testVector.get(i);
        }
        net = net - bias;
        if (net >= 0) return 1;
        else return 0;
    }

    public static void updateWeights(ArrayList<Double> trainVector, int expectedOutput) {
        ArrayList<Double> tempWeights = new ArrayList<>();
        int output = predict(trainVector);
        for (int i = 0; i < vectorSize; i++) {
            tempWeights.add(weights.get(i) + learningRate * (expectedOutput - output) * trainVector.get(i));
        }
        weights = tempWeights;
        bias = bias - learningRate * (expectedOutput - output);

    }

    public static HashMap<String, ArrayList<ArrayList<Double>>> loadSet(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] temp = line.trim().split(",");
                String key = temp[temp.length - 1];
                ArrayList<Double> values = new ArrayList<>();
                if (vectorSize < temp.length - 1) vectorSize = temp.length - 1;
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
        }
        return set;
    }


}
