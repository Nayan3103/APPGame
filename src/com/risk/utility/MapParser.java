package com.risk.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.risk.model.Country;
import com.risk.model.Edge;
import com.risk.model.LandFactory;
import com.risk.model.Land;
import com.risk.model.Map;

/**
 * This class parses the input map files and creates the map it also generate a
 * map file from map object
 * 
 * @author Raghav
 * @version 1.0
 */

public class MapParser {
	
	private static Map map;
	public MapParser() {
	}

	public static boolean BMapValidator(String input) throws Exception {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(input));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			StringBuilder sb = new StringBuilder();
			String sLine = br.readLine();
			String sHeader = "";
			ArrayList arrayMap = new ArrayList();
			ArrayList arrayContinents = new ArrayList();
			ArrayList arrayCountries = new ArrayList();
			boolean bExistsMap = false;
			boolean bExistsContinents = false;
			boolean bExistsTerritories = false;
			while (sLine != null) {
				sb.append(sLine);
				sb.append(System.lineSeparator());
				//validates the map
				if (sLine.startsWith("[Map]")) {
					sHeader = "Map";
					bExistsMap = true;
				} else if (sLine.startsWith("[Continents]")) {
					sHeader = "Continents";
					bExistsContinents = true;
				} else if (sLine.startsWith("[Territories]")) {
					sHeader = "Territories";
					bExistsTerritories = true;
				} else if (!sLine.isEmpty()) {
					switch (sHeader) {
					case "Map":
						arrayMap.add(sLine);
						break;
					case "Continents":
						arrayContinents.add(sLine);
						break;
					case "Territories":
						arrayCountries.add(sLine);
						break;
					}
				}
				sLine = br.readLine();
			}
			br.close();
			// validate headers: [Map],[Continents],[Territories]
			if (bExistsMap == false || bExistsContinents == false || bExistsTerritories == false) {
				throw new Exception("Header validator failed");

			}
			// continents validation, fails if it doesnt contain any continent
			if (arrayContinents.size() < 1) {
				throw new Exception("Map contains no continent!");
			}
			// countries validation fails if it doesnt contain less then 5 countries
			if (arrayCountries.size() < 2) {
				throw new Exception("Map contains less than 5 countries!");
			}

		} catch (Exception ex) {

			throw ex;

		}
		return true;
	}

	/**
	 * 
	 * @param param_input which is the name of the map file
	 * @return is boolean which is true if it passes validation otherwise flase
	 * @throws Exception
	 * if the file map does not exist
	 */
	//map header validation
	public static boolean MapValidator_Header(String param_input) throws Exception {
		boolean isValid = false;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(param_input));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return isValid;
		}
		StringBuilder sb = new StringBuilder();
		String sLine = br.readLine();
		String sHeader = "";
		ArrayList arrayMap = new ArrayList();
		ArrayList arrContinents = new ArrayList();
		ArrayList arrCountries = new ArrayList();
		boolean existsMap = false;
		boolean existsContinents = false;
		boolean existsTerritories = false;
		while (sLine != null) {
			sb.append(sLine);
			sb.append(System.lineSeparator());
			if (sLine.startsWith("[Map]")) {
				sHeader = "Map";
				existsMap = true;
			} else if (sLine.startsWith("[Continents]")) {
				sHeader = "Continents";
				existsContinents = true;
			} else if (sLine.startsWith("[Territories]")) {
				sHeader = "Territories";
				existsTerritories = true;
			} else if (!sLine.isEmpty()) {
				switch (sHeader) {
				case "Map":
					arrayMap.add(sLine);
					break;
				case "Continents":
					arrContinents.add(sLine);
					break;
				case "Territories":
					arrCountries.add(sLine);
					break;
				}
			}
			sLine = br.readLine();
		}
		br.close();
		// validate headers
		if (existsMap == true && existsContinents == true && existsTerritories == true) {
			isValid = true;

		}

		return isValid;
	}

	/**
	 * 
	 * @param prm_input
	 *            is the name of the map file
	 * @return is boolean which is true if the validation passes otherwise false
	 * @throws Exception
	 *             if the map file does not exist
	 */
	//validation of map with min continents
	
	public static boolean MapValidatorMinimumContinents(String prm_input) throws Exception {
		boolean isValid = false;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(prm_input));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return isValid;
		}
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();
		String header = "";
		ArrayList arrayMap = new ArrayList();
		ArrayList arrayContinents = new ArrayList();
		ArrayList arrayCountries = new ArrayList();
		boolean existsMap = false;
		boolean existsContinents = false;
		boolean existsTerritories = false;
		while (line != null) {
			sb.append(line);
			sb.append(System.lineSeparator());
			if (line.startsWith("[Map]")) {
				header = "Map";
				existsMap = true;
			} else if (line.startsWith("[Continents]")) {
				header = "Continents";
				existsContinents = true;
			} else if (line.startsWith("[Territories]")) {
				header = "Territories";
				existsTerritories = true;
			} else if (!line.isEmpty()) {
				switch (header) {
				case "Map":
					arrayMap.add(line);
					break;
				case "Continents":
					arrayContinents.add(line);
					break;
				case "Territories":
					arrayCountries.add(line);
					break;
				}
			}
			line = br.readLine();
		}
		br.close();
		// continents validation fails if there is no continent
		if (arrayContinents.size() >= 1) {
			isValid = true;
			;
		}

		return isValid;
	}

	/**
	 * 
	 * @param input
	 *            is the name of the map file
	 * @return is boolean which is true if the validation passes otherwise false
	 * @throws Exception
	 *             if the map file does not exist
	 */
	public static boolean MapValidatorMinimumCountries(String input) throws Exception {
		boolean isValid = false;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(input));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return isValid;
		}
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();
		String header = "";
		ArrayList arrayMap = new ArrayList();
		ArrayList arrayContinents = new ArrayList();
		ArrayList arrayCountries = new ArrayList();
		boolean existsMap = false;
		boolean existsContinents = false;
		boolean existsTerritories = false;
		while (line != null) {
			sb.append(line);
			sb.append(System.lineSeparator());
			if (line.startsWith("[Map]")) {
				header = "Map";
				existsMap = true;
			} else if (line.startsWith("[Continents]")) {
				header = "Continents";
				existsContinents = true;
			} else if (line.startsWith("[Territories]")) {
				header = "Territories";
				existsTerritories = true;
			} else if (!line.isEmpty()) {
				switch (header) {
				case "Map":
					arrayMap.add(line);
					break;
				case "Continents":
					arrayContinents.add(line);
					break;
				case "Territories":
					arrayCountries.add(line);
					break;
				}
			}
			line = br.readLine();
		}
		br.close();
		// countries validation fails if there are less than 5 countries
		if (arrayCountries.size() >= 5) {
			isValid = true;
		}

		return isValid;
	}

	/**
	 * this method parse a map file and returns a map object
	 * 
	 * @param input
	 *            is a file
	 * @return a map object contains continents and countries
	 * @throws Exception
	 *             if the map file does not exist
	 */
	public static Map MapParser(String input) throws Exception {
		map = new Map("map1");
		BufferedReader br = null;
		try {
			// validates the map before creating
			if (!BMapValidator(input))
				throw new Exception("NotValidMapFile");
			br = new BufferedReader(new FileReader(input));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			String header = "";
			ArrayList arrMap = new ArrayList();
			ArrayList arrContinents = new ArrayList();
			ArrayList arrCountries = new ArrayList();
			// reading the map file line by line
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				if (line.startsWith("[Map]")) {
					header = "Map";
				} else if (line.startsWith("[Continents]")) {
					header = "Continents";
				} else if (line.startsWith("[Territories]")) {
					header = "Territories";
				} else if (!line.isEmpty()) {
					switch (header) {
					case "Map":
						arrMap.add(line);
						break;
					case "Continents":
						arrContinents.add(line);
						break;
					case "Territories":
						arrCountries.add(line);
						break;
					}
				}
				line = br.readLine();
			}
			br.close();
			// building map
			// set map properties
			for (Object o : arrMap) {
				ParseMap((String) o);
			}
			int continentCount = 1;
			for (Object o : arrContinents) {
				map.listLands.add(ParseContinents((String) o));
				continentCount++;
			}
			// building countries without neighbours country
			int countryCount = 1;
			for (Object o : arrCountries) {
				map.listLands.add(CountriesParse((String) o));
				countryCount++;
			}
			// add edges and set neighbours
			String[] strLines;
			for (Object o : arrCountries) {
				strLines = ((String) o).split(",");
				if (strLines.length > 4) {
					for (int i = 4; i < strLines.length; i++) {
						map.AddEdge(new Edge(map.GetCountryIdByName(strLines[0].trim()), map.GetCountryIdByName(strLines[i].trim())));
						map.GetCountryById(map.GetCountryIdByName(strLines[0].trim()))
								.AddNeighbor(map.GetCountryById(map.GetCountryIdByName(strLines[i].trim())));
					}
				}

			}
			if (!map.ValidationMapConnectivity()) {
				throw new Exception("The map is not connected");
			}
			if (!map.ValidateContinentsConnectivity()) {
				throw new Exception("The continents are not connected");
			}
			return map;
		} finally {

		}
	}

	/**
	 * this method extract continent from a line of map file
	 * 
	 * @param line
	 *            a line of map file
	 * @return a continent object
	 */
	public static Land ParseContinents(String line) {
		if (!line.isEmpty()) {
			String[] lines = line.split("=");
			String strName = lines[0];
			int control = Integer.parseInt(lines[1].trim());
			return LandFactory.GetLand("Continent", strName.trim(), -1, -1, -1, control);
		}
		return null;
	}

	/**
	 * this method parses the Map header
	 * 
	 * @param line
	 *            is a line of map file
	 */
	public static void ParseMap(String line) {
		if (!line.isEmpty()) {
			String[] lines = line.split("=");
			String strName = lines[0];
			String strValue = lines[1];
			switch (strName.toLowerCase()) {
			case "author":
				map.SetAuthor(strValue);
				break;
			case "image":
				map.SetImage(strValue);
				break;
			}
		}

	}

	/**
	 * @param line
	 *            is the line of a map
	 * @return is a land
	 */
	public static Land CountriesParse(String line) {
		if (!line.isEmpty()) {
			String[] lines = line.split(",");
			String strName = lines[0].trim();
			int x = Integer.parseInt(lines[1]);
			int y = Integer.parseInt(lines[2]);
			String strContinent = lines[3].trim();
			return LandFactory.GetLand("Country", strName.trim(), map.GetContinentIdByName(strContinent), x, y, -1);
		} else
			return null;
	}

	/**
	 * this method converts a map object to a map file
	 * 
	 * @param map
	 *            is the map to be converted
	 * @param file
	 *            is the output file
	 * @throws IOException
	 *             if the output file has issue to be written
	 */
	public static void WriteMapObjToFile(Map map, String file) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(file));
		for (String line : map.MapToLines()) {
			pw.println(line);
		}
		pw.close();
	}
}
