package test.java.com.ades;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import com.ades.Season;
import com.ades.SeasonUtils;

public class SeasonUtilsTest {

    @Test
    public void testGetCurrentSeason() {
        // Set the current date to a specific month to simulate different seasons
        LocalDate winterDate = LocalDate.of(2023, Month.JANUARY, 1);
        LocalDate springDate = LocalDate.of(2023, Month.APRIL, 1);
        LocalDate summerDate = LocalDate.of(2023, Month.JULY, 1);
        LocalDate autumnDate = LocalDate.of(2023, Month.OCTOBER, 1);

        // Test for Winter season
        setCurrentDate(winterDate);
        Season winterSeason = SeasonUtils.getCurrentSeason();
        Assertions.assertEquals(Season.Winter, winterSeason);

        // Test for Spring season
        setCurrentDate(springDate);
        Season springSeason = SeasonUtils.getCurrentSeason();
        Assertions.assertEquals(Season.Spring, springSeason);

        // Test for Summer season
        setCurrentDate(summerDate);
        Season summerSeason = SeasonUtils.getCurrentSeason();
        Assertions.assertEquals(Season.Summer, summerSeason);

        // Test for Autumn season
        setCurrentDate(autumnDate);
        Season autumnSeason = SeasonUtils.getCurrentSeason();
        Assertions.assertEquals(Season.Autumn, autumnSeason);
    }

    private void setCurrentDate(LocalDate date) {
        // Mock the current date for testing purposes
        System.setProperty("java.time.LocalDate.now", date.toString());
    }
}
