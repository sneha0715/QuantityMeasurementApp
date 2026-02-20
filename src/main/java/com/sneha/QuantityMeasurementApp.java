package com.sneha;

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
        System.out.println("1.0 ft equals 1.0 ft: " + checkFeetEquality(1.0, 1.0));
        System.out.println("1.0 ft equals 2.0 ft: " + checkFeetEquality(1.0, 2.0));
        System.out.println("1.0 inch equals 1.0 inch: " + checkInchesEquality(1.0, 1.0));
        System.out.println("1.0 inch equals 2.0 inch: " + checkInchesEquality(1.0, 2.0));
        
        Quantity feet1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity inches12 = new Quantity(12.0, LengthUnit.INCHES);
        Quantity feet2 = new Quantity(1.0, LengthUnit.FEET);
        
        System.out.println("Quantity(1.0, FEET) equals Quantity(12.0, INCHES): " + feet1.equals(inches12));
        System.out.println("Quantity(1.0, FEET) equals Quantity(1.0, FEET): " + feet1.equals(feet2));
    }
}