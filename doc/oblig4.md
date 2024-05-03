# Rapport – innlevering 4

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

- Den er bra. Vi har vert flinkere til å planlegge.

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

## Krav og spesifikasjon

**Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang. Er dere
kommet forbi MVP? Forklar hvordan dere prioriterer ny funksjonalitet.**

- Spillet er ferdig. Vi har en start, slutt, nye tegninger og lyd.
- Vi har lagt til tester for det meste som ikke bare er visuelt.

**For hvert krav dere jobber med, må dere lage 1) ordentlige brukerhistorier, 2) akseptansekriterier og 3)
arbeidsoppgaver. Husk at akseptansekriterier ofte skrives mer eller mindre som tester**

Dette er brukerhistorier for de store endringene vi har gjort siden sist innlevering.

1. Som en spiller vil jeg har lydeffekter i spillet for å få tydelig feedback på handlingene mine i spillet.
    * Akseptansekriterier:
        * Lydeffekter som spiller når karakteren flytter seg, dytter på en sten osv.
    * Arbeidsoppgaver:
        * Finne gratis lydeffekter. [freesound.org](https://freesound.org).
        * Spille av lydeffekter når visse handlinger skjer i koden.

2. Som en utvikler vil jeg har flere tester, slik at jeg bedre kan vite at programmet fungerer som jeg tror det skal.
    * Akseptansekriterier:
        * Tester som dekker 70% av logikken til spillet.
    * Arbeidsoppgaver:
        * Skrive tester til de forskjellige klassene i spillet som behandler logikk.


3. Som en spiller vil jeg har en hjelpe side, slik at jeg bedre kan vite hvordan jeg spiller spillet
    * Akseptansekriterier:
        * Hjelpe side som viser kontroller og forklarer spillet
    * Arbeidsoppgaver:
        * Lage en hjelpeside

**Har dere gjort justeringer på kravene som er med i MVP? Forklar i så fall hvorfor. Hvis det er gjort endringer i
rekkefølge utfra hva som er gitt fra kunde, hvorfor er dette gjort?**

Sist hadde vi en tanke om å tilfeldig laste in forskjellige kart, men vi har heller laget en større fast bane.
Dette er gjort fordi det er lettere å implementere på en måte som er gøy å spille.

**Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang.**

Vi har prioritert lyd og tester, og ble ferdige med det.

**Angående fabrikker**

Vi har ikke fabrikker i koden fordi vi ikke har sett noe særlig behov for dette slik som koden er nå.
Entity konstruktøren gir samme funksjon som en fabrikk, i den forstand at man kan lage en vilkårlig entity type basert
på en texture og noen flagg (Tile).
