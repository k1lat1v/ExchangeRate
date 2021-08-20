package org.vitalii.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private Date date;
    private String bank;
    private int baseCurrency;
    private String baseCurrencyLit;

    @OneToMany(mappedBy = "exchangeRate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SingleRate> exchangeRate;

    public ExchangeRate(Date date, String bank, int baseCurrency, String baseCurrencyLit, List<SingleRate> exchangeRate) {
        this.date = date;
        this.bank = bank;
        this.baseCurrency = baseCurrency;
        this.baseCurrencyLit = baseCurrencyLit;
        this.exchangeRate = exchangeRate;
    }

    public ExchangeRate() {
    }

    public void updateSingles(){
        for(SingleRate rs : exchangeRate){
            rs.setExchangeRate(this);
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public int getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(int baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getBaseCurrencyLit() {
        return baseCurrencyLit;
    }

    public void setBaseCurrencyLit(String baseCurrencyLit) {
        this.baseCurrencyLit = baseCurrencyLit;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public List<SingleRate> getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(List<SingleRate> exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public String toString() {
        return "ExchangeRates{" +
                "date=" + date +
                ", bank='" + bank + '\'' +
                ", baseCurrency=" + baseCurrency +
                ", baseCurrencyLit='" + baseCurrencyLit + '\'' +
                ", exchangeRate=" + exchangeRate +
                '}';
    }
}
