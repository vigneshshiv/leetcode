-- MS SQL Server --

-- https://leetcode.com/problems/fix-names-in-a-table/

SELECT user_id, CONCAT(UPPER(SUBSTRING(name, 1, 1)), LOWER(SUBSTRING(name, 2, LEN(name)))) AS name
FROM Users
ORDER BY user_id