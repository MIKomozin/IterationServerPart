package com.example.IterationProject.Repository;

import com.example.IterationProject.Entity.Valute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValuteRepository extends JpaRepository<Valute, Integer> {

    Valute findValuteByName(String name);
}
