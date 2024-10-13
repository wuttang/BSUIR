-- Получить номера поставщиков, поставляющих по крайней мере одну деталь, поставляемую по
-- крайней мере одним поставщиком, который поставляет по крайней мере одну красную деталь.

SELECT DISTINCT s.id
FROM suppliers AS s
WHERE EXISTS (
    SELECT 1
    FROM counts AS c1
    WHERE c1.supplier_id = s.id
    AND c1.product_id IN (
        SELECT DISTINCT c2.product_id
        FROM counts AS c2
        WHERE c2.supplier_id IN (
            SELECT DISTINCT c3.supplier_id
            FROM counts AS c3
            JOIN products AS p3 ON c3.product_id = p3.id
            WHERE p3.color = 'Красный'
        )
    )
);