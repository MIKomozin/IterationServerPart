package com.example.IterationProject.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "valutes")
public class Valute {

    @Id
    @Column(columnDefinition = "VARCHAR(128) NOT NULL")
    private String name;

    @Column(name = "num_code",columnDefinition = "INT NOT NULL")
    private Integer numCode;

    @Column(name = "char_code", columnDefinition = "VARCHAR(5) NOT NULL")
    private String charCode;

    @Column(columnDefinition = "INT NOT NULL")
    private Integer nominal;

    @OneToMany(mappedBy = "valute")
    List<ValuteCourse> valuteCourseList;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumCode() {
        return numCode;
    }

    public void setNumCode(Integer numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public Integer getNominal() {
        return nominal;
    }

    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }

    public List<ValuteCourse> getValuteCourseList() {
        return valuteCourseList;
    }

    public void setValuteCourseList(List<ValuteCourse> valuteCourseList) {
        this.valuteCourseList = valuteCourseList;
    }
}
