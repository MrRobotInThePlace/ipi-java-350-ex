import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.Entreprise;
import com.ipiecoles.java.java350.service.EmployeService;
import com.thoughtworks.gauge.Step;
import org.assertj.core.api.Assertions;
import java.time.LocalDate;

public class StepImplementation {

    Employe employe;
    Entreprise entreprise;
    EmployeService employeService;
    Integer nbAnneeAnciennete;
    Double primeAnnuelleEmploye;
    String matriculeCommercial = "C12345";
    Long caTraite;
    Long objectifCa;
    Integer performanceCalculee;

    @Step("Soit un technicien")
    public void createEmploye() {
        employe = new Employe();
        employe.setPrenom("John");
        employe.setPrenom("Doe");
        employe.setMatricule("T12345");
    }

    @Step("Soit un manager")
    public void createManager() {
        employe = new Employe();
        employe.setMatricule("M12345");
    }

    @Step("Soit un commercial")
    public void createCommercial() {
        employe = new Employe();
        employe.setMatricule("C12345");
    }

    @Step("embauché cette année")
    public void embaucheToday() {
        employe.setDateEmbauche(LocalDate.now());
    }

    @Step("embauché il y a <5> an(s)")
    public void embauchePast(Integer anneeAnciennete) {
        employe.setDateEmbauche(LocalDate.now().minusYears(anneeAnciennete));
    }

    @Step("travaillant à plein temps")
    public void employeTempsPlein() {
        employe.setTempsPartiel(1.0);
    }

    @Step("travaillant à mi-temps")
    public void employeTempsPartiel() {
        employe.setTempsPartiel(0.5);
    }

    @Step("avec une performance de <0>")
    public void employePerformance(Integer performanceEmploye) {
        employe.setPerformance(performanceEmploye);
    }

    @Step("Lorsque je calcule son nombre d'année d'ancienneté")
    public void getNbAnneeAnciennete() {
        nbAnneeAnciennete = employe.getNombreAnneeAnciennete();
    }

    @Step("J'obtiens <ancienneteCalculee>")
    public void verifAnciennete(Integer ancienneteCalculee) {
        Assertions.assertThat(nbAnneeAnciennete).isEqualTo(ancienneteCalculee);
    }

    @Step("Lorsque je calcule sa prime annuelle")
    public void getPrimeAnnuelle() {
        primeAnnuelleEmploye = employe.getPrimeAnnuelle();
    }

    @Step("J'obtiens une prime annuelle de <1700>")
    public void verifPrimeAnnuuelle(Double primeCalculee) {
        Assertions.assertThat(primeAnnuelleEmploye).isEqualTo(primeCalculee);
    }

    @Step("avec un chiffre d'affaire de <200> euros")
    public void employeChiffreAffaire(Long caTraite2) {
        caTraite = caTraite2;
    }

    @Step("avec un objectif fixé de <1000> euros")
    public void employeObjectifCa(Long objectifCa2) {
        objectifCa = objectifCa2;
    }

    @Step("avec une performance initiale de <2>")
    public void employePerformanceInitiale(Integer performanceInitiale) {
        employe.setPerformance(performanceInitiale);
    }

    @Step("avec une performance de base de <1>")
    public void employePerformanceBase(Integer performanceBase) {
        performanceBase = Entreprise.PERFORMANCE_BASE;
    }

    @Step("Lorsque je calcule sa nouvelle performance")
    public void calculPerformanceCommercial() {
        performanceCalculee = employe.getPerformance();
    }

    @Step("J'obtiens une performance de <1>")
    public void verifPrimeAnnuuelle(Integer performanceVoulue) {
        Assertions.assertThat(performanceCalculee).isEqualTo(performanceVoulue);
    }
}
