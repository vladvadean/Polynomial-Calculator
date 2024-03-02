package DataModels;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class Monomial {
    private int exponent;
    private float coefficient;

    public Monomial(float coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public Monomial(Monomial other) {
        this.coefficient = other.coefficient;
        this.exponent = other.exponent;
    }

    public Monomial(String input) throws InputException {
        Pattern pattern = Pattern.compile("(?=.)([+-])?(\\d+\\.?\\d*)?(x)?(\\^)?(\\d+)?");
        Matcher matcher = pattern.matcher(input);
        List<String> aux = new ArrayList<>();
        while (matcher.find()) {
            aux.add(matcher.group(1));
            aux.add(matcher.group(2));
            aux.add(matcher.group(3));
            aux.add(matcher.group(4));
            aux.add(matcher.group(5));
        }
        float auxCoeficient = 1;
        int auxExponent = 0;
        if (aux.get(0) != null)
            if (aux.get(0).equals("-"))
                auxCoeficient *= -1;
        if (aux.get(1) != null) {
            try {
                auxCoeficient *= Float.parseFloat(aux.get(1));
            } catch (NumberFormatException ex) {
                throw new InputException(1);
            }
        }
        if (aux.get(2) == null)
            if (aux.get(3) != null && aux.get(4) != null)
                throw new InputException(2);
        if (aux.get(2) != null)
            auxExponent = 1;
        if (aux.get(4) != null)
            if (!aux.get(4).isEmpty()) {
                try {
                    auxExponent *= Integer.parseInt(aux.get(4));
                } catch (NumberFormatException ex) {
                    throw new InputException(4);
                }
            }
        this.coefficient = auxCoeficient;
        this.exponent = auxExponent;
    }

    public float getCoefficient() {
        return this.coefficient;
    }

    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }

    public int getExponent() {
        return this.exponent;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    @Override
    public String toString() {
        String s = "";
        if (this.coefficient == 0)
            return null;
        s += Float.toString(coefficient);
        if (exponent != 0) {
            s += "x^";
            s += Integer.toString(exponent);
        }
        return s;
    }
}
