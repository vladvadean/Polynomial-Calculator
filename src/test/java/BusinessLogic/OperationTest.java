package BusinessLogic;

import DataModels.InputException;
import DataModels.Polynomial;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class OperationTest {
    Operation op;

    @Test
    void testAddition() throws InputException {
        Polynomial p1 = new Polynomial("1.0x^10");
        Polynomial p2 = new Polynomial("1.0x^5");
        op = new Operation(p1, p2);
        Polynomial p3 = new Polynomial("1.0x^10+1.0x^5");
        Assertions.assertTrue(op.checkPolynomialsEquals(op.addition(), p3), "Addition doesn't work!");
    }

    @Test
    void testAdditionIncorrect() throws InputException {
        Polynomial p1 = new Polynomial("1.0x^10");
        Polynomial p2 = new Polynomial("1.0x^6");
        op = new Operation(p1, p2);
        Polynomial p3 = new Polynomial("1.0x^10+1.0x^5");
        Assertions.assertFalse(op.checkPolynomialsEquals(op.addition(), p3), "Addition doesn't work!");
    }

    @Test
    void testSubtraction() throws InputException {
        Polynomial p1 = new Polynomial("1.0x^10");
        Polynomial p2 = new Polynomial("1.0x^5");
        op = new Operation(p1, p2);
        Polynomial p3 = new Polynomial("1.0x^10-1.0x^5");
        Assertions.assertTrue(op.checkPolynomialsEquals(op.subtraction(p1, p2), p3), "Subtraction doesn't work!");
    }

    @Test
    void testSubtractionIncorrect() throws InputException {
        Polynomial p1 = new Polynomial("1.0x^10");
        Polynomial p2 = new Polynomial("1.0x^5");
        op = new Operation(p1, p2);
        Polynomial p3 = new Polynomial("1.0x^10+1.0x^5");
        Assertions.assertFalse(op.checkPolynomialsEquals(op.subtraction(p1, p2), p3), "Subtraction doesn't work!");
    }

    @Test
    void testMultiply() throws InputException {
        Polynomial p1 = new Polynomial("1.0x^10");
        Polynomial p2 = new Polynomial("1.0x^5");
        op = new Operation(p1, p2);
        Polynomial p3 = new Polynomial("1.0x^15");
        Assertions.assertTrue(op.checkPolynomialsEquals(op.multiply(p1, p2), p3), "Multiply doesn't work!");
    }

    @Test
    void testMultiplyIncorrect() throws InputException {
        Polynomial p1 = new Polynomial("1.0x^10");
        Polynomial p2 = new Polynomial("1.0x^5");
        op = new Operation(p1, p2);
        Polynomial p3 = new Polynomial("1.0x^14");
        Assertions.assertFalse(op.checkPolynomialsEquals(op.multiply(p1, p2), p3), "Multiply doesn't work!");
    }

    @Test
    void testDivide() throws InputException {
        Polynomial p1 = new Polynomial("12x^10");
        Polynomial p2 = new Polynomial("6x^10");
        op = new Operation(p1, p2);
        Polynomial p3 = new Polynomial("2x^0");
        Assertions.assertTrue(op.checkPolynomialsEquals(op.divide().get(0), p3), "Division doesn't work!");
    }

    @Test
    void testDivideIncorrect() throws InputException {
        Polynomial p1 = new Polynomial("12x^10");
        Polynomial p2 = new Polynomial("6x^10");
        op = new Operation(p1, p2);
        Polynomial p3 = new Polynomial("2x^6");
        Assertions.assertFalse(op.checkPolynomialsEquals(op.divide().get(0), p3), "Division doesn't work!");
    }

    @Test
    void testDerivate() throws InputException {
        Polynomial p1 = new Polynomial("1.0x^10");
        Polynomial p2 = new Polynomial("1.0x^5");
        op = new Operation(p1, p2);
        Polynomial p3 = new Polynomial("10.0x^9");
        Assertions.assertTrue(op.checkPolynomialsEquals(op.derivate(), p3), "Derivate doesn't work!");
    }

    @Test
    void testDerivateIncorrect() throws InputException {
        Polynomial p1 = new Polynomial("1.0x^10");
        Polynomial p2 = new Polynomial("1.0x^5");
        op = new Operation(p1, p2);
        Polynomial p3 = new Polynomial("10.0x^11");
        Assertions.assertFalse(op.checkPolynomialsEquals(op.derivate(), p3), "Derivate doesn't work!");
    }

    @Test
    void testIntegrate() throws InputException {
        Polynomial p1 = new Polynomial("4x^3");
        Polynomial p2 = new Polynomial("1.0x^5");
        op = new Operation(p1, p2);
        Polynomial p3 = new Polynomial("1x^4");
        Assertions.assertTrue(op.checkPolynomialsEquals(op.integrate(), p3), "Integrate doesn't work!");
    }

    @Test
    void testIntegrateIncorrect() throws InputException {
        Polynomial p1 = new Polynomial("4x^3");
        Polynomial p2 = new Polynomial("1.0x^5");
        op = new Operation(p1, p2);
        Polynomial p3 = new Polynomial("1x^5");
        Assertions.assertFalse(op.checkPolynomialsEquals(op.integrate(), p3), "Integrate doesn't work!");
    }
}