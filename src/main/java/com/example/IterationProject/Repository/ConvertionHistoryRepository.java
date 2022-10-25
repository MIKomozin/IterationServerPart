package com.example.IterationProject.Repository;

import com.example.IterationProject.Entity.ConvertionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConvertionHistoryRepository extends JpaRepository<ConvertionHistory, Integer> {

    @Query(value = "SELECT count(*) FROM convertion_history " +
            "WHERE have_valute = ?1 AND want_valute = ?2 " +
            "AND date >= (CURRENT_DATE - 6) AND date <= CURRENT_DATE", nativeQuery = true)
    Integer findNumberOfConvertion(String inputName, String outputName);

    @Query(value = "SELECT * FROM convertion_history " +
            "WHERE have_valute = ?1 AND want_valute = ?2 " +
            "ORDER BY date DESC", nativeQuery = true)
    List<ConvertionHistory> findHistoryOfConvertion(String inputName, String outputName);

    @Query(value = "SELECT AVG(course) FROM convertion_history " +
            "WHERE have_valute = ?1 AND want_valute = ?2 " +
            "AND date >= (CURRENT_DATE - 6) AND date <= CURRENT_DATE", nativeQuery = true)
    Double findAvgCourse(String inputName, String outputName);
}
