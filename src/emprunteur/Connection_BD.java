
package emprunteur;

import documents.Bibliotheque;
import documents.Dictionnaire;
import documents.Document;
import documents.Livre;
import documents.Magazine;
import java.io.FileInputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Connection_BD {
    
    
	private  java.sql.Connection myconn;
	public Connection_BD () {
            try {
                Properties props=new Properties();
                
                myconn=DriverManager.getConnection("jdbc:mysql://localhost:3306/biblio","root","");
                System.out.println("Connexion avec succé a : ");
            } catch (SQLException ex) {
                Logger.getLogger(Connection_BD.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
	
	
	/* public ArrayList<Adherent> getAdherent() throws SQLException{   
	    	ArrayList<Adherent> ad=new ArrayList<Adherent>();;
	    	java.sql.Statement myStmt=null;
		    ResultSet myRs=null;
	    try{
	    	myStmt=myconn.createStatement();
	    	myRs=myStmt.executeQuery("select * from Adherent");
	    	
	    	while(myRs.next()){
	    		Adherent adherent = new Adherent();
	    		adherent.setAge(myRs.getInt("age"));
	    		adherent.setCin(myRs.getString("cin"));
	    		adherent.setNom(myRs.getString("nom"));
	    		adherent.setPrenom(myRs.getString("prenom"));
	    		adherent.setMdp(myRs.getString("mdp"));
	    		adherent.setNombreEmprunts(myRs.getInt("nb_emprunt"));
	    		ad.add(adherent);
	    	}
	    }
	    finally{
	    	
	    }
		return ad;
  
	}	
	 
	
	 public void delete_adherent(Adherent ad) throws SQLException{
		 java.sql.Statement myStmt=null;
		    ResultSet myRs=null;
	    try{
	    	myStmt=myconn.createStatement();
	    	myStmt.executeUpdate("delete from Adherent where cin='"+ad.getCin()+"'");
	    }
	    finally{
	    	
	    }
	 }*/
	 
	 public void AjouterEtudiant(Etudiant ad) {
            try {
                java.sql.Statement myStmt=null;
                ResultSet myRs=null;
                
                myStmt=myconn.createStatement();
                myStmt.executeUpdate("insert into etudiants(cne,nom,prenom,filier,password,age,nombreEmprunts,nombreEmpruntsMax) "
                        + "values('"+ad.getCne()+"','"+ad.getNom()+"','"+ad.getPrenom()+"','"+ad.getFilier()+"','"+ad.getPassword()+"','"+ad.getAge()+"','"+ad.getNombreEmprunts()+"','"+ad.getNombreEmpruntsMax()+"')");
            } catch (SQLException ex) {
                Logger.getLogger(Connection_BD.class.getName()).log(Level.SEVERE, null, ex);
            }
	    
	    
	 }
         
	  public void AjouterProfesseur(Professeur ad) {
            try {
                java.sql.Statement myStmt=null;
                
                
                myStmt=myconn.createStatement();
                myStmt.executeUpdate("insert into professeurs(cin,nom,prenom,matiere,password,age,nombreEmprunts,nombreEmpruntsMax) "
                        + "values('"+ad.getCin()+"','"+ad.getNom()+"','"+ad.getPrenom()+"','"+ad.getMatiere()+"','"+ad.getPassword()+"','"+ad.getAge()+"','"+ad.getNombreEmprunts()+"','"+ad.getNombreEmpruntsMax()+"')");
            } catch (SQLException ex) {
                Logger.getLogger(Connection_BD.class.getName()).log(Level.SEVERE, null, ex);
            }
            
          }
          
          
    public void AjouterLivre(Livre ad){
        
            try {
                java.sql.Statement myStmt=null;
                
                String at=ad.getAuteur()[0];
                for(int i=1;i<ad.getAuteur().length;i++)at=" ,"+at;
                myStmt=myconn.createStatement();
                myStmt.executeUpdate("insert into professeurs(isbn,titre,editeur,edition,nombreExemplaires,tome,langue,nombreChapitres,nombredepage,type,auteur) "
                        + "values('"+ad.getIsbn()+"','"+ad.getTitre()+"','"+ad.getEditeur()+"','"+ad.getEdition()+"','"+ad.getNombreExemplaires()+"','"+ad.getTome()+"','"+ad.getLangue()+"','"+ad.getNombreChapitres()+"','"+ad.getType()+"','"+at+"' )");
            } catch (SQLException ex) {
                Logger.getLogger(Connection_BD.class.getName()).log(Level.SEVERE, null, ex);
            }            
	    
	    
	 }
    
    
        public void getDocumentByEditeur(String edit){
        
            try {
                ArrayList<Document> doc=new ArrayList<Document>();
                java.sql.Statement myStmt=null;
                ResultSet myRs=null;
                String[] tab = new String[1];
                myStmt=myconn.createStatement();
                myRs=myStmt.executeQuery("select * from lives where editeur = edit");
                
                while(myRs.next()){
                    tab[0] =myRs.getString("auteur");
                    doc.add(new Livre(myRs.getString("titre"),tab,myRs.getString("editeur"),myRs.getString("nombreExemplaire"),
                                      myRs.getString("isbn"),myRs.getString("nombreChapitre"),myRs.getString("nombrePages"),myRs.getString("langue"),
                                      myRs.getString("tome"),myRs.getString("type")));  /// les valeurs des colones 

                }
                
                myStmt.close();
                myRs.close();

                
                myStmt=myconn.createStatement();
                myRs=myStmt.executeQuery("select * from magazines where editeur = edit");
                
                while(myRs.next()){
                    tab[0] =myRs.getString("auteur");
                    doc.add(new Magazine(myRs.getString("titre"),tab,myRs.getString("editeur"),myRs.getString("edition"),
                                        myRs.getString("nombreExemplaire"),myRs.getString("isbn"),myRs.getString("periodicite"),myRs.getString("mois"),
                                        myRs.getString("jour")));  /// les valeurs des colones 
                    
                    
                }
                
                myStmt.close();
                myRs.close();


                myStmt=myconn.createStatement();
                myRs=myStmt.executeQuery("select * from dictionaires where editeur = edit");
                
                while(myRs.next()){
                    tab[0] =myRs.getString("auteur");
                    doc.add(new Dictionnaire(myRs.getString("titre"),tab,myRs.getString("editeur"),myRs.getString("edition"),
                                             myRs.getString("nombreExemplaire"),myRs.getString("isbn"),myRs.getString("tome"),myRs.getString("langue")));  /// les valeurs des colones 
                }
                
                myStmt.close();
                myRs.close(); 
                
            } catch (SQLException ex) {
                Logger.getLogger(Connection_BD.class.getName()).log(Level.SEVERE, null, ex);
            }
	    }
	  
	    	
	    }
	    
	 
    
    
	 /*public ArrayList<Document> getDocument() throws SQLException{
		 ArrayList<Document> doc=new ArrayList<Document>();
		 java.sql.Statement myStmt=null;
		    ResultSet myRs=null;
	    try{
	    	myStmt=myconn.createStatement();
	    	myRs=myStmt.executeQuery("select * from document");
	    	
	    	while(myRs.next()){
	    		Document document = new Document();
	    		document.setEditeur(myRs.getString("editeur"));
	    		document.setEdition(myRs.getInt("edition"));
	    		document.setIsbn(myRs.getString("isbn"));
	    		document.setTitre(myRs.getString("titre"));
	    		document.setNombreExemplaires(myRs.getInt("nb_exemplaire"));
	    		doc.add(document);
	    	}
	    }
	    finally{
	    	
	    }
		return doc;
	 }
	 
	 public void modifierMdp(Adherent ad) throws SQLException{
		 java.sql.Statement myStmt=null;
		    ResultSet myRs=null;
	    try{
	    	myStmt=myconn.createStatement();
	    	myStmt.executeUpdate("update Adherent set mdp='"+ad.getMdp()+"' where cin='"+ad.getCin()+"'");
	    }
	    finally{
	    	
	    }
	 }
	 
	 public Document getDoc(String isbn) throws SQLException{
		 
		 java.sql.Statement myStmt=null;
		    ResultSet myRs=null;
		    Document doc;
	    try{
	    	myStmt=myconn.createStatement();
	    	myRs=myStmt.executeQuery("select * from document where isbn='"+isbn+"'");

	    		doc = new Document();
	    		myRs.next();
	    		doc.setIsbn(myRs.getString("isbn"));
	    		doc.setEditeur(myRs.getString("editeur"));
	    		doc.setTitre(myRs.getString("titre"));
	    		doc.setEdition(myRs.getInt("edition"));
	    }
	    finally{
	    	
	    }
		return doc;
		 
	 }
	 
	 public ArrayList<String> getIsbn(String cin) throws SQLException{
		 
		 ArrayList<String> doc=new ArrayList<String>();
		 java.sql.Statement myStmt=null;
		    ResultSet myRs=null;
	    try{
	    	myStmt=myconn.createStatement();
	    	myRs=myStmt.executeQuery("select isbn from emprunt where cin='"+cin+"'");
	    	System.out.println(cin);
	    	while(myRs.next()){
	    		doc.add(myRs.getString("isbn"));
	    	}
	    }
	    finally{
	    	
	    }
		return doc;
		 
	 }
	 
	 public int count() throws SQLException{
		 int x = 0;
		 java.sql.Statement myStmt=null;
		    ResultSet myRs=null;
	    try{
	    	myStmt=myconn.createStatement();
	    	myRs=myStmt.executeQuery("select count(*) as somme from Document");
	    	
	    	while(myRs.next()){
	    		x=myRs.getInt("somme");
	    	}
	    }
	    finally{
	    	
	    }
	    return x;
		 
	 }
	 
	 public boolean etudiant(Adherent ad) throws SQLException{
		 int x = 0;
		 String cin;
		 java.sql.Statement myStmt=null;
		    ResultSet myRs=null;
	    try{
	    	myStmt=myconn.createStatement();
	    	myRs=myStmt.executeQuery("select * from Adherent");
	    	
	    	while(myRs.next()){
	    		x=myRs.getInt("etudiant");
	    		cin=myRs.getString("cin");
	    		if(cin.equals(ad.getCin())){
	    			if(x==1)return true;
	    			return false;
	    		}
	    	}
	    }
	    finally{
	    	
	    }
		return false;
	 }
	 
	 public void reserver(String isbn,Adherent ad) throws SQLException{
		 java.sql.Statement myStmt=null;
		    ResultSet myRs=null;
	    try{
	    	myStmt=myconn.createStatement();
	    	myStmt.executeUpdate("insert into emprunt(isbn,cin) values('"+isbn+"','"+ad.getCin()+"')");
	    	myStmt.executeUpdate("update adherent set nb_emprunt=nb_emprunt+1 where cin='"+ad.getCin()+"'");
	    }
	    finally{
	    	
	    }	 
	 }
	 
	 public void rendre(String isbn,Adherent ad) throws SQLException{
		 java.sql.Statement myStmt=null;
		    ResultSet myRs=null;
	    try{
	    	myStmt=myconn.createStatement();
	    	myStmt.executeUpdate("delete from emprunt where isbn='"+isbn+"' and cin='"+ad.getCin()+"'");
	    	myStmt.executeUpdate("update adherent set nb_emprunt=nb_emprunt-1 where cin='"+ad.getCin()+"'");
	    }
	    finally{
	    	
	    }	
	 }
	 */
	 
	public static void main(String[] args) {
            
            
            Connection_BD db=new Connection_BD();
            db.AjouterEtudiant(new Etudiant("1234","SSSSSS","lmmmmm","fffff",12));
            
           
    } 
	 
    
}
