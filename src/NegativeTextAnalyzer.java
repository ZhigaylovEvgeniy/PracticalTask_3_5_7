import java.util.Arrays;

public class NegativeTextAnalyzer extends KeywordAnalyzer implements TextAnalyzer{
    String[] keywords;
    NegativeTextAnalyzer(){
        this.keywords = new String[]{":(", "=(", ":|"};
    }

    @Override
    String getKeywords() {
        return Arrays.toString(keywords);
    }

    @Override
    String getLabel() {
        return Label.NEGATIVE_TEXT.regExpres;
    }

    @Override
    public Label processText(String text) {
        for (int i = 0; i < keywords.length; ++i){
            if ((text.toLowerCase()).indexOf((keywords[i]).toLowerCase()) != (-1)){
                return Label.NEGATIVE_TEXT;
            }
        }
        return null;
    }
}
