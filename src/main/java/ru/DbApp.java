package ru;

import java.sql.*;

public class DbApp {
    public static final String url = "jdbc:mysql://localhost:3306/cinema?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    public static final String user = "root";
    public static final String password = "123456";
    private static Connection con;
    public static Statement stmt;
    public static ResultSet rs;
    public static PreparedStatement ps;


    public static void main(String[] args) {


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();


            showTime();
            showCountPeopleByFilm();
            showTotalFilmSum();
            showTotalSum();
            vizitFromTimeToTime(Time.valueOf("09:00:00"), Time.valueOf("15:00:00"));
            vizitFromTimeToTime(Time.valueOf("15:00:00"), Time.valueOf("18:00:00"));
            vizitFromTimeToTime(Time.valueOf("18:00:00"), Time.valueOf("21:00:00"));
            vizitFromTimeToTime(Time.valueOf("21:00:00"), Time.valueOf("24:00:00"));



        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try { con.close(); } catch(SQLException se) {  }
            try { stmt.close(); } catch(SQLException se) { }
            try { rs.close(); } catch(SQLException se) {  }
            try { ps.close(); } catch(SQLException se) {  }
        }

    }

    public static void showTotalFilmSum() throws SQLException {
        String query = "SELECT film.title,count(*) as `tickets count`, sum(cost) as `total sum` " +
                "FROM cinema.ticket JOIN cinema.seance ON ticket.seance_id = seance.id " +
                "JOIN  cinema.film ON seance.film_id = film.id GROUP BY film.title ORDER BY `total sum` DESC";

        rs = stmt.executeQuery(query);
        System.out.println();
        while (rs.next()) {
            String title = rs.getString(1);
            int count = rs.getInt(2);
            int sum = rs.getInt(3);

            System.out.println(String.format("%5d человек принесло %8d руб за фильм %s", count, sum, title));
        }
    }

    public static void showCountPeopleByFilm() throws SQLException {
        String queryCountFilmVizitor ="SELECT film.title,count(*) as `tickets count`" +
                "FROM cinema.ticket JOIN cinema.seance ON ticket.seance_id = seance.id " +
                "JOIN  cinema.film ON seance.film_id = film.id GROUP BY film.title";

        rs = stmt.executeQuery(queryCountFilmVizitor);
        System.out.println();
        while (rs.next()) {
            String title = rs.getString(1);
            int count = rs.getInt(2);
            System.out.println(String.format("%5d посетило %s", count,title));
        }
    }

    public static void showTime() throws SQLException {
        String queryFilms = "SELECT seance.`data`,seance.`time`, film.title, film.duration, seance.cost, count(*)" +
                "FROM cinema.ticket " +
                "JOIN cinema.seance ON seance.id = ticket.seance_id " +
                "JOIN cinema.film ON seance.film_id = film.id " +
                "GROUP BY seance.id ORDER BY seance.`data`, seance.`time`";

        rs = stmt.executeQuery(queryFilms);
        System.out.println();
        while (rs.next()) {
            Date data  = rs.getDate(1);
            Time time = rs.getTime(2);
            String title = rs.getString(3);
            int durationMin = rs.getInt(4);
            int cost = rs.getInt(5);
            int ticket = rs.getInt(6);
            System.out.println("" + data + " " +time+ " "+durationMin + " мин. Стоимость:"+ cost +" Продано "+ticket+ " билетов " + title);
        }
    }

    public static void showTotalSum() throws SQLException {
        String queryTotal ="SELECT sum(cost) FROM cinema.film " +
                "JOIN cinema.seance ON seance.film_id = film.id " +
                "JOIN cinema.ticket ON ticket.seance_id = seance.id " +
                "ORDER BY title";


        rs = stmt.executeQuery(queryTotal);
        System.out.println();
        while (rs.next()) {
            int totalSum = rs.getInt(1);
            System.out.println("Всего за прокат: "+ totalSum);
        }
    }

    public static void vizitFromTimeToTime(Time timeFrom, Time timeTo) throws SQLException {
        ps = con.prepareStatement("SELECT film.title,count(*) as `tickets count`, sum(cost) as `total sum` FROM cinema.ticket " +
                "JOIN cinema.seance ON ticket.seance_id = seance.id " +
                "JOIN  cinema.film ON seance.film_id = film.id " +
                "WHERE seance.`time` between ? and ? " +
                "GROUP BY film.title ORDER BY `total sum` DESC");
        ps.setTime(1, timeFrom);
        ps.setTime(2, timeTo);
        System.out.println("\nПосетители с" + timeFrom + " до " + timeTo);
        try {
            rs = ps.executeQuery();

            while (rs.next()) {
                String title = rs.getString(1);
                int count = rs.getInt(2);
                int sum = rs.getInt(3);

                System.out.println(String.format("%4d  человека принесли :%d рублей за фильм %s.",count,sum,title));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

}
