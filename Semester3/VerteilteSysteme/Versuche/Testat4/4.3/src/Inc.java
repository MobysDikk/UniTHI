
public class Inc extends Thread {
    

public void run () {

    for(int i =0; i<0;i++) {
        IncDecThreads.setToggle(IncDecThreads.getToggle() - 1);
        System.out.println(IncDecThreads.getToggle());
    }
       
      }
    
    

}
