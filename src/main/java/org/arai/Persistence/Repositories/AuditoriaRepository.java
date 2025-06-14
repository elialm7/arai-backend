package org.arai.Persistence.Repositories;

import org.arai.Persistence.Entities.Auditoria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AuditoriaRepository {


    private Logger logger = LoggerFactory.getLogger(AuditoriaRepository.class);
    private final JdbcTemplate jdbcTemplate;

    public AuditoriaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Auditoria mapToAuditoria(ResultSet rs, int rownum) throws SQLException {
        var auditoria = new Auditoria();
        auditoria.setAuditoria_id(rs.getInt("auditoria_id"));
        auditoria.setUser_id(rs.getInt("user_id"));
        auditoria.setOperacion(rs.getString("operacion"));
        auditoria.setTimestamp(rs.getTimestamp("timestamp"));
        auditoria.setNombre_tabla(rs.getString("nombre_tabla"));
        auditoria.setFila_tabla(rs.getInt("fila_tabla"));
        auditoria.setNuevo_datos(rs.getString("nuevos_datos"));
        auditoria.setViejos_datos(rs.getString("viejos_datos"));
        auditoria.setIp_address(rs.getString("ip_address"));
        auditoria.setUser_agent(rs.getString("user_agent"));
        return auditoria;
    }

    /**
     * Recupera todas las auditorías almacenadas en la base de datos.
     *
     * @return Una lista de objetos Auditoria que representan las auditorías encontradas.
     *         Si ocurre un error al acceder a la base de datos, se devuelve una lista vacía.
     *
     * @throws DataAccessException Si ocurre un error al ejecutar la consulta SQL.
     */
    @Transactional(readOnly = true)
    public List<Auditoria> findAllAuditorias(){
        List<Auditoria> auditorias = new ArrayList<>();
        try{
         String sql = "SELECT * FROM tb_auditoria;";
         auditorias = jdbcTemplate.query(sql, this::mapToAuditoria);
        }catch (DataAccessException e){
            logger.error("Error al recuperar Auditorias de la base de datos", e);
        }
        return auditorias;
    }

    /**
     * Inserta una nueva auditoría en la base de datos.
     *
     * @param auditoria El objeto Auditoria que contiene los datos a insertar.
     *                  Debe incluir los siguientes campos:
     *                  - user_id: ID del usuario que realiza la operación.
     *                  - operacion: Descripción de la operación realizada.
     *                  - nombre_tabla: Nombre de la tabla afectada.
     *                  - fila_tabla: ID de la fila afectada en la tabla.
     *                  - nuevos_datos: Datos nuevos en formato JSON.
     *                  - viejos_datos: Datos antiguos en formato JSON.
     *                  - timestamp: Marca de tiempo de la operación.
     *                  - ip_address: Dirección IP del usuario.
     *                  - user_agent: Información del agente de usuario.
     *
     * @return El ID generado de la auditoría insertada si la operación es exitosa,
     *         o -1 si ocurre un error.
     *
     * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
     */
    @Transactional
    public Integer insertAuditoria(Auditoria auditoria){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = """
                INSERT INTO tb_auditoria(user_id, operacion, nombre_tabla, 
                                         fila_tabla, nuevos_datos, 
                                         viejos_datos, timestamp, 
                                         ip_address, user_agent) 
                VALUES (?,?,?,?,?::json,?::json,?,?,?)
                """;
        try {
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(sql, new String[]{"auditoria_id"});
                ps.setInt(1, auditoria.getUser_id());
                ps.setString(2, auditoria.getOperacion());
                ps.setString(3, auditoria.getNombre_tabla());
                ps.setInt(4, auditoria.getFila_tabla());
                ps.setString(5, auditoria.getNuevo_datos());
                ps.setString(6, auditoria.getViejos_datos());
                ps.setTimestamp(7, new Timestamp(auditoria.getTimestamp().getTime()));
                ps.setString(8, auditoria.getIp_address());
                ps.setString(9, auditoria.getUser_agent());
                return ps;
            }, keyHolder);
            Number key = keyHolder.getKey();
            return key != null ? key.intValue() : -1;
        } catch (DataAccessException e) {
            logger.error("Error al insertar Auditoria", e);
            return -1;
        }

    }




}
