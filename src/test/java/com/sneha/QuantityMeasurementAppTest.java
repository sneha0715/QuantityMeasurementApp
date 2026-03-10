package com.sneha;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testLengthEquality_SameValue() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q1 =
                new QuantityMeasurementApp.Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q2 =
                new QuantityMeasurementApp.Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testLengthEquality_DifferentValue() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q1 =
                new QuantityMeasurementApp.Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q2 =
                new QuantityMeasurementApp.Quantity<>(2.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertFalse(q1.equals(q2));
    }

    @Test
    public void testLengthEquality_FeetToInches() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> feet =
                new QuantityMeasurementApp.Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> inches =
                new QuantityMeasurementApp.Quantity<>(12.0, QuantityMeasurementApp.LengthUnit.INCHES);

        assertTrue(feet.equals(inches));
    }

    @Test
    public void testLengthEquality_YardToFeet() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> yard =
                new QuantityMeasurementApp.Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.YARDS);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> feet =
                new QuantityMeasurementApp.Quantity<>(3.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertTrue(yard.equals(feet));
    }

    @Test
    public void testLengthEquality_CentimeterToInch() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> cm =
                new QuantityMeasurementApp.Quantity<>(2.54, QuantityMeasurementApp.LengthUnit.CENTIMETERS);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> inch =
                new QuantityMeasurementApp.Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.INCHES);

        assertTrue(cm.equals(inch));
    }

    @Test
    public void testLengthConversion_FeetToInches() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> feet =
                new QuantityMeasurementApp.Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> result =
                feet.convertTo(QuantityMeasurementApp.LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), 1e-6);
    }

    @Test
    public void testLengthConversion_InchesToFeet() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> inches =
                new QuantityMeasurementApp.Quantity<>(24.0, QuantityMeasurementApp.LengthUnit.INCHES);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> result =
                inches.convertTo(QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), 1e-6);
    }

    @Test
    public void testAddition_SameUnit() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q1 =
                new QuantityMeasurementApp.Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q2 =
                new QuantityMeasurementApp.Quantity<>(2.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> result = q1.add(q2);

        assertEquals(3.0, result.getValue(), 1e-6);
    }

    @Test
    public void testAddition_CrossUnit() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> feet =
                new QuantityMeasurementApp.Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> inches =
                new QuantityMeasurementApp.Quantity<>(12.0, QuantityMeasurementApp.LengthUnit.INCHES);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> result = feet.add(inches);

        assertEquals(2.0, result.getValue(), 1e-6);
    }

    @Test
    public void testAddition_TargetUnit() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> inches36 =
                new QuantityMeasurementApp.Quantity<>(36.0, QuantityMeasurementApp.LengthUnit.INCHES);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> feet3 =
                new QuantityMeasurementApp.Quantity<>(3.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> result =
                inches36.add(feet3, QuantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(2.0, result.getValue(), 1e-6);
        assertEquals(QuantityMeasurementApp.LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void testWeightEquality_KgToGram() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.WeightUnit> kg =
                new QuantityMeasurementApp.Quantity<>(1.0, QuantityMeasurementApp.WeightUnit.KILOGRAM);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.WeightUnit> gram =
                new QuantityMeasurementApp.Quantity<>(1000.0, QuantityMeasurementApp.WeightUnit.GRAM);

        assertTrue(kg.equals(gram));
    }

    @Test
    public void testWeightConversion_KgToGram() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.WeightUnit> kg =
                new QuantityMeasurementApp.Quantity<>(1.0, QuantityMeasurementApp.WeightUnit.KILOGRAM);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.WeightUnit> result =
                kg.convertTo(QuantityMeasurementApp.WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), 1e-6);
    }

    @Test
    public void testWeightAddition_SameUnit() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.WeightUnit> w1 =
                new QuantityMeasurementApp.Quantity<>(2.0, QuantityMeasurementApp.WeightUnit.KILOGRAM);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.WeightUnit> w2 =
                new QuantityMeasurementApp.Quantity<>(3.0, QuantityMeasurementApp.WeightUnit.KILOGRAM);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.WeightUnit> result = w1.add(w2);

        assertEquals(5.0, result.getValue(), 1e-6);
    }

    @Test
    public void testWeightAddition_CrossUnit() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.WeightUnit> kg =
                new QuantityMeasurementApp.Quantity<>(1.0, QuantityMeasurementApp.WeightUnit.KILOGRAM);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.WeightUnit> grams =
                new QuantityMeasurementApp.Quantity<>(500.0, QuantityMeasurementApp.WeightUnit.GRAM);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.WeightUnit> result =
                kg.add(grams, QuantityMeasurementApp.WeightUnit.KILOGRAM);

        assertEquals(1.5, result.getValue(), 1e-6);
    }
}