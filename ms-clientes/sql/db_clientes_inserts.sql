-- ============================================================
-- DATOS DE PRUEBA - db_clientes
-- MS-Clientes (puerto 8082)
-- ⚠️ Ejecutar DESPUÉS de levantar ms-clientes por primera vez.
-- ⚠️ idpersona referencia personas en db_usuarios (MS-Usuarios).
--    Asegúrate de haber insertado las personas primero.
-- ============================================================

USE db_clientes;

-- ------------------------------------------------------------
-- CLIENTES
-- idpersona: referencia a personas en MS-Usuarios
--   1 = Carlos Ramirez  → cliente persona natural
--   2 = Ana Gutierrez   → cliente persona natural
--   3 = Luis Paredes    → representante de empresa
-- tipoCliente: 'PN' = Persona Natural, 'PJ' = Persona Jurídica
-- ------------------------------------------------------------
INSERT INTO clientes (idpersona, razon_social, tipo_cliente, sector, representante_legal, estado, fecha_reg, fecha_mod) VALUES
(1, 'Carlos Ramirez Torres',          'PN', 'Retail',      'Carlos Ramirez Torres',   'ACT', NOW(), NOW()),
(2, 'Ana Gutierrez Lopez',            'PN', 'Educacion',   'Ana Gutierrez Lopez',     'ACT', NOW(), NOW()),
(3, 'Tech Solutions SAC',             'PJ', 'Tecnologia',  'Luis Paredes Quispe',     'ACT', NOW(), NOW()),
(4, 'Distribuidora Flores EIRL',      'PJ', 'Comercio',    'Maria Flores Chavez',     'ACT', NOW(), NOW()),
(5, 'Consultora Mendoza & Asociados', 'PJ', 'Consultoria', 'Roberto Mendoza Salas',   'INA', NOW(), NOW());
