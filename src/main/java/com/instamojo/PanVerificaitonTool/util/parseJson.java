package com.instamojo.PanVerificaitonTool.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.instamojo.PanVerificationTool.entities.Pan;

public class parseJson {
	public static Pan parsonJson(String jsonString) {
		Pan newPanData=new Pan();
		Gson gson=new GsonBuilder().setPrettyPrinting().create();
		newPanData=gson.fromJson(jsonString,Pan.class);
		return newPanData;
	}
	
	public static void main(String args[]) {
		String jsonData="{\n" + 
				"	\"textAngle\": 0.0,\n" + 
				"	\"orientation\": \"NotDetected\",\n"+
				"\"language\": \"en\"}"
				;
		System.out.println(parseJson.parsonJson(jsonData).getLanguage());
	}

}
