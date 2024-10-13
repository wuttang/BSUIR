-- Получить общее число проектов, обеспечиваемых поставщиком П1.

SELECT COUNT(DISTINCT counts.project_id) AS total FROM counts
WHERE supplier_id = '5';