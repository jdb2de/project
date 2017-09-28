CREATE TABLE data_types (
  id                             INTEGER NOT NULL,
  tp_bigint                      BIGINT,
  tp_bigserial                   BIGSERIAL,
  tp_bit                         BIT(1),
  tp_bit_varying                 BIT VARYING(10),
  tp_boolean                     BOOLEAN,
  tp_box                         BOX,
  tp_bytea                       BYTEA,
  tp_character                   CHARACTER(20),
  tp_character_varying           CHARACTER VARYING(30),
  tp_cidr                        CIDR,
  tp_circle                      CIRCLE,
  tp_date                        DATE,
  tp_double_precision            DOUBLE PRECISION,
  tp_inet                        INET,
  tp_integer                     INTEGER,
  tp_interval                    INTERVAL,
  tp_json                        JSON,
  tp_line                        LINE,
  tp_lseg                        LSEG,
  tp_macaddr                     MACADDR,
  tp_money                       MONEY,
  tp_numeric                     NUMERIC (10, 3),
  tp_path                        PATH,
  tp_point                       POINT,
  tp_polygon                     POLYGON,
  tp_real                        REAL,
  tp_smallint                    SMALLINT,
  tp_smallserial                 SMALLSERIAL,
  tp_serial                      SERIAL,
  tp_text                        TEXT,
  tp_time_without_time_zone      TIME WITHOUT TIME ZONE,
  tp_time_with_time_zone         TIME WITH TIME ZONE,
  tp_timestamp_without_time_zone TIMESTAMP WITHOUT TIME ZONE,
  tp_timestamp_with_time_zone    TIMESTAMP WITH TIME ZONE,
  tp_tsquery                     TSQUERY,
  tp_tsvector                    TSVECTOR,
  tp_txid_snapshot               TXID_SNAPSHOT,
  tp_uuid                        UUID,
  tp_xml                         XML
);

COMMENT ON TABLE  data_types                                IS 'PostgreSQL available data types';
COMMENT ON COLUMN data_types.tp_bigint                      IS 'Signed eight-byte integer';
COMMENT ON COLUMN data_types.tp_bigserial                   IS 'Auto incrementing eight-byte integer';
COMMENT ON COLUMN data_types.tp_bit                         IS 'Fixed-length bit string';
COMMENT ON COLUMN data_types.tp_bit_varying                 IS 'Variable-length bit string';
COMMENT ON COLUMN data_types.tp_boolean                     IS 'Logical Boolean (true/false)';
COMMENT ON COLUMN data_types.tp_box                         IS 'Rectangular box on a plane';
COMMENT ON COLUMN data_types.tp_bytea                       IS 'Binary data ("byte array")';
COMMENT ON COLUMN data_types.tp_character                   IS 'Fixed-length character string';
COMMENT ON COLUMN data_types.tp_character_varying           IS 'Variable-length character string';
COMMENT ON COLUMN data_types.tp_cidr                        IS 'IPv4 or IPv6 network address';
COMMENT ON COLUMN data_types.tp_circle                      IS 'Circle on a plane';
COMMENT ON COLUMN data_types.tp_date                        IS 'Calendar date (year, month, day)';
COMMENT ON COLUMN data_types.tp_double_precision            IS 'Double precision floating-point number (8 bytes)';
COMMENT ON COLUMN data_types.tp_inet                        IS 'IPv4 or IPv6 host address';
COMMENT ON COLUMN data_types.tp_integer                     IS 'Signed four-byte integer';
COMMENT ON COLUMN data_types.tp_interval                    IS 'Time span';
COMMENT ON COLUMN data_types.tp_json                        IS 'JSON data';
COMMENT ON COLUMN data_types.tp_line                        IS 'Infinite line on a plane';
COMMENT ON COLUMN data_types.tp_lseg                        IS 'Line segment on a plane';
COMMENT ON COLUMN data_types.tp_macaddr                     IS 'MAC (Media Access Control) address';
COMMENT ON COLUMN data_types.tp_money                       IS 'Currency amount';
COMMENT ON COLUMN data_types.tp_numeric                     IS 'Exact numeric of selectable precision';
COMMENT ON COLUMN data_types.tp_path                        IS 'Geometric path on a plane';
COMMENT ON COLUMN data_types.tp_point                       IS 'Geometric point on a plane';
COMMENT ON COLUMN data_types.tp_polygon                     IS 'Closed geometric path on a plane';
COMMENT ON COLUMN data_types.tp_real                        IS 'Single precision floating-point number (4 bytes)';
COMMENT ON COLUMN data_types.tp_smallint                    IS 'Signed two-byte integer';
COMMENT ON COLUMN data_types.tp_smallserial                 IS 'Auto incrementing two-byte integer';
COMMENT ON COLUMN data_types.tp_serial                      IS 'Auto incrementing four-byte integer';
COMMENT ON COLUMN data_types.tp_text                        IS 'Variable-length character string';
COMMENT ON COLUMN data_types.tp_time_without_time_zone      IS 'Time of day (no time zone)';
COMMENT ON COLUMN data_types.tp_time_with_time_zone         IS 'Time of day, including time zone';
COMMENT ON COLUMN data_types.tp_timestamp_without_time_zone IS 'Date and time (no time zone)';
COMMENT ON COLUMN data_types.tp_timestamp_with_time_zone    IS 'Date and time, including time zone';
COMMENT ON COLUMN data_types.tp_tsquery                     IS 'Text search query';
COMMENT ON COLUMN data_types.tp_tsvector                    IS 'Text search document';
COMMENT ON COLUMN data_types.tp_txid_snapshot               IS 'User-level transaction ID snapshot';
COMMENT ON COLUMN data_types.tp_uuid                        IS 'Universally unique identifier';
COMMENT ON COLUMN data_types.tp_xml                         IS 'XML data';

CREATE TABLE simple (
  id            INTEGER NOT NULL,
  creation_date DATE    NOT NULL DEFAULT now()
);

COMMENT ON TABLE  simple               IS 'Simple table';
COMMENT ON COLUMN simple.id            IS 'Simple table identification';
COMMENT ON COLUMN simple.creation_date IS 'Creation date';

CREATE TABLE composite (
  id            INTEGER NOT NULL,
  simple_id     INTEGER NOT NULL,
  creation_date DATE    NOT NULL DEFAULT now()
);

COMMENT ON TABLE  composite               IS 'Composite primary key';
COMMENT ON COLUMN composite.id            IS 'Composite primary key identification';
COMMENT ON COLUMN composite.simple_id     IS 'Simple table identification';
COMMENT ON COLUMN composite.creation_date IS 'Creation date';

CREATE TABLE multiple_relation (
  id            INTEGER NOT NULL,
  simple_id     INTEGER NOT NULL,
  simple_one_id INTEGER NOT NULL,
  simple_two_id INTEGER NOT NULL
);

COMMENT ON TABLE  multiple_relation               IS 'Multiple relation';
COMMENT ON COLUMN multiple_relation.id            IS 'Multiple relation identification';
COMMENT ON COLUMN multiple_relation.simple_id     IS 'Main relation';
COMMENT ON COLUMN multiple_relation.simple_one_id IS 'Relation one';
COMMENT ON COLUMN multiple_relation.simple_two_id IS 'Relation two';

CREATE TABLE tb_prefix (
  id          INTEGER     NOT NULL,
  description VARCHAR(10) NOT NULL
);

COMMENT ON TABLE  tb_prefix             IS 'Prefix table';
COMMENT ON COLUMN tb_prefix.id          IS 'Prefix table identification';
COMMENT ON COLUMN tb_prefix.description IS 'Description field';

CREATE TABLE tb_prefix_relation (
  id          INTEGER     NOT NULL,
  prefix_id   INTEGER     NOT NULL
);

COMMENT ON TABLE  tb_prefix_relation           IS 'Prefix relation table';
COMMENT ON COLUMN tb_prefix_relation.id        IS 'Prefix table relation identification';
COMMENT ON COLUMN tb_prefix_relation.prefix_id IS 'Prefix table identification';

-- #################################
-- PRIMARY KEYS
-- #################################

ALTER TABLE ONLY data_types
  ADD CONSTRAINT data_types_pk PRIMARY KEY (id);

ALTER TABLE ONLY simple
  ADD CONSTRAINT simple_pk PRIMARY KEY (id);

ALTER TABLE ONLY composite
  ADD CONSTRAINT composite_pk PRIMARY KEY (id, simple_id);

ALTER TABLE ONLY multiple_relation
  ADD CONSTRAINT multiple_relation_pk PRIMARY KEY (id);

ALTER TABLE ONLY tb_prefix
  ADD CONSTRAINT tb_prefix_pk PRIMARY KEY (id);

ALTER TABLE ONLY tb_prefix_relation
  ADD CONSTRAINT tb_prefix_relation_pk PRIMARY KEY (id);

-- #################################
-- FOREIGN KEYS
-- #################################
ALTER TABLE ONLY composite
  ADD CONSTRAINT composite_fk01 FOREIGN KEY (simple_id) REFERENCES simple (id);

ALTER TABLE ONLY multiple_relation
  ADD CONSTRAINT multiple_relation_fk01 FOREIGN KEY (simple_id) REFERENCES simple (id);

ALTER TABLE ONLY multiple_relation
  ADD CONSTRAINT multiple_relation_fk02 FOREIGN KEY (simple_one_id) REFERENCES simple (id);

ALTER TABLE ONLY multiple_relation
  ADD CONSTRAINT multiple_relation_fk03 FOREIGN KEY (simple_two_id) REFERENCES simple (id);

ALTER TABLE ONLY tb_prefix_relation
  ADD CONSTRAINT tb_prefix_relation_fk01 FOREIGN KEY (prefix_id) REFERENCES tb_prefix (id);
