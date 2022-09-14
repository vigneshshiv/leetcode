-- MS SQL Server --

-- https://leetcode.com/problems/the-latest-login-in-2020/

SELECT user_id, MAX(time_stamp) AS last_stamp
FROM Logins
WHERE YEAR(time_stamp) = '2020'
GROUP BY user_id

-- OR

WITH LoginDetails AS (
    SELECT user_id, time_stamp,
    ROW_NUMBER() OVER(PARTITION BY user_id ORDER BY time_stamp DESC) AS rank_order
    FROM Logins
    WHERE YEAR(time_stamp) = '2020'
)
SELECT LoginDetails.user_id, LoginDetails.time_stamp AS last_stamp
FROM LoginDetails
WHERE rank_order = 1;