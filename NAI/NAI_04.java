package NAI_04;

import java.io.*;
import java.util.*;

public class NAI_04 {
    public static HashMap<String, ArrayList<ArrayList<Double>>> set = new HashMap<>();
    public static HashMap<String, Integer> values = new HashMap<>();
    public static ArrayList<ArrayList<Double>> weights = new ArrayList<>();
    public static ArrayList<Double> biases = new ArrayList<>();
    public static int vectorSize = 26;
    public static double learningRate;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        set = loadSet("src/NAI_04/lang.train.csv");

        int k = 0;
        for (String key : set.keySet()) {
            values.put(key, k);
            k++;
        }

        initializeParameters(values.size(), vectorSize);

        System.out.println("Input a learning rate: ");
        learningRate = sc.nextDouble();
        System.out.println("Input a number of epochs: ");
        int epoch = sc.nextInt();

        trainPerceptron(epoch);
        System.out.println("Training complete!");


        HashMap<String, ArrayList<ArrayList<Double>>> testSet = loadSet("src/NAI_04/lang.test.csv");
        double accuracy = testPerceptron(testSet);
        System.out.println("Accuracy: " + accuracy + "%");


        sc.nextLine();
        while (true) {
            System.out.println("\nEnter a sentence to predict its language (or type 'exit'):");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("exit")) break;

            double[] vector = vectorize(input);
            ArrayList<Double> inputVector = new ArrayList<>();
            for (double v : vector) inputVector.add(v);

            String predictedLanguage = predictLanguage(inputVector);
            System.out.println("Predicted language: " + predictedLanguage);
        }
    }

    public static void initializeParameters(int numLanguages, int vectorSize) {
        Random rand = new Random();
        for (int i = 0; i < numLanguages; i++) {
            ArrayList<Double> w = new ArrayList<>();
            for (int j = 0; j < vectorSize; j++) {
                w.add(rand.nextDouble());
            }
            weights.add(w);
            biases.add(rand.nextDouble());
        }
    }

    public static void trainPerceptron(int epoch) {
        for (int e = 0; e < epoch; e++) {
            for (String key : set.keySet()) {
                int expectedIndex = values.get(key);
                for (ArrayList<Double> vector : set.get(key)) {
                    for (int i = 0; i < values.size(); i++) {
                        int target = (i == expectedIndex) ? 1 : 0;
                        int output = predictNeuron(i, vector);
                        ArrayList<Double> newWeights = new ArrayList<>();
                        for (int j = 0; j < vectorSize; j++) {
                            double updated = weights.get(i).get(j) + learningRate * (target - output) * vector.get(j);
                            newWeights.add(updated);
                        }
                        weights.set(i, newWeights);
                        biases.set(i, biases.get(i) - learningRate * (target - output));
                    }
                }
            }
        }
    }

    public static double testPerceptron(HashMap<String, ArrayList<ArrayList<Double>>> testSet) {
        int correct = 0, total = 0;
        for (String key : testSet.keySet()) {
            for (ArrayList<Double> vec : testSet.get(key)) {
                String predicted = predictLanguage(vec);
                if (predicted.equals(key)) correct++;
                total++;
            }
        }
        return (double) correct / total * 100;
    }

    public static int predictNeuron(int neuronIndex, ArrayList<Double> inputVector) {
        double net = 0;
        for (int i = 0; i < vectorSize; i++) {
            net += weights.get(neuronIndex).get(i) * inputVector.get(i);
        }
        net -= biases.get(neuronIndex);
        return net >= 0 ? 1 : 0;
    }

    public static String predictLanguage(ArrayList<Double> inputVector) {
        double maxNet = -99999999.0;
        int predictedIndex = -1;

        for (int i = 0; i < weights.size(); i++) {
            double net = 0;
            for (int j = 0; j < vectorSize; j++) {
                net += weights.get(i).get(j) * inputVector.get(j);
            }
            net -= biases.get(i);
            if (net > maxNet) {
                maxNet = net;
                predictedIndex = i;
            }
        }
        for (Map.Entry<String, Integer> entry : values.entrySet()) {
            if (entry.getValue() == predictedIndex) return entry.getKey();
        }
        return "Unknown";
    }

    public static HashMap<String, ArrayList<ArrayList<Double>>> loadSet(String fileName) {
        HashMap<String, ArrayList<ArrayList<Double>>> result = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length < 2) continue;

                String label = parts[0].trim();
                String text = parts[1].trim();

                double[] vectorArray = vectorize(text);
                ArrayList<Double> vector = new ArrayList<>();
                for (double v : vectorArray) vector.add(v);

                result.computeIfAbsent(label, k -> new ArrayList<>()).add(vector);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + fileName);
        }
        return result;
    }

    public static double[] vectorize(String text) {
        double[] result = new double[26];
        String[] parts = text.split(",", 2);
        String content = parts.length == 2 ? parts[1] : parts[0];
        for (char c : content.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                result[c - 'a'] += 1;
            }
        }
        double norm = 0;
        for (double d : result) norm += d * d;
        norm = Math.sqrt(norm);
        if (norm != 0) {
            for (int i = 0; i < result.length; i++) {
                result[i] /= norm;
            }
        }
        return result;
    }
}
