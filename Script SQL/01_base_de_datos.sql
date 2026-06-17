-- ============================================================
-- PROYECTO: Foro Estudiantil Web
-- AUTOR: Equipo de desarrollo
-- DESCRIPCION: Script de creacion de tablas para Oracle DB
-- ============================================================

-- Borrar tablas si ya existen (en orden por dependencias)
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE mensajes CASCADE CONSTRAINTS';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
/
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE usuarios CASCADE CONSTRAINTS';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
/

-- Borrar secuencias si ya existen
BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE seq_usuarios';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
/
BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE seq_mensajes';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
/

-- ============================================================
-- SECUENCIAS (equivalente a AUTO_INCREMENT en Oracle)
-- ============================================================

CREATE SEQUENCE seq_usuarios
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE seq_mensajes
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- ============================================================
-- TABLA: USUARIOS
-- Almacena las cuentas de los estudiantes registrados
-- ============================================================

CREATE TABLE usuarios (
    id              NUMBER          PRIMARY KEY,
    nombre          VARCHAR2(100)   NOT NULL,
    apellido        VARCHAR2(100)   NOT NULL,
    correo          VARCHAR2(150)   NOT NULL UNIQUE,
    contrasena      VARCHAR2(255)   NOT NULL,   -- Almacena el hash SHA-256
    fecha_registro  DATE            DEFAULT SYSDATE NOT NULL,
    activo          NUMBER(1)       DEFAULT 1 NOT NULL  -- 1=activo, 0=inactivo
);

-- Comentarios de la tabla usuarios
COMMENT ON TABLE  usuarios              IS 'Tabla de usuarios registrados en el foro estudiantil';
COMMENT ON COLUMN usuarios.id           IS 'Identificador unico del usuario';
COMMENT ON COLUMN usuarios.nombre       IS 'Nombre del estudiante';
COMMENT ON COLUMN usuarios.apellido     IS 'Apellido del estudiante';
COMMENT ON COLUMN usuarios.correo       IS 'Correo electronico unico usado para login';
COMMENT ON COLUMN usuarios.contrasena   IS 'Hash SHA-256 de la contrasena del usuario';
COMMENT ON COLUMN usuarios.fecha_registro IS 'Fecha en que el usuario se registro';
COMMENT ON COLUMN usuarios.activo       IS 'Estado del usuario: 1=activo, 0=desactivado';

-- ============================================================
-- TABLA: MENSAJES
-- Almacena los posts publicados en el foro
-- ============================================================

CREATE TABLE mensajes (
    id                  NUMBER          PRIMARY KEY,
    usuario_id          NUMBER          NOT NULL,
    titulo              VARCHAR2(200)   NOT NULL,
    contenido           CLOB            NOT NULL,
    fecha_publicacion   DATE            DEFAULT SYSDATE NOT NULL,
    CONSTRAINT fk_mensajes_usuario 
        FOREIGN KEY (usuario_id) 
        REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Comentarios de la tabla mensajes
COMMENT ON TABLE  mensajes                      IS 'Tabla de mensajes publicados en el foro';
COMMENT ON COLUMN mensajes.id                   IS 'Identificador unico del mensaje';
COMMENT ON COLUMN mensajes.usuario_id           IS 'Referencia al usuario que publico el mensaje';
COMMENT ON COLUMN mensajes.titulo               IS 'Titulo del mensaje o hilo del foro';
COMMENT ON COLUMN mensajes.contenido            IS 'Contenido completo del mensaje';
COMMENT ON COLUMN mensajes.fecha_publicacion    IS 'Fecha y hora de publicacion del mensaje';

-- ============================================================
-- TRIGGERS (para auto-incremento con secuencias)
-- ============================================================

-- Trigger para usuarios
CREATE OR REPLACE TRIGGER trg_usuarios_id
    BEFORE INSERT ON usuarios
    FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        SELECT seq_usuarios.NEXTVAL INTO :NEW.id FROM DUAL;
    END IF;
END;
/

-- Trigger para mensajes
CREATE OR REPLACE TRIGGER trg_mensajes_id
    BEFORE INSERT ON mensajes
    FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        SELECT seq_mensajes.NEXTVAL INTO :NEW.id FROM DUAL;
    END IF;
END;
/

-- ============================================================
-- INDICES (para mejorar el rendimiento de consultas)
-- ============================================================

CREATE INDEX idx_mensajes_usuario ON mensajes(usuario_id);
CREATE INDEX idx_mensajes_fecha   ON mensajes(fecha_publicacion DESC);
CREATE INDEX idx_usuarios_correo  ON usuarios(correo);

-- ============================================================
-- DATOS DE PRUEBA (opcional, para testear la aplicacion)
-- ============================================================

-- Usuario de prueba (contrasena: "admin123" en SHA-256)
INSERT INTO usuarios (nombre, apellido, correo, contrasena)
VALUES ('Admin', 'Sistema', 'admin@foro.com', 
        '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9');

-- Mensaje de prueba
INSERT INTO mensajes (usuario_id, titulo, contenido)
VALUES (1, 'Bienvenidos al Foro Estudiantil', 
        'Este es el foro oficial para compartir dudas y recursos academicos. ¡Participa!');

COMMIT;

-- ============================================================
-- VERIFICACION: Ver tablas creadas
-- ============================================================
SELECT table_name FROM user_tables 
WHERE table_name IN ('USUARIOS', 'MENSAJES')
ORDER BY table_name;
