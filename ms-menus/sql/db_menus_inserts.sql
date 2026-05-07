-- ============================================================
-- DATOS DE PRUEBA - db_menus
-- MS-Menus (puerto 8084)
-- ⚠️ Ejecutar DESPUÉS de levantar ms-menus por primera vez.
-- ⚠️ idaplicacion referencia aplicaciones en db_aplicaciones.
--    Asegúrate de haber insertado las aplicaciones primero.
-- ============================================================

USE db_menus;

-- ------------------------------------------------------------
-- MENUS
-- nivelMenu: '1' = menú principal (padre), '2' = submenu (hijo)
-- idmenu_padre: null si es nivel 1, ID del padre si es nivel 2
-- ruta: ruta Angular del componente
-- icono: nombre del icono (ej: Material Icons o FontAwesome)
-- orden / nivel_orden: para ordenar en el sidebar
-- ------------------------------------------------------------

-- ── APP001: Sistema de Ventas ────────────────────────────────
INSERT INTO menus (idmenu, idaplicacion, nombre, nivel_menu, idmenu_padre, orden, icono, nivel_orden, ruta, fecha_reg, fecha_mod) VALUES
('MNU001', 'APP001', 'Dashboard',       '1', NULL,     1, 'dashboard',    1, '/dashboard',        NOW(), NOW()),
('MNU002', 'APP001', 'Ventas',          '1', NULL,     2, 'point_of_sale',2, '/ventas',           NOW(), NOW()),
('MNU003', 'APP001', 'Clientes',        '1', NULL,     3, 'people',       3, '/clientes',         NOW(), NOW()),
('MNU004', 'APP001', 'Reportes',        '1', NULL,     4, 'bar_chart',    4, '/reportes',         NOW(), NOW()),
('MNU005', 'APP001', 'Configuracion',   '1', NULL,     5, 'settings',     5, '/configuracion',    NOW(), NOW());

-- Submenus de Ventas (MNU002)
INSERT INTO menus (idmenu, idaplicacion, nombre, nivel_menu, idmenu_padre, orden, icono, nivel_orden, ruta, fecha_reg, fecha_mod) VALUES
('MNU002A', 'APP001', 'Nueva Venta',        '2', 'MNU002', 1, 'add_shopping_cart', 21, '/ventas/nueva',      NOW(), NOW()),
('MNU002B', 'APP001', 'Historial Ventas',   '2', 'MNU002', 2, 'receipt_long',      22, '/ventas/historial',  NOW(), NOW()),
('MNU002C', 'APP001', 'Cotizaciones',       '2', 'MNU002', 3, 'request_quote',     23, '/ventas/cotizaciones',NOW(), NOW());

-- Submenus de Clientes (MNU003)
INSERT INTO menus (idmenu, idaplicacion, nombre, nivel_menu, idmenu_padre, orden, icono, nivel_orden, ruta, fecha_reg, fecha_mod) VALUES
('MNU003A', 'APP001', 'Listar Clientes',    '2', 'MNU003', 1, 'list',          31, '/clientes/lista',   NOW(), NOW()),
('MNU003B', 'APP001', 'Nuevo Cliente',      '2', 'MNU003', 2, 'person_add',    32, '/clientes/nuevo',   NOW(), NOW());

-- Submenus de Reportes (MNU004)
INSERT INTO menus (idmenu, idaplicacion, nombre, nivel_menu, idmenu_padre, orden, icono, nivel_orden, ruta, fecha_reg, fecha_mod) VALUES
('MNU004A', 'APP001', 'Reporte Mensual',    '2', 'MNU004', 1, 'calendar_month',41, '/reportes/mensual', NOW(), NOW()),
('MNU004B', 'APP001', 'Reporte Anual',      '2', 'MNU004', 2, 'calendar_today',42, '/reportes/anual',   NOW(), NOW());

-- Submenus de Configuracion (MNU005)
INSERT INTO menus (idmenu, idaplicacion, nombre, nivel_menu, idmenu_padre, orden, icono, nivel_orden, ruta, fecha_reg, fecha_mod) VALUES
('MNU005A', 'APP001', 'Usuarios',           '2', 'MNU005', 1, 'manage_accounts',51, '/configuracion/usuarios',    NOW(), NOW()),
('MNU005B', 'APP001', 'Permisos',           '2', 'MNU005', 2, 'lock',           52, '/configuracion/permisos',    NOW(), NOW()),
('MNU005C', 'APP001', 'Parametros',         '2', 'MNU005', 3, 'tune',           53, '/configuracion/parametros',  NOW(), NOW());

-- ── APP002: Portal de Clientes ───────────────────────────────
INSERT INTO menus (idmenu, idaplicacion, nombre, nivel_menu, idmenu_padre, orden, icono, nivel_orden, ruta, fecha_reg, fecha_mod) VALUES
('MNU010', 'APP002', 'Inicio',             '1', NULL,     1, 'home',         1, '/inicio',           NOW(), NOW()),
('MNU011', 'APP002', 'Mi Perfil',          '1', NULL,     2, 'account_circle',2, '/perfil',           NOW(), NOW()),
('MNU012', 'APP002', 'Mis Aplicaciones',   '1', NULL,     3, 'apps',         3, '/mis-aplicaciones', NOW(), NOW()),
('MNU013', 'APP002', 'Soporte',            '1', NULL,     4, 'support_agent',4, '/soporte',          NOW(), NOW());

-- ── APP003: Modulo de Reportes ───────────────────────────────
INSERT INTO menus (idmenu, idaplicacion, nombre, nivel_menu, idmenu_padre, orden, icono, nivel_orden, ruta, fecha_reg, fecha_mod) VALUES
('MNU020', 'APP003', 'Reportes Ventas',    '1', NULL,     1, 'trending_up',  1, '/rep-ventas',       NOW(), NOW()),
('MNU021', 'APP003', 'Reportes Clientes',  '1', NULL,     2, 'group',        2, '/rep-clientes',     NOW(), NOW()),
('MNU022', 'APP003', 'Exportar',           '1', NULL,     3, 'download',     3, '/exportar',         NOW(), NOW());
