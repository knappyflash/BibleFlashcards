import java.text.Format

class Passage(var PassageIndex: String, var IncludePassage: Boolean,
              var CorrectGuesses: Long, var TotalGuesses: Long, var Passage: String) {

    var CorrectPercent: Double = 0.00

    public fun Correct(){
        CorrectGuesses = CorrectGuesses + 1
        TotalGuesses=TotalGuesses + 1
    }

    public fun Wrong(){
        TotalGuesses=TotalGuesses + 1
    }

    public fun Get_TotalGuesses(): Long{
        return TotalGuesses
    }

    public fun Get_CorrectGuesses(): Long{
        return CorrectGuesses
    }

    public  fun Get_Correct_Ratio(): String {
        CorrectPercent = CorrectGuesses.toDouble()/TotalGuesses.toDouble() * 100
        if (CorrectPercent.isNaN()) {
            CorrectPercent = 0.00
        }
        return CorrectGuesses.toString() + " of " + TotalGuesses.toString() + " " + String.format("%.2f", CorrectPercent) + "%"
    }

}