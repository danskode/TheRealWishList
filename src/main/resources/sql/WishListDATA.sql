USE WishList;


-- Her opretter vi ønsker
-- INSERT INTO wish (wish_name, wish_url, wish_price, wish_reserved) VALUES ('Vintage t-shirt', 'test-url', 240, 1);
-- INSERT INTO wish (wish_name, wish_url, wish_price, wish_reserved) VALUES ('Blå jeans', 'test-url', 299, 0);
-- INSERT INTO wish (wish_name, wish_url, wish_price, wish_reserved) VALUES ('Flot striktrøje', 'test-url', 450, 1);
-- INSERT INTO wish (wish_name, wish_url, wish_price, wish_reserved) VALUES ('Playstation 2', 'test-url', 400, 0);

SELECT * FROM wish;

-- Her opretter vi brugere
INSERT INTO user (user_name, user_password) VALUES ('Jens', '1234');
INSERT INTO user (user_name, user_password) VALUES ('Anna', 'minkode');

SELECT * FROM user; 

-- Her opretter vi ønskelister
-- INSERT INTO wish_list (list_name) VALUES ('Juleaften');

SELECT * FROM wish_list;

