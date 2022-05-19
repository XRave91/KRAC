package com.company;

import java.util.Scanner;
import static java.util.Collections.nCopies;

public class Main {
    public static int convertToArabic(String a){
        final String[] romanNumbers =new String[]{"","I","II","III","IV","V","VI","VII","VIII","IX","X"};
        for (int i = 0; i <=10; i++) {
            if(a.equalsIgnoreCase(romanNumbers[i])) return i;
        }
        throw new RuntimeException("wrong roman number");
    }

    public static String convertToRoman(Integer a){
        if (a<1) throw new RuntimeException("roman number cant be lower than I");
        return String.join("", nCopies(a, "I"))
                .replace("IIIII", "V")
                .replace("IIII", "IV")
                .replace("VV", "X")
                .replace("VIV", "IX")
                .replace("XXXXX", "L")
                .replace("XXXX", "XL")
                .replace("LL", "C");
    }
    public static String calc(String input) {
        String[] abarray;
        int result=-1;
        int a=0;
        int b=0;
        boolean isRoman = false;
        abarray = input.split(" ");
        if (abarray.length != 3) {
            throw new RuntimeException("wrong input string format should be like 'a + b'");
        }
        if (abarray[0].matches("^[1-9]$|10$")&&abarray[2].matches("^[1-9]$|10$")){
            a=Integer.parseInt(abarray[0]);
            b=Integer.parseInt(abarray[2]);
        } else{
            if (abarray[0].matches("^[XIV]{1,4}$")&&abarray[2].matches("^[XIV]{1,4}$")){
                a=convertToArabic(abarray[0]);
                b=convertToArabic(abarray[2]);
                isRoman=true;
            } else{
                throw new RuntimeException("wrong number format");
            }
        }

        if (abarray[1].matches("^[+\\-/*]$")){
            switch (abarray[1]) {
                case("+"):
                    result=a+b;
                    break;
                case("-"):
                    result=a-b;
                    break;
                case("/"):
                    result=(int)(a/b);
                    break;
                case("*"):
                    result=a*b;
                    break;
            }
        } else {
            throw new RuntimeException("wrong operand: must be '+' '-' '/' '*'. But '"+abarray[1]+" is given");
        }

        if (isRoman) {
            return convertToRoman(result);
        }
        return(Integer.toString(result));

    }

    public static void main(String[] args) {
        String input_line;
        Scanner in = new Scanner(System.in);
        input_line=in.nextLine();
        in.close();
        System.out.println(calc(input_line));
    }
}
