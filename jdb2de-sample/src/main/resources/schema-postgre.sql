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

COMMENT ON TABLE  data_types                               IS 'Customer registration';
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

CREATE TABLE customer (
  customer_id INTEGER                NOT NULL,
  store_id    SMALLINT               NOT NULL,
  first_name  VARCHAR(45)            NOT NULL,
  last_name   VARCHAR(45)            NOT NULL,
  email       VARCHAR(50),
  address_id  SMALLINT               NOT NULL,
  activebool  BOOLEAN DEFAULT TRUE   NOT NULL,
  create_date DATE DEFAULT now()     NOT NULL,
  last_update TIMESTAMP DEFAULT now(),
  active      INTEGER
);

COMMENT ON TABLE  customer             IS 'Customer registration';
COMMENT ON COLUMN customer.customer_id IS 'Customer identification';
COMMENT ON COLUMN customer.store_id    IS 'Store identification';
COMMENT ON COLUMN customer.first_name  IS 'Customer first name';
COMMENT ON COLUMN customer.last_name   IS 'Customer last name';
COMMENT ON COLUMN customer.email       IS 'Customer email address';
COMMENT ON COLUMN customer.address_id  IS 'Customer address identification';
COMMENT ON COLUMN customer.activebool  IS 'Indicate if customer is active (boolean)';
COMMENT ON COLUMN customer.create_date IS 'Creation date';
COMMENT ON COLUMN customer.last_update IS 'Last update';
COMMENT ON COLUMN customer.active      IS 'Indicate if customer is active (integer)';

CREATE TABLE actor (
  actor_id    INTEGER                 NOT NULL,
  first_name  VARCHAR(45)             NOT NULL,
  last_name   VARCHAR(45)             NOT NULL,
  last_update TIMESTAMP DEFAULT now() NOT NULL
);

COMMENT ON TABLE  actor             IS 'Actor list';
COMMENT ON COLUMN actor.actor_id    IS 'Actor identification';
COMMENT ON COLUMN actor.first_name  IS 'Actor fist name';
COMMENT ON COLUMN actor.last_name   IS 'Actor last name';
COMMENT ON COLUMN actor.last_update IS 'Last update';

CREATE TABLE category (
  category_id INTEGER                 NOT NULL,
  name        VARCHAR(25)             NOT NULL,
  last_update TIMESTAMP DEFAULT now() NOT NULL
);

COMMENT ON TABLE  category             IS 'Movie categories';
COMMENT ON COLUMN category.category_id IS 'Category identification';
COMMENT ON COLUMN category.name        IS 'Category name';
COMMENT ON COLUMN category.last_update IS 'Last update';

CREATE TABLE film (
  film_id          INTEGER                     NOT NULL,
  title            VARCHAR(255)                NOT NULL,
  description      TEXT,
  release_year     SMALLINT,
  language_id      SMALLINT                    NOT NULL,
  rental_duration  SMALLINT DEFAULT 3          NOT NULL,
  rental_rate      NUMERIC(4, 2) DEFAULT 4.99  NOT NULL,
  length           SMALLINT,
  replacement_cost NUMERIC(5, 2) DEFAULT 19.99 NOT NULL,
  rating           VARCHAR(1) DEFAULT 'G',
  last_update      TIMESTAMP DEFAULT now()     NOT NULL,
  special_features TEXT [],
  fulltext         TSVECTOR                    NOT NULL
);

COMMENT ON TABLE  film                  IS 'Available film';
COMMENT ON COLUMN film.film_id          IS 'Film identification';
COMMENT ON COLUMN film.title            IS 'Film title';
COMMENT ON COLUMN film.description      IS 'Film description';
COMMENT ON COLUMN film.release_year     IS 'Film release year';
COMMENT ON COLUMN film.language_id      IS 'Language identification';
COMMENT ON COLUMN film.rental_duration  IS 'Rental duration';
COMMENT ON COLUMN film.rental_rate      IS 'Rental rate';
COMMENT ON COLUMN film.length           IS 'Rental length';
COMMENT ON COLUMN film.replacement_cost IS 'Replacement cost';
COMMENT ON COLUMN film.rating           IS 'Rating';
COMMENT ON COLUMN film.last_update      IS 'Last update';
COMMENT ON COLUMN film.special_features IS 'Special features';
COMMENT ON COLUMN film.fulltext         IS 'Full text';

CREATE TABLE film_actor (
  actor_id    SMALLINT                NOT NULL,
  film_id     SMALLINT                NOT NULL,
  last_update TIMESTAMP DEFAULT now() NOT NULL
);

COMMENT ON TABLE  film_actor          IS 'Film and actor relation';
COMMENT ON COLUMN film_actor.actor_id IS 'Actor identification';
COMMENT ON COLUMN film_actor.film_id  IS 'Film identification';
COMMENT ON COLUMN film.last_update    IS 'Last update';

CREATE TABLE film_category (
  film_id     SMALLINT                NOT NULL,
  category_id SMALLINT                NOT NULL,
  last_update TIMESTAMP DEFAULT now() NOT NULL
);

COMMENT ON TABLE  film_category             IS 'Film and category relation';
COMMENT ON COLUMN film_category.film_id     IS 'Film identification';
COMMENT ON COLUMN film_category.category_id IS 'Category identification';
COMMENT ON COLUMN film_category.last_update IS 'Last update';

CREATE TABLE address (
  address_id  INTEGER                 NOT NULL,
  address     VARCHAR(50)             NOT NULL,
  address2    VARCHAR(50),
  district    VARCHAR(20)             NOT NULL,
  city_id     SMALLINT                NOT NULL,
  postal_code VARCHAR(10),
  phone       VARCHAR(20)             NOT NULL,
  last_update TIMESTAMP DEFAULT now() NOT NULL
);


CREATE TABLE city (
  city_id     INTEGER                 NOT NULL,
  city        VARCHAR(50)             NOT NULL,
  country_id  SMALLINT                NOT NULL,
  last_update TIMESTAMP DEFAULT now() NOT NULL
);

CREATE TABLE country (
  country_id  INTEGER                 NOT NULL,
  country     VARCHAR(50)             NOT NULL,
  last_update TIMESTAMP DEFAULT now() NOT NULL
);


CREATE TABLE inventory (
  inventory_id INTEGER                 NOT NULL,
  film_id      SMALLINT                NOT NULL,
  store_id     SMALLINT                NOT NULL,
  last_update  TIMESTAMP DEFAULT now() NOT NULL
);


CREATE TABLE language (
  language_id INTEGER                 NOT NULL,
  name        CHARACTER(20)           NOT NULL,
  last_update TIMESTAMP DEFAULT now() NOT NULL
);

CREATE TABLE payment (
  payment_id   INTEGER       NOT NULL,
  customer_id  SMALLINT      NOT NULL,
  staff_id     SMALLINT      NOT NULL,
  rental_id    INTEGER       NOT NULL,
  amount       NUMERIC(5, 2) NOT NULL,
  payment_date TIMESTAMP     NOT NULL
);

CREATE TABLE rental (
  rental_id    INTEGER                 NOT NULL,
  rental_date  TIMESTAMP               NOT NULL,
  inventory_id INTEGER                 NOT NULL,
  customer_id  SMALLINT                NOT NULL,
  return_date  TIMESTAMP,
  staff_id     SMALLINT                NOT NULL,
  last_update  TIMESTAMP DEFAULT now() NOT NULL
);

CREATE TABLE staff (
  staff_id    INTEGER                 NOT NULL,
  first_name  VARCHAR(45)             NOT NULL,
  last_name   VARCHAR(45)             NOT NULL,
  address_id  SMALLINT                NOT NULL,
  email       VARCHAR(50),
  store_id    SMALLINT                NOT NULL,
  active      BOOLEAN DEFAULT TRUE    NOT NULL,
  username    VARCHAR(16)             NOT NULL,
  password    VARCHAR(40),
  last_update TIMESTAMP DEFAULT now() NOT NULL,
  picture     BYTEA
);

CREATE TABLE store (
  store_id         INTEGER                 NOT NULL,
  manager_staff_id SMALLINT                NOT NULL,
  address_id       SMALLINT                NOT NULL,
  last_update      TIMESTAMP DEFAULT now() NOT NULL
);

-- #################################
-- PRIMARY KEYS
-- #################################

ALTER TABLE ONLY data_types
  ADD CONSTRAINT data_types_pk PRIMARY KEY (id);

ALTER TABLE ONLY actor
  ADD CONSTRAINT actor_pk PRIMARY KEY (actor_id);

ALTER TABLE ONLY address
  ADD CONSTRAINT address_pk PRIMARY KEY (address_id);

ALTER TABLE ONLY category
  ADD CONSTRAINT category_pk PRIMARY KEY (category_id);

ALTER TABLE ONLY city
  ADD CONSTRAINT city_pk PRIMARY KEY (city_id);

ALTER TABLE ONLY country
  ADD CONSTRAINT country_pk PRIMARY KEY (country_id);

ALTER TABLE ONLY customer
  ADD CONSTRAINT customer_pk PRIMARY KEY (customer_id);

ALTER TABLE ONLY film_actor
  ADD CONSTRAINT film_actor_pk PRIMARY KEY (actor_id, film_id);

ALTER TABLE ONLY film_category
  ADD CONSTRAINT film_category_pk PRIMARY KEY (film_id, category_id);

ALTER TABLE ONLY film
  ADD CONSTRAINT film_pk PRIMARY KEY (film_id);

ALTER TABLE ONLY inventory
  ADD CONSTRAINT inventory_pk PRIMARY KEY (inventory_id);

ALTER TABLE ONLY language
  ADD CONSTRAINT language_pk PRIMARY KEY (language_id);

ALTER TABLE ONLY payment
  ADD CONSTRAINT payment_pk PRIMARY KEY (payment_id);

ALTER TABLE ONLY rental
  ADD CONSTRAINT rental_pk PRIMARY KEY (rental_id);

ALTER TABLE ONLY staff
  ADD CONSTRAINT staff_pk PRIMARY KEY (staff_id);

ALTER TABLE ONLY store
  ADD CONSTRAINT store_pk PRIMARY KEY (store_id);

-- #################################
-- FOREIGN KEYS
-- #################################

ALTER TABLE ONLY customer
  ADD CONSTRAINT customer_address_id_fk FOREIGN KEY (address_id) REFERENCES address (address_id) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE ONLY film_actor
  ADD CONSTRAINT film_actor_actor_id_fk FOREIGN KEY (actor_id) REFERENCES actor (actor_id) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE ONLY film_actor
  ADD CONSTRAINT film_actor_film_id_fk FOREIGN KEY (film_id) REFERENCES film (film_id) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE ONLY film_category
  ADD CONSTRAINT film_category_category_id_fk FOREIGN KEY (category_id) REFERENCES category (category_id) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE ONLY film_category
  ADD CONSTRAINT film_category_film_id_fk FOREIGN KEY (film_id) REFERENCES film (film_id) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE ONLY film
  ADD CONSTRAINT film_language_id_fk FOREIGN KEY (language_id) REFERENCES language (language_id) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE ONLY address
  ADD CONSTRAINT fk_address_city FOREIGN KEY (city_id) REFERENCES city (city_id);

ALTER TABLE ONLY city
  ADD CONSTRAINT fk_city FOREIGN KEY (country_id) REFERENCES country (country_id);

ALTER TABLE ONLY inventory
  ADD CONSTRAINT inventory_film_id_fk FOREIGN KEY (film_id) REFERENCES film (film_id) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE ONLY payment
  ADD CONSTRAINT payment_customer_id_fk FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE ONLY payment
  ADD CONSTRAINT payment_rental_id_fk FOREIGN KEY (rental_id) REFERENCES rental (rental_id) ON UPDATE CASCADE ON DELETE SET NULL;

ALTER TABLE ONLY payment
  ADD CONSTRAINT payment_staff_id_fk FOREIGN KEY (staff_id) REFERENCES staff (staff_id) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE ONLY rental
  ADD CONSTRAINT rental_customer_id_fk FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE ONLY rental
  ADD CONSTRAINT rental_inventory_id_fk FOREIGN KEY (inventory_id) REFERENCES inventory (inventory_id) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE ONLY rental
  ADD CONSTRAINT rental_staff_id_key FOREIGN KEY (staff_id) REFERENCES staff (staff_id);

ALTER TABLE ONLY staff
  ADD CONSTRAINT staff_address_id_fk FOREIGN KEY (address_id) REFERENCES address (address_id) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE ONLY store
  ADD CONSTRAINT store_address_id_fk FOREIGN KEY (address_id) REFERENCES address (address_id) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE ONLY store
  ADD CONSTRAINT store_manager_staff_id_fk FOREIGN KEY (manager_staff_id) REFERENCES staff (staff_id) ON UPDATE CASCADE ON DELETE RESTRICT;
