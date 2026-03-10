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

        Quantity<LengthUnit> length1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(12, LengthUnit.INCHES);

        System.out.println(length1 + " == " + length2 + " -> " + length1.equals(length2));
        System.out.println("Conversion: " + length1 + " = " + length1.convertTo(LengthUnit.INCHES));
        System.out.println("Addition: " + length1 + " + " + length2 + " = " + length1.add(length2));

        Quantity<WeightUnit> weight1 = new Quantity<>(1, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> weight2 = new Quantity<>(1000, WeightUnit.GRAM);

        System.out.println(weight1 + " == " + weight2 + " -> " + weight1.equals(weight2));
        System.out.println("Conversion: " + weight1 + " = " + weight1.convertTo(WeightUnit.GRAM));
        System.out.println("Addition: " + weight1 + " + " + weight2 + " = " + weight1.add(weight2));

        Quantity<VolumeUnit> volume1 = new Quantity<>(1, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume2 = new Quantity<>(1000, VolumeUnit.MILLILITRE);

        System.out.println(volume1 + " == " + volume2 + " -> " + volume1.equals(volume2));
        System.out.println("Conversion: " + volume1 + " = " + volume1.convertTo(VolumeUnit.MILLILITRE));
        System.out.println("Addition: " + volume1 + " + " + volume2 + " = " + volume1.add(volume2));
    }
}