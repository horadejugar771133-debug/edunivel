package com.edunivel.sistema;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EvaluacionRepository extends JpaRepository<Evaluacion, Integer> {

    @Query("SELECT e FROM Evaluacion e WHERE e.nivel = 'BAJO'")
    List<Evaluacion> buscarEstudiantesNecesitanAyuda();

    List<Evaluacion> findByNivel(String nivel);
}