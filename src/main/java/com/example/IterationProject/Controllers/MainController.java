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
    public Double converteValute(@Argument String inputName,
                                 @Argument String outputName,
                                 @Argument double inputData) {
        return valuteService.getConverterValute(inputName, outputName, inputData);
    }

    @QueryMapping(name = "avgCourse")
    public Double avgCourse(@Argument String inputName,
                            @Argument String outputName) {
        return valuteService.getAvgCourse(inputName, outputName);
    }

    @QueryMapping(name = "numberOfConversion")
    public Integer numberOfConversion(@Argument String inputName,
                                      @Argument String outputName) {
        return valuteService.getNumberOfConvertion(inputName, outputName);
    }

    @QueryMapping(name = "historyOfConversion")
    List<ConvertionHistory> historyOfConversion(@Argument String inputName,
                                                @Argument String outputName) {
        return valuteService.getHistoryOfConvertion(inputName, outputName);
    }

}
