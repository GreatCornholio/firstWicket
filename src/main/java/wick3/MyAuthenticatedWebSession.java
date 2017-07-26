package wick3;

import java.sql.*;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

public class MyAuthenticatedWebSession extends AuthenticatedWebSession
{
    public MyAuthenticatedWebSession(Request request)
    {
        super(request);
    }

    private static String userName = "";

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return userName;
    }

    @Override
    public boolean authenticate(final String username, final String password)
    {
        //  Database credentials
        Connection connection = null;
        Statement statement = null;
        final String USER = "postgres";
        final String PASS = "1111";
        final String URL = "jdbc:postgresql://localhost/first_wicket";

        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            statement = connection.createStatement();

            ResultSet resultset = statement.executeQuery("SELECT id FROM logins WHERE name = '" + username
                                                                                 + "' AND pass = '" + password + "'");
            if (resultset.next()) {
                setUserName(username);
                return true;
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace(); //хз как оно работает в формате веба
        }
        return false;
    }

    @Override
    public void invalidate(){
        super.invalidate();
        setUserName("");
    }

    @Override
    public Roles getRoles()
    {
        if (isSignedIn())
        {
            // If the user is signed in, they have these roles
            return new Roles(Roles.ADMIN);
        }
        return null;
    }
}