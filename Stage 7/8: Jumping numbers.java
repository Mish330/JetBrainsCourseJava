import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        write your code here
        Scanner sc = new Scanner(System.in);
        printMenu();
        while (true) {
            System.out.println("Enter a request:");
            String[] numStr = sc.nextLine().split(" ");

            if (!checkInput(numStr)) {
                //System.out.println("Check Input = " + checkInput(numStr));

                long number1 = Long.parseLong(numStr[0]);
                if (number1 > 0) {
                    int inputLength = numStr.length;
                    switch (inputLength) {
                        case 1 -> {
                            checkFor1(number1);
                        }
                        case 2 -> {
                            int number2 = Integer.parseInt(numStr[1]);
                            if (number2 > 0) {
                                checkFor2(number1, number2);
                            } else {
                                //System.out.println("The second parameter should be a natural number.");
                            }
                        }
                        default -> checkForAll(number1, numStr);
                    }
                } else if (number1 == 0) {
                    System.out.println("Goodbye!");
                    break;
                } else {
                    //System.out.println("The first parameter should be a natural number or zero.");
                }

            } else {
                //System.out.println("Check Input = " + checkInput(numStr));
            }

        }
    }

    public static boolean checkInput(String[] allStr) {

        boolean wrongStatement = false;
        StringBuilder paramBuilder = new StringBuilder("");
        for (int i = 0; i < allStr.length; i++) {
            switch (i) {
                case 0:
                    if (Long.parseLong(allStr[0]) < 0) {
                        System.out.println("The first parameter should be a natural number or zero.");
                        return true;
                    }
                    break;
                case 1:
                    if (Integer.parseInt(allStr[1]) < 0) {
                        System.out.println("The second parameter should be a natural number.");
                        return true;
                    }
                    break;
                default:
                    if (!checkExPar(allStr[i])) {
                        wrongStatement = true;
                        paramBuilder.append(allStr[i]).append(", ");
                    }
                    break;
            }
        }
        if (wrongStatement) {
            System.out.println(paramBuilder);
        }
        return wrongStatement;
    }

    public static boolean checkExPar (String str1) {
        return "BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, EVEN, ODD".contains(str1.toUpperCase());
    }

    public static boolean checkMutPar (String str1, String str2) {
        boolean mutCheck = true;
        String str1Ch = str1.toUpperCase();
        String str2Ch = str2.toUpperCase();
        boolean check1 = "EVEN, ODD".contains(str1Ch) && "EVEN, ODD".contains(str2Ch);
        boolean check2 = "DUCK, SPY".contains(str1Ch) && "DUCK, SPY".contains(str2Ch);
        boolean check3 = "SQUARE, SUNNY".contains(str1Ch) && "SQUARE, SUNNY".contains(str2Ch);
        if (!str1Ch.equals(str2Ch) && (check1 || check2 || check3)) {
            mutCheck = false;
        }
        return mutCheck;
    }

    public static boolean checkEven (long num1) {
        return num1 % 2 == 0;
    }

    public static boolean checkBuzz (long num1) {
        return num1 % 10 == 7 || num1 % 7 == 0;
    }

    public static boolean checkDuck(long num2) {
        boolean duckCheck = false;
        for (int i = 1; i <= String.valueOf(num2).length()-1; i++) {
            if (String.valueOf(num2).charAt(i) == '0') {
                duckCheck = true;
                break;
            }
        }
        return duckCheck;
    }

    public static boolean checkPalindromic(long num3) {
        boolean palindromicCheck = true;
        String numStr  = Long.toString(num3);
        int lengthStr = numStr.length();
        int j = lengthStr - 1;
        for (int i = 0; i < lengthStr / 2; i++) {
            if (numStr.charAt(i) != numStr.charAt(j)) {
                palindromicCheck = false;
            }
            j--;
        }
        return palindromicCheck;
    }

    public static boolean checkGapful(long num4) {
        boolean gapfulCheck = false;
        if (num4 > 99) {
            String num4Str = String.valueOf(num4);
            long firstAndLast = Long.parseLong(num4Str.charAt(0) + num4Str.substring(num4Str.length() - 1));
            if (num4 % firstAndLast == 0) {
                gapfulCheck = true;
            }
        }
        return gapfulCheck;
    }

    public static boolean checkSpy (long num7) {
        String[] num7Str = String.valueOf(num7).split("");
        int sum = 0;
        int power = 1;
        for (int i = 0;i < num7Str.length; i++) {
            sum += java.lang.Integer.parseInt(num7Str[i]);
            power *= java.lang.Integer.parseInt(num7Str[i]);
        }
        return sum == power;
    }

    public static boolean checkPerfSq (long num11) {
        double squareRootD = Math.sqrt(num11);
        int squareRootI = (int) squareRootD;
        return squareRootD == squareRootI;
    }

    public static void checkFor2 (long num5, int numCount) {
        for (int i = 0; i < numCount; i++) {
            String strFor2;
            //StringBuilder strFor2 = new StringBuilder();
            strFor2 = num5 + i + " is ";
            if (checkEven(num5 + i)) {
                strFor2 = strFor2 + "even, ";
            } else {
                strFor2 = strFor2 + "odd, ";
            }
            if (checkBuzz(num5 + i)) {
                strFor2 = strFor2 + "buzz, ";
            }
            if (checkDuck(num5 + i)) {
                strFor2 = strFor2 + "duck, ";
            }
            if (checkPalindromic(num5 + i)) {
                strFor2 = strFor2 + "palindromic, ";
            }
            if (checkGapful(num5 + i)) {
                strFor2 = strFor2 + "gapful, ";
            }
            if (checkSpy(num5 + i)) {
                strFor2 = strFor2 + "spy, ";
            }
            if (checkPerfSq(num5 + i)) {
                strFor2 = strFor2 + "square, ";
            }
            if (checkPerfSq(num5 + 1 + i)) {
                strFor2 = strFor2 + "sunny, ";
            }
            System.out.println(strFor2.substring(0, strFor2.length() -2));
        }
    }

    public static void checkFor1 (long num6) {
        System.out.println("Properties of " + num6);
        boolean even = checkEven (num6);
        System.out.println("\t\teven: " + even);
        System.out.println("\t\todd: " + !even);
        System.out.println("\t\tbuzz: " + checkBuzz(num6));
        System.out.println("\t\tduck: " + checkDuck(num6));
        System.out.println("\t\tpalindromic: " + checkPalindromic(num6));
        System.out.println("\t\tgapful: " + checkGapful(num6));
        System.out.println("\t\tspy: " + checkSpy(num6));
        System.out.println("\t\tsquare: " + checkPerfSq(num6));
        System.out.println("\t\tsunny: " + checkPerfSq(num6 + 1));
    }

    public static void checkForAll (long num8, String[] numStr) {
        int numCount = Integer.parseInt(numStr[1]);
        int countParameter = numStr.length;
        long startNum = num8;

        int j;
        for (int i = 1; i <= numCount; i++) {
            j = 2;
            while (j < countParameter) {
                if (checkCase(startNum, numStr[j].toLowerCase())) {
                    j++;
                } else {
                    startNum++;
                    j = 2;
                }
            }
            checkFor2(startNum, 1);
            startNum++;
        }
    }

    public static boolean checkCase (long num10, String parCheck) {
        return switch (parCheck) {
            case "spy" -> checkSpy(num10);
            case "even" -> checkEven(num10);
            case "odd" -> !checkEven(num10);
            case "buzz" -> checkBuzz(num10);
            case "duck" -> checkDuck(num10);
            case "palindromic" -> checkPalindromic(num10);
            case "gapful" -> checkGapful(num10);
            case "square" -> checkPerfSq(num10);
            case "sunny" -> checkPerfSq(num10 + 1);
            default -> false;
        };
    }

    public static void printMenu () {
        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("\t* the first parameter represents a starting number;");
        System.out.println("\t* the second parameter shows how many consecutive numbers are to be printed;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.\n");
    }

}
