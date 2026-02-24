package com.sneha;

import java.util.Scanner;

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
    
    public static void demonstrateLengthConversion(double value, LengthUnit source, LengthUnit target) {
        double result = convert(value, source, target);
        System.out.println("Convert " + value + " " + source + " to " + target + ": " + result);
    }
    
    public static void demonstrateLengthConversion(Quantity quantity, LengthUnit target) {
        double result = quantity.convertTo(target);
        System.out.println("Convert " + quantity + " to " + target + ": " + result);
    }
    
    public static void demonstrateLengthEquality(Quantity quantity1, Quantity quantity2) {
        boolean result = quantity1.equals(quantity2);
        System.out.println(quantity1 + " equals " + quantity2 + ": " + result);
    }
    
    public static void demonstrateLengthComparison(double value1, LengthUnit unit1, double value2, LengthUnit unit2) {
        Quantity q1 = new Quantity(value1, unit1);
        Quantity q2 = new Quantity(value2, unit2);
        demonstrateLengthEquality(q1, q2);
    }
    
    public static void main(String[] args) {
        
        System.out.println("=== UC1 & UC2: Basic Equality Checks ===");
        System.out.println("1.0 ft equals 1.0 ft: " + checkFeetEquality(1.0, 1.0));
        System.out.println("1.0 inch equals 1.0 inch: " + checkInchesEquality(1.0, 1.0));
        
        System.out.println("\n=== UC3: Generic Quantity Class ===");
        System.out.println("Quantity(1.0, FEET) equals Quantity(12.0, INCHES): " + 
            new Quantity(1.0, LengthUnit.FEET).equals(new Quantity(12.0, LengthUnit.INCHES)));
        
        System.out.println("\n=== UC4: Extended Unit Support (Yards & Centimeters) ===");
        System.out.println("Quantity(1.0, YARDS) equals Quantity(3.0, FEET): " + 
            new Quantity(1.0, LengthUnit.YARDS).equals(new Quantity(3.0, LengthUnit.FEET)));
        System.out.println("Quantity(1.0, YARDS) equals Quantity(36.0, INCHES): " + 
            new Quantity(1.0, LengthUnit.YARDS).equals(new Quantity(36.0, LengthUnit.INCHES)));
        System.out.println("Quantity(2.0, YARDS) equals Quantity(2.0, YARDS): " + 
            new Quantity(2.0, LengthUnit.YARDS).equals(new Quantity(2.0, LengthUnit.YARDS)));
        
        System.out.println("\n=== UC5: Unit-to-Unit Conversion ===");
        System.out.println("Convert 1.0 FEET to INCHES: " + convert(1.0, LengthUnit.FEET, LengthUnit.INCHES));
        System.out.println("Convert 24.0 INCHES to FEET: " + convert(24.0, LengthUnit.INCHES, LengthUnit.FEET));
        System.out.println("Convert 1.0 YARDS to INCHES: " + convert(1.0, LengthUnit.YARDS, LengthUnit.INCHES));
        System.out.println("Convert 6.0 FEET to YARDS: " + convert(6.0, LengthUnit.FEET, LengthUnit.YARDS));
        System.out.println("Convert 1.0 CENTIMETERS to INCHES: " + convert(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES));
        System.out.println("Convert 0.0 FEET to INCHES: " + convert(0.0, LengthUnit.FEET, LengthUnit.INCHES));
        System.out.println("Convert -1.0 FEET to INCHES: " + convert(-1.0, LengthUnit.FEET, LengthUnit.INCHES));
        
        System.out.println("\n=== Demonstration Methods ===");
        demonstrateLengthConversion(3.0, LengthUnit.FEET, LengthUnit.INCHES);
        demonstrateLengthConversion(1.0, LengthUnit.YARDS, LengthUnit.FEET);
        demonstrateLengthConversion(36.0, LengthUnit.INCHES, LengthUnit.YARDS);
        
        Quantity quantity = new Quantity(2.54, LengthUnit.CENTIMETERS);
        demonstrateLengthConversion(quantity, LengthUnit.INCHES);
        
        demonstrateLengthComparison(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCHES);
        demonstrateLengthComparison(1.0, LengthUnit.YARDS, 3.0, LengthUnit.FEET);
    }
}