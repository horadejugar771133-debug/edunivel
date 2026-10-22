package com.edunivel.sistema;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class ControladorWeb {

    private final EstudianteRepository estudianteRepository;
    private final EvaluacionRepository evaluacionRepository;

    // Constructor injection
    public ControladorWeb(EstudianteRepository estudianteRepository, 
                         EvaluacionRepository evaluacionRepository) {
        this.estudianteRepository = estudianteRepository;
        this.evaluacionRepository = evaluacionRepository;
    }

    @GetMapping("/")
    public String paginaPrincipal(Model model) {
        model.addAttribute("titulo", "Bienvenido a EduNivel");
        model.addAttribute("mensaje", "Sistema para determinar el nivel de preparación académica");
        return "index";
    }

    @GetMapping("/registro")
    public String paginaRegistro() {
        return "registro";
    }

    @PostMapping("/guardar-estudiante")
    public String guardarEstudiante(
            @RequestParam String codigo,
            @RequestParam String curso,
            @RequestParam String paralelo,
            @RequestParam int gestion,
            Model model) {
        
        try {
            Estudiante nuevo = new Estudiante(codigo, curso, paralelo, gestion);
            estudianteRepository.save(nuevo);
            model.addAttribute("mensaje", "✅ Estudiante registrado con éxito: " + codigo);
        } catch (Exception e) {
            model.addAttribute("mensaje", " Error al registrar: " + e.getMessage());
        }
        return "confirmacion";
    }

    @GetMapping("/evaluacion")
    public String paginaEvaluacion(Model model) {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        model.addAttribute("estudiantes", estudiantes);
        return "evaluacion";
    }

    @PostMapping("/procesar-evaluacion")
    public String procesarEvaluacion(
            @RequestParam int id_estudiante,
            @RequestParam double matematica,
            @RequestParam double comprension_lectora,
            @RequestParam double razonamiento_logico,
            @RequestParam double competencia_digital,
            Model model) {
        
        try {
            double puntaje_general = (matematica + comprension_lectora + razonamiento_logico + competencia_digital) / 4.0;
            
            String nivel;
            if (puntaje_general >= 75) {
                nivel = "ALTO";
            } else if (puntaje_general >= 50) {
                nivel = "MEDIO";
            } else {
                nivel = "BAJO";
            }
            
            Evaluacion evaluacion = new Evaluacion();
            evaluacion.setId_estudiante(id_estudiante);
            evaluacion.setMatematica(matematica);
            evaluacion.setComprension_lectora(comprension_lectora);
            evaluacion.setRazonamiento_logico(razonamiento_logico);
            evaluacion.setCompetencia_digital(competencia_digital);
            evaluacion.setPuntaje_general(puntaje_general);
            evaluacion.setNivel(nivel);
            
            evaluacionRepository.save(evaluacion);
            
            model.addAttribute("mensaje", "✅ Evaluación registrada. Nivel: " + nivel + " (Puntaje: " + String.format("%.2f", puntaje_general) + ")");
            model.addAttribute("nivel", nivel);
            
        } catch (Exception e) {
            model.addAttribute("mensaje", "❌ Error: " + e.getMessage());
        }
        
        return "confirmacion";
    }

    @GetMapping("/ayuda")
    public String estudiantesNecesitanAyuda(Model model) {
        List<Evaluacion> estudiantesBajos = evaluacionRepository.buscarEstudiantesNecesitanAyuda();
        model.addAttribute("estudiantesAyuda", estudiantesBajos);
        return "ayuda";
    }

    @GetMapping("/evaluaciones")
    public String todasLasEvaluaciones(Model model) {
        List<Evaluacion> todas = evaluacionRepository.findAll();
        model.addAttribute("evaluaciones", todas);
        return "evaluaciones";
    }
}