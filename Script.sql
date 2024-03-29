DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
CREATE TABLE public."UsuarioRegistrado" (
  "ID"               SERIAL NOT NULL , 
  "Nombre"             varchar(255) NOT NULL, 
  "Email"             varchar(255) NOT NULL UNIQUE, 
  "Contrasenia"             varchar(255) NOT NULL, 
  "Tipo"             varchar(1) NOT NULL CONSTRAINT tipoUsuarioCheck CHECK("Tipo"='S' OR "Tipo"='T') ,
  "Experiencia" text,
  PRIMARY KEY ("ID"));
CREATE TABLE public."Interes" (
  "ID"               SERIAL NOT NULL , 
  "Nombre"             text NOT NULL UNIQUE,
  PRIMARY KEY ("ID"));
CREATE TABLE public."UsuarioXIntereses" (
  "Usuario RegistradoID" int NOT NULL, 
  "InteresID"         int NOT NULL, 
  PRIMARY KEY ("Usuario RegistradoID", 
  "InteresID"));
CREATE TABLE public."Logro" (
  "ID"               SERIAL NOT NULL , 
  "Texto"            text NOT NULL,
  PRIMARY KEY ("ID"));
CREATE TABLE public."UsuarioXLogros" (
  "Usuario RegistradoID" int NOT NULL, 
  "LogroID"         int NOT NULL, 
  PRIMARY KEY ("Usuario RegistradoID", 
  "LogroID"));
CREATE TABLE public."Materia" (
  "ID"               SERIAL NOT NULL , 
  "Nombre"             varchar(255) NOT NULL,
  PRIMARY KEY ("ID"));
CREATE TABLE public."UsuarioXMaterias" (
  "Usuario RegistradoID" int NOT NULL, 
  "MateriaID"         int NOT NULL, 
  PRIMARY KEY ("Usuario RegistradoID", 
  "MateriaID"));
CREATE TABLE public."HorarioAtencion" (
  "ID"    SERIAL NOT NULL ,
  "Franja" varchar (255), 
  PRIMARY KEY ("ID"));
CREATE TABLE public."UsuarioXHorarioAtencion" (
  "Usuario RegistradoID" int NOT NULL, 
  "HorarioID"         int NOT NULL, 
  PRIMARY KEY ("Usuario RegistradoID", 
  "HorarioID"));
CREATE TABLE public."UsuarioXDestacable" (
  "Usuario RegistradoID" int NOT NULL, 
  "DestacableID"         int NOT NULL, 
  PRIMARY KEY ("Usuario RegistradoID", 
  "DestacableID"));
CREATE TABLE public."Destacable" (
  "ID"          SERIAL NOT NULL , 
  "Descripcion" varchar(255) NOT NULL UNIQUE, 
  PRIMARY KEY ("ID"));
CREATE TABLE public."GrupoEstudio" (
  "ID"               SERIAL NOT NULL ,
  "Codigo" varchar(255) UNIQUE, 
  "Publico" varchar(1) NOT NULL CONSTRAINT grupoPrivadoCheck CHECK("Publico"='S' OR "Publico"='N'),
  "Nombre" varchar(255) NOT NULL UNIQUE, 
  PRIMARY KEY ("ID"));
CREATE TABLE public."UsuarioxGrupoEstudio" (
  "UsuarioRegistradoID" int NOT NULL, 
  "GrupoEstudioID"      int NOT NULL,
  "Admin" varchar(1) NOT NULL CONSTRAINT userAdminCheck CHECK("Admin"='S' OR "Admin"='N'),
  PRIMARY KEY ("UsuarioRegistradoID", 
  "GrupoEstudioID"));
CREATE TABLE public."Reunion" (
  "ID"    SERIAL NOT NULL , 
  "Fecha" text NOT NULL, 
  PRIMARY KEY ("ID"));
CREATE TABLE public."Chat" (
  "ID"   SERIAL NOT NULL ,
  "FechaCreacion" text NOT NULL,
  "Tipo" varchar(1) NOT NULL CONSTRAINT chatTipoCheck CHECK("Tipo"='P' OR "Tipo"='G'), 
  "PerteneceID" int,
  "Usuario1ID" int,
  "Usuario2ID" int,
  PRIMARY KEY ("ID"));
CREATE TABLE public."Comentario" (
  "ID"    SERIAL NOT NULL , 
  "Texto" text,
  "Estrellas" varchar(100),
  PRIMARY KEY ("ID"));
CREATE TABLE public."Mensaje" (
  "ID"    SERIAL NOT NULL , 
  "Mensaje" text,
  "Fecha" text NOT NULL, 
  "RemitenteID" int NOT NULL,
  PRIMARY KEY ("ID"));
CREATE TABLE public."ChatXMensaje"(
  "ChatID" int NOT NULL,
  "MensajeID" int NOT NULL,
  PRIMARY KEY ("ChatID", 
  "MensajeID"));
CREATE TABLE public."UsuarioXComentario" (
  "UsuarioRegistradoID" int NOT NULL, 
  "ComentarioID"        int NOT NULL, 
  PRIMARY KEY ("UsuarioRegistradoID", 
  "ComentarioID"));
CREATE TABLE public."ReunionXGrupoEstudio" (
  "GrupoEstudioID" int NOT NULL, 
  "ReunionID"      int NOT NULL, 
  PRIMARY KEY ("GrupoEstudioID", 
  "ReunionID"));
ALTER TABLE public."UsuarioXDestacable" ADD CONSTRAINT "FKUsuarioXDe790678" FOREIGN KEY ("Usuario RegistradoID") REFERENCES "UsuarioRegistrado" ("ID");
ALTER TABLE public."Mensaje" ADD CONSTRAINT "FKRemitente6281" FOREIGN KEY ("RemitenteID") REFERENCES "UsuarioRegistrado" ("ID");
ALTER TABLE public."UsuarioXDestacable" ADD CONSTRAINT "FKUsuarioXDe521363" FOREIGN KEY ("DestacableID") REFERENCES "Destacable" ("ID");
ALTER TABLE public."ChatXMensaje" ADD CONSTRAINT "FKChatXM790678" FOREIGN KEY ("ChatID") REFERENCES "Chat" ("ID");
ALTER TABLE public."Chat" ADD CONSTRAINT "FKChat790678" FOREIGN KEY ("PerteneceID") REFERENCES "GrupoEstudio" ("ID");
ALTER TABLE public."Chat" ADD CONSTRAINT "FKChat90678" FOREIGN KEY ("Usuario1ID") REFERENCES "UsuarioRegistrado" ("ID");
ALTER TABLE public."Chat" ADD CONSTRAINT "FKChat678" FOREIGN KEY ("Usuario2ID") REFERENCES "UsuarioRegistrado" ("ID");
ALTER TABLE public."ChatXMensaje" ADD CONSTRAINT "FKChatXM521363" FOREIGN KEY ("MensajeID") REFERENCES "Mensaje" ("ID");
ALTER TABLE public."UsuarioXMaterias" ADD CONSTRAINT "FKUsuarioXMat521363" FOREIGN KEY ("Usuario RegistradoID") REFERENCES "UsuarioRegistrado" ("ID");
ALTER TABLE public."UsuarioXMaterias" ADD CONSTRAINT "FKUsuarioXMat521364" FOREIGN KEY ("MateriaID") REFERENCES "Materia" ("ID");
ALTER TABLE public."UsuarioXLogros" ADD CONSTRAINT "FKUsuarioXLogrt521363" FOREIGN KEY ("Usuario RegistradoID") REFERENCES "UsuarioRegistrado" ("ID");
ALTER TABLE public."UsuarioXLogros" ADD CONSTRAINT "FKUsuarioXLogr521364" FOREIGN KEY ("LogroID") REFERENCES "Logro" ("ID");
ALTER TABLE public."UsuarioXIntereses" ADD CONSTRAINT "FKUsuarioXInter521363" FOREIGN KEY ("Usuario RegistradoID") REFERENCES "UsuarioRegistrado" ("ID");
ALTER TABLE public."UsuarioXIntereses" ADD CONSTRAINT "FKUsuarioXInter521364" FOREIGN KEY ("InteresID") REFERENCES "Interes" ("ID");
ALTER TABLE public."UsuarioxGrupoEstudio" ADD CONSTRAINT "FKUsuarioxGr206487" FOREIGN KEY ("UsuarioRegistradoID") REFERENCES "UsuarioRegistrado" ("ID");
ALTER TABLE public."UsuarioxGrupoEstudio" ADD CONSTRAINT "FKUsuarioxGr705719" FOREIGN KEY ("GrupoEstudioID") REFERENCES "GrupoEstudio" ("ID");
ALTER TABLE public."UsuarioXHorarioAtencion" ADD CONSTRAINT "FKUsuarioxHorarioA206487" FOREIGN KEY ("Usuario RegistradoID") REFERENCES "UsuarioRegistrado" ("ID");
ALTER TABLE public."UsuarioXHorarioAtencion" ADD CONSTRAINT "FKUsuarioxHorarioA705719" FOREIGN KEY ("HorarioID") REFERENCES "HorarioAtencion" ("ID");
ALTER TABLE public."UsuarioXComentario" ADD CONSTRAINT "FKUsuarioXCo742724" FOREIGN KEY ("UsuarioRegistradoID") REFERENCES "UsuarioRegistrado" ("ID");
ALTER TABLE public."UsuarioXComentario" ADD CONSTRAINT "FKUsuarioXCo246220" FOREIGN KEY ("ComentarioID") REFERENCES "Comentario" ("ID");
ALTER TABLE public."ReunionXGrupoEstudio" ADD CONSTRAINT "FKReunionXGr367591" FOREIGN KEY ("GrupoEstudioID") REFERENCES "GrupoEstudio" ("ID");
ALTER TABLE public."ReunionXGrupoEstudio" ADD CONSTRAINT "FKReunionXGr128047" FOREIGN KEY ("ReunionID") REFERENCES "Reunion" ("ID");
INSERT INTO public."UsuarioRegistrado" ("Nombre", "Email", "Contrasenia", "Tipo") VALUES ('Abril Cano', 'abril@cano.com', '@bril', 'S'); --ID 1
INSERT INTO public."UsuarioRegistrado" ("Nombre", "Email", "Contrasenia", "Tipo") VALUES ('Jose Torres', 'jose@torres.com', 'J0s3', 'S'); --ID 2
INSERT INTO public."UsuarioRegistrado" ("Nombre", "Email", "Contrasenia", "Tipo") VALUES ('Usuario de prueba', 'a', 'a', 'S'); --ID 3
INSERT INTO public."UsuarioRegistrado" ("Nombre", "Email", "Contrasenia", "Tipo") VALUES ('Anabel Montero', 'anmontero@javeriana.edu.co', '@Nab3l', 'T'); --ID 4
INSERT INTO public."GrupoEstudio" ("Nombre", "Codigo", "Publico") VALUES ('ELGrupoDeAbril', 'abril800q','S'); --ID 1
INSERT INTO public."GrupoEstudio" ("Nombre", "Codigo", "Publico") VALUES ('Grupo de prueba', 'prueba', 'N'); --ID 2
INSERT INTO public."GrupoEstudio" ("Nombre", "Codigo", "Publico") VALUES ('Grupo de prueba2', 'prueba2', 'N'); --ID 3
INSERT INTO public."GrupoEstudio" ("Nombre", "Codigo", "Publico") VALUES ('Cincomas1', 'cm1', 'N'); --ID 4
INSERT INTO public."GrupoEstudio" ("Nombre", "Codigo", "Publico") VALUES ('Cincomas2', 'cm2', 'N'); --ID 5
INSERT INTO public."GrupoEstudio" ("Nombre", "Codigo", "Publico") VALUES ('Cincomas3', 'cm3', 'N'); --ID 6
INSERT INTO public."GrupoEstudio" ("Nombre", "Codigo", "Publico") VALUES ('Cincomas4', 'cm4', 'N'); --ID 7
INSERT INTO public."UsuarioxGrupoEstudio" ("UsuarioRegistradoID", "GrupoEstudioID", "Admin") VALUES (1, 1, 'S');
INSERT INTO public."UsuarioxGrupoEstudio" ("UsuarioRegistradoID", "GrupoEstudioID", "Admin") VALUES (2, 1, 'N');
INSERT INTO public."Interes" ("Nombre") VALUES ('Futbol'); --ID 1
INSERT INTO public."Logro" ("Texto") VALUES ('Excelencia Academica'); --ID 1
INSERT INTO public."Logro" ("Texto") VALUES ('Premio a la profe del año'); --ID 2
INSERT INTO public."Materia" ("Nombre") VALUES ('Analisis Numerico'); --ID 1
INSERT INTO public."Materia" ("Nombre") VALUES ('Sistemas de Informacion'); --ID 2
INSERT INTO public."Materia" ("Nombre") VALUES ('Fundamentos de Ing de Software'); --ID 3
INSERT INTO public."Comentario" ("Estrellas") VALUES ('3.0'); --ID 1
INSERT INTO public."Comentario" ("Estrellas", "Texto") VALUES ('4.5', 'Una persona muy comprometida con todas sus actividades'); --ID 2
INSERT INTO public."HorarioAtencion" ("Franja") VALUES ('Lunes 7-9'); --ID 1
INSERT INTO public."HorarioAtencion" ("Franja") VALUES ('Martes 9-11'); --ID 2
INSERT INTO public."Reunion" ("Fecha") VALUES ('14/11/21 13:00:00'); --ID 1
INSERT INTO public."UsuarioXComentario" ("UsuarioRegistradoID", "ComentarioID") VALUES (1,2);
INSERT INTO public."UsuarioXHorarioAtencion" ("Usuario RegistradoID", "HorarioID") VALUES (4,2);
INSERT INTO public."UsuarioXIntereses" ("Usuario RegistradoID", "InteresID") VALUES (2,1);
INSERT INTO public."UsuarioXLogros" ("Usuario RegistradoID", "LogroID") VALUES (1,1);
INSERT INTO public."UsuarioXLogros" ("Usuario RegistradoID", "LogroID") VALUES (4,2);
INSERT INTO public."UsuarioXMaterias" ("Usuario RegistradoID", "MateriaID") VALUES (1,1);
INSERT INTO public."UsuarioXMaterias" ("Usuario RegistradoID", "MateriaID") VALUES (1,2);
INSERT INTO public."UsuarioXMaterias" ("Usuario RegistradoID", "MateriaID") VALUES (1,3);
INSERT INTO public."ReunionXGrupoEstudio" ("GrupoEstudioID", "ReunionID") VALUES (1,1);