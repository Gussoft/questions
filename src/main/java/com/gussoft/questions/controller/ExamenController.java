package com.gussoft.questions.controller;

import com.gussoft.questions.model.Categoria;
import com.gussoft.questions.model.Examen;
import com.gussoft.questions.service.ExamenService;
import com.gussoft.questions.transfer.response.GenericResponse;
import java.util.List;
import java.util.Set;
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
public class ExamenController {

    @Autowired
    private ExamenService examenService;

    @GetMapping("/examenes/")
    public ResponseEntity<Set<Examen>> getAll() {
        return ResponseEntity.ok(examenService.getAllObj());
    }

    @GetMapping("/examenes/{id}")
    public ResponseEntity<Examen> getTest(@PathVariable("id") Long id) {
        return ResponseEntity.ok(examenService.findObjById(id));
    }

    @PostMapping("/examenes/")
    public ResponseEntity<Examen> save(@RequestBody Examen examen) {
        return ResponseEntity.ok(examenService.save(examen));
    }

    @PutMapping("/examenes/{id}")
    public ResponseEntity<Examen> update(@RequestBody Examen examen, @PathVariable("id") Long id) {
        Examen data = examenService.findObjById(id);
        if (data != null) {
            return ResponseEntity.ok(examenService.update(examen, id));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/examenes/{id}")
    public ResponseEntity<GenericResponse> deleted(@PathVariable("id") Long id) {
        Examen data = examenService.findObjById(id);
        if (data != null) {
            examenService.delete(id);
            return ResponseEntity.ok(new GenericResponse(HttpStatus.OK.value(), "OK"));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/examenes/categoria/{id}")
    public ResponseEntity<List<Examen>> listTestCategory(@PathVariable("id") Long id) {
        Categoria data = new Categoria();
        data.setId(id);
        return ResponseEntity.ok(examenService.listTestOfCategory(data));
    }

    @GetMapping("/examenes/activo")
    public ResponseEntity<List<Examen>> listTestActives() {
        return ResponseEntity.ok(examenService.getTestActive());
    }

    @GetMapping("/examenes/categoria/activo/{id}")
    public ResponseEntity<List<Examen>> listTestActiveOfCategory(@PathVariable("id") Long id) {
        Categoria data = new Categoria();
        data.setId(id);
        return ResponseEntity.ok(examenService.getTestActiveOfCategory(data));
    }

}
