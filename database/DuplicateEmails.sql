-- MS SQL Server --

-- https://leetcode.com/problems/duplicate-emails/

SELECT Temp.email FROM (
    SELECT COUNT(1) As emailCount, email FROM Person
    GROUP BY email
    HAVING COUNT(1) > 1
) AS Temp;