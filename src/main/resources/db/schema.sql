DROP TABLE IF EXISTS appliances;
DROP SEQUENCE IF EXISTS global_seq;
DROP TYPE IF EXISTS technical_condition;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TYPE technical_condition AS ENUM ('SERVICEABLE', 'FAULTY', 'WORKABLE');

CREATE TABLE appliances
(
  serial_number           INTEGER DEFAULT NEXTVAL('global_seq'),
  inventory_number        INTEGER NOT NULL UNIQUE,
  name                    VARCHAR(25) NOT NULL,
  last_verification_date  TIMESTAMP NOT NULL,
  responsible_person      VARCHAR NOT NULL,
  current_condition       technical_condition NOT NULL,
  note                    VARCHAR,
  PRIMARY KEY (serial_number)
)