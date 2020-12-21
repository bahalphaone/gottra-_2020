# coding: utf-8

from __future__ import print_function
from datetime import date, datetime, timedelta
import csv
import nltk
import re
import sys
import time
import os
from enum import Enum
from difflib import SequenceMatcher
import os.path
from os.path import basename
import mysql.connector
import treetaggerwrapper as tt

import applicationconfig
config = applicationconfig.dbConfig


def add_case(name, title, ID_fichier):
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

def case_exists(ID_cas, titre):
    cnx = mysql.connector.connect(**config)
    cursor = cnx.cursor()
    query = ("SELECT ID_cas FROM usecase WHERE ID_cas = %s AND titre = %s")
    cursor.execute(query, (ID_cas, titre))
    ID = None
    for ID in cursor:
        break;
    cursor.close()
    cnx.close()
    return ID

def update_case(ID_cas, ID_acteur):
    cnx = mysql.connector.connect(**config)
    cursor = cnx.cursor()
    query = ("UPDATE usecase SET ID_acteur = (%s) WHERE ID_cas = (%s)")
    cursor.execute(query, (ID_acteur, ID_cas))
    cnx.commit()
    cursor.close()
    cnx.close()

def similar(a, b):
    return SequenceMatcher(None, a, b).ratio()

def get_verb(sentence):
    tt_fr = tt.TreeTagger(TAGLANG='fr', TAGDIR='TreeTagger')
    tags = tt_fr.TagText(sentence)
    print(sentence)
    for word in tags:
     	if(len(word.split("\t")) > 1):
            POS = word.split("\t")[1]
            word = word.split("\t")[2]
            if("VER" in POS):
                with open(os.path.dirname(__file__) +'/../reference/verbes_cs.txt') as actions:
                    print("verbe: " + word)
                    word_ = word.upper().replace(' ', '')
                    sims = [(similar(word_, action), action) for action in actions]
                    res = max(sims)
                    if(res[0] > 0.8):
                        print(word_ + ' est similaire ?' + res[1] + "Score: " + str(res[0]) + "\n")
                        return word
    return ''

def get_action_output(sentence):
    split = sentence.split(",")
    if(len(split) > 1):
        action = split[:len(split)-1]
        action = ', '.join(split[:len(split)-1])
        output = str(split[len(split)-1])
    elif(get_verb(sentence)):
        action = sentence[:sentence.find(get_verb(sentence))]
        output = sentence[sentence.find(get_verb(sentence)):]
    else:
        action = sentence
        output = ""
    return (action, output)

def alimenter_bdd(file, specType, ID_fichier):
    start_time = time.time()
    regex_cas = re.compile(r'[a-zA-Z]+\d+-\d+')
    with open(file, encoding = "UTF-8") as f:
        action = False
        rgestion = False
        rgestion_text = ''
        for sentence in f:
            sentence = sentence.strip()
            if(action):
                rgestion_text = ''
                if( (sentence == "\n") | (sentence == "") ):
                    action = False
                else:
                    action, output = get_action_output(sentence)
                    add_action(action, output, ID_cas, sentence)
            elif(rgestion):
                if( (sentence == "\n") | (sentence == "") ):
                    rgestion = False
                    add_rule(rgestion_text, ID_cas)
                else:
                    rgestion_text = rgestion_text  + sentence
            else:
                rgestion_text = ''
                if(regex_cas.search(sentence)):
                    position = regex_cas.search(sentence).span()
                    name_cas = sentence[position[0]:position[1]].strip()
                    title_cas = sentence[position[1]:None].strip()
                    ID_cas = add_case(name_cas, title_cas, ID_fichier)
                    print("added case : " + str(ID_cas))
                elif(sentence.lower().startswith('acteur : ')):
                    sentence = sentence.strip()[9:None]
                    for actor_ in sentence.split('/'):
                        for actor in actor_.split('&'):
                            actor = actor.strip()
                            ID_acteur = actor_exists(actor)
                            if(ID_acteur == None):
                                ID_acteur = add_actor(actor)
                            else:
                                ID_acteur = ID_acteur[0]
                            update_case(ID_cas, ID_acteur)
                elif('actions :' in sentence.lower()):
                    action = True

                elif('règles de gestion :' in sentence.lower()):
                    rgestion = True

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
    
alimenter_bdd(specFile, specType, ID_fichier)