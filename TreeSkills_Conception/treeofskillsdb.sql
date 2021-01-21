/*==============================================================*/
/* Nom de SGBD :  MySQL 5.0                                     */
/* Date de création :  1/21/2021 11:50:21 AM                    */
/*==============================================================*/


drop table if exists Apprenant;

drop table if exists Competence;

drop table if exists Niveau;

drop table if exists Promotion;

drop table if exists Refereniel;

drop table if exists Staff;

drop table if exists compapprenant;

drop table if exists promoapprenant;

/*==============================================================*/
/* Table : Apprenant                                            */
/*==============================================================*/
create table Apprenant
(
   idApp                int not null,
   nomApp               varchar(254),
   prenomApp            varchar(254),
   emailApp             varchar(254),
   telApp               varchar(254),
   villeApp             varchar(254),
   primary key (idApp)
);

/*==============================================================*/
/* Table : Competence                                           */
/*==============================================================*/
create table Competence
(
   idRef                int,
   idComp               int not null,
   idNiveau             int not null,
   titreComp            varchar(254),
   primary key (idComp, idNiveau)
);

/*==============================================================*/
/* Table : Niveau                                               */
/*==============================================================*/
create table Niveau
(
   idNiveau             int not null,
   titreNiveau          varchar(254),
   primary key (idNiveau)
);

/*==============================================================*/
/* Table : Promotion                                            */
/*==============================================================*/
create table Promotion
(
   idPromo              int not null,
   idStaff              int,
   idRef                int,
   titrePromo           varchar(254),
   anneePromo           int,
   primary key (idPromo)
);

/*==============================================================*/
/* Table : Refereniel                                           */
/*==============================================================*/
create table Refereniel
(
   idRef                int not null,
   titreRef             varchar(254),
   primary key (idRef)
);

/*==============================================================*/
/* Table : Staff                                                */
/*==============================================================*/
create table Staff
(
   idStaff              int not null,
   nomStaff             varchar(254),
   prenomStaff          varchar(254),
   specialiteStaff      varchar(254),
   nbrAppStaff          int,
   primary key (idStaff)
);

/*==============================================================*/
/* Table : compapprenant                                        */
/*==============================================================*/
create table compapprenant
(
   idApp                int,
   idComp               int,
   idNiveau             int
);

/*==============================================================*/
/* Table : promoapprenant                                       */
/*==============================================================*/
create table promoapprenant
(
   idPromo              int,
   idApp                int
);

alter table Competence add constraint FK_Association_4 foreign key (idRef)
      references Refereniel (idRef) on delete restrict on update restrict;

alter table Competence add constraint FK_Association_5 foreign key (idNiveau)
      references Niveau (idNiveau) on delete restrict on update restrict;

alter table Promotion add constraint FK_Association_3 foreign key (idRef)
      references Refereniel (idRef) on delete restrict on update restrict;

alter table Promotion add constraint FK_Association_6 foreign key (idStaff)
      references Staff (idStaff) on delete restrict on update restrict;

alter table compapprenant add constraint FK_compapprenant foreign key (idApp)
      references Apprenant (idApp) on delete restrict on update restrict;

alter table compapprenant add constraint FK_compapprenant foreign key (idComp, idNiveau)
      references Competence (idComp, idNiveau) on delete restrict on update restrict;

alter table promoapprenant add constraint FK_promoapprenant foreign key (idApp)
      references Apprenant (idApp) on delete restrict on update restrict;

alter table promoapprenant add constraint FK_promoapprenant foreign key (idPromo)
      references Promotion (idPromo) on delete restrict on update restrict;

