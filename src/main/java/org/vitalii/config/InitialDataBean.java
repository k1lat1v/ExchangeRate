package org.vitalii.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;
import org.vitalii.model.ExchangeRate;
import org.vitalii.service.ExchangeRateService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Component
public class InitialDataBean {

    private final ExchangeRateService exchangeRateService;

    public InitialDataBean(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @PostConstruct
    public void getArchive(){
        try {
            Calendar present = new GregorianCalendar();
            Calendar past = new GregorianCalendar();
            past.add(Calendar.DAY_OF_MONTH, -2);

            setBounds(present, past);

            getData(present, past);

        }catch (MalformedURLException e){
            System.out.println("********************* FATAL ERROR *********************");
            System.out.println("********************* BAD URL FROM PRIVATBANK *********************");
            e.printStackTrace();
        }catch (IOException e){
            System.out.println("********************* FATAL ERROR *********************");
            System.out.println("********************* CANNOT READ FROM PRIVATBANK *********************");
            e.printStackTrace();
        }
    }

    // Установить максимальные и минимальные дни в форме
    private void setBounds(Calendar present, Calendar past){
        String htmlPattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(htmlPattern);

        past.add(Calendar.DAY_OF_MONTH, 1); // Нужно сменить для правильного отображения в форме и избежать null

        exchangeRateService.setMax(sdf.format(present.getTime()));
        exchangeRateService.setMin(sdf.format(past.getTime()));
    }

    private void getData(Calendar present, Calendar past) throws IOException{
        String pattern = "dd.MM.yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Gson gson = new GsonBuilder().setDateFormat(pattern).create();

        past.add(Calendar.DAY_OF_MONTH, -1); // После изменения в методе setBounds(), нужно вернуть значение назад

        while(!present.equals(past)){
            String url = "https://api.privatbank.ua/p24api/exchange_rates?json&date=" + sdf.format(present.getTime());
            InputStream is = new URL(url).openStream();
            byte[] array = is.readAllBytes();
            ExchangeRate exchangeRate = gson.fromJson(new String(array), ExchangeRate.class);
            exchangeRate.updateSingles(); // Всем SingleRate из текущего JSON присвоить этот ExchangeRate
            exchangeRateService.addExchangeRate(exchangeRate);
            present.add(Calendar.DAY_OF_MONTH, -1);
        }
    }
}
