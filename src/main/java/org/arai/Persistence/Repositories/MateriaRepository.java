package org.arai.Persistence.Repositories;

import org.arai.Persistence.Entities.Materia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MateriaRepository {

    private Logger logger = LoggerFactory.getLogger(MateriaRepository.class);
    private final JdbcTemplate jdbcTemplate;

    public MateriaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Materia mapToMateria(ResultSet rs, int rownum) throws SQLException {
        var materia = new Materia();
        materia.setId_materia(rs.getInt("id_materia"));
        materia.setNombre_materia(rs.getString("nombre_materia"));
        return materia;
    }


    @Transactional(readOnly = true)
    public List<Materia> findAllMaterias(){
        List<Materia> materias = new ArrayList<>();
        try{
            String sql = "SELECT * FROM tb_materia;";
            materias = jdbcTemplate.query(sql, this::mapToMateria);
        }catch(DataAccessException e){
            logger.error("Error al recuperar materias de la base de datos", e);
        }
        return materias;
    }

    @Transactional
    public Integer insertMateria(Materia materia){
        String sql = "INSERT INTO tb_materia (nombre_materia) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id_materia"});
                ps.setString(1, materia.getNombre_materia());
                return ps;
            }, keyHolder);
            return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : -1;
        } catch (DataAccessException e) {
            logger.error("Error al insertar materia", e);
            return -1;// Retorna -1 en caso de error
        }
    }
    @Transactional
    public boolean updateMateria(Materia materia){
        String sql = "UPDATE tb_materia SET nombre_materia = ? WHERE id_materia = ?;";
        try{
            int rowsAffected = jdbcTemplate.update(sql, materia.getNombre_materia(), materia.getId_materia());
            return rowsAffected > 0; // Retorna true si se actualizó al menos una fila
        } catch (DataAccessException e) {
            logger.error("Error al actualizar materia", e);
            return false; // Retorna false en caso de error
        }
    }

    @Transactional
    public boolean deleteMateria(Materia materia){
        String sql = "DELETE FROM tb_materia WHERE id_materia = ?;";
        try {
            int rowsAffected = jdbcTemplate.update(sql, materia.getId_materia());
            return rowsAffected > 0; // Retorna true si se eliminó al menos una fila
        } catch (DataAccessException e) {
            logger.error("Error al eliminar materia", e);
            return false; // Retorna false en caso de error
        }
    }

}
