package org.arai.Persistence.Entities;

import lombok.Data;

@Data
public class PlaneamientoCapacidad {

    private Integer id_plan_capacidad;
    private Integer id_planeamiento_fk;
    private String  capacidad;

}
