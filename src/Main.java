/**
 * Practical task 3.5.7
 *
 * Пришло время попробовать реализовать иерархию классов определенного вида и решить конкретную задачу.
 *
 * Представим, вы делаете систему фильтрации комментариев на каком-то веб-портале,
 * будь то новости, видео-хостинг, а может даже для системы онлайн-обучения :).
 * Вы хотите фильтровать комментарии по разным критериям,
 * уметь легко добавлять новые фильтры и модифицировать старые.
 * Допустим,
 * мы будем фильтровать спам,
 * комментарии с негативным содержанием
 * и слишком длинные комментарии.
 *
 * Спам будем фильтровать по наличию указанных ключевых слов в тексте.
 * Негативное содержание будем определять по наличию одного из трех смайликов -  :( =( :|.
 * Слишком длинные комментарии будем определять исходя из данного числа - максимальной длины комментария.
 *
 * Вы решили абстрагировать фильтр в виде следующего интерфейса:
 *
 *      interface TextAnalyzer {
 *          Label processText(String text);
 *      }
 *
 * Label – тип-перечисление, которые содержит метки, которыми будем помечать текст:
 *
 *      enum Label {
 *          SPAM, NEGATIVE_TEXT, TOO_LONG, OK
 *      }
 *
 * Дальше,
 * вам необходимо реализовать три класса,
 * которые реализуют данный интерфейс:
 * SpamAnalyzer,
 * NegativeTextAnalyzer
 * и
 * TooLongTextAnalyzer.
 * SpamAnalyzer должен конструироваться от массива строк с ключевыми словами.
 * Объект этого класса должен сохранять в своем состоянии этот массив строк в приватном поле keywords.
 * NegativeTextAnalyzer должен конструироваться конструктором по-умолчанию.
 * TooLongTextAnalyzer должен конструироваться от int-а с максимальной допустимой длиной комментария.
 * Объект этого класса должен сохранять в своем состоянии это число
 * в приватном поле maxLength.
 *
 * Наверняка вы уже заметили, что SpamAnalyzer и NegativeTextAnalyzer во многом похожи:
 * они оба проверяют текст на наличие каких-либо ключевых слов
 * (в случае спама мы получаем их из конструктора,
 * в случае негативного текста мы заранее знаем набор грустных смайликов)
 * и в случае нахождения одного из ключевых слов
 * возвращают Label (SPAM и NEGATIVE_TEXT соответственно),
 * а если ничего не нашлось возвращают OK.
 * Давайте эту логику абстрагируем
 * в абстрактный класс KeywordAnalyzer
 * следующим образом:
 *
 * Выделим два абстрактных метода
 * getKeywords и
 * getLabel,
 * один из которых будет возвращать набор ключевых слов,
 * а второй метку,
 * которой необходимо пометить положительные срабатывания.
 * Нам незачем показывать эти методы потребителям классов,
 * поэтому оставим доступ к ним только для наследников.
 * Реализуем processText таким образом,
 * чтобы он зависел только от
 * getKeywords и
 * getLabel.
 * Сделаем
 * SpamAnalyzer и
 * NegativeTextAnalyzer
 * наследниками KeywordAnalyzer
 * и реализуем абстрактные методы.
 *
 * Последний штрих
 * - написать метод checkLabels,
 * который будет возвращать метку для комментария по набору анализаторов текста.
 * checkLabels
 * должен возвращать первую не-OK метку в порядке данного набора анализаторов,
 * и OK, если все анализаторы вернули OK.
 * Используйте, пожалуйста, самый открытый модификатор доступа для всех классов.
 * В итоге, реализуйте классы
 * KeywordAnalyzer,
 * SpamAnalyzer,
 * NegativeTextAnalyzer и
 * TooLongTextAnalyzer и
 * метод checkLabels.
 * TextAnalyzer и Label уже подключены,
 * лишние импорты вам не потребуются.
 *
 * Все классы реализации должны быть публичные
 *
 *      public Label checkLabels(TextAnalyzer[] analyzers, String text) {
 * 	        //Твой код здесь
 * 	        return Label.OK;
 *      }
 *          //Твой код здесь
 *
 *      //
* */
public class Main {
    public static void main(String[] args) {
        int mL = 100;
        TooLongAnalyzer tooLongAnalyzer1 = new TooLongAnalyzer(mL);

        SpamAnalyzer spamAnalyzer1 = new SpamAnalyzer(new String[]{"Москв", "Беларус", "Хабаров", "лид", "Минск", "Амур", "гродн", "боГ"});
        NegativeTextAnalyzer negativeTextAnalyzer1 = new NegativeTextAnalyzer();
        negativeTextAnalyzer1.keywords = new String[]{":(", "=(", ":|", ":)", ")"};

        //Чат1 с коментариями
        Chat chat = new Chat();
        TextAnalyzer[] textAnalyzers = new TextAnalyzer[]{spamAnalyzer1, negativeTextAnalyzer1, tooLongAnalyzer1};
        Main main = new Main();

        for (int i = 0; i < chat.comment.length; ++i ){
            main.checkLabels(textAnalyzers, chat.comment[i]);
        }


/*        for (int i = 0; i < chat.comment.length; ++i ){
            if (tooLongAnalyzer1.processText(chat.comment[i]) != null){
                System.out.println("Коментарий № " + (i + 1) + "\n\tПревышает максимальное значение " +
                        mL + " символов, поэтому мы помечаем этот коментарий " +
                        "специальной меткой " + tooLongAnalyzer1.processText(chat.comment[i]) +
                        "\n\tСодержание Коментария:\n\t\t\t" + chat.comment[i] + "\n");
            }
            if (spamAnalyzer1.processText(chat.comment[i]) != null){
                System.out.println("Коментарий № " + (i + 1) + "\n\tСодержит Спам " +
                        spamAnalyzer1.getKeywords() + " , поэтому мы помечаем этот коментарий " +
                        "специальной меткой " + spamAnalyzer1.getLabel() +
                        "\n\tСодержание Коментария:\n\t\t\t" + chat.comment[i] + "\n");
            }
            if (negativeTextAnalyzer1.processText(chat.comment[i]) != null){
                System.out.println("Коментарий № " + (i + 1) + "\n\tСодержит негативный текст " +
                        negativeTextAnalyzer1.getKeywords() + " поэтому мы помечаем этот коментарий " +
                        "специальной меткой " + negativeTextAnalyzer1.getLabel() +
                        "\n\tСодержание Коментария:\n\t\t\t" + chat.comment[i] + "\n");
            }

        }*/

    }
    public Label checkLabels(TextAnalyzer[] analyzers, String text) {
        for (int i = 0; i < analyzers.length; ++i){
            Label label1 = analyzers[i].processText(text);
            if (label1 != null){
                //System.out.println(label1.regExpres + "\n\t" + text);
                return label1;
            }
        }
        //System.out.println(Label.OK.regExpres + "\n\t" + text);
        return Label.OK;
    }
    //Твой код здесь

    //






}
