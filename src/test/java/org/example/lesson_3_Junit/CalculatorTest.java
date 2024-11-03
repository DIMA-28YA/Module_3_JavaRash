package org.example.lesson_3_Junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator calculator;

    @BeforeEach
    void inti() {
        calculator = new Calculator();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void givenTwoSmallNumbers_WhenSumming_ThenGetResult() throws CalculationException {
        int result = calculator.add(1,5);
        assertEquals(6,result);
    }

    @Test
    void add_ShouldThrowException_WhenAddPositiveBounder() throws CalculationException {
//        assertThrows(CalculationException.class, () ->) {
//            calculator.add(Integer.MAX_VALUE, 8);
//        }
    }
    @Test
    void sub() throws CalculationException {
        int result = calculator.add(10,10);
        assertEquals(0,result);
    }

    @Test
    void mul() throws CalculationException {
        int result = calculator.add(-5,-3);
        assertEquals(15,result);
    }

    @Test
    void diy() throws CalculationException {
        int result = calculator.add(2,3);
        assertEquals(0,result);
    }
}