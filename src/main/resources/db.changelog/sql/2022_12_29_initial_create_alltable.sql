CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE current_location
(
    id           BIGINT NOT NULL,
    created_at   date,
    updated_at   date,
    town_town_id BIGINT,
    CONSTRAINT pk_current_location PRIMARY KEY (id)
);

CREATE TABLE destination
(
    id           BIGINT NOT NULL,
    created_at   date,
    updated_at   date,
    town_town_id BIGINT,
    CONSTRAINT pk_destination PRIMARY KEY (id)
);

CREATE TABLE passport
(
    id         BIGINT NOT NULL,
    created_at date,
    updated_at date,
    serial     VARCHAR(255),
    number     VARCHAR(255),
    gender     VARCHAR(255),
    CONSTRAINT pk_passport PRIMARY KEY (id)
);

CREATE TABLE town
(
    town_id    BIGINT       NOT NULL,
    created_at date,
    updated_at date,
    name       VARCHAR(255) NOT NULL,
    priority   INTEGER      NOT NULL,
    CONSTRAINT pk_town PRIMARY KEY (town_id)
);

CREATE TABLE stations
(
    id                   BIGINT       NOT NULL,
    created_at           date,
    updated_at           date,
    station              VARCHAR(255) NOT NULL,
    townEntities_town_id BIGINT,
    CONSTRAINT pk_stations PRIMARY KEY (id) ---
);

CREATE TABLE ticket
(
    id                      BIGINT NOT NULL,
    created_at              date,
    updated_at              date,
    user_id                 BIGINT,
    passport_id             BIGINT,
    booked                  BOOLEAN,
    paid                    BOOLEAN,
    current_location_id     BIGINT,
    destination_location_id BIGINT,
    CONSTRAINT pk_ticket PRIMARY KEY (id)
);


CREATE TABLE users
(
    id          BIGINT NOT NULL,
    created_at  date,
    updated_at  date,
    first_name  VARCHAR(255),
    last_name   VARCHAR(255),
    passport_id BIGINT,
    ticket_id   BIGINT,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE current_location
    ADD CONSTRAINT FK_CURRENT_LOCATION_ON_TOWN_TOWN FOREIGN KEY (town_town_id) REFERENCES town (town_id);

ALTER TABLE destination
    ADD CONSTRAINT FK_DESTINATION_ON_TOWN_TOWN FOREIGN KEY (town_town_id) REFERENCES town (town_id);

ALTER TABLE stations
    ADD CONSTRAINT FK_STATIONS_ON_TOWNENTITIES_TOWN FOREIGN KEY (townEntities_town_id) REFERENCES town (town_id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_CURRENT_LOCATION FOREIGN KEY (current_location_id) REFERENCES current_location (id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_DESTINATION_LOCATION FOREIGN KEY (destination_location_id) REFERENCES destination (id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_PASSPORT FOREIGN KEY (passport_id) REFERENCES passport (id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_PASSPORT FOREIGN KEY (passport_id) REFERENCES passport (id);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_TICKET FOREIGN KEY (ticket_id) REFERENCES ticket (id);