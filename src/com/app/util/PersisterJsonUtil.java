package com.app.util;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.app.constants.AppConstants;
import com.app.dto.FiledsPersister;
import com.app.dto.Structures;
import com.app.dto.frame.PersisterDataFrame;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author balamurugan.durairajan - z024029
 * 
 * @since - 2020-08-27
 */
public class PersisterJsonUtil {
	
	static void setDefaultKeyValues(List<FiledsPersister> fieldsList) {
		FiledsPersister fp1 = new FiledsPersister();
		fp1.setName("file_name");
		fp1.setFieldType("string");
		fp1.setComment("Ingested file");
		
		FiledsPersister fp2 = new FiledsPersister();
		fp2.setName("t_ingestion_time");
		fp2.setFieldType("timestamp");
		fp2.setComment("Creation Date");
		
		FiledsPersister fp3 = new FiledsPersister();
		fp3.setName("t_loading_date");
		fp3.setFieldType("date");
		fp3.setComment("Loading date");
		
		FiledsPersister fp4 = new FiledsPersister();
		fp4.setName("t_loading_year");
		fp4.setFieldType("string");
		fp4.setComment("Loading year");
		
		FiledsPersister fp5 = new FiledsPersister();
		fp5.setName("dt_extract");
		fp5.setFieldType("timestamp");
		fp5.setComment("File arrival date");
		
		FiledsPersister fp6 = new FiledsPersister();
		fp6.setName("t_flag_last_date");
		fp6.setFieldType("string");
		fp6.setComment("t_flag_last_date");
		
		fieldsList.add(fp1);
		fieldsList.add(fp2);
		fieldsList.add(fp3);
		fieldsList.add(fp4);
		fieldsList.add(fp5);
		fieldsList.add(fp6);
	}
	

	static void read(String fileName, String dirFileName) {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(dirFileName));

			// A JSON object. Key value pairs are unordered. JSONObject supports
			// java.util.Map interface.
			JSONObject jsonObject = (JSONObject) obj;

			// A JSON array. JSONObject supports java.util.List interface.
			JSONObject obj1 = (JSONObject) jsonObject.get("fields");
			JSONArray fixedList = (JSONArray) obj1.get("fixed-width-fields");

			Iterator<JSONObject> iterator = fixedList.iterator();

			PersisterDataFrame writeobj = new PersisterDataFrame();
			writeobj.setType("orc");	
			
			List<Structures> structureList = new ArrayList<>();
			List<FiledsPersister> fieldsList = new ArrayList<>();
			List<Structures> strucList = new ArrayList<>();
			
			Structures  struct = new Structures();
			struct.setName("");
			struct.setDbName("");
			struct.setTablename("");
			struct.setMergeType("");
			struct.setOutputPath("");
			struct.setPersistHive(false);
			
			while (iterator.hasNext()) {
				JSONObject data = iterator.next();
				
				FiledsPersister fp = new FiledsPersister();
				fp.setName(data.get("name").toString());
				fp.setFieldType(data.get("type").toString());
				fp.setComment("to_fill");
				
				fieldsList.add(fp);
			}
			
			// adding default key & values 
			setDefaultKeyValues(fieldsList);
			
			struct.setFields(fieldsList);
			strucList.add(struct);
			
			writeobj.setStructures(strucList);
			
			write(AppConstants.SRC_DATA+fileName+"\\persister.json", writeobj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void write(String outName, PersisterDataFrame persistObj) {
		// Writing to a file
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			Files.write(Paths.get(outName), mapper.writerWithDefaultPrettyPrinter().writeValueAsString(persistObj).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doTask(Map<String, String> map) throws IOException, ParseException {
		map.forEach((k, v) -> {
			read(k, v);
		});
		System.out.println("Persister File Written Done");
	}

}
