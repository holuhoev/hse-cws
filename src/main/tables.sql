ALTER DATABASE hsecws
CHARACTER SET utf8
COLLATE utf8_general_ci;

DROP TABLE IF EXISTS FACULTY;
CREATE TABLE FACULTY
(
  ID           INT          NOT NULL
    PRIMARY KEY,
  faculty_name VARCHAR(255) NULL,
  institute_id INT          NULL,
  CONSTRAINT FACULTY_ID_uindex
  UNIQUE (ID)
);


DROP TABLE IF EXISTS INSTITUTE;
CREATE TABLE INSTITUTE
(
  ID             INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  institute_name VARCHAR(255),
  CONSTRAINT INSTITUTE_ID_uindex
  UNIQUE (ID)
);


DROP TABLE IF EXISTS STUDENT;
CREATE TABLE STUDENT
(
  ID           INT          NOT NULL
    PRIMARY KEY,
  fio          VARCHAR(255) NULL,
  faculty_id   INT          NULL,
  group_id     INT          NULL,
  institute_id INT          NULL,
  CONSTRAINT STUDENT_ID_uindex
  UNIQUE (ID)
);

DROP TABLE IF EXISTS STUDGROUP;
CREATE TABLE STUDGROUP
(
  ID           INT          NOT NULL
    PRIMARY KEY,
  faculty_id   INT          NULL,
  institute_id INT          NULL,
  course       INT          NULL,
  group_number VARCHAR(255) NULL,
  CONSTRAINT GROUP_ID_uindex
  UNIQUE (ID)
);


DROP TABLE IF EXISTS CHAIR;
CREATE TABLE CHAIR
(
  ID         INT          NOT NULL
    PRIMARY KEY,
  faculty_id INT          NULL,
  code       VARCHAR(255) NULL,
  chair_name VARCHAR(255) NULL,
  CONSTRAINT CHAIR_ID_uindex
  UNIQUE (ID)
);

DROP TABLE IF EXISTS LECTURER;
CREATE TABLE LECTURER
(
  ID        INT          NOT NULL
    PRIMARY KEY,
  chair_id  INT          NULL,
  fio       VARCHAR(255) NULL,
  short_fio VARCHAR(255) NULL,
  CONSTRAINT LECTURER_ID_uindex
  UNIQUE (ID)
);
