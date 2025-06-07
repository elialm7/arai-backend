package org.arai.Persistence.Entities;


import lombok.Data;

import java.util.Date;

@Data
public class PlaneamientoDetalle {

    private Integer id_planeamiento_detalle;
    private Date fecha_desarrrollo;
    private String observacion;
    private String recursos;
    private String unidad_tematica;


}
