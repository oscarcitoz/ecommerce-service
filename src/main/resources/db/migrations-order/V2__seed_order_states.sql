INSERT INTO "public"."order_states"("id") VALUES('CREATED') RETURNING  "id";
INSERT INTO "public"."order_states"("id") VALUES('IN_PROGRESS') RETURNING  "id";
INSERT INTO "public"."order_states"("id") VALUES('FINISHED') RETURNING  "id";