package org.epita.comptage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class LectureFichier {
    // lecture du fichier en ENTREE
    public static List<String> fichier() {
        Path path = Paths.get("chanson.txt");
        List<String> lignesFichier = new ArrayList<String>();

        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lignesFichier.add(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lignesFichier;
    }

    // lecture du fichier sans tri des mots
    public static void ecritureFichier(Map<String,Integer> listeMot,String nomFichier){
        Path path = Paths.get(nomFichier);
        String ligne = "";
        int total=0;

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            for (Map.Entry<String,Integer> element : listeMot.entrySet()){
                ligne = "Mots="+element.getKey() + " apparait " + element.getValue() + " fois\n";
                bufferedWriter.write(ligne);
                total = total + element.getValue();
            }
            ligne = "le TOTAL de mots est "+total;
            bufferedWriter.write(ligne);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // séparation des mots sur une LIGNE
    public static List<String> separerMots(List<String> lignes) {
        List<String> lignesFichier = new ArrayList<String>();

        for (int i = 0; i < lignes.size(); i++) {
            String[] motsLigne = lignes.get(i).split(" ");
            for (int j = 0; j < motsLigne.length; j++) {
                lignesFichier.add(motsLigne[j].toUpperCase());
//                System.out.println(motsLigne[j].toUpperCase());
            }
        }
        return lignesFichier;
    }

    // établir une de mots UNIQUE avec un Set
    public static Set<String> listeMots(List<String> mots) {
        return new HashSet<String>(mots);
    }

    // compter chaque mot
    public static Map<String , Integer> comptage(List<String> mots,Set<String> motsUnique) {
        Map<String,Integer> compteMots = new HashMap<>();
        int valeur = 0;
        for (String mot : motsUnique){
            compteMots.put(mot,0);              // renseigne les clés
        }
        for (String element : mots) {
            valeur = compteMots.get(element);
            compteMots.put(element,++valeur);
        }
        for (Map.Entry<String,Integer> compte : compteMots.entrySet()){
//            System.out.println("Mots="+compte.getKey() + " apparait " + compte.getValue() + " fois ");
        }

        return compteMots;
    }

    // trier le comptage
    public static Map<String,Integer> listeTriee(Map<String,Integer> listeFichier) {
        HashMap<String,Integer> map = new HashMap<String, Integer>();
        Comparateur comp =  new Comparateur(map);
        TreeMap<String,Integer> listeTriee = new TreeMap<String, Integer>(comp);

        map.putAll(listeFichier);
        listeTriee.putAll(map);
//        for (Map.Entry<String,Integer> compte : listeFichier.entrySet()){
//            listeTriee.put(compte.getKey(), compte.getValue());
////            System.out.println("Mots="+compte.getKey() + " apparait " + compte.getValue() + " fois ");
//        }

        return listeTriee;
    }
}
