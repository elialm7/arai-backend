package org.arai.Entities;

import org.arai.Enums.DesarrolloRealiza;
import org.arai.Enums.PerteneceActividad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_planeamiento_actividades")
@Getter
@Setter
public class PlaneamientoActividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plan_act")
    private Long id;

    @Column(name = "actividad")
    private String actividad;

    @Enumerated(EnumType.STRING)
    @Column(name = "actividad_pertenece_tipo")
    private PerteneceActividad perteneceActividad;


    @Enumerated(EnumType.STRING)
    @Column(name = "actividad_realiza_tipo")
    private DesarrolloRealiza realizaTipo; 

    @Column(name = "desarrollo_directo")
    private String desarrollo_directo; 


    @ManyToOne
    @JoinColumn(name = "id_planeamiento_fk")
    private Planeamiento planeamiento;



}
