package com.example.project_3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OmistajaRepository extends JpaRepository<Omistaja, Long> {
    @Query("SELECT o FROM Omistaja o WHERE o.etunimi = :etunimi AND o.sukunimi = :sukunimi")
    Omistaja findByEtuJaSukunimi(@Param("etunimi") String etunimi, @Param("sukunimi") String sukunimi);
}
