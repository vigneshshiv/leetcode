-- MS SQL Server --

-- https://leetcode.com/problems/market-analysis-i/

WITH UsersOrder AS (
    SELECT Users.user_id AS buyer_id, COUNT(1) AS orders_in_2019
    FROM Users
        LEFT JOIN Orders ON Orders.buyer_id = Users.user_id
    WHERE YEAR(Orders.order_date) = '2019'
    GROUP BY Users.user_id
)
SELECT Users.user_id AS buyer_id, Users.join_date, ISNULL(UsersOrder.orders_in_2019, 0) AS orders_in_2019
FROM Users LEFT JOIN UsersOrder ON UsersOrder.buyer_id = Users.user_id
