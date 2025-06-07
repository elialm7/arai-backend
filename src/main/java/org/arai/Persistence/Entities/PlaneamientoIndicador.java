package org.arai.Persistence.Entities;

import lombok.Data;

@Data
public class PlaneamientoIndicador {

    private Integer id_plan_indicador;
    private Integer id_planeamiento_fk;
    private String indicador;

}
