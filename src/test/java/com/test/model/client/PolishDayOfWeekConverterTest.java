package com.test.model.client;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

public class PolishDayOfWeekConverterTest extends TestCase {
    @Test
    void convertToPolishTest_Positive() {
        String dayToConvert = PolishDayOfWeekConverter.convertToPolish(DayOfWeek.SUNDAY);

        Assert.assertEquals(dayToConvert, "Niedziela");
    }

    @Test
    void convertToPolishTest_Negative() {
        String dayToConvert = PolishDayOfWeekConverter.convertToPolish(DayOfWeek.WEDNESDAY);

        Assert.assertNotSame("O nie sroda to nie niedzila", "Niedziela", dayToConvert);
    }


}