-- MS SQL Server --

-- https://leetcode.com/problems/employees-earning-more-than-their-managers/

SELECT emp.name AS Employee FROM Employee emp WHERE 1 = 1 AND EXISTS (
    SELECT 1 FROM Employee WHERE id = emp.managerId AND emp.salary > Employee.salary
);