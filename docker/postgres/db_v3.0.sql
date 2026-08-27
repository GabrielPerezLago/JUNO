
CREATE SCHEMA IF NOT EXISTS "juno";

CREATE TABLE "juno"."empleado_rol" (
                                       "id" SERIAL NOT NULL,
                                       "nombre" VARCHAR(500) NOT NULL,
                                       PRIMARY KEY ("id")
);

CREATE TABLE "juno"."usuario" (
                                  "id" SERIAL NOT NULL,
                                  "nombre" VARCHAR(500) NOT NULL,
                                  "apellidos" VARCHAR(500),
                                  "dni" VARCHAR(500) NOT NULL,
                                  "email" VARCHAR(500) NOT NULL,
                                  "passsword" VARCHAR(500) NOT NULL,
                                  "telefono" VARCHAR(500),
                                "nacimiento" DATE,
                                  PRIMARY KEY ("id")
);

CREATE TABLE "juno"."plaza" (
                                "id" SERIAL NOT NULL,
                                "id_estado" INTEGER NOT NULL,
                                "id_alumno" INTEGER NOT NULL,
                                "id_centro" INTEGER NOT NULL,
                                PRIMARY KEY ("id")
);

CREATE TABLE "juno"."tipo_aula" (
                                   "id" SERIAL NOT NULL,
                                   "tipo" VARCHAR(500),
                                   "ratio" INTEGER,
                                   PRIMARY KEY ("id")
);

CREATE TABLE "juno"."novedades" (
                                    "id" SERIAL NOT NULL,
                                    "titulo" VARCHAR(500) NOT NULL,
                                    "texto" VARCHAR(500),
                                    "centro" INTEGER NOT NULL,
                                    PRIMARY KEY ("id")
);

CREATE TABLE "juno"."historial_evolutivo" (
                                              "id" SERIAL NOT NULL,
                                              "titulo" VARCHAR(500) NOT NULL,
                                              "informe" VARCHAR(500),
                                              "id_alumno" INTEGER NOT NULL,
                                              PRIMARY KEY ("id")
);

CREATE TABLE "juno"."tipo_horario" (
                                       "id" SERIAL NOT NULL,
                                       "nombre" VARCHAR(500) NOT NULL,
                                       PRIMARY KEY ("id")
);

CREATE TABLE "juno"."aula" (
                               "id" SERIAL NOT NULL,
                               "nombre" VARCHAR(500),
                               "id_tipo" INTEGER NOT NULL,
                               "id_centro" INTEGER NOT NULL,
                               PRIMARY KEY ("id")
);

CREATE TABLE "juno"."horario" (
                                  "id" SERIAL NOT NULL,
                                  "tipo" INTEGER NOT NULL,
                                  "fecha_inicio" DATE NOT NULL,
                                  "fecha_fin" DATE NOT NULL,
                                  "id_centro" INTEGER NOT NULL,
                                  PRIMARY KEY ("id")
);

CREATE TABLE "juno"."centro" (
                                 "id" SERIAL NOT NULL,
                                 "nombre" VARCHAR(500) NOT NULL,
                                 "email" VARCHAR(500) NOT NULL,
                                 "descripcion" VARCHAR(500),
                                 "telefono" VARCHAR(500) NOT NULL,
                                 "direccion" VARCHAR(500),
                                 PRIMARY KEY ("id")
);

CREATE TABLE "juno"."empleado" (
                                   "id_usuario" INTEGER NOT NULL,
                                   "id_estado" INTEGER NOT NULL,
                                   "id_rol" INTEGER NOT NULL,
                                   "id_centro" INTEGER NOT NULL,
                                   "id_aula" INTEGER NOT NULL,
                                   PRIMARY KEY ("id_usuario")
);

CREATE TABLE "juno"."estado_plaza" (
                                       "id" SERIAL NOT NULL,
                                       "tipo" VARCHAR(500) NOT NULL,
                                       PRIMARY KEY ("id")
);

CREATE TABLE "juno"."plaza_horario" (
                                        "id_plaza" INTEGER NOT NULL,
                                        "id_horario" INTEGER NOT NULL,
                                        PRIMARY KEY ("id_plaza", "id_horario")
);

CREATE TABLE "juno"."empleado_estado" (
                                          "id" SERIAL NOT NULL,
                                          "estado" VARCHAR(500),
                                          PRIMARY KEY ("id")
);

CREATE TABLE "juno"."anecdotario" (
                                      "id" SERIAL NOT NULL,
                                      "titulo" VARCHAR(100) NOT NULL,
                                      "anecdota" VARCHAR(500),
                                      "id_tipo" INTEGER NOT NULL,
                                      "id_alumno" INTEGER NOT NULL,
                                      PRIMARY KEY ("id")
);

CREATE TABLE "juno"."tipo_anecdota" (
                                        "id" SERIAL NOT NULL,
                                        "tipo" VARCHAR(100) NOT NULL,
                                        PRIMARY KEY ("id")
);

CREATE TABLE "juno"."alumno" (
                                 "id" SERIAL NOT NULL,
                                 "nombre" VARCHAR(100) NOT NULL,
                                 "apellidos" VARCHAR(100),
                                 "dni" VARCHAR(100) NOT NULL,
                                 "nacimiento" DATE NOT NULL,
                                 "descripcion" VARCHAR(500),
                                 "id_centro" INTEGER,
                                 "id_horario" INTEGER,
                                 "id_pariente" INTEGER NOT NULL,
                                 "id_aula" INTEGER NOT NULL,
                                 PRIMARY KEY ("id")
);

CREATE TABLE "juno"."token" (
    "id" SERIAL NOT NULL,
    "token" VARCHAR(500) NOT NULL,
    "revoked" BOOLEAN NOT NULL,
    "expired" BOOLEAN NOT NULL,
    "id_usuario" INTEGER NOT NULL,
    PRIMARY KEY ("id")
);

-- Novedades
alter table juno.novedades add constraint "fk_novedades_centro_id" foreign key ("centro") references juno.centro("id");

-- Alumno
alter table juno.alumno add constraint "fk_alumno_centro_id" foreign key ("id_centro") references juno.centro("id");
alter table juno.alumno add constraint "fk_alumno_horario_id" foreign key ("id_horario") references juno.horario("id");
alter table juno.alumno add constraint "fk_alumno_usuario_id" foreign key ("id_pariente") references juno.usuario("id");
alter table juno.alumno add constraint "fk_alumno_aula_id" foreign key ("id_aula") references juno.aula("id");


-- Anecdotario
alter table juno.anecdotario add constraint "fk_anecdotario_alumno_id" foreign key ("id_alumno") references juno.alumno("id");
alter table juno.anecdotario add constraint "fk_anecdotario_tipo_id" foreign key ("id_tipo") references juno.tipo_anecdota("id");

--Aula
alter table juno.aula add constraint "fk_aula_tipo_id" foreign key ("id_tipo") references juno.tipo_aula("id");
alter table juno.aula add constraint "fk_aula_centro_id" foreign key ("id_centro") references juno.centro("id");

--Empleado
alter table juno.empleado add constraint "fk_pk_empleado_usuario_id" foreign key ("id_usuario") references juno.usuario("id");
alter table juno.empleado add constraint "fk_empleado_estado_id" foreign key ("id_estado") references juno.empleado_estado("id");
alter table juno.empleado add constraint "fk_empleado_rol_id" foreign key ("id_rol") references juno.empleado_rol("id");
alter table juno.empleado add constraint "fk_empleado_centro_id" foreign key ("id_centro") references juno.centro("id");
alter table juno.empleado add constraint "fk_empleado_aula_id" foreign key ("id_aula") references juno.aula("id");

--Historial_Evolutivo
alter table juno.historial_evolutivo add constraint "fk_historial_evolutivo_alumn_id" foreign key ("id_alumno") references juno.alumno("id");

--Horario
alter table juno.horario add constraint "fk_horario_centro_id" foreign key ("id_centro") references juno.centro("id");

-- Plaza
alter table juno.plaza add constraint "fk_plaza_estado_id" foreign key ("id_estado") references juno.estado_plaza("id");
alter table juno.plaza add constraint "fk_plaza_alumno_id" foreign key ("id_alumno") references juno.alumno("id");
alter table juno.plaza add constraint "fk_plaza_centro_id" foreign key ("id_centro") references juno.centro("id");

-- Plaza_Horario
alter table juno.plaza_horario add constraint "fk_plaza_horario_plaza_id" foreign key ("id_plaza") references juno.plaza("id");
alter table juno.plaza_horario add constraint "fk_plaza_horario_horario_id" foreign key ("id_horario") references juno.horario("id");

-- Token
alter table juno.token add constraint "fk_tocken_usuario_id" foreign key ("id_usuario") references juno.usuario("id");

-- DATOS NECESARIOS EN TABLAS DE TIPOS

insert into juno.empleado_estado (estado) values
                                              ('activo'),
                                              ('no activo');

insert into juno.empleado_rol (nombre)
values ('direccion'),
       ('administracion'),
       ('docencia');

insert into juno.estado_plaza (tipo)
values ('solicitada'),
       ('en revision'),
       ('aceptada'),
       ('denegada');

insert into juno.tipo_aula (tipo, ratio)
values ('bebes', 7),
       ('uno_dos', 12),
       ('dos_tres', 18);

insert into juno.tipo_horario (nombre) values
                                           ('madrugadores'),
                                           ('mañana'),
                                           ('tarde');

insert into juno.tipo_anecdota (tipo) values
                                          ('simpre'),
                                          ('a veces'),
                                          ('nunca');