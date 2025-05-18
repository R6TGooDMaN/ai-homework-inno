SELECT SUM(amount) AS total_march_sales
FROM orders
WHERE strftime('%Y-%m', order_date) = '2024-03';