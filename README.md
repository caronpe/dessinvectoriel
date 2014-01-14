Projet sous licence GLP v2 - 2013

Auteurs : 
Caron Pierre-Édouard
Thorez Alexandre
Huitelec Fabien

Démarré en Décembre 2013 dans le cadre d'un projet collaboratif édité par Monsieur Jean Martinet au sein de la matière AP5 du DUT (IUT A) informatique de Lille 1 (S3 - groupe N).

Pour toutes indications à propos du développement : https://github.com/caronpe/dessinvectoriel/wiki


Démarrage du logiciel :
Outil par défaut : sélection,
couleur par défaut : noire,
type par défaut : plein,
extension : .cth.


Nouveau - raccourci Ctrl + N :
Vide la Liste de dessin de toutes ses formes et la zone de dessin est redessinée.


Ouvrir - raccourci Ctrl + O :
L'utilisateur choisi le fichier qu'il souhaite ouvrir. Si l'extension n'est pas la bonne, il en est notifié.
S'il n'a pas préalablement enregistré, il lui est demandé s'il souhaite le faire.


Enregistrement - raccourci Ctrl + S :
Le premier enregistrement se fait via une fenêtre comme suit : 
L'utilisateur peut enregistrer son fichier. L'extension est .cth. Le nom de fichier est préselectionné, de sorte que l'utilisateur peut directement écrire le nom du fichier et appuyer sur la touche "Entrée" ou le bouton "enregistrer".
Si l'utilisateur entre directement son nom de fichier sans extension, elle est tout de même rajoutée lors de l'enregistrement.
Il est notifié à l'utilisateur si le fichier existe déjà et lui demande confirmation avant de l'écraser.

Une fois enregistré, si l'utilisateur clique sur Enregistrer ou fait Ctrl + S, le fichier sera enregistré à la dernière adresse connue (celle du premier enregistrement donc).

Si l'utilisateur souhaite enregistrer à un autre emplacement, il cliquera sur Enregistrer Sous.


Quitter - raccourci Ctrl + Q :
Si l'utilisateur a enregistré, il ne lui est pas demandé en quittant d'enregistrer.
De même, si entre l'enregistrement et la fermeture, il crée d'autres formes, il lui est demandé s'il souhaite réenregistrer lorsqu'il quitte.


Fonctionnement général :
Lorsqu'une forme est ajoutée, l'arrayList du modèle (l'objet ListeDessin plus particulièrement) est modifiée : une forme lui est ajoutée.
Il y a 3 types de formes : le rectangle, le rond et le trait.


Sélection :
On peut sélection une forme en cliquant dessus. Des marqueurs de sélection sont dessinés pour notifier à l'utilisateur qu'une forme est sélectionnée. La zone de sélection d'un rectangle est le rectangle lui-même, celle d'un cercle est un rectangle de mêmes dimensions que le cercle lui-même. Celle d'un trait en revanche est une zone de 40 pixel de largeur et de la longueur du trait (Le théorème de Pythagore y a été utilisé avec succès).
On peut sélectionner plusieurs formes en cliquant sur Shift. De même si on a sélectionné une mauvaise forme, on peut cliquer dessus pour la déselectionner. Un clic dans le vide (sur la zone de dessin) déselectionne toutes les formes. De même, si on sélectionne une ou plusieurs formes et qu'on clique sur un outil, toutes les formes sont déselectionnées.
L'utilisateur peut modifier la couleur de la ou des formes sélectionnées. Ce changement de couleur ne modifie pas la couleur actuelle du modèle.


Déplacement :
Quand on clique sur une forme et qu'on la glisse, la forme est déplacée, la zone de dessin est rechargé chaque fois que le listener détecte un changement de position. Cela permets un déplacement de la forme fluide.


Suppression : 
La ou les formes sélectionnées peuvent être supprimées en appuyant sur la touche Suppr du clavier.