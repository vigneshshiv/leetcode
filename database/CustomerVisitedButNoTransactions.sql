-- MS SQL Server --

-- https://leetcode.com/problems/customer-who-visited-but-did-not-make-any-transactions/

SELECT customer_id, COUNT(Visits.visit_id) AS count_no_trans
FROM Visits LEFT JOIN Transactions ON Transactions.visit_id = Visits.visit_id
WHERE Transactions.transaction_id IS NULL
GROUP BY customer_id