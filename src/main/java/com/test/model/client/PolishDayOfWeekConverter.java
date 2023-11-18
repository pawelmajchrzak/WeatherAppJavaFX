package com.test.model.client;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

public class PolishDayOfWeekConverter {
    private static final Map<DayOfWeek, String> dayOfWeekMap = new HashMap<>();

    static {
        dayOfWeekMap.put(DayOfWeek.MONDAY, "Poniedziałek");
        dayOfWeekMap.put(DayOfWeek.TUESDAY, "Wtorek");
        dayOfWeekMap.put(DayOfWeek.WEDNESDAY, "Środa");
        dayOfWeekMap.put(DayOfWeek.THURSDAY, "Czwartek");
        dayOfWeekMap.put(DayOfWeek.FRIDAY, "Piątek");
        dayOfWeekMap.put(DayOfWeek.SATURDAY, "Sobota");
        dayOfWeekMap.put(DayOfWeek.SUNDAY, "Niedziela");
    }

    public static String convertToPolish(DayOfWeek dayOfWeek) {
        return dayOfWeekMap.getOrDefault(dayOfWeek, "Nieznany dzień tygodnia");
    }
}
