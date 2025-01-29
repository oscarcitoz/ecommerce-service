CREATE TABLE discount_types
(
    id character varying(255) PRIMARY KEY
);


CREATE TABLE offers
(
    id                BIGSERIAL PRIMARY KEY,
    offer_name        character varying(255)      NOT NULL,
    offer_description character varying(500)      NOT NULL,
    enabled           boolean                     NOT NULL,
    created_at        timestamp without time zone NOT NULL,
    updated_at        timestamp without time zone NOT NULL,
    discount_type     character varying(255)      NOT NULL REFERENCES discount_types (id),
    discount_value    numeric(15, 2),
    owner_id          character varying(255)      NOT NULL,
    product_id        character varying(255)      NOT NULL
);


CREATE INDEX offers_owner_id_idx ON offers (owner_id text_ops);
CREATE UNIQUE INDEX offers_product_id_owner_type_idx ON offers (product_id text_ops);

CREATE TABLE offer_customer
(
    id          BIGSERIAL PRIMARY KEY,
    offer_id    BIGSERIAL REFERENCES offers (id),
    order_id    BIGSERIAL,
    customer_id character varying(255)      NOT NULL,
    enabled     boolean                     NOT NULL,
    created_at  timestamp without time zone NOT NULL,
    starts_at   timestamp without time zone NOT NULL,
    ends_at     timestamp without time zone NOT NULL
);

CREATE INDEX offer_customer_offer_id_idx ON offer_customer (offer_id int8_ops);
CREATE INDEX offer_customer_order_id_idx ON offer_customer (order_id int8_ops);
CREATE INDEX offer_customer_customer_id_idx ON offer_customer (customer_id text_ops);
CREATE INDEX offers_starts_at_ends_at_idx ON offer_customer (starts_at timestamp_ops, ends_at timestamp_ops);
