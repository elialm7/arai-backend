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

--OTHER INITIAL SEEDING HERE ---

-- Permisos extra --
-- DIRECTOR
INSERT INTO tb_permisos(nombre_permiso, descripcion_permiso) VALUES
('VIEW_ALL_PLANS', 'Puede ver todos los planes docentes'),
('REVIEW_PLAN', 'Puede revisar planes docentes enviados'),
-- Estos solo por si acaso, pienso en la parte de notificar docentes sobre sus planes
('APPROVE_PLAN', 'Puede aprobar planes docentes'),
('REJECT_PLAN', 'Puede rechazar planes docentes'),
-- Este pienso para profesores suplentes
('ASSIGN_HELPERS', 'Puede asignar ayudantes a docentes')
ON CONFLICT (nombre_permiso) DO NOTHING;

-- DOCENTE
INSERT INTO tb_permisos(nombre_permiso, descripcion_permiso) VALUES
('CREATE_PLAN', 'Puede crear su propio plan docente'),
('EDIT_OWN_PLAN', 'Puede editar su propio plan docente'),
('VIEW_OWN_PLAN', 'Puede ver su propio plan docente'),
('DELETE_OWN_PLAN', 'Puede eliminar su propio plan docente'),
-- De nuevo por si hacemos lo de que el director valide los planes
('SUBMIT_PLAN', 'Puede enviar su plan docente para revisión')
ON CONFLICT (nombre_permiso) DO NOTHING;

-- AYUDANTE
INSERT INTO tb_permisos(nombre_permiso, descripcion_permiso) VALUES
('VIEW_ASSIGNED_DOCENTE_PLANS', 'Puede ver los planes de los docentes asignados'),
('EDIT_ASSIGNED_DOCENTE_PLANS', 'Puede editar planes de docentes asignados'),
('CREATE_DRAFT_PLAN', 'Puede crear borradores de planes docentes')
ON CONFLICT (nombre_permiso) DO NOTHING;

-- Roles
INSERT INTO tb_roles(nombre_rol) VALUES
('DIRECTOR'),
('DOCENTE'),
('AYUDANTE')
ON CONFLICT (nombre_rol) DO NOTHING;

-- Intermedia
-- Relacion permisos-rol Director
INSERT INTO tb_rol_permisos(id_rol_fk, id_permiso_fk)
SELECT r.id_rol, p.id_permiso
FROM tb_roles r, tb_permisos p
WHERE r.nombre_rol = 'DIRECTOR'
  AND p.nombre_permiso IN ('VIEW_ALL_PLANS', 'REVIEW_PLAN', 'APPROVE_PLAN', 'REJECT_PLAN', 'ASSIGN_HELPERS')
ON CONFLICT (id_rol_fk, id_permiso_fk) DO NOTHING;

-- Relacion permisos-rol docente
INSERT INTO tb_rol_permisos(id_rol_fk, id_permiso_fk)
SELECT r.id_rol, p.id_permiso
FROM tb_roles r, tb_permisos p
WHERE r.nombre_rol = 'DOCENTE'
  AND p.nombre_permiso IN ('CREATE_PLAN', 'EDIT_OWN_PLAN', 'VIEW_OWN_PLAN', 'DELETE_OWN_PLAN', 'SUBMIT_PLAN')
ON CONFLICT (id_rol_fk, id_permiso_fk) DO NOTHING;

-- Relacion permisos-rol ayudante
INSERT INTO tb_rol_permisos(id_rol_fk, id_permiso_fk)
SELECT r.id_rol, p.id_permiso
FROM tb_roles r, tb_permisos p
WHERE r.nombre_rol = 'AYUDANTE'
  AND p.nombre_permiso IN ('VIEW_ASSIGNED_DOCENTE_PLANS', 'EDIT_ASSIGNED_DOCENTE_PLANS', 'CREATE_DRAFT_PLAN')
ON CONFLICT (id_rol_fk, id_permiso_fk) DO NOTHING;
