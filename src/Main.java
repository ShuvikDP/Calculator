import java.util.Scanner;
public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        if (scanner.hasNext()) {
            String s = scanner.nextLine();
            s = s.replaceAll("\\s+", ""); //Устранение проблемы "лишних" пробелов в операции
            String operator = defineOperator(s);
            String[] stringArray = s.split("[+-/*]"); //Разделителение строк на подстроки по условию

            if (stringArray.length > 2) {
                throw new IllegalArgumentException("ОШИБКА: формат математической операции не удовлетворяет заданию - два операнда и один оператор");
            }
            //Проверка строки на количество операндов (не более двух)
            if ((romanToNumber(stringArray[0]) == -1 && romanToNumber(stringArray[1]) != -1)
                    || (romanToNumber(stringArray[0]) != -1 && romanToNumber(stringArray[1]) == -1)) {
                throw new IllegalArgumentException("ОШИБКА: используются одновременно разные системы счисления");
            }
            //Проверка на сооветствие условию, что оба операнда записаны одновременно либо арабскими, либо римскими цифрами
            int result;
            if (romanToNumber(stringArray[0]) == -1 || romanToNumber(stringArray[1]) == -1) {
                result = calc(Integer.parseInt(stringArray[0]), Integer.parseInt(stringArray[1]), operator);
                System.out.println(result);
            } else {
                if((romanToNumber(stringArray[0]) <= romanToNumber(stringArray[1])) && operator.equals("-")){
                    throw new IllegalArgumentException("ОШИБКА: в римской системе нет отрицательных чисел");
                }
                result = calc(romanToNumber(stringArray[0]), romanToNumber(stringArray[1]), operator);
                System.out.println(intToRoman(result));
            }
            //Если оба числа арабские, то вычисляем арабскими и по аналогии для римских чисел
            System.out.println();
        }

    }
    private static int calc(int left, int right, String operator) {
        if ((((float) left / 10) > 1) || (((float) right / 10) > 1)) {
            throw new IllegalArgumentException("ОШИБКА: калькулятор принимает на вход только положительные числа от 1 до 10 включительно");
        }
        if (operator.equals("*")) {
            return left * right;
        }
        if (operator.equals("-")) {
            return left - right;
        }
        if (operator.equals("+")) {
            return left + right;
        }
        if (operator.equals("/")) {
            double result = (double) left / (double) right;
            if ((result / (int) result) != 1) {
                throw new IllegalArgumentException("ОШИБКА: не целочисленное число");
            }
            return (int) result;
        } else throw new IllegalArgumentException("ОШИБКА: уравнение неверно записано");
    }
    //Само уравнение
    private static String defineOperator(String string) {
        if (string.contains("*")) {
            return "*";
        }
        if (string.contains("+")) {
            return "+";
        }
        if (string.contains("-")) {
            return "-";
        }
        if (string.contains("/")) {
            return "/";
        } else throw new IllegalArgumentException("ОШИБКА: в строке отсутствует знак операции");
    }
    //Определение знака
    private static int romanToNumber(String roman) {
        return switch (roman) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> -1;
        };
    }
    //Приведение римских чисел к арабским
    public static String intToRoman(int num) {
        int[] values = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanLetters = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num = num - values[i];
                roman.append(romanLetters[i]);
            }
            //Используемые римские числа
        }
        return roman.toString();
    }
}