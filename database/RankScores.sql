-- MS SQL Server --

-- https://leetcode.com/problems/rank-scores/

SELECT score, DENSE_RANK() OVER(ORDER BY score DESC) AS rank
FROM Scores;