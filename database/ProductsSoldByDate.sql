-- MS SQL Server --

-- https://leetcode.com/problems/group-sold-products-by-the-date/

SELECT SoldByDate.sell_date,
    COUNT(SoldByDate.product) AS num_sold,
    STRING_AGG(SoldByDate.product, ',') WITHIN GROUP (ORDER BY SoldByDate.product) AS products
FROM (
    SELECT DISTINCT sell_date, product FROM Activities
) AS SoldByDate
GROUP BY SoldByDate.sell_date
ORDER BY SoldByDate.sell_date