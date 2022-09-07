-- MS SQL Server --

-- https://leetcode.com/problems/combine-two-tables/

SELECT Person.firstName, Person.lastName, ISNULL(Address.city, Null) AS city, ISNULL(Address.state, Null) AS state
FROM Person LEFT JOIN Address ON Address.personId = Person.personId;