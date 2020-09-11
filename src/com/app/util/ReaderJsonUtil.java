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
import com.app.dto.Fields;
import com.app.dto.frame.ReaderDataFrame;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author balamurugan.durairajan - z024029
 * 
 * @since - 2020-08-27
 */
public class ReaderJsonUtil {

	static void read(String fileName, String dirFileName) {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(dirFileName));

			// A JSON object. Key value pairs are unordered. JSONObject supports
			// java.util.Map interface.
			JSONObject jsonObject = (JSONObject) obj;

			// A JSON array. JSONObject supports java.util.List interface.
			JSONObject obj1 = (JSONObject) jsonObject.get("fields");
			JSONArray companyList = (JSONArray) obj1.get("fixed-width-fields");

			Iterator<JSONObject> iterator = companyList.iterator();

			ReaderDataFrame writeobj = new ReaderDataFrame();
			writeobj.setType("fixed");
			writeobj.setPath("");
			writeobj.setHasHeader(false);
			writeobj.setLoadOption("each");

			List<Fields> fieldList = new ArrayList<>();

			while (iterator.hasNext()) {
				JSONObject data = iterator.next();

				Fields rdf = new Fields();
				rdf.setName(data.get("name").toString());
				rdf.setStart((long) data.get("position"));
				rdf.setLength((long) data.get("width"));
				fieldList.add(rdf);
			}
			writeobj.setFields(fieldList);
			write(AppConstants.SRC_DATA+fileName+"\\reader.json", writeobj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void write(String outName, ReaderDataFrame readerObj) {
		// Writing to a file
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			Files.write(Paths.get(outName), mapper.writerWithDefaultPrettyPrinter().writeValueAsString(readerObj).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doTask(Map<String, String> map) throws IOException, ParseException {
		map.forEach((k, v) -> {
			read(k, v);
		});
		System.out.println("Reader File Written Done");
	}

}
