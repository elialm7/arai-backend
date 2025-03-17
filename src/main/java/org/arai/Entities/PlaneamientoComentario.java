package org.arai.Entities;

import java.util.List;

import org.hibernate.mapping.Join;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_planeamiento_comentario")
@Getter
@Setter
public class PlaneamientoComentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_planeamiento_comentario")
    private Long id;

    @Column(name = "comentario", nullable = false)
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "id_planeamiento_fk", nullable = false)
    private Planeamiento planeamiento;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private Usuario usuario;


}
