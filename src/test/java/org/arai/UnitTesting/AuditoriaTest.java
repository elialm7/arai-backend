package org.arai.UnitTesting;

import org.arai.Persistence.Entities.Auditoria;
import org.arai.Persistence.Repositories.AuditoriaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AuditoriaTest {
    @Autowired
    private AuditoriaRepository auditoriaRepository;


    @Test
    @Transactional
    @Rollback(true)
    void testInsertAuditoria() {
        Auditoria auditoria = new Auditoria();
        auditoria.setUser_id(1);
        auditoria.setOperacion("INSERT");
        auditoria.setNombre_tabla("tb_test");
        auditoria.setFila_tabla(1);
        auditoria.setNuevo_datos("{}");
        auditoria.setViejos_datos("{}");
        auditoria.setTimestamp(new Date());
        auditoria.setIp_address("127.0.0.1");
        auditoria.setUser_agent("JUnit");
        Integer id = auditoriaRepository.insertAuditoria(auditoria);
        assertNotNull(id);
        assertTrue(id > 0);
        boolean existe = auditoriaRepository.findAllAuditorias()
                .stream().anyMatch(aud -> Objects.equals(aud.getUser_id(), auditoria.getUser_id()));
        assertTrue(existe);
    }

    @Test
    void testFindAllAuditoria(){
        List<Auditoria> auditorias = auditoriaRepository.findAllAuditorias();
        assertNotNull(auditorias);
        auditorias.forEach(System.out::println);
    }


}
