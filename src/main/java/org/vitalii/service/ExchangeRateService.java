package org.vitalii.service;

import org.vitalii.model.ExchangeRate;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import java.util.List;

public interface ExchangeRateService {
    void setMin(String min);
    void setMax(String max);
    String getMin();
    String getMax();
    void addExchangeRate(ExchangeRate exchangeRate);
    ExchangeRate find(Long id);
    ExchangeRate findByDate(Date date);
    List<ExchangeRate> list();
}
