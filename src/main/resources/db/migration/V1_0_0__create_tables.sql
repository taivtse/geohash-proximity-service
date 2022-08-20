CREATE TABLE IF NOT EXISTS geo_spatial_index
(
    id              int PRIMARY KEY AUTO_INCREMENT,
    restaurant_name varchar(255) NOT NULL,
    geo_hash        varchar(10)  NOT NULL,
    latitude        double       NOT NULL,
    longitude       double       NOT NULL
);