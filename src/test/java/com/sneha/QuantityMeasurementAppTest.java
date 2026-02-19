package com.sneha;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {
    
    @Test
    public void testFeetEquality_SameValue() {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet feet2 = new QuantityMeasurementApp.Feet(1.0);
        assertTrue(feet1.equals(feet2));
    }
    
    @Test
    public void testFeetEquality_DifferentValue() {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet feet2 = new QuantityMeasurementApp.Feet(2.0);
        assertFalse(feet1.equals(feet2));
    }
    
    @Test
    public void testFeetEquality_NullComparison() {
        QuantityMeasurementApp.Feet feet = new QuantityMeasurementApp.Feet(1.0);
        assertFalse(feet.equals(null));
    }
    
    @Test
    public void testFeetEquality_SameReference() {
        QuantityMeasurementApp.Feet feet = new QuantityMeasurementApp.Feet(1.0);
        assertTrue(feet.equals(feet));
    }
    
    @Test
    public void testFeetEquality_DifferentType() {
        QuantityMeasurementApp.Feet feet = new QuantityMeasurementApp.Feet(1.0);
        String notFeet = "1.0";
        assertFalse(feet.equals(notFeet));
    }
    
    @Test
    public void testInchesEquality_SameValue() {
        QuantityMeasurementApp.Inches inches1 = new QuantityMeasurementApp.Inches(1.0);
        QuantityMeasurementApp.Inches inches2 = new QuantityMeasurementApp.Inches(1.0);
        assertTrue(inches1.equals(inches2));
    }
    
    @Test
    public void testInchesEquality_DifferentValue() {
        QuantityMeasurementApp.Inches inches1 = new QuantityMeasurementApp.Inches(1.0);
        QuantityMeasurementApp.Inches inches2 = new QuantityMeasurementApp.Inches(2.0);
        assertFalse(inches1.equals(inches2));
    }
    
    @Test
    public void testInchesEquality_NullComparison() {
        QuantityMeasurementApp.Inches inches = new QuantityMeasurementApp.Inches(1.0);
        assertFalse(inches.equals(null));
    }
    
    @Test
    public void testInchesEquality_SameReference() {
        QuantityMeasurementApp.Inches inches = new QuantityMeasurementApp.Inches(1.0);
        assertTrue(inches.equals(inches));
    }
    
    @Test
    public void testInchesEquality_DifferentType() {
        QuantityMeasurementApp.Inches inches = new QuantityMeasurementApp.Inches(1.0);
        String notInches = "1.0";
        assertFalse(inches.equals(notInches));
    }
    
    @Test
    public void testFeetEqualityHelper_SameValue() {
        assertTrue(QuantityMeasurementApp.checkFeetEquality(1.0, 1.0));
    }
    
    @Test
    public void testFeetEqualityHelper_DifferentValue() {
        assertFalse(QuantityMeasurementApp.checkFeetEquality(1.0, 2.0));
    }
    
    @Test
    public void testInchesEqualityHelper_SameValue() {
        assertTrue(QuantityMeasurementApp.checkInchesEquality(1.0, 1.0));
    }
    
    @Test
    public void testInchesEqualityHelper_DifferentValue() {
        assertFalse(QuantityMeasurementApp.checkInchesEquality(1.0, 2.0));
    }
    
    @Test
    public void testFeetAndInchesNotEqual() {
        QuantityMeasurementApp.Feet feet = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Inches inches = new QuantityMeasurementApp.Inches(1.0);
        assertFalse(feet.equals(inches));
    }
}
