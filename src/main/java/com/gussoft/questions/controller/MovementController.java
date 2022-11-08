package com.gussoft.questions.controller;

import com.gussoft.questions.model.Movement;
import com.gussoft.questions.model.dto.Details;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class MovementController {

/*    @Autowired
    private JobLauncher jobLotMovementsLauncher;

    @Autowired
    private Job processLotMovementsJob;

    @PostMapping("/movement/")
    public ResponseEntity<Long> saveBatch() throws Exception {
        JobExecution execution = jobLotMovementsLauncher
                .run(processLotMovementsJob, new JobParametersBuilder()
                        .addLong("idInit", System.nanoTime())
                        .toJobParameters());
        return ResponseEntity.ok(execution.getId());
    }*/

    @GetMapping(value = {"/movement/", "/movement/{id}"})
    public ResponseEntity<Movement> getMovement(@PathVariable(name = "id", required = false) Long id
                                                ,@RequestParam(defaultValue = "Guss", required = false) String name
                                                ,@RequestPart(name = "amount", required = false) String amount) {
        String op = "";
        double salary = 0;
        if (amount != null) {
            salary = Double.parseDouble(amount);
        }
        double total = salary + 1200.0;
        BigDecimal pago = new BigDecimal(total);
        if (id != null) {
            op = "Mostar uno por Id y name: " + name;
        } else {
            op = "Mostrar todo : " + name;
            id = 0L;
        }
        return ResponseEntity.ok(new Movement(id, op, pago, LocalDate.now()));
    }

    @GetMapping("/details")
    public ResponseEntity<Details> getDetails(@RequestBody Details details) {
        System.out.println(details);
        return ResponseEntity.ok(details);
    }
}
