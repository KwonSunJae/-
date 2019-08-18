package 웹파싱;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.text.Document;

public class InSeoul_Server_commit {

	public static void main(String[] args) throws Exception, Exception {
		run("C:\\Users\\DGCOM\\Desktop\\Seoul_hot\\Seoul_Hot\\data.csv","euc-kr");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://ksun1234.cafe24.com/ksun1234","ksun1234","kwonsunjae1!");
			Statement stmt = conn.createStatement();
			
			//String sql = "insert InSeoul_upso-data  values('') ON DUPLICATE KEY UPDATE foodCont='';";
			//stmt.executeUpdate(sql);

			
			conn.close();
			
		
		}catch( ClassNotFoundException e )
        {
        System.out.println("JDBC 드라이버가 존재하지 않습니다. "+e);}
		catch( java.sql.SQLException e )
		{
       System.out.println("DB 쿼리오류"+e); 
		}           
		catch( Exception e )
		{
			System.out.println("기타 오류 "+e);
		}
	}
	
	private static void run(String path, String encoding) {
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path), encoding));
            while ((line = br.readLine()) != null) {
                String[] field = line.split(cvsSplitBy);
                System.out.println(field[0]);
                break;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
	
	
}
