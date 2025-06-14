package org.arai.Persistence.Repositories;


import org.arai.Persistence.Entities.Grado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GradoRepository {

    private Logger logger  = LoggerFactory.getLogger(GradoRepository.class);
    private final JdbcTemplate jdbcTemplate;

    public GradoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Grado mapToGrado(ResultSet rs, int rownum)throws SQLException {
        var grado = new  Grado();
        grado.setGrado_id(rs.getInt("id_grado"));
        grado.setNombre_grado(rs.getString("nombre_grado"));
        return grado;
    }

    /**
     * Recupera todos los registros de la tabla `tb_grado`.
     *
     * @return Una lista de objetos `Grado` que representan todos los registros
     *         en la tabla `tb_grado`. Si ocurre un error, se devuelve una lista vacía.
     * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
     */
    @Transactional(readOnly = true)
    public List<Grado> findAllGrados(){
        List<Grado> grados = new ArrayList<>();
        try{
            String sql = "SELECT * FROM tb_grado;";
            grados = jdbcTemplate.query(sql,this::mapToGrado);
        }catch(DataAccessException e){
            logger.error("Error al recuperar grados ", e);
        }
        return grados;
    }

    /**
     * Inserta un nuevo registro en la tabla `tb_grado`.
     *
     * @param grado El objeto `Grado` que contiene los datos del grado a insertar,
     *              específicamente el nombre del grado.
     * @return El ID generado del nuevo registro si la inserción fue exitosa,
     *         o `-1` si ocurre un error o no se genera un ID.
     * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
     */
    @Transactional
    public Integer insertGrado(Grado grado) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO tb_grado (nombre_grado) VALUES (?)";
        try {
             jdbcTemplate.update(con -> {
                var ps = con.prepareStatement(sql, new String[]{"id_grado"});
                ps.setString(1, grado.getNombre_grado());
                return ps;
            }, keyHolder);
            return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : -1;
        } catch (DataAccessException e) {
            logger.error("Error al insertar grado: {}", grado, e);
            return -1;
        }
    }

    /**
     * Actualiza un registro en la tabla `tb_grado` basado en el ID del grado.
     *
     * @param grado El objeto `Grado` que contiene los datos actualizados, incluyendo
     *              el ID del grado y el nuevo nombre del grado.
     * @return `true` si la actualización fue exitosa (al menos una fila afectada),
     *         `false` en caso contrario o si ocurre un error.
     * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
     */
    @Transactional
    public boolean updateGrado(Grado grado) {
        String sql = "UPDATE tb_grado SET nombre_grado = ? WHERE id_grado = ?";
        try {
            int filasAfectadas = jdbcTemplate.update(sql, grado.getNombre_grado(), grado.getGrado_id());
            return filasAfectadas > 0;
        } catch (DataAccessException e) {
            logger.error("Error al actualizar grado: {}", grado, e);
            return false;
        }
    }

    /**
     * Elimina un registro de la tabla `tb_grado` basado en su ID.
     *
     * @param gradoId El ID del grado que se desea eliminar.
     * @return `true` si la eliminación fue exitosa (al menos una fila afectada),
     *         `false` en caso contrario o si ocurre un error.
     * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
     */
    @Transactional
    public boolean deleteGrado(Integer gradoId) {
        String sql = "DELETE FROM tb_grado WHERE id_grado = ?";
        try {
            int filasAfectadas = jdbcTemplate.update(sql, gradoId);
            return filasAfectadas > 0;
        } catch (DataAccessException e) {
            logger.error("Error al eliminar grado con ID: {}", gradoId, e);
            return false;
        }
    }


}
