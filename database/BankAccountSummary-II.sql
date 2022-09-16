-- MS SQL Server --

-- https://leetcode.com/problems/bank-account-summary-ii/

SELECT Users.name, SUM(Transactions.amount) AS balance
FROM Users JOIN Transactions ON Transactions.account = Users.account
GROUP BY Users.name
HAVING SUM(Transactions.amount) >= 10000