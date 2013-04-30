Echipa: Rage Quitters
Seria CA:
Membrii: 	Tirnacop Flavius 	321CA (Capitan)
			Gosu Irina			321CA
			Soare Ion Mihai		321CA
			Popescu Laurentiu 	323CA

Etapa 0:

Botul folseste un algorim simplu ce consta in:
- calculul drumului cel mai lung (deci cel mai avantajos) din punctul in care se
 afla in acel moment.
- deci pentru toate cele 4 directii (din care una va avea cost 0) calculeaza
 distanta pana la urmatorul perete sau coada de jucator

Solutie pentru managementul surselor:
- Folosim un repo privat pe Github.com

Informatii suplimentare:
- ca mod de dezvoltare folosim Eclipse deoarece lucram in Java
- pentru prima etapa am abordat un algoritm foarte simplu care reuseste sa bata
 cei doi boti Explorer si Random.

Solutia trimisa:
https://www.hackerrank.com/contests/bucharest-tron/submissions/game/623530/page/1

Linkuri Jocuri:

Vs RandomBot - castiga toate meciurile

https://www.hackerrank.com/showgame/2123213
https://www.hackerrank.com/showgame/2123215
https://www.hackerrank.com/showgame/2123222
https://www.hackerrank.com/showgame/2123224
https://www.hackerrank.com/showgame/2123226
https://www.hackerrank.com/showgame/2123228
https://www.hackerrank.com/showgame/2123232
https://www.hackerrank.com/showgame/2123233
https://www.hackerrank.com/showgame/2123206
https://www.hackerrank.com/showgame/2123209
https://www.hackerrank.com/showgame/2123211
https://www.hackerrank.com/showgame/2123216
https://www.hackerrank.com/showgame/2123218
https://www.hackerrank.com/showgame/2123220
https://www.hackerrank.com/showgame/2123230

Vs Explorer - pierde doar 3 meciuri

https://www.hackerrank.com/showgame/2123236
https://www.hackerrank.com/showgame/2123237
https://www.hackerrank.com/showgame/2123238
https://www.hackerrank.com/showgame/2123242
https://www.hackerrank.com/showgame/2123243
https://www.hackerrank.com/showgame/2123244
https://www.hackerrank.com/showgame/2123245
https://www.hackerrank.com/showgame/2123247
https://www.hackerrank.com/showgame/2123248
https://www.hackerrank.com/showgame/2123250
https://www.hackerrank.com/showgame/2123235
https://www.hackerrank.com/showgame/2123239
https://www.hackerrank.com/showgame/2123240
https://www.hackerrank.com/showgame/2123241
https://www.hackerrank.com/showgame/2123246
https://www.hackerrank.com/showgame/2123215
https://www.hackerrank.com/showgame/2123236

Etapa 2:

	Botul foloseste un algoritm de tip alpha-beta pruning pentru a calcula pentru 
fiecare mutare posibila a botului, care este cea mai eficienta.La acest algoritm se 
mai adauga o cautare de tip BFS bidirectionala ce ajuta la calculul ariei maximale 
in momentul respectiv.
	Ideea botului este aceea de a calcula, in functie de posibilele mutari,  
daca suprafata disponibila este mai mare decat cea a adversarului. 

	Ne-am gandit ca, pentru a fi eficient, la inceput botul se duce spre 
adversar. In functie de punctele de articulatie intalnite pe parcurs si de 
numarul de puncte de articulatie disponibile in "camera" in care acesta se 
afla, el ar trebui sa blocheze anumite iesiri pentru a micsora radical suprafata 
disponibila a botului adversar. 

	Pentru aceasta, trebuie calculat daca botul nostru poate ajunge la acel 
punct de articulatie inaintea botului adversar (care probabil va incerca sa 
blocheze si el punctul de articulatie respectiv pentru a-i limita botului nostru 
suprafata disponibila si deci, sansele de castig).

	In cazul in care botul adversar i-ar taia calea botului nostru si ar reusi 
sa il inchida intr-o camera, atunci ne-am gandit ca cea mai eficienta varianta 
ar fi un bot de tip explorer, care umple eficient spatiul pe care il are la 
dispozitie pentru a castiga timp.(implementat metoda survival)

	Pentru aceasta etapa a proiectului, am hotarat cu totii ca aceasta este 
cea mai buna metoda de abordare a problemei, iar contributiile concrete ale 
fiecarui membru sunt urmatoarele:

	- Flavius Tirnacop: a implementat strategia pentru construirea unui graf din 
	matricea hartii pe baza caruia se poate realiza calcularea unei mutari 
	eficiente.A implementat metoda care face un BFS bidirectional pornind din 
	cele doua capete ale playerilor pentru a afla care este in avantaj.

	- Irina Gosu: a implementat metode care calculeaza punctele de articulatie 
	dintr-o camera in care se afla un bot si realizeaza umplerea eficienta a 
	unui spatiu disponibil atunci cand botul este "inchis" intr-o camera de 
	catre adversar.

	- Mihai Soare: a implementat metoda de alpha-beta pruning care calculeaza 
	costul unei mutari pentru a putea determina mutarea cea mai eficienta.

	- Laurentiu Popescu: a facut research pentru a determina maniera in care ar 
	trebui sa mute un bot pentru a functiona eficient.

	Pentru mai multe detalii check github. 

	Momentan nu am reusit sa impelmentam corect functia de alpha beta asa ca
	am decis sa ramanem la varianta de backup doar cu BFS bidirectional.
	
	Rage_Quitters vs. stage2bot: 

	https://www.hackerrank.com/contests/bucharest-tron/submissions/game/632874/page/1

	https://www.hackerrank.com/showgame/2267484
	https://www.hackerrank.com/showgame/2267477
	https://www.hackerrank.com/showgame/2267483
	https://www.hackerrank.com/showgame/2267487
	https://www.hackerrank.com/showgame/2267488
	https://www.hackerrank.com/showgame/2267489
	https://www.hackerrank.com/showgame/2267492
	https://www.hackerrank.com/showgame/2267493
	https://www.hackerrank.com/showgame/2267478
	https://www.hackerrank.com/showgame/2267481
	https://www.hackerrank.com/showgame/2267485
	https://www.hackerrank.com/showgame/2267490
	https://www.hackerrank.com/showgame/2267491
	https://www.hackerrank.com/showgame/2267494
	https://www.hackerrank.com/showgame/2267495
	https://www.hackerrank.com/showgame/2267486