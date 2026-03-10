package com.sneha;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class QuantityMeasurementApp {

    public enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(1.0 / 30.48);

        private final double toFeet;

        LengthUnit(double toFeet) {
            this.toFeet = toFeet;
        }

        public double convertToBase(double value) {
            return value * toFeet;
        }

        public double convertFromBase(double baseValue) {
            return baseValue / toFeet;
        }
    }

    public enum WeightUnit {
        KILOGRAM(1.0),
        GRAM(1.0 / 1000.0),
        POUND(0.45359237);

        private final double toKg;

        WeightUnit(double toKg) {
            this.toKg = toKg;
        }

        public double convertToBase(double value) {
            return value * toKg;
        }

        public double convertFromBase(double baseValue) {
            return baseValue / toKg;
        }
    }

    public enum VolumeUnit {
        LITRE(1.0),
        MILLILITRE(1.0 / 1000.0),
        GALLON(3.78541);

        private final double toLitre;

        VolumeUnit(double toLitre) {
            this.toLitre = toLitre;
        }

        public double convertToBase(double value) {
            return value * toLitre;
        }

        public double convertFromBase(double baseValue) {
            return baseValue / toLitre;
        }
    }

    public static class Quantity<T> {
        private final double value;
        private final T unit;

        public Quantity(double value, T unit) {
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public T getUnit() {
            return unit;
        }

        public Quantity<T> add(Quantity<T> other) {
            return add(other, null);
        }

        public Quantity<T> add(Quantity<T> other, T targetUnit) {
            checkNull(other);
            double baseValue = toBase(this.value, this.unit);
            double otherBase = toBase(other.value, other.unit);
            double resultBase = baseValue + otherBase;
            T resultUnit = targetUnit != null ? targetUnit : this.unit;
            double resultValue = fromBase(resultBase, resultUnit);
            return new Quantity<>(round(resultValue), resultUnit);
        }

        public Quantity<T> subtract(Quantity<T> other) {
            return subtract(other, null);
        }

        public Quantity<T> subtract(Quantity<T> other, T targetUnit) {
            checkNull(other);
            double baseValue = toBase(this.value, this.unit);
            double otherBase = toBase(other.value, other.unit);
            double resultBase = baseValue - otherBase;
            T resultUnit = targetUnit != null ? targetUnit : this.unit;
            double resultValue = fromBase(resultBase, resultUnit);
            return new Quantity<>(round(resultValue), resultUnit);
        }

        public double divide(Quantity<T> other) {
            checkNull(other);
            double baseValue = toBase(this.value, this.unit);
            double otherBase = toBase(other.value, other.unit);
            if (otherBase == 0) throw new ArithmeticException("Cannot divide by zero");
            return round(baseValue / otherBase);
        }

        private void checkNull(Quantity<T> other) {
            if (other == null) throw new IllegalArgumentException("Operand cannot be null");
            if (!unit.getClass().equals(other.unit.getClass())) {
                throw new IllegalArgumentException("Units must be of the same type");
            }
        }

        private double toBase(double value, T unit) {
            if (unit instanceof LengthUnit) return ((LengthUnit) unit).convertToBase(value);
            if (unit instanceof WeightUnit) return ((WeightUnit) unit).convertToBase(value);
            if (unit instanceof VolumeUnit) return ((VolumeUnit) unit).convertToBase(value);
            throw new IllegalArgumentException("Unsupported unit type");
        }

        private double fromBase(double baseValue, T unit) {
            if (unit instanceof LengthUnit) return ((LengthUnit) unit).convertFromBase(baseValue);
            if (unit instanceof WeightUnit) return ((WeightUnit) unit).convertFromBase(baseValue);
            if (unit instanceof VolumeUnit) return ((VolumeUnit) unit).convertFromBase(baseValue);
            throw new IllegalArgumentException("Unsupported unit type");
        }

        private double round(double val) {
            BigDecimal bd = BigDecimal.valueOf(val);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            return bd.doubleValue();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Quantity)) return false;
            Quantity<?> quantity = (Quantity<?>) o;
            return Double.compare(quantity.value, value) == 0 && unit.equals(quantity.unit);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, unit);
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }
}