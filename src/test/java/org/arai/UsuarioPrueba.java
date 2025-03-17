package org.arai;

import static org.junit.jupiter.api.Assertions.*;

import org.arai.Entities.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class UsuarioPrueba {

    @Autowired
    private EntityManager manager;

    @Test
    public void test(){
        Long userid = 1L; 
        Usuario user = manager.find(Usuario.class, userid);
        assertEquals(user.getNombre(), "rodolfo");
        assertEquals(user.getRol().getNombreRol(), "ADMIN");
        assertEquals(user.getRol().getPermisos().get(0).getNombrePermiso(), "ALL");

    }
}
