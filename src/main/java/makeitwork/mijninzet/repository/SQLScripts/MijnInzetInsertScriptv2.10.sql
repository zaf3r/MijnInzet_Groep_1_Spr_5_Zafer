-- insert gebruikers

INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`,`achternaam`,`email`,`uren`,`uren_bezetting`) VALUES ('1', 'TheBoss', '$2a$10$txE89lVWqzofuQpGc7LFcuC.y6BbsrtSeXUcUnOGU/Obw5cQsFNsS','1','TheBoss','info@theboss.tu','40','0');
INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`,`achternaam`,`email`,`uren`,`uren_bezetting`) VALUES ('2', 'Remi de Boer', '$2a$10$szbWYgtrnVnTZV4FxwcS6.XfB7flFK3ElwMg70GDXzHvJn/q7GPSu','1','de Boer','info@theteacher.ru','40','0');
INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`,`achternaam`,`email`,`uren`,`uren_bezetting`) VALUES ('3', 'Terminator', '$2a$10$Gv9vyEofFsczKjLB73UbhevF9.Twq4DXzL1XvquYhFgxU0gPmd3fe','1','Capo','info@ciao.it','40','0');


INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`,`achternaam`,`email`,`uren`,`uren_bezetting`) VALUES ('4', 'Huub van Thienen', '$2a$10$Gv9vyEofFsczKjLB73UbhevF9.Twq4DXzL1XvquYhFgxU0gPmd3fe','1','Thienen','h.a.van.thienen@hva.nl','40','0');
INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`,`achternaam`,`email`,`uren`,`uren_bezetting`) VALUES ('7', 'Gerke de Boer', '$2a$10$Gv9vyEofFsczKjLB73UbhevF9.Twq4DXzL1XvquYhFgxU0gPmd3fe','1','de Boer','g.f.de.boer@hva.nl','40','0');
INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`,`achternaam`,`email`,`uren`,`uren_bezetting`) VALUES ('8', 'Pietje precies', '$2a$10$Gv9vyEofFsczKjLB73UbhevF9.Twq4DXzL1XvquYhFgxU0gPmd3fe','1','de Boer','p.precies@hva.nl','40','0');
INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`,`achternaam`,`email`,`uren`,`uren_bezetting`) VALUES ('9', 'Ipsem Lorem', '$2a$10$Gv9vyEofFsczKjLB73UbhevF9.Twq4DXzL1XvquYhFgxU0gPmd3fe','1','Ipsem Lorem','I.Lorem@hva.nl','40','0');

INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`,`achternaam`,`email`,`uren`,`uren_bezetting`) VALUES ('10', 'Geen docent', '$2a$10$Gv9vyEofFsczKjLB73UbhevF9.Twq4DXzL1XvquYhFgxU0gPmd3fe','0','Geen docent','geen.docent@hva.nl','0','0');
INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`,`achternaam`,`email`,`uren`,`uren_bezetting`) VALUES ('5', 'Merel', '$2a$10$6vTfkahn3JjTWDQGcJE4buOD0SA1Vw1rjHEpq4bosTqZaM1.qRFNK','1','Slingenberg','info@slingenberg.nl','40','0');
INSERT INTO `mijninzet`.`gebruiker` (`idGebruiker`, `gebruikersnaam`, `wachtwoord`,`actief`,`achternaam`,`email`,`uren`,`uren_bezetting`) VALUES ('6', 'Slobodanka', '$2a$10$6vTfkahn3JjTWDQGcJE4buOD0SA1Vw1rjHEpq4bosTqZaM1.qRFNK','0','Boss','Slobbie@hva.nl','40','0');

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

insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-01-01', 'Nieuwjaarsdag');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-07-08', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-07-09', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-07-10', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2020-07-11', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-07-12', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-07-13', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-07-14', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-07-15', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-07-16', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-07-17', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-07-18', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-07-19', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-07-20', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-07-21', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-07-22', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-07-23', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-07-24', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-07-25', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-07-26', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-07-27', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-07-28', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-07-29', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-07-30', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-07-31', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-01', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-02', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-03', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-04', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-05', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-06', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-07', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-08', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-09', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-10', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-11', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-12', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-13', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-14', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-15', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-16', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-17', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-18', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-19', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-20', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-21', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-22', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-23', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-24', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-25', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-26', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-27', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-28', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-29', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-30', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-08-31', 'Zomervakantie');
insert into  `MijnInzet`.`vakantie_rooster` (`datum`, `omaschrijving`) VALUES ('2019-09-01', 'Zomervakantie');



-- -----------------------------------------------------
-- Table `mijninzet`.`cohort`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `mijninzet`.`cohort` (
  `cohort_naam` VARCHAR(45) NOT NULL,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  PRIMARY KEY (`cohort_naam`))
ENGINE = InnoDB;
INSERT INTO `mijninzet`.`vacature`(`id`, `beschrijving`, `beschrijving_lang`, `einddatum`, `locatie`, `sluitdatum`, `startdatum`, `task_status`, `titel`, `uren`)
VALUES ('1', 'De Engelse term voor spek is ‘bacon’. Maar in Nederland bestaat er wel degelijk verschil tussen bacon en spek. Ontbijtspek bijvoorbeeld wordt uit de buik gesneden.
Het vlees voor bacon wordt gesneden uit de karbonadestrook van het varken...', 'van de rug, met een laagje spek erop. Daardoor is een plakje bacon ook iets minder vet. Ontbijtspek
bijvoorbeeld wordt uit de buik gesneden.' , '2019-08-20', 'Fraijlemaborg', '2019-09-21', '2019-10-20', '0', 'BaconBakker', '4');
INSERT INTO `mijninzet`.`vacature`(`id`, `beschrijving`, `beschrijving_lang`, `einddatum`, `locatie`, `sluitdatum`, `startdatum`, `task_status`, `titel`, `uren`)
VALUES ('2', 'Ben jij een zonnestraaltje in de ochtend en ben je verkikkerd op koffie? Dan hebben we jou nodig! Voor Brandstof op de AmstelCampus zijn we op zoek naar een barrista.
Het betreft met name enkele uren in de ochtend', 'Het zetten van een heerlijk bakje koffie vroeg in de morgen is voor jou geen grote opgave, evenmin geldt dat voor het aanspreken en begroeten van mensen.
Dan hebben we jou nodig! Voor Brandstof op de AmstelCampus zijn we op zoek naar een barrista.', '2019-09-20', 'AmstelCampus Wibautstraat', '2019-10-21', '2019-11-15','0', 'Barrista', '2');
INSERT INTO `mijninzet`.`vacature`(`id`, `beschrijving`, `beschrijving_lang`, `einddatum`, `locatie`, `sluitdatum`, `startdatum`, `task_status`, `titel`, `uren`)
VALUES ('3', 'Ben jij een boeken- en papierworm? Doe je graag onderzoek en ben je geordend. De AmstelCampus is op zoek naar een betrokken archivarus die een dag in de week het archief up-to-date
houdt...', 'Ben jij een boeken- en papierworm? Doe je graag onderzoek en ben je geordend. De AmstelCampus is op zoek naar een betrokken archivarus die een dag in de week het archief up-to-date
houdt.', '2019-10-20', 'Fraijlemaborg', '2019-10-21', '2019-10-15','0', 'Archivaris', '4');
INSERT INTO `mijninzet`.`vacature`(`id`, `beschrijving`, `beschrijving_lang`, `einddatum`, `locatie`, `sluitdatum`, `startdatum`, `task_status`, `titel`, `uren`)
VALUES ('4', 'Spicy jalapeno bacon ipsum dolor amet dolore ribeye labore, proident pig t-bone bacon brisket turducken sunt cupidatat beef ground round. Filet mignon burgdoggen
shank occaecat turkey. Est doner aliqua, non strip steak... ', 'Spicy jalapeno bacon ipsum dolor amet dolore k occaecat turkey. Venison ut eiusmod consectetur buffalo turkey.
Commodo tempor jerky. Sed capicoladolorribs ad.Spicy je consequat ut.', '2019-08-20', 'Kohnstammhuis', '2019-09-21', '2019-10-20', '0', 'Lorem Specialist', '8');
INSERT INTO `mijninzet`.`vacature`(`id`, `beschrijving`, `beschrijving_lang`, `einddatum`, `locatie`, `sluitdatum`, `startdatum`, `task_status`, `titel`, `uren`)
VALUES ('5', 'Voor de afsluiting van de lokalen op de vierde verdieping, alle stoelen en tafels op z’n plaats zetten, het bord uitvegen en de grond aanvegen.
 Op die manier kunnen de studenten en docenten in een schoon lokaal verse kennis opsnuiven...', 'Voor de afsluiting van de lokalen op de vierde verdieping,
 alle stoelen en tafels op z’n plaats zetten, het bord uitvegen en de grond aanvegen. Op die manier kunnen de studenten en docenten in een schoon lokaal verse
 kennis opsnuiven.', '2019-09-14', 'Kohnstammhuis', '2019-09-03', '2019-11-05', '0', 'Afsluiter','4');
INSERT INTO `mijninzet`.`vacature`(`id`, `beschrijving`, `beschrijving_lang`, `einddatum`, `locatie`, `sluitdatum`, `startdatum`, `task_status`, `titel`, `uren`)
VALUES ('6', 'Het aanwijzen van examinatoren behoort tot de wettelijke taken en bevoegdheden van de examencommissie. Een examencommissie dient elk jaar voor elk
 studieonderdeel vast te stellen wie als examinator optreedt.', 'Een examencommissie dient elk jaar voor elk studieonderdeel vast te stellen wie als examinator optreedt.
  De examencommissie baseert zich bij het aanwijzen van examinatoren op criteria, die zijn vastgelegd in een profiel voor examinatoren…', '2020-10-01', 'Kohnstammhuis', '2019-10-01', '2019-10-08', '0', 'Examinator', '4');
INSERT INTO `mijninzet`.`vacature`(`id`, `beschrijving`, `beschrijving_lang`, `einddatum`, `locatie`, `sluitdatum`, `startdatum`, `task_status`, `titel`, `uren`)
VALUES ('7', 'Voor al het technisch onderhoud in de verschillende Hva gebouwen van de campus, zijn we op zoek naar een echte techniek nerd. Het liefst vind je het heerlijk
 om moeilijke problemen op te lossen...', 'We zijn we op zoek naar een echte techniek nerd. Voor al het technisch onderhoud in de verschillende Hva gebouwen van de campus.
 Het liefst vind je het heerlijk om moeilijke problemen op te lossen. Ben jij deze persoon?', '2020-12-05', 'AmstelCampus Wibautstraat', '2019-07-05', '2019-09-01', '0', 'Technicus', '2');
INSERT INTO `mijninzet`.`vacature`(`id`, `beschrijving`, `beschrijving_lang`, `einddatum`, `locatie`, `sluitdatum`, `startdatum`, `task_status`, `titel`, `uren`)
VALUES ('8', 'Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel,
luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt…', 'Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing
 sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt.', '2020-08-02', 'AmstelCampus Wibautstraat', '2019-07-02', '2019-09-07', '0',
 'Titelcreativeling', '4');
INSERT INTO `mijninzet`.`vacature`(`id`, `beschrijving`, `beschrijving_lang`, `einddatum`, `locatie`, `sluitdatum`, `startdatum`, `task_status`, `titel`, `uren`)
VALUES ('9', 'We zijn op zoek naar een ICT-specialist die verantwoordelijk is voor het dagelijks beheer en het onderhoud van de ICT-omgeving binnen een organisatie.
Bij netwerkproblemen of andere technische complicaties…', 'We zijn op zoek naar een ICT-specialist die verantwoordelijk is voor het dagelijks beheer en het onderhoud
van de ICT-omgeving binnen een organisatie. Bij netwerkproblemen of andere technische complicaties…', '2020-10-01', 'Theo Thijssenhuis', '2019-07-07', '2019-09-08', '0', 'Systeembeheerder', '8');

INSERT INTO `mijninzet`.`cohort`(`cohort_id`, `cohort_naam`, `end_date`, `start_date`, `idgebruiker`) VALUES ('1', 'Lente', '2019-03-01', '2019-07-05', '7');

-- -----------------------------------------------------
-- Table `mijninzet`.`cohort`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `mijninzet`.`cohort` (
  `cohort_naam` VARCHAR(45) NOT NULL,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  PRIMARY KEY (`cohort_naam`))
ENGINE = InnoDB;

