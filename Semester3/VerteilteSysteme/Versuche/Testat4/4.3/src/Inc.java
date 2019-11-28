
public class Inc extends Thread {
    
    Int increase;
    
    Inc(Int number){
        this.increase = number;
    }
    

public void run () {
    
    for(int i =0; i<10;i++) {
       int add= increase.getInt();
       add++;
       
       increase.setInt(add);
       System.out.println(add);
      }
    
}  

}
