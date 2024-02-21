CREATE SEQUENCE IF NOT EXISTS snippet_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE snippet
(
    id            BIGINT       NOT NULL DEFAULT nextval('snippet_seq'),
    title         VARCHAR(255) NOT NULL,
    code          OID          NOT NULL,
    created_date  BIGINT       NOT NULL,
    modified_date BIGINT,
    CONSTRAINT pk_snippet PRIMARY KEY (id)
);