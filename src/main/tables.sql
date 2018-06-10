ALTER DATABASE hsecws
CHARACTER SET utf8
COLLATE utf8_general_ci;

DROP TABLE IF EXISTS faculty;
CREATE TABLE faculty
(
  ID           INT          NOT NULL
    PRIMARY KEY,
  faculty_name VARCHAR(255) NULL,
  institute_id INT          NULL,
  CONSTRAINT FACULTY_ID_uindex
  UNIQUE (ID)
);


DROP TABLE IF EXISTS institute;
CREATE TABLE institute
(
  ID             INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  institute_name VARCHAR(255),
  CONSTRAINT INSTITUTE_ID_uindex
  UNIQUE (ID)
);


DROP TABLE IF EXISTS student;
CREATE TABLE student
(
  ID             INT          NOT NULL
    PRIMARY KEY,
  fio            VARCHAR(255) NULL,
  faculty_id     INT          NULL,
  group_id       INT          NULL,
  institute_id   INT          NULL,
  course         INT          NULL,
  education_type INT          NULL,
  CONSTRAINT STUDENT_ID_uindex
  UNIQUE (ID)
);

DROP TABLE IF EXISTS studgroup;
CREATE TABLE studgroup
(
  ID             INT          NOT NULL
    PRIMARY KEY,
  faculty_id     INT          NULL,
  institute_id   INT          NULL,
  course         INT          NULL,
  group_number   VARCHAR(255) NULL,
  education_type INT          NULL,
  CONSTRAINT GROUP_ID_uindex
  UNIQUE (ID)
);


DROP TABLE IF EXISTS chair;
CREATE TABLE chair
(
  ID         INT          NOT NULL
    PRIMARY KEY,
  faculty_id INT          NULL,
  code       VARCHAR(255) NULL,
  chair_name VARCHAR(255) NULL,
  CONSTRAINT CHAIR_ID_uindex
  UNIQUE (ID)
);

DROP TABLE IF EXISTS lecturer;
CREATE TABLE lecturer
(
  ID        INT          NOT NULL
    PRIMARY KEY,
  chair_id  INT          NULL,
  fio       VARCHAR(255) NULL,
  short_fio VARCHAR(255) NULL,
  CONSTRAINT LECTURER_ID_uindex
  UNIQUE (ID)
);


DROP TABLE IF EXISTS studworkload;
CREATE TABLE studworkload
(
  ID         INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  student_id INT  NOT NULL,
  workload   INT  NOT NULL,
  day        DATE NOT NULL,
  CONSTRAINT STUDWORKLOAD_ID_uindex
  UNIQUE (ID)
);

DROP TABLE IF EXISTS building;
CREATE TABLE building
(
  ID            INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  address       VARCHAR(255) NULL,
  building_name VARCHAR(255) NULL,
  city          INT          NULL,
  CONSTRAINT BUILDING_ID_uindex
  UNIQUE (ID)
);

DROP TABLE IF EXISTS pair;
CREATE TABLE pair
(
  ID       INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  pair     INT  NOT NULL,
  city     INT  NOT NULL,
  week_day INT  NOT NULL,
  begin    TIME NOT NULL,
  end      TIME NOT NULL,
  CONSTRAINT BUILDING_ID_uindex
  UNIQUE (ID)
);


DROP TABLE IF EXISTS lectworkload;
CREATE TABLE lectworkload
(
  ID          INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  lecturer_id INT  NOT NULL,
  workload    INT  NOT NULL,
  day         DATE NOT NULL,
  CONSTRAINT LECTWORKLOAD_ID_uindex
  UNIQUE (ID)
);


