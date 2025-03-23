package org.arai;

import static org.junit.jupiter.api.Assertions.*;

import org.arai.Entities.Usuario;
import org.arai.Exceptions.UsuarioNoEncontradoException;
import org.arai.Persistence.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class UsuarioPrueba {

    @Autowired
    UsuarioRepository repository;

    @Test
    public void testUsuarioPorUsername(){

      
        Usuario userName = new Usuario();
        userName.setUsername("ELIKAWA");
        Usuario useUsuario = repository.buscarPorUsername(userName).get();
        assertNotNull(useUsuario);
        assertEquals("ELIKAWA", useUsuario.getUsername());
    }

    @Test
    public void testUsuarioNoEncontrado(){


        UsuarioNoEncontradoException noEncontrdo = assertThrows(UsuarioNoEncontradoException.class, 
            () ->{
                Usuario user = new Usuario();
                user.setUsername("random");
                repository.buscarPorUsername(user);
            }
        );

        assertNotNull(noEncontrdo);


    }
}
