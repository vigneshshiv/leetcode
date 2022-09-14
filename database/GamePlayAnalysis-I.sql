-- MS SQL Server --

-- https://leetcode.com/problems/game-play-analysis-i/

SELECT player_id, MIN(event_date) AS first_login
FROM Activity
GROUP BY player_id

-- OR

WITH LoginDetails AS (
    SELECT player_id, event_date,
        ROW_NUMBER() OVER(PARTITION BY player_id ORDER BY event_date ASC) AS rank_order
    FROM Activity
)
SELECT LoginDetails.player_id, LoginDetails.event_date AS first_login
FROM LoginDetails
WHERE LoginDetails.rank_order = 1