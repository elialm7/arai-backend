package org.arai.Persistence.Entities;

import lombok.Data;

@Data
public class PlaneamientoActividad {

    private Integer id_plan_actividad;
    private Integer id_planeamiento_fk;

    private String actividad;
    private String actividad_Pertenece;
    private String actividad_realiza_tipo;
    private String desarrollo_directo;


}
