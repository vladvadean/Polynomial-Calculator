package DataModels;

import javafx.collections.ModifiableObservableListBase;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Polynomial {
    private TreeMap<Integer, Monomial> structure = new TreeMap<>();

    public Polynomial(TreeMap<Integer, Monomial> s) {
        this.structure = s;
    }

    public Polynomial(Polynomial other) {
        this.structure = new TreeMap<>();
        for (Map.Entry<Integer, Monomial> entry : other.structure.entrySet()) {
            this.structure.put(entry.getKey(), new Monomial(entry.getValue()));
        }
    }

    public TreeMap<Integer, Monomial> getStructure() {
        return structure;
    }

    public Polynomial(String input) throws InputException {
        this.structure = new TreeMap<>();
        Pattern pattern = Pattern.compile("((?=.)([+-])?(\\d+\\.?\\d*)?(x)?(\\^)?(\\d+)?)");
        Matcher matcher = pattern.matcher(input);
        List<Monomial> mList = new ArrayList<>();
        while (matcher.find()) {
            mList.add(new Monomial(matcher.group()));
        }
        for (Monomial m : mList) {
            if (this.structure.containsKey(m.getExponent())) {
                Monomial maux = new Monomial(m.getCoefficient() + this.structure.get(m.getExponent()).getCoefficient(), m.getExponent());
                this.structure.put(maux.getExponent(),maux);
            }
            else
                this.structure.put(m.getExponent(), m);
        }
    }

    @Override
    public String toString() {
        int k = 0;
        String s = "";
        Monomial first, last;
        first = this.structure.get(this.structure.lastKey());
        last = this.structure.get(this.structure.firstKey());
        NavigableMap<Integer, Monomial> aux;
        if (first.getExponent() > last.getExponent())
            aux = structure.descendingMap();
        else
            aux = structure;
        for (Map.Entry<Integer, Monomial> entry : aux.entrySet()) {
            if (k != 0) {
                if (entry.getValue().getCoefficient() > 0)
                    s += '+';
            }
            if (entry.getValue().getCoefficient() != 0)
                s += entry.getValue().toString();
            k++;
        }
        if (s.isEmpty())
            s += '0';
        return s;
    }
}
