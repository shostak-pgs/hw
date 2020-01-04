CREATE TABLE Users
            (id INTEGER not null AUTO_INCREMENT,
            userName VARCHAR(255),
            password VARCHAR(255),
            PRIMARY KEY(id));
CREATE TABLE Orders
            (id INTEGER not null AUTO_INCREMENT,
            userId INTEGER,
            totalPrice DOUBLE,
            FOREIGN KEY(userId) REFERENCES Users(id),
            PRIMARY KEY(id));
CREATE TABLE Goods
            (id INTEGER not null,
            name VARCHAR(255),
            price DOUBLE,
            PRIMARY KEY(id));
CREATE TABLE OrderGoods
            (id INTEGER not null AUTO_INCREMENT,
            orderId INTEGER,
            goodId INTEGER,
            FOREIGN KEY(orderId) REFERENCES Orders(id),
            FOREIGN KEY(goodId) REFERENCES Goods(id),
            PRIMARY KEY(id));
