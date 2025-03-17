package org.arai.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_grado_seccion")
@Getter
@Setter
public class GradoSeccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grado_seccion")
    private Long id; 


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_grado_fk")
    private Grado grado; 

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_seccion_fk")
    private Seccion seccion;



}
