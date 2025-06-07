package org.arai.Persistence.Entities;

import lombok.Data;

@Data
public class GradoSeccion {

    private Integer id_grado_seccion;
    private Integer id_grado_fk;
    private Integer id_seccion_fk;

    private String nombre_grado;
    private String nombre_seccion;

}
