BEGIN TRANSACTION;
CREATE TABLE user_information (
    iduser INTEGER PRIMARY KEY AUTOINCREMENT,
    user_name text DEFAULT '' NOT NULL,
    device_key text DEFAULT '' NOT NULL,
    user_second_name text DEFAULT '' NOT NULL,
    user_mail text NOT NULL,
    user_password text DEFAULT '' NOT NULL,
    user_station_default integer NOT NULL,
    alarm_weekdays text DEFAULT '' NOT NULL,
    alarm_time time without time zone
);
INSERT INTO user_information(iduser,user_name,user_mail,user_station_default,alarm_time) VALUES (-1,'Guest','guest@allergyiap.com',-1,'0:00');

CREATE TABLE user_allergies (
    id_user integer NOT NULL,
    id_allergy integer NOT NULL,
		PRIMARY KEY (id_user, id_allergy)
);
CREATE TABLE station (
    idstation  INTEGER PRIMARY KEY AUTOINCREMENT,
    name_station text DEFAULT '' NOT NULL,
    latitude real NOT NULL,
    longitude real NOT NULL
);
CREATE TABLE relation_pharmacies_customers (
    id_pharmacy integer NOT NULL,
    id_customer integer NOT NULL,
		PRIMARY KEY (id_pharmacy, id_customer)
);
CREATE TABLE product_catalog (
    idproduct_catalog INTEGER PRIMARY KEY AUTOINCREMENT,
    allergy_idallergy integer NOT NULL,
    customer_idcustomer integer NOT NULL,
    product_name text,
    product_description text,
    product_url_image text
);
CREATE TABLE pharmacy (
    id_pharmacy INTEGER PRIMARY KEY AUTOINCREMENT,
    name_pharmacy text DEFAULT '' NOT NULL,
    latitude real NOT NULL,
    longitude real NOT NULL
);
CREATE TABLE customer (
    idcustomer INTEGER PRIMARY KEY AUTOINCREMENT,
    company_name text DEFAULT '' NOT NULL,
    url_logo text DEFAULT '' NOT NULL,
    company_description text DEFAULT '' NOT NULL
);
CREATE TABLE allergy_level (
    idallergy_level INTEGER PRIMARY KEY AUTOINCREMENT,
    allergy_idallergy integer NOT NULL,
    current_level double precision,
    station text NOT NULL,
    date_start date,
    date_end date,
    forecast_level text
);
CREATE TABLE allergy (
    idallergy INTEGER PRIMARY KEY AUTOINCREMENT,
    allergy_name text,
    allergy_description text,
    allergy_code text
);

INSERT INTO station VALUES (-1,'Default',0,0);

CREATE TABLE IF NOT EXISTS data_version (iddata_version INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT , last_update DATE);

COMMIT;
