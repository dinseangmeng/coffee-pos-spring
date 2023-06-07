package com.gic23.coffee_pos.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DoubleRound {
    public static double roundUp(double value, int decimalPlaces) {
        BigDecimal decimal = BigDecimal.valueOf(value);
        BigDecimal roundedUp = decimal.setScale(decimalPlaces, RoundingMode.UP);
        return roundedUp.doubleValue();
    }
}
