//package own_jokes;
//
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class Test {
//
//    public static void main(String[] args) {
//        //Task1//
//        double[][] matrix = {{-10, 9, -1, 2, -6, -52.1},
//                {7, 0, 6, 3, 3, 48},
//                {5, 5, -6, 8, -2, 8.4},
//                {-3, 5, 10, -6, 2, 21.4},
//                {-4, 2, 8, 5, 10, 41.5}};
//
//        task11(matrix);
//        //System.out.println(Arrays.toString(task11(matrix)));
//        //Task2//
//        System.out.println("Введіть будь-яке натуральне число : ");
//        Scanner scanner = new Scanner(System.in);
//        float numb1 = scanner.nextFloat();
//        System.out.println("Введіть будь-яке натуральне число : ");
//        float numb2 = scanner.nextFloat();
//        System.out.println("Введіть будь-яке натуральне число : ");
//        float numb3 = scanner.nextFloat();
//        System.out.print("Сума парних дільників для 1 значення : " + task2(numb1) + "\n");
//        System.out.print("Сума парних дільників для 2 значення : " + task2(numb2) + "\n");
//        System.out.print("Сума парних дільників для 3 значення : " + task2(numb3) + "\n");
//    }
//
//    //Зробити трикутникову матрицю//
//    public static double[][] task11(double matrix[][]) {
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[0].length; j++) {
//                System.out.printf("%f ", matrix[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println();
//        //
//        double coef;
//        for (int k = 1; k < matrix.length; k++) {
//            for (int j = k; j < matrix.length; j++) {
//                coef = matrix[j][k - 1] / matrix[k-1][k - 1];
//                for (int i = 0; i < matrix.length + 1; i++) {
//                    System.out.println(matrix[j][k]);
//                    matrix[j][i] -= coef * matrix[k - 1][i];//iLoveOleksiy
//                    System.out.println(matrix[j][i]);
//                    System.out.printf("%d %d %d %f",i,j,k,coef);
//                    System.out.printf("\n");
//                }
//                System.out.printf("\n");
//            }
//
//        }
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[0].length; j++) {
//                System.out.printf("%f ", matrix[i][j]);
//            }
//            System.out.println();
//        }
//        return matrix;
//    }
//
//    /*public static double task12(){
//
//        return 0;
//    }
//    public static double task13(){
//
//        return 0;
//    }*/
//
//    public static int[][] matr (int n, int m) {
//        int[][] res = new int[n][m];
//        for (int i = 0; i < res.length; i++) {
//            for (int j = 0; j < res.length; j++) {
//                res[i][j] = ran(-5, 5);
//            }
//        }
//        return res;
//    }
//    public static int ran(int min, int max) {
//        return (int) (Math.random() * (max - min + 1) + min);
//    }
//}