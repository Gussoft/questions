package com.gussoft.questions.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "examen", schema = "colegio")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "examen_sequence")
    @SequenceGenerator(name = "examen_sequence", sequenceName = "examen_sequence")
    private Long id;

    private String titulo;

    private String descripcion;

    private String puntosMaximos;

    private String numeroPreguntas;

    private boolean activo = false;

    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoria;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "examen")
    @JsonIgnore
    private Set<Pregunta> preguntas = new HashSet<>();

}
