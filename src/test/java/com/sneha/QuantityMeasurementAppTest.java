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
    
    @Test
    public void testQuantityEquality_YardToYard_SameValue() {
        QuantityMeasurementApp.Quantity yard1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        QuantityMeasurementApp.Quantity yard2 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        assertTrue(yard1.equals(yard2));
    }
    
    @Test
    public void testQuantityEquality_YardToYard_DifferentValue() {
        QuantityMeasurementApp.Quantity yard1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        QuantityMeasurementApp.Quantity yard2 = new QuantityMeasurementApp.Quantity(2.0, QuantityMeasurementApp.LengthUnit.YARDS);
        assertFalse(yard1.equals(yard2));
    }
    
    @Test
    public void testQuantityEquality_YardToFeet_EquivalentValue() {
        QuantityMeasurementApp.Quantity yard = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        QuantityMeasurementApp.Quantity feet = new QuantityMeasurementApp.Quantity(3.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertTrue(yard.equals(feet));
    }
    
    @Test
    public void testQuantityEquality_FeetToYard_EquivalentValue() {
        QuantityMeasurementApp.Quantity feet = new QuantityMeasurementApp.Quantity(3.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.Quantity yard = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        assertTrue(feet.equals(yard));
    }
    
    @Test
    public void testQuantityEquality_YardToInches_EquivalentValue() {
        QuantityMeasurementApp.Quantity yard = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        QuantityMeasurementApp.Quantity inches = new QuantityMeasurementApp.Quantity(36.0, QuantityMeasurementApp.LengthUnit.INCHES);
        assertTrue(yard.equals(inches));
    }
    
    @Test
    public void testQuantityEquality_InchesToYard_EquivalentValue() {
        QuantityMeasurementApp.Quantity inches = new QuantityMeasurementApp.Quantity(36.0, QuantityMeasurementApp.LengthUnit.INCHES);
        QuantityMeasurementApp.Quantity yard = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        assertTrue(inches.equals(yard));
    }
    
    @Test
    public void testQuantityEquality_YardToFeet_NonEquivalentValue() {
        QuantityMeasurementApp.Quantity yard = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        QuantityMeasurementApp.Quantity feet = new QuantityMeasurementApp.Quantity(2.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertFalse(yard.equals(feet));
    }
    
    @Test
    public void testQuantityEquality_CentimetersToCentimeters_SameValue() {
        QuantityMeasurementApp.Quantity cm1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        QuantityMeasurementApp.Quantity cm2 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        assertTrue(cm1.equals(cm2));
    }
    
    @Test
    public void testQuantityEquality_CentimetersToCentimeters_DifferentValue() {
        QuantityMeasurementApp.Quantity cm1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        QuantityMeasurementApp.Quantity cm2 = new QuantityMeasurementApp.Quantity(2.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        assertFalse(cm1.equals(cm2));
    }
    
    @Test
    public void testQuantityEquality_CentimetersToInches_EquivalentValue() {
        QuantityMeasurementApp.Quantity cm = new QuantityMeasurementApp.Quantity(2.54, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        QuantityMeasurementApp.Quantity inches = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.INCHES);
        assertTrue(cm.equals(inches));
    }
    
    @Test
    public void testQuantityEquality_InchesToCentimeters_EquivalentValue() {
        QuantityMeasurementApp.Quantity inches = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.INCHES);
        QuantityMeasurementApp.Quantity cm = new QuantityMeasurementApp.Quantity(2.54, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        assertTrue(inches.equals(cm));
    }
    
    @Test
    public void testQuantityEquality_YardSameReference() {
        QuantityMeasurementApp.Quantity yard = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        assertTrue(yard.equals(yard));
    }
    
    @Test
    public void testQuantityEquality_YardNullComparison() {
        QuantityMeasurementApp.Quantity yard = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        assertFalse(yard.equals(null));
    }
    
    @Test
    public void testQuantityEquality_CentimetersSameReference() {
        QuantityMeasurementApp.Quantity cm = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        assertTrue(cm.equals(cm));
    }
    
    @Test
    public void testQuantityEquality_CentimetersNullComparison() {
        QuantityMeasurementApp.Quantity cm = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        assertFalse(cm.equals(null));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testQuantityEquality_YardWithNullUnit() {
        new QuantityMeasurementApp.Quantity(1.0, null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testQuantityEquality_CentimetersWithNullUnit() {
        new QuantityMeasurementApp.Quantity(1.0, null);
    }
    
    @Test
    public void testQuantityEquality_MultiUnit_TransitiveProperty() {
        QuantityMeasurementApp.Quantity yard = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        QuantityMeasurementApp.Quantity feet = new QuantityMeasurementApp.Quantity(3.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.Quantity inches = new QuantityMeasurementApp.Quantity(36.0, QuantityMeasurementApp.LengthUnit.INCHES);
        
        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches));
    }
    
    @Test
    public void testQuantityEquality_MultiUnit_ComplexScenario() {
        QuantityMeasurementApp.Quantity yards2 = new QuantityMeasurementApp.Quantity(2.0, QuantityMeasurementApp.LengthUnit.YARDS);
        QuantityMeasurementApp.Quantity feet6 = new QuantityMeasurementApp.Quantity(6.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.Quantity inches72 = new QuantityMeasurementApp.Quantity(72.0, QuantityMeasurementApp.LengthUnit.INCHES);
        
        assertTrue(yards2.equals(feet6));
        assertTrue(feet6.equals(inches72));
        assertTrue(yards2.equals(inches72));
    }
    
    @Test
    public void testQuantityEquality_YardDifferentType() {
        QuantityMeasurementApp.Quantity yard = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        String notQuantity = "1.0";
        assertFalse(yard.equals(notQuantity));
    }
    
    @Test
    public void testQuantityEquality_CentimetersDifferentType() {
        QuantityMeasurementApp.Quantity cm = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        String notQuantity = "1.0";
        assertFalse(cm.equals(notQuantity));
    }
    
    @Test
    public void testQuantityEquality_CentimetersToFeet_NonEquivalent() {
        QuantityMeasurementApp.Quantity cm = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        QuantityMeasurementApp.Quantity feet = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertFalse(cm.equals(feet));
    }
}
