package org.arai.Persistence.Entities;

import lombok.Data;

@Data
public class PlaneamientoComentario {

    private Integer id_planemaiento_comentario;
    private Integer id_planeamiento_fk;
    private Integer root_comment_id;
    private Integer id_user;
    private String comentario;

}
