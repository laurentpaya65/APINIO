package org.epita.comptage;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import org.epita.comptage.LectureFichier.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class TestLecture {

    @Test
    public void lecture_fichier() {
        List<String> listeLignes = new ArrayList<>(LectureFichier.fichier());

        assertThat(listeLignes.size()).isEqualTo(18);       // la taille
        assertThat(listeLignes.get(0)).isEqualTo("Terre Terre Terre brûlée au vent"); // le 1er
        assertThat(listeLignes.get(listeLignes.size()-1)).isEqualTo("Du Connemara"); // le dernier
    }
    @Test
    public void split_en_mots() {
        List<String> motslignes = new ArrayList<>(LectureFichier.separerMots(LectureFichier.fichier()));

        assertThat(motslignes.size()).isEqualTo(59);       // la taille
        assertThat(motslignes.get(0)).isEqualTo("TERRE"); // le 1er
        assertThat(motslignes.get(motslignes.size()-1)).isEqualTo("CONNEMARA"); // le dernier
    }
    @Test
    public void mots_unique() {
        Set<String> motsUnique = new HashSet<>(LectureFichier.separerMots(LectureFichier.fichier())) ;

        assertThat(motsUnique.size()).isEqualTo(42);       // la taille
        assertThat(motsUnique).contains("TERRE"); // le 1er
        assertThat(motsUnique).contains("CONNEMARA"); // le dernier
    }
    @Test
    public void comptage_mots() {
        Set<String> motsUnique = new HashSet<>(LectureFichier.separerMots(LectureFichier.fichier())) ;
        List<String> motslignes = new ArrayList<>(LectureFichier.separerMots(LectureFichier.fichier()));
        Map<String,Integer> compteMots = new HashMap<>(LectureFichier.comptage(motslignes,motsUnique));

        assertThat(compteMots).containsEntry("TERRE",4);
    }
    @Test
    public void ecrire_fichier() {
        Set<String> motsUnique = new HashSet<>(LectureFichier.separerMots(LectureFichier.fichier())) ;
        List<String> motslignes = new ArrayList<>(LectureFichier.separerMots(LectureFichier.fichier()));
        final Map<String,Integer> compteMots = new HashMap<>(LectureFichier.comptage(motslignes,motsUnique));

        String nomFichier = "chansonmots.txt";
        Path path = Paths.get(nomFichier);

        LectureFichier.ecritureFichier(compteMots,nomFichier);         //ecriture Fichier
        assertThat(path).exists();
    }
    @Test
    public void ecrire_fichier_trie() {
        Set<String> motsUnique = new HashSet<>(LectureFichier.separerMots(LectureFichier.fichier())) ;
        List<String> motslignes = new ArrayList<>(LectureFichier.separerMots(LectureFichier.fichier()));
        final Map<String,Integer> compteMots = new HashMap<>(LectureFichier.comptage(motslignes,motsUnique));

        String nomFichier = "chansonmotsTrie.txt";
        Path path = Paths.get(nomFichier);

        LectureFichier.ecritureFichier(LectureFichier.listeTriee(compteMots),nomFichier);         //ecriture Fichier
        assertThat(path).exists();
    }
}
