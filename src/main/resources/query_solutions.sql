-- V1 N HITS ON DB USING UNION
(select name, lawyers.customers_number
from lawyers
order by customers_number desc
limit 1)

union all

(select name, lawyers.customers_number
from lawyers
order by customers_number asc
limit 1)

union all

(select 'Average', round(avg(customers_number))
from lawyers);


-- v2 using db functions
(select name, customers_number
from lawyers
where customers_number = (select max(customers_number)
                          from lawyers))

UNION ALL

(select name, customers_number
from lawyers
where customers_number = (select min(customers_number)
                          from lawyers))
union all

(select 'Average', round(avg(customers_number))
from lawyers);
