CREATE DATABASE mydb;
\c mydb;

--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.5

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;


CREATE TABLE public.parking_details(
   parking_name VARCHAR (255) PRIMARY KEY,
   parking_address VARCHAR(1000),
   latitude numeric,
   longitude numeric
);

CREATE TABLE public.car_park_info(
   car_park_number VARCHAR (255),
   lot_type CHAR,
   total_lots int ,
   lots_available int,
   update_datetime TIMESTAMP,
   PRIMARY key (car_park_number,lot_type)
);

