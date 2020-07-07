import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class InvokeByRuntime {
	/**

     * @param args

     * @throws IOException 

     * @throws InterruptedException 

     */

    public static void main(String[] args) throws IOException, InterruptedException {

    	
   try {
    		String exe = "python";
    	    String command = "E:\\javapy1\\py2.py";
            String num1 = "outcome12";
            
            

    	    String[] cmdArr = new String[] {exe, command, num1 };
            Process pr = Runtime.getRuntime().exec(cmdArr);
            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;
            line = in.readLine();
            while ((line = in.readLine()) != null) {
                //line = decodeUnicode(line);
            	
                System.out.println(line);
            }
                
            in.close();
            pr.waitFor();
            System.out.println("end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
