DROP TABLE transaction;

DROP TABLE product;

DROP TABLE users;

DROP SEQUENCE seq_product_prod_no;

DROP SEQUENCE seq_transaction_tran_no;

CREATE SEQUENCE seq_product_prod_no INCREMENT BY 1 START WITH 10000;

CREATE SEQUENCE seq_transaction_tran_no INCREMENT BY 1 START WITH 10000;

CREATE TABLE users (
    user_id VARCHAR2(20),
    user_name VARCHAR2(50) NOT NULL,
    password VARCHAR2(10) NOT NULL,
    role VARCHAR2(5) DEFAULT 'user',
    ssn VARCHAR2(13),
    cell_phone VARCHAR2(14),
    addr VARCHAR2(100),
    email VARCHAR2(50),
    reg_date DATE DEFAULT SYSDATE,
    CONSTRAINT users_pk PRIMARY KEY(user_id),
    CONSTRAINT users_role_check CHECK (role IN ('user', 'admin'))
);

CREATE TABLE product (
    prod_no NUMBER(16),
    prod_name VARCHAR2(100) NOT NULL,
    prod_detail VARCHAR2(200),
    manufacture_day DATE DEFAULT SYSDATE,
    price NUMBER(10) NOT NULL,
    image_file VARCHAR2(100),
    reg_date DATE DEFAULT SYSDATE,
    stock NUMBER(10) NOT NULL,
    CONSTRAINT product_pk PRIMARY KEY(prod_no)
);

CREATE TABLE transaction (
    tran_no NUMBER(16),
    buyer_id VARCHAR2(20) NOT NULL,
    payment_option CHAR(3),
    receiver_name VARCHAR2(20),
    receiver_phone VARCHAR2(14),
    demailaddr VARCHAR2(100),
    dlvy_request VARCHAR2(100),
    tran_status_code CHAR(3),
    order_date DATE DEFAULT SYSDATE,
    dlvy_date DATE DEFAULT SYSDATE,
    CONSTRAINT transaction_pk PRIMARY KEY(tran_no),
    CONSTRAINT transaction_buyer_id FOREIGN KEY(buyer_id) REFERENCES users(user_id),
    CONSTRAINT transaction_payment_option CHECK (payment_option IN ('0', '1')),
    CONSTRAINT transaction_trans_status_code CHECK (tran_status_code IN ('0', '1', '2', '3'))
);

CREATE TABLE transaction_prod (
    tran_no NUMBER(16) NOT NULL,
    prod_no NUMBER(16) NOT NULL,
    quantity NUMBER(10) NOT NULL,
    CONSTRAINT tp_pk PRIMARY KEY(tran_no, prod_no),
    CONSTRAINT tp_tran_no_fk FOREIGN KEY(tran_no) REFERENCES transaction(tran_no),
    CONSTRAINT tp_prod_no_fk FOREIGN KEY(prod_no) REFERENCES product(prod_no),
    CONSTRAINT tp_quantity_num_range CHECK (quantity >= 0)
);

INSERT INTO
    users (
        user_id,
        user_name,
        password,
        role,
        ssn,
        cell_phone,
        addr,
        email,
        reg_date
    )
VALUES
    (
        'admin',
        'admin',
        '1234',
        'admin',
        NULL,
        NULL,
        '����� ���ʱ�',
        'admin@mvc.com',
        to_date('2012/01/14 10:48:43', 'YYYY/MM/DD HH24:MI:SS')
    );

INSERT INTO
    users (
        user_id,
        user_name,
        password,
        role,
        ssn,
        cell_phone,
        addr,
        email,
        reg_date
    )
VALUES
    (
        'manager',
        'manager',
        '1234',
        'admin',
        NULL,
        NULL,
        NULL,
        'manager@mvc.com',
        to_date('2012/01/14 10:48:43', 'YYYY/MM/DD HH24:MI:SS')
    );

INSERT INTO
    users
VALUES
    (
        'user01',
        'SCOTT',
        '1111',
        'user',
        NULL,
        NULL,
        NULL,
        NULL,
        sysdate
    );

INSERT INTO
    users
VALUES
    (
        'user02',
        'SCOTT',
        '2222',
        'user',
        NULL,
        NULL,
        NULL,
        NULL,
        sysdate
    );

INSERT INTO
    users
VALUES
    (
        'user03',
        'SCOTT',
        '3333',
        'user',
        NULL,
        NULL,
        NULL,
        NULL,
        sysdate
    );

INSERT INTO
    users
VALUES
    (
        'user04',
        'SCOTT',
        '4444',
        'user',
        NULL,
        NULL,
        NULL,
        NULL,
        sysdate
    );

INSERT INTO
    users
VALUES
    (
        'user05',
        'SCOTT',
        '5555',
        'user',
        NULL,
        NULL,
        NULL,
        NULL,
        sysdate
    );

INSERT INTO
    users
VALUES
    (
        'user06',
        'SCOTT',
        '6666',
        'user',
        NULL,
        NULL,
        NULL,
        NULL,
        sysdate
    );

INSERT INTO
    users
VALUES
    (
        'user07',
        'SCOTT',
        '7777',
        'user',
        NULL,
        NULL,
        NULL,
        NULL,
        sysdate
    );

INSERT INTO
    users
VALUES
    (
        'user08',
        'SCOTT',
        '8888',
        'user',
        NULL,
        NULL,
        NULL,
        NULL,
        sysdate
    );

INSERT INTO
    users
VALUES
    (
        'user09',
        'SCOTT',
        '9999',
        'user',
        NULL,
        NULL,
        NULL,
        NULL,
        sysdate
    );

INSERT INTO
    users
VALUES
    (
        'user10',
        'SCOTT',
        '1010',
        'user',
        NULL,
        NULL,
        NULL,
        NULL,
        sysdate
    );

INSERT INTO
    users
VALUES
    (
        'user11',
        'SCOTT',
        '1111',
        'user',
        NULL,
        NULL,
        NULL,
        NULL,
        sysdate
    );

INSERT INTO
    users
VALUES
    (
        'user12',
        'SCOTT',
        '1212',
        'user',
        NULL,
        NULL,
        NULL,
        NULL,
        sysdate
    );

INSERT INTO
    users
VALUES
    (
        'user13',
        'SCOTT',
        '1313',
        'user',
        NULL,
        NULL,
        NULL,
        NULL,
        sysdate
    );

INSERT INTO
    users
VALUES
    (
        'user14',
        'SCOTT',
        '1414',
        'user',
        NULL,
        NULL,
        NULL,
        NULL,
        sysdate
    );

INSERT INTO
    users
VALUES
    (
        'user15',
        'SCOTT',
        '1515',
        'user',
        NULL,
        NULL,
        NULL,
        NULL,
        sysdate
    );

INSERT INTO
    users
VALUES
    (
        'user16',
        'SCOTT',
        '1616',
        'user',
        NULL,
        NULL,
        NULL,
        NULL,
        sysdate
    );

INSERT INTO
    users
VALUES
    (
        'user17',
        'SCOTT',
        '1717',
        'user',
        NULL,
        NULL,
        NULL,
        NULL,
        sysdate
    );

INSERT INTO
    users
VALUES
    (
        'user18',
        'SCOTT',
        '1818',
        'user',
        NULL,
        NULL,
        NULL,
        NULL,
        sysdate
    );

INSERT INTO
    users
VALUES
    (
        'user19',
        'SCOTT',
        '1919',
        'user',
        NULL,
        NULL,
        NULL,
        NULL,
        sysdate
    );

INSERT INTO
    users
VALUES
    (
        'user20',
        'SCOTT',
        '2020',
        'user',
        NULL,
        NULL,
        NULL,
        NULL,
        sysdate
    );

INSERT INTO
    users
VALUES
    (
        'user21',
        'SCOTT',
        '2121',
        'user',
        NULL,
        NULL,
        NULL,
        NULL,
        sysdate
    );

INSERT INTO
    users
VALUES
    (
        'user22',
        'SCOTT',
        '2222',
        'user',
        NULL,
        NULL,
        NULL,
        NULL,
        sysdate
    );

INSERT INTO
    users
VALUES
    (
        'user23',
        'SCOTT',
        '2323',
        'user',
        NULL,
        NULL,
        NULL,
        NULL,
        sysdate
    );

insert into
    product
values
    (
        10000,
        'vaio vgn FS70B',
        '�Ҵ� ���̿� ��Ʈ�� �ŵ�ǰ',
        TO_DATE('2012/05/14', 'YYYY-MM-DD'),
        2000000,
        'AHlbAAAAtBqyWAAA.jpg',
        to_date('2012/12/14 11:27:27', 'YYYY/MM/DD HH24:MI:SS'),
        10
    );

insert into
    product
values
    (
        10001,
        '������',
        '������ ���ƿ�~',
        TO_DATE('2012/05/14', 'YYYY-MM-DD'),
        10000,
        'AHlbAAAAvetFNwAA.jpg',
        to_date('2012/11/14 10:48:43', 'YYYY/MM/DD HH24:MI:SS'),
        8
    );

insert into
    product
values
    (
        10002,
        '������',
        '�ְ� ������ ��ǰ',
        TO_DATE('2012/02/01', 'YYYY-MM-DD'),
        1170000,
        'AHlbAAAAvewfegAB.jpg',
        to_date('2012/10/14 10:49:39', 'YYYY/MM/DD HH24:MI:SS'),
        0
    );

insert into
    product
values
    (
        10003,
        '���弼Ʈ',
        '�ѽ��� �ۿ� �Ƚ���ϴ�. ������ �ӱݰ� ������ ��.��',
        TO_DATE('2012/02/17', 'YYYY-MM-DD'),
        200000,
        'AHlbAAAAve1WwgAC.jpg',
        to_date('2012/11/14 10:50:58', 'YYYY/MM/DD HH24:MI:SS'),
        13
    );

insert into
    product
values
    (
        10004,
        '�ζ���',
        '���ƿ�',
        TO_DATE('2012/08/19', 'YYYY-MM-DD'),
        20000,
        'AHlbAAAAve37LwAD.jpg',
        to_date('2012/11/14 10:51:40', 'YYYY/MM/DD HH24:MI:SS'),
        2
    );

insert into
    product
values
    (
        10005,
        '�Ｚ���� 2G',
        'sens �޸� 2Giga',
        TO_DATE('2012/11/21', 'YYYY-MM-DD'),
        800000,
        'AHlbAAAAtBqyWAAA.jpg',
        to_date('2012/11/14 18:46:58', 'YYYY/MM/DD HH24:MI:SS'),
        1
    );

insert into
    product
values
    (
        10006,
        '����',
        '������ ���㺸����',
        TO_DATE('2012/10/22', 'YYYY-MM-DD'),
        232300,
        'AHlbAAAAtDPSiQAA.jpg',
        to_date('2012/11/15 17:39:01', 'YYYY/MM/DD HH24:MI:SS'),
        0
    );

insert into
    product
values
    (
        10007,
        '�Ｚ����',
        '��Ʈ��',
        TO_DATE('2012/02/12', 'YYYY-MM-DD'),
        600000,
        'AHlbAAAAug1vsgAA.jpg',
        to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'),
        0
    );

INSERT INTO
    transaction
VALUES
    (
        10000,
        'user02',
        '1',
        'Nothing',
        '010-1234-1234',
        'asdfasohfiw',
        'request sample',
        '1',
        TO_DATE('2017/2/8', 'YYYY/MM/DD'),
        TO_DATE('2017/2/15', 'YYYY/MM/DD')
    );

INSERT INTO
    transaction
VALUES
    (
        10001,
        'user01',
        '1',
        'Nothing',
        '010-1234-1234',
        'asdfasohfiw',
        'request sample',
        '2',
        TO_DATE('2017/2/8', 'YYYY/MM/DD'),
        TO_DATE('2017/2/15', 'YYYY/MM/DD')
    );

INSERT INTO
    transaction
VALUES
    (
        10002,
        'user23',
        '1',
        'Nothing',
        '010-1234-1234',
        'asdfasohfiw',
        'request sample',
        '2',
        TO_DATE('2017/2/8', 'YYYY/MM/DD'),
        TO_DATE('2017/2/15', 'YYYY/MM/DD')
    );

INSERT INTO
    transaction
VALUES
    (
        10003,
        'user08',
        '1',
        'Nothing',
        '010-1234-1234',
        'asdfasohfiw',
        'request sample',
        '2',
        TO_DATE('2017/2/8', 'YYYY/MM/DD'),
        TO_DATE('2017/2/15', 'YYYY/MM/DD')
    );

INSERT INTO
    transaction
VALUES
    (
        10004,
        'user08',
        '1',
        'Nothing',
        '010-1234-1234',
        'asdfasohfiw',
        'request sample',
        '2',
        TO_DATE('2017/2/8', 'YYYY/MM/DD'),
        TO_DATE('2017/2/15', 'YYYY/MM/DD')
    );

INSERT INTO
    transaction
VALUES
    (
        10005,
        'user08',
        '1',
        'Nothing',
        '010-1234-1234',
        'asdfasohfiw',
        'request sample',
        '2',
        TO_DATE('2017/2/8', 'YYYY/MM/DD'),
        TO_DATE('2017/2/15', 'YYYY/MM/DD')
    );

INSERT INTO
    transaction
VALUES
    (
        10006,
        'user08',
        '1',
        'Nothing',
        '010-1234-1234',
        'asdfasohfiw',
        'request sample',
        '2',
        TO_DATE('2017/2/8', 'YYYY/MM/DD'),
        TO_DATE('2017/2/15', 'YYYY/MM/DD')
    );

INSERT INTO transaction_prod
    (tran_no, prod_no, quantity)
VALUES
    (10000, 10000, 2);

INSERT INTO transaction_prod
    (tran_no, prod_no, quantity)
VALUES
    (10000, 10001, 1);

INSERT INTO transaction_prod
    (tran_no, prod_no, quantity)
VALUES
    (10001, 10000, 3);

INSERT INTO transaction_prod
    (tran_no, prod_no, quantity)
VALUES
    (10001, 10002, 4);

INSERT INTO transaction_prod
    (tran_no, prod_no, quantity)
VALUES
    (10002, 10000, 1);

INSERT INTO transaction_prod
    (tran_no, prod_no, quantity)
VALUES
    (10003, 10004, 5);

INSERT INTO transaction_prod
    (tran_no, prod_no, quantity)
VALUES
    (10004, 10005, 1);

INSERT INTO transaction_prod
    (tran_no, prod_no, quantity)
VALUES
    (10006, 10007, 1);

INSERT INTO transaction_prod
    (tran_no, prod_no, quantity)
VALUES
    (10006, 10005, 2);

-- Additional data
-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product0',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         47400,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product1',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         30400,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product2',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         82300,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product3',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         36900,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product4',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         39900,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product5',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         32100,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product6',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         29700,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product7',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         2100,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product8',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         18700,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product9',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         600,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product10',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         49800,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product11',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         49600,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product12',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         84400,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product13',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         67200,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product14',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         29600,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product15',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         27100,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product16',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         80900,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product17',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         4600,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product18',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         33400,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product19',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         11300,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product20',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         54200,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product21',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         41300,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product22',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         83800,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product23',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         52700,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product24',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         25200,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product25',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         87300,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product26',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         63700,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product27',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         32000,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product28',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         30200,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product29',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         20200,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product30',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         33900,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product31',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         28200,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product32',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         6700,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product33',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         68700,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product34',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         23500,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product35',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         46800,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product36',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         12400,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product37',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         67400,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product38',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         2700,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product39',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         25000,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product40',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         53700,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product41',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         55200,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product42',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         81000,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product43',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         81300,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product44',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         3600,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product45',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         57900,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product46',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         12100,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product47',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         59300,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product48',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         78700,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product49',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         45000,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product50',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         25200,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product51',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         35500,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product52',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         18500,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product53',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         10800,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product54',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         29100,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product55',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         50100,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product56',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         61300,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product57',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         72800,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product58',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         25100,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product59',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         31500,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product60',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         58500,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product61',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         11500,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product62',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         59100,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product63',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         63700,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product64',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         10400,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product65',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         48700,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product66',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         49000,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product67',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         83900,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product68',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         54800,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product69',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         59000,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product70',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         6800,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product71',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         61500,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product72',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         72800,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product73',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         60400,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product74',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         43900,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product75',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         54600,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product76',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         57300,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product77',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         50700,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product78',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         72300,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product79',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         7700,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product80',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         89100,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product81',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         42500,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product82',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         48100,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product83',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         89100,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product84',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         63900,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product85',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         55000,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product86',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         87100,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product87',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         54500,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product88',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         2500,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product89',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         72600,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product90',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         13500,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product91',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         32600,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product92',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         3800,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product93',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         64500,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product94',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         55200,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product95',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         1100,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product96',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         24100,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product97',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         35400,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product98',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         36000,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- INSERT INTO
--     product
-- VALUES
--     (
--         seq_product_prod_no.nextval,
--         'Product99',
--         '��Ʈ��',
--         TO_DATE('2012/02/12', 'YYYY-MM-DD'),
--         52000,
--         'AHlbAAAAug1vsgAA.jpg',
--         to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS')
--     );

-- COMMIT;

COMMIT;
