/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.journaldev.jsf.util.DataConnect;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Estefany
 */
@ManagedBean
@SessionScoped
public class productBean implements Serializable {
    String figurePath;
    String figureName;
    float cardPrice;
    String cardDescription;

    public String getFigurePath() {
        return figurePath;
    }

    public void setFigurePath(String figurePath) {
        this.figurePath = figurePath;
    }

    public String getFigureName() {
        return figureName;
    }

    public void setFigureName(String figureName) {
        this.figureName = figureName;
    }

    public float getCardPrice() {
        return cardPrice;
    }

    public void setCardPrice(float cardPrice) {
        this.cardPrice = cardPrice;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    

    
    
    public void addproduct(String figureName, String figurePath, float cardPrice, String cardDescription){
        Connection con = null;
        try{
            con = DataConnect.getConnection();
            String query = "Insert into Merchandise (figureName, figurePath, cardPrice, cardDescription)" + "values (?,?,?,?)";
            PreparedStatement ps1 = con.prepareStatement(query);
            ps1.setString(1, figureName);
            ps1.setString(2, figurePath);
            ps1.setFloat(3,cardPrice);
            ps1.setString(4, cardDescription);
            ps1.execute();
        }catch (SQLException ex){
            System.out.println("Insertion error -->" + ex.getMessage());
        }finally{
            DataConnect.close(con);
        }
    
}
public String addToDB(){
    addproduct(figureName, figurePath, cardPrice, cardDescription);
    return "store";
}
public void removeProduct(String figureName){
    Connection con = null;
        try{
            con = DataConnect.getConnection();
            String query = "call cw_remove(?)";
            PreparedStatement ps1 = con.prepareStatement(query);
            ps1.setString(1, figureName);
            ps1.execute();
        }catch (SQLException ex){
            System.out.println("Insertion error -->" + ex.getMessage());
        }finally{
            DataConnect.close(con);
        }
}
public String removeFromDB(){
    removeProduct(figureName);
    return "store";
} 

/*
    String url1 = "jdbc:mysql://cardsnw.czqbofjs6eks.us-west-2.rds.amazonaws.com/CardsNW";
    String user = "root";
    String password = "Banana123";
    List<Products> records = new ArrayList<Products>();

public List<Products> getProducts() throws SQLException, ClassNotFoundException{
        System.out.println("i am here");
     Class.forName("com.mysql.jdbc.Driver");
    
    Connection conn1 = DriverManager.getConnection(url1, user, password);
    if (conn1 != null) {
    } 
     
    Statement stmt = conn1.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * from Merchandise");
   
    while(rs.next()) {
            Products product = new Products();
            product.setCard_id(rs.getInt("card_id"));
            product.setFigureName(rs.getString("figureName"));
            product.setCardPrice(rs.getFloat("cardPrice"));
            product.setCardDescription(rs.getString("cardDescription"));
            product.setFigurePath(rs.getString("figurePath"));
            records.add(product);				
     }
    return records;
 
}*/

public List<Products> getProducts() {
      ResultSet rs = null;
      PreparedStatement pst = null;
      Connection con = null;
      List<Products> records = new ArrayList<Products>();

      try{
          con = DataConnect.getConnection();
          String stm = "Select * from Merchandise";
       
         pst = con.prepareStatement(stm);
         pst.execute();
         rs = pst.getResultSet();

         while(rs.next()) {
           Products product = new Products();
            product.setCard_id(rs.getInt("card_id"));
            product.setFigureName(rs.getString("figureName"));
            product.setCardPrice(rs.getFloat("cardPrice"));
            product.setCardDescription(rs.getString("cardDescription"));
            product.setFigurePath(rs.getString("figurePath"));
            records.add(product);				
         }
       } catch (SQLException e) {
         e.printStackTrace();
      }finally{
            DataConnect.close(con);
       }
      return records;

}
}
    
