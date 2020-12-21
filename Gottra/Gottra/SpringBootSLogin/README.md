# Installer le projet

## 1. mysql
- Installer WAMP
- Créer une base de données (choisir l'encodage: unicode utf8)
- Importer le contenu de la base depuis le fichier `reference/gottra.sql`

## 2. Spring Tool Suite
- Installer Spring Tool Suite (3.9.4)
- Importer le projet GIT depuis bitbucket
- Configurer les propriétés pour votre environnement :
    - dans le répertoire `src/main/resources/`, copier le fichier `application-default.template.properties` vers `application-default.properties` 
    - surcharger les propriétés propres à votre environnement (le fichier `application-default.properties` surcharge les propriétés du fichier `application.properties`)

- Tester: lancer l'application Spring Boot App et ouvrir http://localhost:8080 dans un navigateur.

## 3. python
- Installer python 3.x
- installer les modules nltk et mysql-connector-python
- python -m
```
py -m pip install -U pip setuptools
py -m pip install --upgrade pip setuptools wheel
py -m pip install joblib nltk mysql-connector-python panda pandas scipy scikit-learn==0.19.1
```
- Configurer les propriétés pour votre environnement :
    - dans le répertoire `python/`, copier le fichier `applicationconfig_template.py` vers `applicationconfig.py`
    - modifier les propriétés de fichier
- installer le tokenizer punkt (TODO mieux décrire) python -m nltk.download punkt
- tester (TODO mieux décrire)
- Pour Windows rechercher les "Alias d'execution d'application" puis désactivé ceux liés à Python

	
# Développer sur le projet

## Customizer bootstrap
Pour customizer le style de l'application, il peut-être nécessaire de customizer bootstrap. Ces modifications sont réalisées dans le projet node `src/main/resources/bootstrap-custom`, qui génèrent le fichier `src/main/resources/static/css/custombootstrap.min.css`.

La procédure est:

- installer Node.js
- installer les dépendances: 

```
cd src/main/resources/bootstrap-custom
npm install
```
- modifier le fichier `scss/custombootstrap.scss`
- générer la nouvelle version de bootstrap customizée : 

```
npm run dist
```

	