package org.arai.Persistence.Entities;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class Auditoria {
    private Integer auditoria_id;
    private Integer user_id;
    private String operacion;
    private Date timestamp;
    private String nombre_tabla;
    private Integer fila_tabla;
    private String nuevo_datos;
    private String viejos_datos;
    private String ip_address;
    private String user_agent;

}
