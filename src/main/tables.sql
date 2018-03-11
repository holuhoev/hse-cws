CREATE TABLE INSTITUTE
(
  ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(256)
);
CREATE UNIQUE INDEX INSTITUTE_ID_uindex ON INSTITUTE (ID);

CREATE TABLE STUDENT
(
  ID        INT AUTO_INCREMENT
    PRIMARY KEY,
  fio       VARCHAR(256) NULL,
  facultyID INT          NULL,
  groupID   INT          NULL,
  CONSTRAINT STUDENT_ID_uindex
  UNIQUE (ID)
);
CREATE UNIQUE INDEX STUDENT_ID_uindex
  ON STUDENT (ID);

CREATE TABLE `GROUP`
(
  ID        INT AUTO_INCREMENT
    PRIMARY KEY,
  facultyID INT          NULL,
  course    INT          NULL,
  name      VARCHAR(256) NULL,
  CONSTRAINT GROUP_ID_uindex
  UNIQUE (ID)
);
CREATE UNIQUE INDEX GROUP_ID_uindex
  ON `GROUP` (ID);

