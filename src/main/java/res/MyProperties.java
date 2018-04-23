package res;

public class MyProperties {
    final String url = "jdbc:postgresql://localhost:5432/postgres";
    final String username = "postgres";
    final String password = "1234";
    final String driverClassName = "org.postgresql.Driver";

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }
}
