Specification d'acceptation pour la gestion employes
====================================================
     
Nombre d'année d'ancienneté d'un employé embauché au cours de l'année
--------------------------------------------------------------------

* Soit un technicien
* embauché cette année
* Lorsque je calcule son nombre d'année d'ancienneté
* J'obtiens "0"

Nombre d'année d'ancienneté d'un employé embauché l'année dernière
------------------------------------------------------------------

* Soit un technicien
* embauché il y a "1" an(s)
* Lorsque je calcule son nombre d'année d'ancienneté
* J'obtiens "1"

Calcul prime annuelle pour un manager sans anciennete à plein temps sans performanace
-------------------------------------------------------------------------------------

* Soit un manager
* embauché cette année
* travaillant à plein temps
* avec une performance de "0"
* Lorsque je calcule sa prime annuelle
* J'obtiens une prime annuelle de "1700.0"

Calcul prime annuelle pour un manager sans anciennete à mi-temps sans performanace
----------------------------------------------------------------------------------

* Soit un manager
* embauché cette année
* travaillant à mi-temps
* avec une performance de "0"
* Lorsque je calcule sa prime annuelle
* J'obtiens une prime annuelle de "850.0"

Calcul prime annuelle pour un Manager à plein temps avec 5 années d'ancienneté sans performanace
------------------------------------------------------------------------------------------------

* Soit un manager
* embauché il y a "5" an(s)
* travaillant à plein temps
* avec une performance de "0"
* Lorsque je calcule sa prime annuelle
* J'obtiens une prime annuelle de "2200.0"

Calcul prime annuelle pour un Technicien à plein temps sans ancienneté sans performanace
----------------------------------------------------------------------------------------

* Soit un technicien
* embauché cette année
* travaillant à plein temps
* avec une performance de "0"
* Lorsque je calcule sa prime annuelle
* J'obtiens une prime annuelle de "300.0"

Calcul prime annuelle pour un Technicien à plein temps sans ancienneté avec performance 3
-----------------------------------------------------------------------------------------

* Soit un technicien
* embauché cette année
* travaillant à plein temps
* avec une performance de "3"
* Lorsque je calcule sa prime annuelle
* J'obtiens une prime annuelle de "3300.0"

Calcul performance pour un commercial dont le chiffre d'affaire est inférieur de plus de 20% à l'objectif fixé
--------------------------------------------------------------------------------------------------------------
* Soit un commercial
* avec un chiffre d'affaire de "200" euros
* avec un objectif fixé de "500" euros
* avec une performance initiale de "5"
* avec une performance de base de "1"
* Lorsque je calcule sa nouvelle performance
* J'obtiens une performance de "1"

Calcul performance pour un commercial dont le chiffre d'affaire est inférieur entre 20% et 5% par rapport à l'objectif fixé
---------------------------------------------------------------------------------------------------------------------------
* Soit un commercial
* avec un chiffre d'affaire de "900" euros
* avec un objectif fixé de "1000" euros
* avec une performance initiale de "2"
* avec une performance de base de "1"
* Lorsque je calcule sa nouvelle performance
* J'obtiens une performance de "1"

Calcul performance pour un commercial dont le chiffre d'affaire est supérieur de plus de 20%
--------------------------------------------------------------------------------------------
* Soit un commercial
* avec un chiffre d'affaire de "300" euros
* avec un objectif fixé de "240" euros
* avec une performance initiale de "2"
* avec une performance de base de "1"
* Lorsque je calcule sa nouvelle performance
* J'obtiens une performance de "6"
