package com.sneha;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testAdd_Length_FeetAndInches() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q1 =
                new QuantityMeasurementApp.Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q2 =
                new QuantityMeasurementApp.Quantity<>(12.0, QuantityMeasurementApp.LengthUnit.INCHES);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> result = q1.add(q2);

        assertEquals(new QuantityMeasurementApp.Quantity<>(2.0, QuantityMeasurementApp.LengthUnit.FEET), result);
    }

    @Test
    void testAdd_Weight_KgAndGram_TargetGram() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.WeightUnit> q1 =
                new QuantityMeasurementApp.Quantity<>(10.0, QuantityMeasurementApp.WeightUnit.KILOGRAM);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.WeightUnit> q2 =
                new QuantityMeasurementApp.Quantity<>(5000.0, QuantityMeasurementApp.WeightUnit.GRAM);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.WeightUnit> result =
                q1.add(q2, QuantityMeasurementApp.WeightUnit.GRAM);

        assertEquals(new QuantityMeasurementApp.Quantity<>(15000.0, QuantityMeasurementApp.WeightUnit.GRAM), result);
    }

    @Test
    void testSubtract_Length() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q1 =
                new QuantityMeasurementApp.Quantity<>(10.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q2 =
                new QuantityMeasurementApp.Quantity<>(6.0, QuantityMeasurementApp.LengthUnit.INCHES);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> result = q1.subtract(q2);

        assertEquals(new QuantityMeasurementApp.Quantity<>(9.5, QuantityMeasurementApp.LengthUnit.FEET), result);
    }

    @Test
    void testSubtract_Volume_TargetMillilitre() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.VolumeUnit> q1 =
                new QuantityMeasurementApp.Quantity<>(5.0, QuantityMeasurementApp.VolumeUnit.LITRE);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.VolumeUnit> q2 =
                new QuantityMeasurementApp.Quantity<>(2.0, QuantityMeasurementApp.VolumeUnit.LITRE);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.VolumeUnit> result =
                q1.subtract(q2, QuantityMeasurementApp.VolumeUnit.MILLILITRE);

        assertEquals(new QuantityMeasurementApp.Quantity<>(3000.0, QuantityMeasurementApp.VolumeUnit.MILLILITRE), result);
    }

    @Test
    void testDivide_Length() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q1 =
                new QuantityMeasurementApp.Quantity<>(10.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q2 =
                new QuantityMeasurementApp.Quantity<>(2.0, QuantityMeasurementApp.LengthUnit.FEET);

        double result = q1.divide(q2);

        assertEquals(5.0, result);
    }

    @Test
    void testDivide_CrossUnits() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q1 =
                new QuantityMeasurementApp.Quantity<>(24.0, QuantityMeasurementApp.LengthUnit.INCHES);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q2 =
                new QuantityMeasurementApp.Quantity<>(2.0, QuantityMeasurementApp.LengthUnit.FEET);

        double result = q1.divide(q2);

        assertEquals(1.0, result);
    }

    @Test
    void testAdd_NullOperand_ThrowsException() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q1 =
                new QuantityMeasurementApp.Quantity<>(10.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> q1.add(null));
    }

    @Test
    void testSubtract_CrossCategory_ThrowsException() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> length =
                new QuantityMeasurementApp.Quantity<>(10.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.WeightUnit> weight =
                new QuantityMeasurementApp.Quantity<>(5.0, QuantityMeasurementApp.WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class, () -> length.subtract((QuantityMeasurementApp.Quantity) weight));
    }

    @Test
    void testDivide_ByZero() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q1 =
                new QuantityMeasurementApp.Quantity<>(10.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q2 =
                new QuantityMeasurementApp.Quantity<>(0.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertThrows(ArithmeticException.class, () -> q1.divide(q2));
    }

    @Test
    void testAdd_RoundingToTwoDecimals() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q1 =
                new QuantityMeasurementApp.Quantity<>(1.333, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q2 =
                new QuantityMeasurementApp.Quantity<>(1.111, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> result = q1.add(q2);

        assertEquals(new QuantityMeasurementApp.Quantity<>(2.44, QuantityMeasurementApp.LengthUnit.FEET), result);
    }

    @Test
    void testImmutability_AfterAddition() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q1 =
                new QuantityMeasurementApp.Quantity<>(5.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q2 =
                new QuantityMeasurementApp.Quantity<>(5.0, QuantityMeasurementApp.LengthUnit.FEET);

        q1.add(q2);

        assertEquals(new QuantityMeasurementApp.Quantity<>(5.0, QuantityMeasurementApp.LengthUnit.FEET), q1);
        assertEquals(new QuantityMeasurementApp.Quantity<>(5.0, QuantityMeasurementApp.LengthUnit.FEET), q2);
    }

    @Test
    void testImplicitTargetUnit_Add() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q1 =
                new QuantityMeasurementApp.Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q2 =
                new QuantityMeasurementApp.Quantity<>(12.0, QuantityMeasurementApp.LengthUnit.INCHES);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> result = q1.add(q2);

        assertEquals(QuantityMeasurementApp.LengthUnit.FEET,
                result.toString().contains("FEET") ? QuantityMeasurementApp.LengthUnit.FEET : null);
    }

    @Test
    void testExplicitTargetUnit_Add() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q1 =
                new QuantityMeasurementApp.Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q2 =
                new QuantityMeasurementApp.Quantity<>(12.0, QuantityMeasurementApp.LengthUnit.INCHES);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> result =
                q1.add(q2, QuantityMeasurementApp.LengthUnit.INCHES);

        assertEquals(new QuantityMeasurementApp.Quantity<>(24.0, QuantityMeasurementApp.LengthUnit.INCHES), result);
    }
}