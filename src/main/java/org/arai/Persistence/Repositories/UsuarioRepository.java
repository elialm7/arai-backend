package org.arai.Persistence.Repositories;

import org.arai.Persistence.Entities.Usuario;
import org.arai.Exceptions.UsuarioNoEncontradoException;
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
public class UsuarioRepository {


    private JdbcTemplate  jdbcTemplate;
    private Logger log = LoggerFactory.getLogger(UsuarioRepository.class);
    public UsuarioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Usuario mapToUsuario(ResultSet rs) throws SQLException {
        var usuario = new Usuario();
        usuario.setId_user(rs.getInt("id_user"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setApellido(rs.getString("apellido"));
        usuario.setCorreo(rs.getString("correo"));
        usuario.setPassword(rs.getString("password"));
        usuario.setId_rol_fk(rs.getInt("id_rol_fk"));
        usuario.setUsername(rs.getString("username"));
        return usuario;

    }

    @Transactional(readOnly = true)
    public List<Usuario> findAllUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();;
        try {
            String sql = "select * from tb_usuarios";
            usuarios = jdbcTemplate.query(sql, (rs, rowNum) -> mapToUsuario(rs));
        } catch (DataAccessException e) {
            log.error(e.getMessage());
        }
        return usuarios;
    }

    @Transactional(readOnly = true)
    public Usuario findUsuarioByCedula(String cedula) throws UsuarioNoEncontradoException {
        try {
            String sql = "SELECT * FROM tb_usuarios WHERE cedula = ?;";
            return jdbcTemplate.queryForObject(sql, (rs, rownum) -> mapToUsuario(rs), cedula);
        } catch (DataAccessException e) {
            throw new UsuarioNoEncontradoException("No se encontro el usuario", e);
        }
    }

    @Transactional()
    public Integer updateUsuarioById(Usuario usuario){
        try {
            String sql = """
                    UPDATE tb_usuarios
                    	SET id_rol_fk=?, apellido=?, correo=?, nombre=?, password=?, username=?
                    	WHERE id_user = ?;
                """;
            return jdbcTemplate.update(
                    sql,
                    usuario.getId_rol_fk(),
                    usuario.getApellido(),
                    usuario.getCorreo(),
                    usuario.getNombre(),
                    usuario.getPassword(),
                    usuario.getUsername(),
                    usuario.getId_user()
            );
        } catch (Exception e) {
            log.error(e.getMessage());
            return -1;
        }
    }


    @Transactional
    public void createUsuario(Usuario usuario) {
        try {
                String sql = """
                    
                        INSERT INTO tb_usuarios(
                        id_rol_fk,
                        id_user, 
                        apellido, 
                        correo, 
                        nombre,
                        password, 
                        username, 
                        cedula)
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?);
       
    
                   """;

            jdbcTemplate.update(sql,
                    usuario.getId_rol_fk(),
                    usuario.getId_user(),
                    usuario.getApellido(),
                    usuario.getCorreo(),
                    usuario.getNombre(),
                    usuario.getPassword(),
                    usuario.getUsername(),
                    usuario.
                getCedula()
            );
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }


}
