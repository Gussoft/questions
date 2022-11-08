package com.gussoft.questions.controller;

import com.gussoft.questions.model.Categoria;
import com.gussoft.questions.service.CategoriaService;
import com.gussoft.questions.transfer.response.GenericResponse;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/categorias/")
    public ResponseEntity<Set<Categoria>> getAll() {
        return ResponseEntity.ok(categoriaService.getAllObj());
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable("id") Long id) {
        Categoria data = categoriaService.findObjById(id);
        if (data != null) {
            return ResponseEntity.ok(data);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/categorias/")
    public ResponseEntity<Categoria> saved(@Validated @RequestBody Categoria obj) {
        return ResponseEntity.ok(categoriaService.save(obj));
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<Categoria> updated(@Validated @RequestBody Categoria obj, @PathVariable("id") Long id) {
        Categoria data = categoriaService.findObjById(id);
        if (data != null) {
            return ResponseEntity.ok(categoriaService.update(obj, id));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<GenericResponse> deleted(@PathVariable("id") Long id) {
        Categoria data = categoriaService.findObjById(id);
        if (data != null) {
            categoriaService.delete(id);
            return ResponseEntity.ok(new GenericResponse(HttpStatus.OK.value(), "OK"));
        }
        return ResponseEntity.notFound().build();
    }

}
