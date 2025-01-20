--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3
-- Dumped by pg_dump version 15.3

-- Started on 2025-01-20 12:05:10

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
-- TOC entry 214 (class 1259 OID 16587)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    uuid character(36) NOT NULL,
    bcrypt_password character varying(255) NOT NULL,
    username character varying(255) NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 3316 (class 0 OID 16587)
-- Dependencies: 214
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (uuid, bcrypt_password, username) FROM stdin;
4df99733-15f1-4de8-98b4-0843c7a0be5f	$2a$10$FZBzCY3e2BzD.0lMyPhhL.HRGNBASdkrcfOMd9S/0oEAZVltMbQ1a	sunny
b2e098c3-84b7-4bec-a102-aee635764671	$2a$10$8Sm8Z9i5AXoQj5NbFvEMKOW4s2yB5YWgNgEWu6Aj9aig1sXx9.qbm	Rany
\.


--
-- TOC entry 3173 (class 2606 OID 16593)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (uuid);


-- Completed on 2025-01-20 12:05:10

--
-- PostgreSQL database dump complete
--

