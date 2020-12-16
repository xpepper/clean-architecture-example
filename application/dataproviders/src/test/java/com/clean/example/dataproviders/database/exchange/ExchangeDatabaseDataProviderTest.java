package com.clean.example.dataproviders.database.exchange;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExchangeDatabaseDataProviderTest {

    private static final String EXCHANGE_CODE = "exch1";

    JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);

    ExchangeDatabaseDataProvider exchangeDatabaseDataProvider = new ExchangeDatabaseDataProvider(jdbcTemplate);

    @Test
    public void falseWhenExchangeDoesNotExist() throws Exception {
        givenExchangeDoesNotExist();

        boolean doesExchangeExist = exchangeDatabaseDataProvider.doesExchangeExist(EXCHANGE_CODE);

        assertThat(doesExchangeExist).isFalse();
    }

    @Test
    public void trueWhenExchangeDoesNotExist() throws Exception {
        givenExchangeExists();

        boolean doesExchangeExist = exchangeDatabaseDataProvider.doesExchangeExist(EXCHANGE_CODE);

        assertThat(doesExchangeExist).isTrue();
    }

    private void givenExchangeExists() {
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), eq(EXCHANGE_CODE))).thenReturn(1);
    }

    private void givenExchangeDoesNotExist() {
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), eq(EXCHANGE_CODE))).thenReturn(0);
    }

}
