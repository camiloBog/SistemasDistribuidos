----------------------------------------
--***********TABLAS SQL SERVER***********
----------------------------------------
create table pais(
id_pais integer not null PRIMARY KEY,
nom_pais varchar(20) not null
);

create table ciudad(
cod_ciudad integer not null PRIMARY KEY,
nom_ciudad varchar(20) not null,
id_pais integer not null FOREIGN KEY REFERENCES pais(id_pais)
);

create table clientes(
nit_cliente integer primary key not null,
nomcliente varchar(30) null,
profesion varchar(30) null,
telefono integer not null,
dir_resi varchar (40) null,
cod_ciudad integer not null foreign key references ciudad(cod_ciudad)
);

create table menaje(
id_producto integer not null primary key,
nom_pro varchar(30) null,
vlr_pro integer null,
unidad_medida varchar(30),
);

create table categoria(
id_categoria integer not null primary key,
descrip_categoria varchar(30),
valor_dia integer,
);

create table menaje_categoria(
refer_menje_cat integer not null primary key,
cantidad_definida integer not null,
id_producto integer not null foreign key references menaje(id_producto),
id_categoria integer not null foreign key references  categoria (id_categoria),
);

create table habitacion(
num_hab integer not null primary key,
ext_tel integer,
estado varchar(30),
id_categoria integer not null foreign key references categoria(id_categoria)
);

create table caracteristica(
id_caracteristica integer not null primary key,
nom_caracteristica varchar(50)
);

create table clasificacion(
id_clas integer not null PRIMARY KEY,
nom_clas varchar(60) not null, 
);

create table cant_cara_cat(
id_cant_cara_cat integer not null primary key,
id_caracteristica integer not null foreign key references caracteristica(id_caracteristica) ,
id_categoria integer not null foreign key references categoria(id_categoria)
);

create table registro(
num_reg integer not null primary key,
nombre varchar(60),
apellido varchar(60),
cedula integer
num_acomp integer not null,
fecha_i_reg date default getdate(),
fecha_f_reg date not null,
);

create table servicio(
cod_serv integer not null PRIMARY KEY,
nom_serv varchar(60) not null,
val_serv integer not null,
unidad_medida varchar(60) not null,
id_clas integer not null FOREIGN KEY REFERENCES clasificacion(id_clas),
num_reg integer not null FOREIGN KEY REFERENCES registro(num_reg)
);

create table reserva_hospedaje(
id_r_hospedaje integer primary key not null,
fecha_ir date check (fecha_ir < = getdate()) not null,
fecha_fr date check (fecha_fr > = getdate()) not null,
num_aco integer not null check (num_aco > 0),
num_dias integer check (num_dias>0) default 3,
tipo_r varchar(20) default 'garantizada',
estado_r varchar(1) default 'A',
forma_pago varchar(25) default 'efectivo',
vlr_total integer not null check (vlr_total > 0),
num_hab integer not null foreign key references habitacion(num_hab),
nit_cliente integer not null foreign key references clientes(nit_cliente),
);

create table cant_consumida(
refer_menje_cat integer not null foreign key references menaje_categoria(refer_menje_cat),
id_r_hospedaje integer not null foreign key references reserva_hospedaje(id_r_hospedaje)
);

create table recibo(
id_rescibo integer not null PRIMARY KEY,
fech_s date default getdate(),
hora_s date default getdate(),
cant_s integer check(cant_s>0),
vlr_s integer check(vlr_s>0),
cod_serv integer not null FOREIGN KEY REFERENCES servicio(cod_serv),
id_r_hospedaje integer not null FOREIGN KEY REFERENCES reserva_hospedaje(id_r_hospedaje)
);

----------------------------------------
--***********JOURNAL***********
----------------------------------------
create table journal(
sentencia_dml varchar(60),
nombre_tabla varchar(60),
id_transaccion int  identity(1,1) not null primary key,
llave_primaria integer,
nom_servi varchar(60),
valor_serv integer,
unidad_medida varchar(60),
id_clasificacion integer,
numero_reg integer,
usuario varchar(60),
nombre_host varchar(60),
fecha_y_hora datetime,
);
----------------------------------------
--***********VISTAS***********
----------------------------------------
create view vista_servicio as
	select (s.nom_serv) as nombre_servicio,val_serv, 
		r.num_acomp as numero_acompaante,cod_serv,unidad_medida
	from servicio as s join registro as r 
	on s.num_reg=r.num_reg;
----------------------------------------
--***********  ***********
----------------------------------------
CREATE TRIGGER servicio_insert on servicio after insert as
	begin 
		declare @cod_ser integer,@nom_serv varchar(20),@val_serv integer,
			@unidad_medida varchar(30),@id_clas integer,@num_reg integer
		
		select @cod_ser=cod_serv, @nom_serv=nom_serv, @val_serv=val_serv, 
			@unidad_medida=unidad_medida,@id_clas=id_clas,@num_reg=num_reg
			from inserted
		
		insert into journal(sentencia_dml,nombre_tabla,llave_primaria,
			nom_servi,valor_serv,unidad_medida,id_clasificacion,
			numero_reg,usuario,nombre_host,fecha_y_hora) 
		values ('dml_insert','servicio',@cod_ser,@nom_serv,@val_serv,
		@unidad_medida,@id_clas,@num_reg,SYSTEM_USER,HOST_NAME(),GETDATE())
	end
go;
----------------------------------------
--***********  ***********
----------------------------------------
CREATE TRIGGER serv_delete on servicio after delete as
	begin 
		set nocount on;
		declare @cod_serv integer,@nom_serv varchar(20),@val_serv integer,
			@unidad_medida varchar(30),@id_clas integer,@num_reg integer

		set	@cod_serv= (select cod_serv from deleted)		
		set @nom_serv= (select nom_serv from deleted)
		set @val_serv= (select val_serv from deleted)
		set @unidad_medida= (select unidad_medida from deleted)
		set @id_clas= (select id_clas from deleted)
		set @num_reg= (select num_reg from deleted)
		
		INSERT INTO journal (sentencia_dml,nombre_tabla,llave_primaria,
			nom_servi,valor_serv,unidad_medida,id_clasificacion,
			numero_reg,usuario,nombre_host,fecha_y_hora)
		values ('sentencia_delete','servicio',@cod_serv,@nom_serv,@val_serv,
			@unidad_medida,@id_clas,@num_reg,SYSTEM_USER,HOST_NAME(),GETDATE())
	end
go;
----------------------------------------
--***********  ***********
----------------------------------------
create trigger update_servicio on servicio after update as
	begin 
		set nocount on;
		declare @cod_serv integer,@nom_serv varchar(20),@val_serv 
			integer,@unidad_medida varchar(30),@id_clas integer,@num_reg integer

		set	@cod_serv= (select cod_serv from deleted)		
		set @nom_serv= (select nom_serv from deleted)
		set @val_serv= (select val_serv from deleted)
		set @unidad_medida= (select unidad_medida from deleted)
		set @id_clas= (select id_clas from deleted)
		set @num_reg= (select num_reg from deleted)
		
		INSERT INTO journal (sentencia_dml,nombre_tabla,llave_primaria,
			nom_servi,valor_serv,unidad_medida,id_clasificacion,numero_reg,
			usuario,nombre_host,fecha_y_hora)
		values ('sentencia_update','servicio',@cod_serv,@nom_serv,@val_serv,
			@unidad_medida,@id_clas,@num_reg,SYSTEM_USER,HOST_NAME(),GETDATE())
	end
go
----------------------------------------
--***********  ***********
----------------------------------------
select * from journal;
select * from servicio;
select * from registro;
select * from clasificacion;
----------------------------------------
