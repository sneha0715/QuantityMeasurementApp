package com.sneha;

enum LengthUnit {
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }
}

enum WeightUnit {
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }

    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }
}

public class QuantityMeasurementApp {

    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Feet other = (Feet) obj;
            return Double.compare(value, other.value) == 0;
        }
    }

    public static class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Inches other = (Inches) obj;
            return Double.compare(value, other.value) == 0;
        }
    }

    public static boolean checkFeetEquality(double a, double b) {
        return Double.compare(a, b) == 0;
    }

    public static boolean checkInchesEquality(double a, double b) {
        return Double.compare(a, b) == 0;
    }

    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (source == null || target == null) throw new IllegalArgumentException();
        if (Double.isNaN(value) || Double.isInfinite(value)) throw new IllegalArgumentException();
        double base = source.convertToBaseUnit(value);
        return target.convertFromBaseUnit(base);
    }

    public static class Quantity {

        private final double value;
        private final LengthUnit unit;
        private static final double EPSILON = 1e-6;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null) throw new IllegalArgumentException();
            if (Double.isNaN(value) || Double.isInfinite(value)) throw new IllegalArgumentException();
            this.value = value;
            this.unit = unit;
        }

        private double toBaseUnit() {
            return unit.convertToBaseUnit(value);
        }

        public double convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) throw new IllegalArgumentException();
            double base = toBaseUnit();
            return targetUnit.convertFromBaseUnit(base);
        }

        public Quantity convertToQuantity(LengthUnit targetUnit) {
            double result = convertTo(targetUnit);
            return new Quantity(result, targetUnit);
        }

        public Quantity add(Quantity other) {
            return add(other, this.unit);
        }

        public Quantity add(Quantity other, LengthUnit targetUnit) {
            if (other == null || targetUnit == null) throw new IllegalArgumentException();
            double sumBase = this.toBaseUnit() + other.toBaseUnit();
            double result = targetUnit.convertFromBaseUnit(sumBase);
            return new Quantity(result, targetUnit);
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Quantity other = (Quantity) obj;
            return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
        }

        public int hashCode() {
            return Double.valueOf(toBaseUnit()).hashCode();
        }

        public String toString() {
            return value + " " + unit;
        }
    }

    public static class QuantityWeight {

        private final double value;
        private final WeightUnit unit;
        private static final double EPSILON = 1e-6;

        public QuantityWeight(double value, WeightUnit unit) {
            if (unit == null) throw new IllegalArgumentException();
            if (Double.isNaN(value) || Double.isInfinite(value)) throw new IllegalArgumentException();
            this.value = value;
            this.unit = unit;
        }

        private double toBaseUnit() {
            return unit.convertToBaseUnit(value);
        }

        public QuantityWeight convertTo(WeightUnit targetUnit) {
            if (targetUnit == null) throw new IllegalArgumentException();
            double base = toBaseUnit();
            double result = targetUnit.convertFromBaseUnit(base);
            return new QuantityWeight(result, targetUnit);
        }

        public QuantityWeight add(QuantityWeight other) {
            return add(other, this.unit);
        }

        public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {
            if (other == null || targetUnit == null) throw new IllegalArgumentException();
            double sumBase = this.toBaseUnit() + other.toBaseUnit();
            double result = targetUnit.convertFromBaseUnit(sumBase);
            return new QuantityWeight(result, targetUnit);
        }

        public double getValue() {
            return value;
        }

        public WeightUnit getUnit() {
            return unit;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            QuantityWeight other = (QuantityWeight) obj;
            return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
        }

        public int hashCode() {
            return Double.valueOf(toBaseUnit()).hashCode();
        }

        public String toString() {
            return value + " " + unit;
        }
    }

    public static void main(String[] args) {

        Quantity length1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity length2 = new Quantity(12.0, LengthUnit.INCHES);

        Quantity lengthResult = length1.add(length2, LengthUnit.FEET);
        System.out.println("Length Result: " + lengthResult);

        QuantityWeight weight1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight weight2 = new QuantityWeight(500.0, WeightUnit.GRAM);

        QuantityWeight weightResult = weight1.add(weight2, WeightUnit.KILOGRAM);
        System.out.println("Weight Result: " + weightResult);
    }
}