\connect tstpgdb

CREATE SEQUENCE tst_lob_entity_id_seq;

CREATE TABLE TST_LOB_ENTITY
(
  ID int8 NOT NULL,
  NAME varchar(255) NOT NULL,
  LOB_FIELD oid,
  CONSTRAINT TST_LOB_ENTITY_PK PRIMARY KEY (ID)
);
