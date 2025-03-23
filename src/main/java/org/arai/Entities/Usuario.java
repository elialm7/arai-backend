package org.arai.Entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_usuarios")
@Getter
@Setter
@ToString
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;
    
    @Column(name = "username")
    private String username;

    @Column(name = "nombre")
    private String nombre; 

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "correo")
    private String correo; 

    @Column(name = "password")
    private String pssword;



    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Horario> horarios; 

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Planeamiento> planeamientos;


    @OneToMany(mappedBy = "usuario" , fetch = FetchType.EAGER)
    private List<PlaneamientoComentario> comentarios;

       // Un usuario tiene un único rol
    @ManyToOne
    @JoinColumn(name = "id_rol_fk", nullable = false)
    private Rol rol;


    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Token> tokens;


}
