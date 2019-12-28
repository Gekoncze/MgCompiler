package cz.mg.compiler.tasks.composer;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.Stamp;
import cz.mg.compiler.entities.text.Line;
import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.utilities.readers.LineReader;
import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.cast;


public class ComposeBlocksTask extends ComposeTask {
    @Info
    private final LineReader lineReader;

    @Link
    private final ChainList<Block> parent;

    @Info
    private final int indentation;

    @Link
    private ChainList<Text> documentation = new CachedChainList<>();

    @Link
    private ChainList<Stamp> stamps = new CachedChainList<>();

    @cz.mg.compiler.annotations.Part
    private ChainList<ComposeTask> composeTasks = new ChainList<>();

    public ComposeBlocksTask(LineReader lineReader, ChainList<Block> parent, int indentation) {
        this.lineReader = lineReader;
        this.parent = parent;
        this.indentation = indentation;
    }

    @Override
    protected void onRun() {
        while(lineReader.canRead()){
            Line line = lineReader.read();
            if(!isEmpty(line)){
                if(line.getIndentation() >= indentation){
                    processNonemptyLine(line);
                } else if(line.getIndentation() < indentation){
                    lineReader.back();
                    break;
                }
            } else {
                negativeReset();
            }
        }
        negativeReset();
    }

    private static boolean isEmpty(Line line){
        if(line.getComment() != null) return false;
        if(line.getTokens().count() > 0) return false;
        return true;
    }

    private void negativeReset(){
        if(stamps.count() > 0) getErrors().addLast(new ComposeException(stamps.getFirst(), "Missing block for stamps."));
        //if(documentation.count() > 0) getErrors().addLast(new ParseException(documentation.getFirst(), "Missing block for documentation."));
        stamps = new CachedChainList<>();
        documentation = new CachedChainList<>();
    }

    private void positiveReset(){
        stamps = new CachedChainList<>();
        documentation = new CachedChainList<>();
    }

    private void processNonemptyLine(Line line){
        checkValidIndentation(line);
        harvestComments(line);

        ComposePartsTask task = new ComposePartsTask(line);
        composeTasks.addLast(task);
        task.tryToRun();
        ChainList<Part> parts = task.getParts();

        if(parts.count() > 0){
            if(parts.getFirst() instanceof Stamp){
                harvestStamps(parts);
            } else {
                buildBlock(line, parts);
            }
        }
    }

    private void checkValidIndentation(Line line){
        if(line.getIndentation() > indentation){
            getErrors().addLast(new ComposeException(line, "Invalid indentation. Expected ", indentation, " given ", line.getIndentation(), ". Did you indent too much? Or are you missing a line nearby?"));
        }
    }

    private void harvestComments(Line line){
        if(line.getComment() != null){
            // documentation must start with two hashes and must be on line on its own
            // comments are silently dropped
            if(line.getComment().startsWith("##") && line.getTokens().count() <= 0){
                documentation.addLast(line.getComment());
            }
            line.setComment(null);
        }
    }

    private void harvestStamps(ChainList<Part> parts){
        for(Part part : parts){
            Stamp stamp = cast(part, Stamp.class);
            stamps.addLast(stamp);
        }
    }

    private void buildBlock(Line line, ChainList<Part> parts){
        Block block = new Block(line.getContent(), parts, documentation, stamps);
        parent.addLast(block);
        positiveReset();
        composeTasks.addLast(new ComposeBlocksTask(lineReader, block.getBlocks(), indentation + 1));
        composeTasks.getLast().run();
    }
}
