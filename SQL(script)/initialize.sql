
DROP TABLE transaction;
DROP TABLE product;
DROP TABLE users;

DROP SEQUENCE seq_product_prod_no;
DROP SEQUENCE seq_transaction_tran_no;


CREATE SEQUENCE seq_product_prod_no   INCREMENT BY 1 START WITH 10000;
CREATE SEQUENCE seq_transaction_tran_no INCREMENT BY 1 START WITH 10000;


CREATE TABLE users ( 
 user_id   VARCHAR2(20) NOT NULL,
 user_name  VARCHAR2(50) NOT NULL,
 password  VARCHAR2(10) NOT NULL,
 role   VARCHAR2(5)  DEFAULT 'user',
 ssn    VARCHAR2(13),
 cell_phone VARCHAR2(14),
 addr   VARCHAR2(100),
 email   VARCHAR2(50),
 reg_date  DATE,
 PRIMARY KEY(user_id)
);


CREATE TABLE product ( 
 prod_no    NUMBER   NOT NULL,
 prod_name   VARCHAR2(100)  NOT NULL,
 prod_detail   VARCHAR2(200),
 manufacture_day VARCHAR2(8),
 price     NUMBER(10),
 image_file    VARCHAR2(100),
 reg_date    DATE,
 PRIMARY KEY(prod_no)
);

CREATE TABLE transaction ( 
 tran_no    NUMBER   NOT NULL,
 prod_no    NUMBER(16) NOT NULL REFERENCES product(prod_no),
 buyer_id    VARCHAR2(20) NOT NULL REFERENCES users(user_id),
 payment_option CHAR(3),
 receiver_name  VARCHAR2(20),
 receiver_phone VARCHAR2(14),
 demailaddr   VARCHAR2(100),
 dlvy_request   VARCHAR2(100),
 tran_status_code CHAR(3),
 order_date   DATE,
 dlvy_date   DATE,
 PRIMARY KEY(tran_no),
 UNIQUE(prod_no)
);


INSERT 
INTO users ( user_id, user_name, password, role, ssn, cell_phone, addr, email, reg_date ) 
VALUES ( 'admin', 'admin', '1234', 'admin', NULL, NULL, 'º≠øÔΩ√ º≠√ ±∏', 'admin@mvc.com',to_date('2012/01/14 10:48:43', 'YYYY/MM/DD HH24:MI:SS')); 

INSERT 
INTO users ( user_id, user_name, password, role, ssn, cell_phone, addr, email, reg_date ) 
VALUES ( 'manager', 'manager', '1234', 'admin', NULL, NULL, NULL, 'manager@mvc.com', to_date('2012/01/14 10:48:43', 'YYYY/MM/DD HH24:MI:SS'));          

INSERT INTO users 
VALUES ( 'user01', 'SCOTT', '1111', 'user', NULL, NULL, NULL, NULL, sysdate); 

INSERT INTO users 
VALUES ( 'user02', 'SCOTT', '2222', 'user', NULL, NULL, NULL, NULL, sysdate); 

INSERT INTO users 
VALUES ( 'user03', 'SCOTT', '3333', 'user', NULL, NULL, NULL, NULL, sysdate); 

INSERT INTO users 
VALUES ( 'user04', 'SCOTT', '4444', 'user', NULL, NULL, NULL, NULL, sysdate); 

INSERT INTO users 
VALUES ( 'user05', 'SCOTT', '5555', 'user', NULL, NULL, NULL, NULL, sysdate); 

INSERT INTO users 
VALUES ( 'user06', 'SCOTT', '6666', 'user', NULL, NULL, NULL, NULL, sysdate); 

INSERT INTO users 
VALUES ( 'user07', 'SCOTT', '7777', 'user', NULL, NULL, NULL, NULL, sysdate); 

INSERT INTO users 
VALUES ( 'user08', 'SCOTT', '8888', 'user', NULL, NULL, NULL, NULL, sysdate); 

INSERT INTO users 
VALUES ( 'user09', 'SCOTT', '9999', 'user', NULL, NULL, NULL, NULL, sysdate); 

INSERT INTO users 
VALUES ( 'user10', 'SCOTT', '1010', 'user', NULL, NULL, NULL, NULL, sysdate); 

INSERT INTO users 
VALUES ( 'user11', 'SCOTT', '1111', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users 
VALUES ( 'user12', 'SCOTT', '1212', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users 
VALUES ( 'user13', 'SCOTT', '1313', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users 
VALUES ( 'user14', 'SCOTT', '1414', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users 
VALUES ( 'user15', 'SCOTT', '1515', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users 
VALUES ( 'user16', 'SCOTT', '1616', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users 
VALUES ( 'user17', 'SCOTT', '1717', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users 
VALUES ( 'user18', 'SCOTT', '1818', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users 
VALUES ( 'user19', 'SCOTT', '1919', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users 
VALUES ( 'user20', 'SCOTT', '2020', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users 
VALUES ( 'user21', 'SCOTT', '2121', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users 
VALUES ( 'user22', 'SCOTT', '2222', 'user', NULL, NULL, NULL, NULL, sysdate);

INSERT INTO users 
VALUES ( 'user23', 'SCOTT', '2323', 'user', NULL, NULL, NULL, NULL, sysdate);
           
           
insert into product values (seq_product_prod_no.nextval,'vaio vgn FS70B','º“¥œ πŸ¿Ãø¿ ≥Î∆Æ∫œ Ω≈µø«∞','20120514',2000000, 'AHlbAAAAtBqyWAAA.jpg',to_date('2012/12/14 11:27:27', 'YYYY/MM/DD HH24:MI:SS'));
insert into product values (seq_product_prod_no.nextval,'¿⁄¿¸∞≈','¿⁄¿¸∞≈ ¡¡æ∆ø‰~','20120514',10000, 'AHlbAAAAvetFNwAA.jpg',to_date('2012/11/14 10:48:43', 'YYYY/MM/DD HH24:MI:SS'));
insert into product values (seq_product_prod_no.nextval,'∫∏∏£µµ','√÷∞Ì µ¿⁄¿Œ Ω≈«∞','20120201',1170000, 'AHlbAAAAvewfegAB.jpg',to_date('2012/10/14 10:49:39', 'YYYY/MM/DD HH24:MI:SS'));
insert into product values (seq_product_prod_no.nextval,'∫∏µÂºº∆Æ','«—Ω√¡ π€ø° æ»ΩËΩ¿¥œ¥Ÿ. ¥´π∞¿ª ∏”±›∞Ì ≥ª≥ı¿Ω §–.§–','20120217', 200000, 'AHlbAAAAve1WwgAC.jpg',to_date('2012/11/14 10:50:58', 'YYYY/MM/DD HH24:MI:SS'));
insert into product values (seq_product_prod_no.nextval,'¿Œ∂Û¿Œ','¡¡æ∆øÈ','20120819', 20000, 'AHlbAAAAve37LwAD.jpg',to_date('2012/11/14 10:51:40', 'YYYY/MM/DD HH24:MI:SS'));
insert into product values (seq_product_prod_no.nextval,'ªÔº∫ºæΩ∫ 2G','sens ∏ﬁ∏∏Æ 2Giga','20121121',800000, 'AHlbAAAAtBqyWAAA.jpg',to_date('2012/11/14 18:46:58', 'YYYY/MM/DD HH24:MI:SS'));
insert into product values (seq_product_prod_no.nextval,'ø¨≤…','¡§ø¯¿ª ∞°≤„∫∏ººø‰','20121022',232300, 'AHlbAAAAtDPSiQAA.jpg',to_date('2012/11/15 17:39:01', 'YYYY/MM/DD HH24:MI:SS'));
insert into product values (seq_product_prod_no.nextval,'ªÔº∫ºæΩ∫','≥Î∆Æ∫œ','20120212',600000, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO transaction
VALUES (seq_transaction_tran_no.NEXTVAL, 10001, 'user02', 1, 'Nothing', '010-1234-1234', 'asdfasohfiw', 'request sample', 1, TO_DATE('2017/2/8', 'YYYY/MM/DD'), TO_DATE('2017/2/15', 'YYYY/MM/DD'));

INSERT INTO transaction
VALUES (seq_transaction_tran_no.NEXTVAL, 10002, 'user01', 1, 'Nothing', '010-1234-1234', 'asdfasohfiw', 'request sample', 2, TO_DATE('2017/2/8', 'YYYY/MM/DD'), TO_DATE('2017/2/15', 'YYYY/MM/DD'));

INSERT INTO transaction
VALUES (seq_transaction_tran_no.NEXTVAL, 10003, 'user23', 1, 'Nothing', '010-1234-1234', 'asdfasohfiw', 'request sample', 2, TO_DATE('2017/2/8', 'YYYY/MM/DD'), TO_DATE('2017/2/15', 'YYYY/MM/DD'));

INSERT INTO transaction
VALUES (seq_transaction_tran_no.NEXTVAL, 10004, 'user08', 1, 'Nothing', '010-1234-1234', 'asdfasohfiw', 'request sample', 2, TO_DATE('2017/2/8', 'YYYY/MM/DD'), TO_DATE('2017/2/15', 'YYYY/MM/DD'));

INSERT INTO transaction
VALUES (seq_transaction_tran_no.NEXTVAL, 10005, 'user08', 1, 'Nothing', '010-1234-1234', 'asdfasohfiw', 'request sample', 2, TO_DATE('2017/2/8', 'YYYY/MM/DD'), TO_DATE('2017/2/15', 'YYYY/MM/DD'));

INSERT INTO transaction
VALUES (seq_transaction_tran_no.NEXTVAL, 10006, 'user08', 1, 'Nothing', '010-1234-1234', 'asdfasohfiw', 'request sample', 2, TO_DATE('2017/2/8', 'YYYY/MM/DD'), TO_DATE('2017/2/15', 'YYYY/MM/DD'));

INSERT INTO transaction
VALUES (seq_transaction_tran_no.NEXTVAL, 10007, 'user08', 1, 'Nothing', '010-1234-1234', 'asdfasohfiw', 'request sample', 2, TO_DATE('2017/2/8', 'YYYY/MM/DD'), TO_DATE('2017/2/15', 'YYYY/MM/DD'));

-- Additional sample
INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product0','≥Î∆Æ∫œ','20120212',47400, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product1','≥Î∆Æ∫œ','20120212',30400, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product2','≥Î∆Æ∫œ','20120212',82300, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product3','≥Î∆Æ∫œ','20120212',36900, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product4','≥Î∆Æ∫œ','20120212',39900, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product5','≥Î∆Æ∫œ','20120212',32100, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product6','≥Î∆Æ∫œ','20120212',29700, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product7','≥Î∆Æ∫œ','20120212',2100, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product8','≥Î∆Æ∫œ','20120212',18700, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product9','≥Î∆Æ∫œ','20120212',600, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product10','≥Î∆Æ∫œ','20120212',49800, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product11','≥Î∆Æ∫œ','20120212',49600, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product12','≥Î∆Æ∫œ','20120212',84400, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product13','≥Î∆Æ∫œ','20120212',67200, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product14','≥Î∆Æ∫œ','20120212',29600, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product15','≥Î∆Æ∫œ','20120212',27100, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product16','≥Î∆Æ∫œ','20120212',80900, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product17','≥Î∆Æ∫œ','20120212',4600, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product18','≥Î∆Æ∫œ','20120212',33400, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product19','≥Î∆Æ∫œ','20120212',11300, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product20','≥Î∆Æ∫œ','20120212',54200, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product21','≥Î∆Æ∫œ','20120212',41300, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product22','≥Î∆Æ∫œ','20120212',83800, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product23','≥Î∆Æ∫œ','20120212',52700, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product24','≥Î∆Æ∫œ','20120212',25200, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product25','≥Î∆Æ∫œ','20120212',87300, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product26','≥Î∆Æ∫œ','20120212',63700, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product27','≥Î∆Æ∫œ','20120212',32000, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product28','≥Î∆Æ∫œ','20120212',30200, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product29','≥Î∆Æ∫œ','20120212',20200, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product30','≥Î∆Æ∫œ','20120212',33900, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product31','≥Î∆Æ∫œ','20120212',28200, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product32','≥Î∆Æ∫œ','20120212',6700, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product33','≥Î∆Æ∫œ','20120212',68700, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product34','≥Î∆Æ∫œ','20120212',23500, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product35','≥Î∆Æ∫œ','20120212',46800, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product36','≥Î∆Æ∫œ','20120212',12400, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product37','≥Î∆Æ∫œ','20120212',67400, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product38','≥Î∆Æ∫œ','20120212',2700, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product39','≥Î∆Æ∫œ','20120212',25000, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product40','≥Î∆Æ∫œ','20120212',53700, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product41','≥Î∆Æ∫œ','20120212',55200, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product42','≥Î∆Æ∫œ','20120212',81000, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product43','≥Î∆Æ∫œ','20120212',81300, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product44','≥Î∆Æ∫œ','20120212',3600, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product45','≥Î∆Æ∫œ','20120212',57900, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product46','≥Î∆Æ∫œ','20120212',12100, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product47','≥Î∆Æ∫œ','20120212',59300, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product48','≥Î∆Æ∫œ','20120212',78700, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product49','≥Î∆Æ∫œ','20120212',45000, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product50','≥Î∆Æ∫œ','20120212',25200, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product51','≥Î∆Æ∫œ','20120212',35500, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product52','≥Î∆Æ∫œ','20120212',18500, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product53','≥Î∆Æ∫œ','20120212',10800, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product54','≥Î∆Æ∫œ','20120212',29100, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product55','≥Î∆Æ∫œ','20120212',50100, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product56','≥Î∆Æ∫œ','20120212',61300, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product57','≥Î∆Æ∫œ','20120212',72800, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product58','≥Î∆Æ∫œ','20120212',25100, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product59','≥Î∆Æ∫œ','20120212',31500, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product60','≥Î∆Æ∫œ','20120212',58500, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product61','≥Î∆Æ∫œ','20120212',11500, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product62','≥Î∆Æ∫œ','20120212',59100, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product63','≥Î∆Æ∫œ','20120212',63700, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product64','≥Î∆Æ∫œ','20120212',10400, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product65','≥Î∆Æ∫œ','20120212',48700, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product66','≥Î∆Æ∫œ','20120212',49000, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product67','≥Î∆Æ∫œ','20120212',83900, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product68','≥Î∆Æ∫œ','20120212',54800, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product69','≥Î∆Æ∫œ','20120212',59000, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product70','≥Î∆Æ∫œ','20120212',6800, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product71','≥Î∆Æ∫œ','20120212',61500, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product72','≥Î∆Æ∫œ','20120212',72800, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product73','≥Î∆Æ∫œ','20120212',60400, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product74','≥Î∆Æ∫œ','20120212',43900, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product75','≥Î∆Æ∫œ','20120212',54600, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product76','≥Î∆Æ∫œ','20120212',57300, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product77','≥Î∆Æ∫œ','20120212',50700, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product78','≥Î∆Æ∫œ','20120212',72300, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product79','≥Î∆Æ∫œ','20120212',7700, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product80','≥Î∆Æ∫œ','20120212',89100, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product81','≥Î∆Æ∫œ','20120212',42500, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product82','≥Î∆Æ∫œ','20120212',48100, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product83','≥Î∆Æ∫œ','20120212',89100, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product84','≥Î∆Æ∫œ','20120212',63900, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product85','≥Î∆Æ∫œ','20120212',55000, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product86','≥Î∆Æ∫œ','20120212',87100, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product87','≥Î∆Æ∫œ','20120212',54500, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product88','≥Î∆Æ∫œ','20120212',2500, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product89','≥Î∆Æ∫œ','20120212',72600, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product90','≥Î∆Æ∫œ','20120212',13500, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product91','≥Î∆Æ∫œ','20120212',32600, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product92','≥Î∆Æ∫œ','20120212',3800, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product93','≥Î∆Æ∫œ','20120212',64500, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product94','≥Î∆Æ∫œ','20120212',55200, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product95','≥Î∆Æ∫œ','20120212',1100, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product96','≥Î∆Æ∫œ','20120212',24100, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product97','≥Î∆Æ∫œ','20120212',35400, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product98','≥Î∆Æ∫œ','20120212',36000, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));

INSERT INTO product VALUES (seq_product_prod_no.nextval,'Product99','≥Î∆Æ∫œ','20120212',52000, 'AHlbAAAAug1vsgAA.jpg',to_date('2012/11/12 13:04:31', 'YYYY/MM/DD HH24:MI:SS'));



commit;

