DROP TABLE IF EXISTS transaction_prod;

DROP TABLE IF EXISTS transaction;

DROP TABLE IF EXISTS product_image;

DROP TABLE IF EXISTS product;

DROP TABLE IF EXISTS category;

DROP TABLE IF EXISTS subscription;

DROP TABLE IF EXISTS seller;

DROP TABLE IF EXISTS users;

DROP SEQUENCE IF EXISTS seq_product_prod_no;

DROP SEQUENCE IF EXISTS seq_transaction_tran_no;

DROP SEQUENCE IF EXISTS seq_category_category_no;

DROP SEQUENCE IF EXISTS seq_product_image_no;

CREATE SEQUENCE IF NOT EXISTS seq_product_prod_no INCREMENT BY 1 START WITH 10000;

CREATE SEQUENCE IF NOT EXISTS seq_transaction_tran_no INCREMENT BY 1 START WITH 10000;

CREATE SEQUENCE IF NOT EXISTS seq_category_category_no INCREMENT BY 1 START WITH 10000;

CREATE SEQUENCE IF NOT EXISTS seq_product_image_no INCREMENT BY 1 START WITH 10000;

CREATE TABLE IF NOT EXISTS users
(
    user_id    TEXT,
    name_of_user  TEXT NOT NULL,
    password   TEXT NOT NULL,
    role       TEXT DEFAULT 'USER',
    ssn        TEXT,
    cell_phone TEXT,
    addr       TEXT,
    email      TEXT,
    reg_date   DATE DEFAULT CURRENT_DATE,
    CONSTRAINT users_pk PRIMARY KEY (user_id)
);

CREATE TABLE seller
(
    seller_id       TEXT,
    main_image_file TEXT,
    description     TEXT,
    CONSTRAINT seller_pk PRIMARY KEY (seller_id),
    CONSTRAINT seller_users_fk FOREIGN KEY (seller_id) REFERENCES users (user_id)
);


CREATE TABLE IF NOT EXISTS subscription
(
    subscriber_id TEXT,
    seller_id     TEXT,
    CONSTRAINT subscription_pk PRIMARY KEY (subscriber_id, seller_id),
    CONSTRAINT subscription_subscriber_fk FOREIGN KEY (subscriber_id) REFERENCES users (user_id),
    CONSTRAINT subscription_seller_fk FOREIGN KEY (seller_id) REFERENCES seller (seller_id)
);

CREATE TABLE IF NOT EXISTS category
(
    category_no   INTEGER,
    category_name TEXT NOT NULL,
    PRIMARY KEY (category_no),
    UNIQUE (category_name)
);

CREATE TABLE IF NOT EXISTS product
(
    prod_no         INTEGER,
    register        TEXT,
    prod_name       TEXT    NOT NULL,
    prod_detail     TEXT,
    manufacture_day DATE DEFAULT CURRENT_DATE,
    price           INTEGER NOT NULL,
    reg_date        DATE DEFAULT CURRENT_DATE,
    stock           INTEGER NOT NULL,
    category_no     INTEGER,
    CONSTRAINT product_pk PRIMARY KEY (prod_no),
    CONSTRAINT prod_category_fk FOREIGN KEY (category_no) REFERENCES category (category_no),
    CONSTRAINT prod_register_fk FOREIGN KEY (register) REFERENCES seller (seller_id)
);

CREATE TABLE IF NOT EXISTS product_image
(
    image_no    INTEGER,
    prod_no     INTEGER,
    file_name   TEXT,
    description TEXT,
    thumbnail   BOOLEAN,
    CONSTRAINT product_image_pk PRIMARY KEY (image_no),
    CONSTRAINT product_fk FOREIGN KEY (prod_no) REFERENCES product (prod_no)
);

CREATE TABLE IF NOT EXISTS transaction
(
    tran_no          INTEGER,
    buyer_id         TEXT NOT NULL,
    payment_option   CHAR(3),
    receiver_name    TEXT,
    receiver_phone   TEXT,
    demailaddr       TEXT,
    dlvy_request     TEXT,
    tran_status_code CHAR(3),
    order_date       DATE DEFAULT CURRENT_DATE,
    dlvy_date        DATE DEFAULT CURRENT_DATE,
    CONSTRAINT transaction_pk PRIMARY KEY (tran_no),
    CONSTRAINT transaction_buyer_id FOREIGN KEY (buyer_id) REFERENCES users (user_id),
    CONSTRAINT transaction_payment_option CHECK (payment_option IN ('0', '1')),
    CONSTRAINT transaction_trans_status_code CHECK (tran_status_code IN ('0', '1', '2', '3'))
);

CREATE TABLE IF NOT EXISTS transaction_prod
(
    tran_no  INTEGER NOT NULL,
    prod_no  INTEGER NOT NULL,
    quantity INTEGER NOT NULL,
    CONSTRAINT tp_pk PRIMARY KEY (tran_no, prod_no),
    CONSTRAINT tp_tran_no_fk FOREIGN KEY (tran_no) REFERENCES transaction (tran_no),
    CONSTRAINT tp_prod_no_fk FOREIGN KEY (prod_no) REFERENCES product (prod_no),
    CONSTRAINT tp_quantity_num_range CHECK (quantity >= 0)
);

INSERT INTO users
    ( user_id,
      name_of_user,
      password,
      role,
      ssn,
      cell_phone,
      addr,
      email,
      reg_date )
VALUES
    ( 'admin',
      'admin',
      '1234',
      'ADMIN',
      NULL,
      NULL,
      '서울시 서초구',
      'admin@mvc.com',
      TO_DATE('2012/01/14 10:48:43', 'YYYY/MM/DD HH24:MI:SS') );

INSERT INTO users
    ( user_id,
      name_of_user,
      password,
      role,
      ssn,
      cell_phone,
      addr,
      email,
      reg_date )
VALUES
    ( 'manager',
      'manager',
      '1234',
      'ADMIN',
      NULL,
      NULL,
      NULL,
      'manager@mvc.com',
      TO_DATE('2012/01/14 10:48:43', 'YYYY/MM/DD HH24:MI:SS') );

INSERT INTO users
VALUES
    ( 'user01',
      'SCOTT',
      '1111',
      'USER',
      NULL,
      NULL,
      NULL,
      NULL,
      CURRENT_DATE );

INSERT INTO users
VALUES
    ( 'user02',
      'SCOTT',
      '2222',
      'USER',
      NULL,
      NULL,
      NULL,
      NULL,
      CURRENT_DATE );

INSERT INTO users
VALUES
    ( 'user03',
      'SCOTT',
      '3333',
      'USER',
      NULL,
      NULL,
      NULL,
      NULL,
      CURRENT_DATE );

INSERT INTO users
VALUES
    ( 'user04',
      'SCOTT',
      '4444',
      'USER',
      NULL,
      NULL,
      NULL,
      NULL,
      CURRENT_DATE );

INSERT INTO users
VALUES
    ( 'user05',
      'SCOTT',
      '5555',
      'USER',
      NULL,
      NULL,
      NULL,
      NULL,
      CURRENT_DATE );

INSERT INTO users
VALUES
    ( 'user06',
      'SCOTT',
      '6666',
      'USER',
      NULL,
      NULL,
      NULL,
      NULL,
      CURRENT_DATE );

INSERT INTO users
VALUES
    ( 'user07',
      'SCOTT',
      '7777',
      'USER',
      NULL,
      NULL,
      NULL,
      NULL,
      CURRENT_DATE );

INSERT INTO users
VALUES
    ( 'user08',
      'SCOTT',
      '8888',
      'USER',
      NULL,
      NULL,
      NULL,
      NULL,
      CURRENT_DATE );

INSERT INTO users
VALUES
    ( 'user09',
      'SCOTT',
      '9999',
      'USER',
      NULL,
      NULL,
      NULL,
      NULL,
      CURRENT_DATE );

INSERT INTO users
VALUES
    ( 'user10',
      'SCOTT',
      '1010',
      'USER',
      NULL,
      NULL,
      NULL,
      NULL,
      CURRENT_DATE );

INSERT INTO users
VALUES
    ( 'user11',
      'SCOTT',
      '1111',
      'USER',
      NULL,
      NULL,
      NULL,
      NULL,
      CURRENT_DATE );

INSERT INTO users
VALUES
    ( 'user12',
      'SCOTT',
      '1212',
      'USER',
      NULL,
      NULL,
      NULL,
      NULL,
      CURRENT_DATE );

INSERT INTO users
VALUES
    ( 'user13',
      'SCOTT',
      '1313',
      'USER',
      NULL,
      NULL,
      NULL,
      NULL,
      CURRENT_DATE );

INSERT INTO users
VALUES
    ( 'user14',
      'SCOTT',
      '1414',
      'USER',
      NULL,
      NULL,
      NULL,
      NULL,
      CURRENT_DATE );

INSERT INTO users
VALUES
    ( 'user15',
      'SCOTT',
      '1515',
      'USER',
      NULL,
      NULL,
      NULL,
      NULL,
      CURRENT_DATE );

INSERT INTO users
VALUES
    ( 'user16',
      'SCOTT',
      '1616',
      'USER',
      NULL,
      NULL,
      NULL,
      NULL,
      CURRENT_DATE );

INSERT INTO users
VALUES
    ( 'user17',
      'SCOTT',
      '1717',
      'USER',
      NULL,
      NULL,
      NULL,
      NULL,
      CURRENT_DATE );

INSERT INTO users
VALUES
    ( 'user18',
      'SCOTT',
      '1818',
      'USER',
      NULL,
      NULL,
      NULL,
      NULL,
      CURRENT_DATE );

INSERT INTO users
VALUES
    ( 'user19',
      'SCOTT',
      '1919',
      'USER',
      NULL,
      NULL,
      NULL,
      NULL,
      CURRENT_DATE );

INSERT INTO users
VALUES
    ( 'user20',
      'SCOTT',
      '2020',
      'USER',
      NULL,
      NULL,
      NULL,
      NULL,
      CURRENT_DATE );

INSERT INTO users
VALUES
    ( 'user21',
      'SCOTT',
      '2121',
      'USER',
      NULL,
      NULL,
      NULL,
      NULL,
      CURRENT_DATE );

INSERT INTO users
VALUES
    ( 'user22',
      'SCOTT',
      '2222',
      'USER',
      NULL,
      NULL,
      NULL,
      NULL,
      CURRENT_DATE );

INSERT INTO users
VALUES
    ( 'user23',
      'SCOTT',
      '2323',
      'USER',
      NULL,
      NULL,
      NULL,
      NULL,
      CURRENT_DATE );

INSERT INTO users
VALUES
    ( 'seller1',
      '일론 머스크',
      'asdf',
      'SELLER',
      NULL,
      NULL,
      NULL,
      NULL,
      CURRENT_DATE );

INSERT INTO users
VALUES
    ( 'seller2',
      '마이크로소프트',
      'asdf',
      'SELLER',
      NULL,
      NULL,
      NULL,
      NULL,
      CURRENT_DATE );

INSERT INTO users
VALUES
    ( 'seller3',
      '삼성',
      'asdf',
      'SELLER',
      NULL,
      NULL,
      NULL,
      NULL,
      CURRENT_DATE );

INSERT INTO seller
    (
        seller_id,
        main_image_file,
        description )
VALUES
    ( 'seller1',
      'image_readtop_2022_556367_16561184505086722.jpeg',
      '화성에 갈 수도 있고 못 갈 수도 있습니다.' ),
    ( 'seller2',
      'microsoft-logo.jpg',
      'I hate Linux' ),
    ( 'seller3',
      'samsung-logo.jpg',
      '한국인이면 갤럭시 씁시다.' );

INSERT INTO category
    (
        category_no,
        category_name )
VALUES
    ( 9000,
      '레노버 Laptop' ),
    ( 9001,
      '식품' ),
    ( 9002,
      '책' );

INSERT INTO product
    ( prod_no,
      register,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      reg_date,
      stock,
      category_no )
VALUES
    ( 9000,
      'seller1',
      '레노버 2022 아이디어패드 슬림 1 15AMN7 15.6 라이젠3 라이젠 7000 시리즈',
      '레노버에서 파는 2022년산 아이디어패드 슬림 1',
      TO_DATE('2022-02-23', 'YYYY-MM-DD'),
      488990,
      TO_DATE('2022-02-25', 'YYYY-MM-DD'),
      300,
      9000 );


INSERT INTO product_image
    ( image_no,
      prod_no,
      file_name,
      description,
      thumbnail )
VALUES
    ( NEXTVAL('seq_product_image_no'),
      9000,
      '298805464638389-ca1c0934-3411-4ff0-a3aa-aea9cb288b28.jpg',
      'Lenovo IdeaPad Front',
      TRUE ),
    ( NEXTVAL('seq_product_image_no'),
      9000,
      '8efd0cea-55da-48d2-aaa8-55f3d3108de9.jpg',
      'Lenovo IdeaPad Top',
      FALSE ),
    ( NEXTVAL('seq_product_image_no'),
      9000,
      '18457568_3.jpg',
      'Lenovo IdeaPad Front',
      FALSE );

INSERT INTO product
    ( prod_no,
      register,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      reg_date,
      stock,
      category_no )
VALUES
    ( 9001,
      'seller1',
      '레노버 씽크패드 L13 Yoga Gen2 21ADS00300 (SSD 256GB)',
      '사고 싶은 마음이 들게끔 하는 라인업',
      TO_DATE('2023-03-23', 'YYYY-MM-DD'),
      1390000,
      TO_DATE('2024-03-31', 'YYYY-MM-DD'),
      300,
      9000 );

INSERT INTO product_image
    ( image_no,
      prod_no,
      file_name,
      description,
      thumbnail )
VALUES
    ( NEXTVAL('seq_product_image_no'),
      9001,
      '16137110_1.jpg',
      'Lenovo Thinkpad 1',
      TRUE ),
    ( NEXTVAL('seq_product_image_no'),
      9001,
      '16137110_2.jpg',
      'Lenovo Thinkpad 2',
      FALSE ),
    ( NEXTVAL('seq_product_image_no'),
      9001,
      '16137110_3.jpg',
      'Lenovo Thinkpad 3',
      FALSE ),
    ( NEXTVAL('seq_product_image_no'),
      9001,
      '16137110_4.jpg',
      'Lenovo Thinkpad 4',
      FALSE );

INSERT INTO product
    ( prod_no,
      register,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      reg_date,
      stock,
      category_no )
VALUES
    ( 9002,
      'seller1',
      '레노버 씽크북 16p 20YM0002KR (SSD 512GB)',
      '롤을 돌리기에 손색이 없는 성능',
      TO_DATE('2023-03-23', 'YYYY-MM-DD'),
      1170000,
      TO_DATE('2024-03-31', 'YYYY-MM-DD'),
      10,
      9000 );

INSERT INTO product_image
    ( image_no,
      prod_no,
      file_name,
      description,
      thumbnail )
VALUES
    ( NEXTVAL('seq_product_image_no'),
      9002,
      '14995424_1.jpg',
      'Lenovo Thinkpad 1',
      TRUE ),
    ( NEXTVAL('seq_product_image_no'),
      9002,
      '14995424_2.jpg',
      'Lenovo Thinkpad 2',
      FALSE ),
    ( NEXTVAL('seq_product_image_no'),
      9002,
      '14995424_4.jpg',
      'Lenovo Thinkpad 3',
      FALSE );

INSERT INTO product
    ( prod_no,
      register,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      reg_date,
      stock,
      category_no )
VALUES
    ( 9003,
      'seller2',
      '곰곰 스테비아 대추방울토마토, 500g, 1팩',
      '방울토마토',
      TO_DATE('2023-03-23', 'YYYY-MM-DD'),
      6900,
      CURRENT_DATE,
      10,
      9001 );

INSERT INTO product_image
    ( image_no,
      prod_no,
      file_name,
      description,
      thumbnail )
VALUES
    ( NEXTVAL('seq_product_image_no'),
      9003,
      '8506343438264159-861f5245-7306-47e6-a234-dacb800957cf.jpg',
      'tomato 1',
      TRUE ),
    ( NEXTVAL('seq_product_image_no'),
      9003,
      '7889118092650107-cba9ec55-9e26-42a1-a434-6d9b464e265d.jpg',
      'tomato 2',
      FALSE ),
    ( NEXTVAL('seq_product_image_no'),
      9003,
      '7893833092687284-9fb00d78-a6d9-4476-abc9-295c76f373ba.jpg',
      'tomato 3',
      FALSE ),
    ( NEXTVAL('seq_product_image_no'),
      9003,
      '7889117050182107-3e4cb1bb-e30e-4222-985a-0c963152e439.jpg',
      'tomato 4',
      FALSE ),
    ( NEXTVAL('seq_product_image_no'),
      9003,
      '8506349032989131-05ba0c60-805b-4a47-bbc9-fd6971b82b5f.jpg',
      'tomato 5',
      FALSE ),
    ( NEXTVAL('seq_product_image_no'),
      9003,
      '8506349277154571-ec610c6e-198a-4e56-a171-83a2fdc768e2.png',
      'tomato 6',
      FALSE ),
    ( NEXTVAL('seq_product_image_no'),
      9003,
      '1157568235688426-157e7fd0-a332-4e13-96af-030cec396176.png',
      'tomato 7',
      FALSE );

INSERT INTO product
    ( prod_no,
      register,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      reg_date,
      stock,
      category_no )
VALUES
    ( 9004,
      'seller2',
      '델몬트 필리핀산 바나나, 2kg 내외, 1개',
      '바나나',
      TO_DATE('2023-03-23', 'YYYY-MM-DD'),
      6380,
      CURRENT_DATE,
      10,
      9001 );

INSERT INTO product_image
    ( image_no,
      prod_no,
      file_name,
      description,
      thumbnail )
VALUES
    ( NEXTVAL('seq_product_image_no'),
      9004,
      '9001566481325720-203a9938-1dbc-4ee7-b2af-ab520a5dd3b2.jpg',
      'Banana 1',
      TRUE ),
    ( NEXTVAL('seq_product_image_no'),
      9004,
      '9006281449029906-20ed14fe-97ac-4df7-86db-16f71d9f4679.jpg',
      'Banana 2',
      FALSE ),
    ( NEXTVAL('seq_product_image_no'),
      9004,
      '284521538519712-f1387fea-b8b0-478a-a54e-d7d74af6904e.jpg',
      'Banana 3',
      FALSE ),
    ( NEXTVAL('seq_product_image_no'),
      9004,
      '9001566441098146-bd9d2bec-579c-463a-9e09-7d28fb664d6d.jpg',
      'Banana 4',
      FALSE );

INSERT INTO product
    ( prod_no,
      register,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      reg_date,
      stock,
      category_no )
VALUES
    ( 9005,
      'seller2',
      '오늘차림 안동식 순살 찜닭 양념육',
      '큰 맘 먹고 내놓습니다 ㅜㅜ',
      TO_DATE('2023-03-23', 'YYYY-MM-DD'),
      14900,
      CURRENT_DATE,
      50,
      9001 );

INSERT INTO product_image
    ( image_no,
      prod_no,
      file_name,
      description,
      thumbnail )
VALUES
    ( NEXTVAL('seq_product_image_no'),
      9005,
      '646280948155222-8f800d67-ebae-4917-86ff-4732ba980da3.jpg',
      'Meat 1',
      TRUE ),
    ( NEXTVAL('seq_product_image_no'),
      9005,
      'd9709d3b-2ff8-4076-bd37-42de2250a24f.jpg',
      'Meat 2',
      FALSE ),
    ( NEXTVAL('seq_product_image_no'),
      9005,
      '404a834e-e913-4d13-ae08-c2080b6fe202.jpg',
      'Meat 3',
      FALSE );

INSERT INTO product
    ( prod_no,
      register,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      reg_date,
      stock,
      category_no )
VALUES
    ( 9006,
      'seller2',
      '프레시지 신림동 백순대 볶음',
      '먹어보진 않았습니다',
      TO_DATE('2023-03-23', 'YYYY-MM-DD'),
      9900,
      CURRENT_DATE,
      15,
      9001 );

INSERT INTO product_image
    ( image_no,
      prod_no,
      file_name,
      description,
      thumbnail )
VALUES
    ( NEXTVAL('seq_product_image_no'),
      9006,
      'f387499a-f427-489e-84b1-ab2f8512ab64.jpg',
      '순대 1',
      TRUE ),
    ( NEXTVAL('seq_product_image_no'),
      9006,
      'ba6fcf07-5397-4cbd-8c9d-a0bde656742d.jpg',
      '순대 2',
      FALSE ),
    ( NEXTVAL('seq_product_image_no'),
      9006,
      '2443930293053975-12c051eb-ea9c-41f4-b560-78711b958de5.jpg',
      '순대 3',
      FALSE );

INSERT INTO product
    ( prod_no,
      register,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      reg_date,
      stock,
      category_no )
VALUES
    ( 9007,
      'seller3',
      '빙그레 바나나맛우유, 240ml, 8개',
      '안정적인 그 맛',
      TO_DATE('2023-03-23', 'YYYY-MM-DD'),
      11560,
      CURRENT_DATE,
      40,
      9001 );

INSERT INTO product_image
    ( image_no,
      prod_no,
      file_name,
      description,
      thumbnail )
VALUES
    ( NEXTVAL('seq_product_image_no'),
      9007,
      '4431293182250513-c72fb5fe-da6c-4428-b8c9-72e89fbfc7d5.jpg',
      '빙그레 1',
      TRUE ),
    ( NEXTVAL('seq_product_image_no'),
      9007,
      '170795247045845-842f4aa2-63ad-4699-ba1d-1c6a7649e751.jpg',
      '빙그레 2',
      FALSE ),
    ( NEXTVAL('seq_product_image_no'),
      9007,
      '1607026185826778-743a76ba-a3e6-4133-ac55-5db79c0f202f.jpg',
      '빙그레 3',
      FALSE );

INSERT INTO product
    ( prod_no,
      register,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      reg_date,
      stock,
      category_no )
VALUES
    ( 9008,
      'seller3',
      '서울우유 더 진한 순수 플레인 요거트, 1.8L',
      '요거트',
      TO_DATE('2023-03-23', 'YYYY-MM-DD'),
      7360,
      CURRENT_DATE,
      40,
      9001 );

INSERT INTO product_image
    ( image_no,
      prod_no,
      file_name,
      description,
      thumbnail )
VALUES
    ( NEXTVAL('seq_product_image_no'),
      9008,
      '31375867088970-25e70aff-6e34-4a86-93ef-2ea4f5fbb686.jpg',
      '빙그레 1',
      TRUE ),
    ( NEXTVAL('seq_product_image_no'),
      9008,
      '8873325750034811-69dd71a2-9e61-48ad-88eb-21f5d756124f.jpg',
      '빙그레 2',
      FALSE ),
    ( NEXTVAL('seq_product_image_no'),
      9008,
      '8873325763174999-6a5eccbc-4609-48fc-af00-f8d86a11362d.jpg',
      '빙그레 3',
      FALSE );

INSERT INTO product
    ( prod_no,
      register,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      reg_date,
      stock,
      category_no )
VALUES
    ( 9009,
      'seller3',
      '퍼시픽 모짜렐라 스트링 치즈',
      '치즈',
      TO_DATE('2023-03-23', 'YYYY-MM-DD'),
      7360,
      CURRENT_DATE,
      0,
      9001 );

INSERT INTO product_image
    ( image_no,
      prod_no,
      file_name,
      description,
      thumbnail )
VALUES
    ( NEXTVAL('seq_product_image_no'),
      9009,
      '57600316737672-9372e828-3252-443d-bfa5-b35031ecd019.jpg',
      '치이즈',
      TRUE );

INSERT INTO product
    ( prod_no,
      register,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      reg_date,
      stock,
      category_no )
VALUES
    ( 9010,
      'seller3',
      'C Programming: A Modern Approach, 2nd Edition',
      'C 개발자 필독서',
      TO_DATE('2023-03-23', 'YYYY-MM-DD'),
      101000,
      CURRENT_DATE,
      20,
      9002 );

INSERT INTO product_image
    ( image_no,
      prod_no,
      file_name,
      description,
      thumbnail )
VALUES
    ( NEXTVAL('seq_product_image_no'),
      9010,
      '71YNXYuwPGL._SL1454_.jpg',
      '치이즈',
      TRUE ),
    ( NEXTVAL('seq_product_image_no'),
      9010,
      '81Jj5l7IMsL._SL1456_.jpg',
      '치이즈',
      FALSE );

INSERT INTO product
    ( prod_no,
      register,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      reg_date,
      stock,
      category_no )
VALUES
    ( 9011,
      'seller3',
      'Computer Systems: A Programmers Perspective',
      '시스템 프로그래밍을 위한 교과서',
      TO_DATE('2023-03-23', 'YYYY-MM-DD'),
      46540,
      CURRENT_DATE,
      20,
      9002 );

INSERT INTO product_image
    ( image_no,
      prod_no,
      file_name,
      description,
      thumbnail )
VALUES
    ( NEXTVAL('seq_product_image_no'),
      9011,
      '81ZugrP5S3L._SL1500_.jpg',
      '치이즈',
      TRUE ),
    ( NEXTVAL('seq_product_image_no'),
      9011,
      '71l5bfWa6EL._SL1500_.jpg',
      '',
      FALSE ),
    ( NEXTVAL('seq_product_image_no'),
      9011,
      '71QAOrNnpPL._SL1500_.jpg',
      '치이즈',
      FALSE );

INSERT INTO product
    ( prod_no,
      register,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      reg_date,
      stock,
      category_no )
VALUES
    ( 9012,
      'seller3',
      'Computer Organization and Design MIPS Edition: The Hardware/Software Interface',
      '컴퓨터 구조. 특징: 구글에 PDF 굴러다님',
      TO_DATE('2023-03-23', 'YYYY-MM-DD'),
      46540,
      CURRENT_DATE,
      50,
      9002 );

INSERT INTO product_image
    ( image_no,
      prod_no,
      file_name,
      description,
      thumbnail )
VALUES
    ( NEXTVAL('seq_product_image_no'),
      9012,
      '71UfLO8-cqS._SL1459_.jpg',
      '치이즈',
      TRUE );

INSERT INTO product
    ( prod_no,
      register,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      reg_date,
      stock,
      category_no )
VALUES
    ( 9013,
      'seller3',
      'Design Patterns: Elements of Reusable Object-Oriented Software',
      'GoF 디자인 패턴',
      TO_DATE('2023-03-23', 'YYYY-MM-DD'),
      46540,
      CURRENT_DATE,
      50,
      9002 );

INSERT INTO product_image
    ( image_no,
      prod_no,
      file_name,
      description,
      thumbnail )
VALUES
    ( NEXTVAL('seq_product_image_no'),
      9013,
      '81gtKoapHFL._SL1500_.jpg',
      'Gang of Four',
      TRUE ),
    ( NEXTVAL('seq_product_image_no'),
      9013,
      '81wISI5W7bL._SL1500_.jpg',
      'Gang of Four',
      FALSE );

INSERT INTO product
    ( prod_no,
      register,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      reg_date,
      stock,
      category_no )
VALUES
    ( 9014,
      'seller3',
      'Core Java Volume I--Fundamentals',
      '자바 프로그래밍',
      TO_DATE('2023-03-23', 'YYYY-MM-DD'),
      46540,
      CURRENT_DATE,
      50,
      9002 );

INSERT INTO product_image
    ( image_no,
      prod_no,
      file_name,
      description,
      thumbnail )
VALUES
    ( NEXTVAL('seq_product_image_no'),
      9014,
      '61MFx7FNU3L._SL1500_.jpg',
      'Gang of Four',
      TRUE ),
    ( NEXTVAL('seq_product_image_no'),
      9014,
      '81YENpaZmkS._SL1500_.jpg',
      'Gang of Four',
      FALSE ),
    ( NEXTVAL('seq_product_image_no'),
      9014,
      '61EKthzqa-L._SL1500_.jpg',
      'Gang of Four',
      FALSE );

INSERT INTO product
    ( prod_no,
      register,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      reg_date,
      stock,
      category_no )
VALUES
    ( 9030,
      'seller3',
      'Effective Java',
      '자바를 효율적으로 사용하는 방법을 기술한 고급 자바 개발자를 위한 책',
      TO_DATE('2023-03-23', 'YYYY-MM-DD'),
      40000,
      CURRENT_DATE,
      50,
      9002 );

INSERT INTO product_image
    ( image_no,
      prod_no,
      file_name,
      description,
      thumbnail )
VALUES
    ( NEXTVAL('seq_product_image_no'),
      9030,
      '7167aaVxs3L._SL1500_.jpg',
      '이걸 알면 좋습니다',
      TRUE );

INSERT INTO product
    ( prod_no,
      register,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      reg_date,
      stock,
      category_no )
VALUES
    ( 9015,
      'seller3',
      'C++ Primer',
      'C++의 교과서',
      TO_DATE('2023-03-23', 'YYYY-MM-DD'),
      45000,
      CURRENT_DATE,
      50,
      9002 );

INSERT INTO product_image
    ( image_no,
      prod_no,
      file_name,
      description,
      thumbnail )
VALUES
    ( NEXTVAL('seq_product_image_no'),
      9015,
      '71vil4NdEPL._SL1500_.jpg',
      'C++ Primer',
      TRUE );

INSERT INTO product
    ( prod_no,
      register,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      reg_date,
      stock,
      category_no )
VALUES
    ( 9016,
      'seller3',
      'Java Concurrency in Practice',
      '자바 병렬 프로그래밍의 교과서',
      TO_DATE('2023-03-23', 'YYYY-MM-DD'),
      33680,
      CURRENT_DATE,
      20,
      9002 );

INSERT INTO product_image
    ( image_no,
      prod_no,
      file_name,
      description,
      thumbnail )
VALUES
    ( NEXTVAL('seq_product_image_no'),
      9016,
      '71PniFEjttL._SL1086_.jpg',
      'C++ Primer',
      TRUE );

INSERT INTO product
    ( prod_no,
      register,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      reg_date,
      stock,
      category_no )
VALUES
    ( 9017,
      'seller3',
      'Test Driven: TDD and Acceptance TDD for Java Developers',
      'TDD를 실무에서 사용할 일이 많이 있을지는 잘 모르겠지만 TDD를 알아야 건강한 코딩을 할 수 있습니다.',
      TO_DATE('2023-03-23', 'YYYY-MM-DD'),
      33680,
      CURRENT_DATE,
      30,
      9002 );

INSERT INTO product_image
    ( image_no,
      prod_no,
      file_name,
      description,
      thumbnail )
VALUES
    ( NEXTVAL('seq_product_image_no'),
      9017,
      '71I24F1EffL._SL1500_.jpg',
      'TDD',
      TRUE );

INSERT INTO product
    ( prod_no,
      register,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      reg_date,
      stock,
      category_no )
VALUES
    ( 9018,
      'seller3',
      'Clean Code',
      '깔끔한 코드를 작성하기 위한 교과서.',
      TO_DATE('2023-03-23', 'YYYY-MM-DD'),
      41130,
      CURRENT_DATE,
      30,
      9002 );

INSERT INTO product_image
    ( image_no,
      prod_no,
      file_name,
      description,
      thumbnail )
VALUES
    ( NEXTVAL('seq_product_image_no'),
      9018,
      '51E2055ZGUL._SL1000_.jpg',
      'Clean Code',
      TRUE );

INSERT INTO product
    ( prod_no,
      register,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      reg_date,
      stock,
      category_no )
VALUES
    ( 9019,
      'seller3',
      'Pro Git',
      'GIT을 알아야 취업이 됩니다.',
      TO_DATE('2023-03-23', 'YYYY-MM-DD'),
      43850,
      CURRENT_DATE,
      30,
      9002 );

INSERT INTO product_image
    ( image_no,
      prod_no,
      file_name,
      description,
      thumbnail )
VALUES
    ( NEXTVAL('seq_product_image_no'),
      9019,
      '61ueFgwHyIL._SL1020_.jpg',
      'Pro Git은 살 인터넷에 무료로 공개돼 있습니다.',
      TRUE );

INSERT INTO product
    ( prod_no,
      register,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      reg_date,
      stock,
      category_no )
VALUES
    ( 9020,
      'seller3',
      'TCP/IP Illustrated, Vol. 1: The Protocols',
      'TCP/IP의 바이블',
      TO_DATE('2023-03-23', 'YYYY-MM-DD'),
      58510,
      CURRENT_DATE,
      30,
      9002 );

INSERT INTO product_image
    ( image_no,
      prod_no,
      file_name,
      description,
      thumbnail )
VALUES
    ( NEXTVAL('seq_product_image_no'),
      9020,
      '91Ok5AaCC-L._SL1500_.jpg',
      '이걸 읽으면 TCP/IP에 대해서 많이 알게 된다고 하네요',
      TRUE );

INSERT INTO product
    ( prod_no,
      register,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      reg_date,
      stock,
      category_no )
VALUES
    ( 9021,
      'seller3',
      'Modern Java in Action: Lambdas, streams, functional and reactive programming',
      '모던 자바',
      TO_DATE('2023-03-23', 'YYYY-MM-DD'),
      54990,
      CURRENT_DATE,
      0,
      9002 );

INSERT INTO product_image
    ( image_no,
      prod_no,
      file_name,
      description,
      thumbnail )
VALUES
    ( NEXTVAL('seq_product_image_no'),
      9021,
      '71qoANDJu+L._SL1500_.jpg',
      '포스트모던 자바',
      TRUE );

INSERT INTO product
    ( prod_no,
      register,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      reg_date,
      stock,
      category_no )
VALUES
    ( 9022,
      'seller3',
      'Operating System Concepts',
      '운영체제 책은 표지에 공룡 있는 책 사라고 그랬어요',
      TO_DATE('2023-03-23', 'YYYY-MM-DD'),
      89950,
      CURRENT_DATE,
      5,
      9002 );

INSERT INTO product_image
    ( image_no,
      prod_no,
      file_name,
      description,
      thumbnail )
VALUES
    ( NEXTVAL('seq_product_image_no'),
      9022,
      '81SwKCia7VL._SL1500_.jpg',
      '공룡책',
      TRUE );

INSERT INTO product
    ( prod_no,
      register,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      reg_date,
      stock,
      category_no )
VALUES
    ( 9023,
      'seller3',
      'HTTP: The Definitive Guide',
      '요즘 네트워크 통신은 HTTP 기반이 제일 많다고 하네요.',
      TO_DATE('2023-03-23', 'YYYY-MM-DD'),
      89950,
      CURRENT_DATE,
      15,
      9002 );

INSERT INTO product_image
    ( image_no,
      prod_no,
      file_name,
      description,
      thumbnail )
VALUES
    ( NEXTVAL('seq_product_image_no'),
      9023,
      '91CZ5e4UeHL._SL1500_.jpg',
      '다람쥐',
      TRUE ),
    ( NEXTVAL('seq_product_image_no'),
      9023,
      '71s9AMZ9UlL._SL1360_.jpg',
      '다람쥐 작음',
      FALSE );

INSERT INTO product
    ( prod_no,
      register,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      reg_date,
      stock,
      category_no )
VALUES
    ( 9024,
      'seller3',
      'Database Design and Implementation',
      '자바를 사용해서 DBMS를 바닥부터 깡으로 구현해 보는 책이라고 합니다. 저도 언젠간 읽고 싶네요.',
      TO_DATE('2023-03-23', 'YYYY-MM-DD'),
      100000,
      CURRENT_DATE,
      15,
      9002 );

INSERT INTO product_image
    ( image_no,
      prod_no,
      file_name,
      description,
      thumbnail )
VALUES
    ( NEXTVAL('seq_product_image_no'),
      9024,
      '61FyhGfuL5L._AC_UF1000,1000_QL80_.jpg',
      '집 가고 싶다.',
      TRUE );

INSERT INTO transaction
VALUES
    ( NEXTVAL('seq_transaction_tran_no'),
      'user02',
      '1',
      'Nothing',
      '010-1234-1234',
      'asdfasohfiw',
      'request sample',
      '1',
      TO_DATE('2017/2/8', 'YYYY/MM/DD'),
      TO_DATE('2017/2/15', 'YYYY/MM/DD') );

INSERT INTO transaction
VALUES
    ( NEXTVAL('seq_transaction_tran_no'),
      'user01',
      '1',
      'Nothing',
      '010-1234-1234',
      'asdfasohfiw',
      'request sample',
      '1',
      TO_DATE('2017/2/8', 'YYYY/MM/DD'),
      TO_DATE('2017/2/15', 'YYYY/MM/DD') );

INSERT INTO transaction
VALUES
    ( NEXTVAL('seq_transaction_tran_no'),
      'user23',
      '1',
      'Nothing',
      '010-1234-1234',
      'asdfasohfiw',
      'request sample',
      '2',
      TO_DATE('2017/2/8', 'YYYY/MM/DD'),
      TO_DATE('2017/2/15', 'YYYY/MM/DD') );

INSERT INTO transaction
VALUES
    ( NEXTVAL('seq_transaction_tran_no'),
      'user08',
      '1',
      'Nothing',
      '010-1234-1234',
      'asdfasohfiw',
      'request sample',
      '2',
      TO_DATE('2017/2/8', 'YYYY/MM/DD'),
      TO_DATE('2017/2/15', 'YYYY/MM/DD') );

INSERT INTO transaction
VALUES
    ( NEXTVAL('seq_transaction_tran_no'),
      'user08',
      '1',
      'Nothing',
      '010-1234-1234',
      'asdfasohfiw',
      'request sample',
      '2',
      TO_DATE('2017/2/8', 'YYYY/MM/DD'),
      TO_DATE('2017/2/15', 'YYYY/MM/DD') );

INSERT INTO transaction
VALUES
    ( NEXTVAL('seq_transaction_tran_no'),
      'user08',
      '1',
      'Nothing',
      '010-1234-1234',
      'asdfasohfiw',
      'request sample',
      '1',
      TO_DATE('2017/2/8', 'YYYY/MM/DD'),
      TO_DATE('2017/2/15', 'YYYY/MM/DD') );

INSERT INTO transaction
VALUES
    ( NEXTVAL('seq_transaction_tran_no'),
      'user08',
      '1',
      'Nothing',
      '010-1234-1234',
      'asdfasohfiw',
      'request sample',
      '1',
      TO_DATE('2017/2/8', 'YYYY/MM/DD'),
      TO_DATE('2017/2/15', 'YYYY/MM/DD') );

INSERT INTO transaction_prod
    ( tran_no,
      prod_no,
      quantity )
VALUES
    ( 10000,
      9000,
      2 );

INSERT INTO transaction_prod
    ( tran_no,
      prod_no,
      quantity )
VALUES
    ( 10000,
      9001,
      1 );

INSERT INTO transaction_prod
    ( tran_no,
      prod_no,
      quantity )
VALUES
    ( 10001,
      9002,
      3 );

INSERT INTO transaction_prod
    ( tran_no,
      prod_no,
      quantity )
VALUES
    ( 10001,
      9003,
      4 );

INSERT INTO transaction_prod
    ( tran_no,
      prod_no,
      quantity )
VALUES
    ( 10002,
      9012,
      1 );

INSERT INTO transaction_prod
    ( tran_no,
      prod_no,
      quantity )
VALUES
    ( 10003,
      9003,
      5 );

INSERT INTO transaction_prod
    ( tran_no,
      prod_no,
      quantity )
VALUES
    ( 10004,
      9005,
      1 );

INSERT INTO transaction_prod
    ( tran_no,
      prod_no,
      quantity )
VALUES
    ( 10005,
      9006,
      1 );

INSERT INTO transaction_prod
    ( tran_no,
      prod_no,
      quantity )
VALUES
    ( 10005,
      9007,
      2 );

INSERT INTO transaction_prod
    ( tran_no,
      prod_no,
      quantity )
VALUES
    ( 10006,
      9005,
      1 );

INSERT INTO transaction_prod
    ( tran_no,
      prod_no,
      quantity )
VALUES
    ( 10006,
      9010,
      2 );

INSERT INTO category
VALUES
    ( NEXTVAL('seq_category_category_no'),
      'sample-category' );

COMMIT;