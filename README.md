Als je het project hebt gecloned, zie je in eerste instantie alleen de "master". Mbv intellij moet je de betreffende branch lokaal aanmaken.
Vergeet niet:
1) op de sprint<x>-branch een werkbranch te maken. In deze werkbranch werk je aan je project
2) alleen te pushen naar sprint<x>-branches

Voordat je pushed de volgende handelingen uitvoeren:
1) de wijzigingen in je werkbranch commiten;
2) de /origin/sprint<x> pullen (naar je lokale sprint<x>-branch);
3) in je werkbranch gaat staan. vervolgens git merge sprint<x>;
4) eventuele conflicten in je werkbranch oplossen;
5) checkout sprint<x>;
6) merge werkbranch;
7) push sprint<x>