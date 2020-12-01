# VINKKIKIRJASTO
![GitHub Actions](https://github.com/Miniaya/lukuvinkkikirjasto/workflows/Java%20CI%20with%20Gradle/badge.svg)
[![codecov](https://codecov.io/gh/Miniaya/lukuvinkkikirjasto/branch/main/graph/badge.svg?token=M85UDMO56Y)](https://codecov.io/gh/Miniaya/lukuvinkkikirjasto)

Tämä on Tiimi Viiden komentorivipohjainen vinkkikirjastosovellus OHTU syksy 2020 kurssia varten.

Löydät lisämateriaalit ja käyttöohjeen alta.

## LINKIT
- [Backlog & Sprintlogit](https://docs.google.com/spreadsheets/d/1XuvgQQRyYOgVvAYmBFQm1ab_-g5Kg-24XLIuX7o79t4/)
- [Definition of Done](https://github.com/Miniaya/lukuvinkkikirjasto/blob/main/Vinkkikirjasto/docs/DOD.md)
- [GitHub Actions](https://github.com/Miniaya/lukuvinkkikirjasto/actions)

## KÄYTTÖOHJE
Asenna sovellus [release](https://github.com/Miniaya/lukuvinkkikirjasto/releases)-sivulta.

Pura tiedosto.

Siirry kansioon ja käynnistä sovellus komentoriviltä komennolla:
- `./gradlew run --console=plain` tai `gradle run`


## KOMENNOT
- `help` - listaa kaikki komennot

Tällä komennolla voit nähdä kaikki mahdolliset toiminnot.

- `listaa` - listaa kaikki vinkit

Näet kaikki sovellukseen lisätyt vinkit. Kirjoissa näet kirjan nimen, kirjoittajan, sivumäärän ja lukemasi prosentin. Artikkeleista näet nimen ja URLin.

- `muokkaa` - päivitä vinkkiä

Tällä hetkellä voit lisätä kirjavinkkeihin luetun sivumäärän ja päivittää sitä.

- `poista` - poista vinkki

Voit poistaa vinkin kirjastosta.

- `sulje` - sulje sovellus

Sulkee sovelluksen. Kaikki antamasi vinkit säilyvät sovelluksessa.

- `uusi` - lisää uusi vinkki, joko kirja tai artikkeli

Lisää uusi vinkki kirjastoon. Spesifioi, haluatko lisätä kirjan vai artikkelin, ja anna sitten pyydetty info.
