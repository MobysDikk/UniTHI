
public class Dec extends Thread {

    Int decrease;
   
    
    Dec(Int number){
        this.decrease = number;
    }
    

public void run () {

    for(int i =0; i<10;i++) {
       int add= decrease.getInt();
       add--;
       
       decrease.setInt(add);
       System.out.println(add);
      }
    
}  
    
    }


