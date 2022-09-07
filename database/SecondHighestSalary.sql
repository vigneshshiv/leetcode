-- MS SQL Server --

-- https://leetcode.com/problems/second-highest-salary/

SELECT ISNULL((
    SELECT DISTINCT salary FROM Employee ORDER BY salary DESC OFFSET 1 ROWS FETCH NEXT 1 ROWS ONLY
), null) AS SecondHighestSalary;