package GameArithmetic;


import static java.util.Arrays.sort;

public class GameOperations {

    public static String operation;
    public static int[] numberOfDigits;
    public static int[] numbers;

    public static int result;



    public static void calculate() {

        switch (operation) {
            case "+":
                additional();
                break;
            case "-":
                subtraction();
                break;
            case "*":
                multiply();
                break;
        }
    }


    public static void randomNumbers() {
        numbers = new int[2];
        for (int i = 0; i < 2; i++) {
            numbers[i] = numberOfDigits[0] + (int) ((numberOfDigits[1] - numberOfDigits[0] + 1) * Math.random());
        }
        sort(numbers);
    }

    private static void additional() {
        result = numbers[0] + numbers[1];
    }

    private static void subtraction() {
        result = numbers[1] - numbers[0];
    }

    private static void multiply() {
        result = numbers[0] * numbers[1];
    }


}
