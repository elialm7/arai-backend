package org.arai.Persistence.Entities;

import lombok.Data;

@Data
public class Planeamiento {

    private Integer id_planeamiento;
    private Integer id_user_fk;
    private Integer grado_seccion_fk;
    private Integer materia_id;
    private Integer plan_detalle_id;
    private String raw_file;


}
