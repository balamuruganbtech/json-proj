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
import com.app.dto.ComputedFields;
import com.app.dto.FiledsPersister;
import com.app.dto.frame.DataStructureDataFrame;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * @author balamurugan.durairajan - z024029
 * 
 * @since - 2020-08-27
 */
public class DataStructureJsonUtil {

	static void setDefaultKeyValues(List<ComputedFields> computedFieldsList) {
		ComputedFields cf1 = new ComputedFields();
		cf1.setName("t_ingestion_time");
		cf1.setFieldType("timestamp");
		cf1.setSqlCommand("to_fill_command");
		cf1.setComment("to_fill_comment");

		ComputedFields cf2 = new ComputedFields();
		cf2.setName("t_loading_date");
		cf2.setFieldType("date");
		cf2.setSqlCommand("to_fill_command");
		cf2.setComment("to_fill_comment");

		ComputedFields cf3 = new ComputedFields();
		cf3.setName("t_loading_year");
		cf3.setFieldType("string");
		cf3.setSqlCommand("to_fill_command");
		cf3.setComment("to_fill_comment");

		ComputedFields cf4 = new ComputedFields();
		cf4.setName("dt_extract");
		cf4.setFieldType("date");
		cf4.setSqlCommand("to_fill_command");
		cf4.setComment("to_fill_comment");

		ComputedFields cf5 = new ComputedFields();
		cf5.setName("t_flag_last_date");
		cf5.setFieldType("string");
		cf5.setSqlCommand("to_fill_command");
		cf5.setComment("to_fill_comment");

		ComputedFields cf6 = new ComputedFields();
		cf6.setName("FILE_NAME");
		cf6.setFieldType("string");
		cf6.setSqlCommand("to_fill_command");
		cf6.setComment("DLL_FILE_NAME");

		computedFieldsList.add(cf1);
		computedFieldsList.add(cf2);
		computedFieldsList.add(cf3);
		computedFieldsList.add(cf4);
		computedFieldsList.add(cf5);
		computedFieldsList.add(cf6);
	}

	void read(String fileName, String dirFileName) {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(dirFileName));

			// A JSON object. Key value pairs are unordered. JSONObject supports
			// java.util.Map interface.
			JSONObject jsonObject = (JSONObject) obj;

			// A JSON array. JSONObject supports java.util.List interface.
			JSONObject obj1 = (JSONObject) jsonObject.get("fields");
			JSONArray fixedList = (JSONArray) obj1.get("fixed-width-fields");

			//
			// A JSON array. JSONObject supports java.util.List interface.
			JSONObject obj2 = (JSONObject) jsonObject.get("meta-data");
			JSONArray withCols = (JSONArray) obj2.get("with-columns");

			Iterator<JSONObject> iterator = fixedList.iterator();
			Iterator<JSONObject> iterator2 = withCols.iterator();

			DataStructureDataFrame writeobj = new DataStructureDataFrame();
			writeobj.setType("basic");

			List<FiledsPersister> fieldsList = new ArrayList<>();
			List<ComputedFields> computedFieldsList = new ArrayList<>();

			while (iterator.hasNext()) {
				JSONObject data = iterator.next();

				FiledsPersister fp = new FiledsPersister();
				fp.setName(data.get("name").toString());
				fp.setFieldType(data.get("type").toString());
				fp.setComment("to_fill");

				fieldsList.add(fp);
			}
			
			String sql = "";
			while (iterator2.hasNext()) {
				JSONObject data = iterator2.next();
				sql = data.get("name").toString();

				ComputedFields cf = new ComputedFields();
				cf.setFieldType(data.get("type").toString());
				cf.setSqlCommand(sql);
				cf.setName(sql.substring(sql.lastIndexOf(" ") + 1));

				computedFieldsList.add(cf);
				// System.out.println("Sql = "+sql);
				// System.out.println("Sql last index= "+sql.lastIndexOf(" "));
				// System.out.println("Lastword= "+sql.substring(sql.lastIndexOf(" ")+1));
				// System.out.println(data.get("type").toString()+"
				// "+data.get("name").toString());
			}
			writeobj.setFields(fieldsList);
			
			// set default values
			setDefaultKeyValues(computedFieldsList);
			
			writeobj.setComputedFields(computedFieldsList);
			write(AppConstants.SRC_DATA+fileName+"\\datastructure.json", writeobj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void write(String outName, DataStructureDataFrame dataStructureObj) {
		// Writing to a file
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dataStructureObj);
			Files.write(Paths.get(outName), jsonString.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doTask(Map<String, String> map) throws IOException, ParseException {
		map.forEach((k, v) -> {
			read(k, v);
		});
		System.out.println("DataStructure File Written Done");
	}

}
