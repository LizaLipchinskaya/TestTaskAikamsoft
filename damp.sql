--
-- PostgreSQL database dump
--

-- Dumped from database version 12.7
-- Dumped by pg_dump version 12.6

-- Started on 2022-08-12 20:16:50 +07

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
-- TOC entry 202 (class 1259 OID 25327)
-- Name: customer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer (
    id bigint NOT NULL,
    first_name character varying(50),
    last_name character varying(50)
);


ALTER TABLE public.customer OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 25332)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id bigint NOT NULL,
    name character varying,
    price integer
);


ALTER TABLE public.product OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 25340)
-- Name: purchase; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.purchase (
    id bigint NOT NULL,
    id_customer bigint,
    id_product bigint,
    date_purchase date
);


ALTER TABLE public.purchase OWNER TO postgres;

--
-- TOC entry 3145 (class 0 OID 25327)
-- Dependencies: 202
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.customer VALUES (1, 'Иван', 'Иванов');
INSERT INTO public.customer VALUES (2, 'Пётр', 'Петров');
INSERT INTO public.customer VALUES (3, 'Юрий', 'Долгий');
INSERT INTO public.customer VALUES (4, 'Михаил', 'Иванов');
INSERT INTO public.customer VALUES (5, 'Роман', 'Иванов');


--
-- TOC entry 3146 (class 0 OID 25332)
-- Dependencies: 203
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.product VALUES (1, 'Минеральная вода', 30);
INSERT INTO public.product VALUES (2, 'Хлеб', 40);
INSERT INTO public.product VALUES (3, 'Колбаса', 200);
INSERT INTO public.product VALUES (4, 'Говядина', 400);


--
-- TOC entry 3147 (class 0 OID 25340)
-- Dependencies: 204
-- Data for Name: purchase; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.purchase VALUES (1, 1, 1, '2020-01-14');
INSERT INTO public.purchase VALUES (2, 2, 2, '2020-01-26');
INSERT INTO public.purchase VALUES (3, 2, 1, '2022-08-01');
INSERT INTO public.purchase VALUES (4, 3, 1, '2022-01-01');
INSERT INTO public.purchase VALUES (5, 3, 1, '2020-05-08');
INSERT INTO public.purchase VALUES (6, 3, 1, '2021-04-16');
INSERT INTO public.purchase VALUES (7, 3, 1, '2020-08-23');
INSERT INTO public.purchase VALUES (8, 3, 1, '2021-02-24');
INSERT INTO public.purchase VALUES (9, 5, 3, '2022-08-12');
INSERT INTO public.purchase VALUES (10, 5, 3, '2022-08-01');
INSERT INTO public.purchase VALUES (11, 4, 3, '2022-06-10');
INSERT INTO public.purchase VALUES (12, 4, 4, '2021-10-03');
INSERT INTO public.purchase VALUES (13, 4, 4, '2021-12-29');


--
-- TOC entry 3012 (class 2606 OID 25331)
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);


--
-- TOC entry 3014 (class 2606 OID 25339)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 3016 (class 2606 OID 25344)
-- Name: purchase purchase_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchase
    ADD CONSTRAINT purchase_pkey PRIMARY KEY (id);


--
-- TOC entry 3017 (class 2606 OID 25345)
-- Name: purchase purchase_id_customer_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchase
    ADD CONSTRAINT purchase_id_customer_fkey FOREIGN KEY (id_customer) REFERENCES public.customer(id);


--
-- TOC entry 3018 (class 2606 OID 25350)
-- Name: purchase purchase_id_product_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchase
    ADD CONSTRAINT purchase_id_product_fkey FOREIGN KEY (id_product) REFERENCES public.product(id);


-- Completed on 2022-08-12 20:16:50 +07

--
-- PostgreSQL database dump complete
--

