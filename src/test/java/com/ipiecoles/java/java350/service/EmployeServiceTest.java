package com.ipiecoles.java.java350.service;

import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.NiveauEtude;
import com.ipiecoles.java.java350.model.Poste;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityExistsException;
import java.time.LocalDate;


@ExtendWith(MockitoExtension.class)
public class EmployeServiceTest {

    @InjectMocks
    private EmployeService employeService;

    @Mock
    private EmployeRepository employeRepository;

    @Test
    void testEmbauchePremierEmployePleinTempsManagerIngenieur() throws Exception {
        //Given
        String nom = "Jean";
        String prenom = "Aurore";
        Poste poste = Poste.MANAGER;
        NiveauEtude niveauEtude = NiveauEtude.INGENIEUR;
        Double tempsPartiel = 1d;

        //Ajouter les mocks...
        Mockito.when(employeRepository.findLastMatricule()).thenReturn(null);
        Mockito.when(employeRepository.findByMatricule("M00001")).thenReturn(null);
        // Lorsque save va être appelé, peut importe le paramètre, je veux retourner la valeur du premier paramètre
        Mockito.when(employeRepository.save(Mockito.any(Employe.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());

        //When
        employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);


        //Then
        ArgumentCaptor<Employe> employeArgumentCaptor = ArgumentCaptor.forClass(Employe.class);
        Mockito.verify(employeRepository).save(employeArgumentCaptor.capture());
        Employe employe = employeArgumentCaptor.getValue();

        Assertions.assertThat(employe).isNotNull();
        Assertions.assertThat(employe.getNom()).isEqualTo(nom);
        Assertions.assertThat(employe.getPrenom()).isEqualTo(prenom);
        Assertions.assertThat(employe.getDateEmbauche()).isEqualTo(LocalDate.now());
        Assertions.assertThat(employe.getMatricule()).isEqualTo("M00001");
        Assertions.assertThat(employe.getTempsPartiel()).isEqualTo(tempsPartiel);
        Assertions.assertThat(employe.getSalaire()).isEqualTo(2433.95d);
    }

    @Test
    void testEmbaucheEmployePleinTempsManagerIngenieur() throws Exception {
        //Given
        String nom = "Didou";
        String prenom = "Koala";
        Poste poste = Poste.MANAGER;
        NiveauEtude niveauEtude = NiveauEtude.INGENIEUR;
        Double tempsPartiel = 1d;

        //Ajouter les mocks...
        Mockito.when(employeRepository.findLastMatricule()).thenReturn("12345");
        Mockito.when(employeRepository.findByMatricule("M12346")).thenReturn(null);
        // Lorsque save va être appelé, peut importe le paramètre, je veux retourner la valeur du premier paramètre
        Mockito.when(employeRepository.save(Mockito.any(Employe.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());


        //When
        employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);

        //Then
        ArgumentCaptor<Employe> employeArgumentCaptor = ArgumentCaptor.forClass(Employe.class);
        Mockito.verify(employeRepository).save(employeArgumentCaptor.capture());
        Employe employe = employeArgumentCaptor.getValue();

        Assertions.assertThat(employe).isNotNull();
        Assertions.assertThat(employe.getNom()).isEqualTo(nom);
        Assertions.assertThat(employe.getPrenom()).isEqualTo(prenom);
        Assertions.assertThat(employe.getDateEmbauche()).isEqualTo(LocalDate.now());
        Assertions.assertThat(employe.getMatricule()).isEqualTo("M12346");
        Assertions.assertThat(employe.getTempsPartiel()).isEqualTo(tempsPartiel);
        Assertions.assertThat(employe.getSalaire()).isEqualTo(2433.95d);
    }

    @Test
    void testEmbauchePremierEmployeMiTempsManagerIngenieur() throws Exception {
        //Given
        String nom = "Célère";
        String prenom = "Jacques";
        Poste poste = Poste.MANAGER;
        NiveauEtude niveauEtude = NiveauEtude.INGENIEUR;
        Double tempsPartiel = 0.5d;

        //Ajouter les mocks...
        Mockito.when(employeRepository.findLastMatricule()).thenReturn(null);
        Mockito.when(employeRepository.findByMatricule("M00001")).thenReturn(null);
        // Lorsque save va être appelé, peut importe le paramètre, je veux retourner la valeur du premier paramètre
        Mockito.when(employeRepository.save(Mockito.any(Employe.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());


        //When
        employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);

        //Then
        ArgumentCaptor<Employe> employeArgumentCaptor = ArgumentCaptor.forClass(Employe.class);
        Mockito.verify(employeRepository).save(employeArgumentCaptor.capture());
        Employe employe = employeArgumentCaptor.getValue();

        Assertions.assertThat(employe).isNotNull();
        Assertions.assertThat(employe.getNom()).isEqualTo(nom);
        Assertions.assertThat(employe.getPrenom()).isEqualTo(prenom);
        Assertions.assertThat(employe.getDateEmbauche()).isEqualTo(LocalDate.now());
        Assertions.assertThat(employe.getMatricule()).isEqualTo("M00001");
        Assertions.assertThat(employe.getTempsPartiel()).isEqualTo(tempsPartiel);
        Assertions.assertThat(employe.getSalaire()).isEqualTo(1216.98d);
    }

    @Test
    void testEmbaucheEmployeLimiteMatricule() throws Exception {
        //Given
        String nom = "Jean";
        String prenom = "Aurore";
        Poste poste = Poste.MANAGER;
        NiveauEtude niveauEtude = NiveauEtude.INGENIEUR;
        Double tempsPartiel = 1d;

        //Ajouter les mocks...
        Mockito.when(employeRepository.findLastMatricule()).thenReturn("99999");

        //When
        try {
            employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);
            Assertions.fail("Aurait du planter");
        } catch (Exception e) {
            //Then
            Assertions.assertThat(e).isInstanceOf(EmployeException.class);
            Assertions.assertThat(e.getMessage()).isEqualTo("Limite des 100000 matricules atteinte !");
        }

        // Equivalence avec la derniere syntaxe
        // When / Then
        Assertions.assertThatThrownBy(() -> employeService.embaucheEmploye(nom,prenom,poste,niveauEtude,tempsPartiel))
                .isInstanceOf(EmployeException.class)
                .hasMessage("Limite des 100000 matricules atteinte !");

    }

    @Test
    void testEmbaucheEmployeExistant() throws Exception {
        //Given
        String nom = "Jean";
        String prenom = "Aurore";
        Poste poste = Poste.MANAGER;
        NiveauEtude niveauEtude = NiveauEtude.INGENIEUR;
        Double tempsPartiel = 1d;

        //Ajouter les mocks...
        Mockito.when(employeRepository.findLastMatricule()).thenReturn("99998");
        Mockito.when(employeRepository.findByMatricule("M99999")).thenReturn(new Employe());

        //When
        try {
            employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);
            Assertions.fail("Aurait du planter");
        } catch (Exception e) {
            //Then
            //Vérifie qu'une exception est bien levée, et que c'est la bonne exception
            Assertions.assertThat(e).isInstanceOf(EntityExistsException.class);
            Assertions.assertThat(e.getMessage()).isEqualTo("L'employé de matricule M99999 existe déjà en BDD");
        }

        // Equivalence avec la derniere syntaxe
        // When / Then
        Assertions.assertThatThrownBy(() -> employeService.embaucheEmploye(nom,prenom,poste,niveauEtude,tempsPartiel))
                .isInstanceOf(EntityExistsException.class)
                .hasMessage("L'employé de matricule M99999 existe déjà en BDD");

    }


    // Test sur le calcul de la nouvelle performance pour un commercial
    @ParameterizedTest(name = "Le matricule {0} avec une performance de {1} et un CA traité de {2} et un Objectif CA {3}. La nouvelle perf => {4}")
    @CsvSource({
            // Cas 2 : caTraite >= objectifCa*0.8 && caTraite < objectifCa*0.95
            // => 4000 (5000*0.8) > 4500 && 4500 < 4750 (5000*0.95)
            // performance = Math.max(Entreprise.PERFORMANCE_BASE, employe.getPerformance() - 2)
            // => performance entreprise = 1, performance employé = 4 -> donc nouvelle performance employé = 2 (4 - 2)
            "'C12345',4,4500,5000,2",
            // Cas 3 : caTraite >= objectifCa*0.95 && caTraite <= objectifCa*1.05
            // => 4500 > 4275 (4500*0.95) && 4500 < 4725 (4500*1.05)
            // performance = Math.max(Entreprise.PERFORMANCE_BASE, employe.getPerformance())
            // => performance entreprise = 1, performance employé = 2 -> donc nouvelle performance employé = 2
            "'C12346',2,4500,4500,2",
            // Cas 4 : caTraite <= objectifCa*1.2 && caTraite > objectifCa*1.05
            // => 11000 < 12000 (10000*1.2) && 11000 > 10500 (10000*1.05)
            // performance = employe.getPerformance() + 1
            // => performance employé = 1 + 1 -> donc nouvelle performance employé = 2
            "'C12348',1,11000,10000,2",
            // Cas 5 : caTraite > objectifCa*1.2
            // => 10000 > 6000 (5000*1.2)
            // performance = employe.getPerformance() + 4
            // => performance employé = 1 + 4 -> donc performance = 5
            // la performance moyenne étant de 3, la performance calculé est donc supérieur, on ajoute +1 à la performance
            // => donc nouvelle performance employé = 6
            "'C12349',1,10000,5000,6",
            // Cas autre => nouvelle performance employé = performance de base donc 1
            "'C12345',9,2000,5000, 1"

    })
    void testCalculPerformanceCommercial(String matricule, Integer performance, Long caTraite, Long objectifCa, Integer resultPerformance) throws EmployeException {
        //Given
        Employe employe = new Employe();
        employe.setMatricule(matricule);
        employe.setPerformance(performance);
        employe.setTempsPartiel(1d);
        employe.setNom("Fly");
        employe.setPrenom("Hackim");
        //When
        Mockito.when(employeRepository.findByMatricule(employe.getMatricule())).thenReturn(employe);
        Mockito.when(employeRepository.avgPerformanceWhereMatriculeStartsWith("C")).thenReturn(3d);
        employeService.calculPerformanceCommercial(matricule,caTraite,objectifCa);
        ArgumentCaptor<Employe> employeArgumentCaptor = ArgumentCaptor.forClass(Employe.class);
        Mockito.verify(employeRepository,Mockito.times(1)).save(employeArgumentCaptor.capture());
        //Then
        Assertions.assertThat(employeArgumentCaptor.getValue().getPerformance()).isEqualTo(resultPerformance);
    }

    // Cas pour un commercial dont le matricule n'existe pas en BDD
    @Test
    void testCalculPerformanceCommercialMatriculeNotExist() throws EmployeException {
        //Given
        Long caTraite = 10000L;
        Long objectifCa = 5000L;
        String matricule = "C12345";
        //When
        Mockito.when(employeRepository.findByMatricule(matricule)).thenReturn(null);
        //Then
        Assertions.assertThatThrownBy(()-> employeService.calculPerformanceCommercial(matricule,caTraite,objectifCa))
                .isInstanceOf(EmployeException.class)
                .hasMessage("Le matricule " + matricule + " n'existe pas !");
    }

    // Cas avec matricule ne commençant pas par 'C'
    @Test
    void testCalculPerformanceCommercialMatriculeNotStartWithC() throws EmployeException {
        //Given
        Long caTraite = 10000L;
        Long objectifCa = 5000L;
        String matricule = "T12345";
        //Then
        Assertions.assertThatThrownBy(()-> employeService.calculPerformanceCommercial(matricule,caTraite,objectifCa))
                .isInstanceOf(EmployeException.class)
                .hasMessage("Le matricule ne peut être null et doit commencer par un C !");
    }

    // Cas caTraite nul ou négatif
    @Test
    void testCalculPerformanceCommercialCATraiteNegatif() throws EmployeException {
        //Given
        Long caTraite = -1000L;
        Long objectifCa = 5000L;
        String matricule = "T12345";
        //Then
        Assertions.assertThatThrownBy(()-> employeService.calculPerformanceCommercial(matricule,caTraite,objectifCa))
                .isInstanceOf(EmployeException.class)
                .hasMessage("Le chiffre d'affaire traité ne peut être négatif ou null !");
    }

    // Cas objectifCa nul ou négatif
    @Test
    void testCalculPerformanceCommercialObjectifCANegatif() throws EmployeException {
        //Given
        Long caTraite = 1000L;
        Long objectifCa = -5000L;
        String matricule = "T12345";
        //Then
        Assertions.assertThatThrownBy(()-> employeService.calculPerformanceCommercial(matricule,caTraite,objectifCa))
                .isInstanceOf(EmployeException.class)
                .hasMessage("L'objectif de chiffre d'affaire ne peut être négatif ou null !");
    }
}