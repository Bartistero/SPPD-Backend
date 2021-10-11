-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema SPPD
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `SPPD` ;

-- -----------------------------------------------------
-- Schema SPPD
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SPPD` DEFAULT CHARACTER SET utf8 ;
USE `SPPD` ;

-- -----------------------------------------------------
-- Table `SPPD`.`Permissions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SPPD`.`Permissions` ;

CREATE TABLE IF NOT EXISTS `SPPD`.`Permissions` (
  `idPermissions` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idPermissions`),
  UNIQUE INDEX `nazwa_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SPPD`.`AccountStatus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SPPD`.`AccountStatus` ;

CREATE TABLE IF NOT EXISTS `SPPD`.`AccountStatus` (
  `idAccountStatus` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`idAccountStatus`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SPPD`.`County`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SPPD`.`County` ;

CREATE TABLE IF NOT EXISTS `SPPD`.`County` (
  `idCounty` INT NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCounty`),
  UNIQUE INDEX `country_UNIQUE` (`country` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SPPD`.`CityName`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SPPD`.`CityName` ;

CREATE TABLE IF NOT EXISTS `SPPD`.`CityName` (
  `idCityName` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCityName`),
  UNIQUE INDEX `Name_UNIQUE` (`Name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SPPD`.`ZipCode`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SPPD`.`ZipCode` ;

CREATE TABLE IF NOT EXISTS `SPPD`.`ZipCode` (
  `idZipCode` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`idZipCode`),
  UNIQUE INDEX `ZipCodecol_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SPPD`.`Person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SPPD`.`Person` ;

CREATE TABLE IF NOT EXISTS `SPPD`.`Person` (
  `idPerson` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `middleName` VARCHAR(30) NULL,
  `surname` VARCHAR(35) NOT NULL,
  `login` VARCHAR(30) NOT NULL,
  `email` VARCHAR(30) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `albumNumber` VARCHAR(7) NULL,
  `pesel(11)` VARCHAR(11) NOT NULL,
  `phone` VARCHAR(12) NULL,
  `idPermissions` INT NOT NULL,
  `idAccountStatus` INT NOT NULL,
  `idCountry` INT NOT NULL,
  `idCityName` INT NOT NULL,
  `idZipCode` INT NOT NULL,
  `houseNumber` VARCHAR(45) NOT NULL,
  `flatNumber` VARCHAR(45) NULL,
  PRIMARY KEY (`idPerson`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `nrAlbumu_UNIQUE` (`albumNumber` ASC) VISIBLE,
  UNIQUE INDEX `pesel(11)_UNIQUE` (`pesel(11)` ASC) VISIBLE,
  INDEX `fk_Osoba_Uprawnienia_idx` (`idPermissions` ASC) VISIBLE,
  INDEX `fk_Osoba_accountStatus1_idx` (`idAccountStatus` ASC) VISIBLE,
  INDEX `fk_Osoba_County1_idx` (`idCountry` ASC) VISIBLE,
  INDEX `fk_Osoba_CityName1_idx` (`idCityName` ASC) VISIBLE,
  INDEX `fk_Osoba_ZipCode1_idx` (`idZipCode` ASC) VISIBLE,
  CONSTRAINT `fk_Osoba_Uprawnienia`
    FOREIGN KEY (`idPermissions`)
    REFERENCES `SPPD`.`Permissions` (`idPermissions`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Osoba_accountStatus1`
    FOREIGN KEY (`idAccountStatus`)
    REFERENCES `SPPD`.`AccountStatus` (`idAccountStatus`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Osoba_County1`
    FOREIGN KEY (`idCountry`)
    REFERENCES `SPPD`.`County` (`idCounty`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Osoba_CityName1`
    FOREIGN KEY (`idCityName`)
    REFERENCES `SPPD`.`CityName` (`idCityName`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Osoba_ZipCode1`
    FOREIGN KEY (`idZipCode`)
    REFERENCES `SPPD`.`ZipCode` (`idZipCode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SPPD`.`TypeOfThesis`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SPPD`.`TypeOfThesis` ;

CREATE TABLE IF NOT EXISTS `SPPD`.`TypeOfThesis` (
  `idTypeThesis` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(50) NULL,
  PRIMARY KEY (`idTypeThesis`),
  UNIQUE INDEX `nazwa_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SPPD`.`Year`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SPPD`.`Year` ;

CREATE TABLE IF NOT EXISTS `SPPD`.`Year` (
  `idYear` INT NOT NULL AUTO_INCREMENT,
  `year` VARCHAR(4) NOT NULL,
  PRIMARY KEY (`idYear`),
  UNIQUE INDEX `rokRozpoczecia_UNIQUE` (`year` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SPPD`.`ThesisStatus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SPPD`.`ThesisStatus` ;

CREATE TABLE IF NOT EXISTS `SPPD`.`ThesisStatus` (
  `idThesisStatus` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(45) NOT NULL,
  `description` VARCHAR(200) NULL,
  PRIMARY KEY (`idThesisStatus`),
  UNIQUE INDEX `status_UNIQUE` (`status` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SPPD`.`Faculty`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SPPD`.`Faculty` ;

CREATE TABLE IF NOT EXISTS `SPPD`.`Faculty` (
  `idDepartment` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `dean` VARCHAR(45) NOT NULL,
  `headDeansOffice` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idDepartment`),
  UNIQUE INDEX `Nazwa_UNIQUE` (`name` ASC) VISIBLE,
  UNIQUE INDEX `dziekan_UNIQUE` (`dean` ASC) VISIBLE,
  UNIQUE INDEX `kierownikDziekanatu_UNIQUE` (`headDeansOffice` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SPPD`.`Depratment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SPPD`.`Depratment` ;

CREATE TABLE IF NOT EXISTS `SPPD`.`Depratment` (
  `idCathedral` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `kierownik` VARCHAR(70) NOT NULL,
  `idDepartment` INT NOT NULL,
  PRIMARY KEY (`idCathedral`),
  UNIQUE INDEX `Nazwa_UNIQUE` (`name` ASC) VISIBLE,
  UNIQUE INDEX `kierownik_UNIQUE` (`kierownik` ASC) VISIBLE,
  INDEX `fk_Katedra_Wydzial1_idx` (`idDepartment` ASC) VISIBLE,
  CONSTRAINT `fk_Katedra_Wydzial1`
    FOREIGN KEY (`idDepartment`)
    REFERENCES `SPPD`.`Faculty` (`idDepartment`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SPPD`.`ThesisTitle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SPPD`.`ThesisTitle` ;

CREATE TABLE IF NOT EXISTS `SPPD`.`ThesisTitle` (
  `idThesisTitle` INT NOT NULL AUTO_INCREMENT,
  `thesisName` VARCHAR(45) NOT NULL,
  `Description` VARCHAR(50) NULL,
  `idTypeThesis` INT NOT NULL,
  `idYear` INT NOT NULL,
  `idThesisStatus` INT NOT NULL,
  `Depratment_idCathedral` INT NOT NULL,
  PRIMARY KEY (`idThesisTitle`),
  INDEX `fk_tematPracy_RodzajPracy1_idx` (`idTypeThesis` ASC) VISIBLE,
  INDEX `fk_tematPracy_Rocznik1_idx` (`idYear` ASC) VISIBLE,
  INDEX `fk_tematPracy_StatusPracy1_idx` (`idThesisStatus` ASC) VISIBLE,
  INDEX `fk_ThesisTitle_Depratment1_idx` (`Depratment_idCathedral` ASC) VISIBLE,
  CONSTRAINT `fk_tematPracy_RodzajPracy1`
    FOREIGN KEY (`idTypeThesis`)
    REFERENCES `SPPD`.`TypeOfThesis` (`idTypeThesis`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tematPracy_Rocznik1`
    FOREIGN KEY (`idYear`)
    REFERENCES `SPPD`.`Year` (`idYear`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tematPracy_StatusPracy1`
    FOREIGN KEY (`idThesisStatus`)
    REFERENCES `SPPD`.`ThesisStatus` (`idThesisStatus`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ThesisTitle_Depratment1`
    FOREIGN KEY (`Depratment_idCathedral`)
    REFERENCES `SPPD`.`Depratment` (`idCathedral`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SPPD`.`Person_has_ThesisTitle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SPPD`.`Person_has_ThesisTitle` ;

CREATE TABLE IF NOT EXISTS `SPPD`.`Person_has_ThesisTitle` (
  `Person_idPerson` INT NOT NULL,
  `ThesisTitle_idThesisTitle` INT NOT NULL,
  `ID` VARCHAR(45) NOT NULL,
  INDEX `fk_Person_has_ThesisTitle_ThesisTitle1_idx` (`ThesisTitle_idThesisTitle` ASC) VISIBLE,
  INDEX `fk_Person_has_ThesisTitle_Person1_idx` (`Person_idPerson` ASC) VISIBLE,
  PRIMARY KEY (`ID`),
  CONSTRAINT `fk_Person_has_ThesisTitle_Person1`
    FOREIGN KEY (`Person_idPerson`)
    REFERENCES `SPPD`.`Person` (`idPerson`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Person_has_ThesisTitle_ThesisTitle1`
    FOREIGN KEY (`ThesisTitle_idThesisTitle`)
    REFERENCES `SPPD`.`ThesisTitle` (`idThesisTitle`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
