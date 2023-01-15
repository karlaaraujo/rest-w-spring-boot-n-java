package br.com.karla.calculatorapi.controllers;

import br.com.karla.calculatorapi.exceptions.UnsupportedMathOperationException;
import br.com.karla.calculatorapi.math.Arithmetic;
import org.springframework.web.bind.annotation.*;
import static br.com.karla.calculatorapi.helpers.Conversion.convertToDouble;
import static br.com.karla.calculatorapi.helpers.Validation.isNumeric;

@RestController
public class CalculatorController {

    @RequestMapping(value = "/sum/{firstNumber}/{secondNumber}", method = RequestMethod.GET)
    public double sum(
            @PathVariable(value = "firstNumber") String firstNumber,
            @PathVariable(value = "secondNumber") String secondNumber
    ) throws Exception {

        if (!isNumeric(firstNumber) || !isNumeric(secondNumber)){
            throw new UnsupportedMathOperationException("Please insert only numeric values.");
        }

        Arithmetic arithmetic = new Arithmetic();

        return arithmetic.sum(convertToDouble(firstNumber), convertToDouble(secondNumber));
    }

    @RequestMapping(value = "/subtract/{firstNumber}/{secondNumber}", method = RequestMethod.GET)
    public double subtract(
            @PathVariable(value = "firstNumber") String firstNumber,
            @PathVariable(value = "secondNumber") String secondNumber
    ) throws Exception {

        if (!isNumeric(firstNumber) || !isNumeric(secondNumber)){
            throw new UnsupportedMathOperationException("Please insert only numeric values.");
        }

        Arithmetic arithmetic = new Arithmetic();

        return arithmetic.subtract(convertToDouble(firstNumber), convertToDouble(secondNumber));
    }

    @RequestMapping(value = "/multiply/{firstNumber}/{secondNumber}", method = RequestMethod.GET)
    public double multiply(
            @PathVariable(value = "firstNumber") String firstNumber,
            @PathVariable(value = "secondNumber") String secondNumber
    ) throws Exception {

        if (!isNumeric(firstNumber) || !isNumeric(secondNumber)){
            throw new UnsupportedMathOperationException("Please insert only numeric values.");
        }

        Arithmetic arithmetic = new Arithmetic();

        return arithmetic.multiply(convertToDouble(firstNumber), convertToDouble(secondNumber));
    }

    @RequestMapping(value = "/divide/{firstNumber}/{secondNumber}", method = RequestMethod.GET)
    public double divide(
            @PathVariable(value = "firstNumber") String firstNumber,
            @PathVariable(value = "secondNumber") String secondNumber
    ) throws Exception {

        if (!isNumeric(firstNumber) || !isNumeric(secondNumber)){
            throw new UnsupportedMathOperationException("Please insert only numeric values.");
        }

        Arithmetic arithmetic = new Arithmetic();

        return arithmetic.divide(convertToDouble(firstNumber), convertToDouble(secondNumber));
    }

    @RequestMapping(value = "/average/{firstNumber}/{secondNumber}", method = RequestMethod.GET)
    public double avg(
            @PathVariable(value = "firstNumber") String firstNumber,
            @PathVariable(value = "secondNumber") String secondNumber
    ) throws Exception {

        if (!isNumeric(firstNumber) || !isNumeric(secondNumber)){
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



}