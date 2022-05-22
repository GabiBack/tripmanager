# tripmanager


## Funkcjonalności
1. Strona początkowa - wpisanie nazwy użytkownika/loginu + wysyłka maila z linkiem potwierdzającym autentykacje 

Dodawanie użytkownika oraz przypisywanie do wycieczki - tylko przez admina. 

Dodany uprzednio do bazy użytkownik będzie mogl wpisac podana wcześniej nazwe i otrzymać na maila link z tokenem umożliwiający przejście do strony glownej – token jest wazny 15 minut

Przycisk uruchamia endpoint w klasie UserController:
GET: localhost:8080/user/{name}

Jeśli user nie istnieje - leci NullPointerException „user not found” - tu może być jakaś notyfikacja wyskakująca

Jeśli user istnieje - zostaje zwrócony w postaci JSON, na froncie powinien zostać wywołany redirect do endpointu: 
GET: localhost:8080/token/{userId}
(Tu jest generowany token, i wysyłany mail, 
w mailu znajduje się przycisk potwierdzający autentykacje użytkownika,
Po kliknięciu w link na mailu, następuje redirect na endpoint:
GET: localhost:8080/confirm
Endpoint zwraca boolean, jeśli autentykacja przebiegła poprawnie, użytkownik powinien zostać przeniesiony na str 2:  GET:localhost:8080/user/{id}, jeśli coś poszło nie tak, np token wygasł - user dostaje notyfikacje, ze autentykacja nie przebiegła poprawnie i zostaje przeniesiony na stronę 1. 

UWAGA - localhost:8080/confirm -  ten endpoint przekazuje dwa parametry w requestParam:
Token oraz userId:
http://localhost:8080/confirm?token=599b9be9-def7-4dd8-ae35-4577ddd8b09d&userId=1



2. Wyświetlanie podróży, w których bierze udział konkretny użytkownik 
3. Dodawanie propzycji podróży
4. Wyświetlanie "odliczania do podróży" 
5. Wyświetlanie listy użytkowników 
6. Wyświetlanie transportów
7. Dodawnaie transportów 
8. Dwie listy rozwijane - "to do" oraz "co zabrać" z możliwością dodania pozycji przez użytkownika 

NOTE: Każda funkcjonalność musi być osobnym komponentem!

## Przygotowanie środowiska

- Java 8 
- Maven 
- MySql + SqlWorkbench
- Vue.js 3
- Node.js
- git 
- IntelliJ wersja ultimate (dla studentów - darmowa licencja) lub dowolnie wybrane IDE 
- Połączenie z bazą danych należy skonfigurować dla każdego użytkownika lokalnie w pliku application.properties
