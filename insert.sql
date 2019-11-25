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

--
-- Data for Name: reservation; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.reservation (id, date_debut_demandee, date_fin_demandee, date_parution, statut, locataire_id, topo_id) VALUES (2, '2019-12-26 00:00:00', '2019-12-27 00:00:00', '2019-11-25 20:05:09.008458', 'En attente', 1, 9);
INSERT INTO public.reservation (id, date_debut_demandee, date_fin_demandee, date_parution, statut, locataire_id, topo_id) VALUES (1, '2019-12-22 00:00:00', '2019-12-24 00:00:00', '2019-11-25 20:04:40.482277', 'Acceptée', 1, 13);


--
-- Data for Name: utilisateur; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.utilisateur (id, email, password, staff, username, reservation_id) VALUES (2, 'user@gmail.com', '$2a$10$7eHCzS73D2tXsyHXsXQa/.CKwyUMqKJrdR4rIUyQ4ALCYqC7rORue', false, 'user', NULL);
INSERT INTO public.utilisateur (id, email, password, staff, username, reservation_id) VALUES (1, 'axel.allain.a@gmail.com', '$2a$10$P7MTLmw3II4E00LP4jGC3ewzr6DhiMk5XcLkhf12GDpLn8Bg/lph6', true, 'admin', NULL);


--
-- Data for Name: spot; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.spot (id, cotationmax, cotationmin, date_parution, description, image_url, lieu, nom, officiel, utilisateur_id) VALUES (6, '3a', '2a', '2019-11-25 19:26:41.830366', 'Description du spot un', 'http://image.noelshack.com/fichiers/2019/46/5/1573779764-spot.jpg', 'Cherbourg', 'Spot un', false, 1);
INSERT INTO public.spot (id, cotationmax, cotationmin, date_parution, description, image_url, lieu, nom, officiel, utilisateur_id) VALUES (11, '3a', '2a', '2019-11-25 19:29:20.500534', 'Description du spot cinq', 'http://image.noelshack.com/fichiers/2019/46/5/1573779764-spot.jpg', 'Rennes', 'Spot cinq', false, 1);
INSERT INTO public.spot (id, cotationmax, cotationmin, date_parution, description, image_url, lieu, nom, officiel, utilisateur_id) VALUES (12, '3a', '2a', '2019-11-25 19:29:33.228662', 'Description du spot six', 'http://image.noelshack.com/fichiers/2019/46/5/1573779764-spot.jpg', 'Marseille', 'Spot six', true, 1);
INSERT INTO public.spot (id, cotationmax, cotationmin, date_parution, description, image_url, lieu, nom, officiel, utilisateur_id) VALUES (8, '3a', '2a', '2019-11-25 19:27:23.405153', 'Description du spot trois', 'http://image.noelshack.com/fichiers/2019/46/5/1573779764-spot.jpg', 'Rouen', 'Spot trois', true, 1);
INSERT INTO public.spot (id, cotationmax, cotationmin, date_parution, description, image_url, lieu, nom, officiel, utilisateur_id) VALUES (10, '3a', '2a', '2019-11-25 19:28:43.241712', 'Description du spot quatre', 'http://image.noelshack.com/fichiers/2019/46/5/1573779764-spot.jpg', 'Paris', 'Spot quatre', true, 1);
INSERT INTO public.spot (id, cotationmax, cotationmin, date_parution, description, image_url, lieu, nom, officiel, utilisateur_id) VALUES (7, '4c', '4c', '2019-11-25 19:26:55.913316', 'Description du spot deux', 'http://image.noelshack.com/fichiers/2019/46/5/1573779764-spot.jpg', 'Caen', 'Spot deux', false, 1);


--
-- Data for Name: commentaire; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.commentaire (id, date_parution, message, spot_id, utilisateur_id) VALUES (14, '2019-11-25 20:14:54.520383', 'Ce spot est vraiment génial !', 12, 1);
INSERT INTO public.commentaire (id, date_parution, message, spot_id, utilisateur_id) VALUES (15, '2019-11-25 20:15:06.690977', 'Je le recommande fortement.', 12, 1);
INSERT INTO public.commentaire (id, date_parution, message, spot_id, utilisateur_id) VALUES (16, '2019-11-25 20:15:54.574583', 'Spot sympathique avec ses deux voies, de bonnes cotations pour évoluer', 6, 1);


--
-- Data for Name: voie; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.voie (id, cotationmax, cotationmin, nom, spot_id) VALUES (1, '3a', '2a', 'Voie A', 6);
INSERT INTO public.voie (id, cotationmax, cotationmin, nom, spot_id) VALUES (2, '2b', '2b', 'Voie B', 6);
INSERT INTO public.voie (id, cotationmax, cotationmin, nom, spot_id) VALUES (3, '4c', '4c', 'Voie A', 7);


--
-- Data for Name: longueur; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.longueur (id, cotation, nom, voie_id) VALUES (1, '2a', 'Longueur A', 1);
INSERT INTO public.longueur (id, cotation, nom, voie_id) VALUES (2, '3a', 'Longueur B', 1);
INSERT INTO public.longueur (id, cotation, nom, voie_id) VALUES (3, '2b', 'Longueur A', 2);
INSERT INTO public.longueur (id, cotation, nom, voie_id) VALUES (4, '4c', 'Longueur A', 3);


--
-- Data for Name: topo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.topo (id, date_parution, description, disponible, image_url, lieu, nom, reservation_id, utilisateur_id) VALUES (9, '2019-11-25 19:28:00.871632', 'Description du topo de la Normandie', true, 'http://image.noelshack.com/fichiers/2019/46/5/1573779764-spot.jpg', 'Normandie', 'Topo de la Normandie', NULL, 1);
INSERT INTO public.topo (id, date_parution, description, disponible, image_url, lieu, nom, reservation_id, utilisateur_id) VALUES (13, '2019-11-25 19:30:06.080367', 'Description du topo de la France', false, 'http://image.noelshack.com/fichiers/2019/46/5/1573779764-spot.jpg', 'France', 'Topo de la France', NULL, 1);


--
-- Data for Name: topo_spot; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.topo_spot (topo_id, spot_id) VALUES (9, 6);
INSERT INTO public.topo_spot (topo_id, spot_id) VALUES (9, 7);
INSERT INTO public.topo_spot (topo_id, spot_id) VALUES (9, 8);
INSERT INTO public.topo_spot (topo_id, spot_id) VALUES (13, 10);
INSERT INTO public.topo_spot (topo_id, spot_id) VALUES (13, 11);
INSERT INTO public.topo_spot (topo_id, spot_id) VALUES (13, 12);


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 16, true);


--
-- Name: longueur_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.longueur_id_seq', 4, true);


--
-- Name: reservation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reservation_id_seq', 2, true);


--
-- Name: voie_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.voie_id_seq', 3, true);


--
-- PostgreSQL database dump complete
--

