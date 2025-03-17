package org.arai.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_planeamiento_indicadores")
@Getter
@Setter
public class PlaneamientoIndicador {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plan_indicadores")
    private Long id;

    @Column(name = "indicador", nullable = false)
    private String indicador;

    @ManyToOne
    @JoinColumn(name = "id_planeamiento_fk", nullable = false)
    private Planeamiento planeamiento;

}
