--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4
-- Dumped by pg_dump version 17.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: event; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.event (
    id_event bigint NOT NULL,
    nama_event character varying(255) NOT NULL,
    harga_event integer NOT NULL,
    jadwal_event timestamp without time zone NOT NULL,
    jadwal_event_day character varying(255) NOT NULL,
    foto_event character varying(255) NOT NULL,
    deskripsi_event character varying(255) NOT NULL,
    kuota integer NOT NULL,
    kategori character varying(255) NOT NULL
);


ALTER TABLE public.event OWNER TO postgres;

--
-- Name: event_id_event_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.event_id_event_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.event_id_event_seq OWNER TO postgres;

--
-- Name: event_id_event_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.event_id_event_seq OWNED BY public.event.id_event;


--
-- Name: pelanggan; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pelanggan (
    id_pelanggan bigint NOT NULL,
    nama_pelanggan character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    no_hp_pelanggan character varying(255) NOT NULL,
    token character varying(255),
    tokenexpiredat bigint,
    token_expired_at bigint
);


ALTER TABLE public.pelanggan OWNER TO postgres;

--
-- Name: pelanggan_id_pelanggan_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pelanggan_id_pelanggan_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.pelanggan_id_pelanggan_seq OWNER TO postgres;

--
-- Name: pelanggan_id_pelanggan_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pelanggan_id_pelanggan_seq OWNED BY public.pelanggan.id_pelanggan;


--
-- Name: pembayaran; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pembayaran (
    id_pembayaran bigint NOT NULL,
    id_pembelian bigint NOT NULL,
    nama_pelanggan character varying(255) NOT NULL,
    bank character varying(255) NOT NULL,
    total_harga integer NOT NULL,
    tanggal_bayar character varying(255) NOT NULL
);


ALTER TABLE public.pembayaran OWNER TO postgres;

--
-- Name: pembayaran_id_pembayaran_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pembayaran_id_pembayaran_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.pembayaran_id_pembayaran_seq OWNER TO postgres;

--
-- Name: pembayaran_id_pembayaran_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pembayaran_id_pembayaran_seq OWNED BY public.pembayaran.id_pembayaran;


--
-- Name: pembelian; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pembelian (
    id_pembelian bigint NOT NULL,
    id_pelanggan bigint NOT NULL,
    tanggal_pembelian timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    total_pembelian integer NOT NULL,
    status_pembelian character varying(255) DEFAULT 'Pending'::character varying NOT NULL
);


ALTER TABLE public.pembelian OWNER TO postgres;

--
-- Name: pembelian_event; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pembelian_event (
    id_pembelian_event bigint NOT NULL,
    id_pembelian bigint NOT NULL,
    id_event bigint NOT NULL,
    jumlah integer NOT NULL,
    nama_event character varying(255) NOT NULL,
    kategori character varying(255) NOT NULL,
    harga_total integer NOT NULL
);


ALTER TABLE public.pembelian_event OWNER TO postgres;

--
-- Name: pembelian_event_id_pembelian_event_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pembelian_event_id_pembelian_event_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.pembelian_event_id_pembelian_event_seq OWNER TO postgres;

--
-- Name: pembelian_event_id_pembelian_event_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pembelian_event_id_pembelian_event_seq OWNED BY public.pembelian_event.id_pembelian_event;


--
-- Name: pembelian_id_pembelian_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pembelian_id_pembelian_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.pembelian_id_pembelian_seq OWNER TO postgres;

--
-- Name: pembelian_id_pembelian_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pembelian_id_pembelian_seq OWNED BY public.pembelian.id_pembelian;


--
-- Name: event id_event; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event ALTER COLUMN id_event SET DEFAULT nextval('public.event_id_event_seq'::regclass);


--
-- Name: pelanggan id_pelanggan; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pelanggan ALTER COLUMN id_pelanggan SET DEFAULT nextval('public.pelanggan_id_pelanggan_seq'::regclass);


--
-- Name: pembayaran id_pembayaran; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pembayaran ALTER COLUMN id_pembayaran SET DEFAULT nextval('public.pembayaran_id_pembayaran_seq'::regclass);


--
-- Name: pembelian id_pembelian; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pembelian ALTER COLUMN id_pembelian SET DEFAULT nextval('public.pembelian_id_pembelian_seq'::regclass);


--
-- Name: pembelian_event id_pembelian_event; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pembelian_event ALTER COLUMN id_pembelian_event SET DEFAULT nextval('public.pembelian_event_id_pembelian_event_seq'::regclass);


--
-- Data for Name: event; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.event (id_event, nama_event, harga_event, jadwal_event, jadwal_event_day, foto_event, deskripsi_event, kuota, kategori) FROM stdin;
1	Aku Cinta Indonesia	75000	2025-03-20 14:00:00	Kamis	images/1737444813_1.png	Lestarikan budaya leluhur dengan mengenal pakaian adat, rumah adat, alat musik tradisional, dan menjelajahi Indonesia dengan VR (Virtual Reality)	20	Kegiatan
4	3D Digital Printing	50000	2025-04-01 09:30:00	Selasa	images/1737444658_4.png	Belajar membuat karakter maskot 3D sesuai dengan pilihan daerah favoritmu	20	Kegiatan
3	Kreasi Sablon	70000	2025-04-13 15:00:00	Minggu	images/1738379110_3.png	Memberikan pengalaman berkreasi dengan membuat desain kreatif dan mencetaknya di perlengkapan pribadi	20	Kegiatan
2	Programer Cilik	100000	2025-03-03 10:00:00	Senin	images/1738207359_2.png	Mengasah keterampilan coding sejak dini dengan membuat game edukasi interaktif tentang budaya Indonesia yang seru	20	Kegiatan
\.


--
-- Data for Name: pelanggan; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pelanggan (id_pelanggan, nama_pelanggan, password, email, no_hp_pelanggan, token, tokenexpiredat, token_expired_at) FROM stdin;
37	Naufal 	$2a$10$EN2LuMev5qooBrcHmZh4HOxRWleppSFG39k/ZQAJiyCnVkk7vL8s.	nopal@gmail.com	081325461767	\N	\N	\N
38	adjie	$2a$10$PgCodkgCat5wODoqyeC9/uvzR0i.SBF7gxJtkvYlraoOCn0kR4Ple	adjie@gmail.com	08172635378	\N	\N	\N
36	zhenya	$2a$10$dFoSFPSZBqCv/3H6RXa96OVX2yvEtsFl.pcbEtmEunDCuvyR6C0FW	grumpyy@gmail.com	081234567890	4384e80d-5b7a-43a7-b11e-497e12e8148c	\N	1743583030785
1	Hana	$2a$10$R2Z3lEF6DTl1hM/yFqxlX.Y4c1YxW1r2Dau/R0OlohMnfEMuSAZ7S	hana@gmail.com	08123456789	eab25c9b-08d8-424b-825e-1e6e00f80fdb	\N	1742900969871
2	nadhil	$2a$10$r603KbCIgSUbSHcaxaPQMuXtS6FUsYpinOLcPBpEJJx/aecBFmrwC	nadhil@gmail.com	081234567890	149a636c-44ca-4cc9-ad4f-d2e8ca74e439	\N	1742902201235
39	vany	$2a$10$LRXhTmqZwYodVgeJf18jF.fDyIz0jQJiFdVaWCDkuho3ph59LnJLi	vany@gmail.com	03uwb2sosowbql	\N	\N	\N
40	naufal	$2a$10$z7cQGTK9yO9Z4WtI86s20uNuIHaZVLEhqDNF6/oxt.EXyJj8tA.ti	nopal123@gmail.com	081325647890	\N	\N	\N
3	naufal	$2a$10$Ow5.okBfLTxOq3/R.roi..p/IdLHJouKqWTE2t5gV6oTqgla.TfHa	naufal@gmail.com	081234567890	bddca73b-6b8d-4619-a522-b8c89f2b09aa	\N	1743237585928
42	nabil	$2a$10$ZhYqJN9h9OqoZRwZI.Mkd.cnSolPBiv/j..qaCgf5f0ESiv08Niua	nabil@gmail.com	08279263648	\N	\N	\N
45	Naufal	$2a$10$ZVnbIuad3KBxjEeHqDyl4Od0Qq06EyAPJ8lbAgHayD6LXs2HH9hb.	naufal1234@gmail.com	0891268272	640fa63d-b9e8-486d-a8bb-0de548021c14	\N	1743665683494
41	Naufal\n	$2a$10$ayDyWi1jCSiccD1xOO18COPxtgQ7Aj0QJ.C07aH4csC8lH3F6FtuS	laila@gmail.com	081324567836	f1a0908d-503f-4a43-9ac4-4787655ad4d1	\N	1743824528069
\.


--
-- Data for Name: pembayaran; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pembayaran (id_pembayaran, id_pembelian, nama_pelanggan, bank, total_harga, tanggal_bayar) FROM stdin;
1	1	Hana	BCA	500000	2025-02-26 22:02:49.972+07
2	1	Hana	BCA	500000	2025-03-04 11:12:58.323+07
3	2	Hana	BCA	300000	2025-03-04 11:19:50.567+07
4	4	nadhil	DANA	390000	2025-03-04 13:29:17.635+07
\.


--
-- Data for Name: pembelian; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pembelian (id_pembelian, id_pelanggan, tanggal_pembelian, total_pembelian, status_pembelian) FROM stdin;
1	1	2025-02-26 12:46:52.282	50000	Lunas
2	1	2025-02-26 12:47:21.141	300000	Lunas
3	2	2025-03-04 13:26:14.156	390000	Pending
5	2	2025-03-04 13:27:30.132	390000	Pending
4	2	2025-03-04 13:27:02.439	390000	Lunas
\.


--
-- Data for Name: pembelian_event; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pembelian_event (id_pembelian_event, id_pembelian, id_event, jumlah, nama_event, kategori, harga_total) FROM stdin;
1	1	4	1	3D Digital Printing	Kegiatan	50000
2	2	1	4	Aku Cinta Indonesia	Kegiatan	300000
3	3	4	5	3D Digital Printing	Kegiatan	250000
4	3	3	2	Kreasi Sablon	Kegiatan	140000
5	4	4	5	3D Digital Printing	Kegiatan	250000
6	4	3	2	Kreasi Sablon	Kegiatan	140000
7	5	4	5	3D Digital Printing	Kegiatan	250000
8	5	3	2	Kreasi Sablon	Kegiatan	140000
\.


--
-- Name: event_id_event_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.event_id_event_seq', 4, true);


--
-- Name: pelanggan_id_pelanggan_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pelanggan_id_pelanggan_seq', 45, true);


--
-- Name: pembayaran_id_pembayaran_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pembayaran_id_pembayaran_seq', 4, true);


--
-- Name: pembelian_event_id_pembelian_event_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pembelian_event_id_pembelian_event_seq', 8, true);


--
-- Name: pembelian_id_pembelian_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pembelian_id_pembelian_seq', 5, true);


--
-- Name: event event_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event
    ADD CONSTRAINT event_pkey PRIMARY KEY (id_event);


--
-- Name: pelanggan pelanggan_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pelanggan
    ADD CONSTRAINT pelanggan_email_key UNIQUE (email);


--
-- Name: pelanggan pelanggan_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pelanggan
    ADD CONSTRAINT pelanggan_pkey PRIMARY KEY (id_pelanggan);


--
-- Name: pembayaran pembayaran_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pembayaran
    ADD CONSTRAINT pembayaran_pkey PRIMARY KEY (id_pembayaran);


--
-- Name: pembelian_event pembelian_event_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pembelian_event
    ADD CONSTRAINT pembelian_event_pkey PRIMARY KEY (id_pembelian_event);


--
-- Name: pembelian pembelian_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pembelian
    ADD CONSTRAINT pembelian_pkey PRIMARY KEY (id_pembelian);


--
-- Name: pembelian FK86hnf0t88ey1vmjrcqac8ynnj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pembelian
    ADD CONSTRAINT "FK86hnf0t88ey1vmjrcqac8ynnj" FOREIGN KEY (id_pelanggan) REFERENCES public.pelanggan(id_pelanggan);


--
-- Name: pembayaran fk_pembayaran_pembelian; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pembayaran
    ADD CONSTRAINT fk_pembayaran_pembelian FOREIGN KEY (id_pembelian) REFERENCES public.pembelian(id_pembelian) ON DELETE CASCADE;


--
-- Name: pembelian_event pembelian_event_id_event_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pembelian_event
    ADD CONSTRAINT pembelian_event_id_event_fkey FOREIGN KEY (id_event) REFERENCES public.event(id_event) ON DELETE CASCADE;


--
-- Name: pembelian_event pembelian_event_id_pembelian_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pembelian_event
    ADD CONSTRAINT pembelian_event_id_pembelian_fkey FOREIGN KEY (id_pembelian) REFERENCES public.pembelian(id_pembelian) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

