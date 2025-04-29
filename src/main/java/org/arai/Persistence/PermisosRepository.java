package org.arai.Persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.arai.Entities.Permiso;
import org.arai.Entities.Rol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class PermisosRepository {

    @PersistenceContext
    private EntityManager em;

    private Logger logger = LoggerFactory.getLogger(Rol.class);

    public List<Permiso> listarTodosLosPermisos(){
        logger.debug("Listando todos los permisos!");
        List<Permiso> permisos = new ArrayList<>();
        try {
            permisos = em.createQuery("select p from Permiso p").getResultList();
            return permisos;
        } catch (Exception e) {
            logger.error("Se produjo un error al listar todos los permisos: ", e);
        }
        return permisos;
    }
}
