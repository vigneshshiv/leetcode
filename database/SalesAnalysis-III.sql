-- MS SQL Server --

-- https://leetcode.com/problems/sales-analysis-iii/

SELECT p.product_id, p.product_name
FROM Product p
WHERE EXISTS (
    SELECT s.product_id from Sales s
    WHERE p.product_id = s.product_id AND s.sale_date BETWEEN '2019-01-01' AND '2019-03-31')
AND NOT EXISTS (
    SELECT s.product_id from Sales s
    WHERE p.product_id = s.product_id AND s.sale_date NOT BETWEEN '2019-01-01' AND '2019-03-31')


-- TLE Below --

SELECT Product.product_id, Product.product_name
FROM Product, Sales
WHERE Sales.product_id = Product.product_id
GROUP BY Product.product_id, Product.product_name
HAVING MIN(Sales.sale_date) >= '2019-01-01' AND MAX(Sales.sale_date) <= '2019-03-31'


WITH Temp AS (
    SELECT Sales.product_id, Product.product_name,
        SUM(CASE WHEN Sales.sale_date >= '2019-01-01' AND Sales.sale_date <= '2019-03-31' THEN 0 ELSE 1 END) AS rank_order
    FROM Sales LEFT JOIN Product ON Product.product_id = Sales.product_id
    GROUP BY Sales.product_id, Product.product_name
)
SELECT Temp.product_id, Temp.product_name
FROM Temp
WHERE Temp.rank_order = 0


SELECT Product.product_id, Product.product_name
FROM Product
WHERE Product.product_id NOT IN (
    SELECT Sales.product_id FROM Sales WHERE Sales.sale_date NOT BETWEEN '2019-01-01' AND '2019-03-31'
)
AND Product.product_id IN (SELECT Sales.product_id FROM Sales)
