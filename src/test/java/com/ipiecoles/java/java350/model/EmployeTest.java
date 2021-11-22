package com.ipiecoles.java.java350.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

public class EmployeTest {
    //Scénarios de test, 1 scénario = 1 test
    @Test
    public void testGetNbAnneesAncienneteDateEmbaucheToday(){
        //Given
        LocalDate dateEmbaucheToday = LocalDate.now();
        Employe employe = new Employe();
        employe.setDateEmbauche(dateEmbaucheToday);
        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();
        //Then
        Assertions.assertThat(nbAnneesAnciennete).isEqualTo(0);
    }

    @Test
    public void testGetNbAnneesAncienneteDateEmbaucheFuture(){
        //Given
        LocalDate dateEmbaucheFuture = LocalDate.now().plusYears(5);
        Employe employe = new Employe();
        employe.setDateEmbauche(dateEmbaucheFuture);
        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();
        //Then
        Assertions.assertThat(nbAnneesAnciennete).isNull();
    }

    @Test
    public void testGetNbAnneesAncienneteDateEmbauchePast(){
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now().minusYears(5));
        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();
        //Then
        Assertions.assertThat(nbAnneesAnciennete).isEqualTo(5);
    }

    @Test
    public void testGetNbAnneesAncienneteDateEmbaucheNull(){
        //Given
        Employe employe = new Employe("Doe","John","T1234",null,2500.0,1,1.0);
        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();
        //Then
        Assertions.assertThat(nbAnneesAnciennete).isNull();
    }

    @Test
    public void testGetPrimeAnnuelleManagerSansAnciennetePleinTemps(){
        //Given
        // 4 données d'entrée
        LocalDate dateEmbauche = LocalDate.now();
        Integer performance = null;
        String matricule = "M12345";
        Double tempsPartiel = 1.0;
        //Initialise l'employé à partir des données d'entrée
        Employe employe = new Employe("Doe", "John", matricule,
                dateEmbauche, Entreprise.SALAIRE_BASE, performance, tempsPartiel);
        //When
        Double primeCalculee = employe.getPrimeAnnuelle();

        //Then
        //1000 * 1.7 = 1700
        Assertions.assertThat(primeCalculee).isEqualTo(1700.0);
    }

    @Test //Change l'annotation
    //Rajoute l'annotation contenant les scénarios de test  (réflechir aux dfférents scénarios possibles)
    public void testGetPrimeAnnuelle(/*Les données d'entrées et les données de sortie*/){
        //Given
        // 4 données d'entrée => remplacer par les paramètres
        LocalDate dateEmbauche = LocalDate.now();
        Integer performance = null;
        String matricule = "M12345";
        Double tempsPartiel = 1.0;
        //Initialise l'employé à partir des données d'entrée
        Employe employe = new Employe("Doe", "John", matricule,
                dateEmbauche, Entreprise.SALAIRE_BASE, performance, tempsPartiel);
        //When
        Double primeCalculee = employe.getPrimeAnnuelle();
        //Then
        //1000 * 1.7 = 1700
        //Remplace la valeur de sortie en dur par le paramètre de sortie
        Assertions.assertThat(primeCalculee).isEqualTo(1700.0);
    }

    @ParameterizedTest(name = "Employé anciennete {0}, performance {1}, matricule {2}, temps partiel {3} => Prime {4}") //Change l'annotation
    //Rajoute l'annotation contenant les scénarios de test  (réflechir aux dfférents scénarios possibles)
    @CsvSource({
            "0,,'M12345',1.0,1700.0"
    })
    public void testGetPrimeAnnuelle2(Integer nbAnneesAnciennete, Integer performance, String matricule, Double tempsPartiel,
                                     Double primeObtenue){
        //Given
        // 4 données d'entrée => remplacer par les paramètres
        LocalDate dateEmbauche = LocalDate.now().minusYears(nbAnneesAnciennete);
        //Initialise l'employé à partir des données d'entrée
        Employe employe = new Employe("Doe", "John", matricule,
                dateEmbauche, Entreprise.SALAIRE_BASE, performance, tempsPartiel);
        //When
        Double primeCalculee = employe.getPrimeAnnuelle();
        //Then
        //Remplace la valeur de sortie en dur par le paramètre de sortie
        Assertions.assertThat(primeCalculee).isEqualTo(primeObtenue);
    }

    @ParameterizedTest(name = "Employé anciennete {0}, performance {1}, matricule {2}, temps partiel {3} => Prime {4}") //Change l'annotation
    //Rajoute l'annotation contenant les scénarios de test  (réflechir aux dfférents scénarios possibles)
    @CsvSource({
            "0,,'M12345',1.0,1700.0", //Manager à plein temps sans ancienneté
            "0,,'T12345',1.0,1000.0", //Technicien à plein temps sans ancienneté
            "0,,'M12345',0.5,850.0", //Manager à mi-temps sans ancienneté
            "5,,'M12345',1.0,2200.0", //Manager à plein temps avec 5 années d'ancienneté
            "0,3,'T12345',1.0,3300.0", //Technicien à plein temps sans ancienneté avec performance 3

            })
    public void testGetPrimeAnnuelle(Integer nbAnneesAnciennete, Integer performance, String matricule, Double tempsPartiel,
                                     Double primeObtenue){
        //Given
        // 4 données d'entrée => remplacer par les paramètres
        LocalDate dateEmbauche = LocalDate.now().minusYears(nbAnneesAnciennete);
        //Initialise l'employé à partir des données d'entrée
        Employe employe = new Employe("Doe", "John", matricule,
                dateEmbauche, Entreprise.SALAIRE_BASE, performance, tempsPartiel);
        //When
        Double primeCalculee = employe.getPrimeAnnuelle();
        //Then
        //Remplace la valeur de sortie en dur par le paramètre de sortie
        Assertions.assertThat(primeCalculee).isEqualTo(primeObtenue);
    }

    @Test
    public void testAugmenterSalaireManager(){
        //Given
        Poste poste = Poste.MANAGER;
        Double salaire = 3000.0;
        LocalDate dateEmbauche = LocalDate.now().minusYears(5);

        //Initialise l'employé à partir des données d'entrée
        Employe employe = new Employe("Doe", "John", "M00012",
                dateEmbauche, salaire, 1, 1.0);

        //When

        Double newSalaire = employe.augmenterSalaire(10.0);

        //Then
        // 3000 + 10%
        Assertions.assertThat(newSalaire).isEqualTo(3300);
    }

    @Test
    public void testAugmenterSalaireTechnicien(){
        //Given
        Poste poste = Poste.TECHNICIEN;
        Double salaire = 1000.0;
        LocalDate dateEmbauche = LocalDate.now().minusYears(10);

        //Initialise l'employé à partir des données d'entrée
        Employe employe = new Employe("Amstrong", "Toto", "T00012",
                dateEmbauche, salaire, 1, 1.0);

        //When
        Double newSalaire = employe.augmenterSalaire(5.0);

        //Then
        // 1000 + 5%
        Assertions.assertThat(newSalaire).isEqualTo(1050);
    }

    @Test
    public void testAugmenterSalaireCommercial(){
        //Given
        Poste poste = Poste.COMMERCIAL;
        Double salaire = 2000.0;
        LocalDate dateEmbauche = LocalDate.now().minusYears(1);

        //Initialise l'employé à partir des données d'entrée
        Employe employe = new Employe("Durand", "Pauline", "C00012",
                dateEmbauche, salaire, 1, 1.0);

        //When
        Double newSalaire = employe.augmenterSalaire(8.0);

        //Then
        // 2000 + 8%
        Assertions.assertThat(newSalaire).isEqualTo(2160);
    }

    @Test
    public void testAugmenterSalaireNouvelEmploye(){
        //Given
        Poste poste = Poste.COMMERCIAL;
        Double salaire = 2000.0;
        LocalDate dateEmbauche = LocalDate.now();

        //Initialise l'employé à partir des données d'entrée
        Employe employe = new Employe("Delacoline", "Marie", "C00014",
                dateEmbauche, salaire, 1, 1.0);

        //When
        Double newSalaire = employe.augmenterSalaire(0.0);

        //Then
        // 2000 + 0%
        Assertions.assertThat(newSalaire).isEqualTo(2000);
    }

    @ParameterizedTest(name = "matricule {0}, salaire {1}, pourcentage d'augmentation {2}, Integer nbAnneeAnciennete {3} => Salaire final {4}")
    //Rajoute l'annotation contenant les scénarios de test  (réflechir aux dfférents scénarios possibles)
    @CsvSource({
            "'M00012',3000,10.0,5,3300.0", // Manager avec ancienneté
            "'T00012',1000,5.0,10,1050.0", // Technicien avec ancienneté
            "'C00012',2000,8.0,1,2160.0",  // Commercial avec ancienneté
            "'C00014',2000,8.0,0,2000.0",  // Commercial sans ancienneté
            "'E00014',2000,8.0,0,2000.0",  // Employé autre que C, T, ou M sans ancienneté
            ",2000,8.0,0,2000.0",          // Matricule null

    })
    public void testAugmenterSalaire(String matricule, Double salaire, Double pourcentage, Integer nbAnneesAnciennete, Double newSalaire) {

        //Given
        // 4 données d'entrée => remplacer par les paramètres
        LocalDate dateEmbauche = LocalDate.now().minusYears(nbAnneesAnciennete);
        //Initialise l'employé à partir des données d'entrée
        Employe employe = new Employe("Pierre", "Richard", matricule,
                dateEmbauche, salaire, 1, 1.0);
        ///When
        Double salaireEstime = employe.augmenterSalaire(pourcentage);

        //Then
        Assertions.assertThat(salaireEstime).isEqualTo(newSalaire);
    }
    

    @ParameterizedTest(name = "anneeActuelle {0} => nbRTT {1}")
    @CsvSource({
            "'2019-01-01',8" ,  // 2019 : l'année est non bissextile, a débuté un mardi et il y a 10 jours fériés ne tombant pas le week-end.
            "'2021-01-01',10" , // 2021 : l'année est non bissextile, a débuté un vendredi et il y a 7 jours fériés ne tombant pas le week-end.
            "'2022-01-01',10" , // 2022 : l'année est non bissextile, a débuté un samedi et il y a 7 jours fériés ne tombant pas le week-end.
            "'2026-01-01',9" ,  // 2026 : l'année est non bissextile, a débuté un jeudi et il y a 8 jours fériés ne tombant pas le week-end.
            "'2032-01-01',11" , // 2032 : l'année est bissextile, a débuté un jeudi et il y a 7 jours fériés ne tombant pas le week-end.
            "'2044-01-01',9" ,  // 2044 : l'année est bissextile, a débuté un vendredi et il y a 8 jours fériés ne tombant pas le week-end.

    })

    public void testGetNbRTT(LocalDate anneeActuelle, Integer nbRTT){
        //given
        Employe employe = new Employe();

        //when
        Integer RTTEstime = employe.getNbRtt(anneeActuelle);

        //then
        //resultat attendus
        Assertions.assertThat(RTTEstime).isEqualTo(nbRTT);

    }
}
