
package shopp.services;



import shopp.entities.Reclamation;
import shopp.utils.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReclamationService{
    private static ReclamationService instance;
    public static ReclamationService getInstance(){
        	if(instance == null) {
			instance = new ReclamationService();
		}
			
		return instance;	
    }
    
     public Connection connection = new Connection();

    public ReclamationService() {
    
    }
    public ResultSet getall_rc() {
         
        try {
            PreparedStatement req = connection.getConnection().prepareStatement("SELECT * FROM reclamation");
            ResultSet rs = req.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
      return null;
    }

public void ajouterReclamation(Reclamation r){
    String query="insert into reclamation(object,description) values (?,?)";
        try {
            PreparedStatement ste = connection.getConnection().prepareStatement(query);
            ste.setString(1, r.getObject());
            ste.setString(2, r.getDescription());
            ste.executeUpdate();
            System.out.println("Reclamation Ajoutée!!");
        } catch (SQLException ex) {
            System.out.println("");
        }
    
} 
    public void modifier_reclamation(Reclamation p ,String ob) {
        try {
            PreparedStatement req = connection.getConnection().prepareStatement("update reclamation set object=?,description=? where object=?");
            
            req.setString(1, p.getObject());
            req.setString(2, p.getDescription());
    
      
            req.setString(3,ob);
          
            req.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
      public boolean supprimer_rec(String p) {
         boolean ok=false;
        try {
            
            PreparedStatement req = connection.getConnection().prepareStatement("delete from reclamation where object = ? ");
            req.setString(1, p);
            req.executeUpdate();
ok=true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ok;
    }
}

   


