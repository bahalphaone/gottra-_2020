DESCRIPTION DES DOSSIERS: 

Data Preprocessing:
Contient tous les scripts permettant de générer les différents datasets avec les différentes features. 

Classification:
Contient 5 notebooks au total:
    - Trois notebooks correspondent aux techniques d'apprentissage testées : SVM.ipynb, neural network.ipynb, et naive bayes.ipynb. 
    - comparaison.ipynb est, lui, utilisé afin de générer les cas de test qui sont dans le dossier Ressources/Test Cases/ en suivant la même structure des cas de tests générés manuellement par l'expert. Ceci afin de pouvoir comparer ces derniers avec les résultats qu'on a eu.
    - save model.ipynb contient le script qui permet d'enregistrer un modèle donné. 
Contient aussi le dossier Models où sont stockés les modèles précédemment entraînés.

Prediction:
Contient le notebook prediction function.ipynb permettant de classifier des nouveaux documents et insérer les résultats dans la base de données en utilisant un des modèles stockés dans le dossier Classification/Models/. Ce script est celui utilisé dans l'application web.

Ressources:
Contient tous les documents utilisés dans le projet.

Autre: 
Contient deux notebooks qui ne sont pas utilisés dans mon stage mais pouvant être exploités dans le projet: 
    - parse tree.ipynb contient des scripts faisant des parse tree et pouvant être exploités pour la séléction des variables.
    - visualization.ipynb contient des scripts de visualisation des données.
    
    
NOTES:
    - Pour mieux comprendre le projet, commencer par le dossier Data preprocessing/ pour comprendre la façon dont les datasets ont été générés. Ensuite, enchaîner avec le dossier Classification/ où les techniques d'apprentissage ont été testés sur les datasets générés. Le modèle le plus optimal ou n'importe quel autre modèle peut être utilisé pour classifier de nouveaux datasets dans le dossier Prediction/. Pour les plus curieux, regarder aussi le dossier Autre. 
    - Les datasets ont été générérs une fois pour toute, afin de pouvoir comparer les techniques d'apprentissage proprement. La regénération des datasets entraînera un changement des résultats des modèles d'apprentissage.
    - La génération des datasets a été faite en séparant le dataset d'apprentissage et celui de testing à l'avance.