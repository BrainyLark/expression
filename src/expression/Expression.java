package expression;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expression {

    public static void main(String[] args) {
        Expression e = new Expression();
        System.out.println("Input Line : ");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        String token = input;

        Pattern patternNum = Pattern.compile("[0-9]+/[1-9]+");
        Matcher matcher = patternNum.matcher(input);

        ArrayList<String> opList = new ArrayList<>();
        ArrayList<String> numList = new ArrayList<>();
        while (matcher.find()) {
            numList.add(e.gcd(matcher.group()));
            //System.out.println(gcd(matcher.group()));
        }

        token = matcher.replaceAll(" NUM");
        token = token.replaceAll("\\+", " OP_ADD");
        token = token.replaceAll("\\*", " OP_MUL");
        token = token.replaceAll("\\/", " OP_DIV");
        token = token.replaceAll("[^\\+\\*\\/( NUM)( OP_ADD)( OP_MUL)( OP_DIV)]", " UNKNOWN");
        System.out.println("Token  : \t" + token);

        String[] result = token.split(" ");

        if (result.length == 2 && result[1].equals("NUM")) {
            System.out.println("Result : \t" + e.gcd(input));
            return;
        } else if (result[1].equals("OP_ADD") || result[1].equals("OP_MUL") || result[1].equals("OP_DIV")) {
            System.out.println("Result : \t-1");
            return;
        } else {

            for (int i = 0; i < result.length - 1; i++) {
                if (result[i].equals("UNKNOWN") || result[i + 1].equals("UNKNOWN")) {
                    System.out.println("Result : \t" + "0");
                    return;
                } else if (result[i].equals("OP_ADD") && printSemanticError(result[i + 1])) {
                    return;
                } else if (result[i].equals("OP_DIV") && printSemanticError(result[i + 1])) {
                    return;
                } else if (result[i].equals("OP_MUL") && printSemanticError(result[i + 1])) {
                    return;
                } else {
                    if (result[i].equals("OP_MUL") || result[i].equals("OP_ADD") || result[i].equals("OP_DIV")) {
                        opList.add(result[i]);
                    }
                }
            }

            if (result[result.length - 1].equals("OP_MUL") || result[result.length - 1].equals("OP_ADD") || result[result.length - 1].equals("OP_DIV")) {
                System.out.println("Result : \t-1");
                return;
            }
        }

        for (int i = 0; i < opList.size(); i++) {
            if (opList.get(i).equals("OP_MUL")) {
                String res = mul(numList.get(i), numList.get(i + 1));
                numList.set(i + 1, res);
                numList.remove(i);
                opList.remove(i);
            } else if (opList.get(i).equals("OP_DIV")) {
                String res = div(numList.get(i), numList.get(i + 1));
                numList.set(i + 1, res);
                numList.remove(i);
                opList.remove(i);

            }

        }

        for (int i = 0; i < opList.size(); i++) {
            String res = e.add(numList.get(i), numList.get(i + 1));
            numList.set(i + 1, res);
            numList.remove(i);
            opList.remove(i);
        }

        if (numList.size() > 1) {
            System.out.println("Result : \t" + e.gcd(e.add(numList.get(0), numList.get(1))));
        }

    }

    /**
     * Get the Operation name Compare other operation name If input value equals
     * the operation this print the semantics error info "-1"
     */
    static boolean printSemanticError(String op) {
        if (op.equals("OP_ADD") || op.equals("OP_DIV") || op.equals("OP_MUL")) {
            System.out.println("Result : \t-1");
            return true;
        }
        return false;
    }

    static String mul(String num, String num1) {
        String[] n = num.split("\\/");
        String[] n1 = num1.split("\\/");

        int hur = Integer.valueOf(n[0]) * Integer.valueOf(n1[0]);
        int huw = Integer.valueOf(n[1]) * Integer.valueOf(n1[1]);

        return hur + "/" + huw;
    }

    static String div(String num, String num1) {
        String[] n = num.split("\\/");
        String[] n1 = num1.split("\\/");

        int hur = Integer.valueOf(n[0]) * Integer.valueOf(n1[1]);
        int huw = Integer.valueOf(n[1]) * Integer.valueOf(n1[0]);

        return hur + "/" + huw;
    }

    public String add(String num, String num1) {
        int firstNum;
        int secondNum;
        int firstNum1;
        int secondNum1;

        String[] n = num.split("\\/");
        String[] n1 = num1.split("\\/");

        if (n[0].isEmpty() || n[1].isEmpty()) {
            return "p1 empty";
        }
        if (n1[0].isEmpty() || n1[1].isEmpty()) {
            return "p2 empty";
        }
        
        try {
            firstNum = Integer.parseInt(n[0]);
            secondNum = Integer.parseInt(n[1]);
            firstNum1 = Integer.parseInt(n1[0]);
            secondNum1 = Integer.parseInt(n1[1]);
        } catch (NumberFormatException e) {
            return "not integer";
        }

        int hur = firstNum * secondNum1 + secondNum * firstNum1;
        int huw = secondNum * secondNum1;

        return hur + "/" + huw;
    }

    public String gcd(String num) {
        int n1, n2;
        String[] result = num.split("/");
        
        if(result[0].isEmpty())
            return "arg empty";

        try {
            n1 = Integer.valueOf(result[0]);
            n2 = Integer.valueOf(result[1]);
        } catch (NumberFormatException e) {
            return "not integer";
        }
        
        if(n1 == 0 || n2 == 0) 
            return "zero";
        
        int tmp, num1 = n1, num2 = n2;

        while (num2 != 0) {
            tmp = num1 % num2;
            num1 = num2;
            num2 = tmp;
        }

        n2 /= num1;
        n1 /= num1;

        return n1 + "/" + n2;
    }

}
