package at.campus02.swe.E2E;

import at.campus02.swe.Calculator;
import at.campus02.swe.CalculatorException;
import at.campus02.swe.logic.CalculatorImpl;
import at.campus02.swe.parser.Parser;
import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class CalculatorEndToEndTest {

    @Test
    public void testEnd2EndSubtract() throws CalculatorException, XMLStreamException, FileNotFoundException {
        Calculator calc = new CalculatorImpl();
        Parser parser = new Parser(calc);

        double result = parser.parse(new File("src/test/resources/test03.xml"));

        assertEquals(2.0, result, 0);
    }

    @Test
    public void testEnd2EndAddAndMultiply() throws CalculatorException, XMLStreamException, FileNotFoundException {
        Calculator calc = new CalculatorImpl();
        Parser parser = new Parser(calc);
        double result = parser.parse(new File("src/test/resources/test04.xml"));
        assertEquals(13.0, result, 0);
    }

    @Test
    public void testEnd2EndDotProductFourElements() throws CalculatorException, XMLStreamException, FileNotFoundException {
        Calculator calc = new CalculatorImpl();
        Parser parser = new Parser(calc);

        double result = parser.parse(new File("src/test/resources/dotProduct/dotproduct_end2end.xml"));

        // Erwartetes Ergebnis: 1*2 + 3*4 + 5*6 + 7*8 = 2 + 12 + 30 + 56 = 100
        assertEquals(100.0, result, 0);
    }


}
