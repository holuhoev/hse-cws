ALTER DATABASE hsecws
CHARACTER SET utf8
COLLATE utf8_general_ci;

CREATE TABLE FACULTY
(
  ID           INT          NOT NULL
    PRIMARY KEY,
  faculty_name VARCHAR(255) NULL,
  instituteID  INT          NULL,
  CONSTRAINT FACULTY_ID_uindex
  UNIQUE (ID)
);


CREATE TABLE INSTITUTE
(
  ID             INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  institute_name VARCHAR(255)
);


CREATE TABLE STUDENT
(
  ID          INT          NOT NULL
    PRIMARY KEY,
  fio         VARCHAR(255) NULL,
  facultyID   INT          NULL,
  groupID     INT          NULL,
  instituteID INT          NULL,
  CONSTRAINT STUDENT_ID_uindex
  UNIQUE (ID)
);

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


