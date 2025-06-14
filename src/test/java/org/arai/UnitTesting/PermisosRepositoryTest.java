package org.arai.UnitTesting;

import org.arai.Persistence.Repositories.PermisosRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PermisosRepositoryTest {

    @Autowired
    private PermisosRepository permisosRepository;


    @Test
    void testFindAllPermisos() {
        var permisos = permisosRepository.findAllPermisos();
        permisos.forEach(System.out::println);
    }


}
