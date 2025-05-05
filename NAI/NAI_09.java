package NAI_09;

import java.util.ArrayList;
import java.util.Arrays;

public class NAI_09 {
    public static void main(String[] args) {
        double[][] x_cluster = {{4.0, 8.0, 4.0, 3.0}, {4.0, 6.0, 3.0, 6.0}, {7.0, 7.0, 2.0, 3.0}};
        System.out.println(Arrays.toString(calculateCentroid(x_cluster)));
    }

    public static double[] calculateCentroid(double[][] x_cluster) {
        double[] centroid = new double[x_cluster[0].length];
        for(int i = 0; i < x_cluster[0].length; i++) {
            double avg = 0;
            for(int j = 0; j < x_cluster.length; j++) {
                avg += x_cluster[j][i];
            }
            avg = avg / x_cluster.length;
            centroid[i] = avg;
        }
        return centroid;
    }


}
