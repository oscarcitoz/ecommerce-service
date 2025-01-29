CREATE TABLE order_states
(
    name        character varying(50) PRIMARY KEY,
    description character varying(255) NOT NULL
);

CREATE TABLE orders
(
    id                        BIGSERIAL PRIMARY KEY,
    total_value               numeric(15, 2)              NOT NULL,
    notes                     character varying(255),
    created_at                timestamp without time zone NOT NULL,
    updated_at                timestamp without time zone NOT NULL,
    state                     character varying(255) REFERENCES order_states (name),
    total_value_with_discount numeric(15, 2)
);

CREATE INDEX orders_state_idx ON orders (state text_ops);


CREATE TABLE order_customer
(
    id                    BIGSERIAL PRIMARY KEY,
    order_id              BIGSERIAL REFERENCES orders (id) ON DELETE CASCADE,
    name                  character varying(255),
    customer_address      character varying(255),
    ip_origin             character varying(100),
    prefix_phone          character varying(10),
    phone                 character varying(255),
    email                 character varying(255),
    created_at            timestamp without time zone NOT NULL,
    customer_id           character varying(255),
    identification_number character varying(255),
    identification_type   character varying(255),
    last_name             character varying(255)
);

CREATE UNIQUE INDEX order_customer_order_id_idx ON order_customer (order_id int8_ops);
CREATE INDEX order_customer_email_idx ON order_customer (email text_ops);
CREATE INDEX order_customer_phone_idx ON order_customer (phone text_ops, prefix_phone text_ops);
CREATE INDEX order_customer_id_idx ON order_customer (customer_id text_ops);


CREATE TABLE order_store
(
    id         BIGSERIAL PRIMARY KEY,
    owner_id   character varying(100)      NOT NULL,
    order_id   BIGSERIAL REFERENCES orders (id) ON DELETE CASCADE,
    url        character varying(255)      NOT NULL,
    created_at timestamp without time zone NOT NULL
);


CREATE INDEX order_store_owner_id_idx ON order_store (owner_id text_ops);
CREATE UNIQUE INDEX order_store_order_id_idx ON order_store (order_id int8_ops);


CREATE TABLE order_modifications
(
    id         BIGSERIAL PRIMARY KEY,
    order_id   BIGSERIAL REFERENCES orders (id) ON DELETE CASCADE,
    event_id   character varying(500)      NOT NULL,
    raw        jsonb,

    created_at timestamp without time zone NOT NULL
);

CREATE INDEX order_modifications_order_id_idx ON order_modifications (order_id int8_ops);
CREATE INDEX order_modifications_event_event_id_idx ON order_modifications (event_id text_ops);



CREATE TABLE order_product
(
    id                        BIGSERIAL PRIMARY KEY,
    order_id                  BIGSERIAL REFERENCES orders (id) ON DELETE CASCADE,
    product_id                character varying(255)      NOT NULL,
    name                      character varying(255)      NOT NULL,
    images                    jsonb                       NOT NULL,
    value_offer               numeric(15, 2),
    units                     integer                     NOT NULL,
    unit_price                numeric(15, 2)              NOT NULL,
    unit_price_with_discount  numeric(15, 2)              NOT NULL,
    total_value               numeric(15, 2)              NOT NULL,
    created_at                timestamp without time zone NOT NULL,
    updated_at                timestamp without time zone NOT NULL,
    customer_comments         character varying(500),
    total_value_with_discount numeric(15, 2)
);

CREATE INDEX order_product_order_id_idx ON order_product (order_id int8_ops);
CREATE INDEX order_product_product_id_idx ON order_product (product_id text_ops);


CREATE TABLE order_offer
(
    id               BIGSERIAL PRIMARY KEY,
    order_product_id BIGSERIAL REFERENCES order_product (id) ON DELETE CASCADE,
    offer_id         bigint                      NOT NULL,
    starts_at        timestamp without time zone NOT NULL,
    ends_at          timestamp without time zone NOT NULL,
    created_at       timestamp without time zone NOT NULL,
    discount_type    character varying(255)      NOT NULL,
    discount_value   numeric(15, 2),
    discount_applied numeric(15, 2)
);

CREATE INDEX order_offer__order_product_id_idx ON order_offer (order_product_id int8_ops);
CREATE INDEX order_offer_offer_id_idx ON order_offer (offer_id int8_ops);