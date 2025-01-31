CREATE TABLE products
(
    id          character varying(100) PRIMARY KEY,
    owner_id    character varying(100)      NOT NULL,
    name        character varying(255)      NOT NULL,
    price       numeric(15, 2)              NOT NULL,
    available   boolean                     NOT NULL,
    created_at  timestamp without time zone NOT NULL,
    updated_at  timestamp without time zone NOT NULL,
    deleted_at  timestamp without time zone,
    images      jsonb                       NOT NULL,
    description text,
    category    character varying(60)
);

CREATE INDEX owner_id_index ON products (owner_id text_ops);