-- MS SQL Server --

-- https://leetcode.com/problems/rising-temperature/

SELECT W1.id
FROM Weather AS W1
    JOIN Weather AS W2 ON W1.recordDate = DATEADD(DAY, 1, W2.RecordDate)
WHERE W1.temperature > W2.temperature