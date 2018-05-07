-- MySQL Script generated by MySQL Workbench
-- Sun May  6 20:20:40 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema inventario
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema inventario
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `inventario` DEFAULT CHARACTER SET utf8 ;
USE `inventario` ;

-- -----------------------------------------------------
-- Table `inventario`.`Perfil`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventario`.`Perfil` ;

CREATE TABLE IF NOT EXISTS `inventario`.`Perfil` (
  `idPefil` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `fechaCreacion` DATE NULL,
  `fechaModificacion` DATE NULL,
  `usuario` VARCHAR(45) NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPefil`),
  INDEX `fk_Pefil_Usuario1_idx` (`usuario` ASC),
  CONSTRAINT `fk_Pefil_Usuario1`
    FOREIGN KEY (`usuario`)
    REFERENCES `inventario`.`Usuario` (`usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventario`.`Persona`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventario`.`Persona` ;

CREATE TABLE IF NOT EXISTS `inventario`.`Persona` (
  `tipoIdentificacion` VARCHAR(45) NOT NULL,
  `identificacion` INT NOT NULL,
  `primerNombre` VARCHAR(45) NOT NULL,
  `cargo` VARCHAR(45) NOT NULL,
  `fechaCreacion` DATE NULL,
  `fechaModificacion` DATE NULL,
  `usuario` VARCHAR(45) NULL,
  `segundoNombre` VARCHAR(45) NULL,
  `primerApellido` VARCHAR(45) NOT NULL,
  `segundoApellido` VARCHAR(45) NULL,
  `correo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`tipoIdentificacion`, `identificacion`),
  INDEX `fk_Persona_Usuario1_idx` (`usuario` ASC),
  CONSTRAINT `fk_Persona_Usuario1`
    FOREIGN KEY (`usuario`)
    REFERENCES `inventario`.`Usuario` (`usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventario`.`Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventario`.`Usuario` ;

CREATE TABLE IF NOT EXISTS `inventario`.`Usuario` (
  `usuario` VARCHAR(45) NOT NULL,
  `contrasenia` VARCHAR(45) NOT NULL,
  `idPerfil` INT NOT NULL,
  `fechaCreacion` DATE NULL,
  `fechaModificacion` DATE NULL,
  `ultimoIngreso` DATE NULL,
  `estado` VARCHAR(45) NOT NULL,
  `numeroIngresos` INT NULL,
  `intentosFallidos` INT NULL,
  `tipoIdentificacion` VARCHAR(45) NULL,
  `identificacion` INT NULL,
  INDEX `fk_Usuario_Pefil1_idx` (`idPerfil` ASC),
  PRIMARY KEY (`usuario`),
  INDEX `fk_Usuario_Persona1_idx` (`tipoIdentificacion` ASC, `identificacion` ASC),
  CONSTRAINT `fk_Usuario_Pefil1`
    FOREIGN KEY (`idPerfil`)
    REFERENCES `inventario`.`Perfil` (`idPefil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuario_Persona1`
    FOREIGN KEY (`tipoIdentificacion` , `identificacion`)
    REFERENCES `inventario`.`Persona` (`tipoIdentificacion` , `identificacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventario`.`Categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventario`.`Categoria` ;

CREATE TABLE IF NOT EXISTS `inventario`.`Categoria` (
  `idCategoria` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `fechaCreacion` DATE NULL,
  `fechaModificacion` DATE NULL,
  `usuario` VARCHAR(45) NULL,
  PRIMARY KEY (`idCategoria`),
  INDEX `fk_Categoria_Usuario1_idx` (`usuario` ASC),
  CONSTRAINT `fk_Categoria_Usuario1`
    FOREIGN KEY (`usuario`)
    REFERENCES `inventario`.`Usuario` (`usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventario`.`TipoProducto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventario`.`TipoProducto` ;

CREATE TABLE IF NOT EXISTS `inventario`.`TipoProducto` (
  `idTipoProducto` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `fechaCreacion` DATE NULL,
  `fechaModificacion` DATE NULL,
  `usuario` VARCHAR(45) NULL,
  PRIMARY KEY (`idTipoProducto`),
  INDEX `fk_TipoProducto_Usuario1_idx` (`usuario` ASC),
  CONSTRAINT `fk_TipoProducto_Usuario1`
    FOREIGN KEY (`usuario`)
    REFERENCES `inventario`.`Usuario` (`usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventario`.`Tamanio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventario`.`Tamanio` ;

CREATE TABLE IF NOT EXISTS `inventario`.`Tamanio` (
  `idTamanio` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `fechaCreacion` DATE NULL,
  `fechaModificacion` DATE NULL,
  `usuario` VARCHAR(45) NULL,
  PRIMARY KEY (`idTamanio`),
  INDEX `fk_Tamanio_Usuario1_idx` (`usuario` ASC),
  CONSTRAINT `fk_Tamanio_Usuario1`
    FOREIGN KEY (`usuario`)
    REFERENCES `inventario`.`Usuario` (`usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventario`.`Marca`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventario`.`Marca` ;

CREATE TABLE IF NOT EXISTS `inventario`.`Marca` (
  `idMarca` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `fechaCreacion` DATE NULL,
  `fechaModificacion` DATE NULL,
  `usuario` VARCHAR(45) NULL,
  PRIMARY KEY (`idMarca`),
  INDEX `fk_Marca_Usuario1_idx` (`usuario` ASC),
  CONSTRAINT `fk_Marca_Usuario1`
    FOREIGN KEY (`usuario`)
    REFERENCES `inventario`.`Usuario` (`usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventario`.`Origen`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventario`.`Origen` ;

CREATE TABLE IF NOT EXISTS `inventario`.`Origen` (
  `idOrigen` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `fechaCreacion` DATE NULL,
  `fechaModificacion` DATE NULL,
  `usuario` VARCHAR(45) NULL,
  PRIMARY KEY (`idOrigen`),
  INDEX `fk_Origen_Usuario1_idx` (`usuario` ASC),
  CONSTRAINT `fk_Origen_Usuario1`
    FOREIGN KEY (`usuario`)
    REFERENCES `inventario`.`Usuario` (`usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventario`.`Producto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventario`.`Producto` ;

CREATE TABLE IF NOT EXISTS `inventario`.`Producto` (
  `idProducto` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `idCategoria` INT NOT NULL,
  `idTipo` INT NOT NULL,
  `precioCompra` VARCHAR(45) NOT NULL,
  `idTamanio` INT NOT NULL,
  `idMarca` INT NOT NULL,
  `genero` VARCHAR(45) NOT NULL,
  `fechaCreacion` DATE NULL,
  `fechaModificacion` DATE NULL,
  `usuario` VARCHAR(45) NULL,
  `idOrigen` INT NOT NULL,
  `cantidad` VARCHAR(45) NOT NULL,
  `precioVenta` BIGINT(10) NOT NULL,
  PRIMARY KEY (`idProducto`),
  INDEX `idCategoria_idx` (`idCategoria` ASC),
  INDEX `idTipo_idx` (`idTipo` ASC),
  INDEX `fk_Producto_Tamaño1_idx` (`idTamanio` ASC),
  INDEX `fk_Producto_Marca1_idx` (`idMarca` ASC),
  INDEX `fk_Producto_Usuario1_idx` (`usuario` ASC),
  INDEX `fk_Producto_Origen1_idx` (`idOrigen` ASC),
  CONSTRAINT `idCategoria`
    FOREIGN KEY (`idCategoria`)
    REFERENCES `inventario`.`Categoria` (`idCategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idTipo`
    FOREIGN KEY (`idTipo`)
    REFERENCES `inventario`.`TipoProducto` (`idTipoProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Producto_Tamaño1`
    FOREIGN KEY (`idTamanio`)
    REFERENCES `inventario`.`Tamanio` (`idTamanio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Producto_Marca1`
    FOREIGN KEY (`idMarca`)
    REFERENCES `inventario`.`Marca` (`idMarca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Producto_Usuario1`
    FOREIGN KEY (`usuario`)
    REFERENCES `inventario`.`Usuario` (`usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Producto_Origen1`
    FOREIGN KEY (`idOrigen`)
    REFERENCES `inventario`.`Origen` (`idOrigen`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventario`.`Inventario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventario`.`Inventario` ;

CREATE TABLE IF NOT EXISTS `inventario`.`Inventario` (
  `idInventario` INT NOT NULL AUTO_INCREMENT,
  `fechaCreacion` DATE NULL,
  `fechaModificacion` DATE NULL,
  `usuario` VARCHAR(45) NULL,
  `tipoInventario` VARCHAR(45) NOT NULL,
  `fechaInventario` DATE NOT NULL,
  PRIMARY KEY (`idInventario`),
  INDEX `fk_Inventario_Usuario1_idx` (`usuario` ASC),
  CONSTRAINT `fk_Inventario_Usuario1`
    FOREIGN KEY (`usuario`)
    REFERENCES `inventario`.`Usuario` (`usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventario`.`Bitacora`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventario`.`Bitacora` ;

CREATE TABLE IF NOT EXISTS `inventario`.`Bitacora` (
  `idBitacora` INT NOT NULL AUTO_INCREMENT,
  `accion` VARCHAR(45) NOT NULL,
  `tabla` VARCHAR(45) NOT NULL,
  `usuario` VARCHAR(45) NOT NULL,
  `fechaCrecion` DATE NULL,
  `fechaModifiacion` DATE NULL,
  PRIMARY KEY (`idBitacora`),
  INDEX `fk_Bitacora_Usuario1_idx` (`usuario` ASC),
  CONSTRAINT `fk_Bitacora_Usuario1`
    FOREIGN KEY (`usuario`)
    REFERENCES `inventario`.`Usuario` (`usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventario`.`Entrada`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventario`.`Entrada` ;

CREATE TABLE IF NOT EXISTS `inventario`.`Entrada` (
  `idEntrada` INT NOT NULL AUTO_INCREMENT,
  `cantidad` INT NOT NULL,
  `precioCompra` BIGINT(10) NOT NULL,
  `idProducto` INT NOT NULL,
  PRIMARY KEY (`idEntrada`),
  INDEX `fk_Entrada_Producto1_idx` (`idProducto` ASC),
  CONSTRAINT `fk_Entrada_Producto1`
    FOREIGN KEY (`idProducto`)
    REFERENCES `inventario`.`Producto` (`idProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventario`.`Salida`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventario`.`Salida` ;

CREATE TABLE IF NOT EXISTS `inventario`.`Salida` (
  `idSalida` INT NOT NULL AUTO_INCREMENT,
  `cantidad` INT NOT NULL,
  `Producto_idProducto` INT NOT NULL,
  PRIMARY KEY (`idSalida`),
  INDEX `fk_Salida_Producto1_idx` (`Producto_idProducto` ASC),
  CONSTRAINT `fk_Salida_Producto1`
    FOREIGN KEY (`Producto_idProducto`)
    REFERENCES `inventario`.`Producto` (`idProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventario`.`inventarioPeriodico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventario`.`inventarioPeriodico` ;

CREATE TABLE IF NOT EXISTS `inventario`.`inventarioPeriodico` (
  `idInventarioPeriodico` INT NOT NULL AUTO_INCREMENT,
  `cantidad` INT NOT NULL,
  `idInventario` INT NOT NULL,
  `idProducto` INT NOT NULL,
  `fechaCreacion` DATE NULL,
  `fechaModificacion` DATE NULL,
  PRIMARY KEY (`idInventarioPeriodico`),
  INDEX `fk_inventarioPeriodico_Inventario2_idx` (`idInventario` ASC),
  INDEX `fk_inventarioPeriodico_Producto2_idx` (`idProducto` ASC),
  CONSTRAINT `fk_inventarioPeriodico_Inventario2`
    FOREIGN KEY (`idInventario`)
    REFERENCES `inventario`.`Inventario` (`idInventario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventarioPeriodico_Producto2`
    FOREIGN KEY (`idProducto`)
    REFERENCES `inventario`.`Producto` (`idProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
