CREATE TABLE NEXTNUM (
       TABLENAME            VARCHAR(24) NOT NULL,
       NEXTNUM              INTEGER NULL
);


ALTER TABLE NEXTNUM
       ADD  ( PRIMARY KEY (TABLENAME) ) ;


CREATE TABLE PROJECTS (
       PROJECTID            INTEGER NOT NULL,
       PROJECTNAME          VARCHAR(32) NULL,
       DESCRIPTION          VARCHAR(255) NULL,
       REQUESTOR            VARCHAR(32) NULL,
       REQUESTDATE          DATE NULL,
       STARTDATE            DATE NULL,
       COMPLETIONDATE       DATE NULL,
       STATUSID             INTEGER NULL,
       HOURS                INTEGER NULL,
       PRIORITY             INTEGER NULL
);


ALTER TABLE PROJECTS
       ADD  ( PRIMARY KEY (PROJECTID) ) ;


CREATE TABLE PROJECTSTATUS (
       STATUSID             INTEGER NOT NULL,
       STATUS               VARCHAR(15) NULL
);


ALTER TABLE PROJECTSTATUS
       ADD  ( PRIMARY KEY (STATUSID) ) ;


CREATE TABLE USERPROJECTS (
       PROJECTID            INTEGER NOT NULL,
       USERID               INTEGER NOT NULL,
       PROJECTORDER         INTEGER NULL
);


ALTER TABLE USERPROJECTS
       ADD  ( PRIMARY KEY (PROJECTID, USERID) ) ;


CREATE TABLE USERS (
       USERID               INTEGER NOT NULL,
       USERNAME             VARCHAR(12) NULL,
       PASSWORD             VARCHAR(12) NULL,
       ACTUALNAME           VARCHAR(32) NULL,
       PREFEREDNAME         VARCHAR(32) NULL
);


ALTER TABLE USERS
       ADD  ( PRIMARY KEY (USERID) ) ;


ALTER TABLE PROJECTS
       ADD  ( FOREIGN KEY (STATUSID)
                             REFERENCES PROJECTSTATUS ) ;


ALTER TABLE USERPROJECTS
       ADD  ( FOREIGN KEY (USERID)
                             REFERENCES USERS ) ;


ALTER TABLE USERPROJECTS
       ADD  ( FOREIGN KEY (PROJECTID)
                             REFERENCES PROJECTS ) ;
                             
                             

CREATE TABLE PROJECTNOTES (
       NOTEID               INTEGER NOT NULL,
       COMMENT              VARCHAR(255) NULL,
       COMMENTDATE          DATE NULL,
       PROJECTID            INTEGER NULL,
       USERID               INTEGER NULL
);


ALTER TABLE PROJECTNOTES
       ADD  ( PRIMARY KEY (NOTEID) ) ;


ALTER TABLE PROJECTNOTES
       ADD  ( FOREIGN KEY (USERID)
                             REFERENCES USERS ) ;


ALTER TABLE PROJECTNOTES
       ADD  ( FOREIGN KEY (PROJECTID)
                             REFERENCES PROJECTS ) ;
                             
                             
                             
------- INIALIZING TABLE VALUES ----------------
insert into NEXTNUM values('PROJECTS',0);
insert into NEXTNUM values('FETCHNOTES',0);
insert into NEXTNUM values('USERS',1);
--changeme ==> rvnzrovm
insert into USERS (USERID,USERNAME,PASSWORD,ACTUALNAME,PREFEREDNAME) 
values(0,'admin','rvnzrovm','Administrator','not-used');

	