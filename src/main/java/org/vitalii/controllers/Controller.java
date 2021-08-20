package org.vitalii.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.vitalii.model.ExchangeRate;
import org.vitalii.service.ExchangeRateService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@org.springframework.stereotype.Controller
public class Controller {

    private final ExchangeRateService exchangeRateService;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public Controller(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @RequestMapping("/")
    public String index(Model model){
        LocalDate today = LocalDate.now();
        Date date = java.sql.Date.valueOf(today);
        model.addAttribute("date", sdf.format(date));
        model.addAttribute("exchangeRate", exchangeRateService.findByDate(date).getExchangeRate());
        model.addAttribute("min", exchangeRateService.getMin());
        model.addAttribute("max", exchangeRateService.getMax());
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String search(@RequestParam String date, Model model){
        if(date.equals("")){
            return "redirect:/";
        }
        try {
            Date input = sdf.parse(date);
            ExchangeRate exchangeRate = exchangeRateService.findByDate(input);
            model.addAttribute("date", sdf.format(input));
            model.addAttribute("exchangeRate", exchangeRate.getExchangeRate());
            model.addAttribute("min", exchangeRateService.getMin());
            model.addAttribute("max", exchangeRateService.getMax());
        }catch (ParseException e){
            e.printStackTrace();
        }
        return "index";
    }
}
