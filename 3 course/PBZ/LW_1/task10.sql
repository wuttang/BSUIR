-- Получить номера проектов, полностью обеспечиваемых поставщиком П1.

WITH SupplierProjects AS (
    SELECT
        c.project_id,
        COUNT(DISTINCT c.product_id) AS total_products
    FROM counts AS c
    WHERE c.supplier_id = 1
    GROUP BY c.project_id
),
AllProducts AS (
    SELECT
        c.project_id,
        COUNT(DISTINCT c.product_id) AS total_products
    FROM counts AS c
    GROUP BY c.project_id
)
SELECT sp.project_id
FROM SupplierProjects AS sp
JOIN AllProducts ap ON sp.project_id = ap.project_id
WHERE sp.total_products = ap.total_products;