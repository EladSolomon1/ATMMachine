package sample.Database;

import sample.model.User;

import java.sql.*;

public class DatabaseHandler extends Configs {

    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString= "jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName;
        Class.forName("com.mysql.jdbc.Driver");

        dbConnection= DriverManager.getConnection(connectionString,dbUser,dbPass);
        return dbConnection;
    }

    public void signupUser(User user){
        String insert="INSERT INTO "+Const.ACCOUNTS_TABLE+" ("+Const.ACCOUNTS_FIRSTNAME+","+Const.ACCOUNTS_LASTNAME+","
                +Const.ACCOUNTS_PASSWORD+","+Const.ACCOUNTS_EMAIL+","+Const.ACCOUNTS_GENDER+") "+" VALUES(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement=getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1,user.getFirstName());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.setString(3,user.getPassword());
            preparedStatement.setString(4,user.getEmail());
            preparedStatement.setString(5,user.getGender());

            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getUserByAccountNumber(User user){
        ResultSet resultSet=null;

            String query="SELECT * FROM "+Const.ACCOUNTS_TABLE+" WHERE "+Const.ACCOUNTS_ACCOUNT_NUMBER+"=?";
            try {
                PreparedStatement preparedStatement=getDbConnection().prepareStatement(query);
                preparedStatement.setInt(1,user.getCustomer_number());

                resultSet=preparedStatement.executeQuery();

            }catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        return resultSet;

    }
    public void updateAccount(User user,double updateAmount){
        ResultSet resultSet=null;
        String query="UPDATE "+Const.ACCOUNTS_TABLE+" SET "+Const.ACCOUNTS_BALANCE+"=? "+" WHERE "+Const.ACCOUNTS_ACCOUNT_NUMBER+"=?";
        try {
            PreparedStatement preparedStatement=getDbConnection().prepareStatement(query);
            preparedStatement.setDouble(1,updateAmount);
            preparedStatement.setDouble(2,user.getCustomer_number());

            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getUserByAccountNumberAndPassword(User user){
        ResultSet resultSet=null;

        if (user.getCustomer_number()!=0||!user.getPassword().equals("")){
            String query="SELECT * FROM "+Const.ACCOUNTS_TABLE+" WHERE "+Const.ACCOUNTS_ACCOUNT_NUMBER+"=?"+" AND "+Const.ACCOUNTS_PASSWORD+"=?";
            try {
                PreparedStatement preparedStatement=getDbConnection().prepareStatement(query);
                preparedStatement.setInt(1,user.getCustomer_number());
                preparedStatement.setString(2,user.getPassword());

                resultSet=preparedStatement.executeQuery();

            }catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Please enter your credentials");
        }
        return resultSet;
    }
    public ResultSet getUserByEmail(User user){
        ResultSet resultSet=null;

        if (!user.getEmail().equals("")){
            String query="SELECT * FROM "+Const.ACCOUNTS_TABLE+" WHERE "+Const.ACCOUNTS_EMAIL+"=?";
            try {
                PreparedStatement preparedStatement=getDbConnection().prepareStatement(query);
                preparedStatement.setString(1,user.getEmail());

                resultSet=preparedStatement.executeQuery();

            }catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Please enter your credentials");
        }
        return resultSet;
    }

}
