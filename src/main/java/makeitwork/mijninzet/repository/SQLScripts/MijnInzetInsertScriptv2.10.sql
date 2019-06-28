-- insert gebruikers

INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`,`achternaam`,`email`,`uren`,`uren_bezetting`) VALUES ('1', 'TheBoss', '$2a$10$txE89lVWqzofuQpGc7LFcuC.y6BbsrtSeXUcUnOGU/Obw5cQsFNsS','1','TheBoss','info@theboss.tu','0','0');
INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`,`achternaam`,`email`,`uren`,`uren_bezetting`) VALUES ('2', 'TheTeacher', '$2a$10$szbWYgtrnVnTZV4FxwcS6.XfB7flFK3ElwMg70GDXzHvJn/q7GPSu','1','TheTeacher','info@theteacher.ru','40','0');
INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`,`achternaam`,`email`,`uren`,`uren_bezetting`) VALUES ('3', 'Terminator', '$2a$10$Gv9vyEofFsczKjLB73UbhevF9.Twq4DXzL1XvquYhFgxU0gPmd3fe','1','Capo','info@ciao.it','0','0');


INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`,`achternaam`,`email`,`uren`,`uren_bezetting`) VALUES ('4', 'Huub van Thienen', '$2a$10$Gv9vyEofFsczKjLB73UbhevF9.Twq4DXzL1XvquYhFgxU0gPmd3fe','1','Thienen','h.a.van.thienen@hva.nl','0','0');
INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`,`achternaam`,`email`,`uren`,`uren_bezetting`) VALUES ('7', 'Gerke de Boer', '$2a$10$Gv9vyEofFsczKjLB73UbhevF9.Twq4DXzL1XvquYhFgxU0gPmd3fe','1','de Boer','g.f.de.boer@hva.nl','0','0');
INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`,`achternaam`,`email`,`uren`,`uren_bezetting`) VALUES ('8', 'Pietje precies', '$2a$10$Gv9vyEofFsczKjLB73UbhevF9.Twq4DXzL1XvquYhFgxU0gPmd3fe','1','de Boer','p.precies@hva.nl','0','0');
INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`,`achternaam`,`email`,`uren`,`uren_bezetting`) VALUES ('9', 'Ipsem Lorem', '$2a$10$Gv9vyEofFsczKjLB73UbhevF9.Twq4DXzL1XvquYhFgxU0gPmd3fe','1','Ipsem Lorem','I.Lorem@hva.nl','0','0');

INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`,`achternaam`,`email`,`uren`,`uren_bezetting`) VALUES ('10', 'Geen docent', '$2a$10$Gv9vyEofFsczKjLB73UbhevF9.Twq4DXzL1XvquYhFgxU0gPmd3fe','0','Geen docent','geen.docent@hva.nl','0','0');
INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`,`achternaam`,`email`,`uren`,`uren_bezetting`) VALUES ('5', 'Merel', '$2a$10$6vTfkahn3JjTWDQGcJE4buOD0SA1Vw1rjHEpq4bosTqZaM1.qRFNK','1','Slingenberg','info@slingenberg.nl','0','0');
INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`,`achternaam`,`email`,`uren`,`uren_bezetting`) VALUES ('6', 'Slobodanka', '$2a$10$6vTfkahn3JjTWDQGcJE4buOD0SA1Vw1rjHEpq4bosTqZaM1.qRFNK','0','Boss','Slobbie@hva.nl','0','0');

INSERT INTO `mijninzet`.`rollen`(`Rol_id`,`Rol`) VALUES ('1','Docent');
INSERT INTO `mijninzet`.`rollen`(`Rol_id`,`Rol`) VALUES ('2','Administrateur');
INSERT INTO `mijninzet`.`rollen`(`Rol_id`,`Rol`) VALUES ('3','Terminator');
INSERT INTO `mijninzet`.`rollen`(`Rol_id`,`Rol`) VALUES ('4','Manager');
INSERT INTO `mijninzet`.`rollen`(`Rol_id`,`Rol`) VALUES ('5','Roosteraar');

INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('10', '1');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('4', '1');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('7', '4');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('8', '5');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('9', '2');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('1', '4');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('2', '1');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('3', '3');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('4', '1');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('5', '1');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('10', '1');

INSERT INTO `mijninzet`.`vak` (`codeVak`, `naamvak`, `uren`) VALUES ('1', 'Programming', '50');
INSERT INTO `mijninzet`.`vak` (`codeVak`, `naamvak`, `uren`) VALUES ('2', 'Object oriented programming', '50');
INSERT INTO `mijninzet`.`vak` (`codeVak`, `naamvak`, `uren`) VALUES ('3 ', 'Databases', '50');
INSERT INTO `mijninzet`.`vak` (`codeVak`, `naamvak`, `uren`) VALUES ('4', 'Advanced programming', '50');
INSERT INTO `mijninzet`.`vak` (`codeVak`, `naamvak`, `uren`) VALUES ('5', 'Complex programming', '50');
INSERT INTO `mijninzet`.`vak` (`codeVak`, `naamvak`, `uren`) VALUES ('6', 'Data structures', '50');
INSERT INTO `mijninzet`.`vak` (`codeVak`, `naamvak`, `uren`) VALUES ('7', 'Test driven development', '50');



-- -----------------------------------------------------
-- Table `mijninzet`.`cohort`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `mijninzet`.`cohort` (
  `cohort_naam` VARCHAR(45) NOT NULL,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  PRIMARY KEY (`cohort_naam`))
ENGINE = InnoDB;