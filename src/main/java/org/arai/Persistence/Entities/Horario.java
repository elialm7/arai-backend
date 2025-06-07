package org.arai.Persistence.Entities;

import lombok.Data;

import java.util.Date;

@Data
public class Horario {

    private Integer id_horario;
    private Integer id_grado_seccion;
    private Integer id_materia;
    private Integer user_id;

    private String dia;
    private String turno;


    private Date horario_inicio;
    private Date horario_fin;

}
