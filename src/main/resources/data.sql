INSERT INTO tb_person (id, name, birth_date)
VALUES ('b2761dc7-b7bf-4983-bd3c-9731413347cb', 'Rafael', '1997-02-28');

INSERT INTO tb_person (id, name, birth_date)
VALUES ('66cd4ba2-34de-46e8-bfb4-78de2a417fad', 'Marcello', '1991-06-16');

INSERT INTO tb_person (id, name, birth_date)
VALUES ('7dcb1dc8-5186-4079-9271-5f57b5fd033a', 'Fernanda', '1985-04-04');

INSERT INTO tb_address (id, city, zip_code, public_place, number, is_main_address, person_id)
VALUES ('6fa65ef3-6781-45d3-97a7-3da81f18196c', 'Curitiba', '81015-123', 'Rua das Flores', 123, true, 'b2761dc7-b7bf-4983-bd3c-9731413347cb');

INSERT INTO tb_address (id, city, zip_code, public_place, number, is_main_address, person_id)
VALUES ('3d447dc8-ad22-4592-b788-a7dc21ce5612', 'São Paulo', '81010-124', 'Rua Do Ipiranga', 168, false, 'b2761dc7-b7bf-4983-bd3c-9731413347cb');

INSERT INTO tb_address (id, city, zip_code, public_place, number, is_main_address, person_id)
VALUES ('0b67c506-af8b-4309-a363-36039d4b59ec', 'Florianópolis', '81010-125', 'Rua Dom Pedro', 1155, true, '66cd4ba2-34de-46e8-bfb4-78de2a417fad');