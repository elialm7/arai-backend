package org.arai.Persistence.Entities;

import lombok.Data;

import java.util.Date;

@Data
public class Token {

    private Integer id_token;
    private Boolean activo;
    private Date expiracion;
    private String tipo_token;
    private String token;
    private Integer id_user_fk;

}
