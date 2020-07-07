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
    	    String command = "E://javapy1//py2.py";
            String num1 = "outcome12";
            
            

    	    String[] cmdArr = new String[] {exe, command,num1 };
            Process pr = Runtime.getRuntime().exec(cmdArr);
            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line=null;
            System.out.println("begin to read\r");
            

            while ((line = in.readLine()) != null) {
            	int a=0;
            	int b=30000;
            	int reallength=0;
            	String sline=null;
                //line = decodeUnicode(line);
            	//System.out.println(line.length());
            	if(line.length()>=30000) {
            	int times=line.length()/30000;
            	reallength=line.length();
            	times++;
            	for(int h=1;h<=times;h++) {
                sline=line.substring(a, b);//0-42703  30000-42703
                
                a+=30000;
                b+=30000;
                if(b>line.length()) {
                	b=line.length();
                }
                System.out.println(sline);
                }
                
                }
            	else {
            		System.out.println(line);
            	}
            }
                
            in.close();
            pr.waitFor();
            System.out.println("end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
