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

    public enum VolumeUnit implements IMeasurable {
        LITRE(1000),
        MILLILITRE(1),
        GALLON(3785.41);

        private final double factor;

        VolumeUnit(double factor) {
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

            if (other == null || targetUnit == null)
                throw new IllegalArgumentException();

            if (!unit.getClass().equals(other.unit.getClass()))
                throw new IllegalArgumentException();

            double base1 = unit.toBase(value);
            double base2 = other.unit.toBase(other.value);

            double sumBase = base1 + base2;

            double result = targetUnit.fromBase(sumBase);

            return new Quantity<>(round(result), targetUnit);
        }

        public Quantity<U> subtract(Quantity<U> other) {
            return subtract(other, this.unit);
        }

        public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

            if (other == null || targetUnit == null)
                throw new IllegalArgumentException();

            if (!unit.getClass().equals(other.unit.getClass()))
                throw new IllegalArgumentException();

            double base1 = unit.toBase(value);
            double base2 = other.unit.toBase(other.value);

            double diffBase = base1 - base2;

            double result = targetUnit.fromBase(diffBase);

            return new Quantity<>(round(result), targetUnit);
        }

        public double divide(Quantity<U> other) {

            if (other == null)
                throw new IllegalArgumentException();

            if (!unit.getClass().equals(other.unit.getClass()))
                throw new IllegalArgumentException();

            double base1 = unit.toBase(value);
            double base2 = other.unit.toBase(other.value);

            if (base2 == 0)
                throw new ArithmeticException("Division by zero");

            return base1 / base2;
        }

        private double round(double value) {
            return Math.round(value * 100.0) / 100.0;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (!(obj instanceof Quantity))
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

    public static void main(String[] args) {

        Quantity<LengthUnit> l1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(6, LengthUnit.INCHES);

        System.out.println(l1.subtract(l2));
        System.out.println(l1.subtract(l2, LengthUnit.INCHES));
        System.out.println(l1.divide(new Quantity<>(2, LengthUnit.FEET)));

        Quantity<WeightUnit> w1 = new Quantity<>(10, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(5000, WeightUnit.GRAM);

        System.out.println(w1.subtract(w2));
        System.out.println(w1.subtract(w2, WeightUnit.GRAM));
        System.out.println(w1.divide(new Quantity<>(5, WeightUnit.KILOGRAM)));

        Quantity<VolumeUnit> v1 = new Quantity<>(5, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(500, VolumeUnit.MILLILITRE);

        System.out.println(v1.subtract(v2));
        System.out.println(v1.subtract(v2, VolumeUnit.MILLILITRE));
        System.out.println(v1.divide(new Quantity<>(10, VolumeUnit.LITRE)));
    }
}