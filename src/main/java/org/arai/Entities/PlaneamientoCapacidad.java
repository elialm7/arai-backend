package org.arai.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table(name = "tb_planeamiento_capacidades")
@Getter
@Setter
public class PlaneamientoCapacidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plan_cap")
    private Long id;

    @Column(name = "capacidad", nullable = false)
    private String capacidad;

    @ManyToOne
    @JoinColumn(name = "id_planeamiento_fk", nullable = false)
    private Planeamiento planeamiento;

}
