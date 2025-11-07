package at.campus02.swe.parser;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

import at.campus02.swe.CalculatorException;
import at.campus02.swe.logic.CalculatorImpl;
import org.junit.Test;

import static org.mockito.Mockito.*;

import at.campus02.swe.Calculator;
import at.campus02.swe.Calculator.Operation;

import javax.xml.stream.XMLStreamException;


public class ParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNullParser() {
        new Parser(null);
    }

    @Test(expected = FileNotFoundException.class)
    public void testParserInvalidFile() throws Exception {

        Calculator cal = mock(Calculator.class);

        Parser parser = new Parser(cal);
        parser.parse(new File("invalid"));
    }

    @Test
    public void testParserTest01Xml() throws Exception {

        Calculator cal = mock(Calculator.class);

        Parser parser = new Parser(cal);
        parser.parse(new File("src/test/resources/test01.xml"));

        verify(cal).push(1.0);
        verify(cal).push(2.0);
        verify(cal).perform(Operation.add);

        verifyNoMoreInteractions(cal);
    }

    @Test
    public void testParserTest02Xml() throws Exception {

        Calculator cal = mock(Calculator.class);

        Parser parser = new Parser(cal);
        parser.parse(new File("src/test/resources/test02.xml"));

        verify(cal).push(1.0);
        verify(cal).push(2.0);
        verify(cal).perform(Operation.mod);

        verifyNoMoreInteractions(cal);
    }

    @Test
    public void testParserDotProductTwoElements() throws CalculatorException, XMLStreamException, FileNotFoundException {
        Calculator calc = new CalculatorImpl();
        Parser parser = new Parser(calc);
        double result = parser.parse(new File("src/test/resources/dotProduct/dotproduct_2.xml"));
        assertEquals(14.0, result, 0);
    }

    @Test
    public void testParserDotProductThreeElements() throws CalculatorException, XMLStreamException, FileNotFoundException {
        Calculator calc = new CalculatorImpl();
        Parser parser = new Parser(calc);
        double result = parser.parse(new File("src/test/resources/dotProduct/dotproduct_3.xml"));
        assertEquals(32.0, result, 0);
    }

    @Test(expected = CalculatorException.class)
    public void testParserDotProductInvalidN() throws CalculatorException, XMLStreamException, FileNotFoundException {
        Calculator calc = new CalculatorImpl();
        Parser parser = new Parser(calc);
        parser.parse(new File("src/test/resources/dotProduct/dotproduct_invalid.xml"));
  }
}