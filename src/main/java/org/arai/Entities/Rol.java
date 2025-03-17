package org.arai.Entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "tb_roles")
@Getter
@Setter
@ToString
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long id;

    @Column(name = "nombre_rol", nullable = false, unique = true)
    private String nombreRol;

    // Un rol puede tener múltiples permisos
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "tb_rol_permisos",
        joinColumns = @JoinColumn(name = "id_rol_fk"),
        inverseJoinColumns = @JoinColumn(name = "id_permiso_fk")
    )
    private List<Permiso> permisos;
}
