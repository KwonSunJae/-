package 웹파싱;



import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class mysqlconnector {
	public static void main(String[] args) {
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://ksun1234.cafe24.com/ksun1234","ksun1234","kwonsunjae1!");
		Statement stmt = conn.createStatement();
		String target = "http://www.kyeongbuk.hs.kr";
		HttpURLConnection con = (HttpURLConnection) new URL(target).openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
		String temp;
		int cnt=0;
		String date,food = null;
		String rdate = null;
		FileWriter fw =null;
		
		while((temp=br.readLine())!= null) {
			
			if(temp.contains("<span>[")&&cnt==0) {
				cnt= cnt +1;
				date=temp.split(">")[1].split("<")[0];
				rdate= date.replace("[", "");
				rdate= rdate.replace("]", "");
				fw = new FileWriter(rdate + ".txt");
				
			}if(temp.contains("href=\"/user/carte/list.do?menuCd=MCD_000000000000060088\""))
			{
				
				food=temp.split(">")[2].split("<")[0];
				fw.write(food + "\r\n");
				
			}
			
			
		}
		String sql = "insert FOOD values ('"+rdate+"','"+food+"');";
		stmt.executeUpdate(sql);
		System.out.println(sql);
		fw.close();
		con.disconnect();
		conn.close();
		br.close();
		
	
	
		
	}catch( ClassNotFoundException e )
    {
    System.out.println("JDBC 드라이버가 존재하지 않습니다. "+e);
}
catch( java.sql.SQLException e )
{
   System.out.println("DB 쿼리오류"+e); 
}           
catch( Exception e )
{
   System.out.println("기타 오류 "+e);
}
}
}


