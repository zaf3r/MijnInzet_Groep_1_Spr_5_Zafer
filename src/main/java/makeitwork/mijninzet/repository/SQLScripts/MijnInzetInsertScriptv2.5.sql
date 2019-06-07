-- insert gebruikers

INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`) VALUES ('1', 'TheBoss', '$2a$10$txE89lVWqzofuQpGc7LFcuC.y6BbsrtSeXUcUnOGU/Obw5cQsFNsS','1');
INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`) VALUES ('2', 'TheTeacher', '$2a$10$szbWYgtrnVnTZV4FxwcS6.XfB7flFK3ElwMg70GDXzHvJn/q7GPSu','1');
INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`) VALUES ('3', 'capo di tutti capi', '$2a$10$Gv9vyEofFsczKjLB73UbhevF9.Twq4DXzL1XvquYhFgxU0gPmd3fe','1');
INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`) VALUES ('4', 'ForeverDisabled', '$2a$10$bkyCwHFpeiSt6TcaDCEXS.tOXrip8FmM0Hlrxy8ZjzRjdAb4yEYx2','0');

INSERT INTO `rollen`(`Rol_id`,`Rol`) VALUES ('1','Docent');
INSERT INTO `rollen`(`Rol_id`,`Rol`) VALUES ('2','Administrateur');
INSERT INTO `rollen`(`Rol_id`,`Rol`) VALUES ('3','Systeem');
INSERT INTO `rollen`(`Rol_id`,`Rol`) VALUES ('4','Manager');
INSERT INTO `rollen`(`Rol_id`,`Rol`) VALUES ('5','Roosteraar');

INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('1', '4');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('2', '1');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('3', '1');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('3', '2');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('3', '3');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('3', '4');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('3', '5');
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('4', '1');

INSERT INTO `mijninzet`.`vak` (`codeVak`, `naamvak`) VALUES ('1', 'Programming');
INSERT INTO `mijninzet`.`vak` (`codeVak`, `naamvak`) VALUES ('2', 'Object oriented programming');
INSERT INTO `mijninzet`.`vak` (`codeVak`, `naamvak`) VALUES ('3 ', 'Databases');
INSERT INTO `mijninzet`.`vak` (`codeVak`, `naamvak`) VALUES ('4', 'Advanced programming');
INSERT INTO `mijninzet`.`vak` (`codeVak`, `naamvak`) VALUES ('5', 'Complex programming');
INSERT INTO `mijninzet`.`vak` (`codeVak`, `naamvak`) VALUES ('6', 'Data structures');
INSERT INTO `mijninzet`.`vak` (`codeVak`, `naamvak`) VALUES ('7', 'Test driven development');

