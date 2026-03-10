package com.sneha;

import java.util.Objects;

public class QuantityMeasurementApp {

    public interface IMeasurable {
        double getConversionFactor();

        default double toBase(double value) {
            return value * getConversionFactor();
        }

        default double fromBase(double baseValue) {
            return baseValue / getConversionFactor();
        }

        String getUnitName();
    }

    public enum LengthUnit implements IMeasurable {
        FEET(12),
        INCHES(1),
        YARDS(36),
        CENTIMETERS(0.3937);

        private final double factor;

        LengthUnit(double factor) {
            this.factor = factor;
        }

        public double getConversionFactor() {
            return factor;
        }

        public String getUnitName() {
            return name();
        }
    }

    public enum WeightUnit implements IMeasurable {
        KILOGRAM(1000),
        GRAM(1),
        TONNE(1000000);

        private final double factor;

        WeightUnit(double factor) {
            this.factor = factor;
        }

        public double getConversionFactor() {
            return factor;
        }

        public String getUnitName() {
            return name();
        }
    }

    public static class Quantity<U extends IMeasurable> {

        private final double value;
        private final U unit;

        public Quantity(double value, U unit) {
            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            if (Double.isNaN(value) || Double.isInfinite(value))
                throw new IllegalArgumentException("Invalid value");

            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public U getUnit() {
            return unit;
        }

        public Quantity<U> convertTo(U targetUnit) {
            double base = unit.toBase(value);
            double result = targetUnit.fromBase(base);
            return new Quantity<>(round(result), targetUnit);
        }

        public Quantity<U> add(Quantity<U> other) {
            return add(other, this.unit);
        }

        public Quantity<U> add(Quantity<U> other, U targetUnit) {

            if (!unit.getClass().equals(other.unit.getClass()))
                throw new IllegalArgumentException("Different measurement categories");

            double base1 = unit.toBase(value);
            double base2 = other.unit.toBase(other.value);

            double sumBase = base1 + base2;

            double result = targetUnit.fromBase(sumBase);

            return new Quantity<>(round(result), targetUnit);
        }

        private double round(double value) {
            return Math.round(value * 100.0) / 100.0;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null || !(obj instanceof Quantity))
                return false;

            Quantity<?> other = (Quantity<?>) obj;

            if (!unit.getClass().equals(other.unit.getClass()))
                return false;

            double base1 = unit.toBase(value);
            double base2 = other.unit.toBase(other.value);

            return Double.compare(base1, base2) == 0;
        }

        @Override
        public int hashCode() {
            double base = unit.toBase(value);
            return Objects.hash(base, unit.getClass());
        }

        @Override
        public String toString() {
            return value + " " + unit.getUnitName();
        }
    }

    public static void demonstrateEquality(Quantity<?> q1, Quantity<?> q2) {
        System.out.println("Equality: " + q1 + " == " + q2 + " -> " + q1.equals(q2));
    }

    public static <U extends IMeasurable> void demonstrateConversion(Quantity<U> q, U target) {
        System.out.println("Conversion: " + q + " -> " + q.convertTo(target));
    }

    public static <U extends IMeasurable> void demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U target) {
        System.out.println("Addition: " + q1 + " + " + q2 + " = " + q1.add(q2, target));
    }

    public static void main(String[] args) {

        Quantity<LengthUnit> length1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(12.0, LengthUnit.INCHES);

        demonstrateEquality(length1, length2);
        demonstrateConversion(length1, LengthUnit.INCHES);
        demonstrateAddition(length1, length2, LengthUnit.FEET);

        Quantity<WeightUnit> weight1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> weight2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        demonstrateEquality(weight1, weight2);
        demonstrateConversion(weight1, WeightUnit.GRAM);
        demonstrateAddition(weight1, weight2, WeightUnit.KILOGRAM);
    }
}