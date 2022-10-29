package com.example.IterationProject.Controllers;

import com.example.IterationProject.Entity.ConvertionHistory;
import com.example.IterationProject.Service.ValuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MainController {

    private final ValuteService valuteService;

    @Autowired
    public MainController(ValuteService valuteService) {
        this.valuteService = valuteService;
    }

    //метод добавления данных при загрузке приложения
    public void addValutesInDataBase() {
        valuteService.addDataInDataBaseBeforeStartApp();
    }

    @QueryMapping(name = "converteValute")
    public Double converteValute(@Argument String inputCharCode,
                                 @Argument String outputCharCode,
                                 @Argument double inputData) {
        return valuteService.getConverterValute(inputCharCode, outputCharCode, inputData);
    }

    @QueryMapping(name = "avgCourse")
    public Double avgCourse(@Argument String inputCharCode,
                            @Argument String outputCharCode) {
        return valuteService.getAvgCourse(inputCharCode, outputCharCode);
    }

    @QueryMapping(name = "numberOfConvertion")
    public Integer numberOfConvertion(@Argument String inputCharCode,
                                      @Argument String outputCharCode) {
        return valuteService.getNumberOfConvertion(inputCharCode, outputCharCode);
    }

    @QueryMapping(name = "historyOfConvertion")
    List<ConvertionHistory> historyOfConvertion(@Argument String inputCharCode,
                                                @Argument String outputCharCode) {
        return valuteService.getHistoryOfConvertion(inputCharCode, outputCharCode);
    }

}
