CREATE TABLE users
(
    id_user      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name         VARCHAR(16),
    sur_name     VARCHAR(16),
    patronymic   VARCHAR(16),
    phone_number VARCHAR(255),
    email        VARCHAR(30),
    CONSTRAINT pk_users PRIMARY KEY (id_user)
);