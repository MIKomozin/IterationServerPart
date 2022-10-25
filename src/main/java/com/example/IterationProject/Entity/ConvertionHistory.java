package com.example.IterationProject.Entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "convertion_history")
public class ConvertionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "have_valute",columnDefinition = "VARCHAR(128) NOT NULL")
    private String haveValute;

    @Column(name = "want_valute",columnDefinition = "VARCHAR(128) NOT NULL")
    private String wantValute;

    @Column(name = "have_value",columnDefinition = "REAL NOT NULL")
    private Double haveValue;

    @Column(name = "want_value",columnDefinition = "REAL NOT NULL")
    private Double wantValue;

    @Column(columnDefinition = "REAL NOT NULL")
    private Double course;

    @Column(columnDefinition = "DATE NOT NULL")
    private LocalDate date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHaveValute() {
        return haveValute;
    }

    public void setHaveValute(String haveValute) {
        this.haveValute = haveValute;
    }

    public String getWantValute() {
        return wantValute;
    }

    public void setWantValute(String wantValute) {
        this.wantValute = wantValute;
    }

    public Double getHaveValue() {
        return haveValue;
    }

    public void setHaveValue(Double haveValue) {
        this.haveValue = haveValue;
    }

    public Double getWantValue() {
        return wantValue;
    }

    public void setWantValue(Double wantValue) {
        this.wantValue = wantValue;
    }

    public Double getCourse() {
        return course;
    }

    public void setCourse(Double course) {
        this.course = course;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
