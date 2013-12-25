Projet sous licence GLP v2 - 2013

Auteurs : 
Caron Pierre-Édouard
Thorez Alexandre
Huitelec Fabien

Démarré en Décembre 2013 dans le cadre d'un projet collaboratif édité par Monsieur Jean Martinet au sein de la matière AP5 du DUT (IUT A) informatique de Lille 1 (S3 - groupe N).

Indications développement :

JAVADOC : 	Dans les VM options de génération javadoc ajoutez pour éviter tout problème d'accentuation et pour éviter tout warning concernant le tag @category:
				-encoding utf8 -docencoding utf8 -charset utf8 -tag category	
JAVADOC : 	Dans les Extra Javadoc options ajoutez : controler main model ressources view
			Ca vous permettra de n'avoir qu'à cliquer sur finish et vous fera gagner du temps dans la génération de javadoc
JAVADOC : 	Pour ne pas voir la doc dans le projet sous eclipse, allez dans les propriétés du projet puis Resource/Resource Filters
			puis ajoutez un filtre qui exclue tous les fichiers et dossier dont le nom correspond à "doc" et un deuxième correspondant à "package-info*"
JAVADOC :	Les fonctions inutilisées sont marqués dans les commentaires javadoc avec le tag @deprecated. 

Démarrage du logiciel :
Outil par défaut : droite
couleur par défaut : noir,
type par défaut : plein.

Enregistrement :
L'utilisateur peut enregistrer son fichier. L'extension est .cth. Le nom de fichier est préselectionné, de sorte que l'utilisateur peut directement écrire le nom du fichier et appuyer sur la touche "Entrée" ou le bouton "enregistrer".
Si l'utilisateur entre directement son nom de fichier sans extension, elle est tout de même rajoutée lors de l'enregistrement.
Il est notifié à l'utilisateur si le fichier existe déjà et lui demande confirmation avant de l'écraser.
Si l'utilisateur enregistre, il ne lui est pas demandé en quittant d'enregistrer.
De même, si entre l'enregistrement et la fermeture, il crée d'autres formes, il lui est demandé s'il souhaite réenregistrer.

Fonctionnement général :
Lorsqu'une forme est ajoutée, l'arrayList du modèle est modifiée : une forme lui est ajoutée