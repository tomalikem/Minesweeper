package minesweeper.resources

object Strings {
  val title = "Minesweeper"

  val ok = "OK"

  val configurationPrompt = "Wybierz ustawienia początkowe:"

  val beginnerConfiguration = "Początkujący"
  val intermediateConfiguration = "Średnio zaawansowany"
  val expertConfiguration = "Ekspert"
  val nonstandardConfiguration = "Gra niestandardowa"

  val boardWidth = "Szerokość"
  val boardHeight = "Wysokość"
  val bombCount = "Liczba bomb"
  val time = "Czas"

  val errorDialogTitle = "Błąd"
  val noInitialConfigurationError = "Nie wybrano ustawień początkowych."
  val negativeBoardSizeError = "Rozmiar planszy nie może być ujemny."
  val negativeBombCountError = "Liczba bomb nie może być ujemna."
  val bombCountOverflowError = "Liczba bomb nie może przekraczać liczby pól."

  val victoryTitle = "Gratulacje!"
  val victoryText = "Ocaliłeś miasto!"

  val failureTitle = "Koniec gry"
  val failureText = "Niestety, saper myli się tylko raz."
}
