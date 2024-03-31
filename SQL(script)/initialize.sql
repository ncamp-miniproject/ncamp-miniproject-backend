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
    user_name  TEXT NOT NULL,
    password   TEXT NOT NULL,
    role       TEXT DEFAULT 'user',
    ssn        TEXT,
    cell_phone TEXT,
    addr       TEXT,
    email      TEXT,
    reg_date   DATE DEFAULT CURRENT_DATE,
    CONSTRAINT users_pk PRIMARY KEY (user_id)
);

CREATE TABLE seller
(
    seller_id         TEXT,
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