package org.arai.UnitTesting;

import org.arai.Persistence.Entities.Materia;
import org.arai.Persistence.Repositories.MateriaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
@SpringBootTest
public class MateriaTest {



    @Autowired
    private MateriaRepository materiaRepository;

    @Test
    @Transactional
    @Rollback(true)
    void testInsertMateria() {
        Materia materia = new Materia();
        materia.setNombre_materia("Matemáticas");
        Integer id = materiaRepository.insertMateria(materia);
        assertNotNull(id);
        assertTrue(id > 0);
        boolean existe = materiaRepository.findAllMaterias()
                .stream().anyMatch(m -> Objects.equals(m.getNombre_materia(), materia.getNombre_materia()));
        assertTrue(existe);
    }

    @Test
    @Transactional
    @Rollback(true)
    void testUpdateMateria() {
        Materia materia = new Materia();
        materia.setNombre_materia("Historia");
        Integer id = materiaRepository.insertMateria(materia);
        assertNotNull(id);
        materia.setId_materia(id);
        materia.setNombre_materia("Historia Universal");
        boolean actualizado = materiaRepository.updateMateria(materia);
        assertTrue(actualizado);
        boolean existe = materiaRepository.findAllMaterias()
                .stream().anyMatch(m -> Objects.equals(m.getNombre_materia(), "Historia Universal"));
        assertTrue(existe);
    }

    @Test
    @Transactional
    @Rollback(true)
    void testDeleteMateria() {
        Materia materia = new Materia();
        materia.setNombre_materia("Física");
        Integer id = materiaRepository.insertMateria(materia);
        assertNotNull(id);
        materia.setId_materia(id);
        boolean eliminado = materiaRepository.deleteMateria(materia);
        assertTrue(eliminado);
        boolean existe = materiaRepository.findAllMaterias()
                .stream().anyMatch(m -> Objects.equals(m.getId_materia(), id));
        assertFalse(existe);
    }

    @Test
    void testFindAllMaterias() {
        List<Materia> materias = materiaRepository.findAllMaterias();
        assertNotNull(materias);
        materias.forEach(System.out::println);
    }


}
