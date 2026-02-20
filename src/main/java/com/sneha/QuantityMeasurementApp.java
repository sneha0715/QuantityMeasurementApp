package com.sneha;

import java.util.Scanner;

public class QuantityMeasurementApp {
    
    public enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12.0);
        
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
        
        public Quantity(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
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
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            
            Quantity quantity = (Quantity) obj;
            return Double.compare(this.convertToBaseFeet(), quantity.convertToBaseFeet()) == 0;
        }
        
        @Override
        public int hashCode() {
            return Double.hashCode(convertToBaseFeet());
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
    
    public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    System.out.println("Enter first value:");
    double value1 = sc.nextDouble();

    System.out.println("Enter first unit (FEET / INCHES):");
    String unit1Input = sc.next().toUpperCase();
    LengthUnit unit1 = LengthUnit.valueOf(unit1Input);

    System.out.println("Enter second value:");
    double value2 = sc.nextDouble();

    System.out.println("Enter second unit (FEET / INCHES):");
    String unit2Input = sc.next().toUpperCase();
    LengthUnit unit2 = LengthUnit.valueOf(unit2Input);

    Quantity quantity1 = new Quantity(value1, unit1);
    Quantity quantity2 = new Quantity(value2, unit2);

    boolean result = checkQuantityEquality(quantity1, quantity2);

    if (result) {
        System.out.println("Both quantities are EQUAL.");
    } else {
        System.out.println("Both quantities are NOT EQUAL.");
    }

    sc.close();
}
}