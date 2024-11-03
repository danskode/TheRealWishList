-- Opretter og indsætter testdata i H2-databasen

-- Indsætter brugere
INSERT INTO "user" (user_name, user_password) VALUES ('Jens', '1234');
INSERT INTO "user" (user_name, user_password) VALUES ('Anna', 'minkode');

-- Tjekker indholdet af user-tabellen
SELECT * FROM "user";  -- Dette kan bruges til at bekræfte, at brugerne er oprettet

-- Indsætter en ønskeliste for Jens (user_id 1)
INSERT INTO wish_list (list_name, user_id) VALUES ('Juleaften', 1);

-- Tjekker indholdet af wish_list-tabellen
SELECT * FROM wish_list;  -- Bekræft, at ønskelisten er oprettet

-- Indsætter ønsker (forudsat at tabellen wish allerede findes)
-- Her antager vi, at wish_list_id 1 tilhører en eksisterende ønskeliste
INSERT INTO wish (wish_name, wish_url, wish_price, wish_reserved, wish_list_id) VALUES ('Vintage t-shirt', 'test-url', 240, TRUE, 1);
INSERT INTO wish (wish_name, wish_url, wish_price, wish_reserved, wish_list_id) VALUES ('Blå jeans', 'test-url', 299, FALSE, 1);
INSERT INTO wish (wish_name, wish_url, wish_price, wish_reserved, wish_list_id) VALUES ('Rød striktrøje', 'test-url', 450, TRUE, 1);
INSERT INTO wish (wish_name, wish_url, wish_price, wish_reserved, wish_list_id) VALUES ('Playstation 2', 'test-url', 400, FALSE, 1);
INSERT INTO wish (wish_name, wish_url, wish_price, wish_reserved, wish_list_id) VALUES ('Playstation 4', 'test-url', 400, FALSE, 1);

-- Tjekker indholdet af wish-tabellen
SELECT * FROM wish;  -- Bekræft, at ønsker er oprettet