package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Test3 {
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(
				new FileReader(new File("//Users//pbarri//Downloads//credit_card57066ea.csv")));
		String s = br.readLine();
		Class.forName("com.mysql.cj.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/Prakash","root","Cisco123!");
		PreparedStatement ps1 = con.prepareStatement("insert into credit_record values(?,?,?,?,?,?,?,?)");
		PreparedStatement ps2 = con.prepareStatement("insert into Credit_Pay_Record values(?,?,?,?,?,?,?)");
		PreparedStatement ps3 = con.prepareStatement("insert into Credit_Bill_Record values(?,?,?,?,?,?,?)");
		PreparedStatement ps4 = con.prepareStatement("insert into Credit_PayMent_Record values(?,?,?,?,?,?,?)");
		s = br.readLine();
		while(s!=null)
		{
			String[] strs = s.split(",");
			ps1.setString(1, strs[0]);
			ps1.setString(2, strs[1]);
			ps1.setString(3, strs[2]);
			ps1.setString(4, strs[3]);
			ps1.setString(5, strs[4]);
			ps1.setString(6, strs[5]);
			ps1.setString(7, strs[6]);
			ps1.setString(8, strs[25]);
			ps2.setString(1, strs[0]);
			ps2.setString(2, strs[7]);
			ps2.setString(3, strs[8]);
			ps2.setString(4, strs[9]);
			ps2.setString(5, strs[10]);
			ps2.setString(6, strs[11]);
			ps2.setString(7, strs[12]);
			ps3.setString(1, strs[0]);
			ps3.setString(2, strs[13]);
			ps3.setString(3, strs[14]);
			ps3.setString(4, strs[15]);
			ps3.setString(5, strs[16]);
			ps3.setString(6, strs[17]);
			ps3.setString(7, strs[18]);
			ps4.setString(1, strs[0]);
			ps4.setString(2, strs[19]);
			ps4.setString(3, strs[20]);
			ps4.setString(4, strs[21]);
			ps4.setString(5, strs[22]);
			ps4.setString(6, strs[23]);
			ps4.setString(7, strs[24]);
			ps1.executeUpdate();
			ps2.executeUpdate();
			ps3.executeUpdate();
			ps4.executeUpdate();
			s = br.readLine();
		}
		con.close();
		br.close();
	}

}
