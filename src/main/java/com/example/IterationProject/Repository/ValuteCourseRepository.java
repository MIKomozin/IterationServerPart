package com.example.IterationProject.Repository;

import com.example.IterationProject.Entity.Valute;
import com.example.IterationProject.Entity.ValuteCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ValuteCourseRepository extends JpaRepository<ValuteCourse, Integer> {
    ValuteCourse findValuteCourseByValuteAndDate(Valute valute, LocalDate date);

    List<ValuteCourse> findValuteCoursesByDate(LocalDate date);

    @Query(value = "SELECT valute_course.id AS id, valute_id, nominal, value, date FROM valute_course " +
            "JOIN valutes ON valute_id = valutes.id " +
            "WHERE name = ?1 AND date = ?2", nativeQuery = true)
    ValuteCourse findValuteCourseByNameAndDate(String name, LocalDate date);
}
