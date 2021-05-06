package com.ipiecoles.java.java350.service;

import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.NiveauEtude;
import com.ipiecoles.java.java350.model.Poste;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
        String nom = "Jean";
        String prenom = "Aurore";
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
}