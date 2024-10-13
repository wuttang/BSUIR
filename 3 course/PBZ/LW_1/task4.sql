-- Получить номера деталей, поставляемых поставщиком в Минске для проекта в Минске.

SELECT DISTINCT c.product_id
FROM counts c
JOIN suppliers s ON c.supplier_id = s.id
JOIN projects p ON c.project_id = p.id
WHERE s.city = 'Минск' AND p.city = 'Минск';