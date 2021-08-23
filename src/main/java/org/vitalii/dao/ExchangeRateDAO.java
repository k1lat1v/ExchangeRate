package org.vitalii.dao;

import org.vitalii.model.ExchangeRate;

import java.util.Date;
import java.util.List;

public interface ExchangeRateDAO {
    ExchangeRate find(Long id);
    ExchangeRate findByDate(Date date);
    void add(ExchangeRate exchangeRate);
    List<ExchangeRate> list();

}
