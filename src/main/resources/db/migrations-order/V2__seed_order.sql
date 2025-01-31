INSERT INTO "public"."order_states"("id") VALUES('created') RETURNING  "id";
INSERT INTO "public"."order_states"("id") VALUES('in_progress') RETURNING  "id";
INSERT INTO "public"."order_states"("id") VALUES('finished') RETURNING  "id";
INSERT INTO "public"."order_states"("id") VALUES('canceled') RETURNING  "id";

INSERT INTO "public"."order_modification_types"("id") VALUES('add_product') RETURNING  "id";

