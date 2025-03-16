package org.arai.Entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_permisos")
@Getter
@Setter
public class Permiso {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso")
    private Long id; 


    @Column(name = "nombre_permiso")
    private String nombrePermiso;


    @Column(name = "descripcion_permiso")
    private String descripcionPermiso;

    @ManyToMany(mappedBy = "permisos")
    private List<Rol> roles;


}
