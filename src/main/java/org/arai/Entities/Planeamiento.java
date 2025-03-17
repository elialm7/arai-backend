package org.arai.Entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Entity
@Table(name = "tb_planeamiento")
@Getter
@Setter
public class Planeamiento {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_planeamiento")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "id_user")
    private Usuario usuario;  //un usuario puede tener muchos planeamientos(many:this, to one: usuario)

    @OneToOne 
    @JoinColumn(name = "materia_id")
    private Materia materia; 


    @OneToOne
    @JoinColumn(name = "grado_seccion_id")
    private GradoSeccion gradoSeccion; 



    @OneToOne
    @JoinColumn(name = "plan_detalle_id")
    private PlaneamientoDetalle detalle;


    @Column(name = "raw_file")
    private String raw_file_url ;


    @OneToMany(mappedBy = "planeamiento")
    private List<PlaneamientoActividad> actividades;

    @OneToMany(mappedBy = "planeamiento")
    private List<PlaneamientoCapacidad> capacidades; 

    @OneToMany(mappedBy = "planeamiento")
    private List<PlaneamientoIndicador> indicadores; 

    @OneToMany(mappedBy = "planeamiento")
    private List<PlaneamientoComentario> comentarios; 


}
