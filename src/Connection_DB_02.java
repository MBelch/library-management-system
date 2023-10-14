import documents.*;
import emprunteur.*;
import java.io.FileInputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Connection_DB_02 {
    
    	private  java.sql.Connection myconn;
	public Connection_DB_02 () {
            try {
                Properties props=new Properties();
                
                myconn=DriverManager.getConnection("jdbc:mysql://localhost:3306/biblio","root","");
                System.out.println("Connexion avec succé a : ");
            } catch (SQLException ex) {
                Logger.getLogger(Connection_BD.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
        
        
	 public void AjouterEtudiant(Etudiant ad) {
            try {
                java.sql.Statement myStmt=null;
                
                myStmt=myconn.createStatement();
                myStmt.executeUpdate("insert into etudiants(cne,nom,prenom,filier,password,age,nombreEmprunts,nombreEmpruntsMax) "
                        + "values('"+ad.getCne()+"','"+ad.getNom()+"','"+ad.getPrenom()+"','"+ad.getFilier()+"','"+ad.getPassword()+"','"+ad.getAge()+"','0','"+ad.getNombreEmpruntsMax()+"')");
                
                myStmt.close();

                myStmt=myconn.createStatement();
                myStmt.executeUpdate("insert into adherent(id_adh,libeller) values('"+ad.getCne()+"','etudiant')");                
            } catch (SQLException ex) {
                
            }
	    
	    
	 }        
         
         
	  public void AjouterProfesseur(Professeur ad) {
            try {
                java.sql.Statement myStmt = null;
                
                myStmt=myconn.createStatement();
                myStmt.executeUpdate("insert into professeurs(cin,nom,prenom,matiere,password,age,nombreEmprunts,nombreEmpruntsMax) "
                        + "values('"+ad.getCin()+"','"+ad.getNom()+"','"+ad.getPrenom()+"','"+ad.getMatiere()+"','"+ad.getPassword()+"','"+ad.getAge()+"','"+ad.getNombreEmprunts()+"','"+ad.getNombreEmpruntsMax()+"')");

                myStmt.close();

                myStmt=myconn.createStatement();
                myStmt.executeUpdate("insert into adherent(id_adh,libeller) values('"+ad.getCin()+"','professeur')");                 
            
            } catch (SQLException ex) {
 
            }
            
          } 
          
          
          
   /*
               
         public void SupprimerEtudiant(String id_etud){
             
            try {
                java.sql.Statement myStmt = null;
                
                myStmt=myconn.createStatement();
                myStmt.executeUpdate("delete from etudiants where cne=id_etud");
                myStmt.close();

                myStmt=myconn.createStatement();
                myStmt.executeUpdate("delete from adherent where id_adh=id_etud");                 
            
            } catch (SQLException ex) {
 
            }
             
         } 
    */   
         
     /*
         
            
         public void SupprimerProfesseur(String id_prof){
             
            try {
                java.sql.Statement myStmt = null;
                
                myStmt=myconn.createStatement();
                myStmt.executeUpdate("delete from professeurs where cne=id_prof");
                myStmt.close();

                myStmt=myconn.createStatement();
                myStmt.executeUpdate("delete from adherent where id_adh=id_prof");                 
            
            } catch (SQLException ex) {
 
            }
           
            
             
         }          
    */ 
          

         public void SupprimerAdherent(String id_adherent){
             
           
              try {
                java.sql.Statement myStmt = null;
                ResultSet myRs=null;
                myStmt=myconn.createStatement();
                myRs = myStmt.executeQuery("select libeller from adherent where id_adh = id_adherent");
                
                 String name_table = myRs.getString("libeller");
                    
                if(name_table.toLowerCase() == "etudiant"){
                     myStmt.close();
                    
                     myStmt=myconn.createStatement();
                     myStmt.executeUpdate("delete from etudiants where cne = id_adherent");
                     myStmt.close();

                     myStmt=myconn.createStatement();
                     myStmt.executeUpdate("delete from adherent where id_adh = id_adherent");                     
                    
                   
                }
                      
                    if(name_table.toLowerCase() == "professeur"){
                 
                     myStmt.close();
                     myStmt=myconn.createStatement();
                     myStmt.executeUpdate("delete from professeurs where cne = id_adherent");
                    
                     myStmt.close();

                    myStmt=myconn.createStatement();
                    myStmt.executeUpdate("delete from adherent where id_adh = id_adherent");
                                  
                    }
                
                               
            
            } catch (SQLException ex) {
 
            }             
             
         } 
         
         
        
        public void get_adhrenet(String id_adherent){   // REcherche adherent
            
              try {
                java.sql.Statement myStmt = null;
                ResultSet myRs=null;
                myStmt=myconn.createStatement();
                myRs = myStmt.executeQuery("select libeller from adherent where id_adh = id_adherent");
                
                 String name_table = myRs.getString("libeller");
                    
                if(name_table.toLowerCase() == "etudiant"){
                    myStmt.close();
                    myRs.close();
                    myStmt=myconn.createStatement();
                   myRs = myStmt.executeQuery("select * from etudiants where cne = id_adherent");
                   
                    Etudiant ed = new Etudiant(myRs.getString("cne"),myRs.getString("filier"),myRs.getString("nom"),myRs.getString("prenom"),myRs.getInt("age"));
                    
                     ed.toString();
                }
                      
                    if(name_table.toLowerCase() == "professeur"){
                 
                     myStmt.close();
                     myRs.close();
                     myStmt=myconn.createStatement();
                     myRs = myStmt.executeQuery("select * from professeurs where cin = id_adherent");
                    
                     Professeur prof = new Professeur(myRs.getString("cin"),myRs.getString("matiere"),myRs.getString("nom"),myRs.getString("prenom"),myRs.getInt("age"));
                    
                     prof.toString();                     

                    }
                    
               } catch (SQLException ex) {
 
            }          
        }     
        
        
    public void AjouterLivre(Livre liv){
        
            try {
                java.sql.Statement myStmt=null;
                
                String at=liv.getAuteur()[0];
                for(int i=1;i<liv.getAuteur().length;i++)at=","+at;
                myStmt=myconn.createStatement();
                myStmt.executeUpdate("insert into livres(isbn,titre,auteur,editeur,edition,nombreExemplaires,tome,langue,nombreChapitres,nombredepage,type) "
                        + "values('"+liv.getIsbn()+"','"+liv.getTitre()+"','"+at+"','"+liv.getEditeur()+"','"+liv.getEdition()+"','"+liv.getNombreExemplaires()+"','"+liv.getTome()+"','"+liv.getLangue()+"','"+liv.getNombreChapitres()+"','"+liv.getType()+"')");
            
                myStmt.close();
                myStmt=myconn.createStatement();
                myStmt.executeUpdate("insert into document(isbn,libeller) values('"+liv.getIsbn()+"','livre')");            
            } catch (SQLException ex) {
 
            }            
	    
	    
	 }
    
    
    public void AjouterDictionnaire(Dictionnaire dic){
        
            try {
                java.sql.Statement myStmt=null;
                
                String at=dic.getAuteur()[0];
                for(int i=1;i<dic.getAuteur().length;i++)at=","+at;
                myStmt=myconn.createStatement();
                myStmt.executeUpdate("insert into dictionnaires(isbn,titre,auteur,editeur,edition,nombreExemplaires,tome,langue) "
                        + "values('"+dic.getIsbn()+"','"+dic.getTitre()+"','"+at+"','"+dic.getEditeur()+"','"+dic.getEdition()+"','"+dic.getNombreExemplaires()+"','"+dic.getTome()+"','"+dic.getLangue()+"')");
                
                myStmt.close();
                myStmt=myconn.createStatement();
                myStmt.executeUpdate("insert into document(isbn,libeller) values('"+dic.getIsbn()+"','dictionaire')");
            } catch (SQLException ex) {
 
            }            
	    
	    
	 }


    public void AjouterMagazine(Magazine mag){
        
            try {
                java.sql.Statement myStmt=null;
                
                String at=mag.getAuteur()[0];
                for(int i=1;i<mag.getAuteur().length;i++)at=","+at;
                myStmt=myconn.createStatement();
                myStmt.executeUpdate("insert into magazines(isbn,titre,auteur,editeur,edition,periodicite,mois,jour) "
                        + "values('"+mag.getIsbn()+"','"+mag.getTitre()+"','"+at+"','"+mag.getEditeur()+"','"+mag.getEdition()+"','"+mag.getPeriodicite()+"','"+mag.getMois()+"','"+mag.getJour()+"')");
                
                myStmt.close();
                myStmt=myconn.createStatement();
                myStmt.executeUpdate("insert into document(isbn,libeller) values('"+mag.getIsbn()+"','magazine')");                
            } catch (SQLException ex) {
 
            }            
	    
	    
	 } 
    
    
    public void supprimerDocument(String id_doc) {
        
           
              try {
                java.sql.Statement myStmt = null;
                ResultSet myRs=null;
                myStmt=myconn.createStatement();
                myRs = myStmt.executeQuery("select libeller from document where isbn = id_doc");
                
                 String name_table = myRs.getString("libeller");
                    
                if(name_table.toLowerCase() == "livre"){
                     myStmt.close();
                    
                     myStmt=myconn.createStatement();
                     myStmt.executeUpdate("delete from livres where isbn = id_doc");
                     myStmt.close();

                     myStmt=myconn.createStatement();
                     myStmt.executeUpdate("delete from document where isbn = id_doc");                     
                    
                   
                }
                      
                    if(name_table.toLowerCase() == "dictionnaire"){
                 
                     myStmt.close();
                     myStmt=myconn.createStatement();
                     myStmt.executeUpdate("delete from dictionnaires where isbn = id_doc");
                    
                     myStmt.close();

                    myStmt=myconn.createStatement();
                    myStmt.executeUpdate("delete from document where isbn = id_doc");
                                  
                    }
                    
                    if(name_table.toLowerCase() == "magazine"){
                 
                     myStmt.close();
                     myStmt=myconn.createStatement();
                     myStmt.executeUpdate("delete from magazines where isbn = id_doc");
                    
                     myStmt.close();

                    myStmt=myconn.createStatement();
                    myStmt.executeUpdate("delete from document where isbn = id_doc");
                                  
                    }                    
                
                               
            
            } catch (SQLException ex) {
 
            }  
    }   
            
        
    public void get_Document(String id_doc){
        
                      try {
                java.sql.Statement myStmt = null;
                ResultSet myRs=null;
                myStmt=myconn.createStatement();
                myRs = myStmt.executeQuery("select libeller from document where isbn = id_doc");
                
                
                 String name_table = myRs.getString("lieller");
                    
                if("livre".equals(name_table.toLowerCase())){
                    myStmt.close();
                    myRs.close();
                    myStmt=myconn.createStatement();
                   myRs = myStmt.executeQuery("select * from livres where isbn = id_doc");
                   
                   String chaine = myRs.getString("auteur");
                   char[] Tab;
                   int 	nombrev=0,j=0;
		   Tab = chaine.toCharArray();
		for(int i=0;i<Tab.length;i++)
		{
			if(Tab[i]==',')nombrev++;
		}
                
               String[] auteurs = new String[nombrev+1];
		auteurs[0]="";
		for(int i=0;i<Tab.length;i++)
		{
			if(Tab[i]==',') {
                            j++;
                            auteurs[j]="";}
			else
			{
				auteurs[j]+=Tab[i];
			}
		}
                
                    Livre liv = new Livre(myRs.getString("Titre"),auteurs,myRs.getString("editeur"),myRs.getInt("edition"),myRs.getInt("nombreExemplaires"),myRs.getString("Isbn"),myRs.getInt("nombreChapitres"),myRs.getInt("	nombredepage"),myRs.getString("langue"),myRs.getInt("tome"),myRs.getString("type"));
             
                     liv.toString();
                }

                      
                    if("dictionnaire".equals(name_table.toLowerCase())){
                 
                     myStmt.close();
                     myRs.close();
                     myStmt=myconn.createStatement();
                     myRs = myStmt.executeQuery("select * from dictionnaires where isbn = id_doc");
               
                   String chaine = myRs.getString("auteur");
                   char[] Tab;
                   int 	nombrev=0,j=0;
		   Tab = chaine.toCharArray();
		for(int i=0;i<Tab.length;i++)
		{
			if(Tab[i]==',')nombrev++;
		}
                
               String[] auteurs = new String[nombrev+1];
		auteurs[0]="";
		for(int i=0;i<Tab.length;i++)
		{
			if(Tab[i]==',') {
                            j++;
                            auteurs[j]="";}
			else
			{
				auteurs[j]+=Tab[i];
			}
		}                     
                    
                     Dictionnaire dic = new Dictionnaire(myRs.getString("Titre"),auteurs,myRs.getString("editeur"),myRs.getInt("edition"),myRs.getInt("nombreExemplaires"),myRs.getString("Isbn"),myRs.getInt("tome"),myRs.getString("langue"));
                    
                     dic.toString();                     
                 
                    }
                    
                    if("magazine".equals(name_table.toLowerCase())){
                 
                     myStmt.close();
                     myRs.close();
                     myStmt=myconn.createStatement();
                     myRs = myStmt.executeQuery("select * from magazines where isbn = id_doc");
                     
                   String chaine = myRs.getString("auteur");
                   char[] Tab;
                   int 	nombrev=0,j=0;
		   Tab = chaine.toCharArray();
		for(int i=0;i<Tab.length;i++)
		{
			if(Tab[i]==',')nombrev++;
		}
                
               String[] auteurs = new String[nombrev+1];
		auteurs[0]="";
		for(int i=0;i<Tab.length;i++)
		{
			if(Tab[i]==',') {
                            j++;
                            auteurs[j]="";}
			else
			{
				auteurs[j]+=Tab[i];
			}
		}                      
                    
                     Magazine mag = new Magazine(myRs.getString("Titre"),auteurs,myRs.getString("editeur"),myRs.getInt("edition"),myRs.getInt("nombreExemplaire"),myRs.getString("Isbn"),myRs.getString("periodicite"),myRs.getInt("mois"),myRs.getInt("jour"));
                    
                     mag.toString();                     

                    }                    
                    
                    
               } catch (SQLException ex) {
 
            }   
    }
    
    
    public void Emprunter(String id_adherent, String id_doc){
        
    
        try {
                java.sql.Statement myStmt = null;
                ResultSet myRs=null;
                myStmt=myconn.createStatement();
                myRs = myStmt.executeQuery("select libeller from adherent where id_adh = id_adherent");
                
                 String name_table = myRs.getString("libeller");
                 
               if("etudiant".equals(name_table.toLowerCase())){
                 
                     myStmt.close();
                     myRs.close();
                     myStmt=myconn.createStatement();
                   
                    myRs = myStmt.executeQuery("select nombreEmprunts, nombreEmpruntsMax from etudiant where Cne = id_adherent");
                    
                   
                    if(myRs.getInt("nombreEmpruntsMax") < myRs.getInt("nombreEmprunts") + 1 ){
                         myStmt.close();
                         myStmt.executeUpdate("insert into emprunter(id_document,id_adherent) values('id_adherent','id_doc')");
                         myStmt.executeUpdate("update etudiant set nombreEmprunts = nombreEmprunts + 1  where cne = id_adherent");
                    }
               }   
               
               
               if("professeur".equals(name_table.toLowerCase())){
                 
                     myStmt.close();
                     myRs.close();
                     myStmt=myconn.createStatement();
                   
                    myRs = myStmt.executeQuery("select nombreEmprunts, nombreEmpruntsMax from etudiant where Cne = id_adherent");
                    
                   
                    if(myRs.getInt("nombreEmpruntsMax") < myRs.getInt("nombreEmprunts") + 1 ){
                         myStmt.close();
                         myStmt.executeUpdate("insert into emprunter(id_document,id_adherent) values('id_adherent','id_doc')");
                           myStmt.executeUpdate("update professeur set nombreEmprunts = nombreEmprunts + 1 where cin = id_adherent ");
                    }
                }                 
                
        } catch (SQLException ex) {
     
           }

    }         
    
    
    
   public void Rendre_Document(String id_adher, String id_doc) {
       
         try {
                java.sql.Statement myStmt = null;
                ResultSet myRs=null;
                myStmt=myconn.createStatement();
                myRs = myStmt.executeQuery("select libeller from adherent where id_adh = id_adher");
                
                 String name_table = myRs.getString("libeller");
                 
               if("etudiant".equals(name_table.toLowerCase())){
                 
                     myStmt.close();                   
                     myStmt=myconn.createStatement();
         
                         myStmt.close();
                         myStmt.executeUpdate("update etudiant set nombreEmprunts = nombreEmprunts - 1  where cne = id_adher");
                     myStmt.close();
                     myStmt=myconn.createStatement();                         
                         myStmt.executeUpdate("delete from emprunter where id_adherent = id_adher");
                    
               }   
               
               
               if("professeur".equals(name_table.toLowerCase())){
                 
                     myStmt.close();                   
                     myStmt=myconn.createStatement();
         
                         myStmt.close();
                         myStmt.executeUpdate("update professeur set nombreEmprunts = nombreEmprunts - 1  where cne = id_adher");
                     myStmt.close();
                     myStmt=myconn.createStatement();                         
                         myStmt.executeUpdate("delete from emprunter where id_adherent = id_adher");
                }                 
                
        } catch (SQLException ex) {
     
           }      
       
   }      
           
        
 	public static void main(String[] args) {
            
            
            Connection_BD db=new Connection_BD();
           // db.AjouterEtudiant(new Etudiant("1234","SSSSSS","lmmmmm","fffff",12));
            
           
    } 
	       
}

