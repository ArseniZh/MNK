package gauss;

public class Gauss {
    public double[] findSolution(double[][] matrix) {
        int  row = matrix.length, col = row + 1;
        double max = matrix[0][0];
        int max_ind = 0;
        double[] temp = new double[col];
        double[] sol = new double[col-1];

        for (int i = 0; i < col; i++) {
            temp[i] = matrix[0][i];
        }

        for (int i = 1; i < row; i++)// max element
            if (Math.abs(matrix[i][0]) > max) {
                max = matrix[i][0];
                max_ind = i;
            }

        for (int j = 0; j < col; j++) { // switch
            matrix[0][j] = matrix[max_ind][j];
            matrix[max_ind][j] = temp[j];
        }

        for (int k = 0; k < row; k++) {
            if (matrix[k][k] == 0) { // main diagonal 1
                for (int i = 0; i < col; i++)
                    temp[i] = matrix[k + 1][i];

                for (int i = 0; i < col; i++) { // switch
                    matrix[k + 1][i] = matrix[k][i];
                    matrix[k][i] = temp[i];
                }
            }

            double ind1 = matrix[k][k];
            for (int i = k; i < col; i++) { // division
                matrix[k][i] /= ind1;
            }

            for (int i = k + 1; i < row; i++) { // subtract
                double ind = matrix[i][k]; // first element
                for (int j = k; j < col; j++) {
                    matrix[i][j] = matrix[i][j] - ind * matrix[k][j]; // from row substract previos
                }
            }
        }

        for (int i = row - 1; i >= 0; i--) { // solutions
            for (int j = 0, counter = 0; j < row; j++) {
                if (counter == row - 1)
                    break;
                if (j == i)
                    j++;

                sol[i] -= sol[j] * matrix[i][j];
                counter++;
            }
            sol[i] += matrix[i][col - 1];
        }
        return sol;
    }
}
