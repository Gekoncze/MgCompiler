package cz.mg.compiler.entities.text.tokens;

import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.entities.text.Token;


public class WordToken extends Token {
    private boolean hasLower;
    private boolean hasUpper;
    private boolean hasNumber;
    private boolean hasLeadingUpper;
    private boolean hasUnderscore;

    public WordToken(Text content, boolean hasLower, boolean hasUpper, boolean hasNumber, boolean hasLeadingUpper, boolean hasUnderscore) {
        super(content);
        this.hasLower = hasLower;
        this.hasUpper = hasUpper;
        this.hasNumber = hasNumber;
        this.hasLeadingUpper = hasLeadingUpper;
        this.hasUnderscore = hasUnderscore;
    }

    public boolean hasLower() {
        return hasLower;
    }

    public boolean hasUpper() {
        return hasUpper;
    }

    public boolean hasNumber() {
        return hasNumber;
    }

    public boolean hasLeadingUpper() {
        return hasLeadingUpper;
    }

    public boolean hasUnderscore() {
        return hasUnderscore;
    }
}
