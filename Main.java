package own_jokes;

import java.math.BigInteger;
import java.util.*;
import java.util.regex.Pattern;

public class Main {
    public static void main (String[] args) {
//        char[] chars = "abc+123)^sad".toCharArray();
        System.out.println(expand("(9t-0)^2"));

    }



    public static String expand(String expr) {
        char[] chars = expr.toCharArray();
        char variable = 'x';
        int varCoefficient = 0, freeCoefficient = 0, power = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 97 && chars[i] <= 122) {
                variable = chars[i];
                varCoefficient = checkNumReverse(chars, i - 1);
                freeCoefficient = checkNum(chars, i + 1);
                for (int j = i; j < chars.length; j++) {
                    if (chars[j] == '^') {
                        power = checkNum(chars, j + 1);
                        break;
                    }
                }
                break;
            }
        }

        if (freeCoefficient == 0) {
            return "" + (Math.pow(varCoefficient, power) == 1 ? "" : (int) Math.pow(varCoefficient, power)) + variable + "^" + power;
        } else if (power == 0) {
            return "1";
        }

        long[] nthRowOfPascalsTriangle = new long[power + 1];
        nthRowOfPascalsTriangle[0] = 1;
        for(int i = 1; i <= power; i++)
        {
            nthRowOfPascalsTriangle[i] = (nthRowOfPascalsTriangle[i - 1] * (power - i + 1)) / i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nthRowOfPascalsTriangle.length; i++) {
            int localPower = power - i;
            long localResult = nthRowOfPascalsTriangle[i] * (long) (Math.pow(varCoefficient, localPower) * Math.pow(freeCoefficient, i));
            if (localPower > 0) {
                if (i > 0 && localResult > 0) {
                    sb.append("+");
                }
                if (localResult == -1) {
                    sb.append("-");
                }
                if (Math.abs(localResult) > 1) {
                    sb.append(localResult);
                }
                sb.append(variable);
                if (localPower > 1) {
                    sb.append("^").append(localPower);
                }
            } else {
                if (localResult > 0) {
                    sb.append("+");
                }
                sb.append(localResult);
            }
        }
        return sb.toString();
    }

    public static int checkNumReverse(char[] arr, int i) {
        StringBuilder s = new StringBuilder();
        while (i >= 0 && arr[i] >= 48 && arr[i] <= 57 || arr[i] == '-') {
            s.insert(0, arr[i]);
            i--;
        }
        if (s.length() == 0) {
            return 1;
        } else if (s.toString().equals("-")) {
            return -1;
        }
        return Integer.parseInt(s.toString());
    }

    public static int checkNum(char[] arr, int i) {
        StringBuilder s = new StringBuilder();
        do {
            s.append(arr[i]);
            i++;
        } while (i < arr.length && arr[i] >= 48 && arr[i] <= 57);
        return Integer.parseInt(s.toString());
    }
}

