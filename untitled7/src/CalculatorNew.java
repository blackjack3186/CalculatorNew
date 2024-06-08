
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

                public class CalculatorNew {
                    static Scanner scanner = new Scanner(System.in);
                    static int number;
                    static char operation;
                    static String result;

                    public static void main(String[] args) throws Exception {
                        System.out.println("Введите две строки, либо строку и число, между ними знак операции: ");
                        String userInput = scanner.nextLine();
                        char[] uchar = new char[40]; //Массив данных.
                        ArrayList<String> strBlock00 = new ArrayList<>(10);
                        for (int i = 0; i < userInput.length(); i++) {
                            uchar[i] = userInput.charAt(i); //Выбор операции.
                            if (uchar[i] == '+') {
                                operation = '+';
                            }
                            if (uchar[i] == '-') {
                                operation = '-';
                            }
                            if (uchar[i] == '*') {
                                operation = '*';
                            }
                            if (uchar[i] == '/') {
                                operation = '/';
                            }

                        }


                        String[] blocks = userInput.split("[+-/*\"]");

                        if (blocks.length == 5) { // Вычисление строки на строку
                            String st00 = blocks[0];
                            String st01 = blocks[1];
                            String st02 = blocks[2];
                            String st03 = blocks[3];
                            String st04 = blocks[4];
                            result = calculated(st01, st04, operation);
                            limitCalc();
                        }  else if (blocks.length ==4){ //Вычисление строки на число
                            String st01 = blocks[1];
                            String st03 = blocks[3];
                            number = Integer.parseInt(st03);
                            result = calculated(st01, number, operation);
                            limitCalc();
                        }else{ // А любая другая операция выбросит исключение.
                            throw new Exception("Недопустимая операция.");
                        }
                    }

                    private static void limitCalc() { //Этот метод считает количество символов в строке результата и выводит в консоль. Если число превысит 40, то строка обрежется и будет добавлено троеточие.
                        int limit = 40;
                        if(result.length() < 40) {
                            System.out.println(result);
                        } else{
                            String subStr = result.codePointCount(0, result.length()) > limit ?
                                    result.substring(0, result.offsetByCodePoints(0, limit)) : result;
                            System.out.println(subStr + "...");
                        }
                    }


                    public static String calculated(String stroka1, String stroka2, char op) throws Exception { //Операции СТРОКА + ЧИСЛО

                        switch (op) {
                            case '+':
                                result = stroka1 + stroka2;
                                break;
                            case '-':
                                int resultA = stroka1.length() - stroka2.length();
                                result = stroka1.substring(0, resultA);
                                break;
                            case '*':
                                throw new Exception("Ошибка. При операциях строки на строку допустимы только знаки + или -");
                            case '/':
                                throw new Exception("Ошибка. При операциях строки на строку допустимы только знаки + или -");
                            default:
                                throw new IllegalArgumentException("Не верный знак операции");
                        }
                        if ( stroka1.length() > 10 | stroka2.length() > 10 ) {
                            throw new Exception("Ошибка. Можно ввести не более 10 символов в одну строку");
                        }

                        return result;
                    }

                    public static String calculated(String stroka1, int chislo, char op) throws Exception { //ОПЕРАЦИИ СТРОКА + ЧИСЛО

                        switch (op) {
                            case '+':
                                throw new Exception("При операции с числом и строкой можно использовать только * или /");
                            case '-':
                                throw new Exception("При операции с числом и строкой можно использовать только * или /");
                            case '*': // Тут использовал метод repeat.
                                    String str = stroka1;
                                    String repeated = str.repeat(chislo);
                                    result = repeated;

                                break;
                            case '/':
                                try {
                                    int resultB = stroka1.length() / chislo;
                                    result = stroka1.substring(0, resultB);
                                } catch (ArithmeticException | InputMismatchException e) {
                                    throw new IllegalArgumentException("Ноль недопустим!");
                                } finally {
                                    if (stroka1.length() < chislo) {
                                        throw new IllegalArgumentException("Ошибка. Делимое меньше, чем делитель");
                                    }
                                }
                                break;
                            default:
                                throw new IllegalArgumentException("Ошибка. Знак операции неверный.");
                        }
                                if (chislo > 10 | chislo < 0 | stroka1.length() > 10 ) {
                                    throw new Exception("Ошибка. Число или количество символов должно быть в интервале от 1 до 10.");
                                }
                        return result; // ВОЗВРАЩАЕМ РЕЗУЛЬТАТ
                    }
                }