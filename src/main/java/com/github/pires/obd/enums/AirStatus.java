package com.github.pires.obd.enums;

/**
 * Created by Massimo on 21/10/15.
 */
public enum AirStatus {
    UPSTREAM(1, "Upstream"),
    DOWNSTREAM(2, "Downstream of catalytic converter"),
    OUTSIDE_ATMOSPHERE_OR_OFF(4, "Upstream"),
    DIAGNOSTIC(8, "Pump commanded on for diagnostics"),
    INVALID(0, "Invalid");

    int value;
    String description;

    private AirStatus(final int value, final String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}