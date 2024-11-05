
Brugere:
Users


Klasser:

User:
name, String
password, String
user_id, auto-increment

Wishlist:
wishlist_id, auto-increment
user_id
arraylist af wish(es) <Wish>

Wish:
id, auto-increment
navn, string
url, string
pris, int/float
Reservation, user_id (foreign key)

User stories:
Som bruger skal jeg kunne logge ind og se mine egne ønskerlister og de ønskelister, som jeg er medlem af/har adgang til.
Som bruger kan jeg oprette mig selv så jeg kan begynde at oprette ønskelister.
Som bruger skal jeg kunne tilføje ønskelister til forskellige begivenheder.
Som bruger skal jeg kunne tilføje ønsker til min egen personlige ønskeliste, så jeg kan dele med andre brugere
Som bruger skal jeg kunne opdatere i ønskerne på min egen personlige ønskeliste, så de afspejler ønskerne ved fx prisændringer
Som bruger skal jeg kunne slette ønsker på min egen personlige ønskeliste, så listen er opdateret altid
Som bruger skal jeg kunne tildele andre brugere adgang til at se og reservere ønsker på specifikke ønskelister, som hjeg har oprettet.
Som bruger skal jeg kunne reservere ønsker på andres personlige ønskeliste som jeg har fået adgang til (er medlem af), så de får nogle gaver og vi der er inviterede har et overblik over gaver der er frie og reserverede

Tech stories:
Vi skal have lavet CI ved brug af H2
Vi vil deploye til Azure med brug af principper for CD
Vi sætter databasen op i MySQL med JDCB i intellij


Endpoints:
login: (/)
Egen ønskeliste: /wishlist (hvis egen: vis ikke reserver, men rediger, slet og tilføj)
Rediger ønske: wishlist/{wish}/edit
Andres ønskeliste: /{username}/wishlist (hvis andres: vis reserver, men ikke slet og rediger)
Reserver andres ønsker: /{username}/wishlist/{wish} (kun hvis andres)


SQL:
(mellemtabel) Wishlist_item:
user id
ønske_id


LINKS:

Klassediagram: 
https://app.diagrams.net/?src=about#G1SaGnmTySzVKpXXoEmWxylyccAvrOrJ46#%7B%22pageId%22%3A%22C5RBs43oDa-KdzZeNtuy%22%7D