DROP TABLE IF EXISTS member;

CREATE TABLE MEMBER
(
    member_id   VARCHAR(200)    NOT NULL PRIMARY KEY,
    email       VARCHAR(200)    NOT NULL,
    password    VARCHAR(255)    NOT NULL,
    name        VARCHAR(20)     NOT NULL,
    age         INTEGER         NOT NULL DEFAULT 0,
    gender      CHAR(1)         NOT NULL DEFAULT 'M',
    type        VARCHAR(20)     NOT NULL DEFAULT 'COMMON'
);

DROP TABLE IF EXISTS code_info;
CREATE TABLE code_info
(
    id          VARCHAR(255)    NOT NULL PRIMARY KEY,
    code_value  VARCHAR(255)    NOT NULL,
    parent      VARCHAR(255),
    order_value INTEGER         NOT NULL DEFAULT 0
);