package org.arai.Persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.arai.Entities.Rol;
import org.arai.Exceptions.RoleNoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@Transactional
public class RolRepository {

    @PersistenceContext
    private EntityManager em;

    private static final Logger logger = LoggerFactory.getLogger(RolRepository.class);

    /**
     * Encuentra un rol por ID
     * @param id id del rol
     * @return devuelve el rol encontrado
     * @throws  RoleNoEncontradoException en caso de que no se encuentre el rol
     */
    public Optional<Rol> encontrarPorId(Integer id) {
        logger.debug("Encontrando por id {}", id);
        try{
            Rol rol = null;
            rol = em.find(Rol.class, id);
            if(Objects.isNull(rol)){
                logger.debug("Rol no encontrado {}", id);
            }
            return Optional.ofNullable(rol);
        }catch (NoResultException e){
            logger.debug("Se ha producido una excepcion intentando encontrar el error ; {}", e.getMessage(), e);
            throw new RoleNoEncontradoException("Rol no encontrado!!", e);
        }
    }


    public List<Rol> listarRoles(){

        logger.debug("Listando todos los roles");
        List<Rol> rols = new ArrayList<>();
        try {
         rols = em.createQuery("select r from Rol r").getResultList();
         return rols;

        } catch (Exception e) {
            logger.error("Erro ao listar roles", e);
        }
        return rols;
    }

}
