package com.test.model;

import com.test.model.client.WeatherClient;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WeatherServiceTest {
    @Mock
    private WeatherClient weatherClient;

    @Test
    public void shouldReturnWeatherClient() {
        // given
        WeatherService weatherService = new WeatherService(weatherClient);

        // when
        WeatherClient result = weatherService.getWeatherClient();

        // then
        assertThat(result).isEqualTo(weatherClient);
    }
}