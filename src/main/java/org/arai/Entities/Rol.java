package org.arai.Entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_permisos")
@Getter
@Setter
public class Rol {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id; 


    @Column(name = "descripcion")
    private String descripcion;

    @OneToOne(mappedBy = "rol")
    private Usuario usuario; 


    @ManyToMany
    @JoinTable(
        name = "tb_permiso_roles", 
        joinColumns = @JoinColumn(name="id_role_fk"), 
        inverseJoinColumns = @JoinColumn(name="id_permiso_fk")

    )
    private List<Permiso> permisos;

}
