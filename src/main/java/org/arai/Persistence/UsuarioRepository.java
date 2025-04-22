package org.arai.Persistence;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;
import org.arai.AraiBackendApplication;
import org.arai.Entities.Usuario;
import org.arai.Exceptions.UsuarioNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UsuarioRepository {


    @PersistenceContext
    private EntityManager manager;


    /**
     * 
     * @param usuario con el parametro de username cargado
     * @returnn ${Usuario} si encuentra el usuario
     * @throws UsuarioNoEncontradoException si no encuentra el usuario.
     */
    public Optional<Usuario> buscarPorUsername(Usuario usuario){

        try{
            Usuario user = manager
            .createQuery("SELECT u FROM Usuario u WHERE u.username = :username", Usuario.class)
            .setParameter("username", usuario.getUsername()).getSingleResult();
            return Optional.of(user);
        }catch(NoResultException e){
            throw new UsuarioNoEncontradoException("No se ha encontrado el usuario con username: " + usuario.getUsername(), e);
        }
        
    }


    public List<Usuario> obtenertodos() {


        try{
            List<Usuario> usuarios = manager.createQuery("SELECT u FROM Usuario u").getResultList();
            return usuarios;
        }catch (NoResultException e){
            throw new UsuarioNoEncontradoException("No hay usuarios!!", e);

        }


    }
}
