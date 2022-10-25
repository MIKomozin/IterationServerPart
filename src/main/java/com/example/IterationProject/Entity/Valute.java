package com.example.IterationProject.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "valutes")
public class Valute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(128) NOT NULL")
    private String name;

    @Column(name = "num_code",columnDefinition = "INT NOT NULL")
    private Integer numCode;

    @Column(name = "char_code", columnDefinition = "VARCHAR(5) NOT NULL")
    private String charCode;

    @OneToMany(mappedBy = "valute")
    List<ValuteCourse> valuteCourseList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public List<ValuteCourse> getValuteCourseList() {
        return valuteCourseList;
    }

    public void setValuteCourseList(List<ValuteCourse> valuteCourseList) {
        this.valuteCourseList = valuteCourseList;
    }
}
