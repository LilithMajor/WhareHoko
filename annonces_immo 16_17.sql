REM *************************************************
REM *******       Annonces Immobilières       *******
REM *******   Copyright © 2017 Ouziri Brette  *******
REM *************************************************

SET FEEDBACK OFF

PROMPT
PROMPT ************************************************************
PROMPT
PROMPT -> CREATION DE LA BASE DE DONNEES << VENTE D APPARTEMENTS >> 
PROMPT

DROP TABLE PROPRIETAIRES CASCADE CONSTRAINTS purge;
DROP TABLE APPARTEMENTS CASCADE CONSTRAINTS purge;
DROP SEQUENCE NUMERO_APPART;

CREATE TABLE PROPRIETAIRES (	
	Nom VARCHAR2(40) NOT NULL,
	Login VARCHAR2(20),
	Mdp VARCHAR2(40) NOT NULL,
	Email VARCHAR2(100),
	CONSTRAINT PK_PROPRIETAIRES PRIMARY KEY (Login)
);

CREATE TABLE APPARTEMENTS (
	Numero INTEGER, 
	TypeAppart VARCHAR2(6),
	Adresse VARCHAR2(75), 
	MontantVente NUMBER (10,2),
	DatePublication Date,
	LoginProp VARCHAR2(20),
	Vendu NUMBER(1,0),
	CONSTRAINT PK_APPARTEMENT PRIMARY KEY (Numero),
	CONSTRAINT FK_PROP_APPART FOREIGN KEY (LoginProp) REFERENCES PROPRIETAIRES (Login)
);

CREATE SEQUENCE NUMERO_APPART START WITH 0 MINVALUE 0 INCREMENT BY 1;

PROMPT  ->  Tables creees

INSERT INTO PROPRIETAIRES VALUES ('Paul', 'paul','paul','victorcdescartes@gmail.com');
INSERT INTO PROPRIETAIRES VALUES ('Anne', 'anne','anne','anne@parisdescartes.fr');
<<<<<<< HEAD
INSERT INTO APPARTEMENTS VALUES (numero_appart.nextval,'STUDIO','non','100',DATE '2017-02-20','paul','0');
INSERT INTO APPARTEMENTS VALUES (numero_appart.nextval,'STUDIO','non','100',DATE '2017-02-20','paul','0');
INSERT INTO APPARTEMENTS VALUES (numero_appart.nextval,'STUDIO','non','100',DATE '2017-02-20','paul','0');
INSERT INTO APPARTEMENTS VALUES (numero_appart.nextval,'STUDIO','non','100',DATE '2017-02-20','paul','0');
=======
>>>>>>> 8bf530517bdc84ca9549adeb7448f22adefb0c8c

COMMIT;

PROMPT
PROMPT -> FIN DE CREATION
PROMPT
PROMPT IMPORTANT : Merci de verifier que les tables suivantes sont bien creees :
PROMPT PROPRIETAIRES et APPARTEMENTS
PROMPT ************************************************************
PROMPT
SET FEEDBACK ON