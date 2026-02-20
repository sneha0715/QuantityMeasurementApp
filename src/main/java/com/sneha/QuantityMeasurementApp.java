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
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter first feet value:");
        double value1 = sc.nextDouble();
        
        System.out.println("Enter second feet value:");
        double value2 = sc.nextDouble();
        
        Feet feet1 = new Feet(value1);
        Feet feet2 = new Feet(value2);
        
        System.out.println(value1 + " ft equals " + value2 + " ft: " + feet1.equals(feet2));
        System.out.println(value1 + " ft equals itself: " + feet1.equals(feet1));
        System.out.println(value1 + " ft equals null: " + feet1.equals(null));
        
        sc.close();
    }
}