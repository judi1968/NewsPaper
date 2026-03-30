TRUNCATE TABLE 
    test.reservation,
    test.hotel,
    test.vehicule,
    test.token,
    test.parametre,
    test.distance_hotel
RESTART IDENTITY CASCADE;

-- Script de réinitialisation des séquences à 1
ALTER SEQUENCE test.seq_hotel RESTART WITH 1;
ALTER SEQUENCE test.seq_reservation RESTART WITH 1;
ALTER SEQUENCE test.seq_vehicule RESTART WITH 1;
ALTER SEQUENCE test.seq_token RESTART WITH 1;
ALTER SEQUENCE test.seq_parametre RESTART WITH 1;
ALTER SEQUENCE test.seq_distance_hotel RESTART WITH 1;