INSERT INTO public.hotel(nom) VALUES 
('Aeroport'),
('hotel 1'),
('hotel 2');

INSERT INTO public.reservation(id_client,nb_passager ,date_heure_arrive,id_hotel) VALUES 
('CL1', 7, '2026-03-27 09:00:00', 'HTL000002'),
('CL2', 4, '2026-03-27 13:00:00', 'HTL000003');




INSERT INTO public.vehicule(reference, nb_place, type_carburant, heure_disponnibilite) VALUES
('V1', 5, 'D', '2026-03-27 00:00:00');

INSERT INTO public.token(token, date_expiration) VALUES 
('asrgbfwb43vvbqbgs9sgf', '2022-04-04 00:00:00');

INSERT INTO public.parametre(vitesse_moyenne, temps_attente) VALUES 
(50, 30);

INSERT INTO public.distance_hotel (hotel_depart, hotel_arrive, distance) VALUES
-- Aeroport Ivato
('HTL000001','HTL000002',90.0),
('HTL000001','HTL000003',35.0),
('HTL000002','HTL000003',60.0);

