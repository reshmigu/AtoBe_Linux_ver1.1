package com.test;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONReadExample{
	
	public static void main(String[] args) throws Exception  
    { 
       
        Object obj = new JSONParser().parse(new FileReader(".\\sample.json")); 
          
        JSONObject jo = (JSONObject) obj; 
          
        Long code = (Long) jo.get("code"); 
       
        
        System.out.println("code --.-------------" +code);
        String data = (String) jo.get("data").toString().replaceAll("\"", ""); 
          
        System.out.println("data ---------------" +data);
        
        JSONObject dataee = (JSONObject) jo.get("data");
        System.out.println("dataee ---------------" +dataee);
        
        
        JSONArray brands = (JSONArray) dataee.get("brands");
        
        System.out.println("size ---------------" +brands.size());
        
        for(int j=0; j<brands.size(); j++)
        {
        
        	JSONObject cc = (JSONObject) brands.get(j);
        	
        
            System.out.println("id ---------------" + cc.get("id"));
            
          
            
            
        }
	
}
}
