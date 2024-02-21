SELECT 
    p.prod_no "prod_no", 
    p.prod_name "prod_name", 
    p.prod_detail "prod_detail", 
    p.manufacture_day "manufacture_day", 
    p.price "price", 
    p.image_file "image_file", 
    p.reg_date "reg_date", 
    NVL(t.tran_status_code, ?) "tran_status_code" 
FROM product p 
LEFT OUTER JOIN transaction t ON t.prod_no = p.prod_no 
WHERE p.prod_no = ?