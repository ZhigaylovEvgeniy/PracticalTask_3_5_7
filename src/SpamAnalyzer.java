import java.util.Arrays;

public class SpamAnalyzer extends KeywordAnalyzer implements TextAnalyzer{
    protected String[] keywords;

    public SpamAnalyzer(String[] keywords) {
        this.keywords = keywords;
    }

    @Override
    String getKeywords() {
        return Arrays.toString(keywords);
    }

    @Override
    String getLabel() {
        return Label.SPAM.regExpres;

    }

    @Override
    public Label processText(String text) {
        for (int i = 0; i < keywords.length; ++i){
            if ((text.toLowerCase()).indexOf(keywords[i].toLowerCase()) != -1){
                return Label.SPAM;
            }
        }
        return null;
    }
}
