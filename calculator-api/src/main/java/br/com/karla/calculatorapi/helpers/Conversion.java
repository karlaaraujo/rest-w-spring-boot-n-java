package br.com.karla.calculatorapi.helpers;

public class Conversion {

    public static double convertToDouble (String number){
        return Double.parseDouble(number.replaceAll(",", "."));
    }
}
