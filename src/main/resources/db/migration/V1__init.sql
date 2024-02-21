CREATE SEQUENCE IF NOT EXISTS snippets_db.snippet_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE snippets_db.snippet
(
    id            BIGINT       NOT NULL DEFAULT nextval('snippets_db.snippet_seq'),
    title         VARCHAR(255) NOT NULL,
    code          OID          NOT NULL,
    created_date  BIGINT       NOT NULL,
    modified_date BIGINT,
    created_by    VARCHAR(255),
    modified_by   VARCHAR(255),
    CONSTRAINT pk_snippet PRIMARY KEY (id)
);