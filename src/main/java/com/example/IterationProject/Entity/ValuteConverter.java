package com.example.IterationProject.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "valute_converter")
public class ValuteConverter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_valute",columnDefinition = "VARCHAR(128) NOT NULL")
    private String firstValute;

    @Column(name = "second_valute",columnDefinition = "VARCHAR(128) NOT NULL")
    private String secondValute;

    @Column(name = "first_value",columnDefinition = "DOUBLE NOT NULL")
    private double firstValue;

    @Column(name = "second_value",columnDefinition = "DOUBLE NOT NULL")
    private double secondValue;

    @Column(columnDefinition = "DATE NOT NULL")
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstValute() {
        return firstValute;
    }

    public void setFirstValute(String firstValute) {
        this.firstValute = firstValute;
    }

    public String getSecondValute() {
        return secondValute;
    }

    public void setSecondValute(String secondValute) {
        this.secondValute = secondValute;
    }

    public double getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(double firstValue) {
        this.firstValue = firstValue;
    }

    public double getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(double secondValue) {
        this.secondValue = secondValue;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
