package org.arai.Persistence.Repositories;

import org.arai.Persistence.Entities.Auditoria;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AuditoriaRepository {


    private final JdbcTemplate jdbcTemplate;

    public AuditoriaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Auditoria mapToAuditoria(ResultSet rs) throws SQLException {

        var usuario = new Auditoria();
        usuario.setAuditoria_id(rs.getInt("auditoria_id"));
        usuario.setUser_id(rs.getInt("user_id"));
        usuario.setOperacion(rs.getString("operacion"));


        return usuario;

    }







}
