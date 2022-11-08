package com.gussoft.questions.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pregunta", schema = "colegio")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pregunta_sequence")
    @SequenceGenerator(name = "pregunta_sequence", sequenceName = "pregunta_sequence")
    private Long id;

    @Column(length = 5000)
    private String contenido;

    private String imagen;

    private String opcion1;

    private String opcion2;

    private String opcion3;

    private String opcion4;

    @Transient
    private String respuestaDada;

    private String respuesta;

    //@JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Examen examen;

}
