DROP SCHEMA IF EXISTS `acoes`;

CREATE SCHEMA IF NOT EXISTS `acoes` DEFAULT CHARACTER SET utf8;
USE `acoes`;

create table acoes.alumno
(
  codigo              int auto_increment
    primary key,
  nombre              varchar(45)                    not null,
  apellidos           varchar(45)                    not null,
  estado              varchar(15)                    null,
  sexo                enum ('Masculino', 'Femenino') not null,
  foto                varchar(100)                   null,
  fecha_nacimiento    date                           not null,
  fecha_entrada_acoes date                           not null,
  fecha_alta          date                           not null,
  fecha_salida_acoes  date                           null,
  grado_curso         int                            not null,
  colonia_procedencia varchar(45)                    not null,
  colonia_actual      varchar(45)                    not null,
  observaciones       varchar(100)                   null,
  apadrinable         bit                            not null
);

create table acoes.region
(
  id     int auto_increment
    primary key,
  region varchar(50) not null
);

create table acoes.region_ayuda
(
  id     int auto_increment
    primary key,
  nombre varchar(50) not null
);

create table acoes.sede
(
  id     int auto_increment
    primary key,
  nombre varchar(100) not null,
  region int          null,
  constraint sede_region_fk
    foreign key (region) references region (id)
);

create table acoes.tipo_proyecto
(
  id          int auto_increment
    primary key,
  nombre      varchar(45)  not null,
  descripcion varchar(100) not null
);

create table acoes.proyecto
(
  id            int auto_increment
    primary key,
  nombre        varchar(50) not null,
  tipo_proyecto int         not null,
  region_ayuda  int         not null,
  constraint fk_centro_proyecto
    foreign key (tipo_proyecto) references tipo_proyecto (id)
      on update cascade
      on delete cascade,
  constraint proyecto_region_ayuda_fk
    foreign key (region_ayuda) references region_ayuda (id)
);

create table acoes.esta_en
(
  alumno      int  not null,
  centro      int  not null,
  fechaInicio date not null,
  fechaFin    date null,
  primary key (alumno, centro),
  constraint fk_estaen_alumno
    foreign key (alumno) references alumno (codigo),
  constraint fk_estaen_centro
    foreign key (centro) references proyecto (id)
);

create index fk_estaen_centro_idx
  on esta_en (centro);

create index fk_centro_proyecto_idx
  on proyecto (tipo_proyecto);

create table acoes.usuario
(
  id                 int auto_increment
    primary key,
  nombre             varchar(50)                                                                                                                                                        not null,
  email              varchar(50)                                                                                                                                                        not null,
  contrasena         varchar(15)                                                                                                                                                        not null,
  rol                enum ('Agente', 'GerenteSede', 'GerenteRegional', 'CoordinadorLocal', 'CoordinadorGeneral', 'AdministradorLocal', 'AdministradorGeneral', 'AdministradorUsuarios') not null,
  sede_usuario_local int                                                                                                                                                                null,
  centro             int                                                                                                                                                                null,
  constraint fk_usuario_centro1
    foreign key (centro) references proyecto (id),
  constraint fk_usuario_sede1
    foreign key (sede_usuario_local) references sede (id)
);

create table acoes.socio
(
  numero_de_socio        int auto_increment
    primary key,
  nombre                 varchar(45)  not null,
  apellidos              varchar(45)  not null,
  estado                 varchar(15)  null,
  nif                    varchar(9)   not null,
  direccion              varchar(45)  not null,
  poblacion              varchar(30)  not null,
  codigo_postal          varchar(10)  not null,
  provincia              varchar(25)  not null,
  telefono_fijo          int          null,
  telefono_movil         int          not null,
  email                  varchar(45)  not null,
  relacion               varchar(45)  null,
  certificado            bit          not null,
  sector                 varchar(50)  not null,
  fecha_alta             date         not null,
  fecha_baja             date         null,
  observaciones          varchar(100) null,
  responsable            int          not null,
  contribucion_economica double       null,
  constraint fk_acoes_responsable
    foreign key (responsable) references usuario (id)
);

create table acoes.apadrinamiento
(
  id           int auto_increment,
  apadrinado   int  not null,
  padrino      int  not null,
  aportacion   int  not null,
  fecha_inicio date not null,
  fecha_fin    date null,
  primary key (id, apadrinado, padrino),
  constraint fk_apadrinamiento_alumno1
    foreign key (apadrinado) references alumno (codigo),
  constraint fk_apadrinamiento_socio
    foreign key (padrino) references socio (numero_de_socio)
);

create index fk_apadrinamiento_alumno1_idx
  on apadrinamiento (apadrinado);

create index fk_apadrinamiento_socio_idx
  on apadrinamiento (padrino);

create table acoes.administracion_apadrinamiento
(
  fecha_inicio              date not null,
  fecha_fin                 date null,
  agente                    int  not null,
  apadrinamiento_id         int  not null,
  apadrinamiento_apadrinado int  not null,
  apadrinamiento_padrino    int  not null,
  primary key (agente, fecha_inicio),
  constraint fk_administracion_apadrinamiento_apadrinamiento1
    foreign key (apadrinamiento_id, apadrinamiento_apadrinado, apadrinamiento_padrino) references apadrinamiento (id, apadrinado, padrino),
  constraint fk_administracion_apadrinamiento_usuario1
    foreign key (agente) references usuario (id)
);

create index fk_administracion_apadrinamiento_apadrinamiento1_idx
  on administracion_apadrinamiento (apadrinamiento_id, apadrinamiento_apadrinado, apadrinamiento_padrino);

create index fk_administracion_apadrinamiento_usuario1_idx
  on administracion_apadrinamiento (agente);

create table acoes.envio
(
  id                        int auto_increment,
  estado                    enum ('Preparado', 'Enviado', 'Recibido') not null,
  apadrinamiento_id         int                                       not null,
  apadrinamiento_apadrinado int                                       not null,
  apadrinamiento_padrino    int                                       not null,
  primary key (id, apadrinamiento_id, apadrinamiento_apadrinado, apadrinamiento_padrino),
  constraint fk_envio_apadrinamiento1
    foreign key (apadrinamiento_id, apadrinamiento_apadrinado, apadrinamiento_padrino) references apadrinamiento (id, apadrinado, padrino)
);

create index fk_envio_apadrinamiento1_idx
  on envio (apadrinamiento_id, apadrinamiento_apadrinado, apadrinamiento_padrino);

create table acoes.partida
(
  id     int auto_increment
    primary key,
  nombre varchar(100)
);

create table acoes.colaborador
(
  id     int auto_increment
    primary key,
  nombre varchar(100) not null
);

create table acoes.tercero
(
  id     int auto_increment
    primary key,
  nombre varchar(100) not null
);

create table acoes.egreso
(
  id                       int auto_increment
    primary key,
  fecha                    date                                               not null,
  concepto                 varchar(45)                                        not null,
  importe                  double                                             not null,
  beneficiario_alumno      int                                                null,
  beneficiario_colaborador int                                                null,
  beneficiario_tercero     int                                                null,
  beneficiario_socio       int                                                null,
  tipo_beneficiario        enum ('Alumno', 'Colaborador', 'Tercero', 'Socio') not null,
  observaciones            varchar(100)                                       null,
  partida                  int                                                not null,
  proyecto                 int                                                not null,
  creador                  int                                                not null,
  responsable              int                                                null,
  validado                 bool                                               not null,
  constraint fk_egreso_alumno
    foreign key (beneficiario_alumno) references alumno (codigo),
  constraint fk_egreso_colaborador
    foreign key (beneficiario_colaborador) references colaborador (id),
  constraint fk_egreso_tercero
    foreign key (beneficiario_tercero) references tercero (id),
  constraint fk_egreso_socio
    foreign key (beneficiario_socio) references socio (numero_de_socio),
  constraint fk_egreso_proyecto
    foreign key (proyecto) references tipo_proyecto (id),
  constraint fk_egreso_creador
    foreign key (creador) references usuario (id),
  constraint fk_egreso_responsable
    foreign key (responsable) references usuario (id)
);

create table acoes.ingreso
(
  id            int auto_increment
    primary key,
  fecha         date         not null,
  concepto      varchar(45)  not null,
  importe       double       not null,
  emisor        varchar(100) not null,
  observaciones varchar(100) null,
  partida       int          not null,
  proyecto      int          not null,
  creador       int          not null,
  validado      bool         not null,
  responsable   int          null,
  constraint fk_ingreso_partida
    foreign key (partida) references partida (id),
  constraint fk_ingreso_proyecto
    foreign key (proyecto) references tipo_proyecto (id),
  constraint fk_ingreso_creador
    foreign key (creador) references usuario (id),
  constraint fk_ingreso_responsable
    foreign key (responsable) references usuario (id)
);

create index fk_registro_economico_usuario1_idx
  on ingreso (responsable);

create index fk_registro_proyecto_idx
  on ingreso (proyecto);

create index fk_usuario_centro1_idx
  on usuario (centro);

create index fk_usuario_sede1_idx
  on usuario (sede_usuario_local);

