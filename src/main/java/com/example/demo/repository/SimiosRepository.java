package com.example.demo.repository;

import com.example.demo.model.Simios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SimiosRepository extends JpaRepository<Simios,Long> {
     Simios findByDna(String dna);
}
