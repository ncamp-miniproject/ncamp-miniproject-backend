SELECT prod_no,
       prod_name,
       prod_detail,
       manufacture_day,
       price,
       image_file,
       reg_date,
       stock
FROM product
WHERE prod_no IN (%s)