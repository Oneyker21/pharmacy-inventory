USE [master]
GO

CREATE DATABASE [Farmacia_Rosales_DB]
 
CREATE TABLE [dbo].[Categoria](
	[Id_Categoria] int IDENTITY(1,1) PRIMARY KEY,
	[Nombre_Categoria] varchar (50) NOT NULL,
	[Descripcion] varchar(100) NULL
	)
GO

CREATE VIEW [dbo].[Vista_Categorias] AS
SELECT * 
FROM Categoria;
GO

CREATE TABLE [dbo].[Cliente](
	[Id_Cliente]  INT  IDENTITY(1,1) PRIMARY KEY,
	[Id_Persona] INT NULL
	)
GO

CREATE VIEW [dbo].[Vista_Clientes] AS
SELECT *
FROM Cliente;
GO


CREATE TABLE [dbo].[Compra](
	[Id_Compra] INT  IDENTITY(1,1) PRIMARY KEY,
	[Fecha_Compra] datetime NULL,
	[Id_Proveedor] int NULL


	)
GO

CREATE VIEW [dbo].[Vista_Compras] AS
SELECT *
FROM Compra;
GO



CREATE TABLE [dbo].[Producto](
	[Id_Producto] INT  IDENTITY(1,1) PRIMARY KEY,
	[Nombre] varchar(50) NOT NULL,
	[Descripcion] varchar(200) NULL,
	[Cantidad_Producto] INT NOT NULL,
	[Precio_Compra] decimal (10, 2) NULL,
	[Precio_Venta] decimal (10, 2) NULL,
	[Imagen_Producto]  varbinary (max) NULL,
	[Fecha_Caducidad] date NOT NULL,
	[Id_Categoria] INT NOT NULL,
	[Id_Presentacion] INT NOT NULL,
	[Id_Laboratorio] INT NOT NULL
	)
GO
CREATE TABLE [dbo].[Compra_Producto](
	[Id_Compra_Producto] INT  IDENTITY(1,1) PRIMARY KEY,
	[Id_Compra] INT NULL,
	[Id_Producto] INT NULL,
	[Cantidad] INT NULL,
	)
GO


CREATE VIEW [dbo].[Vista_Compra_Productos] AS
SELECT CP.*, P.Nombre AS Nombre_Producto
FROM Compra_Producto CP
INNER JOIN Producto P ON CP.Id_Producto = P.Id_Producto;
GO



CREATE TABLE [dbo].[Empleado](
	[Id_Empleado] int IDENTITY(1,1) PRIMARY KEY,
	[Hora_Salida] time (7) NOT NULL,
	[Salario] decimal (10, 2) NULL,
	[Id_Persona] INT NULL

	)
GO



CREATE VIEW [dbo].[Vista_Empleados] AS
SELECT *
FROM Empleado;
GO


CREATE TABLE [dbo].[Laboratorio](
	[Id_Laboratorio]  INT  IDENTITY(1,1) PRIMARY KEY,
	[Nombre] varchar (50) NOT NULL,
	)
GO


CREATE VIEW [dbo].[Vista_Laboratorios] AS
SELECT *
FROM Laboratorio;
GO


CREATE TABLE [dbo].[Persona](
	[Id_Persona]  INT  IDENTITY(1,1) PRIMARY KEY,
	[Cedula] varchar(18) NULL,
	[Nombre_1] varchar(32) NOT NULL,
	[Nombre_2] varchar(32) NULL,
	[Apellido_1] varchar(32) NOT NULL,
	[Apellido_2] varchar(32) NULL,
	[Numero_Celular] varchar(9) NULL,
	[Gmail] Nvarchar(50) NULL,
	[Direccion] varchar(200) NOT NULL
	)

GO


CREATE VIEW [dbo].[Vista_Personas] AS
SELECT *
FROM Persona;
GO



CREATE TABLE [dbo].[Presentacion](
	[Id_Presentacion] INT  IDENTITY(1,1) PRIMARY KEY,
	[Nombre_Presentacion] varchar(50) NOT NULL,
	[Detalle] varchar(70) NOT NULL
	)
GO



CREATE VIEW [dbo].[Vista_Presentaciones] AS
SELECT * 
FROM Presentacion;
GO


CREATE VIEW [dbo].[Vista_Productos] AS
SELECT *
FROM Producto;
GO



CREATE VIEW [dbo].[Vista_Productos_Categoria_Presentacion] AS
SELECT P.*, C.Nombre_Categoria, PR.Nombre_Presentacion
FROM Producto P
INNER JOIN Categoria C ON P.Id_Categoria = C.Id_Categoria
INNER JOIN Presentacion PR ON P.Id_Presentacion = PR.Id_Presentacion;
GO


CREATE TABLE [dbo].[Producto_Proveedor](
	[Id_Producto_Proveedor] INT  IDENTITY(1,1) PRIMARY KEY,
	[Id_Proveedor] INT NOT NULL,
	[Id_Producto] INT NOT NULL
	)

GO


CREATE TABLE [dbo].[Proveedor](
	[Id_Proveedor] INT  IDENTITY(1,1) PRIMARY KEY,
	[Id_Persona] INT NULL,
	)
GO





CREATE VIEW [dbo].[Vista_Proveedores] AS
SELECT *
FROM Proveedor;
GO


CREATE TABLE [dbo].[Venta](
	[Id_Venta] INT  IDENTITY(1,1) PRIMARY KEY,
	[Fecha_Hora] datetime NOT NULL,
	[Id_Cliente] INT NULL,
	[Id_Empleado] INT NULL

	)
GO


CREATE VIEW [dbo].[Vista_Ventas] AS
SELECT * 
FROM Venta;
GO


CREATE TABLE [dbo].[Venta_Producto](
	[Id_Venta_Producto]  INT  IDENTITY(1,1) PRIMARY KEY,
	[Cantidad] INT NOT NULL,
	[Descuento] decimal (8, 2) NULL,
	[Id_Venta] INT NOT NULL,
	[Id_Producto] INT NOT NULL

	)
GO


CREATE VIEW [dbo].[Vista_Venta_Productos] AS
SELECT VP.*, P.Nombre AS Nombre_Producto
FROM Venta_Producto VP
INNER JOIN Producto P ON VP.Id_Producto = P.Id_Producto;
GO





CREATE TABLE [dbo].[Usuarios](
	[Usuario] varchar(50) NOT NULL,
	[Contraseña] varchar(50) NULL,
	[Rol] varchar(50) NULL
)
GO


ALTER TABLE [dbo].[Cliente]  WITH CHECK ADD  CONSTRAINT [FK_Cliente_Persona] FOREIGN KEY([Id_Persona])
REFERENCES [dbo].[Persona] ([Id_Persona])
GO
ALTER TABLE [dbo].[Cliente] CHECK CONSTRAINT [FK_Cliente_Persona]
GO
ALTER TABLE [dbo].[Compra]  WITH CHECK ADD  CONSTRAINT [FK_Compra_Proveedor] FOREIGN KEY([Id_Proveedor])
REFERENCES [dbo].[Proveedor] ([Id_Proveedor])
GO
ALTER TABLE [dbo].[Compra] CHECK CONSTRAINT [FK_Compra_Proveedor]
GO
ALTER TABLE [dbo].[Compra_Producto]  WITH CHECK ADD  CONSTRAINT [FK_Compra_Producto_Compra] FOREIGN KEY([Id_Compra])
REFERENCES [dbo].[Compra] ([Id_Compra])
GO
ALTER TABLE [dbo].[Compra_Producto] CHECK CONSTRAINT [FK_Compra_Producto_Compra]
GO
ALTER TABLE [dbo].[Compra_Producto]  WITH CHECK ADD  CONSTRAINT [FK_Compra_Producto_Producto] FOREIGN KEY([Id_Producto])
REFERENCES [dbo].[Producto] ([Id_Producto])
GO
ALTER TABLE [dbo].[Compra_Producto] CHECK CONSTRAINT [FK_Compra_Producto_Producto]
GO
ALTER TABLE [dbo].[Empleado]  WITH CHECK ADD  CONSTRAINT [FK_Empleado_Persona] FOREIGN KEY([Id_Persona])
REFERENCES [dbo].[Persona] ([Id_Persona])
GO
ALTER TABLE [dbo].[Empleado] CHECK CONSTRAINT [FK_Empleado_Persona]
GO
ALTER TABLE [dbo].[Lote]  WITH CHECK ADD FOREIGN KEY([Id_Producto])
REFERENCES [dbo].[Producto] ([Id_Producto])
GO
ALTER TABLE [dbo].[Producto]  WITH CHECK ADD  CONSTRAINT [FK_Presentacion_Producto ] FOREIGN KEY([Id_Presentacion])
REFERENCES [dbo].[Presentacion] ([Id_Presentacion])
GO
ALTER TABLE [dbo].[Producto] CHECK CONSTRAINT [FK_Presentacion_Producto ]
GO
ALTER TABLE [dbo].[Producto]  WITH CHECK ADD  CONSTRAINT [FK_Producto_Categoria] FOREIGN KEY([Id_Categoria])
REFERENCES [dbo].[Categoria] ([Id_Categoria])
GO
ALTER TABLE [dbo].[Producto] CHECK CONSTRAINT [FK_Producto_Categoria]
GO
ALTER TABLE [dbo].[Producto]  WITH CHECK ADD  CONSTRAINT [FK_Productos_Laboratorio] FOREIGN KEY([Id_Laboratorio])
REFERENCES [dbo].[Laboratorio] ([Id_Laboratorio])
GO
ALTER TABLE [dbo].[Producto] CHECK CONSTRAINT [FK_Productos_Laboratorio]
GO
ALTER TABLE [dbo].[Producto_Proveedor]  WITH CHECK ADD  CONSTRAINT [FK_Producto_Proveedor_Producto] FOREIGN KEY([Id_Producto])
REFERENCES [dbo].[Producto] ([Id_Producto])
GO
ALTER TABLE [dbo].[Producto_Proveedor] CHECK CONSTRAINT [FK_Producto_Proveedor_Producto]
GO
ALTER TABLE [dbo].[Producto_Proveedor]  WITH CHECK ADD  CONSTRAINT [FK_Producto_Proveedor_Proveedor] FOREIGN KEY([Id_Proveedor])
REFERENCES [dbo].[Proveedor] ([Id_Proveedor])
GO
ALTER TABLE [dbo].[Producto_Proveedor] CHECK CONSTRAINT [FK_Producto_Proveedor_Proveedor]
GO
ALTER TABLE [dbo].[Proveedor]  WITH CHECK ADD  CONSTRAINT [FK_Proveedor_Persona] FOREIGN KEY([Id_Persona])
REFERENCES [dbo].[Persona] ([Id_Persona])
GO
ALTER TABLE [dbo].[Proveedor] CHECK CONSTRAINT [FK_Proveedor_Persona]
GO
ALTER TABLE [dbo].[Venta]  WITH CHECK ADD  CONSTRAINT [FK_Venta_Cliente] FOREIGN KEY([Id_Cliente])
REFERENCES [dbo].[Cliente] ([Id_Cliente])
GO
ALTER TABLE [dbo].[Venta] CHECK CONSTRAINT [FK_Venta_Cliente]
GO
ALTER TABLE [dbo].[Venta]  WITH CHECK ADD  CONSTRAINT [FK_Venta_Empleado] FOREIGN KEY([Id_Empleado])
REFERENCES [dbo].[Empleado] ([Id_Empleado])
GO
ALTER TABLE [dbo].[Venta] CHECK CONSTRAINT [FK_Venta_Empleado]
GO
ALTER TABLE [dbo].[Venta_Producto]  WITH CHECK ADD  CONSTRAINT [FK_Venta_Producto_Producto] FOREIGN KEY([Id_Producto])
REFERENCES [dbo].[Producto] ([Id_Producto])
GO
ALTER TABLE [dbo].[Venta_Producto] CHECK CONSTRAINT [FK_Venta_Producto_Producto]
GO
ALTER TABLE [dbo].[Venta_Producto]  WITH CHECK ADD  CONSTRAINT [FK_Venta_Producto_Venta] FOREIGN KEY([Id_Venta])
REFERENCES [dbo].[Venta] ([Id_Venta])
GO
ALTER TABLE [dbo].[Venta_Producto] CHECK CONSTRAINT [FK_Venta_Producto_Venta]
GO
/****** Object:  StoredProcedure [dbo].[ActualizarCategoria]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

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
/****** Object:  StoredProcedure [dbo].[ActualizarCliente]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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

    -- Actualizar registro en la tabla Persona
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
/****** Object:  StoredProcedure [dbo].[ActualizarCompra]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ActualizarCompra]
  @Id_Compra INT,
  @Fecha_Compra DATE,
  @Id_Proveedor INT
AS
  UPDATE [Compra]
  SET fecha_compra = @Fecha_Compra, Id_proveedor = @Id_Proveedor
  WHERE Id_Compra = @Id_Compra
GO
/****** Object:  StoredProcedure [dbo].[ActualizarCompraProducto]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
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
/****** Object:  StoredProcedure [dbo].[ActualizarEmpleado]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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

    -- Actualizar registro en la tabla Persona
    UPDATE Persona
    SET Cedula = @Cedula, Nombre_1 = @Nombre_1, Nombre_2 = @Nombre_2, Apellido_1 = @Apellido_1, Apellido_2 = @Apellido_2, Numero_Celular = @Numero_Celular, Gmail = @Gmail, Direccion = @Direccion
    WHERE Id_Persona = (SELECT Id_Persona FROM Empleado WHERE Id_Empleado = @Id_Empleado);

    -- Actualizar registro en la tabla Empleado
    UPDATE Empleado
    SET Salario = @Salario, Hora_Entrada = @Hora_Entrada, Hora_Salida = @Hora_Salida
    WHERE Id_Empleado = @Id_Empleado;
END
GO
/****** Object:  StoredProcedure [dbo].[ActualizarLaboratorio]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

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
/****** Object:  StoredProcedure [dbo].[ActualizarPresentacion]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
/****** Object:  StoredProcedure [dbo].[ActualizarProducto]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
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
/****** Object:  StoredProcedure [dbo].[ActualizarProductoConProveedor]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ActualizarProductoConProveedor]
  @IdProducto int,
  @NombreProducto varchar(50),
  @DescripcionProducto varchar(200),
  @CantidadProducto int,
  @PrecioCompra decimal(10,2),
  @PrecioVenta decimal(10,2),
  @ImagenProducto varbinary(max),
  @FechaCaducidad date,
  @IdCategoria int,
  @IdPresentacion int,
  @IdLaboratorio int,
  @IdProveedor int
AS
BEGIN
  SET NOCOUNT ON;

  -- Actualizar el producto en la tabla Producto
  UPDATE Producto
  SET
    Nombre = @NombreProducto,
    Descripcion = @DescripcionProducto,
    Cantidad_Producto = @CantidadProducto,
    Precio_Compra = @PrecioCompra,
    Precio_Venta = @PrecioVenta,
    Imagen_Producto = @ImagenProducto,
    Fecha_Caducidad = @FechaCaducidad,
    Id_Categoria = @IdCategoria,
    Id_Presentacion = @IdPresentacion,
    Id_Laboratorio = @IdLaboratorio
  WHERE
    Id_Producto = @IdProducto;

  -- Actualizar la relación entre el producto y el proveedor en la tabla Producto_Proveedor
  UPDATE Producto_Proveedor
  SET
    Id_Proveedor = @IdProveedor
  WHERE
    Id_Producto = @IdProducto;
END
GO
/****** Object:  StoredProcedure [dbo].[ActualizarProductoProveedor]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
/****** Object:  StoredProcedure [dbo].[ActualizarProductoYProveedor]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ActualizarProductoYProveedor]
   @IdProducto int,
   @NombreProducto varchar(50),
   @DescripcionProducto varchar(200),
   @CantidadProducto int,
   @PrecioCompra decimal(8,2),
   @PrecioVenta decimal(8,2),
   @ImagenProducto varbinary(max),
   @FechaCaducidad date,
   @IdCategoria int,
   @IdPresentacion int,
   @IdLaboratorio int,
   @IdProveedor int
AS
BEGIN
  SET NOCOUNT ON;

  -- Actualizar el producto en la tabla Producto
  UPDATE Producto
  SET 
    Nombre = @NombreProducto,
    Descripcion = @DescripcionProducto,
    Cantidad_Producto = @CantidadProducto,
    Precio_Compra = @PrecioCompra,
    Precio_Venta = @PrecioVenta,
    Imagen_Producto = @ImagenProducto,
    Fecha_Caducidad = @FechaCaducidad,
    Id_Categoria = @IdCategoria,
    Id_Presentacion = @IdPresentacion,
    Id_Laboratorio = @IdLaboratorio
  WHERE
    Id_Producto = @IdProducto;
    
  -- Actualizar el proveedor del producto en la tabla Producto_Proveedor
  UPDATE Producto_Proveedor
  SET 
    Id_Proveedor = @IdProveedor
  WHERE
    Id_Producto = @IdProducto;
END
GO
/****** Object:  StoredProcedure [dbo].[ActualizarProveedor]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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

    -- Actualizar registro en la tabla Persona
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
/****** Object:  StoredProcedure [dbo].[ActualizarVenta]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
/****** Object:  StoredProcedure [dbo].[ActualizarVentaProducto]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
/****** Object:  StoredProcedure [dbo].[AgregarCompraYProducto]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[AgregarCompraYProducto]
    @FechaCompra DATETIME,
    @IdProveedor INT,
    @IdProducto INT,
    @Cantidad INT
AS
BEGIN
    DECLARE @IdCompra INT;

    -- Verificar si ya existe una compra con la misma fecha y proveedor
    SELECT @IdCompra = Id_Compra
    FROM Compra
    WHERE Fecha_Compra = @FechaCompra AND Id_Proveedor = @IdProveedor;

    -- Si no existe, crear una nueva compra
    IF @IdCompra IS NULL
    BEGIN
        INSERT INTO Compra (Fecha_Compra, Id_Proveedor)
        VALUES (@FechaCompra, @IdProveedor);

        SET @IdCompra = SCOPE_IDENTITY();
    END
	  -- Verificar si ya existe una entrada en Venta_Producto para el producto y la venta
    IF NOT EXISTS (SELECT 1 FROM Compra_Producto WHERE Id_Compra = @IdCompra AND Id_Producto = @IdProducto)
    BEGIN

    -- Insertar un nuevo registro en la tabla Compra_Producto
    INSERT INTO Compra_Producto (Id_Compra, Id_Producto, Cantidad)
    VALUES (@IdCompra, @IdProducto, @Cantidad);
    
    -- Actualizar la cantidad del producto en la tabla Producto
    UPDATE Producto
    SET Cantidad_Producto = Cantidad_Producto + @Cantidad
    WHERE Id_Producto = @IdProducto;

	
    END
END





GO
/****** Object:  StoredProcedure [dbo].[AgregarLote]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
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
/****** Object:  StoredProcedure [dbo].[AgregarVentaYProducto]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[AgregarVentaYProducto]
@Fecha_Hora DATETIME,
@Id_Cliente INT = NULL,
@Id_Empleado INT,
@Descuento DECIMAL(8, 2) = NULL,
@Id_Producto INT,
@Cantidad INT
AS
BEGIN
    DECLARE @Id_Venta INT;

    -- Verificar si ya existe una venta con la misma fecha y hora, cliente y empleado
    SELECT @Id_Venta = Id_Venta 
    FROM Venta 
    WHERE Fecha_Hora = @Fecha_Hora AND (Id_Cliente = @Id_Cliente OR @Id_Cliente IS NULL) AND Id_Empleado = @Id_Empleado;

    -- Si no existe, crear una nueva venta
    IF @Id_Venta IS NULL
    BEGIN
        INSERT INTO Venta (Fecha_Hora, Id_Cliente, Id_Empleado)
        VALUES (@Fecha_Hora, @Id_Cliente, @Id_Empleado);

        SET @Id_Venta = SCOPE_IDENTITY();
    END

    -- Verificar si ya existe una entrada en Venta_Producto para el producto y la venta
    IF NOT EXISTS (SELECT 1 FROM Venta_Producto WHERE Id_Venta = @Id_Venta AND Id_Producto = @Id_Producto)
    BEGIN
        -- Agregar una nueva entrada en Venta_Producto para el producto
        INSERT INTO Venta_Producto (Cantidad, Descuento, Id_Venta, Id_Producto)
        VALUES (@Cantidad, @Descuento, @Id_Venta, @Id_Producto);

        -- Actualizar la cantidad del producto en la tabla Producto
        UPDATE Producto
        SET Cantidad_Producto = Cantidad_Producto - @Cantidad
        WHERE Id_Producto = @Id_Producto;
    END
END
GO
/****** Object:  StoredProcedure [dbo].[BorrarProductoYProveedor]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[BorrarProductoYProveedor]
   @IdProducto int
AS
BEGIN
  SET NOCOUNT ON;

  -- Primero, eliminar la relación entre el producto y el proveedor en la tabla Producto_Proveedor
  DELETE FROM Producto_Proveedor
  WHERE Id_Producto = @IdProducto;

  -- Luego, eliminar el producto de la tabla Producto
  DELETE FROM Producto
  WHERE Id_Producto = @IdProducto;
END
GO
/****** Object:  StoredProcedure [dbo].[BuscaProductos]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[BuscaProductos]
  @Busqueda NVARCHAR(100) = NULL
AS
BEGIN
    SET @Busqueda = RTRIM(@Busqueda);
    SET @Busqueda = ISNULL(@Busqueda, '');

    SELECT
        p.Id_Producto,
        p.Nombre,
        p.Descripcion,
        p.Precio_Venta,
        cat.Nombre_Categoria AS Categoria,
        pre.Nombre_Presentacion AS Presentacion,
        lab.Nombre AS Laboratorio
    FROM
        Producto p
        LEFT JOIN Categoria cat ON p.Id_Categoria = cat.Id_Categoria
        LEFT JOIN Presentacion pre ON p.Id_Presentacion = pre.Id_Presentacion
        LEFT JOIN Laboratorio lab ON p.Id_Laboratorio = lab.Id_Laboratorio
    WHERE 
        (@Busqueda = '' OR 
        p.Nombre LIKE '%' + @Busqueda + '%' OR
        p.Descripcion LIKE '%' + @Busqueda + '%');
END;
GO
/****** Object:  StoredProcedure [dbo].[BuscarCategoria]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
/****** Object:  StoredProcedure [dbo].[BuscarCategoria_Caja]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
/****** Object:  StoredProcedure [dbo].[BuscarCliente]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
/****** Object:  StoredProcedure [dbo].[BuscarCompra]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[BuscarCompra]
  @Id_Compra INT
AS
  SELECT *
  FROM Compra
  WHERE Id_Compra = @Id_Compra
GO
/****** Object:  StoredProcedure [dbo].[BuscarCompraPorIdFecha]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[BuscarCompraPorIdFecha]
    @IdCompra NVARCHAR(100),
    @FechaCompra NVARCHAR(100)
AS
BEGIN
    SET NOCOUNT ON;

    SELECT 
        C.Id_Compra, 
        ISNULL(Prod.Nombre, 'N/A') AS Producto,
        CP.Cantidad, 
        ISNULL(Per.Nombre_1, 'N/A') + ' ' + ISNULL(Per.Apellido_1, 'N/A') AS Proveedor, 
        CAST(C.Fecha_Compra AS NVARCHAR(100)) AS Fecha_Compra
    FROM 
        Compra AS C
    LEFT JOIN 
        Compra_Producto AS CP ON C.Id_Compra = CP.Id_Compra
    LEFT JOIN 
        Producto AS Prod ON CP.Id_Producto = Prod.Id_Producto
    LEFT JOIN 
        Proveedor AS Prov ON C.Id_Proveedor = Prov.Id_Proveedor
    LEFT JOIN 
        Persona AS Per ON Prov.Id_Persona = Per.Id_Persona
    WHERE
        CAST(C.Id_Compra AS NVARCHAR(100)) LIKE '%' + @IdCompra + '%' AND
        CAST(C.Fecha_Compra AS NVARCHAR(100)) LIKE '%' + @FechaCompra + '%'
END
GO
/****** Object:  StoredProcedure [dbo].[BuscarCompraProducto]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[BuscarCompraProducto]
  @Id_Compra_Producto INT
AS
  SELECT *
  FROM Compra_Producto
  WHERE Id_Compra_Producto = @Id_Compra_Producto
GO
/****** Object:  StoredProcedure [dbo].[BuscarEmpleado]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
/****** Object:  StoredProcedure [dbo].[BuscarLaboratorio]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
/****** Object:  StoredProcedure [dbo].[BuscarLaboratorio_caja]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
/****** Object:  StoredProcedure [dbo].[BuscarPresentacion]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

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
/****** Object:  StoredProcedure [dbo].[BuscarPresentacion_Caja]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[BuscarPresentacion_Caja]
    @Busqueda VARCHAR(50)
AS
BEGIN
    SET @Busqueda = RTRIM(@Busqueda);

    IF @Busqueda IS NULL OR @Busqueda = ''
    BEGIN
        -- No se proporcionó una cadena de búsqueda, mostrar todas las presentaciones
        SELECT [Id_Presentacion], [Nombre_Presentacion]
        FROM [Presentacion]
    END
    ELSE
    BEGIN
        -- Realizar la búsqueda basada en el parámetro de búsqueda
        SELECT [Id_Presentacion], [Nombre_Presentacion]
        FROM [Presentacion]
        WHERE [Nombre_Presentacion] LIKE '%' + @Busqueda + '%'
    END;
END;
GO
/****** Object:  StoredProcedure [dbo].[BuscarProducto]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
/****** Object:  StoredProcedure [dbo].[BuscarProductoconProveedor]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[BuscarProductoconProveedor]
    @Busqueda NVARCHAR(100)
AS
BEGIN
    SELECT 
        P.Id_Producto,
        P.Nombre,
        P.Descripcion,
        P.Cantidad_Producto,
        P.Precio_Compra,
        P.Precio_Venta,
        P.Imagen_Producto,
        P.Fecha_Caducidad,
        C.Nombre_Categoria,
        Pres.Nombre_Presentacion,
        L.Nombre AS Nombre_Laboratorio,
        Prv.Nombre_1 + ' ' + Prv.Apellido_1 AS Proveedor
    FROM 
        dbo.Producto AS P
    INNER JOIN 
        dbo.Producto_Proveedor AS PP ON P.Id_Producto = PP.Id_Producto
    INNER JOIN 
        dbo.Proveedor AS Pr ON PP.Id_Proveedor = Pr.Id_Proveedor
    INNER JOIN 
        dbo.Persona AS Prv ON Pr.Id_Persona = Prv.Id_Persona
    INNER JOIN 
        dbo.Laboratorio AS L ON P.Id_Laboratorio = L.Id_Laboratorio
    INNER JOIN 
        dbo.Categoria AS C ON P.Id_Categoria = C.Id_Categoria
    INNER JOIN 
        dbo.Presentacion AS Pres ON P.Id_Presentacion = Pres.Id_Presentacion
    WHERE 
    RTRIM(TRY_CAST(P.Nombre AS NVARCHAR(100))) LIKE '%' + @Busqueda + '%'
    OR RTRIM(TRY_CAST(P.Descripcion AS NVARCHAR(100))) LIKE '%' + @Busqueda + '%'
    OR RTRIM(CONVERT(NVARCHAR(100), P.Fecha_Caducidad, 120)) LIKE '%' + @Busqueda + '%';

END
GO
/****** Object:  StoredProcedure [dbo].[BuscarProductoProveedor]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[BuscarProductoProveedor]
(
    @Id_Producto_Proveedor INT
)
AS
    SELECT *
    FROM [Producto_Proveedor]
    WHERE [Id_Producto_Proveedor] = @Id_Producto_Proveedor
GO
/****** Object:  StoredProcedure [dbo].[BuscarProveedor]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
/****** Object:  StoredProcedure [dbo].[BuscarProveedor_Caja]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
	END

GO
/****** Object:  StoredProcedure [dbo].[BuscarVenta]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[BuscarVenta]
(
    @Id_Venta INT
)
AS
    SELECT *
    FROM [Venta]
    WHERE [Id_Venta] = @Id_Venta
GO
/****** Object:  StoredProcedure [dbo].[BuscarVentaPorIdFechaHora]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[BuscarVentaPorIdFechaHora]
    @IdVenta NVARCHAR(100),
    @FechaHora NVARCHAR(100)
AS
BEGIN
    SET NOCOUNT ON;

    SELECT 
        V.Id_Venta, 
        ISNULL(Prod.Nombre, 'N/A') AS Producto,
        VP.Cantidad, 
        VP.Descuento, 
        ISNULL(PCliente.Nombre_1, 'N/A') + ' ' + ISNULL(PCliente.Apellido_1, 'N/A') AS Cliente, 
        ISNULL(PEmpleado.Nombre_1, 'N/A') + ' ' + ISNULL(PEmpleado.Apellido_1, 'N/A') AS Empleado,
        V.Fecha_Hora
    FROM 
        Venta AS V
    LEFT JOIN 
        Venta_Producto AS VP ON V.Id_Venta = VP.Id_Venta
    LEFT JOIN 
        Producto AS Prod ON VP.Id_Producto = Prod.Id_Producto
    LEFT JOIN 
        Cliente AS C ON V.Id_Cliente = C.Id_Cliente
    LEFT JOIN 
        Persona AS PCliente ON C.Id_Persona = PCliente.Id_Persona
    LEFT JOIN 
        Empleado AS E ON V.Id_Empleado = E.Id_Empleado
    LEFT JOIN 
        Persona AS PEmpleado ON E.Id_Persona = PEmpleado.Id_Persona
    WHERE
        CAST(V.Id_Venta AS NVARCHAR(100)) LIKE '%' + @IdVenta + '%' AND
        CAST(V.Fecha_Hora AS NVARCHAR(100)) LIKE '%' + @FechaHora + '%'
END
GO
/****** Object:  StoredProcedure [dbo].[BuscarVentaProducto]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
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
/****** Object:  StoredProcedure [dbo].[BuscarVentasPorNombreDescripcionFecha]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[BuscarVentasPorNombreDescripcionFecha]
    @TextoBusqueda VARCHAR(100)
AS
BEGIN
    SELECT
        V.Id_Venta,
        ISNULL(Prod.Nombre, 'N/A') AS Producto,
        VP.Cantidad,
        VP.Descuento,
        ISNULL(PCliente.Nombre_1, 'N/A') + ' ' + ISNULL(PCliente.Apellido_1, 'N/A') AS Cliente,
        ISNULL(PEmpleado.Nombre_1, 'N/A') + ' ' + ISNULL(PEmpleado.Apellido_1, 'N/A') AS Empleado,
        V.Fecha_Hora,
        (VP.Cantidad * Prod.Precio_Venta) AS Total,
        CASE
            WHEN VP.Id_Venta_Producto = MAX(VP.Id_Venta_Producto) OVER (PARTITION BY V.Id_Venta) THEN SUM(VP.Cantidad * Prod.Precio_Venta * (1 - VP.Descuento / 100)) OVER (PARTITION BY V.Id_Venta)
            ELSE NULL
        END AS TotalGeneral
    FROM
        Venta AS V
    LEFT JOIN
        Venta_Producto AS VP ON V.Id_Venta = VP.Id_Venta
    LEFT JOIN
        Producto AS Prod ON VP.Id_Producto = Prod.Id_Producto
    LEFT JOIN
        Cliente AS C ON V.Id_Cliente = C.Id_Cliente
    LEFT JOIN
        Persona AS PCliente ON C.Id_Persona = PCliente.Id_Persona
    LEFT JOIN
        Empleado AS E ON V.Id_Empleado = E.Id_Empleado
    LEFT JOIN
        Persona AS PEmpleado ON E.Id_Persona = PEmpleado.Id_Persona
    WHERE
        Prod.Nombre LIKE '%' + @TextoBusqueda + '%'
        OR Prod.Descripcion LIKE '%' + @TextoBusqueda + '%'
        OR CONVERT(VARCHAR, V.Fecha_Hora, 120) LIKE '%' + @TextoBusqueda + '%'
    ORDER BY
        V.Id_Venta, VP.Id_Venta_Producto;
END
GO
/****** Object:  StoredProcedure [dbo].[BuscarVentaYProducto]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[BuscarVentaYProducto]
    @Parametro NVARCHAR(100) = NULL
AS
BEGIN
    SELECT 
        V.Id_Venta, 
        V.Fecha_Hora,
        V.Id_Cliente
    FROM 
        Venta AS V
    LEFT JOIN 
        Venta_Producto AS VP ON V.Id_Venta = VP.Id_Venta
    LEFT JOIN 
        Producto AS Prod ON VP.Id_Producto = Prod.Id_Producto
    LEFT JOIN 
        Cliente AS C ON V.Id_Cliente = C.Id_Cliente
    LEFT JOIN 
        Persona AS PCliente ON C.Id_Persona = PCliente.Id_Persona
    LEFT JOIN 
        Empleado AS E ON V.Id_Empleado = E.Id_Empleado
    LEFT JOIN 
        Persona AS PEmpleado ON E.Id_Persona = PEmpleado.Id_Persona
    WHERE
        (CAST(V.Id_Venta AS NVARCHAR(10)) LIKE '%' + @Parametro + '%'
        OR CONVERT(NVARCHAR(50), V.Fecha_Hora, 20) LIKE '%' + @Parametro + '%'
        OR CAST(V.Id_Cliente AS NVARCHAR(10)) LIKE '%' + @Parametro + '%');
END

GO
/****** Object:  StoredProcedure [dbo].[CalcularTotalCantidadProducto]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[CalcularTotalCantidadProducto]
AS
BEGIN
    SET NOCOUNT ON;
    DECLARE @TotalCantidad INT;

    SELECT @TotalCantidad = SUM(Cantidad_Producto)
    FROM Producto;

    SELECT @TotalCantidad AS TotalCantidad;
END
GO
/****** Object:  StoredProcedure [dbo].[ConsultarDatosCategoria]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ConsultarDatosCategoria]
AS
    SELECT [Id_Categoria],[Nombre_Categoria], [Descripcion]
    FROM Categoria;
GO
/****** Object:  StoredProcedure [dbo].[ConsultarDatosCliente]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ConsultarDatosCliente]
AS
BEGIN
    SET NOCOUNT ON;

    SELECT C.Id_Cliente, RTRIM(P.Cedula) AS Cedula, RTRIM(P.Nombre_1) AS Nombre_1, RTRIM(P.Nombre_2) AS Nombre_2, RTRIM(P.Apellido_1) AS Apellido_1, RTRIM(P.Apellido_2) AS Apellido_2, RTRIM(P.Numero_Celular) AS Numero_Celular, RTRIM(P.Gmail) AS Gmail, RTRIM(P.Direccion) AS Direccion
    FROM Cliente C
    INNER JOIN Persona P ON C.Id_Persona = P.Id_Persona;
END
GO
/****** Object:  StoredProcedure [dbo].[ConsultarDatosCompra]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ConsultarDatosCompra]
AS
    SELECT [Id_compra], [Fecha_Compra], [Id_Proveedor]
    FROM Compra;
GO
/****** Object:  StoredProcedure [dbo].[ConsultarDatosCompraProducto]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ConsultarDatosCompraProducto]
AS
    SELECT [Id_Compra_Producto], [Id_Compra], [Id_Producto]
    FROM Compra_Producto;
GO
/****** Object:  StoredProcedure [dbo].[ConsultarDatosEmpleado]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ConsultarDatosEmpleado]
AS
BEGIN
    SET NOCOUNT ON;

    SELECT E.Id_Empleado, P.Cedula, P.Nombre_1, P.Nombre_2, P.Apellido_1, P.Apellido_2, E.Salario, E.Hora_Entrada, E.Hora_Salida, P.Numero_Celular, P.Gmail, P.Direccion
    FROM Empleado E
    INNER JOIN Persona P ON E.Id_Persona = P.Id_Persona;
END
GO
/****** Object:  StoredProcedure [dbo].[ConsultarDatosLaboratorio]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ConsultarDatosLaboratorio]
AS
    SELECT [Id_Laboratorio], [Nombre]
    FROM Laboratorio;
GO
/****** Object:  StoredProcedure [dbo].[ConsultarDatosPresentacion]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[ConsultarDatosPresentacion]
AS
    SELECT [Id_Presentacion], [Nombre_Presentacion], [Detalle]
    FROM Presentacion;
GO
/****** Object:  StoredProcedure [dbo].[ConsultarDatosProducto]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO



--Procedimiento para Mostrar la tabla Producto  ------------------------------------------------------

CREATE PROCEDURE [dbo].[ConsultarDatosProducto]
AS
    SELECT [Id_Producto], [Nombre], [Descripcion], [Cantidad_Producto], [Precio_Compra], [Precio_Venta], [Imagen_Producto], [Fecha_Caducidad], [Id_Categoria], [Id_Presentacion], [Id_Laboratorio]
    FROM [Producto]
GO
/****** Object:  StoredProcedure [dbo].[ConsultarDatosProveedor]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ConsultarDatosProveedor]
AS
BEGIN
    SET NOCOUNT ON;

    SELECT PR.Id_Proveedor, RTRIM(P.Cedula) AS Cedula, RTRIM(P.Nombre_1) AS Nombre_1, RTRIM(P.Nombre_2) AS Nombre_2, RTRIM(P.Apellido_1) AS Apellido_1, RTRIM(P.Apellido_2) AS Apellido_2, RTRIM(P.Numero_Celular) AS Numero_Celular, RTRIM(P.Gmail) AS Gmail, RTRIM(P.Direccion) AS Direccion
    FROM Proveedor PR
    INNER JOIN Persona P ON PR.Id_Persona = P.Id_Persona;
END
GO
/****** Object:  StoredProcedure [dbo].[ConsultarDatosVenta]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO



--Procedimiento para Mostrar la tabla Venta  ------------------------------------------------------

CREATE PROCEDURE [dbo].[ConsultarDatosVenta]
AS
    SELECT [Id_Venta], [Fecha_Hora], [Id_Cliente], [Id_Empleado]
    FROM Venta;
GO
/****** Object:  StoredProcedure [dbo].[ConsultarDatosVentaProducto]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO




--Procedimiento para Mostrar la tabla Venta de Producto   ------------------------------------------------------

CREATE PROCEDURE [dbo].[ConsultarDatosVentaProducto]
AS
    SELECT [Id_Venta_Producto], [cantidad], [descuento], [Id_venta], [Id_Producto]
    FROM Venta_Producto;
GO
/****** Object:  StoredProcedure [dbo].[ConsultarTablaProductoProveedor]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO



--Procedimiento para Mostrar la tabla de Producto y Proveedor ------------------------------------------------------

CREATE PROCEDURE [dbo].[ConsultarTablaProductoProveedor]
AS
    SELECT *
    FROM [Producto_Proveedor]
GO
/****** Object:  StoredProcedure [dbo].[EliminarCategoria]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[EliminarCategoria]
(
    @Id_Categoria INT
)
AS
    DELETE FROM [Categoria]
    WHERE[Id_Categoria] = @Id_Categoria
GO
/****** Object:  StoredProcedure [dbo].[EliminarCliente]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[EliminarCliente]
    @Id_Cliente INT
AS
BEGIN
    SET NOCOUNT ON;

    -- Eliminar registro en la tabla Cliente
    DELETE FROM Cliente
    WHERE Id_Cliente = @Id_Cliente;

    -- Eliminar registro en la tabla Persona si no está en uso en otras tablas
    IF NOT EXISTS (SELECT 1 FROM Empleado WHERE Id_Persona = (SELECT Id_Persona FROM Cliente WHERE Id_Cliente = @Id_Cliente))
    BEGIN
        DELETE FROM Persona
        WHERE Id_Persona = (SELECT Id_Persona FROM Cliente WHERE Id_Cliente = @Id_Cliente);
    END
END
GO
/****** Object:  StoredProcedure [dbo].[EliminarCompra]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[EliminarCompra]
  @Id_Compra INT
AS
  DELETE FROM Compra
  WHERE Id_compra = @Id_Compra
GO
/****** Object:  StoredProcedure [dbo].[EliminarCompraProducto]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO



--Procedimiento para eliminar registro de la tabla Compra y Producto------------------------------------------------------

CREATE PROCEDURE [dbo].[EliminarCompraProducto]
  @Id_Compra_Producto INT
AS
  DELETE FROM Compra_Producto
  WHERE Id_Compra_Producto = @Id_Compra_Producto
GO
/****** Object:  StoredProcedure [dbo].[EliminarCompraYProducto]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[EliminarCompraYProducto]
   @Id_Compra INT
AS
BEGIN
    -- Eliminar las entradas en Venta_Producto que tengan el mismo Id_Venta
    DELETE FROM Compra_Producto
    WHERE Id_Compra = @Id_Compra;

    -- Eliminar la venta correspondiente al Id_Venta
    DELETE FROM Compra
    WHERE Id_Compra = @Id_Compra;
END
GO
/****** Object:  StoredProcedure [dbo].[EliminarEmpleado]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[EliminarEmpleado]
(
    @Id_Empleado INT
)
AS
BEGIN
    SET NOCOUNT ON;

    -- Eliminar registro de la tabla Empleado
    DELETE FROM Empleado WHERE Id_Empleado = @Id_Empleado;

    -- Eliminar registro de la tabla Persona relacionado al empleado
    DELETE FROM Persona WHERE Id_Persona = (SELECT Id_Persona FROM Empleado WHERE Id_Empleado = @Id_Empleado);
END
GO
/****** Object:  StoredProcedure [dbo].[EliminarLaboratorio]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
----Procedimiento de EliminarLaboratorio-------------------------------------------------------

CREATE PROCEDURE [dbo].[EliminarLaboratorio]
(
    @Id_Laboratorio INT
)
AS
    DELETE FROM [Laboratorio]
    WHERE [Id_Laboratorio] = @Id_Laboratorio
GO
/****** Object:  StoredProcedure [dbo].[EliminarPresentacion]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[EliminarPresentacion]
(
    @Id_Presentacion INT
)
AS
    DELETE FROM [Presentacion]
    WHERE[Id_Presentacion] = @Id_Presentacion
GO
/****** Object:  StoredProcedure [dbo].[EliminarProducto]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[EliminarProducto]
(
    @Id_Producto INT
)
AS
    DELETE FROM [Producto]
    WHERE [Id_Producto] = @Id_Producto
GO
/****** Object:  StoredProcedure [dbo].[EliminarProductoConProveedor]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[EliminarProductoConProveedor]
  @IdProducto int
AS
BEGIN
  -- Eliminar la asociación del producto con el proveedor en la tabla Producto_Proveedor
  DELETE FROM [dbo].[Producto_Proveedor]
  WHERE
    [Id_Producto] = @IdProducto

  -- Eliminar el producto de la tabla Producto
  DELETE FROM [dbo].[Producto]
  WHERE
    [Id_Producto] = @IdProducto
END
GO
/****** Object:  StoredProcedure [dbo].[EliminarProductoProveedor]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[EliminarProductoProveedor]
(
    @Id_Producto_Proveedor INT
)
AS
    DELETE FROM [Producto_Proveedor]
    WHERE [Id_Producto_Proveedor] = @Id_Producto_Proveedor
GO
/****** Object:  StoredProcedure [dbo].[EliminarProveedor]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[EliminarProveedor]
    @Id_Proveedor INT
AS
BEGIN
    SET NOCOUNT ON;

    -- Eliminar registro en la tabla Proveedor
    DELETE FROM Proveedor
    WHERE Id_Proveedor = @Id_Proveedor;

    -- Eliminar registro en la tabla Persona si no está en uso en otras tablas
    IF NOT EXISTS (SELECT 1 FROM Empleado WHERE Id_Persona = (SELECT Id_Persona FROM Proveedor WHERE Id_Proveedor = @Id_Proveedor))
    BEGIN
        DELETE FROM Persona
        WHERE Id_Persona = (SELECT Id_Persona FROM Proveedor WHERE Id_Proveedor = @Id_Proveedor);
    END
END
GO
/****** Object:  StoredProcedure [dbo].[EliminarVenta]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[EliminarVenta]
(
    @Id_Venta INT
)
AS

    DELETE FROM [Venta]
    WHERE[Id_Venta] = @Id_Venta
GO
/****** Object:  StoredProcedure [dbo].[EliminarVentaProducto]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
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
/****** Object:  StoredProcedure [dbo].[EliminarVentaYProducto]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[EliminarVentaYProducto]
   @Id_Venta INT
AS
BEGIN
    -- Eliminar las entradas en Venta_Producto que tengan el mismo Id_Venta
    DELETE FROM Venta_Producto
    WHERE Id_Venta = @Id_Venta;

    -- Eliminar la venta correspondiente al Id_Venta
    DELETE FROM Venta
    WHERE Id_Venta = @Id_Venta;
END
GO
/****** Object:  StoredProcedure [dbo].[InsertarCategoria]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[InsertarCategoria]
(

    @Nombre_Categoria VARCHAR(50),
    @Descripcion VARCHAR(100)
   
)
AS
    INSERT INTO [Categoria] ([Nombre_Categoria],[Descripcion])
    VALUES (@Nombre_Categoria,@Descripcion)
GO
/****** Object:  StoredProcedure [dbo].[InsertarCliente]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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

    -- Insertar registro en la tabla Persona
    INSERT INTO Persona (Cedula, Nombre_1, Nombre_2, Apellido_1, Apellido_2, Numero_Celular, Gmail, Direccion)
    VALUES (@Cedula, @Nombre_1, @Nombre_2, @Apellido_1, @Apellido_2, @Numero_Celular, @Gmail, @Direccion);

    -- Obtener el Id_Persona generado
    DECLARE @Id_Persona INT = SCOPE_IDENTITY();

    -- Insertar registro en la tabla Cliente
    INSERT INTO Cliente (Id_Persona)
    VALUES (@Id_Persona);
END
GO
/****** Object:  StoredProcedure [dbo].[InsertarEmpleado]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
/****** Object:  StoredProcedure [dbo].[InsertarLaboratorio]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[InsertarLaboratorio]
(
    @Nombre VARCHAR(50)
)
AS
    INSERT INTO [Laboratorio] ([Nombre])
    VALUES (@Nombre)
GO
/****** Object:  StoredProcedure [dbo].[InsertarPresentacion]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[InsertarPresentacion]
(
    @Nombre_Presentacion VARCHAR(50),
    @Detalle VARCHAR(70)
)
AS
    INSERT INTO [Presentacion] ([Nombre_Presentacion],[Detalle])
    VALUES (@Nombre_Presentacion,@Detalle)
GO
/****** Object:  StoredProcedure [dbo].[InsertarProducto]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
    @Id_Laboratorio INT,
    @Id_Proveedor INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    INSERT INTO Producto (Nombre, Descripcion, Cantidad_Producto, Precio_Compra, Precio_Venta, Imagen_Producto, Fecha_Caducidad, Id_Categoria, Id_Presentacion, Id_Laboratorio)
    VALUES (@Nombre, @Descripcion, @Cantidad_Producto, @Precio_Compra, @Precio_Venta, @Imagen_Producto, @Fecha_Caducidad, @Id_Categoria, @Id_Presentacion, @Id_Laboratorio);

    SET @Id_Proveedor = SCOPE_IDENTITY(); -- Obtener el ID del proveedor insertado
END
GO
/****** Object:  StoredProcedure [dbo].[InsertarProductoConProveedor]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[InsertarProductoConProveedor]
   @NombreProducto varchar(50),
  @DescripcionProducto varchar(200),
  @CantidadProducto int,
  @PrecioCompra decimal(10,2),
  @PrecioVenta decimal(10,2),
  @ImagenProducto varbinary(max),
  @FechaCaducidad date,
  @IdCategoria int,
  @IdPresentacion int,
  @IdLaboratorio int,
  @IdProveedor int
AS
BEGIN
  SET NOCOUNT ON;

  DECLARE @IdProducto int;

  -- Insertar el producto en la tabla Producto
  INSERT INTO Producto (
    Nombre,
    Descripcion,
    Cantidad_Producto,
    Precio_Compra,
    Precio_Venta,
    Imagen_Producto,
    Fecha_Caducidad,
    Id_Categoria,
    Id_Presentacion,
    Id_Laboratorio
  )
  VALUES (
    @NombreProducto,
    @DescripcionProducto,
    @CantidadProducto,
    @PrecioCompra,
    @PrecioVenta,
    @ImagenProducto,
    @FechaCaducidad,
    @IdCategoria,
    @IdPresentacion,
    @IdLaboratorio
  );

  -- Obtener el ID del producto recién insertado
  SET @IdProducto = SCOPE_IDENTITY();

  -- Insertar la relación entre el producto y el proveedor en la tabla Producto_Proveedor
  INSERT INTO Producto_Proveedor (
    Id_Proveedor,
    Id_Producto
  )
  VALUES (
    @IdProveedor,
    @IdProducto
  );
  
  -- No se requiere la instrucción SELECT al final
  
END
GO
/****** Object:  StoredProcedure [dbo].[InsertarProveedor]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
/****** Object:  StoredProcedure [dbo].[MostrarCantidadStok]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[MostrarCantidadStok]
  @Busqueda NVARCHAR(100) = NULL
AS
BEGIN
    SET @Busqueda = RTRIM(@Busqueda);
    SET @Busqueda = ISNULL(@Busqueda, '');

    SELECT Cantidad_Producto FROM Producto
    WHERE 
        (@Busqueda = '' OR 
        CAST(Id_Producto AS NVARCHAR(10)) LIKE '%' + @Busqueda + '%' OR
        Nombre LIKE '%' + @Busqueda + '%' OR
        Descripcion LIKE '%' + @Busqueda + '%' OR
        CAST(Cantidad_Producto AS NVARCHAR(10)) LIKE '%' + @Busqueda + '%' OR
        CAST(Precio_Compra AS NVARCHAR(50)) LIKE '%' + @Busqueda + '%' OR
        CAST(Precio_Venta AS NVARCHAR(50)) LIKE '%' + @Busqueda + '%' OR
        CAST(Fecha_Caducidad AS NVARCHAR(10)) LIKE '%' + @Busqueda + '%' OR
        CAST(Id_Categoria AS NVARCHAR(10)) LIKE '%' + @Busqueda + '%' OR
        CAST(Id_Presentacion AS NVARCHAR(10)) LIKE '%' + @Busqueda + '%' OR
        CAST(Id_Laboratorio AS NVARCHAR(10)) LIKE '%' + @Busqueda + '%');
END;
GO
/****** Object:  StoredProcedure [dbo].[MostrarCompraYProducto]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[MostrarCompraYProducto]
AS
BEGIN
    SELECT 
        Compra.Id_Compra, 
        ISNULL(Producto.Nombre, 'N/A') AS Producto,
        Compra_Producto.Cantidad,
        Persona.Nombre_1 + ' ' + Persona.Apellido_1 AS Proveedor, 
        Compra.Fecha_Compra
    FROM 
        Compra 
    LEFT JOIN 
        Compra_Producto ON Compra.Id_Compra = Compra_Producto.Id_Compra
    LEFT JOIN 
        Producto ON Compra_Producto.Id_Producto = Producto.Id_Producto
    LEFT JOIN 
        Proveedor ON Compra.Id_Proveedor = Proveedor.Id_Proveedor
    LEFT JOIN 
        Persona ON Proveedor.Id_Persona = Persona.Id_Persona;
END
GO
/****** Object:  StoredProcedure [dbo].[MostrarDatosCombinados]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
/****** Object:  StoredProcedure [dbo].[MostrarProductoConProveedor]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
  CREATE PROCEDURE [dbo].[MostrarProductoConProveedor]
   @IdProveedor int
AS
BEGIN
  IF @IdProveedor = -1
  BEGIN
    SELECT
      p.Id_Producto AS ID,
      p.Nombre,
      p.Descripcion,
      p.Cantidad_Producto AS Cantidad,
      p.Precio_Compra,
      p.Precio_Venta,
      p.Imagen_Producto AS Imagen,
      p.Fecha_Caducidad,
      cat.Nombre_Categoria AS Categoria,
      pre.Nombre_Presentacion AS Presentacion,
      lab.Nombre AS Laboratorio,
      per.Nombre_1 AS Proveedor
    FROM
      Producto p
      INNER JOIN Producto_Proveedor pp ON p.Id_Producto = pp.Id_Producto
      INNER JOIN Proveedor pr ON pp.Id_Proveedor = pr.Id_Proveedor
      INNER JOIN Persona per ON pr.Id_Persona = per.Id_Persona
      INNER JOIN Categoria cat ON p.Id_Categoria = cat.Id_Categoria
      INNER JOIN Presentacion pre ON p.Id_Presentacion = pre.Id_Presentacion
      INNER JOIN Laboratorio lab ON p.Id_Laboratorio = lab.Id_Laboratorio
  END
  ELSE
  BEGIN
    SELECT
      p.Id_Producto AS ID,
      p.Nombre,
      p.Descripcion,
      p.Cantidad_Producto AS Cantidad,
      p.Precio_Compra,
      p.Precio_Venta,
      p.Imagen_Producto AS Imagen,
      p.Fecha_Caducidad,
      cat.Nombre_Categoria AS Categoria,
      pre.Nombre_Presentacion AS Presentacion,
      lab.Nombre AS Laboratorio,
      per.Nombre_1 AS Proveedor
    FROM
      Producto p
      INNER JOIN Producto_Proveedor pp ON p.Id_Producto = pp.Id_Producto
      INNER JOIN Proveedor pr ON pp.Id_Proveedor = pr.Id_Proveedor
      INNER JOIN Persona per ON pr.Id_Persona = per.Id_Persona
      INNER JOIN Categoria cat ON p.Id_Categoria = cat.Id_Categoria
      INNER JOIN Presentacion pre ON p.Id_Presentacion = pre.Id_Presentacion
      INNER JOIN Laboratorio lab ON p.Id_Laboratorio = lab.Id_Laboratorio
    WHERE
      pr.Id_Proveedor = @IdProveedor
  END
END
GO
/****** Object:  StoredProcedure [dbo].[MostrarProductos]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[MostrarProductos]
  @Busqueda NVARCHAR(100) = NULL
AS
BEGIN
  SET @Busqueda = ISNULL(RTRIM(@Busqueda), '');

  SELECT
    Id_Producto,
    Nombre,
    Descripcion,
    Precio_Venta,
    Id_Categoria,
    Id_Presentacion,
    Id_Laboratorio
  FROM
    Producto
  WHERE 
    (@Busqueda = '' OR 
    Nombre LIKE '%' + @Busqueda + '%' OR
    Descripcion LIKE '%' + @Busqueda + '%');
END;
GO
/****** Object:  StoredProcedure [dbo].[MostrarProductosCompra]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[MostrarProductosCompra]
  @Busqueda NVARCHAR(100) = NULL
AS
BEGIN
    SET @Busqueda = RTRIM(@Busqueda);
    SET @Busqueda = ISNULL(@Busqueda, '');

    SELECT Id_Producto, Nombre, Precio_Compra FROM Producto
    WHERE 
        (@Busqueda = '' OR 
        CAST(Id_Producto AS NVARCHAR(10)) LIKE '%' + @Busqueda + '%' OR
        Nombre LIKE '%' + @Busqueda + '%' OR
        Descripcion LIKE '%' + @Busqueda + '%' OR
        CAST(Cantidad_Producto AS NVARCHAR(10)) LIKE '%' + @Busqueda + '%' OR
        CAST(Precio_Compra AS NVARCHAR(50)) LIKE '%' + @Busqueda + '%' OR
        CAST(Precio_Venta AS NVARCHAR(50)) LIKE '%' + @Busqueda + '%' OR
        CAST(Fecha_Caducidad AS NVARCHAR(10)) LIKE '%' + @Busqueda + '%' OR
        CAST(Id_Categoria AS NVARCHAR(10)) LIKE '%' + @Busqueda + '%' OR
        CAST(Id_Presentacion AS NVARCHAR(10)) LIKE '%' + @Busqueda + '%' OR
        CAST(Id_Laboratorio AS NVARCHAR(10)) LIKE '%' + @Busqueda + '%');
END;
GO
/****** Object:  StoredProcedure [dbo].[MostrarProductosConProveedor]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[MostrarProductosConProveedor]
AS
BEGIN
    SELECT P.Id_Producto, P.Nombre, P.Descripcion, P.Cantidad_Producto, P.Precio_Compra, P.Precio_Venta, P.Imagen_Producto, P.Fecha_Caducidad, PE.Nombre_1 AS NombreProveedor
    FROM [dbo].[Producto] AS P
    INNER JOIN [dbo].[Producto_Proveedor] AS PP ON P.Id_Producto = PP.Id_Producto
    INNER JOIN [dbo].[Proveedor] AS PR ON PP.Id_Proveedor = PR.Id_Proveedor
    INNER JOIN [dbo].[Persona] AS PE ON PR.Id_Persona = PE.Id_Persona
END
GO
/****** Object:  StoredProcedure [dbo].[MostrarVentaYProducto]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
	CREATE PROCEDURE [dbo].[MostrarVentaYProducto]
AS
BEGIN
    SELECT
        V.Id_Venta,
        ISNULL(Prod.Nombre, 'N/A') AS Producto,
        VP.Cantidad,
        VP.Descuento,
        ISNULL(PCliente.Nombre_1, 'N/A') + ' ' + ISNULL(PCliente.Apellido_1, 'N/A') AS Cliente,
        ISNULL(PEmpleado.Nombre_1, 'N/A') + ' ' + ISNULL(PEmpleado.Apellido_1, 'N/A') AS Empleado,
        V.Fecha_Hora,
        (VP.Cantidad * Prod.Precio_Venta) AS Total,
        CASE WHEN VP.Id_Venta_Producto = MAX(VP.Id_Venta_Producto) OVER (PARTITION BY V.Id_Venta) THEN SUM(VP.Cantidad * Prod.Precio_Venta * (1 - COALESCE(VP.Descuento, 0) / 100)) OVER (PARTITION BY V.Id_Venta) ELSE NULL END AS TotalGeneral
    FROM
        Venta AS V
    LEFT JOIN
        Venta_Producto AS VP ON V.Id_Venta = VP.Id_Venta
    LEFT JOIN
        Producto AS Prod ON VP.Id_Producto = Prod.Id_Producto
    LEFT JOIN
        Cliente AS C ON V.Id_Cliente = C.Id_Cliente
    LEFT JOIN
        Persona AS PCliente ON C.Id_Persona = PCliente.Id_Persona
    LEFT JOIN
        Empleado AS E ON V.Id_Empleado = E.Id_Empleado
    LEFT JOIN
        Persona AS PEmpleado ON E.Id_Persona = PEmpleado.Id_Persona
    ORDER BY
        V.Id_Venta, VP.Id_Venta_Producto;
END





GO
/****** Object:  StoredProcedure [dbo].[ObtenerDatosCompra]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ObtenerDatosCompra]
AS
BEGIN
    SELECT
        V.Id_Compra,
        ISNULL(Prod.Nombre, 'N/A') AS Producto,
        VP.Cantidad,
        ISNULL(PProveedor.Nombre_1, 'N/A') + ' ' + ISNULL(PProveedor.Apellido_1, 'N/A') AS Proveedor,
        V.Fecha_Compra,
        (VP.Cantidad * Prod.Precio_Compra) AS Total,
        CASE
            WHEN VP.Id_Compra_Producto = MAX(VP.Id_Compra_Producto) OVER (PARTITION BY V.Id_Compra) THEN SUM(VP.Cantidad * Prod.Precio_Compra) OVER (PARTITION BY V.Id_Compra)
            ELSE NULL
        END AS TotalGeneral
    FROM
        Compra AS V
    LEFT JOIN
        Compra_Producto AS VP ON V.Id_Compra = VP.Id_Compra
    LEFT JOIN
        Producto AS Prod ON VP.Id_Producto = Prod.Id_Producto
    LEFT JOIN
        Proveedor AS P ON V.Id_Proveedor = P.Id_Proveedor
    LEFT JOIN
        Persona AS PProveedor ON P.Id_Persona = PProveedor.Id_Persona
    ORDER BY
        V.Id_Compra, VP.Id_Compra_Producto;
END
GO
/****** Object:  StoredProcedure [dbo].[ObtenerEmpleados]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ObtenerEmpleados]
AS
BEGIN
    SET NOCOUNT ON;

    SELECT E.Id_Empleado, RTRIM(P.Nombre_1) + ' ' + RTRIM(P.Apellido_1) AS NombreCompleto
    FROM Empleado E
    INNER JOIN Persona P ON E.Id_Persona = P.Id_Persona;
END
GO
/****** Object:  StoredProcedure [dbo].[ObtenerNombreApellidoCliente]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ObtenerNombreApellidoCliente]
AS
BEGIN
    SET NOCOUNT ON;

    SELECT C.Id_Cliente, RTRIM(P.Nombre_1) + ' ' + RTRIM(P.Apellido_1) AS NombreCompleto
    FROM Cliente C
    INNER JOIN Persona P ON C.Id_Persona = P.Id_Persona;
END
GO
/****** Object:  StoredProcedure [dbo].[ObtenerProductosYProveedorJunto]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ObtenerProductosYProveedorJunto]
AS
BEGIN
    SELECT 
        P.Id_Producto,
        P.Nombre,
        P.Descripcion,
        P.Cantidad_Producto,
        P.Precio_Compra,
        P.Precio_Venta,
        P.Imagen_Producto,
        P.Fecha_Caducidad,
        C.Nombre_Categoria,
        Pres.Nombre_Presentacion,
        L.Nombre AS Nombre_Laboratorio,
        Prv.Nombre_1 + ' ' + Prv.Apellido_1 AS Proveedor
    FROM 
        dbo.Producto AS P
    INNER JOIN 
        dbo.Producto_Proveedor AS PP ON P.Id_Producto = PP.Id_Producto
    INNER JOIN 
        dbo.Proveedor AS Pr ON PP.Id_Proveedor = Pr.Id_Proveedor
    INNER JOIN 
        dbo.Persona AS Prv ON Pr.Id_Persona = Prv.Id_Persona
    INNER JOIN 
        dbo.Laboratorio AS L ON P.Id_Laboratorio = L.Id_Laboratorio
    INNER JOIN 
        dbo.Categoria AS C ON P.Id_Categoria = C.Id_Categoria
    INNER JOIN 
        dbo.Presentacion AS Pres ON P.Id_Presentacion = Pres.Id_Presentacion;
END
GO
/****** Object:  StoredProcedure [dbo].[ObtenerProveedor]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ObtenerProveedor]
AS
BEGIN
    SET NOCOUNT ON;

    SELECT E.Id_Proveedor, RTRIM(P.Nombre_1) + ' ' + RTRIM(P.Apellido_1) AS NombreCompleto
    FROM Proveedor E
    INNER JOIN Persona P ON E.Id_Persona = P.Id_Persona;
END
GO
/****** Object:  StoredProcedure [dbo].[ObtenerVentasConProductos]    Script Date: 4/8/2023 10:00:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ObtenerVentasConProductos]
AS
BEGIN
    SELECT 
        V.Id_Venta, 
        ISNULL(Prod.Nombre, 'N/A') AS Producto,
        VP.Cantidad, 
        VP.Descuento, 
        ISNULL(PCliente.Nombre_1, 'N/A') + ' ' + ISNULL(PCliente.Apellido_1, 'N/A') AS Cliente, 
        ISNULL(PEmpleado.Nombre_1, 'N/A') + ' ' + ISNULL(PEmpleado.Apellido_1, 'N/A') AS Empleado,
        V.Fecha_Hora
    FROM 
        Venta AS V
    JOIN 
        Venta_Producto AS VP ON V.Id_Venta = VP.Id_Venta
    JOIN 
        Producto AS Prod ON VP.Id_Producto = Prod.Id_Producto
    LEFT JOIN 
        Cliente AS C ON V.Id_Cliente = C.Id_Cliente
    LEFT JOIN 
        Persona AS PCliente ON C.Id_Persona = PCliente.Id_Persona
    LEFT JOIN 
        Empleado AS E ON V.Id_Empleado = E.Id_Empleado
    LEFT JOIN 
        Persona AS PEmpleado ON E.Id_Persona = PEmpleado.Id_Persona;
END
GO
USE [master]
GO
ALTER DATABASE [Farmacia_Rosales_DB] SET  READ_WRITE 
GO
