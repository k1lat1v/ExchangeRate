package org.vitalii.dao;

import org.springframework.stereotype.Repository;
import org.vitalii.model.ExchangeRate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@Repository
public class ExchangeRateDAOImpl implements ExchangeRateDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ExchangeRate find(Long id) {
        ExchangeRate exchangeRate = entityManager.find(ExchangeRate.class, id);
        return exchangeRate;
    }

    @Override
    public ExchangeRate findByDate(Date date){
        List<ExchangeRate> list = list();
        for(ExchangeRate er : list){
            if(er.getDate().compareTo(date) == 0){
                return er;
            }
        }
        return null;
    }

    @Override
    public void add(ExchangeRate exchangeRate) {
        entityManager.persist(exchangeRate);
    }

    @Override
    public List<ExchangeRate> list() {
        TypedQuery<ExchangeRate> query = entityManager.createQuery("SELECT er FROM ExchangeRate er", ExchangeRate.class);
        return query.getResultList();
    }
}
