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

--
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
-- Name: longueur_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.longueur_id_seq OWNED BY public.longueur.id;


--
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
-- Name: reservation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.reservation_id_seq OWNED BY public.reservation.id;


--
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
-- Name: topo_spot; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.topo_spot (
    topo_id bigint NOT NULL,
    spot_id bigint NOT NULL
);


ALTER TABLE public.topo_spot OWNER TO postgres;

--
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
-- Name: voie_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.voie_id_seq OWNED BY public.voie.id;


--
-- Name: longueur id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.longueur ALTER COLUMN id SET DEFAULT nextval('public.longueur_id_seq'::regclass);


--
-- Name: reservation id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation ALTER COLUMN id SET DEFAULT nextval('public.reservation_id_seq'::regclass);


--
-- Name: voie id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.voie ALTER COLUMN id SET DEFAULT nextval('public.voie_id_seq'::regclass);


--
-- Name: commentaire commentaire_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.commentaire
    ADD CONSTRAINT commentaire_pkey PRIMARY KEY (id);


--
-- Name: longueur longueur_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.longueur
    ADD CONSTRAINT longueur_pkey PRIMARY KEY (id);


--
-- Name: reservation reservation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);


--
-- Name: spot spot_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.spot
    ADD CONSTRAINT spot_pkey PRIMARY KEY (id);


--
-- Name: topo topo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topo
    ADD CONSTRAINT topo_pkey PRIMARY KEY (id);


--
-- Name: utilisateur uk_kq7nt5wyq9v9lpcpgxag2f24a; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT uk_kq7nt5wyq9v9lpcpgxag2f24a UNIQUE (username);


--
-- Name: utilisateur uk_rma38wvnqfaf66vvmi57c71lo; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT uk_rma38wvnqfaf66vvmi57c71lo UNIQUE (email);


--
-- Name: utilisateur utilisateur_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT utilisateur_pkey PRIMARY KEY (id);


--
-- Name: voie voie_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.voie
    ADD CONSTRAINT voie_pkey PRIMARY KEY (id);


--
-- Name: topo_spot fk2f3903v9cqhpfmex7ql87qgad; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topo_spot
    ADD CONSTRAINT fk2f3903v9cqhpfmex7ql87qgad FOREIGN KEY (topo_id) REFERENCES public.topo(id);


--
-- Name: spot fk2fdnqy09c2cvvixv8i2dj7tvh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.spot
    ADD CONSTRAINT fk2fdnqy09c2cvvixv8i2dj7tvh FOREIGN KEY (utilisateur_id) REFERENCES public.utilisateur(id);


--
-- Name: topo fk48p5uq6ge8lif1qj7drdap2kk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topo
    ADD CONSTRAINT fk48p5uq6ge8lif1qj7drdap2kk FOREIGN KEY (reservation_id) REFERENCES public.reservation(id);


--
-- Name: topo fk7w124lr4xukv1ttq413hrsq60; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topo
    ADD CONSTRAINT fk7w124lr4xukv1ttq413hrsq60 FOREIGN KEY (utilisateur_id) REFERENCES public.utilisateur(id);


--
-- Name: commentaire fkd12gwjgq8e78sbx2iprcrtq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.commentaire
    ADD CONSTRAINT fkd12gwjgq8e78sbx2iprcrtq FOREIGN KEY (spot_id) REFERENCES public.spot(id);


--
-- Name: reservation fkdtefjmlql0smcks7gqv9oshih; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT fkdtefjmlql0smcks7gqv9oshih FOREIGN KEY (locataire_id) REFERENCES public.utilisateur(id);


--
-- Name: utilisateur fkel7qnw157ci3rriionr7hnfu6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT fkel7qnw157ci3rriionr7hnfu6 FOREIGN KEY (reservation_id) REFERENCES public.reservation(id);


--
-- Name: commentaire fkfkx1pegfdsd6e3cp2wblsc5jf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.commentaire
    ADD CONSTRAINT fkfkx1pegfdsd6e3cp2wblsc5jf FOREIGN KEY (utilisateur_id) REFERENCES public.utilisateur(id);


--
-- Name: topo_spot fkgucsi9i2wct3hekvdqsdcsf6q; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topo_spot
    ADD CONSTRAINT fkgucsi9i2wct3hekvdqsdcsf6q FOREIGN KEY (spot_id) REFERENCES public.spot(id);


--
-- Name: voie fko9233cj3te27pfgas94f7h7lm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.voie
    ADD CONSTRAINT fko9233cj3te27pfgas94f7h7lm FOREIGN KEY (spot_id) REFERENCES public.spot(id);


--
-- Name: longueur fkro1y7gu1g630s7j7vaiksn6s5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.longueur
    ADD CONSTRAINT fkro1y7gu1g630s7j7vaiksn6s5 FOREIGN KEY (voie_id) REFERENCES public.voie(id);


--
-- Name: reservation fks6rhp8bjbn9p9imq1k5fcpb6a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT fks6rhp8bjbn9p9imq1k5fcpb6a FOREIGN KEY (topo_id) REFERENCES public.topo(id);


--
-- PostgreSQL database dump complete
--

