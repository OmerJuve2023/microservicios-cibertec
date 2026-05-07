-- ============================================================
-- DATOS DE PRUEBA - db_aplicaciones
-- MS-Aplicaciones (puerto 8083)
-- ⚠️ Ejecutar DESPUÉS de levantar ms-aplicaciones por primera vez.
-- ⚠️ idcliente en cliente_aplicacion referencia clientes en db_clientes.
--    Asegúrate de haber insertado los clientes primero.
-- ============================================================

USE db_aplicaciones;

-- ------------------------------------------------------------
-- APLICACIONES
-- idaplicacion: String ID definido manualmente
-- ------------------------------------------------------------
INSERT INTO aplicaciones (idaplicacion, nombre, estado, fecha_reg, fecha_mod) VALUES
('APP001', 'Sistema de Ventas',         'ACT', NOW(), NOW()),
('APP002', 'Portal de Clientes',        'ACT', NOW(), NOW()),
('APP003', 'Modulo de Reportes',        'ACT', NOW(), NOW()),
('APP004', 'Gestor de Inventario',      'ACT', NOW(), NOW()),
('APP005', 'Panel Administrativo',      'INA', NOW(), NOW());

-- ------------------------------------------------------------
-- CLIENTE - APLICACION (Contratos)
-- idcliente: referencia a clientes en db_clientes (MS-Clientes)
-- periodoUso: 'M' = Mensual, 'A' = Anual
-- estado: 'ACT' = activo, 'INA' = inactivo
-- ------------------------------------------------------------
INSERT INTO cliente_aplicacion (idcliente, idaplicacion, periodouso, fecha_ini, fecha_fin, estado, preciocontrato, dominio, url, fecha_reg, fecha_mod) VALUES
(1, 'APP001', 'M', '2024-01-01', '2024-12-31', 'ACT', 299.90,  'carlos-retail',    'https://carlos-retail.app.com',    NOW(), NOW()),
(1, 'APP002', 'A', '2024-01-01', '2024-12-31', 'ACT', 1200.00, 'carlos-portal',    'https://carlos-portal.app.com',    NOW(), NOW()),
(2, 'APP001', 'M', '2024-03-01', '2025-02-28', 'ACT', 299.90,  'ana-edu',          'https://ana-edu.app.com',          NOW(), NOW()),
(3, 'APP001', 'A', '2024-01-15', '2025-01-14', 'ACT', 2999.00, 'techsolutions',    'https://techsolutions.app.com',    NOW(), NOW()),
(3, 'APP003', 'A', '2024-01-15', '2025-01-14', 'ACT', 1500.00, 'techsol-reportes', 'https://techsol-rep.app.com',      NOW(), NOW()),
(3, 'APP004', 'M', '2024-06-01', '2025-05-31', 'ACT', 450.00,  'techsol-inv',      'https://techsol-inv.app.com',      NOW(), NOW()),
(4, 'APP002', 'A', '2024-02-01', '2025-01-31', 'ACT', 1200.00, 'flores-portal',    'https://flores-portal.app.com',    NOW(), NOW()),
(5, 'APP001', 'M', '2023-01-01', '2023-12-31', 'INA', 299.90,  'mendoza-cons',     'https://mendoza-cons.app.com',     NOW(), NOW());
