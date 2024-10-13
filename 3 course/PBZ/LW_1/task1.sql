-- Получить номера проектов, для которых не поставляются красные детали поставщиками из Минска.

SELECT p.id
FROM projects AS p
WHERE p.id NOT IN (
    SELECT DISTINCT c.project_id
    FROM counts AS c
    JOIN suppliers AS s ON c.supplier_id = s.id
    JOIN products AS pr ON c.product_id = pr.id
    WHERE s.city = 'Минск' AND pr.color = 'Красный'
);
