
public class MachMAl {

    public static void eineZeitLangGarnichts() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // System.out.println("wartet ein bisschen");

    }

    public static void eineLängereZeitNichts() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // System.out.println("wartet ein bisschen");

    }

}
