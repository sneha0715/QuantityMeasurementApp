package com.sneha;

import java.util.Scanner;

public class QuantityMeasurementApp {
    
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
        
        sc.close();
    }
}