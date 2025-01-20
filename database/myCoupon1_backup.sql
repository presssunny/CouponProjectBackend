--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3
-- Dumped by pg_dump version 15.3

-- Started on 2025-01-20 12:02:53

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

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 16551)
-- Name: coupon; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.coupon (
    id bigint NOT NULL,
    amount integer,
    category integer,
    company_uuid uuid NOT NULL,
    description character varying(255) NOT NULL,
    end_date date NOT NULL,
    imageurl character varying(255) NOT NULL,
    price double precision NOT NULL,
    start_date date NOT NULL,
    title character varying(255) NOT NULL,
    uuid character(36)
);


ALTER TABLE public.coupon OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16550)
-- Name: coupon_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.coupon_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.coupon_id_seq OWNER TO postgres;

--
-- TOC entry 3335 (class 0 OID 0)
-- Dependencies: 214
-- Name: coupon_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.coupon_id_seq OWNED BY public.coupon.id;


--
-- TOC entry 217 (class 1259 OID 16569)
-- Name: coupon_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.coupon_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.coupon_seq OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16559)
-- Name: customer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer (
    customer_id bigint NOT NULL,
    customers uuid
);


ALTER TABLE public.customer OWNER TO postgres;

--
-- TOC entry 3178 (class 2604 OID 16554)
-- Name: coupon id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coupon ALTER COLUMN id SET DEFAULT nextval('public.coupon_id_seq'::regclass);


--
-- TOC entry 3327 (class 0 OID 16551)
-- Dependencies: 215
-- Data for Name: coupon; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.coupon (id, amount, category, company_uuid, description, end_date, imageurl, price, start_date, title, uuid) FROM stdin;
1	50	1	22225215-4222-2222-2222-222222222222	Get a 20% discount on all electronics	2023-02-01	https://example.com/electronics_coupon.jpg	20	2023-01-01	20% Off Electronics	f47ac10b-58cc-4372-a567-0e02b2c3d479
2	30	2	44444542-1444-4444-4444-444444444444	Enjoy free shipping on all fashion items	2023-03-15	https://example.com/fashion_coupon.jpg	0	2023-02-15	Free Shipping on Fashion	d4e12a3d-8d12-4e4e-834a-b5c6ac4271bb
3	40	3	66632542-5666-6666-6666-666666666666	Get $10 off your next grocery purchase	2023-04-01	https://example.com/grocery_coupon.jpg	10	2023-03-01	$10 Off Grocery Purchase	2e7c3b18-ae4b-4733-a8d8-8461c707df7d
4	20	4	88856885-3684-2812-8888-888888888888	Purchase one book and get another one free	2023-05-15	https://example.com/book_coupon.jpg	0	2023-04-15	Buy One, Get One Free - Books	76a39446-8f2f-48d4-8b84-551384c50daa
5	25	5	00258605-6420-0000-0000-000000000000	Get 15% off on fitness equipment purchases	2023-06-01	https://example.com/fitness_coupon.jpg	15	2023-05-01	15% Off Fitness Equipment	0a8b6a9f-007c-48b7-90e5-3c980b242593
\.


--
-- TOC entry 3328 (class 0 OID 16559)
-- Dependencies: 216
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customer (customer_id, customers) FROM stdin;
\.


--
-- TOC entry 3336 (class 0 OID 0)
-- Dependencies: 214
-- Name: coupon_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.coupon_id_seq', 5, true);


--
-- TOC entry 3337 (class 0 OID 0)
-- Dependencies: 217
-- Name: coupon_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.coupon_seq', 1, false);


--
-- TOC entry 3180 (class 2606 OID 16558)
-- Name: coupon coupon_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coupon
    ADD CONSTRAINT coupon_pkey PRIMARY KEY (id);


--
-- TOC entry 3182 (class 2606 OID 16563)
-- Name: coupon uk_2o685cnfrct53beb9mhfk00bs; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coupon
    ADD CONSTRAINT uk_2o685cnfrct53beb9mhfk00bs UNIQUE (uuid);


--
-- TOC entry 3183 (class 2606 OID 16564)
-- Name: customer fkbvxtj709qqccq2duhog1xvr3m; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT fkbvxtj709qqccq2duhog1xvr3m FOREIGN KEY (customer_id) REFERENCES public.coupon(id);


-- Completed on 2025-01-20 12:02:54

--
-- PostgreSQL database dump complete
--

