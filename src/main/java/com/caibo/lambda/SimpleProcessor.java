package com.caibo.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class SimpleProcessor implements RequestHandler<Map<String, String>, String> {
  private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
  private static String DBPASSWORD = "DBPASSWORD";
  private static String DBURL = "DBURL";
  private static String DBUSER = "DBUSER";

  private String dbPassword;
  private String dbUrl;
  private String dbUser;

  public static void main(String[] args) {
    System.out.println("Hello World! ABC ");
  }

  @Override
  public String handleRequest(Map<String, String> event, Context context) {
    LambdaLogger logger = context.getLogger();

    String response = new String("200 OK");
    // log execution details
    logger.log("ENVIRONMENT VARIABLES: " + gson.toJson(System.getenv()));
    if (dbPassword == null) {
      this.dbPassword = System.getenv(DBPASSWORD);
      this.dbUrl = System.getenv(DBURL);
      this.dbUser = System.getenv(DBUSER);
    }

    logger.log("DBURL=" + System.getenv(DBPASSWORD));
    logger.log("DBURL=" + System.getenv(DBURL));
    logger.log("DBUSER=" + System.getenv(DBUSER));

    logger.log("CONTEXT: " + gson.toJson(context));
    // process event
    logger.log("EVENT: " + gson.toJson(event));
    logger.log("EVENT TYPE: " + event.getClass().toString());
    return response;
  }

  // function to conect rds mysql
  private Connection getDBConnection() {
    Connection conn = null;
    try {
      // db parameters
      
      
      // JDBC and database properties
      Class.forName("com.mysql.jdbc.Driver");
      // create a connection to the database
      conn = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword);

      System.out.println("Connection to MySQL database established");

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return conn;
  }

  private boolean writeData(Connection conn, String name, String email) {
    try {
      String sql = "INSERT INTO user (name, email) VALUES (?, ?)";
      java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, name);
      pstmt.setString(2, email);
      pstmt.executeUpdate();
      pstmt.close();
      conn.close();
      return true;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
  }
}
