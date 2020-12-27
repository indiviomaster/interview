/*
ошибки в расписании (фильмы накладываются друг на друга),
отсортированные по возрастанию времени. Выводить надо колонки
«фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;
*/
SELECT s1.`data`, f1.title, s1.`time`, f1.duration, f2.title,  s2.`time`, f2.duration
FROM seance AS s1  JOIN film AS f1 ON s1.film_id = f1.id
JOIN seance AS s2  JOIN film AS f2 ON s2.film_id = f2.id
WHERE s2.`time` between s1.`time`  and  sec_to_time(time_to_sec(s1.`time`)+f1.duration*60)
and s1.film_id <> s2.film_id and s1.`data` = s2.`data`
ORDER BY s1.data, s1.`time`, f1.duration;
/*
перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва.
Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;
*/

SELECT s1.`data`, f1.title, s1.`time`, f1.duration, f2.title, s2.`time`, (time_to_sec(s2.`time` - sec_to_time(time_to_sec(s1.`time`)+f1.duration*60))/60) as tt
FROM seance AS s1  JOIN film AS f1 ON s1.film_id = f1.id
JOIN seance AS s2  JOIN film AS f2 ON s2.film_id = f2.id
WHERE s1.`time` < s2.`time`
and sec_to_time(time_to_sec(s1.`time`)+f1.duration*60) < s2.`time`
and (s2.`time` - sec_to_time(time_to_sec(s1.`time`)+f1.duration*60)) > sec_to_time(30*60)
and (s2.`time` - sec_to_time(time_to_sec(s1.`time`)+f1.duration*60)) < sec_to_time(60*60)
and s1.film_id <> s2.film_id and s1.`data` = s2.`data`
ORDER BY s1.data, s1.`time`, tt;

/*
список фильмов, для каждого — с указанием
общего числа посетителей за все время, среднего числа зрителей за сеанс и общей суммы сборов по каждому фильму
(отсортировать по убыванию прибыли).
*/

SELECT film.title, count(*) as `vizitors`, sum(cost) as `total` FROM cinema.ticket
JOIN cinema.seance ON ticket.seance_id = seance.id
JOIN  cinema.film ON seance.film_id = film.id
GROUP BY film.title order by `total` desc;

/*среднее число зрителей на сеансах*/
SELECT avg(S1.tick) FROM (SELECT count(seance_id) as tick, title FROM cinema.ticket
JOIN cinema.seance ON ticket.seance_id = seance.id
JOIN  cinema.film ON seance.film_id = film.id
GROUP BY ticket.seance_id) as S1;
/*
Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;
*/
SELECT sum(cost) FROM cinema.film
JOIN cinema.seance ON seance.film_id = film.id
JOIN cinema.ticket ON ticket.seance_id = seance.id ORDER BY title;

/*
число посетителей и кассовые сборы, сгруппированные по времени начала фильма:
с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).
*/

/*При указании интервала between '21-00-00' and '00-00-00' выборка не происходит, выход конечное время 23:59:59*/

SELECT film.title, count(*) as `vizitors`, sum(cost) as `total` FROM cinema.ticket
JOIN cinema.seance ON ticket.seance_id = seance.id
JOIN  cinema.film ON seance.film_id = film.id
WHERE seance.`time` between '09-00-00' and '15-00-00'
GROUP BY film.title ORDER BY `total` DESC;
SELECT film.title, count(*) as `vizitors`, sum(cost) as `total` FROM cinema.ticket
JOIN cinema.seance ON ticket.seance_id = seance.id
JOIN  cinema.film ON seance.film_id = film.id
WHERE seance.`time` between '15-00-00' and '18-00-00'
GROUP BY film.title ORDER BY `total` DESC;
SELECT film.title, count(*) as `vizitors`, sum(cost) as `total` FROM cinema.ticket
JOIN cinema.seance ON ticket.seance_id = seance.id
JOIN  cinema.film ON seance.film_id = film.id
WHERE seance.`time` between '21-00-00' and '23-59-59'
GROUP BY film.title ORDER BY `total` DESC;