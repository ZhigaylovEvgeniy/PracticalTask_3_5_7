public enum Label {

    SPAM("SPAM"),
    NEGATIVE_TEXT("NEGATIVE_TEXT"),
    TOO_LONG("TOO_LONG"),
    OK("OK");

    String regExpres;

    Label(String regExpres) {
        this.regExpres = regExpres;
    }

}
