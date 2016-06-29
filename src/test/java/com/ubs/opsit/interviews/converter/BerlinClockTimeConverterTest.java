package com.ubs.opsit.interviews.converter;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by dmitri on 29.06.16.
 */
public class BerlinClockTimeConverterTest {

    private BerlinClockTimeConverter timeConverter;

    @Before
    public void init() {
        timeConverter = new BerlinClockTimeConverter();
    }

    @Test
    public void testGetHoursRepresentation() throws Exception {
        String hours = "18";
        String expected = "RRRO\n" +
                          "RRRO";
        assertThat(expected, is(equalTo(timeConverter.getHoursRepresentation(hours))));
    }

    @Test
    public void testGetMinutesRepresentation() throws Exception {
        String minutes = "34";
        String expected = "YYRYYROOOOO\n" +
                          "YYYY";
        assertThat(expected, is(equalTo(timeConverter.getMinutesRepresentation(minutes))));
    }

    @Test
    public void testGetSecondsRepresentation() throws Exception {
        String seconds = "16";
        String expected = "Y";
        assertThat(expected, is(equalTo(timeConverter.getSecondsRepresentation(seconds))));
    }

    @Test
    public void testGetSecondsRepresentationNotEven() {
        String seconds = "55";
        String expected = "O";
        assertThat(expected, is(equalTo(timeConverter.getSecondsRepresentation(seconds))));
    }
}