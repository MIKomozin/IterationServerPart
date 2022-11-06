package com.example.IterationProject.Repository;

import com.example.IterationProject.Entity.Valute;
import com.example.IterationProject.Entity.ValuteCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ValuteCourseRepository extends JpaRepository<ValuteCourse, Integer> {
    ValuteCourse findValuteCourseByValuteAndDate(Valute valute, LocalDate date);

    @Query(value = "SELECT * FROM valute_course WHERE date = ?1", nativeQuery = true)
    List<ValuteCourse> findValuteCoursesByDate(LocalDate date);

    @Query(value = "SELECT valute_course.id AS id, valute_id, nominal, value, date FROM valute_course " +
            "JOIN valutes ON valute_id = valutes.id " +
            "WHERE char_code = ?1 AND date = ?2", nativeQuery = true)
    ValuteCourse findValuteCourseByCharCodeAndDate(String charCode, LocalDate date);

    @Query(value = "SELECT * FROM valute_course ORDER BY date DESC LIMIT 1", nativeQuery = true)
    ValuteCourse findValuteCourseWithActualDate();

}
