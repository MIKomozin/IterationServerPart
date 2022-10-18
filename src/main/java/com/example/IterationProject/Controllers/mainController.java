package com.example.IterationProject.Controllers;

import com.example.IterationProject.Entity.Valute;
import com.example.IterationProject.Service.ValuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class mainController {

    private ValuteService valuteService;

    @Autowired
    public mainController(ValuteService valuteService) {
        this.valuteService = valuteService;
    }

    @GetMapping("/showValutes")
    public ResponseEntity<List<Valute>> showListOfValutes() {
        valuteService.addDataInDataBase();
        return ResponseEntity.ok(valuteService.getAllValute());
    }

}
