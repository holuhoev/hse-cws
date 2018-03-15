CREATE DATABASE hsecws CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE FACULTY
(
  ID          INT          NOT NULL
    PRIMARY KEY,
  name        VARCHAR(255) NULL,
  instituteID INT          NULL,
  CONSTRAINT FACULTY_ID_uindex
  UNIQUE (ID)
);


CREATE TABLE INSTITUTE
(
  ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(255)
);


CREATE TABLE STUDENT
(
  ID        INT AUTO_INCREMENT
    PRIMARY KEY,
  fio       VARCHAR(255) NULL,
  facultyID INT          NULL,
  groupID   INT          NULL,
  CONSTRAINT STUDENT_ID_uindex
  UNIQUE (ID)
);

CREATE TABLE `GROUP`
(
  ID        INT AUTO_INCREMENT
    PRIMARY KEY,
  facultyID INT          NULL,
  course    INT          NULL,
  name      VARCHAR(255) NULL,
  CONSTRAINT GROUP_ID_uindex
  UNIQUE (ID)
);

