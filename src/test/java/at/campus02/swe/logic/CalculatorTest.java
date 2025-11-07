package at.campus02.swe.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import at.campus02.swe.Calculator;
import at.campus02.swe.CalculatorException;
import at.campus02.swe.Calculator.Operation;

public class CalculatorTest {

    @Test
    public void testSimpleAddOperation() throws Exception {
        //setup
        Calculator calc = new CalculatorImpl();

        //execute
        calc.push(2.0);
        calc.push(3);
        double result = calc.perform(Operation.add);

        //verify
        assertEquals(5, result, 0);
    }

    @Test
    public void testSimpleModuloOperation() throws Exception {
        //setup
        Calculator calc = new CalculatorImpl();

        //execute
        calc.push(2.0);
        calc.push(3);
        double result = calc.perform(Operation.mod);

        //verify
        assertEquals(2, result, 0);
    }

    @Test
    public void testOperationWithTooFewOperands() throws Exception {
        Calculator calc = new CalculatorImpl();
        try {
            calc.push(5);
            calc.perform(Operation.add);

            fail("Exception expected because of too few operands");
        } catch (CalculatorException e) {
            assertEquals("Not enough numbers on stack", e.getMessage());
        }
    }


    @Test
    public void testSimpleMulOperation() throws Exception {

        Calculator calc = new CalculatorImpl();
        calc.push(2.0);
        calc.push(3);
        double result = calc.perform(Operation.mul);

        assertEquals(6, result, 0);

    }

    @Test
    public void testSimpleDivOperation() throws Exception {

        Calculator calc = new CalculatorImpl();
        calc.push(6.0);
        calc.push(2);
        double result = calc.perform(Operation.div);

        assertEquals(3, result, 0);

    }

    //
    @Test(expected = CalculatorException.class)
    public void testPopOnEmptyStack() throws Exception {

        Calculator calc = new CalculatorImpl();
        calc.pop();

    }

    @Test
    public void testDivisionByZero() throws Exception {

        //Setup
        Calculator calc = new CalculatorImpl();
        try {
            calc.push(2);
            calc.push(0);
            calc.perform(Operation.div);

            fail("Exception expected");


        } catch (CalculatorException e) {
            assertEquals("Division by zero", e.getMessage());
            // e.getCause()
        }

    }

    @Test
    public void testSimpleSinOperation() throws Exception {
        Calculator calc = new CalculatorImpl();
        calc.push(Math.PI / 2);
        double result = calc.perform(Operation.sin);
        assertEquals(1.0, result, 1e-9);
    }

    @Test
    public void testSimpleCosOperation() throws Exception {
        Calculator calc = new CalculatorImpl();
        calc.push(0);
        double result = calc.perform(Operation.cos);
        assertEquals(1.0, result, 1e-9);
    }

    @Test
    public void testSinWithTooFewOperands() throws Exception {
        Calculator calc = new CalculatorImpl();
        try {
            calc.perform(Operation.sin);
            fail("Exception expected because of too few operands");
        } catch (CalculatorException e) {
            assertEquals("Not enough numbers on stack", e.getMessage());
        }
    }

    @Test
    public void testCosWithTooFewOperands() throws Exception {
        Calculator calc = new CalculatorImpl();
        try {
            calc.perform(Operation.cos);
            fail("Exception expected because of too few operands");
        } catch (CalculatorException e) {
            assertEquals("Not enough numbers on stack", e.getMessage());
        }
    }

    @Test
    public void testDotProductTwoElements() throws CalculatorException {
        Calculator calc = new CalculatorImpl();
        calc.push(1); // a1
        calc.push(3); // a2
        calc.push(2); // b1
        calc.push(4); // b2
        calc.push(2); // n
        double result = calc.perform(Operation.dotproduct);
        assertEquals(14.0, result, 0);
    }

    @Test
    public void testDotProductThreeElements() throws CalculatorException {
        Calculator calc = new CalculatorImpl();
        calc.push(1); // a1
        calc.push(2); // a2
        calc.push(3); // a3
        calc.push(4); // b1
        calc.push(5); // b2
        calc.push(6); // b3
        calc.push(3); // n
        double result = calc.perform(Operation.dotproduct);
        assertEquals(32.0, result, 0); // 1*4 + 2*5 + 3*6 = 32
    }

    @Test
    public void testDotProductInvalidN() throws CalculatorException {
        Calculator calc = new CalculatorImpl();
        try {
            calc.push(1);
            calc.push(2);
            calc.push(-1); // invalid n
            calc.perform(Operation.dotproduct);
            fail("Expected CalculatorException for invalid vector size");
        } catch (CalculatorException e) {
            assertEquals("Vector size must be positive", e.getMessage());
        }
    }


}