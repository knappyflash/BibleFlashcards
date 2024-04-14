import java.text.Format

class Passage(var PassageIndex: String, var Passage: String) {

    var IncludePassage: Boolean = false
    public var CorrectGuesses: Long = 0
    public var TotalGuesses: Long = 0
    public var CorrectPercent: Double = 0.00

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