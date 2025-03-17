package org.arai.Entities;

import java.time.LocalTime;

import org.arai.Enums.DiaSemana;
import org.arai.Enums.Turno;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_horario")
@Getter
@Setter
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horario")
    private Long id; 


    @OneToOne
    @JoinColumn(name = "id_materia")
    private Materia materia;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Usuario usuario;


    @OneToOne
    @JoinColumn(name = "grado_seccion_id")
    private GradoSeccion gradoSeccion;


    @Enumerated(EnumType.STRING)
    @Column(name = "dia")
    private DiaSemana dia;


    @Enumerated(EnumType.STRING)
    @Column(name = "turno")
    private Turno turno;


    @Column(name = "horario_inicio")
    private LocalTime horarioInicio;

    @Column(name = "horario_fin")
    private LocalTime horarioFin;




}
