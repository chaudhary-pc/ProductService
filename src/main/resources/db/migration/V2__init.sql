CREATE TABLE category
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      datetime NULL,
    last_updated_at datetime NULL,
    is_deleted      BIT(1) NOT NULL,
    name            VARCHAR(255) NULL,
    `description`   VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE joined_instructor
(
    user_id         BIGINT NOT NULL,
    teaching_domain VARCHAR(255) NULL,
    CONSTRAINT pk_joined_instructor PRIMARY KEY (user_id)
);

CREATE TABLE joined_mentor
(
    user_id BIGINT NOT NULL,
    average_rating DOUBLE NOT NULL,
    CONSTRAINT pk_joined_mentor PRIMARY KEY (user_id)
);

CREATE TABLE joined_user
(
    id    BIGINT NOT NULL,
    name  VARCHAR(255) NULL,
    email VARCHAR(255) NULL,
    CONSTRAINT pk_joined_user PRIMARY KEY (id)
);

CREATE TABLE msc_instructor
(
    id              BIGINT NOT NULL,
    name            VARCHAR(255) NULL,
    email           VARCHAR(255) NULL,
    teaching_domain VARCHAR(255) NULL,
    CONSTRAINT pk_msc_instructor PRIMARY KEY (id)
);

CREATE TABLE msc_mentor
(
    id    BIGINT NOT NULL,
    name  VARCHAR(255) NULL,
    email VARCHAR(255) NULL,
    average_rating DOUBLE NOT NULL,
    CONSTRAINT pk_msc_mentor PRIMARY KEY (id)
);

CREATE TABLE product
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      datetime NULL,
    last_updated_at datetime NULL,
    is_deleted      BIT(1) NOT NULL,
    title           VARCHAR(255) NULL,
    price DOUBLE NULL,
    category_id     BIGINT NULL,
    `description`   VARCHAR(255) NULL,
    image           VARCHAR(255) NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE sc_user
(
    id              BIGINT NOT NULL,
    user_type       INT NULL,
    name            VARCHAR(255) NULL,
    email           VARCHAR(255) NULL,
    teaching_domain VARCHAR(255) NULL,
    average_rating DOUBLE NOT NULL,
    CONSTRAINT pk_sc_user PRIMARY KEY (id)
);

CREATE TABLE tpc_instructor
(
    id              BIGINT NOT NULL,
    name            VARCHAR(255) NULL,
    email           VARCHAR(255) NULL,
    teaching_domain VARCHAR(255) NULL,
    CONSTRAINT pk_tpc_instructor PRIMARY KEY (id)
);

CREATE TABLE tpc_mentor
(
    id    BIGINT NOT NULL,
    name  VARCHAR(255) NULL,
    email VARCHAR(255) NULL,
    average_rating DOUBLE NOT NULL,
    CONSTRAINT pk_tpc_mentor PRIMARY KEY (id)
);

CREATE TABLE tpc_user
(
    id    BIGINT NOT NULL,
    name  VARCHAR(255) NULL,
    email VARCHAR(255) NULL,
    CONSTRAINT pk_tpc_user PRIMARY KEY (id)
);

ALTER TABLE joined_instructor
    ADD CONSTRAINT FK_JOINED_INSTRUCTOR_ON_USER FOREIGN KEY (user_id) REFERENCES joined_user (id);

ALTER TABLE joined_mentor
    ADD CONSTRAINT FK_JOINED_MENTOR_ON_USER FOREIGN KEY (user_id) REFERENCES joined_user (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);