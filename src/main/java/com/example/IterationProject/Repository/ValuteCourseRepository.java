package com.example.IterationProject.Repository;

import com.example.IterationProject.Entity.Valute;
import com.example.IterationProject.Entity.ValuteCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ValuteCourseRepository extends JpaRepository<ValuteCourse, Integer> {
    ValuteCourse findValuteCourseByValuteAndDate(Valute valute, Date date);
}
