CREATE DATABASE usedCarsDatabase;
DROP DATABASE usedCarsDatabase;
use usedCarsDatabase;

CREATE TABLE UsedCar (
    usedCarID int auto_increment,
    brand varchar(200),
    model varchar(200),
    engineSize double,
    horsePower int,
    numberOfKm double,
    yearOfManufacture int,
    chassisNumber varchar(200),
    price int,
    PRIMARY KEY (usedCarID)
);

CREATE TABLE UsedCarOwner (
    usedCarOwnerID int auto_increment,
    firstName varchar(255),
    lastName varchar(255),
    birthDay date,
    gender varchar(255),
    email varchar(255),
    address varchar(255),
    usedCarId int default null,
    PRIMARY KEY (usedCarOwnerID),
	FOREIGN KEY (usedCarId) REFERENCES UsedCar(usedCarID)
);

SELECT * FROM UsedCar;

SELECT * FROM UsedCarOwner;

DROP table UsedCarOwner;

DROP table UsedCar;


SELECT * FROM spring_used_car;
SELECT * FROM spring_used_car_owner;
SELECT * FROM spring_used_car_used_cars;

DROP table  spring_used_car;
DROP table  spring_used_car_owner;
DROP table spring_used_car_used_cars;
