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
                QuantityMeasurementApp.Quantity yard1 = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.YARDS);
                QuantityMeasurementApp.Quantity yard2 = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.YARDS);
                assertTrue(yard1.equals(yard2));
        }

        @Test
        public void testQuantityEquality_YardToFeet_EquivalentValue() {
                QuantityMeasurementApp.Quantity yard = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.YARDS);
                QuantityMeasurementApp.Quantity feet = new QuantityMeasurementApp.Quantity(3.0, LengthUnit.FEET);
                assertTrue(yard.equals(feet));
        }

        @Test
        public void testQuantityEquality_YardToInches_EquivalentValue() {
                QuantityMeasurementApp.Quantity yard = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.YARDS);
                QuantityMeasurementApp.Quantity inches = new QuantityMeasurementApp.Quantity(36.0, LengthUnit.INCHES);
                assertTrue(yard.equals(inches));
        }

        @Test
        public void testQuantityEquality_CentimetersToInches_EquivalentValue() {
                QuantityMeasurementApp.Quantity cm = new QuantityMeasurementApp.Quantity(2.54, LengthUnit.CENTIMETERS);
                QuantityMeasurementApp.Quantity inches = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.INCHES);
                assertTrue(cm.equals(inches));
        }

        @Test
        public void testConversion_FeetToInches() {
                double result = QuantityMeasurementApp.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES);
                assertEquals(12.0, result, 1e-6);
        }

        @Test
        public void testConversion_InchesToFeet() {
                double result = QuantityMeasurementApp.convert(24.0, LengthUnit.INCHES, LengthUnit.FEET);
                assertEquals(2.0, result, 1e-6);
        }

        @Test
        public void testConversion_YardsToFeet() {
                double result = QuantityMeasurementApp.convert(1.0, LengthUnit.YARDS, LengthUnit.FEET);
                assertEquals(3.0, result, 1e-6);
        }

        @Test
        public void testConversion_CentimetersToInches() {
                double result = QuantityMeasurementApp.convert(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCHES);
                assertEquals(1.0, result, 1e-4);
        }

        @Test
        public void testQuantityConvertTo_FeetToInches() {
                QuantityMeasurementApp.Quantity feet = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.FEET);
                double result = feet.convertTo(LengthUnit.INCHES);
                assertEquals(12.0, result, 1e-6);
        }

        @Test
        public void testQuantityConvertToQuantity_FeetToYards() {
                QuantityMeasurementApp.Quantity feet = new QuantityMeasurementApp.Quantity(9.0, LengthUnit.FEET);
                QuantityMeasurementApp.Quantity result = feet.convertToQuantity(LengthUnit.YARDS);
                assertEquals(3.0, result.getValue(), 1e-6);
                assertEquals(LengthUnit.YARDS, result.getUnit());
        }

        @Test
        public void testAddition_SameUnit() {
                QuantityMeasurementApp.Quantity q1 = new QuantityMeasurementApp.Quantity(1, LengthUnit.FEET);
                QuantityMeasurementApp.Quantity q2 = new QuantityMeasurementApp.Quantity(2, LengthUnit.FEET);
                QuantityMeasurementApp.Quantity result = q1.add(q2);
                assertEquals(3.0, result.getValue(), 1e-6);
        }

        @Test
        public void testAddition_CrossUnit() {
                QuantityMeasurementApp.Quantity feet = new QuantityMeasurementApp.Quantity(1, LengthUnit.FEET);
                QuantityMeasurementApp.Quantity inches = new QuantityMeasurementApp.Quantity(12, LengthUnit.INCHES);
                QuantityMeasurementApp.Quantity result = feet.add(inches);
                assertEquals(2.0, result.getValue(), 1e-6);
        }

        @Test
        public void testAddition_TargetUnit() {
                QuantityMeasurementApp.Quantity inches36 = new QuantityMeasurementApp.Quantity(36, LengthUnit.INCHES);
                QuantityMeasurementApp.Quantity feet3 = new QuantityMeasurementApp.Quantity(3, LengthUnit.FEET);
                QuantityMeasurementApp.Quantity result = inches36.add(feet3, LengthUnit.YARDS);
                assertEquals(2.0, result.getValue(), 1e-6);
                assertEquals(LengthUnit.YARDS, result.getUnit());
        }
}