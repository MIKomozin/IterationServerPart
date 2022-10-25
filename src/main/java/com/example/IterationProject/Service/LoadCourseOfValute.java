package com.example.IterationProject.Service;

import com.example.IterationProject.DataFromXML.ValuteCurs;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class LoadCourseOfValute {

    //метод добавления актуальных данных в БД при загрузке приложения
    public ValuteCurs getCourseOfValutes() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(date);
        String url_with_date = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + strDate;
        String url = "http://www.cbr.ru/scripts/XML_daily.asp";

        RestTemplate restTemplate = new RestTemplate();

        ValuteCurs courseOfValute = restTemplate.getForObject(url_with_date, ValuteCurs.class);

        //если на сегодняшнюю дату курс валют не загружен, то предоставляем данные на последнюю дату обновления
        if (courseOfValute == null) {
            courseOfValute = restTemplate.getForObject(url, ValuteCurs.class);
        }

        return courseOfValute;
    }

    //метод добавления данных в БД на определенную дату
    public ValuteCurs getCourseOfValutesByDate(LocalDate date) {
        String strDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String url_with_date = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + strDate;

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(url_with_date, ValuteCurs.class);
    }

}
