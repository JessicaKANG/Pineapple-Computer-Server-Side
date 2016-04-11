-- MySQL Script generated by MySQL Workbench
-- Mon Apr 11 04:03:34 2016
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`tb_client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tb_client` (
  `id_client` VARCHAR(45) NOT NULL,
  `name_client` VARCHAR(45) NOT NULL,
  `psd_client` VARCHAR(45) NOT NULL,
  `email_client` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_client`),
  UNIQUE INDEX `tb_clientcol_UNIQUE` (`name_client` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tb_computer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tb_computer` (
  `id_computer` VARCHAR(45) NOT NULL,
  `type_computer` VARCHAR(45) NOT NULL,
  `price_computer` FLOAT NOT NULL,
  `picture_computer` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_computer`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tb_component`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tb_component` (
  `id_component` VARCHAR(45) NOT NULL,
  `price_component` FLOAT NOT NULL,
  `type_component` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_component`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tb_stock`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tb_stock` (
  `id_computer` VARCHAR(45) NULL,
  `id_component` VARCHAR(45) NULL,
  `number_stock` INT NOT NULL,
  PRIMARY KEY (`id_computer`, `id_component`),
  INDEX `fk_tb_stock_tb_computer1_idx` (`id_computer` ASC),
  INDEX `fk_tb_stock_tb_component1_idx` (`id_component` ASC),
  CONSTRAINT `fk_tb_stock_tb_computer1`
    FOREIGN KEY (`id_computer`)
    REFERENCES `mydb`.`tb_computer` (`id_computer`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_stock_tb_component1`
    FOREIGN KEY (`id_component`)
    REFERENCES `mydb`.`tb_component` (`id_component`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tb_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tb_order` (
  `id_order` INT NOT NULL,
  `price_order` FLOAT NOT NULL,
  `date_order` DATETIME NOT NULL,
  `id_client` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_order`),
  INDEX `fk_tb_order_tb_client1_idx` (`id_client` ASC),
  CONSTRAINT `fk_tb_order_tb_client1`
    FOREIGN KEY (`id_client`)
    REFERENCES `mydb`.`tb_client` (`id_client`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tb_order_has_computer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tb_order_has_computer` (
  `id_order` INT NOT NULL,
  `id_computer` VARCHAR(45) NOT NULL,
  `num_computer` INT NOT NULL,
  PRIMARY KEY (`id_order`, `id_computer`),
  INDEX `fk_tb_order_has_tb_computer_tb_computer1_idx` (`id_computer` ASC),
  INDEX `fk_tb_order_has_tb_computer_tb_order1_idx` (`id_order` ASC),
  CONSTRAINT `fk_tb_order_has_tb_computer_tb_order1`
    FOREIGN KEY (`id_order`)
    REFERENCES `mydb`.`tb_order` (`id_order`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_order_has_tb_computer_tb_computer1`
    FOREIGN KEY (`id_computer`)
    REFERENCES `mydb`.`tb_computer` (`id_computer`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tb_computer_has_component`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tb_computer_has_component` (
  `id_computer` VARCHAR(45) NOT NULL,
  `id_component` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_computer`, `id_component`),
  INDEX `fk_tb_computer_has_tb_component_tb_component1_idx` (`id_component` ASC),
  INDEX `fk_tb_computer_has_tb_component_tb_computer1_idx` (`id_computer` ASC),
  CONSTRAINT `fk_tb_computer_has_tb_component_tb_computer1`
    FOREIGN KEY (`id_computer`)
    REFERENCES `mydb`.`tb_computer` (`id_computer`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_computer_has_tb_component_tb_component1`
    FOREIGN KEY (`id_component`)
    REFERENCES `mydb`.`tb_component` (`id_component`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tb_bag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tb_bag` (
  `id_bag` INT NOT NULL,
  `price_bag` FLOAT NOT NULL,
  `id_client` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_bag`),
  INDEX `fk_tb_bag_tb_client1_idx` (`id_client` ASC),
  CONSTRAINT `fk_tb_bag_tb_client1`
    FOREIGN KEY (`id_client`)
    REFERENCES `mydb`.`tb_client` (`id_client`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tb_bag_has_computer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tb_bag_has_computer` (
  `id_bag` INT NOT NULL,
  `id_computer` VARCHAR(45) NOT NULL,
  `num_computer` INT NOT NULL,
  PRIMARY KEY (`id_bag`, `id_computer`),
  INDEX `fk_tb_bag_has_tb_computer_tb_computer1_idx` (`id_computer` ASC),
  INDEX `fk_tb_bag_has_computer_tb_bag1_idx` (`id_bag` ASC),
  CONSTRAINT `fk_tb_bag_has_tb_computer_tb_computer1`
    FOREIGN KEY (`id_computer`)
    REFERENCES `mydb`.`tb_computer` (`id_computer`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_bag_has_computer_tb_bag1`
    FOREIGN KEY (`id_bag`)
    REFERENCES `mydb`.`tb_bag` (`id_bag`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tb_define_computer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tb_define_computer` (
  `id_define_computer` VARCHAR(45) NOT NULL,
  `id_computer` VARCHAR(45) NOT NULL,
  `price_define_computer` FLOAT NOT NULL,
  PRIMARY KEY (`id_define_computer`),
  INDEX `fk_tb_define_computer_tb_computer1_idx` (`id_computer` ASC),
  CONSTRAINT `fk_tb_define_computer_tb_computer1`
    FOREIGN KEY (`id_computer`)
    REFERENCES `mydb`.`tb_computer` (`id_computer`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tb_define_computer_has_component`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tb_define_computer_has_component` (
  `id_component` VARCHAR(45) NOT NULL,
  `id_define_computer` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_component`, `id_define_computer`),
  INDEX `fk_tb_define_computer_has_tb_component_tb_component1_idx` (`id_component` ASC),
  INDEX `fk_tb_define_computer_has_component_tb_define_computer1_idx` (`id_define_computer` ASC),
  CONSTRAINT `fk_tb_define_computer_has_tb_component_tb_component1`
    FOREIGN KEY (`id_component`)
    REFERENCES `mydb`.`tb_component` (`id_component`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_define_computer_has_component_tb_define_computer1`
    FOREIGN KEY (`id_define_computer`)
    REFERENCES `mydb`.`tb_define_computer` (`id_define_computer`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tb_order_has_define_computer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tb_order_has_define_computer` (
  `id_order` INT NOT NULL,
  `id_define_computer` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_order`, `id_define_computer`),
  INDEX `fk_tb_order_has_tb_define_computer_tb_define_computer1_idx` (`id_define_computer` ASC),
  INDEX `fk_tb_order_has_tb_define_computer_tb_order1_idx` (`id_order` ASC),
  CONSTRAINT `fk_tb_order_has_tb_define_computer_tb_order1`
    FOREIGN KEY (`id_order`)
    REFERENCES `mydb`.`tb_order` (`id_order`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_order_has_tb_define_computer_tb_define_computer1`
    FOREIGN KEY (`id_define_computer`)
    REFERENCES `mydb`.`tb_define_computer` (`id_define_computer`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tb_bag_has_define_computer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tb_bag_has_define_computer` (
  `id_bag` INT NOT NULL,
  `id_define_computer` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_bag`, `id_define_computer`),
  INDEX `fk_tb_bag_has_tb_define_computer_tb_bag1_idx` (`id_bag` ASC),
  INDEX `fk_tb_bag_has_tb_define_computer_tb_define_computer1_idx` (`id_define_computer` ASC),
  CONSTRAINT `fk_tb_bag_has_tb_define_computer_tb_bag1`
    FOREIGN KEY (`id_bag`)
    REFERENCES `mydb`.`tb_bag` (`id_bag`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_bag_has_tb_define_computer_tb_define_computer1`
    FOREIGN KEY (`id_define_computer`)
    REFERENCES `mydb`.`tb_define_computer` (`id_define_computer`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;