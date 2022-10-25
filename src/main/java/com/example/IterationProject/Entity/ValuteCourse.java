package com.example.IterationProject.Entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "valute_course")
public class ValuteCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "valute_id", referencedColumnName = "id")
    private Valute valute;

    @Column(columnDefinition = "INT NOT NULL")
    private Integer nominal;

    @Column(columnDefinition = "REAL NOT NULL")
    private Double value;

    @Column(columnDefinition = "DATE NOT NULL")
    private LocalDate date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Valute getValute() {
        return valute;
    }

    public void setValute(Valute valute) {
        this.valute = valute;
    }

    public Integer getNominal() {
        return nominal;
    }

    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
