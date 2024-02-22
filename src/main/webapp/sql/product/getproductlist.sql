SELECT 
    v.prod_no "prod_no", 
    v.prod_name "prod_name", 
    v.prod_detail "prod_detail", 
    v.manufacture_day "manufacture_day", 
    v.price "price", 
    v.image_file "image_file", 
    v.reg_date "reg_date", 
    v.TRAN_STATUS_CODE "tran_status_code", 
    v.COUNT "count" 
FROM ( 
    SELECT 
        ROW_NUMBER() OVER (ORDER BY p.prod_no ASC) ROW_NUM, 
        p.prod_no PROD_NO, 
        p.prod_name PROD_NAME, 
        p.prod_detail PROD_DETAIL, 
        p.manufacture_day MANUFACTURE_DAY, 
        p.price PRICE, 
        p.image_file IMAGE_FILE, 
        p.reg_date REG_DATE, 
        NVL(t.tran_status_code, ?) TRAN_STATUS_CODE, 
        COUNT(*) OVER () COUNT 
    FROM product p
    LEFT OUTER JOIN transaction t 
        ON p.prod_no = t.prod_no %s 
    ORDER BY p.reg_date DESC 
) v 
WHERE v.ROW_NUM BETWEEN ? AND ?