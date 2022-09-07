-- MS SQL Server --

-- https://leetcode.com/problems/nth-highest-salary/

CREATE FUNCTION getNthHighestSalary(@N INT) RETURNS INT AS
BEGIN
    RETURN (
        SELECT ISNULL((
            SELECT DISTINCT salary FROM Employee ORDER BY salary DESC OFFSET @N-1 ROWS FETCH NEXT 1 ROWS ONLY
        ), null)
    );
END