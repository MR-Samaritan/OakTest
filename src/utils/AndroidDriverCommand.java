package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AndroidDriverCommand {
	public List<String> comm(String command) {
		BufferedReader br = null;
		List<String> list = new ArrayList<String>();

		try {
			Process p = Runtime.getRuntime().exec(command);
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line;
			while ((line = br.readLine()) != null) {
				list.add(line);
			}

			// String line;
			// StringBuilder sb=new StringBuilder();
			// while((line=br.readLine())!=null){
			// sb.append(line+"\n");
			//
			// }
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;

	}

	public void commNoReturn(String command) {
		try {
			Process p = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		AndroidDriverCommand command = new AndroidDriverCommand();
//		List<String> list = command.comm("adb shell wm size");
//		String regEx = "[0-9]\\d*";
//		StringBuilder sb=new StringBuilder();
//		Pattern pattern = Pattern.compile(regEx);
//		for (String s : list) {
//			Matcher matcher = pattern.matcher(s);
//			while (matcher.find()) {
//				
//				sb.append(matcher.group());
//			}
//		}
//		
//		System.out.println(sb);
//
//	}
}
