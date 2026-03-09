package com.sneha;

public class QuantityMeasurementApp {

    public enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(1.0 / 30.48);

        private final double factor;

        LengthUnit(double factor) {
            this.factor = factor;
        }

        public double getFactor() {
            return factor;
        }
    }

    public static class Quantity {

        private final double value;
        private final LengthUnit unit;
        private static final double EPSILON = 1e-6;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null)
                throw new IllegalArgumentException("Unit must not be null");

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Value must be finite");

            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        private double toFeet() {
            return value * unit.getFactor();
        }

        public double convertTo(LengthUnit target) {

            if (target == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double feet = toFeet();

            return feet / target.getFactor();
        }

        public Quantity convertToQuantity(LengthUnit target) {
            double newValue = convertTo(target);
            return new Quantity(newValue, target);
        }

        public Quantity add(Quantity other) {

            if (other == null)
                throw new IllegalArgumentException("Second quantity cannot be null");

            double firstFeet = this.toFeet();
            double secondFeet = other.toFeet();

            double sumFeet = firstFeet + secondFeet;

            double resultValue = sumFeet / this.unit.getFactor();

            return new Quantity(resultValue, this.unit);
        }

        public Quantity add(Quantity other, LengthUnit targetUnit) {

            if (other == null)
                throw new IllegalArgumentException("Second quantity cannot be null");

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double firstFeet = this.toFeet();
            double secondFeet = other.toFeet();

            double sumFeet = firstFeet + secondFeet;

            double resultValue = sumFeet / targetUnit.getFactor();

            return new Quantity(resultValue, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            Quantity other = (Quantity) obj;

            return Math.abs(this.toFeet() - other.toFeet()) < EPSILON;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toFeet());
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

            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            Feet other = (Feet) obj;

            return Double.compare(value, other.value) == 0;
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

            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            Inches other = (Inches) obj;

            return Double.compare(value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    public static boolean checkFeetEquality(double a, double b) {
        return new Feet(a).equals(new Feet(b));
    }

    public static boolean checkInchesEquality(double a, double b) {
        return new Inches(a).equals(new Inches(b));
    }

    public static boolean checkQuantityEquality(Quantity q1, Quantity q2) {
        return q1.equals(q2);
    }

    public static double convert(double value, LengthUnit source, LengthUnit target) {

        if (source == null || target == null)
            throw new IllegalArgumentException("Units must not be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        Quantity q = new Quantity(value, source);

        return q.convertTo(target);
    }

    public static void main(String[] args) {

        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCHES);

        System.out.println(q1.add(q2));
        System.out.println(q1.add(q2, LengthUnit.FEET));
        System.out.println(q1.add(q2, LengthUnit.INCHES));
        System.out.println(q1.add(q2, LengthUnit.YARDS));

        Quantity q3 = new Quantity(36.0, LengthUnit.INCHES);
        Quantity q4 = new Quantity(1.0, LengthUnit.YARDS);

        System.out.println(q3.add(q4, LengthUnit.FEET));
    }
}