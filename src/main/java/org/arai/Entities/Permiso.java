package org.arai.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_permisos")
@Getter
@Setter
@ToString
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso")
    private Long id;

    @Column(name = "nombre_permiso", nullable = false, unique = true)
    private String nombrePermiso;

    @Column(name = "descripcion_permiso")
    private String descripcionPermiso;
}
