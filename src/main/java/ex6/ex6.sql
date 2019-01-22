/* Задание  6 Составить следующие запросы:

Вывести список получателей платежей, и сумму платежей по каждому из них.*/
SELECT name       AS 'Получатель платежа',
       SUM(value) AS 'Сумма платежей'
FROM receivers rs,
     expenses
WHERE receiver = rs.num
GROUP BY name;

# Вывести сумму платежей за тот день, когда был наибольший платеж
SELECT sum(value) AS 'Сумма платежей в тот день, когда был наибольший платеж'
FROM expenses
WHERE paydate =
      (SELECT paydate
       FROM expenses
       WHERE value =
             (SELECT MAX(value) FROM expenses));


# Вывести наибольший платеж за тот день, когда сумма платежей была наибольшей
SELECT MAX(value) AS 'Наибольший платеж в тот день, когда сумма платежей наибольшая'
FROM expenses
WHERE paydate = (SELECT paydate
                 FROM (SELECT paydate, SUM(value)
                       FROM expenses
                       GROUP BY paydate
                       HAVING SUM(value) = (SELECT MAX(sum_value)
                                            FROM (SELECT SUM(value) AS sum_value
                                                  FROM expenses
                                                  GROUP BY paydate
                                                 ) AS pd_grpd_sums
                       )
                      ) AS max_sum_pd
);


# Вариант 2: Вывести наибольший платеж за тот день, когда сумма платежей была наибольшей
SELECT MAX(value) AS  'Наибольший платеж в тот день, когда сумма платежей наибольшая'
FROM expenses
GROUP BY paydate
HAVING SUM(value) >= (SELECT SUM(value) FROM expenses GROUP BY paydate ORDER BY SUM(value) DESC LIMIT 1);


# Вывести наименьший платеж за тот день, когда сумма платежей была наименьшей
SELECT MIN(value) AS 'Наименьший платеж в тот день, когда сумма платежей наименьший'
FROM expenses
WHERE paydate = (SELECT paydate
                 FROM (SELECT paydate, SUM(value)
                       FROM expenses
                       GROUP BY paydate
                       HAVING SUM(value) = (SELECT MIN(sum_value)
                                            FROM (SELECT SUM(value) AS sum_value
                                                  FROM expenses
                                                  GROUP BY paydate
                                                 ) AS pd_grpd_sums
                       )
                      ) AS max_sum_pd
);


# Вариант 2: Вывести наименьший платеж за тот день, когда сумма платежей была наименьший
SELECT MIN(value) AS  'Наименьший платеж в тот день, когда сумма платежей наименьший'
FROM expenses
GROUP BY paydate
HAVING SUM(value) <= (SELECT SUM(value) FROM expenses GROUP BY paydate ORDER BY SUM(value) ASC LIMIT 1);
