

package librarysystem;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author udesh
 */
public class Store implements Serializable {
    public  Vector materials    = new Vector(10);
    public  Vector users        = new Vector(10);
    public  Vector borrowedMat  = new Vector(10);
    public  Vector suspendUsers = new Vector(10);


        
    public Users checkLoginUser(String indexNo,String pass) {
        
        Enumeration e = users.elements();
        while(e.hasMoreElements()){
            Users user = (Users)e.nextElement();
            if(user.getIndex().equalsIgnoreCase(indexNo)&&user.getPassword().equalsIgnoreCase(pass)){
                return user;
            }
        }
        return null;
    }

    public Users checkUser(String indexNo,String name) {
        for(int i=0;i<users.size();i++){
            Users user = (Users) users.elementAt(i);
            if(user.getIndex().equalsIgnoreCase(indexNo)&&user.getName().equalsIgnoreCase(name)){
                return user;
            }
        }
       
        return null;
    }

    public Users login(String indexNo,String pass){
        
        Users user1 = checkLoginUser(indexNo, pass);
            if(user1!=null){
                return user1;
            }else{
                JOptionPane.showMessageDialog(null, "User not found, check your indexNo & password");
                Login log = new Login();
                log.setVisible(true);
                return null;
            }

    }

    public Material checkMaterial(String title,String indexNo){
        for(int i=0;i<materials.size();i++){
            Material mat = (Material) materials.elementAt(i);
            if(mat.getIndex().equalsIgnoreCase(indexNo)&&mat.getTitle().equalsIgnoreCase(title)){
                return mat;
            }
        }
        return null;
    }
   
    void addUser(String name, String indexNo, int selection) {
        if(selection==0){
            Users student=new Users(name,indexNo,selection);
            users.add(student);
            System.out.println("Student Added");
        }
        else{
            Users academic=new Users(name,indexNo,selection);
            users.add(academic);
            System.out.println("Acedamic Added");
        }
    }

    void addMaterials(String title, String index, String section, int selection) {
        if(selection==0){
            Books book = new Books(title, index, section);
            book.materialType="Book";
            materials.add(book);
            System.out.println("Book Added");
        }else if(selection==1){
            DVD dvd = new DVD(title, index, section);
            dvd.materialType="DVD";
            materials.add(dvd);
            System.out.println("DVD Added");
        }else if(selection==2){
            Journals journal = new Journals(title, index, section);
            journal.materialType="Journal";
            materials.add(journal);
            System.out.println("Journal Added");
        }
    }

    void removeUser(String name, String indexNo) {
        Users rUser = checkUser(indexNo, name);
        if(rUser!=null){
            users.remove(rUser);
            System.out.println("User Removed");
            JOptionPane.showMessageDialog(null,"User Removed");
            
        }else{
            JOptionPane.showMessageDialog(null,"Entry wrong , Please check");
        }
    }

    void removeMaterial(String title,String index){
        Material rMaterial = checkMaterial(title, index);
        if(rMaterial!=null){
            materials.remove(rMaterial);
            System.out.println("Mateial removed");
            JOptionPane.showMessageDialog(null,"Material Removed");
        }else{
            JOptionPane.showMessageDialog(null,"Entry wrong , Please check");
        }
    }

    void borrowMaterial(String title,String index,int selection,Users user){
        Material bMaterial = checkMaterial(title, index);
        if(bMaterial!=null){
            Material b2Material = bMaterial;
            b2Material.setBorrowingDate(Calendar.getInstance());
            System.out.println(bMaterial.materialType+" Borrowed");
            JOptionPane.showMessageDialog(null,bMaterial.materialType+" Borrowed");
            materials.remove(bMaterial);
            borrowedMat.add(b2Material);
            user.noOfMaterialBorrowed++;

        }else{
            JOptionPane.showMessageDialog(null,"Entry wrong , Please check");
        }
        
    }

    void returnMaterial(String title,String index,int selection,Users user){
        Material rMaterial = checkMaterial(title, index);
            if(rMaterial!=null){
                Material r2Material= rMaterial;
                rMaterial.setReturnDate(Calendar.getInstance());
                int demerit = 0;
                demerit = setDemeritPoints(r2Material.borrowDate,r2Material.returnDate);
                user.demeritPoints = user.demeritPoints+demerit;
                borrowedMat.remove(rMaterial);
                user.noOfMaterialBorrowed--;
                r2Material.borrowDate=null;
                materials.add(r2Material);
                JOptionPane.showMessageDialog(null,rMaterial.materialType+" Returned");
                System.out.println(rMaterial.materialType+" Returned");
            }else{
                JOptionPane.showMessageDialog(null,rMaterial.materialType+" not found , Please check");
            }
    }

    int setDemeritPoints(Calendar borrowDate,Calendar returnDate){
        long borrowMS = borrowDate.getTimeInMillis();
        long returnMS = returnDate.getTimeInMillis();
        int days = (int) (borrowMS-returnMS)/24*60*60*1000;

        if(days>21){
            return (days-21)*1;
        }
        return 0;
    }

    void upDate(){
        Enumeration e = users.elements();
        while(e.hasMoreElements()){
            Users upUser = (Users) e.nextElement();
            if(upUser.demeritPoints>=upUser.demeritLimit){
                String name = upUser.getName();
                String index = upUser.getIndex();
                suspendUser(name, index);
            }
        }
    }

    void suspendUser(String name,String index){
        Users susUser = checkUser(name, index);
        if(susUser!=null){
            Users sus2User =susUser;
            sus2User.timesSuspended++;
            users.remove(susUser);
            suspendUsers.add(sus2User);
            JOptionPane.showMessageDialog(null,"User Suspended");
        }else{
            JOptionPane.showMessageDialog(null,"User not found , Please check");
        }
    }
    
    void reRegisterUser(String name,String index){
        Enumeration e = suspendUsers.elements();
        while(e.hasMoreElements()){
            Users user = (Users) e.nextElement();
            if(user.getIndex().equalsIgnoreCase(index) && user.getName().equalsIgnoreCase(index)){
                if(user!=null){
                    Users reUser = user;
                    suspendUsers.remove(user);
                    users.add(reUser);
                    JOptionPane.showMessageDialog(null,"User reregistered");
                }
            }else{
                JOptionPane.showMessageDialog(null,"User not found , Please check");
            }
        }
        
    }

   void printUser(Users myUser){
       if(myUser.suspendedState&&myUser.timesSuspended<5){
           System.out.print("Temporary Suspended User   : ");
       }else if(myUser.timesSuspended==myUser.maxTimeSuspended){
           System.out.print("Permenently Suspended User : ");
       }else{
           System.out.print("Active Current User        : ");
       }
       System.out.println(myUser.indexNo+"  "+myUser.name+"  "+myUser.noOfMaterialBorrowed+"/"+myUser.materialLimit+
               "  "+myUser.timesSuspended+"/5"+"  "+"Demerit = "+myUser.demeritPoints+"/"+myUser.demeritLimit);
   
   }

   void printAllUsers(Vector typeOfUser){
       Enumeration e = typeOfUser.elements();
       while(e.hasMoreElements()){
           Users user = (Users) e.nextElement();
           printUser(user);
       }
   }

   void printSuspendUsers(){
       printAllUsers(suspendUsers);
   }

   void resetPassword(String pass2,Users myUser){
       myUser.setPassword(pass2);
   }

   void saveData(Store st) {
       try{
          FileOutputStream fis = new FileOutputStream("database");
          ObjectOutputStream obj = new ObjectOutputStream(fis);
          obj.writeObject(st.users);
          obj.writeObject(st.materials);
          obj.writeObject(st.borrowedMat);
          obj.writeObject(st.suspendUsers);
          obj.close();

       }catch(Exception e){
        e.printStackTrace();
       }
   }

   
  

 
}