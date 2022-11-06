package com.example.IterationProject.Repository;

import com.example.IterationProject.Entity.ConvertionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConvertionHistoryRepository extends JpaRepository<ConvertionHistory, Integer> {

    @Query(value = "SELECT * FROM convertion_history " +
            "WHERE have_valute = ?1 AND want_valute = ?2", nativeQuery = true)
    List<ConvertionHistory> findListOfPairValutes(String inputName, String outputName);
}
