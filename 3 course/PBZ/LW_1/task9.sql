-- Получить все такие пары номеров деталей, которые обе поставляются одновременно одним поставщиком.

SELECT DISTINCT
    c1.product_id AS product_id1,
    c2.product_id AS product_id2
FROM counts c1
JOIN counts c2 ON c1.supplier_id = c2.supplier_id AND c1.product_id < c2.product_id