-- Получить номера поставщиков, которые обеспечивают проект ПР1.

SELECT DISTINCT supplier_id FROM counts
WHERE project_id = 2;