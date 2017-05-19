package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



public class Command {
	public static List<String> comm(String command){
		BufferedReader br=null;
		List<String> list=new ArrayList<String>();
		
		try {
			Process p=Runtime.getRuntime().exec(command);
			br=new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			String line;
			while((line=br.readLine())!=null){
				list.add(line);
			}
			
//			String line;
//			StringBuilder sb=new StringBuilder();
//			while((line=br.readLine())!=null){
//				sb.append(line+"\n");
//				
//			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	public static void main(String[] args) {
		List<String> line=comm("net user");
		for(String s:line){
			System.out.println(s);
		}
		//System.out.println(line);
	
	}




}
