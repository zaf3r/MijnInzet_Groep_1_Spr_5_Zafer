-- insert gebruikers

INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`,`achternaam`,`email`) VALUES ('1', 'TheBoss', '$2a$10$txE89lVWqzofuQpGc7LFcuC.y6BbsrtSeXUcUnOGU/Obw5cQsFNsS','1','TheBoss','info@theboss.tu');
INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`,`achternaam`,`email`) VALUES ('2', 'TheTeacher', '$2a$10$szbWYgtrnVnTZV4FxwcS6.XfB7flFK3ElwMg70GDXzHvJn/q7GPSu','1','TheTeacher','info@theteacher.ru');
INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`,`achternaam`,`email`) VALUES ('3', 'capo di tutti capi', '$2a$10$Gv9vyEofFsczKjLB73UbhevF9.Twq4DXzL1XvquYhFgxU0gPmd3fe','1','Capo','info@ciao.it');
INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`,`achternaam`,`email`) VALUES ('4', 'ForeverDisabled', '$2a$10$bkyCwHFpeiSt6TcaDCEXS.tOXrip8FmM0Hlrxy8ZjzRjdAb4yEYx2','0','WeZijnBoos','info@nergenshuizen.nl');
INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`,`achternaam`,`email`) VALUES ('5', 'Merel', '$2a$10$6vTfkahn3JjTWDQGcJE4buOD0SA1Vw1rjHEpq4bosTqZaM1.qRFNK','1','Slingenberg','info@slingenberg.nl');


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
INSERT INTO `mijninzet`.`rollen_gebruiker` (`idGebruiker`, `Rol_id`) VALUES ('5', '1');

INSERT INTO `mijninzet`.`vak` (`codeVak`, `naamvak`) VALUES ('1', 'Programming');
INSERT INTO `mijninzet`.`vak` (`codeVak`, `naamvak`) VALUES ('2', 'Object oriented programming');
INSERT INTO `mijninzet`.`vak` (`codeVak`, `naamvak`) VALUES ('3 ', 'Databases');
INSERT INTO `mijninzet`.`vak` (`codeVak`, `naamvak`) VALUES ('4', 'Advanced programming');
INSERT INTO `mijninzet`.`vak` (`codeVak`, `naamvak`) VALUES ('5', 'Complex programming');
INSERT INTO `mijninzet`.`vak` (`codeVak`, `naamvak`) VALUES ('6', 'Data structures');
INSERT INTO `mijninzet`.`vak` (`codeVak`, `naamvak`) VALUES ('7', 'Test driven development');

INSERT INTO `MijnInzet`.`knowledge_area` (`knowledge_area_id`, `knowledge_area`) VALUES ('1', 'Software Engineering');
INSERT INTO `MijnInzet`.`knowledge_area` (`knowledge_area_id`, `knowledge_area`) VALUES ('2', 'Databases');
INSERT INTO `MijnInzet`.`knowledge_area` (`knowledge_area_id`, `knowledge_area`) VALUES ('3', 'Git');
INSERT INTO `MijnInzet`.`knowledge_area` (`knowledge_area_id`, `knowledge_area`) VALUES ('4', 'Mickey Mouse tekenen');