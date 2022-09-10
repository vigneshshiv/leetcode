-- MS SQL Server --

-- https://leetcode.com/problems/employees-with-missing-information/

SELECT employee_id FROM Employees
    WHERE NOT EXISTS (SELECT 1 FROM Salaries WHERE Salaries.employee_id = Employees.employee_id)
UNION
SELECT employee_id FROM Salaries
    WHERE NOT EXISTS (SELECT 1 FROM Employees WHERE Employees.employee_id = Salaries.employee_id)
ORDER BY 1