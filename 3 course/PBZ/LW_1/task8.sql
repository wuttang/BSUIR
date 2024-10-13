-- Получить номера деталей, поставляемых либо московским поставщиком, либо для московского
-- проекта.

SELECT DISTINCT counts.product_id FROM counts
JOIN projects ON counts.project_id = projects.id
JOIN suppliers ON counts.supplier_id = suppliers.id
WHERE projects.city = 'Москва' OR suppliers.city = 'Москва';