# Kata en Français
### Présentation
Le projet Kata a été implémenté en Java 8/Maven/Springboot 2.  

Ce projet fournit des managers pour l'implémentation des logiques métiers etc. le dépôt, le retrait et la consultation des opérations historiques.  

### Conception
1. Le projet Kata a été créé en Maven avec une seule module racine **Parent**. Les managers sont tous dans un premier temps inclus dans le module **Core**. Il est prévu d'ajouter d'autres modules Maven comme **Persistence**.
2. Chaque scénario métier est implémenté par opération composée d'une suite d'actions (étape). Il est facile à accrocher ou désaccrocher une ou plusiers actions selon le logique métier et à tester en TU. Il sera aussi facile à réaliser un nouveau scénario par exemple le virement.
3. Des Exceptions Business sont utilisées pour permettre au consommateur de managers à gérer de leur propre façon quand.
4. Des Runtime Exceptions sont mis au niveau **Action** qui force le contrôle des arguments lors la création des opérations.      
4. Les messages d'erreur/d'exception sont mis en properties. En cas d'erreur, ce sera le front end à choisir les messages à afficher selon le locale. 

### Etape suivant
1. Un module **Persistence** est prévu pour aller plus loins, ce qui a pour l'objectif de communiquer avec la base de données. Pour cette démo, la persistance se fait en mémoire avec **//fixme**.
2. Pour une solution la plus simple, il n'y a pas de gestion de devises pour l'instant.

### Lancement
Un simulateur de scénarios est mis en place dans demo.bank.kata.core.CoreApplicationTests.