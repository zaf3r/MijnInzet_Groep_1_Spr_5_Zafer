-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
-- -----------------------------------------------------
-- Schema MijnInzet
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `MijnInzet` ;

-- -----------------------------------------------------
-- Schema MijnInzet
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `MijnInzet` DEFAULT CHARACTER SET utf8 ;
USE `MijnInzet` ;

-- ------------------------------------------------------
-- Aanmaken database-user met ALLE rechten
-- ------------------------------------------------------
CREATE USER if not exists 'Umaster'@'localhost' IDENTIFIED BY 'pwdUmaster';
GRANT ALL PRIVILEGES ON * . * TO 'Umaster'@'localhost';
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `MijnInzet`.`Gebruiker`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MijnInzet`.`Gebruiker` (
  `idGebruiker` INT NOT NULL AUTO_INCREMENT,
  `gebruikersnaam` VARCHAR(45) NOT NULL,
  `wachtwoord` CHAR(60) NOT NULL,
  `actief` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idGebruiker`))
ENGINE = InnoDB;

INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`) VALUES ('1', 'TheBoss', '$2a$10$txE89lVWqzofuQpGc7LFcuC.y6BbsrtSeXUcUnOGU/Obw5cQsFNsS','1');
INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`) VALUES ('2', 'TheTeacher', '$2a$10$szbWYgtrnVnTZV4FxwcS6.XfB7flFK3ElwMg70GDXzHvJn/q7GPSu','1');
INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`) VALUES ('3', 'capo di tutti capi', '$2a$10$Gv9vyEofFsczKjLB73UbhevF9.Twq4DXzL1XvquYhFgxU0gPmd3fe','1');
INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`) VALUES ('4', 'ForeverDisabled', '$2a$10$bkyCwHFpeiSt6TcaDCEXS.tOXrip8FmM0Hlrxy8ZjzRjdAb4yEYx2','0');


-- -----------------------------------------------------
-- Table `MijnInzet`.`Docent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MijnInzet`.`Docent` (
  `idGebruiker` INT NOT NULL,
  `Contracturen` DOUBLE NULL,
  PRIMARY KEY (`idGebruiker`),
  CONSTRAINT `fk_Docent_Gebruiker`
    FOREIGN KEY (`idGebruiker`)
    REFERENCES `MijnInzet`.`Gebruiker` (`idGebruiker`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MijnInzet`.`Rollen`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MijnInzet`.`Rollen` (
  `Rol_id` INT NOT NULL,
  `Rol` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Rol_id`))
ENGINE = InnoDB;

INSERT INTO `rollen`(`Rol_id`,`Rol`) VALUES ('1','Docent');
INSERT INTO `rollen`(`Rol_id`,`Rol`) VALUES ('2','Administrateur');
INSERT INTO `rollen`(`Rol_id`,`Rol`) VALUES ('3','Systeem');
INSERT INTO `rollen`(`Rol_id`,`Rol`) VALUES ('4','Manager');
INSERT INTO `rollen`(`Rol_id`,`Rol`) VALUES ('5','Roosteraar');


-- -----------------------------------------------------
-- Table `MijnInzet`.`Beschikbaarheid`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MijnInzet`.`Beschikbaarheid` (
  `Semester` DATE NOT NULL,
  `Jaar` TIME NOT NULL,
  `Blok` INT NOT NULL,
  `idGebruiker` INT NOT NULL,
  INDEX `fk_Beschikbaarheid_Docent1_idx` (`idGebruiker` ASC) VISIBLE,
  PRIMARY KEY (`idGebruiker`, `Semester`, `Jaar`, `Blok`),
  CONSTRAINT `fk_Beschikbaarheid_Docent1`
    FOREIGN KEY (`idGebruiker`)
    REFERENCES `MijnInzet`.`Docent` (`idGebruiker`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MijnInzet`.`Taak`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MijnInzet`.`Taak` (
  `Titel` VARCHAR(45) NOT NULL,
  `Duur(uren)` DOUBLE NOT NULL,
  `Geldigheidsduur` INT NOT NULL,
  `idGebruiker` INT NOT NULL,
  `Vak` VARCHAR(45) NULL,
  `Onderwijstaak ja/nee` TINYINT NOT NULL,
  PRIMARY KEY (`Titel`, `idGebruiker`),
  INDEX `fk_Taak_Docent1_idx` (`idGebruiker` ASC) VISIBLE,
  CONSTRAINT `fk_Taak_Docent1`
    FOREIGN KEY (`idGebruiker`)
    REFERENCES `MijnInzet`.`Docent` (`idGebruiker`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MijnInzet`.`Vak`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MijnInzet`.`Vak` (
  `Vakcode` CHAR(25) NOT NULL,
  `Vaknaam` VARCHAR(45) NOT NULL,
  `Kennisgebied` VARCHAR(45) NOT NULL,
  `Opleiding` VARCHAR(45) NOT NULL,
  `Activiteitstype` VARCHAR(45) NOT NULL,
  `Titel` VARCHAR(45) NOT NULL,
  `idGebruiker` INT NOT NULL,
  `Titel1` VARCHAR(45) NOT NULL,
  `idGebruiker1` INT NOT NULL,
  PRIMARY KEY (`Vakcode`),
  INDEX `fk_Vak_Taak1_idx` (`Titel1` ASC, `idGebruiker1` ASC) VISIBLE,
  CONSTRAINT `fk_Vak_Taak1`
    FOREIGN KEY (`Titel1` , `idGebruiker1`)
    REFERENCES `MijnInzet`.`Taak` (`Titel` , `idGebruiker`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MijnInzet`.`Vacature`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MijnInzet`.`Vacature` (
  `Titel` VARCHAR(45) NOT NULL,
  `idGebruiker` INT NOT NULL,
  `Ingangsdatum` DATETIME NOT NULL,
  `Einddatum` DATETIME NOT NULL,
  `Volgnummer` INT NOT NULL,
  `idGebruiker1` INT NOT NULL,
  PRIMARY KEY (`Volgnummer`),
  INDEX `fk_Vacature_Taak1_idx` (`idGebruiker1` ASC) VISIBLE,
  CONSTRAINT `fk_Vacature_Taak1`
    FOREIGN KEY (`idGebruiker1`)
    REFERENCES `MijnInzet`.`Taak` (`idGebruiker`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MijnInzet`.`Dagdelen`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MijnInzet`.`Dagdelen` (
  `Ochtend` INT NOT NULL,
  `Middag` INT NOT NULL,
  `Dag` DATE NOT NULL,
  `idnummer` INT NOT NULL,
  `UrenDagDeel` INT NOT NULL DEFAULT 4,
  PRIMARY KEY (`idnummer`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MijnInzet`.`Dagdelen_has_Beschikbaarheid`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MijnInzet`.`Dagdelen_has_Beschikbaarheid` (
  `idnummer` INT NOT NULL,
  `idGebruiker` INT NOT NULL,
  `Semester` DATE NOT NULL,
  `Jaar` TIME NOT NULL,
  `Blok` INT NOT NULL,
  PRIMARY KEY (`idnummer`, `idGebruiker`, `Semester`, `Jaar`, `Blok`),
  INDEX `fk_Dagdelen_has_Beschikbaarheid_Beschikbaarheid1_idx` (`idGebruiker` ASC, `Semester` ASC, `Jaar` ASC, `Blok` ASC) VISIBLE,
  INDEX `fk_Dagdelen_has_Beschikbaarheid_Dagdelen1_idx` (`idnummer` ASC) VISIBLE,
  CONSTRAINT `fk_Dagdelen_has_Beschikbaarheid_Dagdelen1`
    FOREIGN KEY (`idnummer`)
    REFERENCES `MijnInzet`.`Dagdelen` (`idnummer`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Dagdelen_has_Beschikbaarheid_Beschikbaarheid1`
    FOREIGN KEY (`idGebruiker` , `Semester` , `Jaar` , `Blok`)
    REFERENCES `MijnInzet`.`Beschikbaarheid` (`idGebruiker` , `Semester` , `Jaar` , `Blok`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MijnInzet`.`Incident`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MijnInzet`.`Incident` (
  `idIncidenten` INT NOT NULL,
  `idnummer` INT NOT NULL,
  `idGebruiker` INT NOT NULL,
  `Semester` DATE NOT NULL,
  `Jaar` TIME NOT NULL,
  `Blok` INT NOT NULL,
  PRIMARY KEY (`idIncidenten`),
  INDEX `fk_Incidenten_Dagdelen_has_Beschikbaarheid1_idx` (`idnummer` ASC, `idGebruiker` ASC, `Semester` ASC, `Jaar` ASC, `Blok` ASC) VISIBLE,
  CONSTRAINT `fk_Incidenten_Dagdelen_has_Beschikbaarheid1`
    FOREIGN KEY (`idnummer` , `idGebruiker` , `Semester` , `Jaar` , `Blok`)
    REFERENCES `MijnInzet`.`Dagdelen_has_Beschikbaarheid` (`idnummer` , `idGebruiker` , `Semester` , `Jaar` , `Blok`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MijnInzet`.`Rollen_gebruiker`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MijnInzet`.`Rollen_gebruiker` (
  `idGebruiker` INT NOT NULL,
  `Rol_id` INT NOT NULL,
  PRIMARY KEY (`idGebruiker`, `Rol_id`),
  INDEX `fk_Gebruiker_has_Rollen_Rollen1_idx` (`Rol_id` ASC) VISIBLE,
  INDEX `fk_Gebruikerollenr_has_Rollen_Gebruiker1_idx` (`idGebruiker` ASC) VISIBLE,
  CONSTRAINT `fk_Gebruiker_has_Rollen_Gebruiker1`
    FOREIGN KEY (`idGebruiker`)
    REFERENCES `MijnInzet`.`Gebruiker` (`idGebruiker`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Gebruiker_has_Rollen_Rollen1`
    FOREIGN KEY (`Rol_id`)
    REFERENCES `MijnInzet`.`Rollen` (`Rol_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('1', '4');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('2', '1');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('3', '1');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('3', '2');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('3', '3');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('3', '4');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('3', '5');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('4', '1');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;