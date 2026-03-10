package com.sneha;

import java.util.function.Function;

public class QuantityMeasurementApp {

        public interface IMeasurable {
                double toBase(double value);

                double fromBase(double baseValue);

                default boolean supportsArithmetic() {
                        return true;
                }

                default void validateOperationSupport(String operation) {
                }
        }

        public enum LengthUnit implements IMeasurable {
                FEET(1.0),
                INCHES(1.0 / 12.0),
                YARDS(3.0),
                CENTIMETERS(1.0 / 30.48);

                private final double factor;

                LengthUnit(double factor) {
                        this.factor = factor;
                }

                public double toBase(double value) {
                        return value * factor;
                }

                public double fromBase(double baseValue) {
                        return baseValue / factor;
                }
        }

        public enum WeightUnit implements IMeasurable {
                KILOGRAM(1.0),
                GRAM(0.001),
                TONNE(1000.0);

                private final double factor;

                WeightUnit(double factor) {
                        this.factor = factor;
                }

                public double toBase(double value) {
                        return value * factor;
                }

                public double fromBase(double baseValue) {
                        return baseValue / factor;
                }
        }

        public enum VolumeUnit implements IMeasurable {
                LITRE(1.0),
                MILLILITRE(0.001),
                GALLON(3.78541);

                private final double factor;

                VolumeUnit(double factor) {
                        this.factor = factor;
                }

                public double toBase(double value) {
                        return value * factor;
                }

                public double fromBase(double baseValue) {
                        return baseValue / factor;
                }
        }

        public enum TemperatureUnit implements IMeasurable {
                CELSIUS(c -> c, c -> c),
                FAHRENHEIT(f -> (f - 32) * 5 / 9, c -> (c * 9 / 5) + 32),
                KELVIN(k -> k - 273.15, c -> c + 273.15);

                private final Function<Double, Double> toCelsius;
                private final Function<Double, Double> fromCelsius;

                TemperatureUnit(Function<Double, Double> toCelsius, Function<Double, Double> fromCelsius) {
                        this.toCelsius = toCelsius;
                        this.fromCelsius = fromCelsius;
                }

                public double toBase(double value) {
                        return toCelsius.apply(value);
                }

                public double fromBase(double baseValue) {
                        return fromCelsius.apply(baseValue);
                }

                @Override
                public boolean supportsArithmetic() {
                        return false;
                }

                @Override
                public void validateOperationSupport(String operation) {
                        throw new UnsupportedOperationException("Temperature does not support " + operation);
                }
        }

        public static class Quantity<U extends IMeasurable> {
                private final double value;
                private final U unit;

                public Quantity(double value, U unit) {
                        if (unit == null)
                                throw new IllegalArgumentException("Unit cannot be null");
                        this.value = value;
                        this.unit = unit;
                }

                public double getValue() {
                        return value;
                }

                public U getUnit() {
                        return unit;
                }

                public Quantity<U> convertTo(U targetUnit) {
                        double base = unit.toBase(value);
                        double converted = targetUnit.fromBase(base);
                        return new Quantity<>(converted, targetUnit);
                }

                public Quantity<U> add(Quantity<U> other) {
                        unit.validateOperationSupport("addition");
                        double base1 = unit.toBase(value);
                        double base2 = other.unit.toBase(other.value);
                        return new Quantity<>(unit.fromBase(base1 + base2), unit);
                }

                public Quantity<U> subtract(Quantity<U> other) {
                        unit.validateOperationSupport("subtraction");
                        double base1 = unit.toBase(value);
                        double base2 = other.unit.toBase(other.value);
                        return new Quantity<>(unit.fromBase(base1 - base2), unit);
                }

                @Override
                public boolean equals(Object obj) {
                        if (this == obj)
                                return true;
                        if (obj == null || !(obj instanceof Quantity<?>))
                                return false;
                        Quantity<?> other = (Quantity<?>) obj;
                        double thisBase = unit.toBase(this.value);
                        double otherBase = other.unit.toBase(other.value);
                        return Math.abs(thisBase - otherBase) < 1e-6;
                }

                @Override
                public String toString() {
                        return "Quantity(" + value + ", " + unit + ")";
                }
        }

        public static void main(String[] args) {
                Quantity<TemperatureUnit> t1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
                Quantity<TemperatureUnit> t2 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
                Quantity<TemperatureUnit> t3 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
                Quantity<TemperatureUnit> t4 = new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT);

                System.out.println("0°C == 32°F : " + t1.equals(t2));
                System.out.println("100°C == 212°F : " + t3.equals(t4));
                System.out.println("100°C to °F : " + t3.convertTo(TemperatureUnit.FAHRENHEIT));
                System.out.println("32°F to °C : " + t2.convertTo(TemperatureUnit.CELSIUS));

                try {
                        t3.add(new Quantity<>(50.0, TemperatureUnit.CELSIUS));
                } catch (UnsupportedOperationException e) {
                        System.out.println(e.getMessage());
                }

                Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
                Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);
                System.out.println("1 ft == 12 in : " + l1.equals(l2));
                System.out.println("1 ft + 12 in : " + l1.add(l2));
                System.out.println("1 ft in cm : " + l1.convertTo(LengthUnit.CENTIMETERS));

                Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
                Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);
                System.out.println("1 kg == 1000 g : " + w1.equals(w2));
                System.out.println("1 kg + 1000 g : " + w1.add(w2));

                Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
                Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
                System.out.println("1 L == 1000 mL : " + v1.equals(v2));
                System.out.println("1 L + 1000 mL : " + v1.add(v2));
        }
}