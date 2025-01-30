CREATE TYPE website_type AS ENUM ('LANDING', 'UPSELL', 'DOWSELL');
CREATE TYPE website_status AS ENUM ('BUILDING', 'PUBLISH', 'PRIVATE', 'HIDDEN');

CREATE TABLE website
(
    id              character varying(100)      PRIMARY KEY,
    owner_id        character varying(100)      NOT NULL,
    product_id      character varying(100)      NOT NULL,
    name            character varying(255)      NOT NULL,
    type            website_type                NOT NULL,
    status          website_status              NOT NULL,
    copys           jsonb,
    url             character varying(255)      NOT NULL,
    template_design jsonb,
    upsell_id       character varying(100),
    downsell_id     character varying(100),

    deleted_at      timestamp without time zone,
    created_at      timestamp without time zone NOT NULL,
    updated_at      timestamp without time zone NOT NULL,

    FOREIGN KEY (upsell_id) REFERENCES website(id) ON DELETE SET NULL,
    FOREIGN KEY (downsell_id) REFERENCES website(id) ON DELETE SET NULL
);

