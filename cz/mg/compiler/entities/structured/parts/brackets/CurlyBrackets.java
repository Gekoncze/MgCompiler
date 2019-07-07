package cz.mg.compiler.entities.structured.parts.brackets;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.entities.structured.parts.Special;
import cz.mg.compiler.entities.structured.Part;


public class CurlyBrackets extends Brackets {
    public CurlyBrackets(ChainList<Part> parts, Special left, Special right) {
        super(parts, left, right);
    }
}
