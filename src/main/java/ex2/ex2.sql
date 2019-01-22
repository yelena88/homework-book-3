/*
  Задание  2
  Напишите запрос, делающий выборку из обеих таблиц полей, где величина расходов    больше 10000.
  В выборке должны присутствовать четыре поля: номер платежа, дата, имя получателя, величина.
*/


SELECT expenses.num AS 'номер платежа',
paydate AS 'дата',
name AS 'имя получателя',
value AS 'величина'
FROM expenses,receivers
WHERE receiver = receivers.num AND value > 10000;