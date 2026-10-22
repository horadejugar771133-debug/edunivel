package com.edunivel.sistema;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "estudiante")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_estudiante;

    private String codigo;
    private String curso;
    private String paralelo;
    private int gestion;

    public Estudiante() {}

    public Estudiante(String codigo, String curso, String paralelo, int gestion) {
        this.codigo = codigo;
        this.curso = curso;
        this.paralelo = paralelo;
        this.gestion = gestion;
    }

    public int getId_estudiante() { return id_estudiante; }
    public void setId_estudiante(int id_estudiante) { this.id_estudiante = id_estudiante; }
    
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    
    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }
    
    public String getParalelo() { return paralelo; }
    public void setParalelo(String paralelo) { this.paralelo = paralelo; }
    
    public int getGestion() { return gestion; }
    public void setGestion(int gestion) { this.gestion = gestion; }
}