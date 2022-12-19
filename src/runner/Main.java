package runner;

import manager.FileManager;
import gauss.Gauss;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Gauss gauss = new Gauss();
        FileManager manager = new FileManager();
        String path1 = "C:\\Программирование\\СИ\\числаки\\MKD\\src\\x1";
        String path2 ="C:\\Программирование\\СИ\\числаки\\MKD\\src\\y1";
        ArrayList<Double> x = new ArrayList<Double>();
        ArrayList<Double> y = new ArrayList<Double>();
        x = manager.coppingFromFile(path1, x);
        y = manager.coppingFromFile(path2, y);
        int m = 2; // степень апроксимирующего полинома
        int n = x.size(); // число измерений
        int row = m+1;
        int col = row + 1;
        double[] powerx = new double[2*m+1];
        double[][] sumx = new double[m + 1][m + 1];
        double promx;
        double ss = 0;

        for (int k = 0; k < 2 * m + 1; k++) {
            promx = 0;
            for (int i = 0; i < n; i++) {
                promx += Math.pow(x.get(i), k);
            }
            powerx[k] = promx;
        }

        for (int i = 0; i < m + 1; i++) {
            sumx[i] = new double[m + 2];
        }

        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                sumx[i][j] = powerx[i + j];
            }
        }
        sumx[0][0] = n;

        for (int k = 0; k < m + 1; k++) {
            promx = 0;
            for (int i = 0; i < n; i++) {
                promx += Math.pow(x.get(i), k ) * y.get(i);
            }
            sumx[k][m + 1] = promx;
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.printf("%20s",sumx[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        double[] solution = gauss.findSolution(sumx);
        for (int i = 0; i < solution.length; i++) {
            System.out.println("a" + i + " = " + solution[i]);
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 1; j < m + 1; j++) {
                sum -= solution[j]*Math.pow(x.get(i), j);
            }
            ss += y.get(i) - solution[0] + sum*sum;
        }
        double s = Math.pow(1/(n-m-1)*ss,  1/2);
        System.out.print("deviation: ");
        System.out.printf("%.16f",s);
    }
}