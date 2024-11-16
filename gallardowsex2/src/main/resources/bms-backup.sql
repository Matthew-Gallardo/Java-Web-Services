--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

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
-- Name: boat_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.boat_id_seq
    START WITH 101
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.boat_id_seq OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: boat; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.boat (
    id integer DEFAULT nextval('public.boat_id_seq'::regclass) NOT NULL,
    name character varying(20) NOT NULL,
    colour character varying(20) NOT NULL
);


ALTER TABLE public.boat OWNER TO postgres;

--
-- Name: reservation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.reservation_id_seq OWNER TO postgres;

--
-- Name: reservation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reservation (
    id integer DEFAULT nextval('public.reservation_id_seq'::regclass) NOT NULL,
    sid integer NOT NULL,
    bid integer NOT NULL,
    date date NOT NULL
);


ALTER TABLE public.reservation OWNER TO postgres;

--
-- Name: sailor_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sailor_id_seq
    START WITH 22
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.sailor_id_seq OWNER TO postgres;

--
-- Name: sailor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sailor (
    id integer DEFAULT nextval('public.sailor_id_seq'::regclass) NOT NULL,
    name character varying(20) NOT NULL,
    rating integer NOT NULL,
    age integer NOT NULL
);


ALTER TABLE public.sailor OWNER TO postgres;

--
-- Data for Name: boat; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.boat (id, name, colour) FROM stdin;
101	Interlake	blue
102	Interlake	red
103	Clipper	green
104	Marine	red
105	Sunny	yello
106	Tune	white
107	Titanic	silver
108	Carpathia	silver
116	NEW BOAT	whitGSDe
117	BOOOAT	whitAE
118	Black Pearl	Black
\.


--
-- Data for Name: reservation; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reservation (id, sid, bid, date) FROM stdin;
1	22	101	2004-01-01
2	22	102	2004-01-01
3	22	103	2004-01-02
4	22	105	2004-01-02
5	31	103	2005-05-05
6	32	104	2005-04-07
17	22	101	2024-11-15
19	22	101	2024-11-16
20	23	102	2024-11-16
\.


--
-- Data for Name: sailor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sailor (id, name, rating, age) FROM stdin;
22	Dustin	7	45
23	Brutus	1	33
24	Lubber	79	65
25	Andy	25	25
26	Rusty	10	35
27	Buplb	10	35
28	Buplerb	10	35
29	Albert	10	35
30	James	10	35
31	Kulet	10	35
32	Matt	10	35
33	Migz	10	35
34	jamesz	10	29
35	jamesza	110	29
38	Popeye	69	29
39	Popeye	69	29
40	Lebron James	10	24
37	UpdatedName	9	30
\.


--
-- Name: boat_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.boat_id_seq', 118, true);


--
-- Name: reservation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reservation_id_seq', 20, true);


--
-- Name: sailor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sailor_id_seq', 40, true);


--
-- Name: boat boat_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.boat
    ADD CONSTRAINT boat_pkey PRIMARY KEY (id);


--
-- Name: reservation reservation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id, sid, bid, date);


--
-- Name: sailor sailor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sailor
    ADD CONSTRAINT sailor_pkey PRIMARY KEY (id);


--
-- Name: reservation reservation_bid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_bid_fkey FOREIGN KEY (bid) REFERENCES public.boat(id);


--
-- Name: reservation reservation_sid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_sid_fkey FOREIGN KEY (sid) REFERENCES public.sailor(id);


--
-- Name: TABLE boat; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.boat TO PUBLIC;


--
-- Name: TABLE reservation; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.reservation TO PUBLIC;


--
-- Name: TABLE sailor; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.sailor TO PUBLIC;


--
-- PostgreSQL database dump complete
--

