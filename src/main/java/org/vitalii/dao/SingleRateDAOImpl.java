package org.vitalii.dao;

import org.springframework.stereotype.Repository;
import org.vitalii.model.SingleRate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@Repository
public class SingleRateDAOImpl implements SingleRateDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SingleRate> list() {
        TypedQuery<SingleRate> query = entityManager.createQuery("SELECT sr FROM SingleRate sr", SingleRate.class);
        List<SingleRate> singleRates = query.getResultList();
        return singleRates;
    }
}
