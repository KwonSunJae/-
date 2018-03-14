package 웹파싱;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class AlertParsing {
private final static String address = "http://www.kyeongbuk.hs.kr/board/list.do?boardId=BBS_0000001&menuCd=MCD_000000000000060047&startPage=";
	
	public static void main(String[] args) throws IOException {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://ksun1234.cafe24.com/ksun1234","ksun1234","kwonsunjae1!");
			Statement stmt = conn.createStatement();
			
				String alertCont = null, alertDate=null, alertName=null, alertSeeview=null;
				String alertNum=null;
				for(int j=1; j<10;j++) {
					Document doc = Jsoup.connect(address+j).get();
					for(int i=1;i<24;i++) {
						Elements elt = doc.getElementsByTag("tr").eq(i);
						String alertHref = elt.select("a[href]").toString();
						String splitHref = alertHref.split("Sid=")[1].split("\"")[0];
						System.out.println(splitHref);
						for(int w=0; w<5; w++) {
							String eelt = elt.select("td").eq(w).text();
							
							if(w==1) {
								alertCont = eelt;
							}
							if(w==0) {
								
								 alertNum =eelt;
							}
							else if(w==2)
							{
								alertName =eelt;
							}
							else if(w==3) {
								alertDate = eelt;
							}
							else if(w==4) {
								alertSeeview =eelt;
							}	
					}if(alertNum.equals("통합공지")) {
						alertNum ="0";
				}
					else {
					int alertnum = Integer.parseInt(alertNum);	
					String sql = "insert ALERT  values('"+alertnum+"','"+alertCont+"','"+alertName+"','"+alertDate+"','"+alertSeeview+"','"+splitHref+"') ON DUPLICATE KEY UPDATE alertSeeview='"+ alertSeeview+"';";
					System.out.println(sql);
					stmt.executeUpdate(sql);
					System.out.println(alertNum + alertCont+ alertName+ alertDate+alertSeeview);}
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
