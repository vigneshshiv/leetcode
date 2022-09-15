-- MS SQL Server --

-- https://leetcode.com/problems/top-travellers/

SELECT A.name, A.travelled_distance FROM (
    SELECT Users.id, Users.name, ISNULL(SUM(Rides.distance), 0) AS travelled_distance
    FROM Users
        LEFT JOIN Rides ON Rides.user_id = Users.id
    GROUP BY Users.id, Users.name
) A
ORDER BY A.travelled_distance DESC, A.name ASC

-- OR

SELECT DISTINCT Users.name,
    ISNULL(SUM(Rides.distance) OVER (PARTITION BY Users.id), 0) as travelled_distance
FROM Users
    LEFT JOIN Rides ON Rides.user_id = Users.id
ORDER BY travelled_distance DESC, Users.name