CREATE TABLE website_status
(
    id character varying(255) PRIMARY KEY
);

CREATE TABLE website
(
    id                                    character varying(100) PRIMARY KEY,
    owner_id                              character varying(100)      NOT NULL,
    product_id                            character varying(100)      NOT NULL,
    price                                 numeric(15, 2)              NOT NULL,
    name                                  character varying(255)      NOT NULL,
    status                                character varying(255)      NOT NULL,
    copies                                jsonb,
    images                                jsonb,
    url                                   character varying(255),
    template_design                       jsonb,
    upsell_product_id                     character varying(100),
    upsell_product_price                  numeric(15, 2),
    upsell_product_image                  character varying(255),
    down_sell_product_id                  character varying(100),
    down_sell_product_price               numeric(15, 2),
    down_sell_product_price_with_discount numeric(15, 2),
    down_sell_product_image               character varying(255),
    deleted_at                            timestamp without time zone,
    created_at                            timestamp without time zone NOT NULL,
    updated_at                            timestamp without time zone NOT NULL,

    FOREIGN KEY (status) REFERENCES website_status (id)
);


CREATE INDEX website_owner_id_index ON website (owner_id text_ops);
CREATE INDEX website_product_id_index ON website (product_id text_ops);
CREATE INDEX website_upsell_product_id_index ON website (upsell_product_id text_ops);
CREATE INDEX website_down_sell_product_id_index ON website (down_sell_product_id text_ops);


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


ALTER TABLE website
    ADD COLUMN product_description character varying(255);

ALTER TABLE website
    ADD COLUMN product_warranties numeric CHECK (product_warranties >= 0 AND product_warranties <= 100);

ALTER TABLE website
    ADD COLUMN is_free_shipping BOOLEAN;

