# Rapport – innlevering 3

**Team:** *Teamnavn* – *medlemmer*...

Teknisk beskrivelse (TIL README)
“kort beskrivelse av spillet og hvordan det brukes (f.eks. hvilke tastetrykk som gjør hva).”

Spillet top down 2d spill, der man beveger spilleren enten ved bruk WASD eller piltastene for å navigere et
labyrint-lignende kart. Kartet har hindringer i form av steiner som kan dyttes, og en stadig stigende lava. Dersom
lavaen treffer spilleren dør man, og får game over.

Prosjekt rapport (oblig 3)

# Rapport – innlevering 3

## Prosjektrapport

**Hvordan fungerer rollene i teamet? Trenger dere å oppdatere hvem som er teamlead eller kundekontakt?
Trenger dere andre roller? Skriv ned noen linjer om hva de ulike rollene faktisk innebærer for dere.**

- Rollene fungerer bra.
- Sander har gått over til rolle som kunstner til prosjektet.

**Er det noen erfaringer enten team-messig eller mtp prosjektmetodikk som er verdt å nevne? Synes teamet at de valgene
dere har tatt er gode? Hvis ikke, hva kan dere gjøre annerledes for å forbedre måten teamet fungerer på?**
Vi er generelt sett fornøyde med prosjektmetodikken. Vi har tenkt på om vi skal gjøre mer parprogrammering.

**Hvordan er gruppedynamikken? Er det uenigheter som bør løses?**

- Den er grei! woohoo!

**Hvordan fungerer kommunikasjonen for dere?**

- Det har fungert ganske greit, men det var en gang det var litt lite kommunikasjon ifht om vi skulle møte på en fysisk
  time som var blitt digital før ferien.

**Gjør et kort retrospektiv hvor dere vurderer hva dere har klart til nå, og hva som kan forbedres. Dette skal handle om
prosjektstruktur, ikke kode. Dere kan selvsagt diskutere kode, men dette handler ikke om feilretting, men om hvordan man
jobber og kommuniserer.**

- Til nå har vi, spesielt Ine vært flink til å organisere bruk av trello. Dette har gjort det lett for oss andre å se
  hva som manglet og måtte bli gjort.

**Under vurdering vil det vektlegges at alle bidrar til kodebasen. Hvis det er stor forskjell i hvem som committer, må
dere legge ved en kort forklaring for hvorfor det er sånn. Husk å committe alt. (Også designfiler)**

- Det er en stor forskjell i mengden comits på grunn av blant annet at Sander er helt ny til Java og har tidligere bare
  brukt Python, og er også ny til git, han har derfor gått over til å ta ansvar for design.

**Referat fra møter siden forrige leveranse skal legges ved (mange av punktene over er typisk ting som havner i
referat):**
https://docs.google.com/document/d/10a05Fu1_dx0Y_XaLF6ZEFuFKvsOZK4iNWEVNr36HerU/edit

**Bli enige om maks tre forbedringspunkter fra retrospektivet, som skal følges opp under neste sprint.**

- Skrive mer tester
- Mer dokumentasjon

## Krav og spesifikasjon

**Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang. Er dere
kommet forbi MVP? Forklar hvordan dere prioriterer ny funksjonalitet.**

- Siden sist har vi fullført mvp. Siden forrige gang har vi blant annet implementert: en spiller som kan dytte steiner.
  Lava som gradvis stiger. Et kart som bestemmer hvor man kan gå. “Fog of war”. Title, game, win og gameOver screens.
  Mer presise kontroller. Pom.xml er oppdatert. Bedre dokumentasjon. (I oblig1 skrev vi at vi planla en mulighet for å
  samle poeng, men vi har gått vekk fra dette)

**For hvert krav dere jobber med, må dere lage 1) ordentlige brukerhistorier, 2) akseptansekriterier og 3)
arbeidsoppgaver. Husk at akseptansekriterier ofte skrives mer eller mindre som tester**

1. Som en spiller ønsker jeg å se et spillebrett, slik at jeg kan forstå spillområdet.
    - Akseptanskriterier:
        - Et kart som viser et spillområde man kan bevege seg på
    - Arbeidoppgaver:
        - Designe et kart
        - implementere kartleser,
        - vise kartet til spiller slik at de kan “interacte” med det.

2. Som en spiller ønsker jeg å se min karakter på spillebrettet, slik at jeg kan identifisere posisjonen min.
    - Akseptanskriterier:
        - En synlig figur som vises på kartet.
    - Arbeidsoppgaver:
        - Skaffe/produsere kunst til spiller.
        - Implementere Player klassen.

3. Som en spiller ønsker jeg å kunne flytte karakteren min ved hjelp av tastene eller lignende, slik at jeg kan utforske
   spillebrettet.
    - Akseptanskriterier:
        - Spiller figuren beveger seg når spilleren trykker w/opp, a/venstre, s/ned eller d/høyre.
    - Arbeidoppgaver:
        - Implementere en Input leser
        - Funksjon som tolker inputs
        - Funksjon som

4. Som en spiller ønsker jeg å kunne interagere med terreng på spillebrettet, slik at jeg kan oppleve ulike utfordringer
   og belønninger.
    - Akseptanskriterier:
        - Når spilleren går inn i en rekke med to eller mindre steiner, skal steinene dyttes, så lenge det er plass til
          det.
        - Når spilleren prøver å gå inn i en vegg, skal den ikke bevege seg.
    - Arbeidsoppgaver:
        - Implementere handlinger mellom Player, Map og Entity i World objektets moveEntity metode.

6. Som en spiller ønsker jeg å se lava på spillebrettet, og den skal påvirke karakteren min, slik at spillet blir
   utfordrende.
    - Akseptanskriterier:
        - Lava som stiger og kan reagere med spilleren (spilleren dør)
    - Arbeidsoppgaver:
        - Klasse som styrer lava hastighet, høyde osv
        - Funksjon som tegner lava
        - Noe som sjekker om spilleren er borti lavaen


7. Som en spiller ønsker jeg å oppleve muligheten til å dø ved kontakt med lava, for å gi en konsekvens ved feil
   handlinger.
    - Akseptanskriterier:
        - Hvis spilleren treffer lavaen blir spilleren møtt med en game-over skjerm
    - Arbeidsoppgaver:
        - Game over skjerm som vises når man dør
        - Noe som sjekker om spilleren er borti lavaen


8. Som en spiller ønsker jeg å ha et mål plassert øverst på spillebrettet, slik at jeg har noe å jobbe mot.
    - Akseptanskriterier:
        - En måte å vinne spillet på
    - Arbeidsoppgaver:
        - Funksjon som håndterer win conditions
        - Win screen


10. Som en spiller ønsker jeg å se en startskjerm ved oppstart og en spill over-skjerm når jeg mislykkes, slik at jeg
    kan navigere mellom ulike spilltilstander.
    - Akseptanskriterier:
        - Title screen, game screen, win screen og game over screen.
    - Arbeidsoppgaver:
        - Implementere ulike skjermer ved å bruke ScreenManager.

**Dersom dere har oppgaver som dere skal til å starte med, hvor dere har oversikt over både brukerhistorie,
akseptansekriterier og arbeidsoppgaver, kan dere ta med disse i innleveringen også.**

Som en spiller ønsker jeg å ha lydeffekter
Akseptanskriterier
En lyd som spiller når man dytter en stein.
Arbeidsoppgaver
Importere og spille lyd når en stein blir dyttet.

Som en spiller ønsker jeg en form for tutorial eln tilsvarende,
Akseptanskriterier
En skjerm eller et overlay som viser kontroller
Arbeidsoppgaver

**Forklar kort hvordan dere har prioritert oppgavene fremover**

Fremover skal vi prioritere tester, lyd, bestemme hva vi skal ha som end-goal, klasse-diagram og power-up

**Har dere gjort justeringer på kravene som er med i MVP? Forklar i så fall hvorfor. Hvis det er gjort endringer i
rekkefølge utfra hva som er gitt fra kunde, hvorfor er dette gjort?**

Siden forrige innlevering har vi endret utfra brukerhistorien vår sitt ønske om å se/starte et nytt spillbrett. Vi
valgte å utvide vårt nåværende kart og la brukeren videre utforske

**Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang.**

Vi har prioritert å bli ferdig med mvp

**Husk å skrive hvilke bugs som finnes i de kravene dere har utført (dersom det finnes bugs).**

Man kan dytte steiner ut av kartet


