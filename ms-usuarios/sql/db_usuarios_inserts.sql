-- ============================================================
-- DATOS DE PRUEBA - db_usuarios
-- MS-Usuarios (puerto 8081)
-- ⚠️ Ejecutar DESPUÉS de levantar ms-usuarios por primera vez
--    para que Hibernate genere las tablas automáticamente.
-- ============================================================

USE db_usuarios;

-- ------------------------------------------------------------
-- PERSONAS
-- ------------------------------------------------------------
INSERT INTO personas (nombre, apellido, email, telefono, tipo_documento, doc_identidad, direccion, distrito, provincia, departamento, fecha_reg, fecha_mod) VALUES
('Carlos',    'Ramirez Torres',   'carlos.ramirez@email.com',   '987654321', 'D', '45678901', 'Av. Lima 123',       'Miraflores',  'Lima',     'Lima',  NOW(), NOW()),
('Ana',       'Gutierrez Lopez',  'ana.gutierrez@email.com',    '912345678', 'D', '32165498', 'Jr. Cusco 456',      'San Isidro',  'Lima',     'Lima',  NOW(), NOW()),
('Luis',      'Paredes Quispe',   'luis.paredes@email.com',     '956123478', 'D', '78945612', 'Calle Arequipa 789', 'Surco',       'Lima',     'Lima',  NOW(), NOW()),
('Maria',     'Flores Chavez',    'maria.flores@email.com',     '945678123', 'D', '65412398', 'Av. Brasil 321',     'Lince',       'Lima',     'Lima',  NOW(), NOW()),
('Roberto',   'Mendoza Salas',    'roberto.mendoza@email.com',  '934567812', 'D', '12378945', 'Jr. Tacna 654',      'Barranco',    'Lima',     'Lima',  NOW(), NOW());

-- ------------------------------------------------------------
-- USUARIOS
-- ⚠️ Los passwords están hasheados con BCrypt.
--    Valor real de cada password:
--      admin123  → usuario: admin
--      user123   → usuario: jefa
--      pass123   → usuarios: lparedes, mflores, rmendoza
-- Si prefieres, crea los usuarios vía POST /api/usuarios/usuarios
-- y el sistema los hashea automáticamente.
-- ------------------------------------------------------------
INSERT INTO usuarios (idpersona, estado, nombre_usuario, password, fecha_reg, fecha_mod) VALUES
(1, 'ACT', 'admin',    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LPVdfy7kMTq', NOW(), NOW()),  -- admin123
(2, 'ACT', 'jefa',     '$2a$10$8K1p/a0dR7oxeVg9yV7YzOm0ORFRx3qL6.kfQZlONq5rHx5DwFG.e', NOW(), NOW()),  -- user123
(3, 'ACT', 'lparedes', '$2a$10$FRtEFz3mOp4N3C5lQA3JHuKXb8TNXo4kJbJHLiTUNYpuFCqHlhVNS', NOW(), NOW()),  -- pass123
(4, 'ACT', 'mflores',  '$2a$10$FRtEFz3mOp4N3C5lQA3JHuKXb8TNXo4kJbJHLiTUNYpuFCqHlhVNS', NOW(), NOW()),  -- pass123
(5, 'INA', 'rmendoza', '$2a$10$FRtEFz3mOp4N3C5lQA3JHuKXb8TNXo4kJbJHLiTUNYpuFCqHlhVNS', NOW(), NOW());  -- pass123

-- ------------------------------------------------------------
-- ACCESOS A MENUS
-- idmenu referencia menus en db_menus (MS-Menus)
-- ------------------------------------------------------------
INSERT INTO accesos_menus (idusuario, idmenu, estado, fecha_reg, fecha_mod) VALUES
(1, 'MNU001', 'ACT', NOW(), NOW()),
(1, 'MNU002', 'ACT', NOW(), NOW()),
(1, 'MNU003', 'ACT', NOW(), NOW()),
(1, 'MNU004', 'ACT', NOW(), NOW()),
(2, 'MNU001', 'ACT', NOW(), NOW()),
(2, 'MNU002', 'ACT', NOW(), NOW()),
(3, 'MNU001', 'ACT', NOW(), NOW()),
(3, 'MNU003', 'ACT', NOW(), NOW()),
(4, 'MNU002', 'INA', NOW(), NOW());
