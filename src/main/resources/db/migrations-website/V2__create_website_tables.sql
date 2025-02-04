ALTER TABLE website
    ADD COLUMN product_description character varying(255);

ALTER TABLE website
    ADD COLUMN product_warranties numeric CHECK (product_warranties >= 0 AND product_warranties <= 100);

ALTER TABLE website
    ADD COLUMN is_free_shipping BOOLEAN;

