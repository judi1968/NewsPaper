TRUNCATE TABLE 
    public.reservation,
    public.hotel,
    public.vehicule,
    public.token,
    public.parametre,
    public.distance_hotel
RESTART IDENTITY CASCADE;

-- Script de réinitialisation des séquences à 1
ALTER SEQUENCE public.seq_hotel RESTART WITH 1;
ALTER SEQUENCE public.seq_reservation RESTART WITH 1;
ALTER SEQUENCE public.seq_vehicule RESTART WITH 1;
ALTER SEQUENCE public.seq_token RESTART WITH 1;
ALTER SEQUENCE public.seq_parametre RESTART WITH 1;
ALTER SEQUENCE public.seq_distance_hotel RESTART WITH 1;
