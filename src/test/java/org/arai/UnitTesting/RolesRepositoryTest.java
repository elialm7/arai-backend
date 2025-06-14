package org.arai.UnitTesting;

import org.arai.Persistence.Repositories.RolRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RolesRepositoryTest {

    @Autowired
    private RolRepository  rolRepository;



    @Test
    void testFindAll() {
        var roles = rolRepository.findAllRoles();
        roles.forEach(System.out::println);
    }

}
