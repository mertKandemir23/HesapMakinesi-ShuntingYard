package com.mertkandemir;

import java.util.Stack;

public class ShuntingYardAlgorithm {
    static int operatorOnceligi(char ch)
    {

        if (ch == '+' || ch == '-')
            return 1;
        else if (ch == '*' || ch == '/')
            return 2;
        else if (ch == '^')
            return 3;
        else
            return -1;
    }

    // Metot verilen infixi postFix ifadesine çeviriyor.
    //Shunting yard algoritmasını gösteriyor.
    static String infixToRpn(String expression)
    {
        // Boş yığınla başla
        Stack<Character> stack = new Stack<>();

        // sonuç stringini boş olarak başlat
        String output = new String("");

        // Karakterleri tara
        for (int i = 0; i < expression.length();) {
            char c = expression.charAt(i);
            // boşlukları ele
            if (c == ' ') {
                i ++;
                continue;
            }

            // Eğer rakam ile karşılaşırsak takip eden karakterleri de alıp
            // sayıyı tümden alırız
            // ['1', '2', '3'] => "123"
            if (Character.isDigit(c)) {
                String currNum = "" + c;
                i ++;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    currNum += expression.charAt(i);
                    i ++;
                }
                output += currNum + " ";
                continue;
            }

            // Eğer opratör varsa bir önceki ile öncelikleri karşılaştır ve yığına ekle
            else {
                while (
                        !stack.isEmpty()
                                && operatorOnceligi(c)
                                <= operatorOnceligi(stack.peek())) {
                    // peek() inbuilt stack function to
                    // fetch the top element(token)

                    output += stack.pop() + " ";
                }
                stack.push(c);
            }

            // indexi güncelle
            i ++;
        }

        // yığındaki tüm operatörleri sonuç stringine at
        while (!stack.isEmpty()) {
            output += stack.pop() + " ";
        }

        return output.trim();
    }
}
