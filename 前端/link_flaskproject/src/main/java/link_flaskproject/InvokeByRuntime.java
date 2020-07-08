package link_flaskproject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

public class InvokeByRuntime {
	/**
	 * 
	 * @param args
	 * 
	 * @throws IOException
	 * 
	 * @throws InterruptedException
	 * 
	 */

	public static void main(String[] args) throws IOException, InterruptedException {

		try {
			Socket socket = new Socket("127.0.0.1", 8081);
//			String requeststr = "http://127.0.0.1:8081/read_json/"+args[0];//正常用时
			String requeststr = "http://127.0.0.1:8081/read_json/"+"outcome1";//测试时
			URL url = new URL(requeststr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "text/html");
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestProperty("Charsert", "UTF-8");
            conn.connect();
            conn.setConnectTimeout(10000);
            InputStream in= conn.getInputStream();
            		
			System.out.println("begin to read\r");
			
//			File file = new File("src/main/webapp/json/echarts"+args[0]+".json");//正常用时
			File file = new File("src/main/webapp/json/echarts/outcome1.json");//测试时
			FileOutputStream out = new FileOutputStream(file);
			
			byte[] buffer = new byte[1024];
	        int temp = 0;
	        while ((temp = in.read(buffer)) != -1) {
                /*将缓存数组中的数据写入文件中，注意：写入的是读取的真实长度；
                 *如果使用fos.write(buffer)方法，那么写入的长度将会是1024，即缓存
                 *数组的长度*/
                out.write(buffer, 0, temp);
            }
			
	        in.close();
	        out.close();

//			while ((line = in.readLine()) != null) {
//				int a = 0;
//				int b = 30000;
//				int reallength = 0;
//				String sline = null;
//				// line = decodeUnicode(line);
//				// System.out.println(line.length());
//				if (line.length() >= 30000) {
//					int times = line.length() / 30000;
//					reallength = line.length();
//					times++;
//					for (int h = 1; h <= times; h++) {
//						sline = line.substring(a, b);// 0-42703 30000-42703
//
//						a += 30000;
//						b += 30000;
//						if (b > line.length()) {
//							b = line.length();
//						}
//						System.out.println(sline);
//					}
//
//				} else {
//					System.out.println(line);
//				}
//			}
//			in.close();
//			pr.waitFor();
			System.out.println("end");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}