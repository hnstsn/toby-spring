package com.example.tobyspring.clock;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ClockTest {
    @Test
    public void clock() {
        Clock clock = Clock.systemDefaultZone();

        LocalDateTime time1 = LocalDateTime.now(clock);
        LocalDateTime time2 = LocalDateTime.now(clock);

        Assertions.assertThat(time2).isAfter(time1);
    }
    
    @Test
    public void fixedClock() {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        LocalDateTime time1 = LocalDateTime.now(clock);
        LocalDateTime time2 = LocalDateTime.now(clock);

        Assertions.assertThat(time2).isEqualTo(time1);

        LocalDateTime time3 = LocalDateTime.now(clock).plusHours(1);

        Assertions.assertThat(time3).isEqualTo(time1.plusHours(1));
    }
}
