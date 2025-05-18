SELECT AVG(amount) AS average_order_value
FROM orders
WHERE order_date BETWEEN '2024-01-01' AND '2024-03-31';
