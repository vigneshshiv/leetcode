-- MS SQL Server --

-- https://leetcode.com/problems/customers-who-never-order/

SELECT name AS Customers
FROM Customers
WHERE 1 = 1 AND NOT EXISTS (
    SELECT 1 FROM Orders WHERE Orders.customerId = Customers.id
);