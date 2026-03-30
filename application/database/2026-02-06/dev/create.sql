CREATE DATABASE gestion_ticketing;
\c gestion_ticketing;

CREATE OR REPLACE FUNCTION public.custom_seq(in_prefix character varying, in_sequence_name character varying, in_digit_count integer)
    RETURNS character varying
    LANGUAGE plpgsql
    AS $function$
    DECLARE
        seq_value INT;
        result VARCHAR;
    BEGIN
        EXECUTE 'SELECT nextval(''' || in_sequence_name || '''::regclass)' INTO seq_value;
        result := in_prefix || LPAD(seq_value::TEXT, in_digit_count, '0');
    RETURN result;
    END;
    $function$;


-- Création des séquences
CREATE SEQUENCE public.seq_reservation;
CREATE SEQUENCE public.seq_hotel;
CREATE SEQUENCE public.seq_vehicule;
CREATE SEQUENCE public.seq_token;
CREATE SEQUENCE public.seq_parametre;
CREATE SEQUENCE public.seq_distance_hotel;


CREATE TABLE public.hotel(
    id VARCHAR PRIMARY KEY DEFAULT custom_seq(
        'HTL' :: character varying,
        'seq_hotel' :: character varying,
        6
    ) NOT NULL,
    nom VARCHAR(255)
);

CREATE TABLE public.reservation(
    id VARCHAR PRIMARY KEY DEFAULT custom_seq(
        'RES' :: character varying,
        'seq_reservation' :: character varying,
        6
    ) NOT NULL,
    id_client VARCHAR (4) NOT NULL,
    nb_passager integer NOT NULL,
    date_heure_arrive timestamp NOT NULL,
    id_hotel VARCHAR (255) NOT NULL,
    CONSTRAINT id_hotel_fk FOREIGN KEY ( id_hotel ) REFERENCES public.hotel( id )
);

CREATE TABLE public.vehicule(
    id VARCHAR PRIMARY KEY DEFAULT custom_seq(
        'VHC' :: character varying,
        'seq_vehicule' :: character varying,
        6
    ) NOT NULL,
    reference VARCHAR (255) NOT NULL,
    nb_place integer NOT NULL,
    type_carburant VARCHAR(255) NOT NULL,
    date_delete timestamp
);
ALTER TABLE public.vehicule ADD heure_disponnibilite timestamp;

CREATE TABLE public.token(
    id VARCHAR PRIMARY KEY DEFAULT custom_seq(
        'TKN' :: character varying,
        'seq_token' :: character varying,
        6
    ) NOT NULL,
    token VARCHAR (255) NOT NULL,
    date_expiration timestamp
);

CREATE TABLE public.parametre(
    id VARCHAR PRIMARY KEY DEFAULT custom_seq(
        'PARAM' :: character varying,
        'seq_parametre' :: character varying,
        6
    ) NOT NULL,
    vitesse_moyenne double precision,
    temps_attente double precision -- en seconde
);

CREATE TABLE public.distance_hotel(
    id VARCHAR PRIMARY KEY DEFAULT custom_seq(
        'PARAM' :: character varying,
        'seq_distance_hotel' :: character varying,
        6
    ) NOT NULL,
    hotel_depart VARCHAR (255) NOT NULL,
    hotel_arrive VARCHAR (255) NOT NULL,
    distance double precision NOT NULL,
    CONSTRAINT id_hotel_depart_fk FOREIGN KEY ( hotel_depart ) REFERENCES public.hotel( id ),
    CONSTRAINT id_hotel_arrive_fk FOREIGN KEY ( hotel_arrive ) REFERENCES public.hotel( id )
);