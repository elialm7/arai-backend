package org.arai.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_usuarios")
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;
    


    @Column(name = "nombre")
    private String nombre; 

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "correo")
    private String correo; 

    @Column(name = "password")
    private String pssword;


    @OneToOne
    @JoinColumn(
        name = "id_rol_fk", 
        referencedColumnName = "role_id"
    )
    private Rol rol;


}
