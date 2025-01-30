CREATE TYPE website_type AS ENUM ('landing', 'upsell', 'downsell');

CREATE TABLE websites
(
    id          character varying(100) PRIMARY KEY,
    owner_id    character varying(100)      NOT NULL,
    product_id  character varying(100)      NOT NULL,
    name        character varying(255)      NOT NULL,
    type        website_type                NOT NULL,
    url         character varying(255)      NOT NULL,
    configuration   jsonb,

    deleted_at  timestamp without time zone,
    created_at  timestamp without time zone NOT NULL,
    updated_at  timestamp without time zone NOT NULL,
);

CREATE INDEX owner_id_index ON products (owner_id text_ops);