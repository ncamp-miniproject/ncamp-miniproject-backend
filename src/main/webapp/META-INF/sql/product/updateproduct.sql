UPDATE product
SET
    -- TODO: if the parameter has null or invalid value,
    -- remove statements of setting columns
    prod_name = ?,
    prod_detail = ?,
    manufacture_day = ?,
    price = ?,
    image_file = ?,
    reg_date = ?
WHERE
    prod_no = ?