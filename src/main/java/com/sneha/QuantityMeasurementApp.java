package com.sneha;

public class QuantityMeasurementApp {

    public enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(1.0 / 30.48);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    public static class Quantity {
        private final double value;
        private final LengthUnit unit;
        private static final double EPSILON = 1e-6;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Value must be a finite number");
            }
            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        private double convertToBaseFeet() {
            return value * unit.getConversionFactor();
        }

        public double convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }
            double baseValue = convertToBaseFeet();
            return baseValue / targetUnit.getConversionFactor();
        }

        public Quantity convertToQuantity(LengthUnit targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }
            double convertedValue = convertTo(targetUnit);
            return new Quantity(convertedValue, targetUnit);
        }

        public Quantity add(Quantity other) {
            if (other == null) {
                throw new IllegalArgumentException("Quantity cannot be null");
            }

            double baseValue1 = this.convertToBaseFeet();
            double baseValue2 = other.convertToBaseFeet();

            double sumBase = baseValue1 + baseValue2;

            double resultValue = sumBase / this.unit.getConversionFactor();

            return new Quantity(resultValue, this.unit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Quantity quantity = (Quantity) obj;
            return Math.abs(this.convertToBaseFeet() - quantity.convertToBaseFeet()) < EPSILON;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(convertToBaseFeet());
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Feet feet = (Feet) obj;
            return Double.compare(this.value, feet.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    public static class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Inches inches = (Inches) obj;
            return Double.compare(this.value, inches.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    public static boolean checkFeetEquality(double value1, double value2) {
        return new Feet(value1).equals(new Feet(value2));
    }

    public static boolean checkInchesEquality(double value1, double value2) {
        return new Inches(value1).equals(new Inches(value2));
    }

    public static boolean checkQuantityEquality(Quantity quantity1, Quantity quantity2) {
        return quantity1.equals(quantity2);
    }

    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (source == null) {
            throw new IllegalArgumentException("Source unit cannot be null");
        }
        if (target == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be a finite number");
        }

        Quantity quantity = new Quantity(value, source);
        return quantity.convertTo(target);
    }

    public static void main(String[] args) {

        System.out.println("=== UC6 Addition Examples ===");

        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCHES);
        System.out.println("1 FEET + 12 INCHES = " + q1.add(q2));

        Quantity q3 = new Quantity(12.0, LengthUnit.INCHES);
        Quantity q4 = new Quantity(1.0, LengthUnit.FEET);
        System.out.println("12 INCHES + 1 FEET = " + q3.add(q4));

        Quantity q5 = new Quantity(1.0, LengthUnit.YARDS);
        Quantity q6 = new Quantity(3.0, LengthUnit.FEET);
        System.out.println("1 YARD + 3 FEET = " + q5.add(q6));

        Quantity q7 = new Quantity(2.54, LengthUnit.CENTIMETERS);
        Quantity q8 = new Quantity(1.0, LengthUnit.INCHES);
        System.out.println("2.54 CM + 1 INCH = " + q7.add(q8));

        Quantity q9 = new Quantity(5.0, LengthUnit.FEET);
        Quantity q10 = new Quantity(0.0, LengthUnit.INCHES);
        System.out.println("5 FEET + 0 INCH = " + q9.add(q10));

        Quantity q11 = new Quantity(5.0, LengthUnit.FEET);
        Quantity q12 = new Quantity(-2.0, LengthUnit.FEET);
        System.out.println("5 FEET + (-2 FEET) = " + q11.add(q12));
    }
}