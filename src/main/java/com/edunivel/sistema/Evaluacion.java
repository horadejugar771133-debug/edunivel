package com.edunivel.sistema;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "evaluacion")
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_evaluacion;

    private int id_estudiante;
    private double matematica;
    private double comprension_lectora;
    private double razonamiento_logico;
    private double competencia_digital;
    private double puntaje_general;
    private String nivel;

    public Evaluacion() {}

    public int getId_evaluacion() { return id_evaluacion; }
    public void setId_evaluacion(int id_evaluacion) { this.id_evaluacion = id_evaluacion; }
    
    public int getId_estudiante() { return id_estudiante; }
    public void setId_estudiante(int id_estudiante) { this.id_estudiante = id_estudiante; }
    
    public double getMatematica() { return matematica; }
    public void setMatematica(double matematica) { this.matematica = matematica; }
    
    public double getComprension_lectora() { return comprension_lectora; }
    public void setComprension_lectora(double comprension_lectora) { this.comprension_lectora = comprension_lectora; }
    
    public double getRazonamiento_logico() { return razonamiento_logico; }
    public void setRazonamiento_logico(double razonamiento_logico) { this.razonamiento_logico = razonamiento_logico; }
    
    public double getCompetencia_digital() { return competencia_digital; }
    public void setCompetencia_digital(double competencia_digital) { this.competencia_digital = competencia_digital; }
    
    public double getPuntaje_general() { return puntaje_general; }
    public void setPuntaje_general(double puntaje_general) { this.puntaje_general = puntaje_general; }
    
    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }
}