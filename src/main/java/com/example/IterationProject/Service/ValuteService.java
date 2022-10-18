package com.example.IterationProject.Service;

import com.example.IterationProject.DataFromXML.DataValute;
import com.example.IterationProject.Entity.Valute;
import com.example.IterationProject.Entity.ValuteCourse;
import com.example.IterationProject.Repository.ValuteCourseRepository;
import com.example.IterationProject.Repository.ValuteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ValuteService {

    private final ValuteRepository valuteRepository;
    private final ValuteCourseRepository valuteCourseRepository;
    private final LoadCourseOfValute loadCourseOfValute;

    @Autowired
    public ValuteService(ValuteRepository valuteRepository, ValuteCourseRepository valuteCourseRepository, LoadCourseOfValute loadCourseOfValute) {
        this.valuteRepository = valuteRepository;
        this.valuteCourseRepository = valuteCourseRepository;
        this.loadCourseOfValute = loadCourseOfValute;
    }

    public void addDataInDataBase() {
        List<DataValute> dataValuteList = loadCourseOfValute.getCourseOfValute().getValute();
        Date date = loadCourseOfValute.getCourseOfValute().getDate();
        for (DataValute dataValute : dataValuteList) {
            Valute valute = new Valute();
            valute.setName(dataValute.getName());
            valute.setNumCode(Integer.parseInt(dataValute.getNumCode()));
            valute.setCharCode(dataValute.getCharCode());
            valute.setNominal(Integer.parseInt(dataValute.getNominal()));
            //проверка на существование валюты в БД
            if (getValuteByName(dataValute.getName()) == null) {
                valuteRepository.save(valute);
            }

            ValuteCourse valuteCourse = new ValuteCourse();
            valuteCourse.setValute(valute);
            valuteCourse.setValue(Double.parseDouble(dataValute.getValue().replace(",",".")));
            valuteCourse.setDate(date);
            //проверка на существование курса валюты на данную дату в БД
            if (getValuteCourseByValuteAndDate(valute, date) == null) {
                valuteCourseRepository.save(valuteCourse);
            }
        }
    }

    public Valute getValuteByName(String name) {
        return valuteRepository.findValuteByName(name);
    }

    public ValuteCourse getValuteCourseByValuteAndDate(Valute valute, Date date) {
        return valuteCourseRepository.findValuteCourseByValuteAndDate(valute, date);
    }

    public List<Valute> getAllValute() {
        return valuteRepository.findAll();
    }

}
