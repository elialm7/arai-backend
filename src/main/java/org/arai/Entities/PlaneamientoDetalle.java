package org.arai.Entities;

import java.time.LocalDate;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_planeamiento_detalle")
@Getter
@Setter
public class PlaneamientoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_planeamiento_detalle")
    private Long id;

    @Column(name = "unidad_tematica")
    private String unidadTematica; 

    @Column(name = "fecha_desarrollo")
    private LocalDate fechaDesarrollo;


    @Column(name = "recursos")
    private String recursos;

    @Column(name = "observacion")
    private String observaciones; 
}
