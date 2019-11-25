--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.5

-- Started on 2019-11-25 20:22:12 CET

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
-- TOC entry 3251 (class 1262 OID 20045)
-- Name: amisescalade; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE amisescalade WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


ALTER DATABASE amisescalade OWNER TO postgres;

\connect amisescalade

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

--
-- TOC entry 197 (class 1259 OID 20048)
-- Name: commentaire; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.commentaire (
    id bigint NOT NULL,
    date_parution timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    message character varying(255) NOT NULL,
    spot_id bigint,
    utilisateur_id bigint
);


ALTER TABLE public.commentaire OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 20046)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 20056)
-- Name: longueur; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.longueur (
    id bigint NOT NULL,
    cotation character varying(255),
    nom character varying(255) NOT NULL,
    voie_id bigint
);


ALTER TABLE public.longueur OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 20054)
-- Name: longueur_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.longueur_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.longueur_id_seq OWNER TO postgres;

--
-- TOC entry 3252 (class 0 OID 0)
-- Dependencies: 198
-- Name: longueur_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.longueur_id_seq OWNED BY public.longueur.id;


--
-- TOC entry 201 (class 1259 OID 20067)
-- Name: reservation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reservation (
    id bigint NOT NULL,
    date_debut_demandee timestamp without time zone,
    date_fin_demandee timestamp without time zone,
    date_parution timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    statut character varying(255),
    locataire_id bigint,
    topo_id bigint
);


ALTER TABLE public.reservation OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 20065)
-- Name: reservation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reservation_id_seq OWNER TO postgres;

--
-- TOC entry 3253 (class 0 OID 0)
-- Dependencies: 200
-- Name: reservation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.reservation_id_seq OWNED BY public.reservation.id;


--
-- TOC entry 202 (class 1259 OID 20074)
-- Name: spot; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.spot (
    id bigint NOT NULL,
    cotationmax character varying(2),
    cotationmin character varying(2),
    date_parution timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    description character varying(250) NOT NULL,
    image_url character varying(255),
    lieu character varying(50) NOT NULL,
    nom character varying(50) NOT NULL,
    officiel boolean,
    utilisateur_id bigint
);


ALTER TABLE public.spot OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 20083)
-- Name: topo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.topo (
    id bigint NOT NULL,
    date_parution timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    description character varying(250) NOT NULL,
    disponible boolean,
    image_url character varying(255),
    lieu character varying(50) NOT NULL,
    nom character varying(50) NOT NULL,
    reservation_id bigint,
    utilisateur_id bigint
);


ALTER TABLE public.topo OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 20092)
-- Name: topo_spot; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.topo_spot (
    topo_id bigint NOT NULL,
    spot_id bigint NOT NULL
);


ALTER TABLE public.topo_spot OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 20095)
-- Name: utilisateur; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.utilisateur (
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    staff boolean NOT NULL,
    username character varying(12) NOT NULL,
    reservation_id bigint
);


ALTER TABLE public.utilisateur OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 20105)
-- Name: voie; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.voie (
    id bigint NOT NULL,
    cotationmax character varying(255),
    cotationmin character varying(255),
    nom character varying(255) NOT NULL,
    spot_id bigint
);


ALTER TABLE public.voie OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 20103)
-- Name: voie_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.voie_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.voie_id_seq OWNER TO postgres;

--
-- TOC entry 3254 (class 0 OID 0)
-- Dependencies: 206
-- Name: voie_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.voie_id_seq OWNED BY public.voie.id;


--
-- TOC entry 3077 (class 2604 OID 20059)
-- Name: longueur id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.longueur ALTER COLUMN id SET DEFAULT nextval('public.longueur_id_seq'::regclass);


--
-- TOC entry 3078 (class 2604 OID 20070)
-- Name: reservation id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation ALTER COLUMN id SET DEFAULT nextval('public.reservation_id_seq'::regclass);


--
-- TOC entry 3082 (class 2604 OID 20108)
-- Name: voie id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.voie ALTER COLUMN id SET DEFAULT nextval('public.voie_id_seq'::regclass);


--
-- TOC entry 3235 (class 0 OID 20048)
-- Dependencies: 197
-- Data for Name: commentaire; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.commentaire (id, date_parution, message, spot_id, utilisateur_id) FROM stdin;
14	2019-11-25 20:14:54.520383	Ce spot est vraiment génial !	12	1
15	2019-11-25 20:15:06.690977	Je le recommande fortement.	12	1
16	2019-11-25 20:15:54.574583	Spot sympathique avec ses deux voies, de bonnes cotations pour évoluer	6	1
\.


--
-- TOC entry 3237 (class 0 OID 20056)
-- Dependencies: 199
-- Data for Name: longueur; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.longueur (id, cotation, nom, voie_id) FROM stdin;
1	2a	Longueur A	1
2	3a	Longueur B	1
3	2b	Longueur A	2
4	4c	Longueur A	3
\.


--
-- TOC entry 3239 (class 0 OID 20067)
-- Dependencies: 201
-- Data for Name: reservation; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reservation (id, date_debut_demandee, date_fin_demandee, date_parution, statut, locataire_id, topo_id) FROM stdin;
2	2019-12-26 00:00:00	2019-12-27 00:00:00	2019-11-25 20:05:09.008458	En attente	1	9
1	2019-12-22 00:00:00	2019-12-24 00:00:00	2019-11-25 20:04:40.482277	Acceptée	1	13
\.


--
-- TOC entry 3240 (class 0 OID 20074)
-- Dependencies: 202
-- Data for Name: spot; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.spot (id, cotationmax, cotationmin, date_parution, description, image_url, lieu, nom, officiel, utilisateur_id) FROM stdin;
6	3a	2a	2019-11-25 19:26:41.830366	Description du spot un	http://image.noelshack.com/fichiers/2019/46/5/1573779764-spot.jpg	Cherbourg	Spot un	f	1
11	3a	2a	2019-11-25 19:29:20.500534	Description du spot cinq	http://image.noelshack.com/fichiers/2019/46/5/1573779764-spot.jpg	Rennes	Spot cinq	f	1
12	3a	2a	2019-11-25 19:29:33.228662	Description du spot six	http://image.noelshack.com/fichiers/2019/46/5/1573779764-spot.jpg	Marseille	Spot six	t	1
8	3a	2a	2019-11-25 19:27:23.405153	Description du spot trois	http://image.noelshack.com/fichiers/2019/46/5/1573779764-spot.jpg	Rouen	Spot trois	t	1
10	3a	2a	2019-11-25 19:28:43.241712	Description du spot quatre	http://image.noelshack.com/fichiers/2019/46/5/1573779764-spot.jpg	Paris	Spot quatre	t	1
7	4c	4c	2019-11-25 19:26:55.913316	Description du spot deux	http://image.noelshack.com/fichiers/2019/46/5/1573779764-spot.jpg	Caen	Spot deux	f	1
\.


--
-- TOC entry 3241 (class 0 OID 20083)
-- Dependencies: 203
-- Data for Name: topo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.topo (id, date_parution, description, disponible, image_url, lieu, nom, reservation_id, utilisateur_id) FROM stdin;
9	2019-11-25 19:28:00.871632	Description du topo de la Normandie	t	http://image.noelshack.com/fichiers/2019/46/5/1573779764-spot.jpg	Normandie	Topo de la Normandie	\N	1
13	2019-11-25 19:30:06.080367	Description du topo de la France	f	http://image.noelshack.com/fichiers/2019/46/5/1573779764-spot.jpg	France	Topo de la France	\N	1
\.


--
-- TOC entry 3242 (class 0 OID 20092)
-- Dependencies: 204
-- Data for Name: topo_spot; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.topo_spot (topo_id, spot_id) FROM stdin;
9	6
9	7
9	8
13	10
13	11
13	12
\.


--
-- TOC entry 3243 (class 0 OID 20095)
-- Dependencies: 205
-- Data for Name: utilisateur; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.utilisateur (id, email, password, staff, username, reservation_id) FROM stdin;
2	user@gmail.com	$2a$10$7eHCzS73D2tXsyHXsXQa/.CKwyUMqKJrdR4rIUyQ4ALCYqC7rORue	f	user	\N
1	axel.allain.a@gmail.com	$2a$10$P7MTLmw3II4E00LP4jGC3ewzr6DhiMk5XcLkhf12GDpLn8Bg/lph6	t	admin	\N
\.


--
-- TOC entry 3245 (class 0 OID 20105)
-- Dependencies: 207
-- Data for Name: voie; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.voie (id, cotationmax, cotationmin, nom, spot_id) FROM stdin;
1	3a	2a	Voie A	6
2	2b	2b	Voie B	6
3	4c	4c	Voie A	7
\.


--
-- TOC entry 3255 (class 0 OID 0)
-- Dependencies: 196
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 16, true);


--
-- TOC entry 3256 (class 0 OID 0)
-- Dependencies: 198
-- Name: longueur_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.longueur_id_seq', 4, true);


--
-- TOC entry 3257 (class 0 OID 0)
-- Dependencies: 200
-- Name: reservation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reservation_id_seq', 2, true);


--
-- TOC entry 3258 (class 0 OID 0)
-- Dependencies: 206
-- Name: voie_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.voie_id_seq', 3, true);


--
-- TOC entry 3084 (class 2606 OID 20053)
-- Name: commentaire commentaire_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.commentaire
    ADD CONSTRAINT commentaire_pkey PRIMARY KEY (id);


--
-- TOC entry 3086 (class 2606 OID 20064)
-- Name: longueur longueur_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.longueur
    ADD CONSTRAINT longueur_pkey PRIMARY KEY (id);


--
-- TOC entry 3088 (class 2606 OID 20073)
-- Name: reservation reservation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);


--
-- TOC entry 3090 (class 2606 OID 20082)
-- Name: spot spot_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.spot
    ADD CONSTRAINT spot_pkey PRIMARY KEY (id);


--
-- TOC entry 3092 (class 2606 OID 20091)
-- Name: topo topo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topo
    ADD CONSTRAINT topo_pkey PRIMARY KEY (id);


--
-- TOC entry 3094 (class 2606 OID 20117)
-- Name: utilisateur uk_kq7nt5wyq9v9lpcpgxag2f24a; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT uk_kq7nt5wyq9v9lpcpgxag2f24a UNIQUE (username);


--
-- TOC entry 3096 (class 2606 OID 20115)
-- Name: utilisateur uk_rma38wvnqfaf66vvmi57c71lo; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT uk_rma38wvnqfaf66vvmi57c71lo UNIQUE (email);


--
-- TOC entry 3098 (class 2606 OID 20102)
-- Name: utilisateur utilisateur_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT utilisateur_pkey PRIMARY KEY (id);


--
-- TOC entry 3100 (class 2606 OID 20113)
-- Name: voie voie_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.voie
    ADD CONSTRAINT voie_pkey PRIMARY KEY (id);


--
-- TOC entry 3110 (class 2606 OID 20163)
-- Name: topo_spot fk2f3903v9cqhpfmex7ql87qgad; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topo_spot
    ADD CONSTRAINT fk2f3903v9cqhpfmex7ql87qgad FOREIGN KEY (topo_id) REFERENCES public.topo(id);


--
-- TOC entry 3106 (class 2606 OID 20143)
-- Name: spot fk2fdnqy09c2cvvixv8i2dj7tvh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.spot
    ADD CONSTRAINT fk2fdnqy09c2cvvixv8i2dj7tvh FOREIGN KEY (utilisateur_id) REFERENCES public.utilisateur(id);


--
-- TOC entry 3107 (class 2606 OID 20148)
-- Name: topo fk48p5uq6ge8lif1qj7drdap2kk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topo
    ADD CONSTRAINT fk48p5uq6ge8lif1qj7drdap2kk FOREIGN KEY (reservation_id) REFERENCES public.reservation(id);


--
-- TOC entry 3108 (class 2606 OID 20153)
-- Name: topo fk7w124lr4xukv1ttq413hrsq60; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topo
    ADD CONSTRAINT fk7w124lr4xukv1ttq413hrsq60 FOREIGN KEY (utilisateur_id) REFERENCES public.utilisateur(id);


--
-- TOC entry 3101 (class 2606 OID 20118)
-- Name: commentaire fkd12gwjgq8e78sbx2iprcrtq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.commentaire
    ADD CONSTRAINT fkd12gwjgq8e78sbx2iprcrtq FOREIGN KEY (spot_id) REFERENCES public.spot(id);


--
-- TOC entry 3104 (class 2606 OID 20133)
-- Name: reservation fkdtefjmlql0smcks7gqv9oshih; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT fkdtefjmlql0smcks7gqv9oshih FOREIGN KEY (locataire_id) REFERENCES public.utilisateur(id);


--
-- TOC entry 3111 (class 2606 OID 20168)
-- Name: utilisateur fkel7qnw157ci3rriionr7hnfu6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT fkel7qnw157ci3rriionr7hnfu6 FOREIGN KEY (reservation_id) REFERENCES public.reservation(id);


--
-- TOC entry 3102 (class 2606 OID 20123)
-- Name: commentaire fkfkx1pegfdsd6e3cp2wblsc5jf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.commentaire
    ADD CONSTRAINT fkfkx1pegfdsd6e3cp2wblsc5jf FOREIGN KEY (utilisateur_id) REFERENCES public.utilisateur(id);


--
-- TOC entry 3109 (class 2606 OID 20158)
-- Name: topo_spot fkgucsi9i2wct3hekvdqsdcsf6q; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topo_spot
    ADD CONSTRAINT fkgucsi9i2wct3hekvdqsdcsf6q FOREIGN KEY (spot_id) REFERENCES public.spot(id);


--
-- TOC entry 3112 (class 2606 OID 20173)
-- Name: voie fko9233cj3te27pfgas94f7h7lm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.voie
    ADD CONSTRAINT fko9233cj3te27pfgas94f7h7lm FOREIGN KEY (spot_id) REFERENCES public.spot(id);


--
-- TOC entry 3103 (class 2606 OID 20128)
-- Name: longueur fkro1y7gu1g630s7j7vaiksn6s5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.longueur
    ADD CONSTRAINT fkro1y7gu1g630s7j7vaiksn6s5 FOREIGN KEY (voie_id) REFERENCES public.voie(id);


--
-- TOC entry 3105 (class 2606 OID 20138)
-- Name: reservation fks6rhp8bjbn9p9imq1k5fcpb6a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT fks6rhp8bjbn9p9imq1k5fcpb6a FOREIGN KEY (topo_id) REFERENCES public.topo(id);


-- Completed on 2019-11-25 20:22:12 CET

--
-- PostgreSQL database dump complete
--

