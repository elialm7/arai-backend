package org.arai.Persistence.Repositories;

import org.arai.Persistence.Entities.Rol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RolRepository {
    private Logger logger = LoggerFactory.getLogger(RolRepository.class);
    private final JdbcTemplate jdbcTemplate;
    public RolRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private Rol mapToRol(ResultSet rs, int rownum) throws SQLException {
        var rol = new Rol();
        rol.setId_rol(rs.getInt("id_rol"));
        rol.setNombre(rs.getString("nombre_rol"));
        return rol;
    }

    @Transactional
    public List<Rol> findAllRoles() {
        List<Rol> roles = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tb_roles;";
            roles = jdbcTemplate.query(sql, this::mapToRol);
        } catch (DataAccessException e) {
            logger.error("Error al recuperar roles de la base de datos", e);
        }
        return roles;
    }


}
