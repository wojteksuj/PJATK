package NAI_08;

import java.util.ArrayList;

public class NAI_08 {
    public static void main(String[] args) {

        double[] x1 = new double[]{1, 1, 2, 4};
        double[] y = new double[]{1, 2, 3, 5};
        double threshold = 1.5;

        System.out.println(nodeRSS(x1, y, threshold));
    }

    public static double nodeRSS(double[] x, double[] y, double treshold) {
        ArrayList<Double> lessThanThreshold = new ArrayList();
        ArrayList<Double> moreThanThreshold = new ArrayList();
        for (int i = 0; i < x.length; i++) {
            if (x[i] < treshold) lessThanThreshold.add(y[i]);
            else moreThanThreshold.add(y[i]);
        }

        double mean1 = 0;
        for (int i = 0; i < lessThanThreshold.size(); i++) {
            mean1 += lessThanThreshold.get(i);
        }
        mean1 /= lessThanThreshold.size();

        double mean2 = 0;
        for (int i = 0; i < moreThanThreshold.size(); i++) {
            mean2 += moreThanThreshold.get(i);
        }
        mean2 /= moreThanThreshold.size();

        double resultRSS = 0;
        for (int i = 0; i < lessThanThreshold.size(); i++) {
            resultRSS += Math.pow(lessThanThreshold.get(i) - mean1, 2);
        }
        for (int i = 0; i < moreThanThreshold.size(); i++) {
            resultRSS += Math.pow(moreThanThreshold.get(i) - mean2, 2);
        }
        return resultRSS;
    }


}