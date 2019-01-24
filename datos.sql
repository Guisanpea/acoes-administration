delete from acoes.egreso;
delete from acoes.ingreso;
delete from acoes.envio;
delete from acoes.administracion_apadrinamiento;
delete from acoes.apadrinamiento;
delete from acoes.socio;
delete from acoes.esta_en;
delete from acoes.alumno;
delete from acoes.usuario;
delete from acoes.proyecto;
delete from acoes.tipo_proyecto;
delete from acoes.region_ayuda;
delete from acoes.sede;
delete from acoes.partida;
delete from acoes.region;
delete from acoes.colaborador;
delete from acoes.tercero;

insert into acoes.tercero (nombre) values ('Grupo de Donantes Anonimos');
insert into acoes.tercero (nombre) values ('Grupo de Ayuda a Otros');

insert into acoes.colaborador (nombre) values ('Colaborando Juntos SA');
insert into acoes.colaborador (nombre) values ('Asociacion Madre Teresa');

insert into acoes.region (region) values ('Espana');

insert into acoes.partida (nombre) values ('Gastos Escolares');
insert into acoes.partida (nombre) values ('Alimentos');
insert into acoes.partida (nombre) values ('Dinero Apadrinamiento');

insert into acoes.sede (nombre, region) values ('Malaga', 1);
insert into acoes.sede (nombre, region) values ('Madrid', 1);

insert into acoes.region_ayuda (nombre) values ('Honduras');

insert into acoes.tipo_proyecto (nombre, descripcion) values ('test', 'descripcion del test');

insert into acoes.proyecto (nombre, tipo_proyecto, region_ayuda) values ('Fuengirola', 1, 1);
insert into acoes.proyecto (nombre, tipo_proyecto, region_ayuda) values ('Malaga', 1, 1);

insert into acoes.usuario (nombre, email, contrasena, rol) values ('Jose Manuel','jmanuel@acoes.com','admin','AdministradorUsuarios');
insert into acoes.usuario (nombre, email, contrasena, rol, sede_usuario_local, centro) values ('Pepe','pepe@acoes.com','agente','Agente', 1, 1);
insert into acoes.usuario (nombre, email, contrasena, rol, sede_usuario_local, centro) values ('Antonio','antonio@acoes.com','gerentesede','GerenteSede',1, 1);
insert into acoes.usuario (nombre, email, contrasena, rol, sede_usuario_local, centro) values ('Susana','susana@acoes.com','coordinadorl','CoordinadorLocal',1, 2);
insert into acoes.usuario (nombre, email, contrasena, rol) values ('Ricardo','ricardo@acoes.com','admingeneral','AdministradorGeneral');

insert into acoes.alumno (nombre, apellidos, estado, sexo, fecha_nacimiento, fecha_entrada_acoes, fecha_alta, fecha_salida_acoes, grado_curso, colonia_procedencia, colonia_actual, observaciones, apadrinable) values ('Juan','Gomez', null ,'Masculino', '1999-02-26', '2018-11-12','2018-11-12' , null , 3, 'Copan', 'Copan', 'Buen comportamiento', 0);
insert into acoes.alumno (nombre, apellidos, estado, sexo, fecha_nacimiento, fecha_entrada_acoes, fecha_alta, fecha_salida_acoes, grado_curso, colonia_procedencia, colonia_actual, observaciones, apadrinable) values ('Luis','Sanchez', null,'Masculino', '2000/07/03', '2017/08/21','2017/08/21', null , 2, 'Copan', 'Copan', 'Alumno muy bueno', 0);
insert into acoes.alumno (nombre, apellidos, estado, sexo, fecha_nacimiento, fecha_entrada_acoes, fecha_alta, fecha_salida_acoes, grado_curso, colonia_procedencia, colonia_actual, apadrinable) values ('Daniela','Riostra', null ,'Femenino', '1997-10-13', '2016-10-21','2018-5-12' , null , 4, 'Distrito Central', 'Copan', 1);


insert into acoes.esta_en (alumno, centro, fechaInicio, fechaFin) values (1, 1, '2016-10-21', '2017-5-13');
insert into acoes.esta_en (alumno, centro, fechaInicio, fechaFin) values (1, 2, '2017-5-13', null);
insert into acoes.esta_en (alumno, centro, fechaInicio, fechaFin) values (2, 1, '2018-11-12', null);
insert into acoes.esta_en (alumno, centro, fechaInicio, fechaFin) values (3, 2, '2017/08/21', null);


insert into acoes.socio (nombre, apellidos, estado, nif, direccion, poblacion, codigo_postal, provincia, telefono_fijo, telefono_movil, email, relacion ,certificado, sector, fecha_alta, fecha_baja, observaciones, contribucion_economica, responsable) values ('Alexander', 'Elliott', 'Casado', 'Y2274476C', 'Calle Malaga', 'Malaga', '29004','Malaga' , null,687987543, 'Alex@lex.com',null ,0, 'sector','2018/07/03',null ,'Padrino de Juan', 20.50, 2);
insert into acoes.socio (nombre, apellidos, estado, nif, direccion, poblacion, codigo_postal, provincia, telefono_fijo, telefono_movil, email, relacion ,certificado, sector, fecha_alta, fecha_baja, observaciones, contribucion_economica, responsable) values ('Martin', 'Sanchez', 'Soltero', '12345678I', 'Calle Bailen', 'Malaga', '29006','Malaga','951234321' , 634098765, 'Martin@mart.com',null , 0, 'sector','2018/03/18' ,null ,'Padrino de Luis', 25.75, 2);
insert into acoes.socio (nombre, apellidos, estado, nif, direccion, poblacion, codigo_postal, provincia, telefono_fijo, telefono_movil, email, relacion ,certificado, sector, fecha_alta, fecha_baja, observaciones, contribucion_economica, responsable) values ('Adrian', 'Laguna', 'Soltero', '77234045E', 'Calle Puerto de Santa Maria', 'Rincon de la Victoria', '29738','Malaga' , null,633538850, 'adrian@adr.com', null, 0, 'sector','2018/07/03', null,'No apadrina', 0, 2);

insert into acoes.apadrinamiento (apadrinado, padrino, aportacion, fecha_inicio, fecha_fin) values (1, 1, 150, '2018-07-03',null );
insert into acoes.apadrinamiento (apadrinado, padrino, aportacion, fecha_inicio, fecha_fin) values (2, 2, 200, '2018-03-18',null );

insert into acoes.administracion_apadrinamiento (fecha_inicio, fecha_fin, agente, apadrinamiento_id, apadrinamiento_apadrinado, apadrinamiento_padrino) values ('2018-07-03', null, 2, 1, 1, 1);
insert into acoes.administracion_apadrinamiento (fecha_inicio, fecha_fin, agente, apadrinamiento_id, apadrinamiento_apadrinado, apadrinamiento_padrino) values ('2018-03-18', null, 2, 2, 2, 2);

insert into acoes.envio (estado, apadrinamiento_id, apadrinamiento_apadrinado, apadrinamiento_padrino) values ('Preparado', 1, 1, 1);
insert into acoes.envio (estado, apadrinamiento_id, apadrinamiento_apadrinado, apadrinamiento_padrino) values ('Enviado', 2, 2, 2);

insert into acoes.ingreso (fecha, concepto, importe, emisor, observaciones, partida, proyecto, creador, validado, responsable) values ('2018/08/03', 'Apadrinamiento de Juan', 20.50, 'emisor', null, 3, 1, 3, 1, 5);
insert into acoes.ingreso (fecha, concepto, importe, emisor, observaciones, partida, proyecto, creador, validado, responsable) values ('2018/04/18', 'Apadrinamiento de Luis', 25.75, 'emisor', null, 3, 1, 4, 1, 5);

insert into acoes.egreso (fecha, concepto, importe, beneficiario_alumno, tipo_beneficiario, observaciones, partida, proyecto, creador, responsable, validado) values ('2018/08/03', 'Parte que va a Juan', 17.50, 1, 'Alumno', null, 1, 1, 3, 5, 1);
insert into acoes.egreso (fecha, concepto, importe, beneficiario_alumno, tipo_beneficiario, observaciones, partida, proyecto, creador, responsable, validado) values ('2018/04/18', 'Parte que va a Luis', 22.75, 3, 'Alumno', null, 1, 1, 3, 5, 1);
