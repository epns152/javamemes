package own_jokes;

public class Main {
    public static void main (String[] args) {
        Matrix m1 = new Matrix(new double[][]{
                {12, 30, 30, 30},
                {1, 3, -5, 30},
                {3, 0.5, 23, 21},
                {21, 23, 4, -2}
        });
        Matrix m2 = new Matrix(new double[][]{
                {-10, 23, 13},
                {12, 5, 0},
                {3.5, 45, 1}
        });
        Matrix m3 = new Matrix(new double[][]{
                {-10},
                {12},
                {3.5}
        });
        Matrix m4 = new Matrix(new double[][]{
                {-10, 23, 13}
        });
        System.out.println(m1.determinant(m1));
    }
}
