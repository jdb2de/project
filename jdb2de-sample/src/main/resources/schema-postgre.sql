CREATE TABLE customer (
  customer_id INTEGER DEFAULT nextval('customer_customer_id_seq' :: REGCLASS) NOT NULL,
  store_id    SMALLINT                                                        NOT NULL,
  first_name  CHARACTER VARYING(45)                                           NOT NULL,
  last_name   CHARACTER VARYING(45)                                           NOT NULL,
  email       CHARACTER VARYING(50),
  address_id  SMALLINT                                                        NOT NULL,
  activebool  BOOLEAN DEFAULT TRUE                                            NOT NULL,
  create_date DATE DEFAULT ('now' :: TEXT) :: DATE                            NOT NULL,
  last_update TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
  active      INTEGER
);

CREATE TABLE actor (
  actor_id    INTEGER DEFAULT nextval('actor_actor_id_seq' :: REGCLASS) NOT NULL,
  first_name  CHARACTER VARYING(45)                                     NOT NULL,
  last_name   CHARACTER VARYING(45)                                     NOT NULL,
  last_update TIMESTAMP WITHOUT TIME ZONE DEFAULT now()                 NOT NULL
);


CREATE TABLE category (
  category_id INTEGER DEFAULT nextval('category_category_id_seq' :: REGCLASS) NOT NULL,
  name        CHARACTER VARYING(25)                                           NOT NULL,
  last_update TIMESTAMP WITHOUT TIME ZONE DEFAULT now()                       NOT NULL
);


CREATE TABLE film (
  film_id          INTEGER DEFAULT nextval('film_film_id_seq' :: REGCLASS) NOT NULL,
  title            CHARACTER VARYING(255)                                  NOT NULL,
  description      TEXT,
  release_year     YEAR,
  language_id      SMALLINT                                                NOT NULL,
  rental_duration  SMALLINT DEFAULT 3                                      NOT NULL,
  rental_rate      NUMERIC(4, 2) DEFAULT 4.99                              NOT NULL,
  length           SMALLINT,
  replacement_cost NUMERIC(5, 2) DEFAULT 19.99                             NOT NULL,
  rating           MPAA_RATING DEFAULT 'G' :: MPAA_RATING,
  last_update      TIMESTAMP WITHOUT TIME ZONE DEFAULT now()               NOT NULL,
  special_features TEXT [],
  fulltext         TSVECTOR                                                NOT NULL
);


CREATE TABLE film_actor (
  actor_id    SMALLINT                                  NOT NULL,
  film_id     SMALLINT                                  NOT NULL,
  last_update TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL
);

CREATE TABLE film_category (
  film_id     SMALLINT                                  NOT NULL,
  category_id SMALLINT                                  NOT NULL,
  last_update TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL
);


CREATE TABLE address (
  address_id  INTEGER DEFAULT nextval('address_address_id_seq' :: REGCLASS) NOT NULL,
  address     CHARACTER VARYING(50)                                         NOT NULL,
  address2    CHARACTER VARYING(50),
  district    CHARACTER VARYING(20)                                         NOT NULL,
  city_id     SMALLINT                                                      NOT NULL,
  postal_code CHARACTER VARYING(10),
  phone       CHARACTER VARYING(20)                                         NOT NULL,
  last_update TIMESTAMP WITHOUT TIME ZONE DEFAULT now()                     NOT NULL
);


CREATE TABLE city (
  city_id     INTEGER DEFAULT nextval('city_city_id_seq' :: REGCLASS) NOT NULL,
  city        CHARACTER VARYING(50)                                   NOT NULL,
  country_id  SMALLINT                                                NOT NULL,
  last_update TIMESTAMP WITHOUT TIME ZONE DEFAULT now()               NOT NULL
);

CREATE TABLE country (
  country_id  INTEGER DEFAULT nextval('country_country_id_seq' :: REGCLASS) NOT NULL,
  country     CHARACTER VARYING(50)                                         NOT NULL,
  last_update TIMESTAMP WITHOUT TIME ZONE DEFAULT now()                     NOT NULL
);


CREATE TABLE inventory (
  inventory_id INTEGER DEFAULT nextval('inventory_inventory_id_seq' :: REGCLASS) NOT NULL,
  film_id      SMALLINT                                                          NOT NULL,
  store_id     SMALLINT                                                          NOT NULL,
  last_update  TIMESTAMP WITHOUT TIME ZONE DEFAULT now()                         NOT NULL
);


CREATE TABLE language (
  language_id INTEGER DEFAULT nextval('language_language_id_seq' :: REGCLASS) NOT NULL,
  name        CHARACTER(20)                                                   NOT NULL,
  last_update TIMESTAMP WITHOUT TIME ZONE DEFAULT now()                       NOT NULL
);

CREATE TABLE payment (
  payment_id   INTEGER DEFAULT nextval('payment_payment_id_seq' :: REGCLASS) NOT NULL,
  customer_id  SMALLINT                                                      NOT NULL,
  staff_id     SMALLINT                                                      NOT NULL,
  rental_id    INTEGER                                                       NOT NULL,
  amount       NUMERIC(5, 2)                                                 NOT NULL,
  payment_date TIMESTAMP WITHOUT TIME ZONE                                   NOT NULL
);

CREATE TABLE rental (
  rental_id    INTEGER DEFAULT nextval('rental_rental_id_seq' :: REGCLASS) NOT NULL,
  rental_date  TIMESTAMP WITHOUT TIME ZONE                                 NOT NULL,
  inventory_id INTEGER                                                     NOT NULL,
  customer_id  SMALLINT                                                    NOT NULL,
  return_date  TIMESTAMP WITHOUT TIME ZONE,
  staff_id     SMALLINT                                                    NOT NULL,
  last_update  TIMESTAMP WITHOUT TIME ZONE DEFAULT now()                   NOT NULL
);

CREATE TABLE staff (
  staff_id    INTEGER DEFAULT nextval('staff_staff_id_seq' :: REGCLASS) NOT NULL,
  first_name  CHARACTER VARYING(45)                                     NOT NULL,
  last_name   CHARACTER VARYING(45)                                     NOT NULL,
  address_id  SMALLINT                                                  NOT NULL,
  email       CHARACTER VARYING(50),
  store_id    SMALLINT                                                  NOT NULL,
  active      BOOLEAN DEFAULT TRUE                                      NOT NULL,
  username    CHARACTER VARYING(16)                                     NOT NULL,
  password    CHARACTER VARYING(40),
  last_update TIMESTAMP WITHOUT TIME ZONE DEFAULT now()                 NOT NULL,
  picture     BYTEA
);

CREATE TABLE store (
  store_id         INTEGER DEFAULT nextval('store_store_id_seq' :: REGCLASS) NOT NULL,
  manager_staff_id SMALLINT                                                  NOT NULL,
  address_id       SMALLINT                                                  NOT NULL,
  last_update      TIMESTAMP WITHOUT TIME ZONE DEFAULT now()                 NOT NULL
);

-- #################################
-- PRIMARY KEYS
-- #################################

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
