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
        
        System.out.println("Enter first feet value:");
        double feetValue1 = sc.nextDouble();
        
        System.out.println("Enter second feet value:");
        double feetValue2 = sc.nextDouble();
        
        boolean feetResult = checkFeetEquality(feetValue1, feetValue2);
        System.out.println(feetValue1 + " ft equals " + feetValue2 + " ft: " + feetResult);
    
        System.out.println("\nEnter first inch value:");
        double inchValue1 = sc.nextDouble();
        
        System.out.println("Enter second inch value:");
        double inchValue2 = sc.nextDouble();
        
        boolean inchResult = checkInchesEquality(inchValue1, inchValue2);
        System.out.println(inchValue1 + " inch equals " + inchValue2 + " inch: " + inchResult);
        
        System.out.println("\nEnter first yard value:");
        double yardValue1 = sc.nextDouble();
        
        System.out.println("Enter second yard value:");
        double yardValue2 = sc.nextDouble();
        
        Quantity yard1 = new Quantity(yardValue1, LengthUnit.YARDS);
        Quantity yard2 = new Quantity(yardValue2, LengthUnit.YARDS);
        System.out.println(yardValue1 + " yard equals " + yardValue2 + " yard: " + yard1.equals(yard2));
        
        System.out.println("\nEnter first centimeter value:");
        double cmValue1 = sc.nextDouble();
        
        System.out.println("Enter second centimeter value:");
        double cmValue2 = sc.nextDouble();
        
        Quantity cm1 = new Quantity(cmValue1, LengthUnit.CENTIMETERS);
        Quantity cm2 = new Quantity(cmValue2, LengthUnit.CENTIMETERS);
        System.out.println(cmValue1 + " cm equals " + cmValue2 + " cm: " + cm1.equals(cm2));
        
        System.out.println("\nDemo: 1 Yard equals 3 Feet: " + new Quantity(1.0, LengthUnit.YARDS).equals(new Quantity(3.0, LengthUnit.FEET)));
        System.out.println("Demo: 1 Yard equals 36 Inches: " + new Quantity(1.0, LengthUnit.YARDS).equals(new Quantity(36.0, LengthUnit.INCHES)));
        System.out.println("Demo: 2 Yards equals 6 Feet: " + new Quantity(2.0, LengthUnit.YARDS).equals(new Quantity(6.0, LengthUnit.FEET)));
        
        sc.close();
    }
}