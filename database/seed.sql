
--SEED--

--PERMISOS--ADMIN--
INSERT INTO tb_permisos(nombre_permiso, descripcion_permiso) VALUES ('ALL', 'Tiene  acceso a todo')
                                                            ON CONFLICT (nombre_permiso) DO NOTHING;

--ROLE-ADMIN--
INSERT INTO tb_roles(nombre_rol) VALUES('ADMIN')
                                ON CONFLICT (nombre_rol) DO NOTHING;

--ADMIN-SEED--
INSERT INTO tb_rol_permisos(id_rol_fk, id_permiso_fk) VALUES (1,1)
                                                      ON CONFLICT (id_rol_fk, id_permiso_fk) DO NOTHING ;
INSERT INTO tb_usuarios(id_rol_fk, nombre, apellido, username, password, correo, cedula) VALUES (1,'ADMIN', 'ADMIN', 'ADMIN', 'ADMIN', 'ADMIN@hakesystems.com', 'NO APLICA')
                                                                                         ON CONFLICT(username, correo, cedula) DO NOTHING;

--OTHER INITIAL SEEDING HERE ---88

