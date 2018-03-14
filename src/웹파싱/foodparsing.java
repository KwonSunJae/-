package 웹파싱;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;






public class foodparsing {

		private static String address = "http://www.kyeongbuk.hs.kr/user/carte/calendarlist.do?menuCd=MCD_000000000000060088";

	public static void main(String[] args) throws Exception, Exception {
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://ksun1234.cafe24.com/ksun1234","ksun1234","kwonsunjae1!");
			Statement stmt = conn.createStatement();
			Document doc = Jsoup.connect(address).get();
			for(int i=0; i<42; i++) {
				Elements elt = doc.select("td").eq(i);
				String day = elt.text();
				String dae = day.split(" ")[0];
				String menu= elt.select("dd").text();
				if(dae.equals("")) {
					
				}
				else {
					String rday= String.format("%02d",Integer.parseInt(dae) );
					System.out.println("2018-03-"+String.format("%02d",Integer.parseInt(dae) )+"/"+menu);
					String sql = "insert FOOD  values('2018-03-"+rday+"','"+menu+"') ON DUPLICATE KEY UPDATE foodCont='"+ menu+"';";
					stmt.executeUpdate(sql);

				}
				
			}
			
			
			conn.close();
			
		
		
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