DROP TABLE transaction_prod;

DROP TABLE transaction;

DROP TABLE product;

DROP TABLE category;

DROP TABLE users;

DROP SEQUENCE seq_product_prod_no;

DROP SEQUENCE seq_transaction_tran_no;

DROP SEQUENCE seq_category_category_no;

CREATE SEQUENCE seq_product_prod_no INCREMENT BY 1 START WITH 10000;

CREATE SEQUENCE seq_transaction_tran_no INCREMENT BY 1 START WITH 10000;

CREATE SEQUENCE seq_category_category_no INCREMENT BY 1 START WITH 10000;

CREATE TABLE users
(
    user_id    VARCHAR2(20),
    user_name  VARCHAR2(50) NOT NULL,
    password   VARCHAR2(10) NOT NULL,
    role       VARCHAR2(6) DEFAULT 'user',
    ssn        VARCHAR2(13),
    cell_phone VARCHAR2(14),
    addr       VARCHAR2(100),
    email      VARCHAR2(50),
    reg_date   DATE DEFAULT sysdate,
    CONSTRAINT users_pk PRIMARY KEY (user_id)
);

CREATE TABLE subscription
(
    subscriber_id VARCHAR(20),
    seller_id     VARCHAR(20),
    CONSTRAINT subscription_pk PRIMARY KEY (subscriber_id, seller_id),
    CONSTRAINT subscription_subscriber_fk FOREIGN KEY (subscriber_id) REFERENCES users (user_id),
    CONSTRAINT subscription_seller_fk FOREIGN KEY (seller_id) REFERENCES users (user_id)
);

CREATE TABLE category
(
    category_no   NUMBER(16),
    category_name VARCHAR2(100) NOT NULL,
    PRIMARY KEY (category_no),
    UNIQUE (category_name)
);

CREATE TABLE product
(
    prod_no         NUMBER(16),
    register        VARCHAR2(20),
    prod_name       VARCHAR2(100) NOT NULL,
    prod_detail     VARCHAR2(200),
    manufacture_day DATE DEFAULT sysdate,
    price           NUMBER(10) NOT NULL,
    image_file      VARCHAR2(100),
    reg_date        DATE DEFAULT sysdate,
    stock           NUMBER(10) NOT NULL,
    category_no     NUMBER(16),
    CONSTRAINT product_pk PRIMARY KEY (prod_no),
    CONSTRAINT prod_category_fk FOREIGN KEY (category_no) REFERENCES category (category_no),
    CONSTRAINT prod_register_fk FOREIGN KEY (register) REFERENCES users (user_id)
);

CREATE TABLE transaction
(
    tran_no          NUMBER(16),
    buyer_id         VARCHAR2(20) NOT NULL,
    payment_option   CHAR(3),
    receiver_name    VARCHAR2(20),
    receiver_phone   VARCHAR2(14),
    demailaddr       VARCHAR2(100),
    dlvy_request     VARCHAR2(100),
    tran_status_code CHAR(3),
    order_date       DATE DEFAULT sysdate,
    dlvy_date        DATE DEFAULT sysdate,
    CONSTRAINT transaction_pk PRIMARY KEY (tran_no),
    CONSTRAINT transaction_buyer_id FOREIGN KEY (buyer_id) REFERENCES users (user_id),
    CONSTRAINT transaction_payment_option CHECK (payment_option IN ('0', '1')),
    CONSTRAINT transaction_trans_status_code CHECK (tran_status_code IN ('0', '1', '2', '3'))
);

CREATE TABLE transaction_prod
(
    tran_no  NUMBER(16) NOT NULL,
    prod_no  NUMBER(16) NOT NULL,
    quantity NUMBER(10) NOT NULL,
    CONSTRAINT tp_pk PRIMARY KEY (tran_no, prod_no),
    CONSTRAINT tp_tran_no_fk FOREIGN KEY (tran_no) REFERENCES transaction (tran_no),
    CONSTRAINT tp_prod_no_fk FOREIGN KEY (prod_no) REFERENCES product (prod_no),
    CONSTRAINT tp_quantity_num_range CHECK (quantity >= 0)
);

INSERT INTO users
    ( user_id,
      user_name,
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
      'admin',
      NULL,
      NULL,
      '서울시 서초구',
      'admin@mvc.com',
      to_date('2012/01/14 10:48:43', 'YYYY/MM/DD HH24:MI:SS') );

INSERT INTO users
    ( user_id,
      user_name,
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
      'admin',
      NULL,
      NULL,
      NULL,
      'manager@mvc.com',
      to_date('2012/01/14 10:48:43', 'YYYY/MM/DD HH24:MI:SS') );

INSERT INTO users
VALUES
    ( 'user01',
      'SCOTT',
      '1111',
      'user',
      NULL,
      NULL,
      NULL,
      NULL,
      sysdate );

INSERT INTO users
VALUES
    ( 'user02',
      'SCOTT',
      '2222',
      'user',
      NULL,
      NULL,
      NULL,
      NULL,
      sysdate );

INSERT INTO users
VALUES
    ( 'user03',
      'SCOTT',
      '3333',
      'user',
      NULL,
      NULL,
      NULL,
      NULL,
      sysdate );

INSERT INTO users
VALUES
    ( 'user04',
      'SCOTT',
      '4444',
      'user',
      NULL,
      NULL,
      NULL,
      NULL,
      sysdate );

INSERT INTO users
VALUES
    ( 'user05',
      'SCOTT',
      '5555',
      'user',
      NULL,
      NULL,
      NULL,
      NULL,
      sysdate );

INSERT INTO users
VALUES
    ( 'user06',
      'SCOTT',
      '6666',
      'user',
      NULL,
      NULL,
      NULL,
      NULL,
      sysdate );

INSERT INTO users
VALUES
    ( 'user07',
      'SCOTT',
      '7777',
      'user',
      NULL,
      NULL,
      NULL,
      NULL,
      sysdate );

INSERT INTO users
VALUES
    ( 'user08',
      'SCOTT',
      '8888',
      'user',
      NULL,
      NULL,
      NULL,
      NULL,
      sysdate );

INSERT INTO users
VALUES
    ( 'user09',
      'SCOTT',
      '9999',
      'user',
      NULL,
      NULL,
      NULL,
      NULL,
      sysdate );

INSERT INTO users
VALUES
    ( 'user10',
      'SCOTT',
      '1010',
      'user',
      NULL,
      NULL,
      NULL,
      NULL,
      sysdate );

INSERT INTO users
VALUES
    ( 'user11',
      'SCOTT',
      '1111',
      'user',
      NULL,
      NULL,
      NULL,
      NULL,
      sysdate );

INSERT INTO users
VALUES
    ( 'user12',
      'SCOTT',
      '1212',
      'user',
      NULL,
      NULL,
      NULL,
      NULL,
      sysdate );

INSERT INTO users
VALUES
    ( 'user13',
      'SCOTT',
      '1313',
      'user',
      NULL,
      NULL,
      NULL,
      NULL,
      sysdate );

INSERT INTO users
VALUES
    ( 'user14',
      'SCOTT',
      '1414',
      'user',
      NULL,
      NULL,
      NULL,
      NULL,
      sysdate );

INSERT INTO users
VALUES
    ( 'user15',
      'SCOTT',
      '1515',
      'user',
      NULL,
      NULL,
      NULL,
      NULL,
      sysdate );

INSERT INTO users
VALUES
    ( 'user16',
      'SCOTT',
      '1616',
      'user',
      NULL,
      NULL,
      NULL,
      NULL,
      sysdate );

INSERT INTO users
VALUES
    ( 'user17',
      'SCOTT',
      '1717',
      'user',
      NULL,
      NULL,
      NULL,
      NULL,
      sysdate );

INSERT INTO users
VALUES
    ( 'user18',
      'SCOTT',
      '1818',
      'user',
      NULL,
      NULL,
      NULL,
      NULL,
      sysdate );

INSERT INTO users
VALUES
    ( 'user19',
      'SCOTT',
      '1919',
      'user',
      NULL,
      NULL,
      NULL,
      NULL,
      sysdate );

INSERT INTO users
VALUES
    ( 'user20',
      'SCOTT',
      '2020',
      'user',
      NULL,
      NULL,
      NULL,
      NULL,
      sysdate );

INSERT INTO users
VALUES
    ( 'user21',
      'SCOTT',
      '2121',
      'user',
      NULL,
      NULL,
      NULL,
      NULL,
      sysdate );

INSERT INTO users
VALUES
    ( 'user22',
      'SCOTT',
      '2222',
      'user',
      NULL,
      NULL,
      NULL,
      NULL,
      sysdate );

INSERT INTO users
VALUES
    ( 'user23',
      'SCOTT',
      '2323',
      'user',
      NULL,
      NULL,
      NULL,
      NULL,
      sysdate );

INSERT INTO product
    ( prod_no,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      image_file,
      reg_date,
      stock )
VALUES
    ( seq_product_prod_no.nextval,
      'vaio vgn FS70B',
      '소니 바이오 노트북 신동품',
      to_date('2012/05/14', 'YYYY-MM-DD'),
      2000000,
      'AHlbAAAAtBqyWAAA.jpg',
      to_date('2012/12/14 11:27:27', 'YYYY/MM/DD HH24:MI:SS'),
      10 );

INSERT INTO product
    ( prod_no,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      image_file,
      reg_date,
      stock )
VALUES
    ( seq_product_prod_no.nextval,
      '자전거',
      '자전거 좋아요~',
      to_date('2012/05/14', 'YYYY-MM-DD'),
      10000,
      'AHlbAAAAvetFNwAA.jpg',
      to_date('2012/11/14 10:48:43', 'YYYY/MM/DD HH24:MI:SS'),
      8 );

INSERT INTO product
    ( prod_no,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      image_file,
      reg_date,
      stock )
VALUES
    ( seq_product_prod_no.nextval,
      '보르도',
      '최고 디자인 신품',
      to_date('2012/02/01', 'YYYY-MM-DD'),
      1170000,
      'AHlbAAAAvewfegAB.jpg',
      to_date('2012/10/14 10:49:39', 'YYYY/MM/DD HH24:MI:SS'),
      0 );

INSERT INTO product
    ( prod_no,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      image_file,
      reg_date,
      stock )
VALUES
    ( seq_product_prod_no.nextval,
      '보드세트',
      '한시즌 밖에 안썼습니다. 눈물을 머금고 내놓음 ㅠ.ㅠ',
      to_date('2012/02/17', 'YYYY-MM-DD'),
      200000,
      'AHlbAAAAve1WwgAC.jpg',
      to_date('2012/11/14 10:50:58', 'YYYY/MM/DD HH24:MI:SS'),
      13 );

INSERT INTO product
    ( prod_no,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      image_file,
      reg_date,
      stock )
VALUES
    ( seq_product_prod_no.nextval,
      '인라인',
      '좋아욥',
      to_date('2012/08/19', 'YYYY-MM-DD'),
      20000,
      'AHlbAAAAve37LwAD.jpg',
      to_date('2012/11/14 10:51:40', 'YYYY/MM/DD HH24:MI:SS'),
      2 );

INSERT INTO product
    ( prod_no,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      image_file,
      reg_date,
      stock )
VALUES
    ( seq_product_prod_no.nextval,
      '삼성센스 2G',
      'sens 메모리 2Giga',
      to_date('2012/11/21', 'YYYY-MM-DD'),
      800000,
      'AHlbAAAAtBqyWAAA.jpg',
      to_date('2012/11/14 18:46:58', 'YYYY/MM/DD HH24:MI:SS'),
      1 );

INSERT INTO product
    ( prod_no,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      image_file,
      reg_date,
      stock )
VALUES
    ( seq_product_prod_no.nextval,
      '연꽃',
      '정원을 가꿔보세요',
      to_date('2012/10/22', 'YYYY-MM-DD'),
      232300,
      'AHlbAAAAtDPSiQAA.jpg',
      to_date('2012/11/15 17:39:01', 'YYYY/MM/DD HH24:MI:SS'),
      0 );

INSERT INTO product
    ( prod_no,
      prod_name,
      prod_detail,
      manufacture_day,
      price,
      image_file,
      reg_date,
      stock )
VALUES
    ( seq_product_prod_no.nextval,
      '삼성센스',
      '노트북',
      to_date('2012/02/12', 'YYYY-MM-DD'),
      600000,
      'AHlbAAAAug1vsgAA.jpg',
      to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'),
      0 );

INSERT INTO transaction
VALUES
    ( seq_transaction_tran_no.nextval,
      'user02',
      '1',
      'Nothing',
      '010-1234-1234',
      'asdfasohfiw',
      'request sample',
      '1',
      to_date('2017/2/8', 'YYYY/MM/DD'),
      to_date('2017/2/15', 'YYYY/MM/DD') );

INSERT INTO transaction
VALUES
    ( seq_transaction_tran_no.nextval,
      'user01',
      '1',
      'Nothing',
      '010-1234-1234',
      'asdfasohfiw',
      'request sample',
      '2',
      to_date('2017/2/8', 'YYYY/MM/DD'),
      to_date('2017/2/15', 'YYYY/MM/DD') );

INSERT INTO transaction
VALUES
    ( seq_transaction_tran_no.nextval,
      'user23',
      '1',
      'Nothing',
      '010-1234-1234',
      'asdfasohfiw',
      'request sample',
      '2',
      to_date('2017/2/8', 'YYYY/MM/DD'),
      to_date('2017/2/15', 'YYYY/MM/DD') );

INSERT INTO transaction
VALUES
    ( seq_transaction_tran_no.nextval,
      'user08',
      '1',
      'Nothing',
      '010-1234-1234',
      'asdfasohfiw',
      'request sample',
      '2',
      to_date('2017/2/8', 'YYYY/MM/DD'),
      to_date('2017/2/15', 'YYYY/MM/DD') );

INSERT INTO transaction
VALUES
    ( seq_transaction_tran_no.nextval,
      'user08',
      '1',
      'Nothing',
      '010-1234-1234',
      'asdfasohfiw',
      'request sample',
      '2',
      to_date('2017/2/8', 'YYYY/MM/DD'),
      to_date('2017/2/15', 'YYYY/MM/DD') );

INSERT INTO transaction
VALUES
    ( seq_transaction_tran_no.nextval,
      'user08',
      '1',
      'Nothing',
      '010-1234-1234',
      'asdfasohfiw',
      'request sample',
      '2',
      to_date('2017/2/8', 'YYYY/MM/DD'),
      to_date('2017/2/15', 'YYYY/MM/DD') );

INSERT INTO transaction
VALUES
    ( seq_transaction_tran_no.nextval,
      'user08',
      '1',
      'Nothing',
      '010-1234-1234',
      'asdfasohfiw',
      'request sample',
      '2',
      to_date('2017/2/8', 'YYYY/MM/DD'),
      to_date('2017/2/15', 'YYYY/MM/DD') );

INSERT INTO transaction_prod
    ( tran_no,
      prod_no,
      quantity )
VALUES
    ( 10000,
      10000,
      2 );

INSERT INTO transaction_prod
    ( tran_no,
      prod_no,
      quantity )
VALUES
    ( 10000,
      10001,
      1 );

INSERT INTO transaction_prod
    ( tran_no,
      prod_no,
      quantity )
VALUES
    ( 10001,
      10000,
      3 );

INSERT INTO transaction_prod
    ( tran_no,
      prod_no,
      quantity )
VALUES
    ( 10001,
      10002,
      4 );

INSERT INTO transaction_prod
    ( tran_no,
      prod_no,
      quantity )
VALUES
    ( 10002,
      10000,
      1 );

INSERT INTO transaction_prod
    ( tran_no,
      prod_no,
      quantity )
VALUES
    ( 10003,
      10004,
      5 );

INSERT INTO transaction_prod
    ( tran_no,
      prod_no,
      quantity )
VALUES
    ( 10004,
      10005,
      1 );

INSERT INTO transaction_prod
    ( tran_no,
      prod_no,
      quantity )
VALUES
    ( 10005,
      10005,
      1 );

INSERT INTO transaction_prod
    ( tran_no,
      prod_no,
      quantity )
VALUES
    ( 10005,
      10006,
      2 );

INSERT INTO transaction_prod
    ( tran_no,
      prod_no,
      quantity )
VALUES
    ( 10006,
      10007,
      1 );

INSERT INTO transaction_prod
    ( tran_no,
      prod_no,
      quantity )
VALUES
    ( 10006,
      10005,
      2 );

INSERT INTO category
VALUES
    ( seq_category_category_no.nextval,
      'sample-category' );

UPDATE product
SET
    category_no = (
        SELECT
            category_no
        FROM category
        WHERE category_name = 'sample-category'
    );

COMMIT;
