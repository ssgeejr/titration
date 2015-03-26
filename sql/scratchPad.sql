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

insert into PROJECTS(PROJECTID,PROJECTNAME,DESCRIPTION,REQUESTOR,REQUESTDATE,STARTDATE,COMPLETIONDATE,STATUSID,HOURS,PRIORITY)
values(0,'Project-01','this is the description for project-01','thatOneGuy',null,null,null,0,0,0);
insert into PROJECTS(PROJECTID,PROJECTNAME,DESCRIPTION,REQUESTOR,REQUESTDATE,STARTDATE,COMPLETIONDATE,STATUSID,HOURS,PRIORITY)
values(1,'Project-01','this is the description for project-01','thatOtherGuy',null,null,null,0,0,1);
insert into PROJECTS(PROJECTID,PROJECTNAME,DESCRIPTION,REQUESTOR,REQUESTDATE,STARTDATE,COMPLETIONDATE,STATUSID,HOURS,PRIORITY)
values(2,'Project-01','this is the description for project-01','thatPeskyD000d',null,null,null,0,0,2);


CREATE TABLE PROJECTSTATUS (
       STATUSID             INTEGER NOT NULL,
       STATUS               VARCHAR(15) NULL
);

insert into PROJECTSTATUS values(0,'Active');
insert into PROJECTSTATUS values(1,'Closed');
insert into PROJECTSTATUS values(2,'On-Going');

    statusModel.addElement("Active");
    statusModel.addElement("Closed");
    statusModel.addElement("On-Going");
    
    
insert into USERPROJECTS values(0,0,0);
insert into USERPROJECTS values(1,0,0);
insert into USERPROJECTS values(2,0,0);
