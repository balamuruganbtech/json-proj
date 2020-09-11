package com.app.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.app.constants.AppConstants;
import com.app.util.DataStructureJsonUtil;
import com.app.util.PersisterJsonUtil;
import com.app.util.ReaderJsonUtil;

/**
 * @author balamurugan.durairajan - z024029
 * @since - 2020-08-27
 */
public class AppLaunch {

	public static void main(String[] args) throws IOException, ParseException {

		Map<String, String> map = new LinkedHashMap<>();

		List<String> sourceList = Files.list(Paths.get(AppConstants.SRC_DATA)).filter(Files::isRegularFile)
				.map(x -> x.toString()).collect(Collectors.toList());

		sourceList.forEach(x -> {
			String dir = x.substring(x.lastIndexOf("\\") + 1).replaceAll("KBP.json", "").trim().toLowerCase();
			map.put(dir, x);
			try {
				Files.createDirectories(Paths.get(AppConstants.SRC_DATA + dir));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		map.forEach((k, v) -> {
			System.out.println(k + "  " + v);
		});

		ReaderJsonUtil reader = new ReaderJsonUtil();
		PersisterJsonUtil persister = new PersisterJsonUtil();
		DataStructureJsonUtil dataStructure = new DataStructureJsonUtil();

		reader.doTask(map);
		persister.doTask(map);
		dataStructure.doTask(map);

	}

}
