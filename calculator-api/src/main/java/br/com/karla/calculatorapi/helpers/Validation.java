package br.com.karla.calculatorapi.helpers;

public class Validation {

    public static boolean isNumeric (String number){
        return number != null
                &&
                number.replaceAll(",", ".")
                        .matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
