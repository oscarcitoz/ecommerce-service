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
    id              character varying(100)      PRIMARY KEY,
    owner_id        character varying(100)      NOT NULL,
    product_id      character varying(100)      NOT NULL,
    name            character varying(255)      NOT NULL,
    type            website_type                NOT NULL,
    status          website_status              NOT NULL,
    copies          jsonb,
    images          jsonb,
    url             character varying(255)      NOT NULL,
    template_design jsonb,
    upsell_id       character varying(100),
    downsell_id     character varying(100),

    deleted_at      timestamp without time zone,
    created_at      timestamp without time zone NOT NULL,
    updated_at      timestamp without time zone NOT NULL,

    FOREIGN KEY (type) REFERENCES website_types(id),
    FOREIGN KEY (status) REFERENCES website_status(id),
    FOREIGN KEY (upsell_id) REFERENCES website(id) ON DELETE SET NULL,
    FOREIGN KEY (downsell_id) REFERENCES website(id) ON DELETE SET NULL
);




INSERT INTO "public"."website_types"("id") VALUES ('LANDING') RETURNING "id";
INSERT INTO "public"."website_types"("id") VALUES ('UPSELL') RETURNING "id";
INSERT INTO "public"."website_types"("id") VALUES ('DOWSELL') RETURNING "id";


INSERT INTO "public"."website_status"("id") VALUES ('BUILDING') RETURNING "id";
INSERT INTO "public"."website_status"("id") VALUES ('PUBLISH') RETURNING "id";
INSERT INTO "public"."website_status"("id") VALUES ('PRIVATE') RETURNING "id";
INSERT INTO "public"."website_status"("id") VALUES ('HIDDEN') RETURNING "id";