package org.arai.UnitTesting;

import org.arai.Persistence.Entities.Grado;
import org.arai.Persistence.Repositories.GradoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class GradoTest {


    @Autowired
    private GradoRepository gradoRepository;


    @Test
    @Transactional
    @Rollback(true)
    void testInsertGrado() {
        Grado grado = new Grado();
        grado.setNombre_grado("Test Grado");
        Integer id = gradoRepository.insertGrado(grado);
        assertNotNull(id);
        assertTrue(id > 0);
        boolean existe = gradoRepository.findAllGrados()
                .stream().anyMatch(g -> Objects.equals(g.getNombre_grado(), grado.getNombre_grado()));
        assertTrue(existe);
    }

    @Test
    @Transactional
    @Rollback(true)
    void testUpdateGrado() {
        Grado grado = new Grado();
        grado.setNombre_grado("Original");
        Integer id = gradoRepository.insertGrado(grado);
        assertNotNull(id);
        grado.setGrado_id(id);
        grado.setNombre_grado("Actualizado");
        boolean actualizado = gradoRepository.updateGrado(grado);
        assertTrue(actualizado);
        boolean existe = gradoRepository.findAllGrados()
                .stream().anyMatch(g -> Objects.equals(g.getNombre_grado(), "Actualizado"));
        assertTrue(existe);
    }

    @Test
    @Transactional
    @Rollback(true)
    void testDeleteGrado() {
        Grado grado = new Grado();
        grado.setNombre_grado("ParaEliminar");
        Integer id = gradoRepository.insertGrado(grado);
        assertNotNull(id);
        boolean eliminado = gradoRepository.deleteGrado(id);
        assertTrue(eliminado);
        boolean existe = gradoRepository.findAllGrados()
                .stream().anyMatch(g -> Objects.equals(g.getGrado_id(), id));
        assertFalse(existe);
    }

    @Test
    void testFindAllGrados() {
        List<Grado> grados = gradoRepository.findAllGrados();
        assertNotNull(grados);
        grados.forEach(System.out::println);
    }

}
