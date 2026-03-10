BEGIN;

-- Tipos de observación básicos
-- Son los que se usan en toda la app
INSERT INTO public.tipo_observacion (nombre) VALUES
    ('Planta'),
    ('Rincón'),
    ('Incidencia')
    ON CONFLICT (nombre) DO NOTHING;

-- Usuarios de prueba
-- Son pocos a propósito, para no liarnos
INSERT INTO public.usuario (nombre, apellidos, nick, email, password_hash, rol)
VALUES
    ('Sergio',    'Caro',     'sergio',    'sergio@demo.local',    '1234',  'USER'),
    ('Antonio',   'Pérez',    'antonio',   'antonio@demo.local',   '1234',  'USER'),
    ('Pepe',      'Martínez', 'pepe',      'pepe@demo.local',      '1234',  'USER'),
    ('Pedro',     'García',   'pedro',     'pedro@demo.local',     '1234',  'USER'),
    ('Clara',     'López',    'clara',     'clara@demo.local',     '1234',  'USER'),
    ('Sofía',     'Ruiz',     'sofia',     'sofia@demo.local',     '1234',  'USER'),
    ('Leonor',    'Sánchez',  'leonor',    'leonor@demo.local',    '1234',  'USER'),
    ('Angustias', 'Navarro',  'angustias', 'angustias@demo.local', '1234',  'USER'),
    ('Admin',     'Demo',     'admin',     'admin@demo.local',     'admin', 'ADMIN')
    ON CONFLICT (nick) DO NOTHING;

-- Catálogo EEI
-- Entradas individuales de especies invasoras del catálogo oficial
INSERT INTO public.catalogo_eei (nombre_cientifico, nombre_comun, reino, familia, normativa_ref)
VALUES
    ('Arundo donax',        'Caña común',      'Plantae', 'Poaceae',       'Real Decreto 630/2013'),
    ('Ailanthus altissima', 'Árbol del cielo', 'Plantae', 'Simaroubaceae', 'Real Decreto 630/2013')
    ON CONFLICT (nombre_cientifico) DO NOTHING;

-- Especies
-- Mezcla de invasoras y no invasoras
-- Las invasoras enlazan con su entrada en el catálogo EEI
WITH eei AS (
    SELECT id_catalogo_eei, nombre_cientifico AS nc FROM public.catalogo_eei
    WHERE nombre_cientifico IN ('Arundo donax', 'Ailanthus altissima')
)
INSERT INTO public.especie (nombre_cientifico, nombre_comun, familia, descripcion, id_catalogo_eei)
VALUES
    ('Arundo donax',        'Caña común',      'Poaceae',       'Muy común en acequias.',  (SELECT id_catalogo_eei FROM eei WHERE nc = 'Arundo donax')),
    ('Ailanthus altissima', 'Árbol del cielo', 'Simaroubaceae', 'Rebrota con fuerza.',     (SELECT id_catalogo_eei FROM eei WHERE nc = 'Ailanthus altissima')),
    ('Nerium oleander',     'Adelfa',          'Apocynaceae',   'Muy típica en ramblas.',  NULL)
    ON CONFLICT (nombre_cientifico) DO NOTHING;

-- Observaciones
-- 15 ejemplos repartidos entre usuarios y ubicaciones de la Huerta de Murcia

COMMIT;
