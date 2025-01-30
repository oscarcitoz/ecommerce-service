INSERT INTO "public"."order_states"("id") VALUES('CREATED') RETURNING  "id";
INSERT INTO "public"."order_states"("id") VALUES('IN_PROGRESS') RETURNING  "id";
INSERT INTO "public"."order_states"("id") VALUES('FINISHED') RETURNING  "id";
INSERT INTO "public"."order_states"("id") VALUES('CANCELED') RETURNING  "id";

INSERT INTO "public"."order_modification_types"("id") VALUES('add_product') RETURNING  "id";

