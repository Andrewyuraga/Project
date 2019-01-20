CREATE DATABASE ShopDB;
USE ShopDB;
CREATE TABLE USER
(
  USER_ID   INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
  FULL_NAME VARCHAR(20) NOT NULL,
  EMAIL     VARCHAR(30) NOT NULL,
  PASSWORD  VARCHAR(30) NOT NULL,
  ADDRESS   VARCHAR(90) NOT NULL,
  BIRTHDAY  DATE,
  STATUS    CHAR(10)                         DEFAULT 'ACTIVE',
  RIGHTS    CHAR(6)                          DEFAULT 'USER'
);
CREATE UNIQUE INDEX USER_EMAIL_uindex
  ON USER (EMAIL);

CREATE TABLE REVIEWS(
  REVIEW_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  USER_ID INT NOT NULL,
  REVIEW VARCHAR(255),
  CONSTRAINT USER_REVIEWS_FK FOREIGN KEY (USER_ID) REFERENCES USER (USER_ID)
);

CREATE TABLE PARTS
(
  PARTS_ID        INT(7) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  PRODUCER        VARCHAR(50) NOT NULL,
  CATEGORY        VARCHAR(50) NOT NULL,
  IMG             VARCHAR(255) NOT NULL,
  NAME_PARTS      VARCHAR(50) NOT NULL,
  CHATACTERISTICS VARCHAR(50) NOT NULL,
  PRICE           DOUBLE                NOT NULL DEFAULT 0.00
);

CREATE TABLE ORD
(
  ORD_ID   INT(11) PRIMARY KEY    NOT NULL AUTO_INCREMENT,
  DATE     DATETIME,
  USER_ID  INT(10)                NOT NULL,
  PARTS_ID INT(10) NOT NULL,
  QUANTITY INT(5)                          DEFAULT 1,
  STATUS   CHAR(10)                        DEFAULT 'NEW',
  TOTAL    DOUBLE                    NOT NULL DEFAULT 0.00,
  CONSTRAINT USER_FK FOREIGN KEY (USER_ID) REFERENCES USER (USER_ID),
  CONSTRAINT PARTS_FK FOREIGN KEY (PARTS_ID) REFERENCES PARTS (PARTS_ID)
);

CREATE INDEX user_fk
  ON ORD (USER_ID);

CREATE TABLE ITEM
(
  ITEM_ID    INT           NOT NULL PRIMARY KEY AUTO_INCREMENT,
  ORD_ID     INT(11)       NOT NULL,
  PARTS_ID   INT           NOT NULL,
  QUANTITY   INT DEFAULT 1 NOT NULL,
  DISCOUNT   DEC DEFAULT 0,
  CONSTRAINT PARTS_ITEM_FK FOREIGN KEY (PARTS_ID) REFERENCES PARTS (PARTS_ID),
  CONSTRAINT ORD_ITEM_FK FOREIGN KEY (ORD_ID) REFERENCES ORD (ORD_ID)
);

INSERT INTO USER (USER_ID, FULL_NAME, EMAIL, PASSWORD, ADDRESS, BIRTHDAY, STATUS, RIGHTS) VALUE (
  1, 'Andrew Yuraga', 'yurag@icloud.com', 'qwerty','Borisov, Chapaeva 12' , '1998-03-12', 'ACTIVE', 'USER'
);

INSERT INTO PARTS (PARTS_ID, PRODUCER, CATEGORY, IMG, NAME_PARTS, CHATACTERISTICS, PRICE) VALUE
  (1, 'Shell', 'Motor oil', 'https://www.amag.ru/upload/iblock/6fa/6faae42a986d46926c6269c246aefc5a.jpg', 'Helix HX8 4l', '5W-40', 25.50),
  (2, 'Shell', 'Motor oil', 'https://www.amag.ru/upload/iblock/6fa/6faae42a986d46926c6269c246aefc5a.jpg','Helix Ultra 4l', '5W-30', 40.00),
  (3, 'Liqui Moly', 'Motor oil', 'https://www.amag.ru/upload/iblock/6fa/6faae42a986d46926c6269c246aefc5a.jpg','ATF Top Tec 5l', '1200', 69.50),
  (4, 'Bosch', 'Accumulator', 'https://www.amag.ru/upload/iblock/6fa/6faae42a986d46926c6269c246aefc5a.jpg', 'S5', '100 A/h', 144.30);
