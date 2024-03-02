package BusinessLogic;


import DataModels.*;

import java.util.*;

public class Operation {
    private Polynomial polynomial1;
    private final Polynomial polynomial2;

    public Operation(Polynomial polynomial1, Polynomial polynomial2) {
        this.polynomial2 = polynomial2;
        this.polynomial1 = polynomial1;
    }

    private Monomial additon(Monomial m1, Monomial m2) {
        //m1+m2
        Monomial aux;
        if (m1 == null) {
            aux = new Monomial(m2.getCoefficient(), m2.getExponent());
            return aux;
        }
        if (m1.getExponent() == m2.getExponent()) {
            aux = new Monomial(m1.getCoefficient() + m2.getCoefficient(), m1.getExponent());
        } else return null;
        return aux;
    }

    public Polynomial addition() {
        //polinomial1->p
        //polinomial2->this
        Polynomial aux;
        TreeMap<Integer, Monomial> tm = new TreeMap<>();
        for (Map.Entry<Integer, Monomial> entry : polynomial1.getStructure().entrySet()) {
            Monomial maux;
            maux = this.additon(polynomial2.getStructure().get(entry.getKey()), entry.getValue());
            if (maux != null) {
                tm.put(maux.getExponent(), maux);
            }

        }
        for (Map.Entry<Integer, Monomial> entry : polynomial2.getStructure().entrySet()) {
            Monomial maux;
            if (polynomial1.getStructure().get(entry.getKey()) == null) {
                maux = entry.getValue();
                tm.put(maux.getExponent(), maux);
            }
        }
        aux = new Polynomial(tm);
        return aux;
    }

    private Monomial subtraction(Monomial m1, Monomial m2) {
        //m1-m2
        Monomial aux;
        if (m1 == null && m2 == null) {
            aux = new Monomial(0, 0);
            return aux;
        }
        if (m1 == null) {
            aux = new Monomial(-m2.getCoefficient(), m2.getExponent());
            return aux;
        }
        if (m2 == null) {
            aux = new Monomial(-m1.getCoefficient(), m1.getExponent());
            return aux;
        }
        if (m1.getExponent() == m2.getExponent()) {
            aux = new Monomial(m1.getCoefficient() - m2.getCoefficient(), m1.getExponent());
        } else return null;
        return aux;
    }

    public Polynomial subtraction(Polynomial p1, Polynomial p2) {
        //polinomial1-polinomial2
        //polinomial1->p
        //polinomial2->this
        if (p1 == null) {
            p1 = polynomial1;
            p2 = polynomial2;
        }
        Polynomial aux;
        TreeMap<Integer, Monomial> tm = new TreeMap<>();
        for (Map.Entry<Integer, Monomial> entry : p2.getStructure().entrySet()) {
            Monomial maux;
            maux = this.subtraction(p1.getStructure().get(entry.getKey()), entry.getValue());
            tm.put(maux.getExponent(), maux);
        }
        for (Map.Entry<Integer, Monomial> entry : p1.getStructure().entrySet()) {
            Monomial maux;
            if (p2.getStructure().get(entry.getKey()) == null) {
                maux = entry.getValue();
                tm.put(maux.getExponent(), maux);
            }
        }
        aux = new Polynomial(tm);
        return aux;
    }

    private Monomial multiply(Monomial m1, Monomial m2) {
        //this->m2
        Monomial aux;
        if (m1 == null) return null;
        aux = new Monomial(m1.getCoefficient() * m2.getCoefficient(), m2.getExponent() + m1.getExponent());
        return aux;
    }

    public Polynomial multiply(Polynomial p1, Polynomial p2) {
        //this->polinomial2
        //p->polinomial1
        if (p1 == null) {
            p1 = polynomial1;
            p2 = polynomial2;
        }
        Polynomial aux;
        TreeMap<Integer, Monomial> tm = new TreeMap<>();
        for (Map.Entry<Integer, Monomial> entryThis : p1.getStructure().entrySet()) {
            for (Map.Entry<Integer, Monomial> entryP : p2.getStructure().entrySet()) {
                Monomial maux;
                maux = this.multiply(entryThis.getValue(), entryP.getValue());
                maux = this.additon(tm.get(maux.getExponent()), maux);
                tm.put(maux.getExponent(), maux);
            }
        }
        aux = new Polynomial(tm);
        return aux;
    }

    private Monomial divide(Monomial m1, Monomial m2) {
        // m/this
        //m->m1
        //this->m2
        Monomial aux;
        if (m1 == null) return null;
        aux = new Monomial(m1.getCoefficient() / m2.getCoefficient(), m1.getExponent() - m2.getExponent());
        return aux;
    }

    public ArrayList<Polynomial> divide() {
        //polinomial1->dividend
        //polinomial2->divisor
        ArrayList<Polynomial> rez = new ArrayList<>();
        Polynomial retainP1 = polynomial1;
        TreeMap<Integer, Monomial> quotinent = new TreeMap<>();
        if (polynomial1.getStructure().lastKey() < polynomial2.getStructure().lastKey()) {
            rez.add(null);
            rez.add(polynomial1);
        }
        while (polynomial1.getStructure().lastKey() >= polynomial2.getStructure().lastKey()) {
            Monomial maux;
            Polynomial paux;
            TreeMap<Integer, Monomial> listAux = new TreeMap<>();
            maux = this.divide(polynomial1.getStructure().get(polynomial1.getStructure().lastKey()), polynomial2.getStructure().get(polynomial2.getStructure().lastKey()));
            listAux.put(maux.getExponent(), maux);
            paux = new Polynomial(listAux);
            polynomial1 = this.subtraction(polynomial1, this.multiply(polynomial2, paux));
            quotinent.put(maux.getExponent(), maux);
            if (Objects.equals(polynomial1.getStructure().lastKey(), polynomial1.getStructure().firstKey())) break;
            polynomial1.getStructure().remove(polynomial1.getStructure().lastKey());
            listAux.remove(maux.getExponent());
        }
        Polynomial pQuotinent = new Polynomial(quotinent);
        Polynomial pRest = polynomial1;
        polynomial1 = retainP1;
        rez.add(pQuotinent);
        rez.add(pRest);
        return rez;
    }

    private Monomial derivate(Monomial m) {
        Monomial rez;
        if (m.getExponent() == 0) {
            m.setCoefficient(0);
            rez = new Monomial(0, 0);
        } else {
            m.setCoefficient(m.getCoefficient() * m.getExponent());
            int auxExponent = m.getExponent();
            m.setExponent(auxExponent - 1);
            rez = new Monomial(m.getCoefficient(), m.getExponent());
        }
        return rez;
    }

    public Polynomial derivate() {
        Polynomial retainP1 = new Polynomial(this.polynomial1);
        TreeMap<Integer, Monomial> tm = new TreeMap<>();
        for (Map.Entry<Integer, Monomial> entryThis : polynomial1.getStructure().entrySet()) {
            Monomial maux;
            maux = this.derivate(entryThis.getValue());
            tm.put(maux.getExponent(), maux);
        }
        Polynomial aux = new Polynomial(tm);
        this.polynomial1 = retainP1;
        return aux;
    }


    private Monomial integrate(Monomial m) {
        Monomial rez;
        m.setExponent(m.getExponent() + 1);
        m.setCoefficient(m.getCoefficient() / m.getExponent());
        rez = new Monomial(m.getCoefficient(), m.getExponent());
        return rez;
    }

    public Polynomial integrate() {
        Polynomial aux;
        Polynomial retainP1 = new Polynomial(this.polynomial1);
        TreeMap<Integer, Monomial> tm = new TreeMap<>();
        for (Map.Entry<Integer, Monomial> entryThis : polynomial1.getStructure().entrySet()) {
            Monomial maux;
            maux = this.integrate(entryThis.getValue());
            tm.put(maux.getExponent(), maux);
        }
        polynomial1 = retainP1;
        aux = new Polynomial(tm);
        return aux;
    }

    public String toString() {
        String rez;
        rez = "BusinessLogic.Operation contains:\n";
        rez += "pol1: " + polynomial1 + '\n';
        rez += "pol2: " + polynomial2;
        return rez;
    }

    public boolean checkPolynomialsEquals(Polynomial polynomial1, Polynomial polynomial2) {
        for (Map.Entry<Integer, Monomial> entryThis : polynomial1.getStructure().entrySet()) {
            if (entryThis != null) if (polynomial2.getStructure().get(entryThis.getKey()) == null) return false;
            else if (polynomial2.getStructure().get(entryThis.getKey()).getCoefficient() != entryThis.getValue().getCoefficient())
                return false;
        }
        return true;
    }

}
