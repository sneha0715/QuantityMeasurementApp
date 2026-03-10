package com.sneha;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testLengthEquality_FeetToInches() {

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> feet =
                new QuantityMeasurementApp.Quantity<>(1, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> inches =
                new QuantityMeasurementApp.Quantity<>(12, QuantityMeasurementApp.LengthUnit.INCHES);

        assertTrue(feet.equals(inches));
    }

    @Test
    public void testLengthConversion_FeetToInches() {

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> feet =
                new QuantityMeasurementApp.Quantity<>(1, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> result =
                feet.convertTo(QuantityMeasurementApp.LengthUnit.INCHES);

        assertEquals(12, result.getValue(), 0.001);
    }

    @Test
    public void testLengthAddition() {

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> feet =
                new QuantityMeasurementApp.Quantity<>(1, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> inches =
                new QuantityMeasurementApp.Quantity<>(12, QuantityMeasurementApp.LengthUnit.INCHES);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> result =
                feet.add(inches);

        assertEquals(2, result.getValue(), 0.001);
    }


    @Test
    public void testWeightEquality() {

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.WeightUnit> kg =
                new QuantityMeasurementApp.Quantity<>(1, QuantityMeasurementApp.WeightUnit.KILOGRAM);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.WeightUnit> gram =
                new QuantityMeasurementApp.Quantity<>(1000, QuantityMeasurementApp.WeightUnit.GRAM);

        assertTrue(kg.equals(gram));
    }

    @Test
    public void testWeightConversion() {

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.WeightUnit> kg =
                new QuantityMeasurementApp.Quantity<>(1, QuantityMeasurementApp.WeightUnit.KILOGRAM);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.WeightUnit> result =
                kg.convertTo(QuantityMeasurementApp.WeightUnit.GRAM);

        assertEquals(1000, result.getValue(), 0.001);
    }

    @Test
    public void testWeightAddition() {

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.WeightUnit> kg =
                new QuantityMeasurementApp.Quantity<>(1, QuantityMeasurementApp.WeightUnit.KILOGRAM);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.WeightUnit> grams =
                new QuantityMeasurementApp.Quantity<>(500, QuantityMeasurementApp.WeightUnit.GRAM);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.WeightUnit> result =
                kg.add(grams, QuantityMeasurementApp.WeightUnit.KILOGRAM);

        assertEquals(1.5, result.getValue(), 0.001);
    }


    @Test
    public void testVolumeEquality() {

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.VolumeUnit> litre =
                new QuantityMeasurementApp.Quantity<>(1, QuantityMeasurementApp.VolumeUnit.LITRE);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.VolumeUnit> ml =
                new QuantityMeasurementApp.Quantity<>(1000, QuantityMeasurementApp.VolumeUnit.MILLILITRE);

        assertTrue(litre.equals(ml));
    }

    @Test
    public void testVolumeConversion() {

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.VolumeUnit> litre =
                new QuantityMeasurementApp.Quantity<>(1, QuantityMeasurementApp.VolumeUnit.LITRE);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.VolumeUnit> result =
                litre.convertTo(QuantityMeasurementApp.VolumeUnit.MILLILITRE);

        assertEquals(1000, result.getValue(), 0.001);
    }

    @Test
    public void testVolumeAddition() {

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.VolumeUnit> litre =
                new QuantityMeasurementApp.Quantity<>(1, QuantityMeasurementApp.VolumeUnit.LITRE);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.VolumeUnit> ml =
                new QuantityMeasurementApp.Quantity<>(1000, QuantityMeasurementApp.VolumeUnit.MILLILITRE);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.VolumeUnit> result =
                litre.add(ml);

        assertEquals(2, result.getValue(), 0.001);
    }

    @Test
    public void testDifferentMeasurementCategoriesNotEqual() {

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> length =
                new QuantityMeasurementApp.Quantity<>(1, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.WeightUnit> weight =
                new QuantityMeasurementApp.Quantity<>(1, QuantityMeasurementApp.WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }
}