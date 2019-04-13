package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Test2 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(
				new FileReader(new File("//Users//pbarri//Downloads//credit_card57066ea.csv")));
		String[] s = br.readLine().split(",");
		String c = "Create Table CreditRecord(";
		for (String x : s) {
			c += x + " VARCHAR(32) ,\n";
		}
		c += ")";
		br.close();
		System.out.println(c);
	}

}
