-- MS SQL Server --

-- https://leetcode.com/problems/tree-node/

SELECT t.id,
    CASE WHEN t.p_id IS NULL
        THEN 'Root'
        ELSE CASE WHEN (
            SELECT TOP(1) id FROM Tree WHERE p_id = t.id
        ) IS NOT NULL THEN 'Inner' ELSE 'Leaf' END
    END AS type
FROM Tree t
ORDER BY t.id

-- OR (FASTER)

SELECT id,
    CASE WHEN p_id IS NULL THEN 'Root'
    ELSE CASE WHEN id IN (SELECT p_id FROM Tree) THEN 'Inner' ELSE 'Leaf' END
    END AS type
FROM Tree
ORDER BY id