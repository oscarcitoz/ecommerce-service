CREATE TABLE website_types
(
    id character varying(255) PRIMARY KEY
);

CREATE TABLE website_status
(
    id character varying(255) PRIMARY KEY
);

CREATE TABLE website
(
    id              character varying(100) PRIMARY KEY,
    owner_id        character varying(100)      NOT NULL,
    product_id      character varying(100)      NOT NULL,
    price       numeric(15, 2)              NOT NULL,
    name            character varying(255)      NOT NULL,
    type            character varying(255)      NOT NULL,
    status          character varying(255)      NOT NULL,
    copies          jsonb,
    images          jsonb,
    url             character varying(255)      NOT NULL,
    template_design jsonb,
    upsell_id       character varying(100),
    downsell_id     character varying(100),
    deleted_at      timestamp without time zone,
    created_at      timestamp without time zone NOT NULL,
    updated_at      timestamp without time zone NOT NULL,

    FOREIGN KEY (type) REFERENCES website_types (id),
    FOREIGN KEY (status) REFERENCES website_status (id),
    FOREIGN KEY (upsell_id) REFERENCES website (id),
    FOREIGN KEY (downsell_id) REFERENCES website (id)
);


CREATE INDEX website_owner_id_index ON website (owner_id text_ops);
CREATE INDEX website_product_id_index ON website (product_id text_ops);
CREATE INDEX website_downsell_id_index ON website (downsell_id text_ops);
CREATE INDEX website_upsell_id_index ON website (upsell_id text_ops);


INSERT INTO "public"."website_types"("id")
VALUES ('LANDING')
RETURNING "id";
INSERT INTO "public"."website_types"("id")
VALUES ('UPSELL')
RETURNING "id";
INSERT INTO "public"."website_types"("id")
VALUES ('DOWNSELL')
RETURNING "id";


INSERT INTO "public"."website_status"("id")
VALUES ('BUILDING')
RETURNING "id";
INSERT INTO "public"."website_status"("id")
VALUES ('PUBLISH')
RETURNING "id";
INSERT INTO "public"."website_status"("id")
VALUES ('PRIVATE')
RETURNING "id";
INSERT INTO "public"."website_status"("id")
VALUES ('HIDDEN')
RETURNING "id";