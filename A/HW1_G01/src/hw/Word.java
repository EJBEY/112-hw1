package hw;

class Word {
    private String content;
    private int length;

    public Word(String content) {
        this.content = content;
        this.length = content.length();
    }

    public String getContent() {
        return content;
    }

    public int getLength() {
        return length;
    }

    public void setContent(String content) {
        this.content = content;
        this.length = content.length();
    }
    
    @Override
    public String toString() {
        return content;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Word word = (Word) obj;
        return length == word.length && content.equals(word.content);
    }
}
