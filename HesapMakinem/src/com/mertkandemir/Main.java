package com.mertkandemir;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    // 2 7 9 * 3 / +
    // postfix olarak yazılmış matematiksel ifadeyi alıp
    // sonucu hesaplar
    public static String postfixHesaplama(String expression) {
        String items[] = expression.split(" ");
        // işlem sonuçları virgüllü çıkabilir
        Stack<Double> st = new Stack<>();

        // her tokeni tara
        for(String item : items) {
            // 0 index i digit ise bu bir sayı demektir
            if (Character.isDigit(item.charAt(0))) {
                // Eğer sayı ise yığına at
                st.push(Double.parseDouble(item));
            } else { // Opeatör durumu
                double right = st.pop(); // en üst
                double left = st.pop(); // üstten ikinci
                char op = item.charAt(0);
                double res = 0;
                // sonucu bul ve diğer işlemlerde kullanılmak üzere
                // yığına ekle
                switch (op) {
                    case '+':
                        res = left + right;
                        break;
                    case '-':
                        res = left - right;
                        break;
                    case '/':
                        res = left / right;
                        break;
                    case '*':
                        res = left * right;
                        break;
                    case '^':
                        res = Math.pow(left, right);
                }
                st.push(res);
            }
        }

        // yığında kalan son bir eleman bize sonucu verecektir
        return String.format("%.6f", st.pop());
    }

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("***---LÜTFEN YAPMAK İSTEDİĞİNİZ İŞLEMİ GİRİNİZ---***");
        String expression = input.next();

        // Düz matematiksel ifadeyi shanting yard kullanarak postfixe çevir
        String rpn = ShuntingYardAlgorithm.infixToRpn(expression);


        System.out.println(postfixHesaplama(rpn));
    }
}
