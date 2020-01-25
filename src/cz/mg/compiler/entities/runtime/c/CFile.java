package cz.mg.compiler.entities.runtime.c;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.collections.text.Text;
import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.runtime.c.definitions.CDefinition;
import cz.mg.compiler.utilities.debug.Trace;


public class CFile extends CEntity {
    @Info
    private final ReadonlyText name;

    @Info
    private Text licence = new Text();

    @Part
    private final ChainList<CInclude> includes = new ChainList<>();

    @Part
    private final ChainList<CDefinition> definitions = new ChainList<>();

    public CFile(Trace trace, ReadonlyText name) {
        super(trace);
        this.name = name;
    }

    public ReadonlyText getName() {
        return name;
    }

    public Text getLicence() {
        return licence;
    }

    public void setLicence(Text licence) {
        this.licence = licence;
    }

    public ChainList<CInclude> getIncludes() {
        return includes;
    }

    public ChainList<CDefinition> getDefinitions() {
        return definitions;
    }
}
