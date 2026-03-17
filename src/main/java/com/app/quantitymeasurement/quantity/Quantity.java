package com.app.quantitymeasurement.quantity;

import com.app.quantitymeasurement.unit.IMeasurable;

public class Quantity<U extends IMeasurable> {

  private final double value;
  private final U unit;

  public Quantity(double value, U unit) {
    IMeasurable.validate(value);
    if (unit == null) {
      throw new IllegalArgumentException("Unit cannot be null");
    }
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
    if (targetUnit == null) {
      throw new IllegalArgumentException("Target unit cannot be null");
    }
    validateCompatibleUnit(targetUnit);
    double base = unit.convertToBaseUnit(value);
    return new Quantity<>(targetUnit.convertFromBaseUnit(base), targetUnit);
  }

  public Quantity<U> add(Quantity<U> other) {
    return add(other, unit);
  }

  public Quantity<U> add(Quantity<U> other, U targetUnit) {
    if (other == null) {
      throw new IllegalArgumentException("Operand quantity cannot be null");
    }
    validateCompatibleQuantity(other);
    validateCompatibleUnit(targetUnit);
    unit.validateOperationSupport("ADD");
    other.unit.validateOperationSupport("ADD");

    double base = unit.convertToBaseUnit(value) + other.unit.convertToBaseUnit(other.value);
    return new Quantity<>(targetUnit.convertFromBaseUnit(base), targetUnit);
  }

  public Quantity<U> subtract(Quantity<U> other) {
    return subtract(other, unit);
  }

  public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
    if (other == null) {
      throw new IllegalArgumentException("Operand quantity cannot be null");
    }
    validateCompatibleQuantity(other);
    validateCompatibleUnit(targetUnit);
    unit.validateOperationSupport("SUBTRACT");
    other.unit.validateOperationSupport("SUBTRACT");

    double base = unit.convertToBaseUnit(value) - other.unit.convertToBaseUnit(other.value);
    return new Quantity<>(targetUnit.convertFromBaseUnit(base), targetUnit);
  }

  public double divide(Quantity<U> other) {
    if (other == null) {
      throw new IllegalArgumentException("Operand quantity cannot be null");
    }
    validateCompatibleQuantity(other);
    unit.validateOperationSupport("DIVIDE");
    other.unit.validateOperationSupport("DIVIDE");

    double denominator = other.unit.convertToBaseUnit(other.value);
    if (Double.compare(denominator, 0.0) == 0) {
      throw new ArithmeticException("Cannot divide by zero quantity");
    }
    return unit.convertToBaseUnit(value) / denominator;
  }

  private void validateCompatibleQuantity(Quantity<?> other) {
    if (getCategoryClass(this.unit) != getCategoryClass(other.unit)) {
      throw new IllegalArgumentException("Quantities must belong to the same measurement category");
    }
  }

  private void validateCompatibleUnit(IMeasurable targetUnit) {
    if (targetUnit == null) {
      throw new IllegalArgumentException("Target unit cannot be null");
    }
    if (getCategoryClass(this.unit) != getCategoryClass(targetUnit)) {
      throw new IllegalArgumentException("Target unit must belong to the same measurement category");
    }
  }

  private Class<?> getCategoryClass(Object unitObj) {
    return unitObj instanceof Enum<?> ? ((Enum<?>) unitObj).getDeclaringClass() : unitObj.getClass();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Quantity<?>)) {
      return false;
    }
    Quantity<?> other = (Quantity<?>) obj;
    if (getCategoryClass(this.unit) != getCategoryClass(other.unit)) {
      return false;
    }
    double left = Math.round(unit.convertToBaseUnit(value) * 1000.0) / 1000.0;
    double right = Math.round(other.unit.convertToBaseUnit(other.value) * 1000.0) / 1000.0;
    return Double.compare(left, right) == 0;
  }

  @Override
  public int hashCode() {
    double baseValue = Math.round(unit.convertToBaseUnit(value) * 1000.0) / 1000.0;
    return java.util.Objects.hash(getCategoryClass(unit), baseValue);
  }

  @Override
  public String toString() {
    return "Quantity(" + value + ", " + unit + ")";
  }
}
