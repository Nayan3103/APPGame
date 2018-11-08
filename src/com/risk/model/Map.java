package com.risk.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Iterator;
import java.util.Random;

import com.risk.utility.staticApplicationVariables;

/**
 * This is a high level class which contains continents and countries it manages.
 * it has several properties such as name, lands.
 * they are the collection of both countries and continents as well
 * as connectivities which in this application is known as edge
 * 
 * @author Raghav
 *
 */

public class Map extends Observable {
	private String name,sAuthor,simage;
	
	public List<Land> listLands;
	public List<Edge> listEdges;
	public List<Country> listCountries;
	public List<Continent> listContinents;
	
	//standard json constructor
	public Map(){
		name="map1";
		sAuthor="author1";
		simage="";
		listLands = new ArrayList<Land>();
		listEdges = new ArrayList<Edge>();
	}
	/**
	 * this method set author of the object
	 * 
	 * @param prm_author,
	 *            which its type is string, will be the name of the author of
	 *            the map
	 */
	public void SetAuthor(String param_author) {
		this.sAuthor = param_author;
	}
	
	/**
	 * this constructor of the class takes the name of the map
	 * 
	 * @param param_name
	 *  is the name of the map
	 */
	public Map(String param_name) {
		this.name = param_name;
		listLands = new ArrayList<Land>();
		listEdges = new ArrayList<Edge>();
	}

	
	/**
	 * this method returns the author of the map
	 * 
	 * @return, which its type is string, is the author of the map
	 */
	public String GetAuthor() {
		return this.sAuthor;
	}

	/**
	 * this method set an image to the object
	 * 
	 * @param prm_image,
	 *            will be the name of the image related to the object
	 */
	public void SetImage(String param_image) {
		this.simage = param_image;
	}

	

	/**
	 * this method returns the image name of the object
	 * 
	 * @return name of the image
	 */
	public String GetImage() {
		return this.simage;
	}

	/**
	 * this method return a country or continent by its name
	 * 
	 * @param prm_name,
	 *            which is the name of the country or continent
	 * @return a country or a continent
	 */
	public Land GetLandByName(String param_name) {
		String stempName = "";
		for (Land land : listLands) {
			if (param_name.equals(land.GetName())) {
				return land;
			}
		}
		return null;

	}

	/**
	 * this method return a string
	 * 
	 * @param id,
	 *            which is the name of the country or continent
	 * @return a country or a continent
	 */
	public String[] getCountryListinStringForCombobox(int id) {
		List<Country> listCountry = staticApplicationVariables.gb.map.GetCountriesByContinentId(id);
		String[] stringCountry = new String[listCountry.size()];

		for (int j = 0; j < listCountry.size(); j++) {
			stringCountry[j] = listCountry.get(j).GetName();
		}
		return stringCountry;
	}

	/**
	 * this method remove a continent
	 * 
	 * @param param_continent
	 *            is the continent to be removed
	 * @return a string which is successful if it is removed and if it does not
	 *         exist, notExist will be returned
	 */
	public String RemoveContinent(Continent param_continent) {
		if (DoesExistContinent(param_continent.GetId())) {
			for (Country c : this.GetCountries()) {
				if (c.GetContinentId() == param_continent.GetContinentId()) {
					this.RemoveCountry(c);
				}
			}
			this.listLands.remove(param_continent);
			return "successful";
		} else {
			return "notExist";
		}

	}

	/**
	 * this method verifies if a given country exists
	 * 
	 * @param param_name
	 *            is the name of the country under question
	 * @return a boolean which is true if exists otherwise false
	 */
	public boolean DoesExistCountry(String param_name) {
		boolean result = false;
		for (Country cnt : this.GetCountries()) {
			if (cnt.GetName().equals(param_name)) {
				return true;
			}
		}
		return result;
	}

	/**
	 * this method verifies if a given continent exists
	 * 
	 * @param param_continentId
	 *            is the name of the continent under question
	 * @return a boolean which is true if exists otherwise false
	 */
	public boolean DoesExistContinent(int param_continentId) {
		boolean result = false;
		for (Continent cnt : this.GetContinents()) {
			if (cnt.GetId() == param_continentId) {
				return true;
			}
		}
		return result;
	}

	/**
	 * this method adds an edge, before adding it checks if it exists or not
	 * 
	 * @param param_edge
	 *            which is the edge to be added
	 * @return which is 1 if the edge was added otherwise -1
	 */
	public int AddEdge(Edge param_edge) {
		int result = -1;
		if (!DoesExistEdge(param_edge)) {
			this.listEdges.add(param_edge);
			result = 1;
		}
		return result;
	}

	/**
	 * this method adds a country, before adding verifies if already exists
	 * 
	 * @param param_name
	 *            is the name of the country
	 * @param param_continentId
	 *            is the id of the continent to which the country belongs
	 * @param param_x
	 *            is x coordinate of the country
	 * @param param_y
	 *            is y coordinate of the country
	 * @return a successfull message
	 */
	public String AddCountry(String param_name, int param_continentId, int param_x, int param_y) {
		if (!DoesExistCountry(param_name)) {
			this.listLands.add(LandFactory.GetLand("Country", param_name, param_continentId, param_x, param_y, -1));
			Country sCountry= GetCountryByName(param_name);
			Country dDountry = null;
			if(GetCountries().size()>1){
				dDountry=GetCountries().get(GetCountries().size()-2);
			}
			if(sCountry!=null && dDountry!=null )
			AddEdge(sCountry.id,dDountry.id);
			return "successful";
		} else {
			return "duplicate";
		}

	}

	/**
	 * this method verifies if a given edge exist
	 * 
	 * @param param_edge
	 *            is the edge under question
	 * @return is boolean which is true if the edge exists otherwise false
	 */
	public boolean DoesExistEdge(Edge param_edge) {
		boolean result = false;
		for (Edge e : this.listEdges) {
			if (e.compareTo(param_edge) == 0) {
				return true;

			}

		}
		return result;
	}

	/**
	 * this method verifies if the edge exists by the id of the countries
	 * 
	 * @param param_countryId1
	 *            which is the id of the first country of the edge under
	 *            question
	 * @param param_countryId2
	 *            which is the id of the second country
	 * @return true if the edge exists otherwise false
	 */
	public boolean DoesExistEdge(int param_countryId1, int param_countryId2) {
		boolean result = false;
		for (Edge e : this.listEdges) {
			if ((e.GetCountryId1() == param_countryId1 && e.GetCountryId2() == param_countryId2)
					|| (e.GetCountryId1() == param_countryId2 && e.GetCountryId2() == param_countryId1)) {
				return true;
			}
		}
		return result;
	}

	/**
	 * this methos adds an edge, it verifies the existence before adding
	 * 
	 * @param param_countryId1
	 *            is the id of the first country in the edge
	 * @param param_countryId2
	 *            is the id of the second country in the edge
	 * @return is string and is successful if the edge was added otherwise is
	 *         duplicate
	 */
	public String AddEdge(int param_countryId1, int param_countryId2) {
		if (!DoesExistEdge(param_countryId1, param_countryId2)) {
			this.listEdges.add(new Edge(param_countryId1, param_countryId2));
			return "successful";
		} else {
			return "duplicate";
		}

	}

	/**
	 * this method verifies of a given continent exists
	 * 
	 * @param param_name
	 *            is the name of the continent under question
	 * @return true if the continent exists otherwise is false
	 */
	public boolean DoesExistContinent(String param_name) {
		boolean result = false;
		for (Continent cnt : this.GetContinents()) {
			if (cnt.GetName().equals(param_name)) {
				return true;
			}
		}
		return result;
	}

	/**
	 * this method returns a country which does not belong to a player randomly
	 * 
	 * @return is an unassigned country randomly
	 */
	public Country GetNotAssignedCountryRandom() {
		List<Country> listCountries = GetCountriesNotAssigned();
		Country randCountry = listCountries.get(new Random().nextInt(listCountries.size()));
		return randCountry;
	}

	/**
	 * this method adds a continent before adding it verifies if the continent
	 * does exist before
	 * 
	 * @param param_name
	 *            is the name of the continent
	 * @param param_control
	 *            ,which its type is integer, is the control of the continent
	 * @return a successful message
	 */
	public String AddContinent(String param_name, int param_control) {
		if (!DoesExistCountry(param_name)) {
			this.listLands.add(LandFactory.GetLand("Continent", param_name, -1, -1, -1, param_control));
			return "successful";
		} else {
			return "duplicate";
		}

	}

	/**
	 * this method returns a list of countries which are not assigned to a
	 * player
	 * 
	 * @return a list of countries
	 */
	public List<Country> GetCountriesNotAssigned() {
		List<Country> listCountriesNotAssigned = new ArrayList<Country>();
		for (Land c : listLands) {
			if (c instanceof Country) {
				if (((Country) c).GetPlayerId() == -1) {
					listCountriesNotAssigned.add((Country) c);
				}
			}
		}
		return listCountriesNotAssigned;
	}

	/**
	 * this method returns a list of countries belong to a given player
	 * 
	 * @param prm_playerId
	 *            is the id of the given player
	 * @return a list of country belong to the given player
	 */
	public List<Country> GetCountriesByPlayerId(int prm_playerId) {
		List<Country> listCountries = new ArrayList<Country>();
		for (Country c : GetCountries()) {
			if (c.GetPlayerId() == prm_playerId) {
				listCountries.add(c);
			}
		}
		return listCountries;
	}

	/**
	 * this method returns a country by its id
	 * 
	 * @param param_countryId
	 *            is the id of the country
	 * @return is the country which its id is given
	 */
	public Country GetCountryById(int param_countryId) {
		Country country = null;
		for (Country c : GetCountries()) {
			if (c.GetId() == param_countryId)
				return c;
		}
		return country;
	}

	/**
	 * this method returns a continent id by its name
	 * 
	 * @param n_name
	 *            is the name of the continent to be returned
	 * @return the id of the continent
	 */
	public int GetContinentIdByName(String n_name) {
		int id = -1;
		for (Land l : this.listLands) {
			if (l instanceof Continent && l.GetName().equalsIgnoreCase(n_name)) {
				return l.GetId();
			}
		}
		return id;
	}

	/**
	 * this method returns the list of all neighbor countries of a given country
	 * 
	 * @param param_countryId
	 *            is the id of the given country
	 * @return is a list of the neighbor countries
	 */
	public List<Country> GetNeighbors(int param_countryId) {
		List<Country> listNeighbors = new ArrayList<Country>();
		for (Edge e : listEdges) {
			if (e.DoesContainCountry(param_countryId)) {
				listNeighbors.add(GetCountryById(e.GetNeighborId(param_countryId)));
			}
		}
		return listNeighbors;

	}

	/**
	 * this method returns a country id by its name
	 * 
	 * @param param_name
	 *            is the name of the country to be return
	 * @return is the id of the country
	 */
	public int GetCountryIdByName(String param_name) {
		int id = -1;
		for (Land l : this.listLands) {
			if (l instanceof Country && l.GetName().equalsIgnoreCase(param_name)) {
				return l.GetId();
			}
		}
		return id;
	}

	/**
	 * this method returns a country by its name
	 * 
	 * @param param_name
	 *            is the name of the country to be returned
	 * @return is a country which its name is given
	 */
	public Country GetCountryByName(String param_name) {
		for (Land land : this.listLands) {
			if (land instanceof Country && land.GetName().equals(param_name)) {
				return (Country) land;
			}
		}
		return null;
	}

	/**
	 * this method verifies if a country exists
	 * 
	 * @param param_countryId
	 *            is the id of the country under question
	 * @return true if the country exists otherwise false
	 */
	public boolean DoesExistCountry(int param_countryId) {
		boolean result = false;
		for (Country c : this.GetCountries()) {
			if (c.GetId() == param_countryId) {
				return true;
			}
		}
		return result;
	}

	/**
	 * this method verifies if a continent exists
	 * 
	 * @param param_continent
	 *            is the country under question
	 * @return true if the country exists otherwise false
	 */
	public boolean DoesExistContinent(Continent param_continent) {
		boolean result = false;
		for (Continent cnt : this.GetContinents()) {
			if (cnt instanceof Continent && cnt.GetName().equals(param_continent.GetName())) {
				return true;
			}
		}
		return result;
	}

	/**
	 * this method returns a continent by its name
	 * 
	 * @param param_name
	 *            is the name of the continent under question
	 * @return the continent which its name is given
	 */
	public Continent GetContinentByName(String param_name) {

		for (Continent c : this.GetContinents()) {
			if (c instanceof Continent && c.GetName().equals(param_name)) {
				return (Continent) c;
			}
		}
		return null;
	}

	/**
	 * this method removes a given country
	 * 
	 * @param param_country
	 *            is the country to be removed
	 * @return is successful if the country was removed otherwise returns
	 *         duplicate
	 */
	public String RemoveCountry(Country param_country) {
		if (DoesExistCountry(param_country.GetId())) {
			this.RemoveEdge(param_country.GetId());
			this.listLands.remove(param_country);
			return "successful";
		} else {
			return "duplicate";
		}

	}

	/**
	 * this method removes all edges related to a given country
	 * 
	 * @param param_countryId
	 *            is the id of the given country
	 * @return successful if they were removed
	 */
	public String RemoveEdge(int param_countryId) {
		Iterator it = this.listEdges.iterator();
		while (it.hasNext()) {
			Object o = it.next();
			if (((Edge) o).DoesContainCountry(param_countryId)) {
				it.remove();
			}
		}
		return "successful";

	}

	/**
	 * this method returns the name of a continent which its id is given
	 * 
	 * @param param_id
	 *            is the id of the continent
	 * @return the name of the continent
	 */
	public String GetContinentNameById(int param_id) {
		String name = "";
		for (Land l : this.listLands) {
			if (l instanceof Continent && l.GetId() == param_id) {
				return l.GetName();
			}
		}
		return name;
	}

	/**
	 * this method returns the name of a country which its id is given
	 * 
	 * @param param_id
	 *            is the id of the country
	 * @return the name of the country
	 */
	public String GetCountryNameById(int param_id) {
		String name = "";
		for (Land l : this.listLands) {
			if (l instanceof Country && l.GetId() == param_id) {
				return l.GetName();
			}
		}
		return name;
	}

	/**
	 * this method returns all continents which exist
	 * 
	 * @return a list of all continents
	 */
	public List<Continent> GetContinents() {
		List<Continent> listContinents = new ArrayList<Continent>();
		for (Land l : this.listLands) {
			if (l instanceof Continent) {
				listContinents.add((Continent) l);
			}
		}
		return listContinents;
	}

	/**
	 * this method returns all countries which exist
	 * 
	 * @return a list of all countries
	 */
	public List<Country> GetCountries() {
		List<Country> continents = new ArrayList<Country>();
		for (Land l : this.listLands) {
			if (l instanceof Country) {
				continents.add((Country) l);
			}
		}
		return continents;
	}

	/**
	 * this method concatenates the name of all neighbor countries names
	 * 
	 * @param param_country
	 *            is the country under question
	 * @return list of neighbor countries
	 */
	public List<String> GetNeighhboursName(Country param_country) {
		List<String> neighborsName = new ArrayList<String>();
		for (Edge e : this.listEdges) {
			if (e.DoesExistCountry(param_country.GetId())) {
				neighborsName.add(this.GetCountryNameById(e.GetNeighborId(param_country.GetId())));
			}
		}
		return neighborsName;

	}

	/**
	 * this method convert all countries to line of the map file
	 * 
	 * @return a string which contains all countries in map format
	 */
	public List<String> CountriesToLine() {
		List<String> lines = new ArrayList<String>();
		String line;
		for (Country c : this.GetCountries()) {
			line = new String();
			line = c.GetName() + "," + c.GetX() + "," + c.GetY() + "," + this.GetContinentNameById(c.GetContinentId());
			for (String neighborName : this.GetNeighhboursName(c)) {
				line += "," + neighborName;
			}
			lines.add(line);
		}
		return lines;
	}

	/**
	 * this method converts the whole map into a lines of string in map format
	 * 
	 * @return countries converted into lines of maps
	 */
	public List<String> MapToLines() {
		List<String> lines = new ArrayList<String>();
		lines.add("[Map]");
		lines.add("author=" + this.GetAuthor());
		lines.add("image=" + this.GetImage());
		lines.add("wrap=yes");
		lines.add("scroll=yes");
		lines.add("warn=yes");
		lines.add("");
		lines.add("[Continents]");
		for (Continent c : this.GetContinents()) {
			lines.add(c.GetName() + "=" + c.GetContinentId());
		}
		lines.add("");
		lines.add("[Territories]");
		for (String l : CountriesToLine()) {
			lines.add(l);
		}
		return lines;
	}

	/**
	 * returns all countries of a given continent
	 * 
	 * @param param_continentId
	 *            is the id of the continent
	 * @return lis of all countries of the given continent
	 */
	public List<Country> GetCountriesByContinentId(int param_continentId) {
		List<Country> countries = new ArrayList<Country>();
		for (Country c : GetCountries()) {
			if (c.GetContinentId() == param_continentId)
				countries.add(c);
		}
		return countries;
	}

	/**
	 * this method returns the list of the name of countries of a given
	 * continent
	 * 
	 * @param param_continentId
	 *            is the id of continent
	 * @return list of countries in string
	 */
	public String[] GetCountriesByContinentIdInStrings(int param_continentId) {
		List<String> countries = new ArrayList<String>();
		for (Country c : GetCountries()) {
			if (c.GetContinentId() == param_continentId)
				countries.add(c.GetName());
		}

		String[] temp = new String[countries.size()];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = countries.get(i);
			// System.out.println("inside getcountrybycontinentid"+temp[i]);
		}

		return temp;
	}

	/**
	 * this method return the id of the player to which a given country belong
	 * 
	 * @param prm_countryId,
	 *            which is the id of the player
	 * @return prm_playerId, which is the id of the player to which the country
	 *         belongs
	 */
	public int GetPlayerIdByCountryId(int param_countryId) {
		return GetCountryById(param_countryId).GetPlayerId();
	}

	public String[] getCountryListStringForCombobox(int id) {
		List<Country> countryList = staticApplicationVariables.gb.map.GetCountriesByContinentId(id);
		String[] countryString = new String[countryList.size()];

		for (int j = 0; j < countryList.size(); j++) {
			countryString[j] = countryList.get(j).GetName();
		}
		return countryString;
	}

	/**
	 * this method returns the adjacent country of a given country which belong
	 * to the same player
	 * 
	 * @param prm_countryId,
	 *            which is the id of the given country
	 * @return is a list of countries which are adjacent of the given country
	 */
	public List<Country> GetNeighborsByCountryIdSamePlayer(int param_countryId) {
		List<Country> neighbors = new ArrayList<Country>();
		for (Edge e : listEdges) {
			if (e.DoesContainCountry(param_countryId)) {
				if (GetPlayerIdByCountryId(param_countryId) == GetPlayerIdByCountryId(e.GetNeighborId(param_countryId)))
					neighbors.add(GetCountryById(e.GetNeighborId(param_countryId)));
			}
		}
		return neighbors;
	}

	/**
	 * this method returns continent by its id
	 * 
	 * @param param_continentId
	 *            the id of the continent
	 * @return the continent
	 */
	public Continent GetContinentById(int param_continentId) {
		// TODO Auto-generated method stub
		for (Continent c : this.GetContinents()) {
			if (c instanceof Continent && c.GetId() == param_continentId)
				return c;
		}
		return null;
	}

	/**
	 * this method returns the adjacent country of a given country which belong
	 * to the opponent player
	 * 
	 * @param prm_countryId,
	 *            which is the id of the given country
	 * @return is a list of countries which are adjacent of the given country
	 */
	public List<Country> GetNeighborsByCountryIdOpponentPlayer(int param_countryId) {
		List<Country> neighbors = new ArrayList<Country>();
		for (Edge e : listEdges) {
			if (e.DoesContainCountry(param_countryId)) {
				if (GetPlayerIdByCountryId(param_countryId) != GetPlayerIdByCountryId(e.GetNeighborId(param_countryId)))
					neighbors.add(GetCountryById(e.GetNeighborId(param_countryId)));
			}
		}
		return neighbors;
	}

	/**
	 * this method returns the neighbor opponent country by id
	 * 
	 * @param param_countryId
	 *            is the id of the country
	 * @param param_neighborId
	 *            the id of the neighbor
	 * @return the neighbor country
	 */
	public Country GetNeighborOpponentById(int param_countryId, int param_neighborId) {
		for (Country c : this.GetNeighborsByCountryIdOpponentPlayer(param_countryId)) {
			if (c.GetId() == param_neighborId)
				return c;
		}
		return null;
	}

	/**
	 * this method verifies if the continent is captured by winner player
	 * 
	 * @param param_winnerPlayerId
	 *            the id of the winner player
	 * @param param_continentId
	 *            the id of the continent
	 * @return true if continent captured otherwise false
	 */
	public boolean IsContinentCaptured(int param_winnerPlayerId, int param_continentId) {
		boolean isContinentCaptured = true;
		for (Country c : this.GetCountriesByContinentId(param_continentId)) {
			if (c.GetPlayerId() != param_winnerPlayerId)
				isContinentCaptured = false;
		}
		if (isContinentCaptured) {
			GetContinentById(param_continentId).SetPlayerId(param_winnerPlayerId);
			// trigger change
			setChanged();
			notifyObservers(this);
			return true;
		}
		return false;
	}

	/**
	 * this method verifies if the world is captured by winner player
	 * 
	 * @param param_winnerPlayerId
	 *            the id of the winner player
	 * @return true if world captured otherwise false
	 */
	public boolean IsWorldCaptured(int param_winnerPlayerId) {
		boolean isWorldCaptured = true;
		for (Country c : this.GetCountries()) {
			if (c.GetPlayerId() != param_winnerPlayerId)
				isWorldCaptured = false;
		}
		if (isWorldCaptured) {
			// trigger change
			return true;
		}
		return false;
	}

	/**
	 * this method reset the visited property of all countries and get the map
	 * prepared to validate connectivity
	 */
	public void ResetVisitedWholeMap() {
		for (Country c : GetCountries()) {
			c.bVisited = false;
		}
	}

	/**
	 * this mthod travers the map from starting point and checks the
	 * connectivity of the map
	 * 
	 * @param param_startCountry
	 *            is the starting country to traverse the map
	 */
	public void DFS(Country param_startCountry) {
		List<Country> neighbors = param_startCountry.GetNeighbors();
		for (Country c : neighbors) {
			if (c != null && !c.bVisited) {
				c.bVisited = true;
				DFS(c);

			}
		}
	}

	/**
	 * this mthod travers the map from starting point and checks the
	 * connectivity of the map
	 * 
	 * @param param_startCountry
	 *            is the starting country to traverse the map
	 */
	public void DFS_continents(Country param_startCountry) {
		List<Country> neighborsInContinent = param_startCountry.GetNeighborsInContinent();
		for (Country c : neighborsInContinent) {
			if (c != null && !c.bVisited) {
				c.bVisited = true;
				DFS_continents(c);

			}
		}
	}

	/**
	 * this method validates the connectivity of the map
	 * 
	 * @return true if it is connected
	 */
	public boolean ValidationMapConnectivity() {
		this.ResetVisitedWholeMap();
		Country startCountry = this.GetCountries().get(0);
		DFS(startCountry);
		boolean mapIsConnected = true;
		for (Country c : this.GetCountries()) {
			if (!c.bVisited) {
				mapIsConnected = false;
				return false;
			}
		}
		return mapIsConnected;
	}

	/**
	 * this method verifies if all continents are connected
	 * 
	 * @return true if all of them are connected otherwise false
	 */
	public boolean ValidateContinentsConnectivity() {
		boolean continentIsValid = true;
		Country startCountry;
		for (Continent countinent : GetContinents()) {
			this.ResetVisitedWholeMap();
			continentIsValid = true;
			startCountry = GetCountriesByContinentId(countinent.GetId()).get(0);
			DFS_continents(startCountry);
			for (Country c : GetCountriesByContinentId(countinent.GetId())) {
				if (!c.bVisited) {
					continentIsValid = false;
					System.out.println("_____Start message: Continent Connectivity Validation Failed_____");
					System.out.println("Continent: "+countinent.GetName()+"is not connected continent!");
					System.out.println("_____End message: Continent Connectivity Validation Failed_____");
					
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * this method adds armies to the country it also notifies observers the
	 * changes
	 * 
	 * @param param_countryId
	 *            the id of the country
	 * @param param_armies
	 *            the number of armies to be added
	 * @return a successful message
	 */
	public String AddArmiesToCountry(int param_countryId, int param_armies) {
		this.GetCountryById(param_countryId).AddArmies(param_armies);
		// trigger change
		//setChanged();
		//notifyObservers(this);
		return "SuccessfullyAddedArmies";
	}

	/**
	 * this method performs country conquest the winner player owns the looser
	 * country
	 * 
	 * @param param_loserCountryId
	 *            is the id of the loser country
	 * @param param_playerId
	 *            is the id of the player
	 * @return successful message
	 */
	public String ConquerCountry(int param_loserCountryId, int param_playerId) {
		GetCountryById(param_loserCountryId).SetPlayerId(param_playerId);
		// trigger change
		setChanged();
		notifyObservers(this);
		return "Looser country is conquered!";
	}

	/**
	 * this method performs taking control of the continent it notifies the
	 * observers
	 * 
	 * @param param_continentId
	 *            the id of the continent to be taken control
	 * @param param_playerId
	 *            the id of the player
	 * @return successfully message
	 */
	public String TakeControlOfContinent(int param_continentId, int param_playerId) {
		GetContinentById(param_continentId).SetPlayerId(param_playerId);
		// trigger change
		//setChanged();
		//notifyObservers(this);
		return "SuccessfullyTookControl";
	}

	/**
	 * this method verifies if the attack is possible
	 * 
	 * @param param_playerId
	 *            the id of the player under the question
	 * @return true if is possible otherwise flase
	 */
	public boolean IsAttackPossibleByPlayerId(int param_playerId) {
		for (Country c : GetCountriesByPlayerId(param_playerId)) {
			if (c.GetArmies() >= 2) {
				for (Country neighber : GetNeighborsByCountryIdOpponentPlayer(c.GetId())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * this method returns the country by its id
	 * 
	 * @param param_countryId
	 *            the id of the country
	 * @return the country
	 */
	public Country GetCountryByCountryId(int param_countryId) {
		for (Country c : GetCountries()) {
			if (c.GetId() == param_countryId)
				return c;
		}
		return null;
	}

	/**
	 * this method checks if a country is the neighbor of the country under
	 * question
	 * 
	 * @param param_sCountryId
	 *            the id of the country under question
	 * @param param_dCountryId
	 *            the id of the neighbor country
	 * @return is true if it is neighbor
	 */
	public boolean IsNeighborByCountryId(int param_sCountryId, int param_dCountryId) {
		for (Edge e : listEdges) {
			if ((e.GetCountryId1() == param_sCountryId && e.GetCountryId2() == param_dCountryId)
					|| (e.GetCountryId1() == param_dCountryId && e.GetCountryId2() == param_sCountryId)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * this method calculates worls domination percentage per player it provied
	 * information for world domination view
	 * 
	 * @return is the information about world domination in percentage of number
	 *         of countries per player
	 */
	public String GetWorldDomination() {
		int player1 = 0, player2 = 0, player3 = 0, player4 = 0, player5 = 0;
		for (Country c : GetCountries()) {
			switch (c.GetPlayerId()) {
			case 1:
				player1 += 1;
				break;
			case 2:
				player2 += 1;
				break;
			case 3:
				player3 += 1;
				break;
			case 4:
				player4 += 1;
				break;
			case 5:
				player5 += 1;
				break;
			}
		}
		if ((player1 + player2 + player3 + player4 + player5) != 0) {
			return "player1 %: " + player1 * 100 / (player1 + player2 + player3 + player4 + player5) + "player2 %: "
					+ player2 * 100 / (player1 + player2 + player3 + player4 + player5) + " player3 %: "
					+ player3 * 100 / (player1 + player2 + player3 + player4 + player5) + " player4 %: "
					+ player4 * 100 / (player1 + player2 + player3 + player4 + player5) + " player5 %: "
					+ player5 * 100 / (player1 + player2 + player3 + player4 + player5);
		} else {
			return "data is not ready";
		}

	}

	/**
	 * this method sets the player id for a country it also triggers the
	 * observer
	 * 
	 * @param param_country
	 *            is the country to change its player id
	 * @param param_playerId
	 *            is the id of the player
	 */
	public void SetCountryPlayerId(Country param_country, int param_playerId) {
		param_country.SetPlayerId(param_playerId);
		setChanged();
		notifyObservers(this);
	}
	/**this method returns the list of countries of a player that are exposed to attack
	 * 
	 * @param param_playerId the id of the player
	 * @return the list of weak countries
	 */
	public List<Country> GetWeakCountriesByPlayerId(int param_playerId){
		List<Country> countries = new ArrayList<Country>();
		for(Country c : GetCountriesByPlayerId(param_playerId)){
			for(Country neighbor : GetNeighborsByCountryIdOpponentPlayer(c.GetId())){
				if(neighbor.GetArmies()>0){
					countries.add(c);
					break;
				}
			}
		}
		return countries;
	}
	/**this method returns the weakest country
	 * 
	 * @param param_playerId the id of the player
	 * @return theweakest country
	 */
	public Country GetWeakestCountry(int param_playerId){
		Country weakestC = new Country();
		int lowestArmies=10000;
		for(Country c : GetWeakCountriesByPlayerId(param_playerId)){
			if(c.GetArmies()<=lowestArmies){
				weakestC=c;
			}
		}
		return weakestC;
	}
	/**this method returns the country with highest armies
	 * 
	 * @param param_playerId the id of the player
	 * @return the country with highest armies
	 */
	public Country GetHighArmiesCountry(int param_playerId){
		Country highestC = new Country();
		int highestArmies=0;
		for(Country c : GetCountriesByPlayerId(param_playerId)){
			if(c.GetArmies()<=highestArmies){
				highestC=new Country();
				highestC=c;
			}
		}
		return highestC;
	}
	public Country GetBorderCountryByPlayerId(int param_playerId){
		Country country = new Country();
		for(Country c : GetCountriesByPlayerId(param_playerId)){
			for(Country neighbor : GetNeighborsByCountryIdOpponentPlayer(c.GetId())){
				if(c.GetArmies()>=2){
					country=c;
					return c;
				}
				
			}
		
		}
		return 	null;
	}
	public List<Country> GetBorderCountriesByPlayerId(int param_playerId){
		List<Country> countries = new ArrayList<Country>();
		for(Country c : GetCountriesByPlayerId(param_playerId)){
			if(GetNeighborsByCountryIdOpponentPlayer(c.GetId()).size()>0){
				countries.add(c);
			}
		}
		return 	countries;
	}

}
