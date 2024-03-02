package GUI;

import BusinessLogic.Operation;
import DataModels.InputException;
import DataModels.Monomial;
import DataModels.Polynomial;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.List;
import java.util.Map;

public class PolynomialCalculatorController {

    private static PolynomialCalculatorView calculatorView;

    public PolynomialCalculatorController(PolynomialCalculatorView calcView) {
        calculatorView = calcView;
    }

    public static EventHandler<ActionEvent> addition = actionEvent -> {
        Polynomial p1 = null;
        try {
            p1 = new Polynomial(calculatorView.getPol1().getText());
        } catch (InputException e) {
            calculatorView.setResult(e.getMessage());
        }
        Polynomial p2 = null;
        try {
            p2 = new Polynomial(calculatorView.getPol2().getText());
        } catch (InputException e) {
            calculatorView.setResult(e.getMessage());
        }
        Operation op = new Operation(p1, p2);
        calculatorView.setResult(op.addition().toString());
    };

    public static EventHandler<ActionEvent> subtraction = actionEvent -> {
        Polynomial p1 = null;
        try {
            p1 = new Polynomial(calculatorView.getPol1().getText());
        } catch (InputException e) {
            calculatorView.setResult(e.getMessage());
        }
        Polynomial p2 = null;
        try {
            p2 = new Polynomial(calculatorView.getPol2().getText());
        } catch (InputException e) {
            calculatorView.setResult(e.getMessage());
        }
        Operation op = new Operation(p1, p2);
        calculatorView.setResult(op.subtraction(p1, p2).toString());
    };

    public static EventHandler<ActionEvent> multiplication = actionEvent -> {
        Polynomial p1 = null;
        try {
            p1 = new Polynomial(calculatorView.getPol1().getText());
        } catch (InputException e) {
            calculatorView.setResult(e.getMessage());
        }
        Polynomial p2 = null;
        try {
            p2 = new Polynomial(calculatorView.getPol2().getText());
        } catch (InputException e) {
            calculatorView.setResult(e.getMessage());
        }
        Operation op = new Operation(p1, p2);
        calculatorView.setResult(op.multiply(p1, p2).toString());
    };

    public static EventHandler<ActionEvent> division = actionEvent -> {
        Polynomial p1 = null;
        try {
            p1 = new Polynomial(calculatorView.getPol1().getText());
        } catch (InputException e) {
            calculatorView.setResult(e.getMessage());
        }
        Polynomial p2 = null;
        try {
            p2 = new Polynomial(calculatorView.getPol2().getText());
        } catch (InputException e) {
            calculatorView.setResult(e.getMessage());
        }
        Operation op = new Operation(p1, p2);
        List<Polynomial> result = op.divide();
        Boolean thereIsRest=false;
        for (Map.Entry<Integer, Monomial> entry : result.get(1).getStructure().entrySet()) {
            if (entry.getValue().getCoefficient() != 0) {
                thereIsRest = true;
                break;
            }
        }
        if (!thereIsRest) {
            calculatorView.setResult("Q: " + result.get(0));
        } else
            calculatorView.setResult("Q: " + result.get(0) + " R: " + result.get(1));
    };
    public static EventHandler<ActionEvent> derivative = actionEvent -> {
        Polynomial p1 = null;
        try {
            p1 = new Polynomial(calculatorView.getPol1().getText());
        } catch (InputException e) {
            calculatorView.setResult(e.getMessage());
        }
        Operation op = new Operation(p1, null);
        calculatorView.setResult(op.derivate().toString());
    };

    public static EventHandler<ActionEvent> integral = actionEvent -> {
        Polynomial p1 = null;
        try {
            p1 = new Polynomial(calculatorView.getPol1().getText());
        } catch (InputException e) {
            calculatorView.setResult(e.getMessage());
        }
        Operation op = new Operation(p1, null);
        calculatorView.setResult(op.integrate().toString());
    };
}