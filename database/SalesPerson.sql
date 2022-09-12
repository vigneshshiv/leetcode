-- MS SQL Server --

-- https://leetcode.com/problems/sales-person/

SELECT SalesPerson.name
FROM Orders
    JOIN Company ON Company.com_id = Orders.com_id AND Company.name = 'RED'
    RIGHT JOIN SalesPerson ON SalesPerson.sales_id = Orders.sales_id
WHERE Orders.sales_id IS NULL