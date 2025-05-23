package org.arai.Persistence;

import org.arai.Entities.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UsuarioRepository {


    private JdbcTemplate  jdbcTemplate;

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
        String sql = "select * from tb_usuarios";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapToUsuario(rs));
    }

    @Transactional(readOnly = true)
    public Usuario findUsuarioByCedula(String cedula){
        String sql = "SELECT * FROM tb_usuarios WHERE cedula = ?;";
        return jdbcTemplate.queryForObject(sql,(rs, rownum)->mapToUsuario(rs),cedula);
    }

    @Transactional()
    public Integer updateUsuarioById(Usuario usuario){

        String sql  = """
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
    }


    @Transactional
    public void createUsuario(Usuario usuario) {
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
                usuario.getCedula()
        );

    }


}
