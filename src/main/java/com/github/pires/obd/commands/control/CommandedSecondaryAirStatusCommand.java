package com.github.pires.obd.commands.control;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.enums.AirStatus;
import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * Created by Massimo on 21/10/15.
 */
public class CommandedSecondaryAirStatusCommand extends ObdCommand {
    private int airStatus;

    public CommandedSecondaryAirStatusCommand() {
        super("01 12");
    }

    @Override
    protected void performCalculations() {
        // Ignore first two bytes [hh hh] of the response.
        airStatus = buffer.get(2);
    }

    @Override
    public String getFormattedResult() {
        return getAirStatus().getDescription();
    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(airStatus);
    }

    @Override
    public String getName() {
        return AvailableCommandNames.COMMANDED_SECONDARY_AIR_STATUS.getValue();
    }

    public AirStatus getAirStatus() {
        // check if there is one, and only one, bit is set
        if (airStatus != 0 && (airStatus & (airStatus-1)) == 0) {
            if ((airStatus & AirStatus.UPSTREAM.getValue()) != 0)
                return AirStatus.UPSTREAM;

            if ((airStatus & AirStatus.DOWNSTREAM.getValue()) != 0)
                return AirStatus.DOWNSTREAM;

            if ((airStatus & AirStatus.OUTSIDE_ATMOSPHERE_OR_OFF.getValue()) != 0)
                return AirStatus.OUTSIDE_ATMOSPHERE_OR_OFF;

            if ((airStatus & AirStatus.DIAGNOSTIC.getValue()) != 0)
                return AirStatus.DIAGNOSTIC;
        }

        return AirStatus.INVALID;
    }
}
