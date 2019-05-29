-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema MijnInzet
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema MijnInzet
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `MijnInzet` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema mijninzet
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mijninzet
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mijninzet` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `MijnInzet` ;

-- -----------------------------------------------------
-- Table `MijnInzet`.`Gebruiker`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MijnInzet`.`Gebruiker` (
  `idGebruiker` INT(11) NOT NULL AUTO_INCREMENT,
  `gebruikersnaam` VARCHAR(45) NOT NULL,
  `wachtwoord` CHAR(60) NOT NULL,
  `actief` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idGebruiker`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;

INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`) VALUES ('1', 'TheBoss', '$2a$10$txE89lVWqzofuQpGc7LFcuC.y6BbsrtSeXUcUnOGU/Obw5cQsFNsS','1');
INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`) VALUES ('2', 'TheTeacher', '$2a$10$szbWYgtrnVnTZV4FxwcS6.XfB7flFK3ElwMg70GDXzHvJn/q7GPSu','1');
INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`) VALUES ('3', 'capo di tutti capi', '$2a$10$Gv9vyEofFsczKjLB73UbhevF9.Twq4DXzL1XvquYhFgxU0gPmd3fe','1');
INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`) VALUES ('4', 'ForeverDisabled', '$2a$10$bkyCwHFpeiSt6TcaDCEXS.tOXrip8FmM0Hlrxy8ZjzRjdAb4yEYx2','0');


-- -----------------------------------------------------
-- Table `MijnInzet`.`Docent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MijnInzet`.`Docent` (
  `idGebruiker` INT NOT NULL,
  PRIMARY KEY (`idGebruiker`),
  CONSTRAINT `fk_Docent_Gebruiker`
    FOREIGN KEY (`idGebruiker`)
    REFERENCES `MijnInzet`.`Gebruiker` (`idGebruiker`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MijnInzet`.`Oee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MijnInzet`.`Oee` (
  `idGebruiker` INT NOT NULL,
  PRIMARY KEY (`idGebruiker`),
  CONSTRAINT `fk_Oee_Gebruiker1`
    FOREIGN KEY (`idGebruiker`)
    REFERENCES `MijnInzet`.`Gebruiker` (`idGebruiker`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MijnInzet`.`Manager`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MijnInzet`.`Manager` (
  `idGebruiker` INT NOT NULL,
  PRIMARY KEY (`idGebruiker`),
  CONSTRAINT `fk_Manager_Gebruiker1`
    FOREIGN KEY (`idGebruiker`)
    REFERENCES `MijnInzet`.`Gebruiker` (`idGebruiker`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MijnInzet`.`Roosteraar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MijnInzet`.`Roosteraar` (
  `idGebruiker` INT NOT NULL,
  PRIMARY KEY (`idGebruiker`),
  CONSTRAINT `fk_Roosteraar_Gebruiker1`
    FOREIGN KEY (`idGebruiker`)
    REFERENCES `MijnInzet`.`Gebruiker` (`idGebruiker`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MijnInzet`.`Administrator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MijnInzet`.`Administrator` (
  `idGebruiker` INT NOT NULL,
  PRIMARY KEY (`idGebruiker`),
  CONSTRAINT `fk_Administrator_Gebruiker1`
    FOREIGN KEY (`idGebruiker`)
    REFERENCES `MijnInzet`.`Gebruiker` (`idGebruiker`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mijninzet`.`vacature`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mijninzet`.`vacature` (
  `Titel` VARCHAR(45) NOT NULL,
  `idGebruiker` INT(11) NOT NULL,
  `Ingangsdatum` DATETIME NOT NULL,
  `Einddatum` DATETIME NOT NULL,
  `Volgnummer` INT(11) NOT NULL,
  `idGebruiker1` INT(11) NOT NULL,
  PRIMARY KEY (`Volgnummer`),
  INDEX `fk_Vacature_Taak1_idx` (`idGebruiker1` ASC) VISIBLE,
  CONSTRAINT `fk_Vacature_Taak1`
    FOREIGN KEY (`idGebruiker1`)
    REFERENCES `mijninzet`.`taak` (`idGebruiker`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `MijnInzet`.`Beschikbaarheid`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MijnInzet`.`Beschikbaarheid` (
  `Datum` DATE NOT NULL,
  `Dagdeel` TIME NOT NULL,
  `Jaar` YEAR NOT NULL,
  `Semester` INT NOT NULL,
  `Blok` INT NOT NULL,
  `idGebruiker` INT NOT NULL,
  `Vak` VARCHAR(45) NOT NULL,
  `Opleiding` VARCHAR(45) NOT NULL,
  INDEX `fk_Beschikbaarheid_Docent1_idx` (`idGebruiker` ASC) VISIBLE,
  PRIMARY KEY (`idGebruiker`, `Datum`, `Dagdeel`),
  CONSTRAINT `fk_Beschikbaarheid_Docent1`
    FOREIGN KEY (`idGebruiker`)
    REFERENCES `MijnInzet`.`Docent` (`idGebruiker`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MijnInzet`.`Taken`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MijnInzet`.`Taken` (
  `Type` VARCHAR(45) NOT NULL,
  `Lesduur` INT NOT NULL,
  `Geldigheidsduur` INT NOT NULL,
  `Soort` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Type`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MijnInzet`.`Vak`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MijnInzet`.`Vak` (
  `Vakcode` CHAR(25) NOT NULL,
  `Vaknaam` VARCHAR(45) NOT NULL,
  `Kennisgebied` VARCHAR(45) NOT NULL,
  `Opleiding` VARCHAR(45) NOT NULL,
  `idGebruiker` INT NOT NULL,
  PRIMARY KEY (`Vakcode`),
  INDEX `fk_Vak_Docent1_idx` (`idGebruiker` ASC) VISIBLE,
  CONSTRAINT `fk_Vak_Docent1`
    FOREIGN KEY (`idGebruiker`)
    REFERENCES `MijnInzet`.`Docent` (`idGebruiker`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MijnInzet`.`voorkeur`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MijnInzet`.`voorkeur` (
  `idpref` INT NOT NULL,
  `voorkeur` VARCHAR(45) NOT NULL,
  `codevak` INT NOT NULL,
  `idgebruiker` INT NOT NULL,
  PRIMARY KEY (`idpref`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MijnInzet`.`docent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MijnInzet`.`docent` (
  `idGebruiker` INT NOT NULL,
  PRIMARY KEY (`idGebruiker`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MijnInzet`.`incidentele_beschikbaarheid`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MijnInzet`.`incidentele_beschikbaarheid` (
  `incidentId` INT NOT NULL,
  `datum` DATE NOT NULL,
  `dagdeel` INT NOT NULL,
  `idGebruiker` INT NOT NULL,
  PRIMARY KEY (`incidentId`),
  INDEX `fk_incidentele_beschikbaarheid_docent1_idx` (`idGebruiker` ASC) VISIBLE,
  CONSTRAINT `fk_incidentele_beschikbaarheid_docent1`
    FOREIGN KEY (`idGebruiker`)
    REFERENCES `MijnInzet`.`docent` (`idGebruiker`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MijnInzet`.`dagdeel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MijnInzet`.`dagdeel` (
  `werkdag` INT NOT NULL,
  `dagdeel` INT NOT NULL,
  PRIMARY KEY (`werkdag`, `dagdeel`))
ENGINE = InnoDB;

insert into dagdeel (`werkdag`, `dagdeel`) VALUES (1,1);
insert into dagdeel (`werkdag`, `dagdeel`) VALUES (1,2);
insert into dagdeel (`werkdag`, `dagdeel`) VALUES (1,3);
insert into dagdeel (`werkdag`, `dagdeel`) VALUES (2,1);
insert into dagdeel (`werkdag`, `dagdeel`) VALUES (2,2);
insert into dagdeel (`werkdag`, `dagdeel`) VALUES (2,3);
insert into dagdeel (`werkdag`, `dagdeel`) VALUES (3,1);
insert into dagdeel (`werkdag`, `dagdeel`) VALUES (3,2);
insert into dagdeel (`werkdag`, `dagdeel`) VALUES (3,3);
insert into dagdeel (`werkdag`, `dagdeel`) VALUES (4,1);
insert into dagdeel (`werkdag`, `dagdeel`) VALUES (4,2);
insert into dagdeel (`werkdag`, `dagdeel`) VALUES (4,3);
insert into dagdeel (`werkdag`, `dagdeel`) VALUES (5,1);
insert into dagdeel (`werkdag`, `dagdeel`) VALUES (5,2);
insert into dagdeel (`werkdag`, `dagdeel`) VALUES (5,3);

-- -----------------------------------------------------
-- Table `MijnInzet`.`globale_beschikbaarheid`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MijnInzet`.`globale_beschikbaarheid` (
  `beschikbaar` TINYINT NOT NULL,
  `idGebruiker` INT NOT NULL,
  `werkdag` INT NOT NULL,
  `dagdeel` INT NOT NULL,
  PRIMARY KEY (`idGebruiker`, `werkdag`, `dagdeel`),
  INDEX `fk_globale_beschikbaarheid_dagdeel1_idx` (`werkdag` ASC, `dagdeel` ASC) VISIBLE,
  CONSTRAINT `fk_globale_beschikbaarheid_docent1`
    FOREIGN KEY (`idGebruiker`)
    REFERENCES `MijnInzet`.`docent` (`idGebruiker`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_globale_beschikbaarheid_dagdeel1`
    FOREIGN KEY (`werkdag` , `dagdeel`)
    REFERENCES `MijnInzet`.`dagdeel` (`werkdag` , `dagdeel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `mijninzet` ;

-- -----------------------------------------------------
-- Table `mijninzet`.`gebruiker`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mijninzet`.`gebruiker` (
  `idgebruiker` INT(11) NOT NULL,
  `actief` INT(11) NOT NULL,
  `wachtwoord` VARCHAR(255) NOT NULL,
  `gebruikersnaam` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idgebruiker`))
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mijninzet`.`hibernate_sequence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mijninzet`.`hibernate_sequence` (
  `next_val` BIGINT(20) NULL DEFAULT NULL)
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mijninzet`.`rollen`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mijninzet`.`rollen` (
  `rol_id` INT(11) NOT NULL,
  `rol` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`rol_id`))
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `rollen`(`Rol_id`,`Rol`) VALUES ('1','Docent');
INSERT INTO `rollen`(`Rol_id`,`Rol`) VALUES ('2','Administrateur');
INSERT INTO `rollen`(`Rol_id`,`Rol`) VALUES ('3','Systeem');
INSERT INTO `rollen`(`Rol_id`,`Rol`) VALUES ('4','Manager');
INSERT INTO `rollen`(`Rol_id`,`Rol`) VALUES ('5','Roosteraar');

-- -----------------------------------------------------
-- Table `mijninzet`.`rollen_gebruiker`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mijninzet`.`rollen_gebruiker` (
  `idgebruiker` INT(11) NOT NULL,
  `rol_id` INT(11) NOT NULL,
  INDEX `FKt6yifv3vee6iu3w7qo1ydjf7f` (`rol_id` ASC) VISIBLE,
  INDEX `FKc1njm95aqoe86uelmsjbsf47w` (`idgebruiker` ASC) VISIBLE)
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('1', '4');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('2', '1');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('3', '1');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('3', '2');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('3', '3');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('3', '4');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('3', '5');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('4', '1');

-- -----------------------------------------------------
-- Table `mijninzet`.`vak`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mijninzet`.`vak` (
  `codevak` INT(11) NOT NULL,
  `naamvak` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`codevak`))
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mijninzet`.`voorkeur`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mijninzet`.`voorkeur` (
  `idpref` BIGINT(20) NOT NULL,
  `voorkeur` INT(11) NULL DEFAULT NULL,
  `codevak` INT(11) NULL DEFAULT NULL,
  `idgebruiker` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idpref`),
  INDEX `FKd0sp4jai680ca4s8njyd1fmtb` (`codevak` ASC) VISIBLE,
  INDEX `FKh9q1g44qjq5rdsp5ccq79p3b0` (`idgebruiker` ASC) VISIBLE)
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
