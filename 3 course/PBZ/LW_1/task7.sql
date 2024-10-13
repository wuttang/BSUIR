-- Получить номера деталей, поставляемых поставщиком в Минске.

SELECT DISTINCT counts.product_id FROM counts
JOIN suppliers ON counts.supplier_id = suppliers.id
WHERE suppliers.city = 'Минск';
