-- MS SQL Server --

-- https://leetcode.com/problems/customer-placing-the-largest-number-of-orders/

SELECT TOP(1) A.customer_number FROM (
    SELECT customer_number, COUNT(1) total_orders
    FROM Orders
    GROUP BY customer_number
) AS A
ORDER BY A.total_orders DESC