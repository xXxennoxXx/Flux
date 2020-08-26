package fluent.translators;

import fluent.WhereType;

public abstract class WhereTranslator extends Translator {
    protected WhereType type;
    protected Object one;
    protected Object bottom;
    protected Object top;
    protected Object[] in;

    public WhereTranslator(WhereType type, Object one) {
        this.type = type;
        this.one = one;
    }

    public WhereTranslator(WhereType type, Object bottom, Object top) {
        this.type = type;
        this.bottom = bottom;
        this.top = top;
    }

    public WhereTranslator(WhereType type, Object[] in) {
        this.type = type;
        this.in = in;
    }

}
