CREATE TABLE parking_details(
   parking_name VARCHAR (255) PRIMARY KEY,
   parking_address VARCHAR(1000),
   latitude DOUBLE,
   longitude DOUBLE
);

INSERT INTO parking_details  VALUES ()

CREATE TABLE car_park_info(
   car_park_number VARCHAR (255),
   lot_type CHAR,
   total_lots int ,
   lots_available int,
   update_datetime TIMESTAMP,
   PRIMARY key (car_park_number,lot_type)
);


INSERT INTO car_park_info (car_park_number,lot_type,total_lots,lots_available,update_datetime) VALUES ('HE12','C',91,1,'2019-12-07 12:24:37') ON CONFLICT(car_park_number,lot_type) DO UPDATE SET car_park_number=EXCLUDED.car_park_number,lot_type=EXCLUDED.lot_type