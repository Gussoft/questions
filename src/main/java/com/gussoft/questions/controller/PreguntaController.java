package com.gussoft.questions.controller;

import com.gussoft.questions.model.Examen;
import com.gussoft.questions.model.Pregunta;
import com.gussoft.questions.service.ExamenService;
import com.gussoft.questions.service.PreguntaService;
import com.gussoft.questions.transfer.response.GenericResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import com.gussoft.questions.transfer.response.QuestionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class PreguntaController {

    @Autowired
    private PreguntaService preguntaService;

    @Autowired
    private ExamenService examenService;

    @GetMapping("/preguntas/examen/{id}")
    public ResponseEntity<List<Examen>> listQuestionsOfTest(@PathVariable("id") Long id) {
        Examen examen = examenService.findObjById(id);
        Set<Pregunta> preguntas = examen.getPreguntas();

        List examenes = new ArrayList(preguntas);
        if (examenes.size() > Integer.parseInt(examen.getNumeroPreguntas())) {
            examenes = examenes.subList(0, Integer.parseInt(examen.getNumeroPreguntas() + 1));
        }

        Collections.shuffle(examenes);
        return ResponseEntity.ok(examenes);
    }

    @GetMapping("/preguntas/{id}")
    public Pregunta listQuestionById(@PathVariable("id") Long id) {
        return preguntaService.findObjById(id);
    }

    @PostMapping("/preguntas/")
    public ResponseEntity<Pregunta> saved(@RequestBody Pregunta pregunta) {
        return ResponseEntity.ok(preguntaService.save(pregunta));
    }

    @PutMapping("/preguntas/{id}")
    public ResponseEntity<Pregunta> updated(@RequestBody Pregunta pregunta, @PathVariable("id") Long id) {
        Pregunta data = preguntaService.findObjById(id);
        if (data != null) {
            return ResponseEntity.ok(preguntaService.update(pregunta, id));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/preguntas/{id}")
    public ResponseEntity<GenericResponse> deleted(@PathVariable("id") Long id) {
        Pregunta data = preguntaService.findObjById(id);
        if (data != null) {
            preguntaService.delete(id);
            return ResponseEntity.ok(new GenericResponse(HttpStatus.OK.value(), "OK"));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/preguntas/examen/all/{id}")
    public ResponseEntity<Set<Pregunta>> listTestHowAdmin(@PathVariable("id") Long id) {
        Examen examen = new Examen();
        examen.setId(id);
        Set<Pregunta> test = preguntaService.getQuestionOfTest(examen);
        if (test.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(test);
    }

    @PostMapping("/preguntas/evaluar-examen")
    public ResponseEntity<QuestionResponse> evaluateTest(@RequestBody List<Pregunta> preguntas) {
        double pointMax = 0;
        Integer trueQuestions = 0;
        Integer trys = 0;

        for (Pregunta question : preguntas) {
            Pregunta pregunta = this.preguntaService.listQuestions(question.getId());
            if (pregunta.getRespuesta().equals(question.getRespuestaDada())) {
                trueQuestions++;
                double puntos = Double.parseDouble(preguntas.get(0).getExamen().getPuntosMaximos()) / preguntas.size();
                pointMax += puntos;
            }
            if (question.getRespuestaDada() != null) {
                trys++;
            }
        }

        return ResponseEntity.ok(new QuestionResponse(pointMax, trueQuestions, trys));
    }
}
