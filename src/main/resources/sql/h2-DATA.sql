-- Opretter og indsætter testdata i H2-databasen

-- Indsætter ønsker (forudsat at tabellen wish allerede findes)
INSERT INTO wish (wish_name, wish_url, wish_price, wish_reserved) VALUES ('Vintage t-shirt', 'test-url', 240, TRUE);
INSERT INTO wish (wish_name, wish_url, wish_price, wish_reserved) VALUES ('Blå jeans', 'test-url', 299, FALSE);
INSERT INTO wish (wish_name, wish_url, wish_price, wish_reserved) VALUES ('Flot striktrøje', 'test-url', 450, TRUE);
INSERT INTO wish (wish_name, wish_url, wish_price, wish_reserved) VALUES ('Playstation 2', 'test-url', 400, FALSE);

-- Tjekker indholdet af wish-tabellen
SELECT * FROM wish;

-- Indsætter brugere
INSERT INTO user (user_name, user_password) VALUES ('Jens', '1234');
INSERT INTO user (user_name, user_password) VALUES ('Anna', 'minkode');

-- Tjekker indholdet af user-tabellen
SELECT * FROM user;

-- Indsætter en ønskeliste
INSERT INTO wish_list (list_name, user_id) VALUES ('Juleaften', 1); -- Her antager vi, at user_id 1 tilhører en eksisterende bruger som Jens

-- Tjekker indholdet af wish_list-tabellen
SELECT * FROM wish_list;