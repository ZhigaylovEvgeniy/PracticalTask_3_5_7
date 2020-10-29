public class TooLongAnalyzer implements TextAnalyzer{
    private int maxLength;

    public TooLongAnalyzer(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public Label processText(String text) {
        if (text.length() > maxLength){
            return Label.TOO_LONG;
        }
        return null;
    }
}
