package com.ipiecoles.java.java350.repository;

import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.Entreprise;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDate;

@ExtendWith(SpringExtension.class) //Junit 5
@SpringBootTest // ou @DataJpaTest
// @TestInstance(TestInstance.Lifecycle.PER_CLASS) => utile pour faire fonctionner le @BeforeAll
public class EmployeRepositoryTest {

    @Autowired
    EmployeRepository employeRepository;

    @AfterEach
    // @BeforeAll => peut remplacer le @BeforeEach
    @BeforeEach
    public void cleanUp(){
        employeRepository.deleteAll();
    }

    //3 Employés avec matricules différents
    @Test
    void testFindLastMatricule3Employes(){
        //Given
        employeRepository.save(new Employe("Doe", "John", "C11032",
                LocalDate.now(), Entreprise.SALAIRE_BASE, Entreprise.PERFORMANCE_BASE, 1.0));
        employeRepository.save(new Employe("Doe", "Jane", "M12345",
                LocalDate.now(), Entreprise.SALAIRE_BASE, Entreprise.PERFORMANCE_BASE, 1.0));
        employeRepository.save(new Employe("Doe", "Jim", "T12000",
                LocalDate.now(), Entreprise.SALAIRE_BASE, Entreprise.PERFORMANCE_BASE, 1.0));
        //When
        String lastMatricule = employeRepository.findLastMatricule();
        //Then
        Assertions.assertThat(lastMatricule).isEqualTo("12345");
    }

    @Test
    public void testFindLastMatricule0Employe(){
        //Given
        //When
        String lastMatricule = employeRepository.findLastMatricule();

        //Then
        Assertions.assertThat(lastMatricule).isNull();
    }

}
