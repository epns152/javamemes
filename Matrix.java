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
                System.out.printf("%f\t", j);
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

    Matrix advancedGauss() {
        double[][] mat = matrix;
        double[] b;
        double k;
        for (int currentIndex = 0; currentIndex < mat.length; currentIndex++) {
            if (currentIndex - 1 >= 0) {
                for (int i = currentIndex; i < mat.length; i++) {
                    if (mat[currentIndex - 1][currentIndex - 1] == 0) {
                        b = mat[currentIndex - 1];
                        mat[currentIndex - 1] = mat[currentIndex];
                        mat[currentIndex] = b;
                        i--;
                    } else if (mat[i][currentIndex - 1] != 0) {
                        k = mat[currentIndex - 1][currentIndex - 1] / mat[i][currentIndex - 1] * -1;
                        for (int j = currentIndex - 1; j < mat[0].length; j++) {
                            mat[i][j] = k * mat[i][j] + mat[currentIndex - 1][j];
                        }
                    }
                }
            }
            k = mat[currentIndex][currentIndex];
            for (int j = currentIndex; j < mat[0].length; j++) {
                if (mat[currentIndex][j] != 0) {
                    mat[currentIndex][j] /= k;
                }
            }
        }
        return new Matrix(mat);
    }

    Matrix gauss() {
        double[][] mat = matrix;
        double[] b;
        double k;
        for (int currentIndex = 1; currentIndex < mat.length; currentIndex++) {
            for (int i = currentIndex; i < mat.length; i++) {
                if (mat[currentIndex - 1][currentIndex - 1] == 0) {
                    b = mat[currentIndex - 1];
                    mat[currentIndex - 1] = mat[currentIndex];
                    mat[currentIndex] = b;
                    i--;
                } else if (mat[i][currentIndex - 1] != 0) {
                    k = mat[currentIndex - 1][currentIndex - 1] / mat[i][currentIndex - 1] * -1;
                    for (int j = currentIndex - 1; j < mat[0].length; j++) {
                        mat[i][j] = k * mat[i][j] + mat[currentIndex - 1][j];
                    }
                }
            }
        }
        return new Matrix(mat);
    }

    public static Matrix randomMatrix(int n, int m, double min, double max) {
        double[][] mat = new double[n][m];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                mat[i][j] = (int) (Math.random() * (max - min + 1) + min);
            }
        }
        return new Matrix(mat);
    }

    public static void print3Matrix(Matrix a, Matrix b, Matrix c) {
        double[][] amat, bmat, cmat;
        amat = a.matrix;
        bmat = b.matrix;
        cmat = c.matrix;
        for (double[] doubles : amat) {
            for (int j = 0; j < amat[0].length; j++) {
                System.out.printf("%.0f\t", doubles[j]);
            }
            System.out.print("\n");
        }
        for (double[] doubles : bmat) {
            for (int t = 0; t < amat.length; t++) {
                System.out.print("\t");
            }
            for (int j = 0; j < bmat[0].length; j++) {
                System.out.printf("%.0f\t", doubles[j]);
            }
            System.out.print("\n");
        }
        for (double[] doubles : cmat) {
            for (int t = 0; t < bmat.length + amat.length; t++) {
                System.out.print("\t");
            }
            for (int j = 0; j < cmat[0].length; j++) {
                System.out.printf("%.0f\t", doubles[j]);
            }
            System.out.print("\n");
        }
    }

    double[] underDiagonal() {
        double[][] mat = matrix;
        int len = 0;
        for (int i = mat.length - 1; i > 0; ) {
            len += (i * 2 - 1);
            i -= 2;
        }
        double[] res = new double[len];
        for (int j = 0, n = 0; j < mat.length; j++) {
            for (int i = j + 1; i < mat.length; i++) {
                res[n++] = mat[i][j];
            }
        }
        return res;
    }
}
