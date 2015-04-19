# Maszyna W - wersja www
Projekt BDII/PP

# Maszyna W - implementowane funkcje:
- lista rozkazów
  - zalogowany użytkownik tworzy swoje archiwum rozkazów, do którego może dodawać swoje rozkazy lub utworzone przez kogoś innego
  - tworzone przez użytkownika rozkazy są widoczne publicznie
  - gość ma dostęp jedynie do kilku podstawowych rozkazów
- zmienne
- konsola wejścia/wyjścia
- zmiana liczby bitów adresowych i kodu
- zmiana adresów przerwań oraz urządzeń I/O
- składniki:
  - podstawowa maszyna W
  - W+ 
    - połączenie międzymagistralowe
  - L 
    - W+
    - inkrementacja i dekrementacja akumulatora
    - operacje logiczne w JAL
    - rozszerzone operacje logiczne w JAL
    - stos
    - rejestr X i Y
  - EW
    - W+
    - L
    - wejście/wyjście
    - dodatkowe znaczniki
- sterowanie ręczne
- poziomy śledzenia:
  - niski - program
  - średni - rozkaz
  - wysoki - takt
- widok edycji programu pod maszyną W (względnie obok maszyny W)
- za pomocą skrótów klawiszowych wykonanie:
  - jednego taktu
  - jednego rozkazu
  - całego programu
  - do kursora
- reset maszyny W
- załadowanie wybranych rozkazów z biblioteki zalogowanego użytkownika
- załadowanie wybranego programu z biblioteki zalogowanego użytkownika

# Aplikacja składać się będzie z czterech modułów:
- maszyna W - pojawiająca się na stronie głównej, ogólnodostępna dla każdego
- forum - dla wszystkich użytkowników (zalogowanych i nie). Jednakże edycja i dodawanie postów i tematów będzie dostępne jedynie dla zalogowanych użytkowników. Dodatkowe uprawnienia posiada administrator
- moduł z programami i rozkazami tworzonymi przez użytkowników
- księga gości - prosta lista wyświetlana na dole strony z datą, nazwą gościa i jego wpisem

Użytkownicy będą mieli możliwość tworzenia osobistych bibliotek, w których znajdować się będą rozkazy oraz programy, które sami stworzą albo które zostaną przez nich zapisane (z forum).
Wizualizacja maszyny W zostanie utworzona w formacie SVG. Użytkownik za pomocą menu będzie wskazywał którą z dostępnych jej wersji będzie chciał wykorzystać (W, W+, L, EW). Ze względu na zaawansowanie projektu postanowiliśmy nie realizować układu przerwań. 

# Skróty klawiszowe
- F2 - Symulator
- F3 - Forum
- F7 - takt
- Ctrl+Shift+1 - logowanie
- Ctrl+Shift+2 - Rejestracja
