package org.arai.UnitTesting;

import org.arai.Persistence.Entities.Usuario;
import org.arai.Persistence.QueryResults.UsuarioPermisoResult;
import org.arai.Persistence.Repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UsuarioRepositoryTest {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @Transactional
    @Rollback(true)
    void testInsertUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId_rol_fk(1);
        usuario.setApellido("Pérez");
        usuario.setCorreo("test@correo.com");
        usuario.setNombre("Juan");
        usuario.setPassword("1234");
        usuario.setUsername("juanp");
        usuario.setCedula("1234567890");

        Integer id = usuarioRepository.insertUsuario(usuario);
        assertNotNull(id);
        assertTrue(id > 0);

        List<Usuario> usuarios = usuarioRepository.findAllUsuarios();
        boolean existe = usuarios.stream().anyMatch(u -> Objects.equals(u.getCorreo(), usuario.getCorreo()));
        assertTrue(existe);
    }

    @Test
    @Transactional
    @Rollback(true)
    void testUpdateUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId_rol_fk(1);
        usuario.setApellido("López");
        usuario.setCorreo("update@correo.com");
        usuario.setNombre("Ana");
        usuario.setPassword("abcd");
        usuario.setUsername("anal");
        usuario.setCedula("0987654321");

        Integer id = usuarioRepository.insertUsuario(usuario);
        assertNotNull(id);
        assertTrue(id > 0);

        usuario.setId_user(id);
        usuario.setNombre("Ana Actualizada");
        usuario.setCorreo("actualizado@correo.com");
        Integer actualizado = usuarioRepository.updateUsuario(usuario);
        assertTrue(actualizado > 0);

        List<Usuario> usuarios = usuarioRepository.findAllUsuarios();
        boolean existe = usuarios.stream().anyMatch(u -> Objects.equals(u.getCorreo(), "actualizado@correo.com"));
        assertTrue(existe);
    }

    @Test
    @Transactional
    @Rollback(true)
    void testFindUsuarioByCedula() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId_rol_fk(1);
        usuario.setApellido("García");
        usuario.setCorreo("buscar@correo.com");
        usuario.setNombre("Pedro");
        usuario.setPassword("pass");
        usuario.setUsername("pedrog");
        usuario.setCedula("1122334455");

        Integer id = usuarioRepository.insertUsuario(usuario);
        assertNotNull(id);
        assertTrue(id > 0);

        Optional<Usuario> encontrado = usuarioRepository.findUsuarioByCedula("1122334455");
        assertNotNull(encontrado.get());
        assertEquals("Pedro", encontrado.get().getNombre());
    }

    @Test
    void testFindAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAllUsuarios();
        assertNotNull(usuarios);
        usuarios.forEach(System.out::println);
    }

    @Test
    void testgetUsuarioPermisos() {
        Optional<UsuarioPermisoResult> result = usuarioRepository.getUsuarioPermisoData(1);
        assertTrue(result.isPresent());
        UsuarioPermisoResult usuarioPermisoResult = result.get();
        assertEquals("ADMIN", usuarioPermisoResult.getNombreRol());
        System.out.println(usuarioPermisoResult.getPermisos());
    }

    @Test
    void testUsuarioBatchfind(){
        List<Usuario> usuarios = usuarioRepository.findAllUsuariosBatch(0, 10);
        assertNotNull(usuarios);
        assertTrue(usuarios.size() <= 10);
        usuarios.forEach(System.out::println);
    }

}
