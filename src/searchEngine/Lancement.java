package searchEngine;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Lancement {
	public static void main(String[] args) {
		ArrayList<String> stopwordlist = initListe();
		String[] listeDeMots;
		TreeMap<Integer, Double> mapFrequences = new TreeMap();
		Index ind = new Index();
		System.out.println();
		Scanner scan = new Scanner(System.in);
		// scan.useDelimiter(" ");
		// String test = scan.nextLine();

		listeDeMots = scan.nextLine().trim().split(" ");
		ArrayList<String> listeFinale = new ArrayList(Arrays.asList(listeDeMots));
		mapFrequences = initMap(stopwordlist, listeDeMots);
		Set<Integer> keys = mapFrequences.keySet();

		for (Integer key : keys) {
			Document d = ind.getDocument(key);
			System.out.println(d.getTitle());
			System.out.println(d.getText());
		}
	}

	public static TreeMap<Integer, Double> initMap(ArrayList<String> stopwordlist, String[] listeDeMots) {
		TreeMap<Integer, Double> mapFrequences = new TreeMap();
		for (int i = 0; i < listeDeMots.length; i++) {
			if (!stopwordlist.contains(listeDeMots[i])) {
				System.out.println(listeDeMots[i]);
				Index.loadVocabulary();
				Keyword k = Index.getKeyword(listeDeMots[i]);
				// System.out.println(k);
				mapFrequences = k.getFrequences();
				// System.out.println(mapFrequences);
			}
		}
		System.out.println("fin méthode");
		return mapFrequences;
	}

	public static ArrayList<String> initListe() {
		// Open the file
		ArrayList<String> list = new ArrayList<String>();
		try {
			FileInputStream fstream;
			fstream = new FileInputStream("src/blocklist.txt");
			String strLine;
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				// Print the content on the console
				list.add(strLine);
			}
			// Close the input stream
			fstream.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
}
