package own_jokes;

import java.util.Arrays;

public class Matrix {
    double[][] matrix;
    public Matrix (double[][] matrix) {
        this.matrix = matrix;
    }

    void printMatrix (Matrix matrix) {
        double[][] matr = matrix.matrix;

        for (double[] i:matr) {
            for (double j:i) {
                System.out.printf("%4.2f\t", j);
            }
            System.out.print("\n");
        }
    }

    Matrix mulMatrixOnNum (double num) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] *= num;
            }
        }
        return new Matrix(matrix);
    }

    Matrix mulMatrixOnMatrix (Matrix m) {
        double[][] secMat = m.matrix;
        double[][] resMat = new double[matrix.length][secMat[0].length];
        double matEl;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < secMat[0].length; j++) {
                matEl = 0;
                for (int k = 0; k < matrix[0].length; k++) {
                    matEl = matEl + matrix[i][k] * secMat[k][j];
                }
                resMat[i][j] = matEl;
            }
        }
        return new Matrix(resMat);
    }

    Matrix sumMatrix (Matrix m) {
        double[][] matM = m.matrix;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] += matM[i][j];
            }
        }
        return new Matrix(matrix);
    }

    Matrix diffMatrix (Matrix m) {
        return new Matrix(sumMatrix(m.mulMatrixOnNum(-1)).matrix);
    }

    Matrix minMatrix (Matrix m, double i, double j) {
        double[][] mat = m.matrix;
        double[][] res = new double[m.matrix.length - 1][m.matrix[0].length - 1];
        int im = 0;
        int jm;
        for (int in = 0; in < mat.length; in++) {
            jm = 0;
            if (in != i) {
                for (int jn = 0; jn < mat[0].length; jn++) {
                    if (jn != j) {
                        res[in - im][jn - jm] = mat[in][jn];
                    } else {
                        jm++;
                    }
                }
            } else {
                im++;
            }
        }
        return new Matrix(res);
    }

    double determinant (Matrix m) {
        double[][] mat = m.matrix;
        double det = 0;
        if (mat.length != mat[0].length) {
            System.out.println("Can't count determinant!");
        } else if (mat.length == 2) {
            det = mat[0][0] * mat[1][1] - mat[0][1] * mat[1][0];
        } else {
            for (int i = 0; i < mat.length; i++) {
                det = det + Math.pow(-1, i + 2) * mat[i][0] * determinant(minMatrix(m, i, 0));
            }
        }
        return det;
    }
}
