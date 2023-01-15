package br.com.karla.calculatorapi;

import br.com.karla.calculatorapi.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalculatorController {

    @RequestMapping(value = "/sum/{firstNumber}/{secondNumber}", method = RequestMethod.GET)
    public double sum(
            @PathVariable(value = "firstNumber") String firstNumber,
            @PathVariable(value = "secondNumber") String secondNumber
    ) throws Exception {

        if (!validateNumbers(firstNumber, secondNumber)){
            throw new UnsupportedMathOperationException("Please insert only numeric values.");
        }

        return convertToDouble(firstNumber) + convertToDouble(secondNumber);
    }

    @RequestMapping(value = "/subtract/{firstNumber}/{secondNumber}", method = RequestMethod.GET)
    public double subtract(
            @PathVariable(value = "firstNumber") String firstNumber,
            @PathVariable(value = "secondNumber") String secondNumber
    ) throws Exception {

        if (!validateNumbers(firstNumber, secondNumber)){
            throw new UnsupportedMathOperationException("Please insert only numeric values.");
        }

        return convertToDouble(firstNumber) - convertToDouble(secondNumber);
    }

    @RequestMapping(value = "/multiply/{firstNumber}/{secondNumber}", method = RequestMethod.GET)
    public double multiply(
            @PathVariable(value = "firstNumber") String firstNumber,
            @PathVariable(value = "secondNumber") String secondNumber
    ) throws Exception {

        if (!validateNumbers(firstNumber, secondNumber)){
            throw new UnsupportedMathOperationException("Please insert only numeric values.");
        }

        return convertToDouble(firstNumber) * convertToDouble(secondNumber);
    }

    @RequestMapping(value = "/divide/{firstNumber}/{secondNumber}", method = RequestMethod.GET)
    public double divide(
            @PathVariable(value = "firstNumber") String firstNumber,
            @PathVariable(value = "secondNumber") String secondNumber
    ) throws Exception {

        if (!validateNumbers(firstNumber, secondNumber)){
            throw new UnsupportedMathOperationException("Please insert only numeric values.");
        }

        return convertToDouble(firstNumber) / convertToDouble(secondNumber);
    }

    @RequestMapping(value = "/average/{firstNumber}/{secondNumber}", method = RequestMethod.GET)
    public double avg(
            @PathVariable(value = "firstNumber") String firstNumber,
            @PathVariable(value = "secondNumber") String secondNumber
    ) throws Exception {

        if (!validateNumbers(firstNumber, secondNumber)){
            throw new UnsupportedMathOperationException("Please insert only numeric values.");
        }

        return (convertToDouble(firstNumber) + convertToDouble(secondNumber)) / 2;
    }

    @RequestMapping(value = "/squareRoot/{number}", method = RequestMethod.GET)
    public double squareRoot(
            @PathVariable(value = "number") String number
    ) throws Exception {

        if (!isNumeric(number)){
            throw new UnsupportedMathOperationException("Please insert a numeric value.");
        }

        return Math.sqrt(
                Double.parseDouble(
                        number.replaceAll(",", ".")
                )
        );
    }


    private boolean validateNumbers (String firstNumber, String secondNumber) {
        if (firstNumber == null || secondNumber == null){
            return false;
        }

        if (!isNumeric(firstNumber) || !isNumeric(secondNumber)){
            return false;
        }

        return true;
    }

    private boolean isNumeric (String number){
        return number.replaceAll(",", ".").matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    private double convertToDouble (String number){
        return Double.parseDouble(number.replaceAll(",", "."));
    }

}