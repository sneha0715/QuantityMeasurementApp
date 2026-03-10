package com.sneha;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testLengthEquality_FeetInches() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q1 =
                new QuantityMeasurementApp.Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q2 =
                new QuantityMeasurementApp.Quantity<>(12.0, QuantityMeasurementApp.LengthUnit.INCHES);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testLengthAddition() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q1 =
                new QuantityMeasurementApp.Quantity<>(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> q2 =
                new QuantityMeasurementApp.Quantity<>(12.0, QuantityMeasurementApp.LengthUnit.INCHES);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> result = q1.add(q2);
        assertEquals(2.0, result.convertTo(QuantityMeasurementApp.LengthUnit.FEET).getValue(), 0.0001);
    }

    @Test
    void testWeightEquality_KgGram() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.WeightUnit> w1 =
                new QuantityMeasurementApp.Quantity<>(1.0, QuantityMeasurementApp.WeightUnit.KILOGRAM);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.WeightUnit> w2 =
                new QuantityMeasurementApp.Quantity<>(1000.0, QuantityMeasurementApp.WeightUnit.GRAM);
        assertTrue(w1.equals(w2));
    }

    @Test
    void testVolumeEquality_LitreMilliLitre() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.VolumeUnit> v1 =
                new QuantityMeasurementApp.Quantity<>(1.0, QuantityMeasurementApp.VolumeUnit.LITRE);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.VolumeUnit> v2 =
                new QuantityMeasurementApp.Quantity<>(1000.0, QuantityMeasurementApp.VolumeUnit.MILLILITRE);
        assertTrue(v1.equals(v2));
    }

    @Test
    void testTemperatureEquality_CelsiusToFahrenheit() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.TemperatureUnit> t1 =
                new QuantityMeasurementApp.Quantity<>(0.0, QuantityMeasurementApp.TemperatureUnit.CELSIUS);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.TemperatureUnit> t2 =
                new QuantityMeasurementApp.Quantity<>(32.0, QuantityMeasurementApp.TemperatureUnit.FAHRENHEIT);
        assertTrue(t1.equals(t2));
    }

    @Test
    void testTemperatureEquality_CelsiusToFahrenheit_100() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.TemperatureUnit> t1 =
                new QuantityMeasurementApp.Quantity<>(100.0, QuantityMeasurementApp.TemperatureUnit.CELSIUS);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.TemperatureUnit> t2 =
                new QuantityMeasurementApp.Quantity<>(212.0, QuantityMeasurementApp.TemperatureUnit.FAHRENHEIT);
        assertTrue(t1.equals(t2));
    }

    @Test
    void testTemperatureEquality_FahrenheitToCelsius_Negative40() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.TemperatureUnit> t1 =
                new QuantityMeasurementApp.Quantity<>(-40.0, QuantityMeasurementApp.TemperatureUnit.CELSIUS);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.TemperatureUnit> t2 =
                new QuantityMeasurementApp.Quantity<>(-40.0, QuantityMeasurementApp.TemperatureUnit.FAHRENHEIT);
        assertTrue(t1.equals(t2));
    }

    @Test
    void testTemperatureConversion_CelsiusToFahrenheit() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.TemperatureUnit> t1 =
                new QuantityMeasurementApp.Quantity<>(100.0, QuantityMeasurementApp.TemperatureUnit.CELSIUS);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.TemperatureUnit> result =
                t1.convertTo(QuantityMeasurementApp.TemperatureUnit.FAHRENHEIT);
        assertEquals(212.0, result.getValue(), 0.0001);
    }

    @Test
    void testTemperatureConversion_FahrenheitToCelsius() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.TemperatureUnit> t1 =
                new QuantityMeasurementApp.Quantity<>(32.0, QuantityMeasurementApp.TemperatureUnit.FAHRENHEIT);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.TemperatureUnit> result =
                t1.convertTo(QuantityMeasurementApp.TemperatureUnit.CELSIUS);
        assertEquals(0.0, result.getValue(), 0.0001);
    }

    @Test
    void testTemperatureUnsupported_Addition() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.TemperatureUnit> t1 =
                new QuantityMeasurementApp.Quantity<>(100.0, QuantityMeasurementApp.TemperatureUnit.CELSIUS);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.TemperatureUnit> t2 =
                new QuantityMeasurementApp.Quantity<>(50.0, QuantityMeasurementApp.TemperatureUnit.CELSIUS);
        assertThrows(UnsupportedOperationException.class, () -> t1.add(t2));
    }

    @Test
    void testTemperatureUnsupported_Subtraction() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.TemperatureUnit> t1 =
                new QuantityMeasurementApp.Quantity<>(100.0, QuantityMeasurementApp.TemperatureUnit.CELSIUS);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.TemperatureUnit> t2 =
                new QuantityMeasurementApp.Quantity<>(50.0, QuantityMeasurementApp.TemperatureUnit.CELSIUS);
        assertThrows(UnsupportedOperationException.class, () -> t1.subtract(t2));
    }

    @Test
    void testCrossCategory_Prevention() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.TemperatureUnit> t =
                new QuantityMeasurementApp.Quantity<>(50.0, QuantityMeasurementApp.TemperatureUnit.CELSIUS);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.LengthUnit> l =
                new QuantityMeasurementApp.Quantity<>(50.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertFalse(((Object) t).equals((Object) l));
    }

    @Test
    void testNullUnitValidation() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityMeasurementApp.Quantity<>(100.0, null));
    }

    @Test
    void testReflexiveEquality() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.TemperatureUnit> t =
                new QuantityMeasurementApp.Quantity<>(100.0, QuantityMeasurementApp.TemperatureUnit.CELSIUS);
        assertTrue(t.equals(t));
    }

    @Test
    void testSymmetricEquality() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.TemperatureUnit> t1 =
                new QuantityMeasurementApp.Quantity<>(0.0, QuantityMeasurementApp.TemperatureUnit.CELSIUS);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.TemperatureUnit> t2 =
                new QuantityMeasurementApp.Quantity<>(32.0, QuantityMeasurementApp.TemperatureUnit.FAHRENHEIT);
        assertTrue(t1.equals(t2));
        assertTrue(t2.equals(t1));
    }

    @Test
    void testTransitiveEquality() {
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.TemperatureUnit> t1 =
                new QuantityMeasurementApp.Quantity<>(0.0, QuantityMeasurementApp.TemperatureUnit.CELSIUS);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.TemperatureUnit> t2 =
                new QuantityMeasurementApp.Quantity<>(32.0, QuantityMeasurementApp.TemperatureUnit.FAHRENHEIT);
        QuantityMeasurementApp.Quantity<QuantityMeasurementApp.TemperatureUnit> t3 =
                new QuantityMeasurementApp.Quantity<>(273.15, QuantityMeasurementApp.TemperatureUnit.KELVIN);
        assertTrue(t1.equals(t2));
        assertTrue(t2.equals(t3));
        assertTrue(t1.equals(t3));
    }
}