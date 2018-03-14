package 웹파싱;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class NoticeParsing {
	private final static String address = "http://www.kyeongbuk.hs.kr/board/list.do?boardId=BBS_0000001&menuCd=MCD_000000000000060050&orderBy=REGISTER_DATE%20DESC&startPage=";
	
	public static void main(String[] args) throws IOException, Exception {
		while(true) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://ksun1234.cafe24.com/ksun1234","ksun1234","kwonsunjae1!");
			Statement stmt = conn.createStatement();
			
				String noticeCont = null, noticeDate=null, noticeName=null, noticeSeeview=null,noticeNum=null;
				for(int j=1; j<5;j++) {
					Document doc = Jsoup.connect(address+j).get();
					
					for(int i=1;i<13;i++) {
						Elements elt = doc.getElementsByTag("tr").eq(i);
						String noticehref = elt.select("a[href]").toString();
						String noticeHref = noticehref.split("dataSid=")[1].split("\"")[0];
								for(int w=0; w<5; w++) {
									String eelt = elt.select("td").eq(w).text();
									
									if(w==1) {
										noticeCont = eelt;
									}
									if(w==0) {
										
										noticeNum =eelt;
									}
									else if(w==2)
									{
										noticeName =eelt;
									}
									else if(w==3) {
										noticeDate = eelt;
									}
									else if(w==4) {
										noticeSeeview =eelt;
									}	
								}
								if(noticeNum.equals("공지")) {
								noticeNum ="0";
						}	
						else {
							int noticenum = Integer.parseInt(noticeNum);	

							//String sql = "insert NOTICE values('"+noticeNum+"','"+noticeCont+"','"+noticeName+"','"+noticeDate+"','"+noticeSeeview+"','"+noticehref+"');";
							//String sql = "update  NOTICE set noticeContent = '"+noticeCont+"', noticeName ='"+noticeName+"',noticeDate='"+noticeDate+"',noticeSeeview='"+noticeSeeview+"',noticeHref='"+noticehref+"' where noticeNum ='"+noticeNum+"');";
							String sql = "insert NOTICE  values('"+noticenum+"','"+noticeCont+"','"+noticeName+"','"+noticeDate+"','"+noticeSeeview+"','"+noticeHref+"') ON DUPLICATE KEY UPDATE noticeSeeview='"+ noticeSeeview+"';";
							
							stmt.executeUpdate(sql);
							System.out.println(noticenum + noticeCont+ noticeName+ noticeDate+noticeSeeview);	
						}
			
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
		Thread.sleep(3600000);
		}
	}
}