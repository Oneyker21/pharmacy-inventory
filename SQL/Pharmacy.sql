USE [master]
GO

CREATE DATABASE [Farmacia_Rosales_DB]
 
USE [Farmacia_Rosales_DB]
GO

--TABLAS ----------------------------------------------------------------


CREATE TABLE [dbo].[Cliente](
	[Id_Cliente]  INT  IDENTITY(1,1) PRIMARY KEY,
	[Id_Persona] int NULL,
GO





GO

CREATE TABLE [dbo].[Laboratorio](
	[Id_Laboratorio]  INT  IDENTITY(1,1) PRIMARY KEY,
	[Nombre] varchar (50) NOT NULL,
GO

CREATE TABLE [dbo].[Categoria](
	[Id_Categoria] int INT  IDENTITY(1,1) PRIMARY KEY,
	[Nombre_Categoria] varchar (50) NOT NULL,
	[Descripcion] [varchar](100) NULL,
GO


CREATE TABLE [dbo].[Presentacion](
	[Id_Presentacion] INT  IDENTITY(1,1) PRIMARY KEY,
	[Nombre_Presentacion] varchar (50) NOT NULL,
	[Detalle] varchar (70) NOT NULL,
GO



CREATE TABLE [dbo].[Venta](
	[Id_Venta] INT  IDENTITY(1,1) PRIMARY KEY,
	[Fecha_Hora] datetime NOT NULL,
	[Id_Cliente] int NOT NULL,
	[Id_Empleado] int NULL,
GO
CREATE TABLE [dbo].[Venta_Producto](
	[Id_Venta_Producto]  INT  IDENTITY(1,1) PRIMARY KEY,
	[Cantidad] int NOT NULL,
	[Descuento] decimal (8, 2) NOT NULL,
	[Id_Venta] int NOT NULL,
	[Id_Producto] int NOT NULL,
GO
CREATE TABLE [dbo].[Empleado](
	[Id_Empleado] int  INT  IDENTITY(1,1) PRIMARY KEY,
	[Hora_Entrada] time NOT NULL,
	[Hora_Salida] time NOT NULL,
	[Salario] decimal (10, 2) NULL,
	[Id_Persona] int NULL,

GO

CREATE TABLE [dbo].[Cliente](
	[Id_Cliente]  INT  IDENTITY(1,1) PRIMARY KEY,
	[Id_Persona] int NULL,
GO


CREATE TABLE [dbo].[Usuarios](
	[Usuario] varchar(50) NOT NULL,
	[Contraseña] varchar (50) NULL,
	[Rol] varchar (50) NULL,
GO


CREATE TABLE [dbo].[Persona](
	[Id_Persona]  INT  IDENTITY(1,1) PRIMARY KEY,
	[Cedula] varchar (18) NULL,
	[Nombre_1] varchar (32) NOT NULL,
	[Nombre_2] varchar (32) NULL,
	[Apellido_1] varchar (32) NOT NULL,
	[Apellido_2] varchar (32) NULL,
	[Numero_Celular] varchar (9) NULL,
	[Gmail] nvarchar (50) NULL,
	[Direccion] varchar (200) NOT NULL,
GO
CREATE TABLE [dbo].[Producto_Proveedor](
	[Id_Producto_Proveedor] INT  IDENTITY(1,1) PRIMARY KEY,
	[Id_Proveedor] int NOT NULL,
	[Id_Producto] int NOT NULL,
GO
CREATE TABLE [dbo].[Proveedor](
	[Id_Proveedor] INT  IDENTITY(1,1) PRIMARY KEY,
	[Id_Persona] int NULL,

GO





CREATE TABLE [dbo].[Producto](
	[Id_Producto] INT  IDENTITY(1,1) PRIMARY KEY,
	[Nombre] varchar (50) NOT NULL,
	[Descripcion] varchar (200) NULL,
	[Cantidad_Producto] int NOT NULL,
	[Precio_Compra] decimal (8, 2) NULL,
	[Precio_Venta] decimal (8, 2) NULL,
	[Imagen_Producto] varbinary (max) NULL,
	[Fecha_Caducidad] date NOT NULL,
	[Id_Categoria] int NOT NULL,
	[Id_Presentacion] int NOT NULL,
	[Id_Laboratorio] int NOT NULL,
GO



CREATE TABLE [dbo].[Compra](
	[Id_Compra] INT  IDENTITY(1,1) PRIMARY KEY,
	[Fecha_Compra] date NULL,
	[Id_Proveedor] int NULL,
GO

CREATE TABLE [dbo].[Compra_Producto](
	[Id_Compra_Producto] INT  IDENTITY(1,1) PRIMARY KEY,
	[Id_Compra] int NULL,
	[Id_Producto] int NULL,
GO

CREATE TABLE [dbo].[Lote](
    [Id_Lote] [int] IDENTITY(1,1) PRIMARY KEY,
    [Id_Producto] [int] FOREIGN KEY REFERENCES Producto(Id_Producto),
    [Fecha_Caducidad] [date] NOT NULL,
    [Precio_Compra] [decimal](10, 2) NOT NULL,
    [Precio_Venta] [decimal](10, 2) NOT NULL,
    [Cantidad] [int] NOT NULL
)
GO

CREATE PROCEDURE [dbo].[AgregarLote]
    @IdProducto INT,
    @FechaCaducidad DATE,
    @PrecioCompra DECIMAL(10, 2),
    @PrecioVenta DECIMAL(10, 2),
    @Cantidad INT
AS
BEGIN
    INSERT INTO Lote (Id_Producto, Fecha_Caducidad, Precio_Compra, Precio_Venta, Cantidad)
    VALUES (@IdProducto, @FechaCaducidad, @PrecioCompra, @PrecioVenta, @Cantidad);
END



--Relaciones entre tablas -------------------------------------------------


ALTER TABLE [dbo].[Cliente] 
ADD  CONSTRAINT [FK_Cliente_Persona] 
FOREIGN KEY([Id_Persona])
REFERENCES [dbo].[Persona] ([Id_Persona])
GO

ALTER TABLE [dbo].[Compra] 
ADD  CONSTRAINT [FK_Compra_Proveedor]
FOREIGN KEY([Id_Proveedor])
REFERENCES [dbo].[Proveedor] ([Id_Proveedor])
GO

ALTER TABLE [dbo].[Compra_Producto] 
ADD  CONSTRAINT [FK_Compra_Producto_Compra] 
FOREIGN KEY([Id_Compra])
REFERENCES [dbo].[Compra] ([Id_Compra])
GO

ALTER TABLE [dbo].[Compra_Producto] 
ADD  CONSTRAINT [FK_Compra_Producto_Producto]
FOREIGN KEY([Id_Producto])
REFERENCES [dbo].[Producto] ([Id_Producto])
GO

ALTER TABLE [dbo].[Empleado]
ADD  CONSTRAINT [FK_Empleado_Persona] 
FOREIGN KEY([Id_Persona])
REFERENCES [dbo].[Persona] ([Id_Persona])
GO

ALTER TABLE [dbo].[Producto] 
ADD  CONSTRAINT [FK_Presentacion_Producto ] 
FOREIGN KEY([Id_Presentacion])
REFERENCES [dbo].[Presentacion] ([Id_Presentacion])
GO

ALTER TABLE [dbo].[Producto] 
ADD  CONSTRAINT [FK_Producto_Categoria]
FOREIGN KEY([Id_Categoria])
REFERENCES [dbo].[Categoria] ([Id_Categoria])
GO

ALTER TABLE [dbo].[Producto] 
ADD  CONSTRAINT [FK_Productos_Laboratorio]
FOREIGN KEY([Id_Laboratorio])
REFERENCES [dbo].[Laboratorio] ([Id_Laboratorio])
GO

ALTER TABLE [dbo].[Producto_Proveedor] 
ADD  CONSTRAINT [FK_Producto_Proveedor_Producto]
FOREIGN KEY([Id_Producto])
REFERENCES [dbo].[Producto] ([Id_Producto])
GO

ALTER TABLE [dbo].[Producto_Proveedor] 
ADD  CONSTRAINT [FK_Producto_Proveedor_Proveedor]
FOREIGN KEY([Id_Proveedor])
REFERENCES [dbo].[Proveedor] ([Id_Proveedor])
GO

ALTER TABLE [dbo].[Proveedor] 
ADD  CONSTRAINT [FK_Proveedor_Persona]
FOREIGN KEY([Id_Persona])
REFERENCES [dbo].[Persona] ([Id_Persona])
GO

ALTER TABLE [dbo].[Venta]
ADD  CONSTRAINT [FK_Venta_Cliente]
FOREIGN KEY([Id_Cliente])
REFERENCES [dbo].[Cliente] ([Id_Cliente])
GO

ALTER TABLE [dbo].[Venta] 
ADD  CONSTRAINT [FK_Venta_Empleado]
FOREIGN KEY([Id_Empleado])
REFERENCES [dbo].[Empleado] ([Id_Empleado])
GO

ALTER TABLE [dbo].[Venta_Producto]
WITH CHECK ADD  CONSTRAINT [FK_Venta_Producto_Producto]
FOREIGN KEY([Id_Producto])
REFERENCES [dbo].[Producto] ([Id_Producto])
GO

ALTER TABLE [dbo].[Venta_Producto] 
 ADD  CONSTRAINT [FK_Venta_Producto_Venta]
 FOREIGN KEY([Id_Venta])
REFERENCES [dbo].[Venta] ([Id_Venta])
GO






--Procedimiento de Actualizar Categotia ---------------------------------------------------

CREATE PROCEDURE [dbo].[ActualizarCategoria]
(
    @Id_Categoria INT,
	@Nombre_Categoria VARCHAR(50),
    @Descripcion VARCHAR(70)
    
)
AS
    UPDATE [Categoria]
    SET   [Nombre_Categoria] = @Nombre_Categoria,
	      [Descripcion] = @Descripcion  
    WHERE [Id_Categoria] = @Id_Categoria
GO

--Procedimiento de Actualizar Cliente y persona ---------------------------------------------------

CREATE PROCEDURE [dbo].[ActualizarCliente]
    @Id_Cliente INT,
    @Cedula VARCHAR(18),
    @Nombre_1 VARCHAR(32),
    @Nombre_2 VARCHAR(32),
    @Apellido_1 VARCHAR(32),
    @Apellido_2 VARCHAR(32),
    @Numero_Celular VARCHAR(9),
    @Gmail NVARCHAR(50),
    @Direccion VARCHAR(200)
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE Persona
    SET Cedula = @Cedula,
        Nombre_1 = @Nombre_1,
        Nombre_2 = @Nombre_2,
        Apellido_1 = @Apellido_1,
        Apellido_2 = @Apellido_2,
        Numero_Celular = @Numero_Celular,
        Gmail = @Gmail,
        Direccion = @Direccion
    WHERE Id_Persona = (SELECT Id_Persona FROM Cliente WHERE Id_Cliente = @Id_Cliente);
END
GO

--Procedimiento de Actualizar Compra ------------------------------------------------------

CREATE PROCEDURE [dbo].[ActualizarCompra]
  @Id_Compra INT,
  @Fecha_Compra DATE,
  @Id_Proveedor INT
AS
  UPDATE [Compra]
  SET fecha_compra = @Fecha_Compra, Id_proveedor = @Id_Proveedor
  WHERE Id_Compra = @Id_Compra
GO


--Procedimiento de Actualizar Compra y Producto ------------------------------------------------------


CREATE PROCEDURE [dbo].[ActualizarCompraProducto]
  @Id_Compra_Producto INT,
  @Id_Compra INT,
  @Id_Producto INT
AS
  UPDATE [Compra_Producto]
  SET Id_compra = @Id_Compra, Id_Producto = @Id_Producto
  WHERE Id_Compra_Producto = @Id_Compra_Producto
GO



--Procedimiento de Actualizar Empleado y persona ------------------------------------------------------

CREATE PROCEDURE [dbo].[ActualizarEmpleado]
(
    @Id_Empleado INT,
    @Salario DECIMAL(10,2),
    @Hora_Entrada TIME,
    @Hora_Salida TIME,
    @Cedula VARCHAR(18),
    @Nombre_1 VARCHAR(32),
    @Nombre_2 VARCHAR(32),
    @Apellido_1 VARCHAR(32),
    @Apellido_2 VARCHAR(32),
    @Numero_Celular VARCHAR(9),
    @Gmail NVARCHAR(50),
    @Direccion VARCHAR(200)
)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @Id_Persona INT;

    UPDATE Persona
    SET Cedula = @Cedula, Nombre_1 = @Nombre_1, Nombre_2 = @Nombre_2, Apellido_1 = @Apellido_1, Apellido_2 = @Apellido_2, Numero_Celular = @Numero_Celular, Gmail = @Gmail, Direccion = @Direccion
    WHERE Id_Persona = (SELECT Id_Persona FROM Empleado WHERE Id_Empleado = @Id_Empleado);

    UPDATE Empleado
    SET Salario = @Salario, Hora_Entrada = @Hora_Entrada, Hora_Salida = @Hora_Salida
    WHERE Id_Empleado = @Id_Empleado;
END
GO



--Procedimiento de Actualizar Laboratorio ------------------------------------------------------

CREATE PROCEDURE [dbo].[ActualizarLaboratorio]
(
    @Id_Laboratorio INT,
    @Nombre VARCHAR(50)
)
AS
    UPDATE [Laboratorio]
    SET[Nombre] = @Nombre
    WHERE[Id_Laboratorio] = @Id_Laboratorio
GO


--Procedimiento de Actualizar Presentacion ------------------------------------------------------

CREATE PROCEDURE [dbo].[ActualizarPresentacion]
(
    @Id_Presentacion INT,
    @Nombre_Presentacion VARCHAR(50),
    @Detalle VARCHAR(70)
)
AS
    UPDATE [Presentacion]
    SET[Nombre_Presentacion] = @Nombre_Presentacion,
       [Detalle] = @Detalle
    WHERE[Id_Presentacion] = @Id_Presentacion
GO



--Procedimiento de Actualizar Producto ------------------------------------------------------

CREATE PROCEDURE [dbo].[ActualizarProducto]
(
    @Id_Producto INT,
    @Nombre VARCHAR(50),
    @Descripcion VARCHAR(200),
    @Cantidad_Producto INT,
    @Precio_Compra DECIMAL(8,2),
    @Precio_Venta DECIMAL(8,2),
    @Imagen_Producto IMAGE,
    @Fecha_Caducidad DATE,
    @Id_Categoria INT,
    @Id_Presentacion INT,
    @Id_Laboratorio INT
)
AS
    UPDATE [Producto]
    SET [Nombre] = @Nombre,
        [Descripcion] = @Descripcion,
        [Cantidad_Producto] = @Cantidad_Producto,
        [Precio_Compra] = @Precio_Compra,
        [Precio_Venta] = @Precio_Venta,
        [Imagen_Producto] = @Imagen_Producto,
        [Fecha_Caducidad] = @Fecha_Caducidad,
        [Id_Categoria] = @Id_Categoria,
        [Id_Presentacion] = @Id_Presentacion,
        [Id_Laboratorio] = @Id_Laboratorio
    WHERE [Id_Producto] = @Id_Producto
GO



--Procedimiento de Actualizar Producto y Proveedor  ------------------------------------------------------

CREATE PROCEDURE [dbo].[ActualizarProductoProveedor]
(
    @Id_Producto_Proveedor INT,
    @Id_Proveedor INT,
    @Id_Producto INT
)
AS
    UPDATE [Producto_Proveedor]
    SET   [Id_Proveedor] = @Id_Proveedor,
          [Id_Producto] = @Id_Producto
    WHERE [Id_Producto_Proveedor] = @Id_Producto_Proveedor
GO


--Procedimiento de Actualizar Proveedor y persona  ------------------------------------------------------

CREATE PROCEDURE [dbo].[ActualizarProveedor]
    @Id_Proveedor INT,
    @Cedula VARCHAR(18),
    @Nombre_1 VARCHAR(32),
    @Nombre_2 VARCHAR(32),
    @Apellido_1 VARCHAR(32),
    @Apellido_2 VARCHAR(32),
    @Numero_Celular VARCHAR(9),
    @Gmail NVARCHAR(50),
    @Direccion VARCHAR(200)
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE Persona
    SET Cedula = @Cedula,
        Nombre_1 = @Nombre_1,
        Nombre_2 = @Nombre_2,
        Apellido_1 = @Apellido_1,
        Apellido_2 = @Apellido_2,
        Numero_Celular = @Numero_Celular,
        Gmail = @Gmail,
        Direccion = @Direccion
    WHERE Id_Persona = (SELECT Id_Persona FROM Proveedor WHERE Id_Proveedor = @Id_Proveedor);
END
GO


--Procedimiento de Actualizar Venta ------------------------------------------------------

CREATE PROCEDURE [dbo].[ActualizarVenta]
(
    @Id_Venta INT,
    @Fecha_Hora DATETIME
)
AS
    UPDATE [Venta]
    SET[Fecha_Hora] = @Fecha_Hora
    WHERE [Id_Venta] = @Id_Venta
GO



--Procedimiento de Actualizar Venta y Producto------------------------------------------------------

CREATE PROCEDURE [dbo].[ActualizarVentaProducto]
(
    @Id_Venta_Producto INT,
    @Cantidad INT,
    @Descuento DECIMAL(8,2),
    @Id_Venta INT,
    @Id_Producto INT
)
AS
    UPDATE [Venta_Producto]
    SET [Cantidad] = @Cantidad,
        [Descuento] = @Descuento,
        [Id_Venta] = @Id_Venta,
        [Id_Producto] = @Id_Producto
    WHERE[Id_Venta_Producto] = @Id_Venta_Producto
GO


--Procedimiento para Buscar una Categoria ------------------------------------------------------

CREATE PROCEDURE [dbo].[BuscarCategoria]
(
    @dato VARCHAR(200) 
)
AS


    SELECT Id_Categoria,Nombre_Categoria,Descripcion
    FROM [Categoria]
    WHERE (@dato IS NULL
	    OR TRY_CAST(Id_Categoria AS VARCHAR) = @dato 
        OR RTRIM(Nombre_Categoria) LIKE '%' + RTRIM(@dato) + '%' 
        OR RTRIM(Descripcion) LIKE '%' + RTRIM(@dato) + '%')
GO


--Procedimiento para Buscar una Categoria en caja de texto ------------------------------------------------------


CREATE PROCEDURE[dbo].[BuscarCategoria_Caja]
    @Busqueda VARCHAR(50)
AS
BEGIN
    SET  @Busqueda = RTRIM(@Busqueda);
	 IF @Busqueda IS NULL OR @Busqueda = ''
    BEGIN

    SELECT [Id_Categoria], [Nombre_Categoria]
    FROM [Categoria]

END 
	ELSE
BEGIN
   SELECT Id_Categoria, Nombre_Categoria FROM Categoria
    WHERE Nombre_Categoria LIKE '%' + @Busqueda + '%'
	 OR CAST( Id_Categoria AS VARCHAR) LIKE '%' + RTRIM(@Busqueda) + '%';

END;
END;
GO


--Procedimiento para Buscar un Cliente ------------------------------------------------------


CREATE PROCEDURE [dbo].[BuscarCliente]
    @Busqueda NVARCHAR(100)
AS
BEGIN
    SET NOCOUNT ON;

    SELECT C.Id_Cliente, RTRIM(P.Cedula) AS Cedula, RTRIM(P.Nombre_1) AS Nombre_1, RTRIM(P.Nombre_2) AS Nombre_2, RTRIM(P.Apellido_1) AS Apellido_1, RTRIM(P.Apellido_2) AS Apellido_2, RTRIM(P.Numero_Celular) AS Numero_Celular, RTRIM(P.Gmail) AS Gmail, RTRIM(P.Direccion) AS Direccion
    FROM Cliente C
    INNER JOIN Persona P ON C.Id_Persona = P.Id_Persona
    WHERE RTRIM(TRY_CAST(CAST(C.Id_Cliente AS NVARCHAR(100)) AS NVARCHAR(100))) LIKE '%' + @Busqueda + '%'
        OR RTRIM(P.Cedula) LIKE '%' + @Busqueda + '%'
        OR RTRIM(P.Nombre_1) LIKE '%' + @Busqueda + '%'
        OR RTRIM(P.Nombre_2) LIKE '%' + @Busqueda + '%'
        OR RTRIM(P.Apellido_1) LIKE '%' + @Busqueda + '%'
        OR RTRIM(P.Apellido_2) LIKE '%' + @Busqueda + '%'
        OR RTRIM(P.Numero_Celular) LIKE '%' + @Busqueda + '%'
        OR RTRIM(P.Gmail) LIKE '%' + @Busqueda + '%'
        OR RTRIM(P.Direccion) LIKE '%' + @Busqueda + '%';
END
GO


--Procedimiento para Buscar una Compra ------------------------------------------------------


CREATE PROCEDURE [dbo].[BuscarCompra]
  @Id_Compra INT
AS
  SELECT *
  FROM Compra
  WHERE Id_Compra = @Id_Compra
GO


--Procedimiento para Buscar una Compra de producto ------------------------------------------------------

CREATE PROCEDURE [dbo].[BuscarCompraProducto]
  @Id_Compra_Producto INT
AS
  SELECT *
  FROM Compra_Producto
  WHERE Id_Compra_Producto = @Id_Compra_Producto
GO



--Procedimiento para Buscar un Empleado ------------------------------------------------------


CREATE PROCEDURE [dbo].[BuscarEmpleado]
(
    @Busqueda NVARCHAR(100)
)
AS
BEGIN
    SET NOCOUNT ON;

    SELECT E.Id_Empleado, RTRIM(P.Cedula) AS Cedula, RTRIM(P.Nombre_1) AS Nombre_1, RTRIM(P.Nombre_2) AS Nombre_2, RTRIM(P.Apellido_1) AS Apellido_1, RTRIM(P.Apellido_2) AS Apellido_2, E.Salario, E.Hora_Entrada, E.Hora_Salida, RTRIM(P.Numero_Celular) AS Numero_Celular, RTRIM(P.Gmail) AS Gmail, RTRIM(P.Direccion) AS Direccion
    FROM Empleado E
    INNER JOIN Persona P ON E.Id_Persona = P.Id_Persona
    WHERE RTRIM(TRY_CAST(E.Id_Empleado AS NVARCHAR(100))) LIKE '%' + @Busqueda + '%'
        OR RTRIM(P.Cedula) LIKE '%' + @Busqueda + '%'
        OR RTRIM(P.Nombre_1) LIKE '%' + @Busqueda + '%'
        OR RTRIM(P.Nombre_2) LIKE '%' + @Busqueda + '%'
        OR RTRIM(P.Apellido_1) LIKE '%' + @Busqueda + '%'
        OR RTRIM(P.Apellido_2) LIKE '%' + @Busqueda + '%'
        OR RTRIM(TRY_CAST(E.Salario AS NVARCHAR(100))) LIKE '%' + @Busqueda + '%'
        OR RTRIM(P.Numero_Celular) LIKE '%' + @Busqueda + '%'
        OR RTRIM(P.Gmail) LIKE '%' + @Busqueda + '%'
        OR RTRIM(P.Direccion) LIKE '%' + @Busqueda + '%';
END
GO



--Procedimiento para Buscar un Laboratorio ------------------------------------------------------


CREATE PROCEDURE [dbo].[BuscarLaboratorio]
(
    @dato VARCHAR (50)
)
AS
    SELECT Id_Laboratorio,Nombre
    FROM [Laboratorio]
    WHERE (@dato IS NULL
	OR TRY_CAST(Id_Laboratorio AS VARCHAR) = @dato
	OR RTRIM(Nombre) LIKE '%' + RTRIM(@dato) + '%')
GO


--Procedimiento para Buscar un Laboratorio en una caja de texto ------------------------------------------------------


CREATE PROCEDURE [dbo].[BuscarLaboratorio_caja]
    @Busqueda VARCHAR(50)
AS
BEGIN
    SET @Busqueda = RTRIM(@Busqueda);

    IF @Busqueda IS NULL OR @Busqueda = ''
    BEGIN
        SELECT [Id_Laboratorio], [Nombre]
        FROM [Laboratorio]
    END
    ELSE
    BEGIN
        SELECT [Id_Laboratorio], [Nombre]
        FROM [Laboratorio]
        WHERE [Nombre] LIKE '%' + @Busqueda + '%'
    END;
END;
GO


--Procedimiento para Buscar una Presentacion ------------------------------------------------------


CREATE PROCEDURE [dbo].[BuscarPresentacion]
(
    @dato VARCHAR(200) 
)
AS

    SELECT Id_Presentacion,Nombre_Presentacion,Detalle
    FROM Presentacion
    WHERE (@dato IS NULL
        OR TRY_CAST(Id_Presentacion AS VARCHAR) = @dato 
		OR RTRIM(Nombre_Presentacion) LIKE '%' + RTRIM (@dato) + '%'
        OR TRIM(Detalle) LIKE '%' + TRIM(@dato) + '%')

GO


--Procedimiento para Buscar una Presentacion en caja  ------------------------------------------------------


CREATE PROCEDURE [dbo].[BuscarPresentacion_Caja]
    @Busqueda VARCHAR(50)
AS
BEGIN
    SET @Busqueda = RTRIM(@Busqueda);

    IF @Busqueda IS NULL OR @Busqueda = ''
    BEGIN
     
        SELECT [Id_Presentacion], [Nombre_Presentacion]
        FROM [Presentacion]
END
    ELSE
    BEGIN
       
        SELECT [Id_Presentacion], [Nombre_Presentacion]
        FROM [Presentacion]
        WHERE [Nombre_Presentacion] LIKE '%' + @Busqueda + '%'
    END;
END;
GO


--Procedimiento para Buscar un Producto ------------------------------------------------------

CREATE PROCEDURE [dbo].[BuscarProducto]
(
    @Id_Producto INT = NULL,
    @Nombre VARCHAR(50) = NULL,
    @Descripcion VARCHAR(200) = NULL
)
AS
    SELECT *
    FROM [Producto]
    WHERE 
        (@Id_Producto IS NULL OR [Id_Producto] = @Id_Producto)
        OR (@Nombre IS NULL OR TRIM([Nombre]) LIKE '%' + @Nombre + '%')
        OR (@Descripcion IS NULL OR TRIM([Descripcion]) LIKE '%' + @Descripcion + '%');
GO



--Procedimiento para Buscar un Producto y proveedor  ------------------------------------------------------

CREATE PROCEDURE [dbo].[BuscarProductoProveedor]
(
    @Id_Producto_Proveedor INT
)
AS
    SELECT *
    FROM [Producto_Proveedor]
    WHERE [Id_Producto_Proveedor] = @Id_Producto_Proveedor
GO


--Procedimiento para Buscar un Proveedor ------------------------------------------------------


CREATE PROCEDURE [dbo].[BuscarProveedor]
    @Busqueda NVARCHAR(100)
AS
BEGIN
    SET NOCOUNT ON;

    SELECT PR.Id_Proveedor, RTRIM(P.Cedula) AS Cedula, RTRIM(P.Nombre_1) AS Nombre_1, RTRIM(P.Nombre_2) AS Nombre_2, RTRIM(P.Apellido_1) AS Apellido_1, RTRIM(P.Apellido_2) AS Apellido_2, RTRIM(P.Numero_Celular) AS Numero_Celular, RTRIM(P.Gmail) AS Gmail, RTRIM(P.Direccion) AS Direccion
    FROM Proveedor PR
    INNER JOIN Persona P ON PR.Id_Persona = P.Id_Persona
    WHERE RTRIM(P.Cedula) LIKE '%' + @Busqueda + '%'
        OR RTRIM(P.Nombre_1) LIKE '%' + @Busqueda + '%'
        OR RTRIM(P.Nombre_2) LIKE '%' + @Busqueda + '%'
        OR RTRIM(P.Apellido_1) LIKE '%' + @Busqueda + '%'
        OR RTRIM(P.Apellido_2) LIKE '%' + @Busqueda + '%'
        OR RTRIM(P.Numero_Celular) LIKE '%' + @Busqueda + '%'
        OR RTRIM(P.Gmail) LIKE '%' + @Busqueda + '%'
        OR RTRIM(P.Direccion) LIKE '%' + @Busqueda + '%';
END
GO


--Procedimiento para Buscar un Proveedor en una caja de texto ------------------------------------------------------

CREATE PROCEDURE [dbo].[BuscarProveedor_Caja]
     @Busqueda VARCHAR(50)
AS
BEGIN
    SET @Busqueda = RTRIM(@Busqueda);

    IF @Busqueda IS NULL OR @Busqueda = ''
    BEGIN
        SELECT PR.Id_Proveedor, PE.Nombre_1, PE.Apellido_1
        FROM Proveedor AS PR
        INNER JOIN Persona AS PE ON PR.Id_Persona = PE.Id_Persona;
    END 
    ELSE
    BEGIN
        SELECT PR.Id_Proveedor, PE.Nombre_1, PE.Apellido_1
        FROM Proveedor AS PR
        INNER JOIN Persona AS PE ON PR.Id_Persona = PE.Id_Persona
        WHERE PR.Id_Proveedor LIKE '%' + @Busqueda + '%'
        OR PE.Nombre_1 LIKE '%' + @Busqueda + '%'
        OR PE.Apellido_1 LIKE '%' + @Busqueda + '%';
    END;
END;


--Procedimiento para Buscar una Venta ------------------------------------------------------

CREATE PROCEDURE [dbo].[BuscarVenta]
(
    @Id_Venta INT
)
AS
    SELECT *
    FROM [Venta]
    WHERE [Id_Venta] = @Id_Venta
GO



--Procedimiento para Buscar una Venta de un producto ------------------------------------------------------


CREATE PROCEDURE [dbo].[BuscarVentaProducto]
(
    @Id_Venta_Producto INT
)
AS
    SELECT *
    FROM [Venta_Producto]
    WHERE [Id_Venta_Producto] = @Id_Venta_Producto
GO  



--Procedimiento para Mostrar la tabla Categoria  ------------------------------------------------------


CREATE PROCEDURE [dbo].[ConsultarDatosCategoria]
AS
    SELECT [Id_Categoria],[Nombre_Categoria], [Descripcion]
    FROM Categoria;
GO



--Procedimiento para Mostrar la tabla Cliente y Persona  ------------------------------------------------------

CREATE PROCEDURE [dbo].[ConsultarDatosCliente]
AS
BEGIN
    SET NOCOUNT ON;

    SELECT C.Id_Cliente, RTRIM(P.Cedula) AS Cedula, RTRIM(P.Nombre_1) AS Nombre_1, RTRIM(P.Nombre_2) AS Nombre_2, RTRIM(P.Apellido_1) AS Apellido_1, RTRIM(P.Apellido_2) AS Apellido_2, RTRIM(P.Numero_Celular) AS Numero_Celular, RTRIM(P.Gmail) AS Gmail, RTRIM(P.Direccion) AS Direccion
    FROM Cliente C
    INNER JOIN Persona P ON C.Id_Persona = P.Id_Persona;
END
GO


--Procedimiento para Mostrar la tabla Compra  ------------------------------------------------------

CREATE PROCEDURE [dbo].[ConsultarDatosCompra]
AS
    SELECT [Id_compra], [Fecha_Compra], [Id_Proveedor]
    FROM Compra;
GO



--Procedimiento para Mostrar la tabla de Compra y Producto  ------------------------------------------------------

CREATE PROCEDURE [dbo].[ConsultarDatosCompraProducto]
AS
    SELECT [Id_Compra_Producto], [Id_Compra], [Id_Producto]
    FROM Compra_Producto;
GO



--Procedimiento para Mostrar la tabla Empleado y Persona  ------------------------------------------------------

CREATE PROCEDURE [dbo].[ConsultarDatosEmpleado]
AS
BEGIN
    SET NOCOUNT ON;

    SELECT E.Id_Empleado, P.Cedula, P.Nombre_1, P.Nombre_2, P.Apellido_1, P.Apellido_2, E.Salario, E.Hora_Entrada, E.Hora_Salida, P.Numero_Celular, P.Gmail, P.Direccion
    FROM Empleado E
    INNER JOIN Persona P ON E.Id_Persona = P.Id_Persona;
END
GO



--Procedimiento para Mostrar la tabla Laboratorio  ------------------------------------------------------

CREATE PROCEDURE [dbo].[ConsultarDatosLaboratorio]
AS
    SELECT [Id_Laboratorio], [Nombre]
    FROM Laboratorio;
GO



--Procedimiento para Mostrar la tabla Presentacion  ------------------------------------------------------

CREATE PROCEDURE [dbo].[ConsultarDatosPresentacion]
AS
    SELECT [Id_Presentacion], [Nombre_Presentacion], [Detalle]
    FROM Presentacion;
GO



--Procedimiento para Mostrar la tabla Producto  ------------------------------------------------------

CREATE PROCEDURE [dbo].[ConsultarDatosProducto]
AS
    SELECT [Id_Producto], [Nombre], [Descripcion], [Cantidad_Producto], [Precio_Compra], [Precio_Venta], [Imagen_Producto], [Fecha_Caducidad], [Id_Categoria], [Id_Presentacion], [Id_Laboratorio]
    FROM [Producto]
GO


--Procedimiento para Mostrar la tabla Proveedor y Persona  ------------------------------------------------------

CREATE PROCEDURE [dbo].[ConsultarDatosProveedor]
AS
BEGIN
    SET NOCOUNT ON;

    SELECT PR.Id_Proveedor, RTRIM(P.Cedula) AS Cedula, RTRIM(P.Nombre_1) AS Nombre_1, RTRIM(P.Nombre_2) AS Nombre_2, RTRIM(P.Apellido_1) AS Apellido_1, RTRIM(P.Apellido_2) AS Apellido_2, RTRIM(P.Numero_Celular) AS Numero_Celular, RTRIM(P.Gmail) AS Gmail, RTRIM(P.Direccion) AS Direccion
    FROM Proveedor PR
    INNER JOIN Persona P ON PR.Id_Persona = P.Id_Persona;
END
GO



--Procedimiento para Mostrar la tabla Venta  ------------------------------------------------------

CREATE PROCEDURE [dbo].[ConsultarDatosVenta]
AS
    SELECT [Id_Venta], [Fecha_Hora], [Id_Cliente], [Id_Empleado]
    FROM Venta;
GO




--Procedimiento para Mostrar la tabla Venta de Producto   ------------------------------------------------------

CREATE PROCEDURE [dbo].[ConsultarDatosVentaProducto]
AS
    SELECT [Id_Venta_Producto], [cantidad], [descuento], [Id_venta], [Id_Producto]
    FROM Venta_Producto;
GO



--Procedimiento para Mostrar la tabla de Producto y Proveedor ------------------------------------------------------

CREATE PROCEDURE [dbo].[ConsultarTablaProductoProveedor]
AS
    SELECT *
    FROM [Producto_Proveedor]
GO

--Procedimiento para eliminar registro de la tabla Categoria------------------------------------------------------

CREATE PROCEDURE [dbo].[EliminarCategoria]
(
    @Id_Categoria INT
)
AS
    DELETE FROM [Categoria]
    WHERE[Id_Categoria] = @Id_Categoria
GO



--Procedimiento para eliminar registro de la tabla Cliente y Persona------------------------------------------------------

CREATE PROCEDURE [dbo].[EliminarCliente]
    @Id_Cliente INT
AS
BEGIN
    SET NOCOUNT ON;

    DELETE FROM Cliente
    WHERE Id_Cliente = @Id_Cliente;

    IF NOT EXISTS (SELECT 1 FROM Empleado WHERE Id_Persona = (SELECT Id_Persona FROM Cliente WHERE Id_Cliente = @Id_Cliente))
    BEGIN
        DELETE FROM Persona
        WHERE Id_Persona = (SELECT Id_Persona FROM Cliente WHERE Id_Cliente = @Id_Cliente);
    END
END
GO



--Procedimiento para eliminar registro de la tabla Compra------------------------------------------------------

CREATE PROCEDURE [dbo].[EliminarCompra]
  @Id_Compra INT
AS
  DELETE FROM Compra
  WHERE Id_compra = @Id_Compra
GO



--Procedimiento para eliminar registro de la tabla Compra y Producto------------------------------------------------------

CREATE PROCEDURE [dbo].[EliminarCompraProducto]
  @Id_Compra_Producto INT
AS
  DELETE FROM Compra_Producto
  WHERE Id_Compra_Producto = @Id_Compra_Producto
GO

--Procedimiento para eliminar registro de la tabla Empleado y Persona ------------------------------------------------------

CREATE PROCEDURE [dbo].[EliminarEmpleado]
(
    @Id_Empleado INT
)
AS
BEGIN
    SET NOCOUNT ON;

    DELETE FROM Empleado WHERE Id_Empleado = @Id_Empleado;

    DELETE FROM Persona WHERE Id_Persona = (SELECT Id_Persona FROM Empleado WHERE Id_Empleado = @Id_Empleado);
END
GO


--Procedimiento para eliminar registro de la tabla Laboaratorio------------------------------------------------------

CREATE PROCEDURE [dbo].[EliminarLaboratorio]
(
    @Id_Laboratorio INT
)
AS
    DELETE FROM [Laboratorio]
    WHERE [Id_Laboratorio] = @Id_Laboratorio
GO


--Procedimiento para eliminar registro de la tabla Presentacion------------------------------------------------------

CREATE PROCEDURE [dbo].[EliminarPresentacion]
(
    @Id_Presentacion INT
)
AS
    DELETE FROM [Presentacion]
    WHERE[Id_Presentacion] = @Id_Presentacion
GO

--Procedimiento para eliminar registro de la tabla Producto------------------------------------------------------

CREATE PROCEDURE [dbo].[EliminarProducto]
(
    @Id_Producto INT
)
AS
    DELETE FROM [Producto]
    WHERE [Id_Producto] = @Id_Producto
GO


--Procedimiento para eliminar registro de la tabla Producto y Proveedor ------------------------------------------------------

CREATE PROCEDURE [dbo].[EliminarProductoProveedor]
(
    @Id_Producto_Proveedor INT
)
AS
    DELETE FROM [Producto_Proveedor]
    WHERE [Id_Producto_Proveedor] = @Id_Producto_Proveedor
GO

--Procedimiento para eliminar registro de la tabla Proveedor ------------------------------------------------------

CREATE PROCEDURE [dbo].[EliminarProveedor]
    @Id_Proveedor INT
AS
BEGIN
    SET NOCOUNT ON;

    DELETE FROM Proveedor
    WHERE Id_Proveedor = @Id_Proveedor;

    IF NOT EXISTS (SELECT 1 FROM Empleado WHERE Id_Persona = (SELECT Id_Persona FROM Proveedor WHERE Id_Proveedor = @Id_Proveedor))
    BEGIN
        DELETE FROM Persona
        WHERE Id_Persona = (SELECT Id_Persona FROM Proveedor WHERE Id_Proveedor = @Id_Proveedor);
    END
END
GO

--Procedimiento para eliminar registro de la tabla Venta------------------------------------------------------

CREATE PROCEDURE [dbo].[EliminarVenta]
(
    @Id_Venta INT
)
AS

    DELETE FROM [Venta]
    WHERE[Id_Venta] = @Id_Venta
GO

--Procedimiento para eliminar registro de la tabla Venta y Producto------------------------------------------------------

CREATE PROCEDURE [dbo].[EliminarVentaProducto]
(
    @Id_Venta_Producto INT
)
AS
    DELETE FROM [Venta_Producto]
    WHERE[Id_Venta_Producto] = @Id_Venta_Producto
GO






--Procedimiento para Insertar registro a la tabla Categoria ------------------------------------------------------

CREATE PROCEDURE [dbo].[InsertarCategoria]
(

    @Nombre_Categoria VARCHAR(50),
    @Descripcion VARCHAR(100)
   
)
AS
    INSERT INTO [Categoria] ([Nombre_Categoria],[Descripcion])
    VALUES (@Nombre_Categoria,@Descripcion)
GO

EXEC [dbo].[InsertarCategoria] 'Analgésicos', 'Medicamentos para el alivio del dolor';
EXEC [dbo].[InsertarCategoria] 'Antibióticos', 'Medicamentos para tratar infecciones bacterianas';
EXEC [dbo].[InsertarCategoria] 'Antihistamínicos', 'Medicamentos para tratar alergias';

GO

SELECT * FROM Categoria
GO


--Procedimiento para Insertar registro a la tabla Cliente y Persona ------------------------------------------------------

CREATE PROCEDURE [dbo].[InsertarCliente]
    @Cedula VARCHAR(18),
    @Nombre_1 VARCHAR(32),
    @Nombre_2 VARCHAR(32),
    @Apellido_1 VARCHAR(32),
    @Apellido_2 VARCHAR(32),
    @Numero_Celular VARCHAR(9),
    @Gmail NVARCHAR(50),
    @Direccion VARCHAR(200)
AS
BEGIN
    SET NOCOUNT ON;

    INSERT INTO Persona (Cedula, Nombre_1, Nombre_2, Apellido_1, Apellido_2, Numero_Celular, Gmail, Direccion)
    VALUES (@Cedula, @Nombre_1, @Nombre_2, @Apellido_1, @Apellido_2, @Numero_Celular, @Gmail, @Direccion);

    -- Obtener el Id_Persona generado
    DECLARE @Id_Persona INT = SCOPE_IDENTITY();

    -- Insertar registro en la tabla Cliente
    INSERT INTO Cliente (Id_Persona)
    VALUES (@Id_Persona);
END
GO

EXEC [dbo].[InsertarCliente] '123456789', 'María', '', 'González', 'López', '123456789', 'maria.gonzalez@gmail.com', 'Calle 123, Ciudad';
EXEC [dbo].[InsertarCliente] '987654321', 'Pedro', '', 'Pérez', 'Gómez', '987654321', 'pedro.perez@gmail.com', 'Avenida 456, Ciudad';
EXEC [dbo].[InsertarCliente] '111111111', 'Laura', '', 'Torres', 'Sánchez', '111111111', 'laura.torres@gmail.com', 'Carrera 789, Ciudad';
GO

SELECT C.Id_Cliente, RTRIM(P.Cedula) AS Cedula, RTRIM(P.Nombre_1) AS Nombre_1, RTRIM(P.Nombre_2) AS Nombre_2, RTRIM(P.Apellido_1) AS Apellido_1, RTRIM(P.Apellido_2) AS Apellido_2, RTRIM(P.Numero_Celular) AS Numero_Celular, RTRIM(P.Gmail) AS Gmail, RTRIM(P.Direccion) AS Direccion
    FROM Cliente C
    INNER JOIN Persona P ON C.Id_Persona = P.Id_Persona;



--Procedimiento para Insertar registro a la tabla Compra ------------------------------------------------------

CREATE PROCEDURE [dbo].[InsertarCompra] 
  @Fecha_Compra DATE,
  @Id_Proveedor INT
AS
  INSERT INTO Compra (fecha_compra, Id_Proveedor)
  VALUES (@Fecha_Compra, @Id_Proveedor)
GO
EXEC [dbo].[InsertarCompra] '2023-06-01', 1;
EXEC [dbo].[InsertarCompra] '2023-06-15', 1;
EXEC [dbo].[InsertarCompra] '2023-06-30', 1;
GO

SELECT * FROM Compra

--Procedimiento para Insertar registro a la tabla Compra de Producto ------------------------------------------------------

CREATE PROCEDURE [dbo].[InsertarCompraProducto]
  @Id_Compra INT,
  @Id_Producto INT
AS
  INSERT INTO Compra_Producto (Id_compra, Id_Producto)
  VALUES (@Id_Compra, @Id_Producto)
GO

EXEC [dbo].[InsertarCompraProducto] 1, 2;
EXEC [dbo].[InsertarCompraProducto] 1, 2;
EXEC [dbo].[InsertarCompraProducto] 1, 2;
GO

SELECT * FROM Compra_Producto
SELECT * FROM Compra
SELECT * FROM Producto


--Procedimiento para Insertar registro a la tabla Empleado y Persona ------------------------------------------------------

CREATE PROCEDURE [dbo].[InsertarEmpleado]
     @Salario DECIMAL(10, 2),
    @Hora_Entrada TIME,
    @Hora_Salida TIME,
    @Cedula VARCHAR(18),
    @Nombre_1 VARCHAR(32),
    @Nombre_2 VARCHAR(32),
    @Apellido_1 VARCHAR(32),
    @Apellido_2 VARCHAR(32),
    @Numero_Celular VARCHAR(9),
    @Gmail NVARCHAR(50),
    @Direccion VARCHAR(200)

AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @Id_Persona INT;

    -- Insertar registro en la tabla Persona
    INSERT INTO Persona (Cedula, Nombre_1, Nombre_2, Apellido_1, Apellido_2, Numero_Celular, Gmail, Direccion)
    VALUES (@Cedula, @Nombre_1, @Nombre_2, @Apellido_1, @Apellido_2, @Numero_Celular, @Gmail, @Direccion);

    -- Obtener el Id_Persona generado
    SET @Id_Persona = SCOPE_IDENTITY();

    -- Insertar registro en la tabla Empleado
    INSERT INTO Empleado (Salario, Hora_Entrada, Hora_Salida, Id_Persona)
    VALUES (@Salario, @Hora_Entrada, @Hora_Salida, @Id_Persona);
END
GO

EXEC [dbo].[InsertarEmpleado] 2500.00, '08:00:00', '17:00:00', '111111111', 'Luis', '', 'Gómez', 'Hernández', '111111111', 'luis.gomez@gmail.com', 'Calle 789, Ciudad';
EXEC [dbo].[InsertarEmpleado] 3000.00, '08:00:00', '17:00:00', '123456789', 'María', '', 'López', 'González', '123456789', 'maria.lopez@gmail.com', 'Avenida 123, Ciudad';
EXEC [dbo].[InsertarEmpleado] 3500.00, '08:00:00', '17:00:00', '987654321', 'Pedro', '', 'Pérez', 'Gómez', '987654321', 'pedro.perez@gmail.com', 'Carrera 456, Ciudad';
GO

SELECT E.Id_Empleado, P.Cedula, P.Nombre_1, P.Nombre_2, P.Apellido_1, P.Apellido_2, E.Salario, E.Hora_Entrada, E.Hora_Salida, P.Numero_Celular, P.Gmail, P.Direccion
    FROM Empleado E
    INNER JOIN Persona P ON E.Id_Persona = P.Id_Persona;
GO


--Procedimiento para Insertar registro a la tabla Laboratorio ------------------------------------------------------

CREATE PROCEDURE [dbo].[InsertarLaboratorio]
(
    @Nombre VARCHAR(50)
)
AS
    INSERT INTO [Laboratorio] ([Nombre])
    VALUES (@Nombre)
GO

EXEC [dbo].[InsertarLaboratorio] 'Laboratorio A';
EXEC [dbo].[InsertarLaboratorio] 'Laboratorio B';
EXEC [dbo].[InsertarLaboratorio] 'Laboratorio C';
GO

SELECT * FROM Laboratorio



--Procedimiento para Insertar registro a la tabla Presentacion ------------------------------------------------------

CREATE PROCEDURE [dbo].[InsertarPresentacion]
(
    @Nombre_Presentacion VARCHAR(50),
    @Detalle VARCHAR(70)
)
AS
    INSERT INTO [Presentacion] ([Nombre_Presentacion],[Detalle])
    VALUES (@Nombre_Presentacion,@Detalle)
GO

EXEC [dbo].[InsertarPresentacion] 'Tableta', 'Presentación en forma de tableta';
EXEC [dbo].[InsertarPresentacion] 'Cápsula', 'Presentación en forma de cápsula';
EXEC [dbo].[InsertarPresentacion] 'Jarabe', 'Presentación en forma de jarabe';
GO

SELECT * FROM Presentacion



--Procedimiento para Insertar registro a la tabla Producto ------------------------------------------------------

CREATE PROCEDURE [dbo].[InsertarProducto]
    @Nombre VARCHAR(50),
    @Descripcion VARCHAR(200),
    @Cantidad_Producto INT,
    @Precio_Compra DECIMAL(8,2),
    @Precio_Venta DECIMAL(8,2),
    @Imagen_Producto VARBINARY(MAX),
    @Fecha_Caducidad DATE,
    @Id_Categoria INT,
    @Id_Presentacion INT,
    @Id_Laboratorio INT
AS
BEGIN
    SET NOCOUNT ON;

    INSERT INTO Producto (Nombre, Descripcion, Cantidad_Producto, Precio_Compra, Precio_Venta, Imagen_Producto, Fecha_Caducidad, Id_Categoria, Id_Presentacion, Id_Laboratorio)
    VALUES (@Nombre, @Descripcion, @Cantidad_Producto, @Precio_Compra, @Precio_Venta, @Imagen_Producto, @Fecha_Caducidad, @Id_Categoria, @Id_Presentacion, @Id_Laboratorio);

    SELECT SCOPE_IDENTITY() AS Id_Producto; -- Obtener el ID del producto insertado
END

GO

EXEC [dbo].[InsertarProducto] 'Paracetamol', 'Medicamento para el dolor y la fiebre', 50, 2.50, 5.00, NULL, '2024-06-01', 1, 1, 1;
EXEC [dbo].[InsertarProducto] 'Amoxicilina', 'Antibiótico de amplio espectro', 30, 5.00, 10.00, NULL, '2024-06-15', 1, 1, 1;
EXEC [dbo].[InsertarProducto] 'Loratadina', 'Antihistamínico para alergias', 20, 3.50, 7.00, NULL, '2024-06-30', 1, 1, 1;
GO
SELECT * FROM Producto


--Procedimiento para Insertar registro a la tabla Producto Proveedor ------------------------------------------------------

CREATE PROCEDURE [dbo].[InsertarProductoProveedor]
    @Id_Proveedor INT,
    @Id_Producto INT
AS
BEGIN
    SET NOCOUNT ON;

    INSERT INTO [Producto_Proveedor] (Id_Proveedor, Id_Producto)
    VALUES (@Id_Proveedor, @Id_Producto);
END
GO

EXEC [dbo].[InsertarProductoProveedor] 1, 2;
EXEC [dbo].[InsertarProductoProveedor]1, 2;
EXEC [dbo].[InsertarProductoProveedor] 1, 2;
GO

SELECT * FROM Producto_Proveedor
GO

--Procedimiento para Insertar registro a la tabla Proveedor ------------------------------------------------------

CREATE PROCEDURE [dbo].[InsertarProveedor]
    @Cedula VARCHAR(18),
    @Nombre_1 VARCHAR(32),
    @Nombre_2 VARCHAR(32),
    @Apellido_1 VARCHAR(32),
    @Apellido_2 VARCHAR(32),
    @Numero_Celular VARCHAR(9),
    @Gmail NVARCHAR(50),
    @Direccion VARCHAR(200)
AS
BEGIN
    SET NOCOUNT ON;

    -- Insertar registro en la tabla Persona
    INSERT INTO Persona (Cedula, Nombre_1, Nombre_2, Apellido_1, Apellido_2, Numero_Celular, Gmail, Direccion)
    VALUES (@Cedula, @Nombre_1, @Nombre_2, @Apellido_1, @Apellido_2, @Numero_Celular, @Gmail, @Direccion);

    -- Obtener el Id_Persona generado
    DECLARE @Id_Persona INT = SCOPE_IDENTITY();

    -- Insertar registro en la tabla Proveedor
    INSERT INTO Proveedor (Id_Persona)
    VALUES (@Id_Persona);
END
GO

EXEC dbo.InsertarProveedor 
    @Cedula = '123456789',
    @Nombre_1 = 'Juan',
    @Nombre_2 = 'Carlos',
    @Apellido_1 = 'Perez',
    @Apellido_2 = 'Gonzalez',
    @Numero_Celular = '123456789',
    @Gmail = 'juancarlos@gmail.com',
    @Direccion = 'Calle 10 #45-23';
GO
 SELECT PR.Id_Proveedor, RTRIM(P.Cedula) AS Cedula, RTRIM(P.Nombre_1) AS Nombre_1, RTRIM(P.Nombre_2) AS Nombre_2, RTRIM(P.Apellido_1) AS Apellido_1, RTRIM(P.Apellido_2) AS Apellido_2, RTRIM(P.Numero_Celular) AS Numero_Celular, RTRIM(P.Gmail) AS Gmail, RTRIM(P.Direccion) AS Direccion
    FROM Proveedor PR
    INNER JOIN Persona P ON PR.Id_Persona = P.Id_Persona;



--Procedimiento para Insertar registro a la tabla Venta ------------------------------------------------------

CREATE PROCEDURE [dbo].[InsertarVenta]
(
    @Fecha_Hora DATETIME,
    @Id_Cliente INT,
    @Id_Empleado INT
)
AS
    INSERT INTO [Venta] ([Fecha_Hora],[Id_Cliente],[Id_Empleado])
    VALUES (@Fecha_Hora,@Id_Cliente,@Id_Empleado)
GO
EXEC dbo.InsertarVenta 
    @Fecha_Hora = '2023-06-27T15:35:29',
    @Id_Cliente = 1,
    @Id_Empleado = 1;
GO

SELECT *FROM Venta

--Procedimiento para Insertar registro a la tabla Venta de Producto ------------------------------------------------------

CREATE PROCEDURE [dbo].[InsertarVentaProducto]
(
    @Cantidad INT,
    @Descuento DECIMAL(8,2),
    @Id_Venta INT,
    @Id_Producto INT
)
AS
    INSERT INTO [Venta_Producto] ([cantidad],[descuento],[Id_venta],[Id_Producto])
    VALUES (@cantidad,@descuento,@Id_venta,@Id_Producto)
GO

EXEC dbo.InsertarVentaProducto 
    @Cantidad = 3,
    @Descuento = 10.5,
    @Id_Venta = 1,
    @Id_Producto = 2;
GO

SELECT * FROM Venta_Producto
GO

--Procedimiento para Insertar registro a la tabla Producto y Ventas ------------------------------------------------------

CREATE PROCEDURE [dbo].[MostrarDatosCombinados]
AS
BEGIN
    SELECT p.Id_Producto, p.Nombre, p.Precio_Venta,
           vp.Cantidad, vp.Descuento,
           v.Id_Venta, v.Fecha_Hora, v.Id_Cliente, v.Id_Empleado
    FROM Producto p
    INNER JOIN Venta_Producto vp ON p.Id_Producto = vp.Id_Producto
    INNER JOIN Venta v ON vp.Id_Venta = v.Id_Venta;
END;
GO


--Procedimiento para Buscar un Producto ------------------------------------------------------

CREATE PROCEDURE [dbo].[MostrarProductos]
  @Busqueda NVARCHAR(100) = NULL
AS
BEGIN
    SET @Busqueda = RTRIM(@Busqueda);

    IF @Busqueda IS NULL OR @Busqueda = ''
    BEGIN
        SELECT Id_Producto, Nombre, Precio_Venta FROM Producto;
    END
    ELSE
    BEGIN
        SELECT Id_Producto, Nombre, Precio_Venta FROM Producto
        WHERE Nombre LIKE '%' + @Busqueda + '%';
    END
END;
GO

--Vistas -----------------------------------------------------------------


CREATE VIEW Vista_Categorias AS
SELECT * 
FROM Categoria;

CREATE VIEW Vista_Clientes AS
SELECT *
FROM Cliente;

CREATE VIEW Vista_Compras AS
SELECT *
FROM Compra;

CREATE VIEW Vista_Compra_Productos AS
SELECT CP.*, P.Nombre AS Nombre_Producto
FROM Compra_Producto CP
INNER JOIN Producto P ON CP.Id_Producto = P.Id_Producto;

CREATE VIEW Vista_Empleados AS
SELECT *
FROM Empleado;

CREATE VIEW Vista_Laboratorios AS
SELECT *
FROM Laboratorio;

CREATE VIEW Vista_Personas AS
SELECT *
FROM Persona;

CREATE VIEW Vista_Presentaciones AS
SELECT * 
FROM Presentacion;

CREATE VIEW Vista_Productos AS
SELECT *
FROM Producto;

CREATE VIEW Vista_Productos_Categoria_Presentacion AS
SELECT P.*, C.Nombre_Categoria, PR.Nombre_Presentacion
FROM Producto P
INNER JOIN Categoria C ON P.Id_Categoria = C.Id_Categoria
INNER JOIN Presentacion PR ON P.Id_Presentacion = PR.Id_Presentacion;

CREATE VIEW Vista_Productos_Proveedor AS
SELECT PP.*, P.Nombre AS Nombre_Producto, PR.Nombre_1 + ' ' + PR.Apellido_1 AS Nombre_Proveedor
FROM Producto_Proveedor PP
INNER JOIN Producto P ON PP.Id_Producto = P.Id_Producto
INNER JOIN Proveedor PV ON PP.Id_Proveedor = PV.Id_Proveedor
INNER JOIN Persona PR ON PV.Id_Persona = PR.Id_Persona;

CREATE VIEW Vista_Proveedores AS
SELECT *
FROM Proveedor;

CREATE VIEW Vista_Ventas AS
SELECT * 
FROM Venta;

CREATE VIEW Vista_Venta_Productos AS
SELECT VP.*, P.Nombre AS Nombre_Producto
FROM Venta_Producto VP
INNER JOIN Producto P ON VP.Id_Producto = P.Id_Producto;

GO
-- Pruebas de las Vistas

-- Consulta de la vista Vista_Categorias
SELECT * FROM Vista_Categorias;

-- Consulta de la vista Vista_Clientes
SELECT * FROM Vista_Clientes;

-- Consulta de la vista Vista_Compras
SELECT * FROM Vista_Compras;

-- Consulta de la vista Vista_Compra_Productos
SELECT * FROM Vista_Compra_Productos;

-- Consulta de la vista Vista_Empleados
SELECT * FROM Vista_Empleados;

-- Consulta de la vista Vista_Laboratorios
SELECT * FROM Vista_Laboratorios;

-- Consulta de la vista Vista_Personas
SELECT * FROM Vista_Personas;

-- Consulta de la vista Vista_Presentaciones
SELECT * FROM Vista_Presentaciones;

-- Consulta de la vista Vista_Productos
SELECT * FROM Vista_Productos;

-- Consulta de la vista Vista_Productos_Categoria_Presentacion
SELECT * FROM Vista_Productos_Categoria_Presentacion;

-- Consulta de la vista Vista_Productos_Proveedor
SELECT * FROM Vista_Productos_Proveedor;

-- Consulta de la vista Vista_Proveedores
SELECT * FROM Vista_Proveedores;

-- Consulta de la vista Vista_Ventas
SELECT * FROM Vista_Ventas;

-- Consulta de la vista Vista_Venta_Productos
SELECT * FROM Vista_Venta_Productos;
