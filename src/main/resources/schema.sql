/* userやgroupといった名前はSQLでは予約語で使えないため，userNameとしていることに注意 */
CREATE TABLE users (
    id IDENTITY NOT NULL,
    name VARCHAR NOT NULL
);
CREATE TABLE matches (
    id IDENTITY NOT NULL PRIMARY KEY,
    user1 INT NOT NULL,
    user2 INT NOT NULL,
    user1Hand VARCHAR NOT NULL,
    user2Hand VARCHAR NOT NULL,
    isActive BOOLEAN NOT NULL
);
CREATE TABLE matchinfo (
    id IDENTITY NOT NULL,
    user1 INT NOT NULL,
    user2 INT NOT NULL,
    user1Hand VARCHAR NOT NULL,
    isActive BOOLEAN NOT NULL
)
