import csv
from sklearn.externals import joblib
import pandas as pd
import os
from os.path import basename
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.feature_extraction.text import TfidfTransformer
import nltk
import string
from nltk.corpus import stopwords
from enum import Enum
import sys
import os
import mysql.connector


import applicationconfig
config = applicationconfig.dbConfig

def add_case(name, title, ID_fichier):
    print("je suis dans add case");
    cnx = mysql.connector.connect(**config)
    cursor = cnx.cursor()
    query = ("INSERT INTO usecase (nom, titre, id_fichier) VALUES (%s, %s, %s)")        
    cursor.execute(query, (name, title, ID_fichier))
    id = cursor.lastrowid    
    cnx.commit()
    cursor.close()
    cnx.close()
    return id

def add_actor(acteur):
    cnx = mysql.connector.connect(**config)
    cursor = cnx.cursor()
    query = ("INSERT INTO acteur (nom) VALUES (%s)")
    cursor.execute(query, (acteur,))
    ID_acteur = cursor.lastrowid
    cnx.commit()
    cursor.close()
    cnx.close()
    return ID_acteur

def add_action(action, output, ID_cas, full_text):
    cnx = mysql.connector.connect(**config)
    cursor = cnx.cursor()
    query = ("INSERT INTO action (ID_cas, action, output, full_text) VALUES (%s, %s, %s, %s)")
    cursor.execute(query, (ID_cas, action, output, full_text))
    cnx.commit()
    cursor.close()
    cnx.close()

def add_rule(text, ID_cas):
    cnx = mysql.connector.connect(**config)
    cursor = cnx.cursor()
    query = ("INSERT INTO rgestion (ID_cas, texte) VALUES (%s, %s)")
    cursor.execute(query, (ID_cas, text))
    cnx.commit()
    cursor.close()
    cnx.close()

def actor_exists(acteur):
    cnx = mysql.connector.connect(**config)
    cursor = cnx.cursor()
    query = ("SELECT ID_acteur FROM acteur WHERE nom = %s")
    cursor.execute(query, (acteur,))
    ID = None
    for ID in cursor:
        break;
    cursor.close()
    cnx.close()    
    return ID

#fonction prenant en paramètre un fichier texte pour le classifier selon le modèle standard
def predict(file, specType, ID_fichier):
    #transformation du texte en dataset à introduire au modèle
    filename = os.path.splitext(file)[0]
    cwd = os.getcwd().replace('\\', '/')
    with open(file, 'r', encoding="UTF-8") as f, open(cwd + '/python/files/' + basename(filename) + '.csv', 'w+', newline = '' , encoding="UTF-8") as csvfile:
        text = ''
        writer = csv.writer(csvfile)
        for sentence in f:
            if( (sentence == '\n') | (not sentence) ):
                if(text):
                    writer.writerow([text])
                text = ''
            else:
                text = text + sentence
    #application du modèle d'apprentissage
    text_to_predict = pd.read_csv(cwd + '/python/files/' + basename(filename) + '.csv', encoding="UTF-8")
    print("afficher text_to_predict")
    print(text_to_predict)
    text_to_predict = text_to_predict[text_to_predict.columns[0]]
    clf = joblib.load(cwd + '/python/model/svm_bow_without_stopwords.pkl')
    result = clf.predict(text_to_predict)
    i = 0
    j = 0
    print("afficher result")
    print(text_to_predict);
    for paragraph in text_to_predict:
        print("result[i]");
        print(result[i]);
        if(result[i] == "gestion"):
            add_rule(paragraph, j)
        elif(result[i] == "usecase"):
            j = add_case("", paragraph, 1)
        elif(result[i] == "acteur"):
            if(paragraph.lower().startswith('acteur : ')):
                    paragraph = paragraph.strip()[9:None]
                    add_rule(paragraph, j)
            if(not actor_exists(paragraph)):
                add_actor(paragraph)
        elif(result[i] == "action"):
            add_action(paragraph, "", j, paragraph)
        else:
            j = add_case("", paragraph, 1)
        i = i + 1
    
    return result

class SpecType(Enum):
    agile = "agile"
    standard = "standard"

if len(sys.argv) < 3:
    print("Usage: %s <filePath> <agile or standard> <ID_fichier>")
    sys.exit()
        
specFile = sys.argv[1]

try:
    specTypeArg = sys.argv[2]
    specType = SpecType[specTypeArg.lower()]
except KeyError:
    print ("type found: %s, must be in %s" % (specTypeArg, str(list(SpecType))))
    sys.exit()

ID_fichier = sys.argv[3]
print(specFile)
result = predict(specFile, specType, ID_fichier)

print(result)
