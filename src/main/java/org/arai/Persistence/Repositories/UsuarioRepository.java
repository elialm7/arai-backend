package org.arai.Persistence.Repositories;

import org.arai.Dto.users.UsuarioPermisoResult;
import org.arai.Exceptions.DatabaseException;
import org.arai.Persistence.Entities.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        usuario.setCedula(rs.getString("cedula"));
        return usuario;

    }

    private Optional<UsuarioPermisoResult> mapToUsuarioPermisoResult(ResultSet resultSet, int i) throws SQLException {
        var usuarioPermisoResult = new UsuarioPermisoResult();
        usuarioPermisoResult.setId_usuario(resultSet.getInt("usuario_id"));
        usuarioPermisoResult.setNombreRol(resultSet.getString("rol_nombre"));
        usuarioPermisoResult.setPermisos(Arrays.asList((String[])resultSet.getArray("permisos").getArray()));
        return Optional.of(usuarioPermisoResult);
    }

    /**
     * Recupera todos los usuarios de la base de datos.
     *
     * @return Una lista de objetos `Usuario` que contiene los datos de todos los usuarios.
     *         Si ocurre un error al acceder a la base de datos, se devuelve una lista vacía.
     */
    @Transactional(readOnly = true)
    public List<Usuario> findAllUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();;
        try {
            String sql = "select * from tb_usuarios";
            usuarios = jdbcTemplate.query(sql, (rs, rowNum) -> mapToUsuario(rs));
        } catch (DataAccessException e) {
            log.error("Error al recuperar usuarios de la base de datos", e);
        }
        return usuarios;
    }

    /**
     * Recupera un lote de usuarios de la base de datos, paginando los resultados.
     *
     * @param offset El desplazamiento inicial para la paginación (número de filas a omitir).
     * @param limit  El número máximo de usuarios a recuperar.
     * @return Una lista de objetos `Usuario` que contiene los datos de los usuarios recuperados.
     *         Si ocurre un error al acceder a la base de datos, se devuelve una lista vacía.
     */
    public List<Usuario> findAllUsuariosBatch(Integer offset, Integer limit) {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tb_usuarios ORDER BY id_user LIMIT ? OFFSET ?;";
            usuarios = jdbcTemplate.query(sql, (rs, rowNum) -> mapToUsuario(rs), limit, offset);
        }catch (EmptyResultDataAccessException e) {
            log.warn("No se encontraron usuarios en la base de datos para el offset {} y limit {}", offset, limit);
        } catch (DataAccessException e) {
            log.error("Error al recuperar usuarios de la base de datos", e);
            throw new DatabaseException("Error al momento de ejecutar la operacion en la base de datos ", e);
        }
        return usuarios;
    }


    /**
     * Busca un usuario en la base de datos por su cédula.
     *
     * @param cedula La cédula del usuario a buscar.
     * @return Un objeto `Optional<Usuario>` que contiene el usuario encontrado, o vacío si no se encuentra.
     *         Si ocurre un error al acceder a la base de datos, se registra el error y se devuelve un `Optional.empty()`.
     */
    @Transactional(readOnly = true)
    public Optional<Usuario> findUsuarioByCedula(String cedula){
        try {
            String sql = "SELECT * FROM tb_usuarios WHERE cedula = ?;";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, (rs, rownum) -> mapToUsuario(rs), cedula));
        } catch (EmptyResultDataAccessException e) {
            log.error("Error al buscar usuario por cedula: {}", cedula, e);
            return Optional.empty();
        }catch (DataAccessException e) {
            log.error("Error al acceder a la base de datos al buscar usuario por cedula: {}", cedula, e);
            throw new DatabaseException("Error al moment de ejecutar la operacion en la base de datos ", e);
        }
    }
    /**
     * Actualiza los datos de un usuario en la base de datos.
     *
     * @param usuario Un objeto `Usuario` que contiene los datos actualizados del usuario.
     *                Los campos requeridos son: `id_user`, `id_rol_fk`, `apellido`, `correo`,
     *                `nombre`, `password` y `username`.
     * @return Un entero que indica el número de filas afectadas por la operación de actualización.
     *         Devuelve -1 si ocurre un error durante la operación.
     */
    @Transactional()
    public Integer updateUsuario(Usuario usuario){
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
        } catch (DataAccessException e) {
            log.error("Error al actualizar usuario: {}", e.getMessage());
            return -1;
        }
    }


    /**
     * Inserta un nuevo usuario en la base de datos.
     *
     * @param usuario Un objeto `Usuario` que contiene los datos del usuario a insertar.
     *                Los campos requeridos son: `id_rol_fk`, `apellido`, `correo`, `nombre`,
     *                `password`, `username` y `cedula`.
     * @return El identificador único (`id_user`) del usuario insertado si la operación es exitosa,
     *         o -1 si ocurre un error durante la inserción.
     */
    @Transactional
    public Integer insertUsuario(Usuario usuario) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
                String sql = """
                        INSERT INTO tb_usuarios(
                        id_rol_fk,
                        apellido,
                        correo,
                        nombre,
                        password,
                        username,
                        cedula)VALUES (?,  ?, ?, ?, ?, ?, ?);
                   """;

            jdbcTemplate.update(con -> {
                    var ps = con.prepareStatement(sql, new String[]{"id_user"});
                    ps.setInt(1, usuario.getId_rol_fk());
                    ps.setString(2, usuario.getApellido());
                    ps.setString(3, usuario.getCorreo());
                    ps.setString(4, usuario.getNombre());
                    ps.setString(5, usuario.getPassword());
                    ps.setString(6, usuario.getUsername());
                    ps.setString(7, usuario.getCedula());
                    return ps;
                }, keyHolder
            );
            return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : -1;
        } catch (Exception e) {
            log.error("Error al insertar usuario: {}", e.getMessage());
            return -1;
        }

    }

    /**
     * Recupera los datos de permisos asociados a un usuario específico.
     *
     * @param user_id El identificador único del usuario para el cual se desean obtener los datos de permisos.
     * @return Un objeto `Optional<UsuarioPermisoResult>` que contiene los datos del usuario, su rol y los permisos asociados.
     *         Si ocurre un error o no se encuentran datos, se devuelve un `Optional.empty()`.
     */
    @Transactional(readOnly = true)
    public Optional<UsuarioPermisoResult> getUsuarioPermisoData(Integer user_id){
        try {

            String sql = """
                    SELECT
                        u.id_user  as usuario_id,
                        r.nombre_rol as rol_nombre,
                        array_agg(p.nombre_permiso ORDER BY p.nombre_permiso) as permisos
                    FROM tb_usuarios u
                    INNER JOIN tb_roles r ON r.id_rol = u.id_rol_fk
                    INNER JOIN tb_rol_permisos rp ON rp.id_rol_fk = r.id_rol
                    INNER JOIN tb_permisos p ON  p.id_permiso = rp.id_permiso_fk
                    WHERE  u.id_user = ?
                    GROUP BY u.id_user, r.nombre_rol;
                    """;
            return jdbcTemplate.queryForObject(sql, this::mapToUsuarioPermisoResult, user_id);
        }catch (DataAccessException e){
            log.error("Error al obtener datos de usuario y permisos: {}", e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * Busca un usuario en la base de datos por su ID.
     *
     * @param idUsuario El ID del usuario a buscar.
     * @return Un objeto `Optional<Usuario>` que contiene el usuario encontrado, o vacío si no se encuentra.
     *         Si ocurre un error al acceder a la base de datos, se registra el error y se lanza una excepción.
     */
    @Transactional(readOnly = true)
    public Optional<Usuario> findUsuarioById(Integer idUsuario) {
        try {
            String sql = "SELECT * FROM tb_usuarios WHERE id_user = ?;";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, (rs, rowNum) -> mapToUsuario(rs), idUsuario));
        } catch (EmptyResultDataAccessException e) {
            log.warn("No se encontró usuario con ID: {}", idUsuario);
            return Optional.empty();
        } catch (DataAccessException e) {
            log.error("Error al acceder a la base de datos al buscar usuario por ID: {}", idUsuario, e);
            throw new DatabaseException("Error al momento de ejecutar la operacion en la base de datos ", e);
        }
    }

    /**
     * Elimina un usuario de la base de datos por su ID.
     *
     * @param idUsuario El ID del usuario a eliminar.
     * @return Un entero que indica el número de filas afectadas por la operación de eliminación.
     *         Si ocurre un error durante la operación, se registra el error y se lanza una excepción.
     */
    @Transactional()
    public Integer deleteUsuarioById(Integer idUsuario) {
        try {
            String sql = "DELETE FROM tb_usuarios WHERE id_user = ?;";
            return jdbcTemplate.update(sql, idUsuario);
        } catch (DataAccessException e) {
            log.error("Error al eliminar usuario con ID: {}", idUsuario, e);
            throw new DatabaseException("Error al momento de ejecutar la operacion en la base de datos ", e);
        }
    }

}
