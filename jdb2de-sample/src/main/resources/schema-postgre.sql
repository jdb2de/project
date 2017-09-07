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
