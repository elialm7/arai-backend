package org.arai.Persistence.Repositories;

import org.arai.Persistence.Entities.Permiso;
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
public class PermisosRepository {

    private final Logger logger = LoggerFactory.getLogger(RolRepository.class);
    private JdbcTemplate jdbcTemplate;
    public PermisosRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Permiso mapToPermiso(ResultSet rs, int rownum) throws SQLException {
        var permiso = new Permiso();
        permiso.setId_permiso(rs.getInt("id_permiso"));
        permiso.setNombre(rs.getString("nombre_permiso"));
        permiso.setDescripcion(rs.getString("descripcion_permiso"));
        return permiso;
    }

    /**
     * Recupera todos los registros de la tabla `tb_roles`.
     *
     * @return Una lista de objetos `Rol` que representan todos los registros
     *         en la tabla `tb_roles`. Si ocurre un error, se devuelve una lista vacía.
     * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
     */
    @Transactional(readOnly = true)
    public List<Permiso> findAllPermisos(){
        List<Permiso> permisos = new ArrayList<>();
        try {

            String sql = "SELECT * FROM tb_permisos;";
            permisos = jdbcTemplate.query(sql, this::mapToPermiso);
        }catch (DataAccessException e){
            logger.error("Error al recuperar permisos de la base de datos", e);
        }
        return permisos;
    }




}
