package com.ubs.opsit.interviews.converter;

import com.ubs.opsit.interviews.TimeConverter;
import com.ubs.opsit.interviews.util.ParseUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by dmitri on 29.06.16.
 */
public class BerlinClockTimeConverter implements TimeConverter {

    private final int UNIFIDE_TIME_DELIMITER = 5;
    private final int SECONDS_DELIMITER = 2;

    private final String YELLOW_LABEL = "Y";
    private final String RED_LABEL = "R";
    private final String OFF_LABEL = "O";

    @Override
    public String convertTime(String aTime) {
        List<String> timeParts = ParseUtil.getEntriesListByPattern(aTime, Pattern.compile("(\\d{1,2})"));

        String berlinClock = String.format(
                "%s\n" +
                "%s\n" +
                "%s",
                getSecondsRepresentation(timeParts.get(2)),
                getHoursRepresentation(timeParts.get(0)),
                getMinutesRepresentation(timeParts.get(1))
        );
        return berlinClock;
    }

    public String getHoursRepresentation(String hours) {
        return String.format("%s\n%s", getTopHourRepresentation(hours), getBottomHourRepresentation(hours));
    }

    public String getMinutesRepresentation(String minutes) {
        return String.format("%s\n%s", getTopMinutesRepresentation(minutes), getBottomMinutesRepresentation(minutes));
    }

    public String getSecondsRepresentation(String seconds) {
        int secondsValue = Integer.parseInt(seconds);
        return secondsValue % SECONDS_DELIMITER == 0 ? YELLOW_LABEL : OFF_LABEL;
    }

    private String getTopHourRepresentation(String hours) {
        int hoursValue = Integer.parseInt(hours) / UNIFIDE_TIME_DELIMITER;
        return calcLamps(4, hoursValue, RED_LABEL);
    }

    private String getBottomHourRepresentation(String hours) {
        int restHoursValue = Integer.parseInt(hours) % UNIFIDE_TIME_DELIMITER;
        return calcLamps(4, restHoursValue, RED_LABEL);
    }

    private String getTopMinutesRepresentation(String minutes) {
        int minutesValue = Integer.parseInt(minutes) / UNIFIDE_TIME_DELIMITER;
        String timeRepresentation = calcLamps(11, minutesValue, YELLOW_LABEL);
        for (int i = 0; i + 1 < timeRepresentation.length() ; i++) {
            if((i + 1) % 3 == 0 && i + 1 <= minutesValue) {
                timeRepresentation = StringUtils.overlay(timeRepresentation, RED_LABEL, i, i+1);
            }
        }
        return timeRepresentation;
    }

    private String getBottomMinutesRepresentation(String minutes) {
        int restMinutes = Integer.parseInt(minutes) % UNIFIDE_TIME_DELIMITER;
        return calcLamps(4, restMinutes, YELLOW_LABEL);
    }

    private String calcLamps(int maxLamps, int onLamps, String onLabel) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 1; i <= maxLamps; i++) {
            if(i <= onLamps) {
                buffer.append(onLabel);
            } else {
                buffer.append(OFF_LABEL);
            }
        }
        return buffer.toString();
    }
}
