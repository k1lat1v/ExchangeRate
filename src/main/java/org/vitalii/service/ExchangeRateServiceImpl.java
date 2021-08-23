package org.vitalii.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vitalii.dao.ExchangeRateDAO;
import org.vitalii.dao.SingleRateDAO;
import org.vitalii.model.ExchangeRate;
import org.vitalii.model.SingleRate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService{

    private final SingleRateDAO singleRateDAO;
    private final ExchangeRateDAO exchangeRateDAO;

    @PersistenceContext
    private EntityManager entityManager;

    // Поля для корректировки максимального и минимального дней в форме, чтобы избежать null
    private String min;
    private String max;

    public ExchangeRateServiceImpl(SingleRateDAO singleRateDAO, ExchangeRateDAO exchangeRateDAO) {
        this.singleRateDAO = singleRateDAO;
        this.exchangeRateDAO = exchangeRateDAO;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public String getMax() {
        return max;
    }

    @Transactional
    public void addExchangeRate(ExchangeRate exchangeRate) {
        exchangeRateDAO.add(exchangeRate);
    }

    @Transactional(readOnly = true)
    public ExchangeRate find(Long id) {
        return exchangeRateDAO.find(id);
    }

    @Transactional(readOnly = true)
    public ExchangeRate findByDate(Date date) {
        return exchangeRateDAO.findByDate(date);
    }

    @Transactional(readOnly = true)
    public List<ExchangeRate> listExchangeRate() {
        return exchangeRateDAO.list();
    }

    @Transactional(readOnly = true)
    public List<SingleRate> listSingleRate() {
        return singleRateDAO.list();
    }
}
