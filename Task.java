package own_jokes;

public class Task {
    public static void main(String[] args) {
        int min = countMaximum(1);
        int mini = 0;
        for (int i = 2; i < 50; i++) {
            if (min >= countMaximum(i)) {
                min = countMaximum(i);
                mini = i;
            }
        }


        System.out.println(min + " " + mini);
        System.out.println(countMaximum(7));
        System.out.println(countMaximum(8));
        System.out.println(countMaximum(9));
        System.out.println(countMaximum(10));
        System.out.println(countMaximum(11));
        System.out.println(countMaximum(12));
        System.out.println(countMaximum(13));
        System.out.println(countMaximum(14));
        System.out.println(countMaximum(15));
    }

    public static int solution(int answer, int interval) {
        int countOfOperations = 0;
        for (int i = 0; i <= 100; i += interval, countOfOperations++) {
            if (answer < i) {
                for (int j = i - interval + 1; j < i; j++, countOfOperations++) {
                    if (j == answer) {
                        return countOfOperations;
                    }
                }
            } else if (answer == i) {
                return countOfOperations;
            }
        }
        return countOfOperations;
    }

    public static int countMaximum(int interval) {
        int max = solution(1, interval);
        for (int i = 2; i <= 100; i++) {
            if (max < solution(i, interval)) {
                max = solution(i, interval);
            }
        }
        return max;
    }
}
