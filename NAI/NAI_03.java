package NAI_03;

public class NAI_03 {
    public static void main(String[] args) {
        int[] y_true = {1,0,1,0,1,1,1,0,0,1};
        int[] y_pred = {0,1,1,0,1,0,1,0,1,1};


        double f = f_score(y_true,y_pred);
        System.out.println(f);
    }

    public static double f_score(int[] y_true, int[] y_pred) {
        double p = p_score(y_true, y_pred);
        double r = r_score(y_true, y_pred);
        return 2*p*r/(p+r);
    }

    public static double p_score(int[] y_true, int[] y_pred){
        double tp = 0;
        double fp = 0;
        for (int i = 0; i < y_true.length; i++) {
            if(y_true[i] == 1 && y_pred[i] == 1) tp++;
            if(y_true[i] == 1 && y_pred[i] == 0) fp++;
        }
        return tp/(tp+fp);
    }

    public static double r_score(int[] y_true, int[] y_pred){
        double tp = 0;
        double fn = 0;
        for (int i = 0; i < y_true.length; i++) {
            if(y_true[i] == 1 && y_pred[i] == 1) tp++;
            if(y_true[i] == 0 && y_pred[i] == 1) fn++;
        }
        return tp/(tp+fn);
    }




}
