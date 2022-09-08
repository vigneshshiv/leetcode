-- SQL Server --

-- https://leetcode.com/problems/calculate-special-bonus/

-- FASTER OUTPUT --
SELECT employee_id,
    IIF(employee_id % 2 = 0 OR SUBSTRING(name, 1, 1) = 'M', 0, salary) AS bonus
FROM Employees
ORDER BY employee_id

-- BIT SLOW --
SELECT employee_id,
    CASE WHEN (employee_id % 2 = 0 OR SUBSTRING(name, 1, 1) = 'M')
        THEN 0
        ELSE salary
    END AS bonus
FROM Employees
ORDER BY employee_id