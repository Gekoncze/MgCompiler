package cz.mg.compiler.tasks.builder.language;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.links.NamedLink;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.Name;
import cz.mg.compiler.entities.structured.parts.chains.Path;
import cz.mg.compiler.tasks.Task;
import cz.mg.compiler.tasks.builder.BlockBuildTask;
import cz.mg.compiler.tasks.builder.utilities.Rules;
import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.*;


public abstract class BuildUsingTask extends BlockBuildTask {
    public BuildUsingTask(Task parentTask, Block block, Context context) {
        super(parentTask, block, context);
    }

    @Override
    protected Rules getRules() {
        return null;
    }

    protected ChainList<NamedLink> buildPath(Part pathPart){
        ChainList<NamedLink> namedPath = new CachedChainList<>();
        Path path = toPath(pathPart);
        for(Part part : path.getParts()){
            Name name = cast(part, Name.class);
            namedPath.addLast(new NamedLink(getContext(), name.getContent()));
        }
        return namedPath;
    }
}
