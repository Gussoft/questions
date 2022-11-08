package com.gussoft.questions.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "movement", schema = "colegio")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movement_sequence")
    @SequenceGenerator(name = "movement_sequence", sequenceName = "movement_sequence")
    private Long id;

    private String operationNumber;

    private BigDecimal amount;

    private LocalDate dateProcess;

}
