package cz.mg.projectexplorer.maps;

//import cz.mg.compiler.entities.c.mg.MgTypename;
//import cz.mg.compiler.entities.c.mg.MgType;
//import cz.mg.compiler.entities.c.mg.MgVariable;
//import cz.mg.compiler.entities.c.mg.MgClass;
//import cz.mg.compiler.entities.c.mg.MgObject;
//import cz.mg.compiler.entities.c.mg.MgAccess;
//import cz.mg.compiler.entities.c.mg.MgVariableGroup;
//import cz.mg.compiler.entities.c.mg.MgFunction;
//import cz.mg.compiler.entities.c.mg.MgOperator;
//import cz.mg.compiler.entities.c.mg.MgComment;
//import cz.mg.compiler.entities.c.mg.MgModule;
//import cz.mg.compiler.entities.c.mg.commands.MgCommand;
import cz.mg.collections.node.TreeNode;
import cz.mg.compiler.entities.Entity;
import cz.mg.compiler.entities.a.segments.Book;
import cz.mg.compiler.entities.a.segments.Line;
import cz.mg.compiler.entities.a.segments.Page;
import cz.mg.compiler.entities.a.segments.Word;
import cz.mg.compiler.entities.resources.ExternalFile;
import cz.mg.compiler.entities.resources.InternalFile;
import cz.mg.compiler.entities.resources.Resource;
import cz.mg.compiler.tasks.Task;
import cz.mg.compiler.tasks.TaskError;
import cz.mg.projectexplorer.components.extensions.BufferedImageIcon;
import cz.mg.projectexplorer.resources.icons.IconsLocation;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;


public class Icons {
	private static boolean loaded = false;
//	private static Icon ACCESS = null;
//	private static Icon ATTRIBUTE = null;
//	private static Icon CLASS = null;
//	private static Icon COMMAND = null;
//	private static Icon COMMENT = null;
//	private static Icon ENUM = null;
//	private static Icon EXCEPTION = null;
//	private static Icon FUNCTION = null;
//	private static Icon GROUP = null;
//	private static Icon LITERAL = null;
//	private static Icon MODULE = null;
//	private static Icon OBJECT = null;
//	private static Icon OPERATOR = null;
//	private static Icon TEMPLATE = null;
//	private static Icon TYPE = null;
//	private static Icon TYPENAME = null;
//	private static Icon VARIABLE = null;
	
	private static Icon ERROR = null;
    private static Icon LINK = null;
    private static Icon UNKNOWN = null;
    private static Icon TASK = null;
    private static Icon ENTITY = null;
    
    private static Icon RESOURCE = null;
    private static Icon FILE = null;
    
    private static Icon BOOK = null;
    private static Icon PAGE = null;
    private static Icon LINE = null;
    private static Icon WORD = null;
	
	public static Icon getErrorIcon(){
		if(!loaded) load();
		return ERROR;
	}
    
    public static Icon getLinkIcon(){
		if(!loaded) load();
		return LINK;
	}
	
//	public static Icon getIcon(MgObject object){
//		if(!loaded) load();
//		//if(object instanceof MgAccess) return ACCESS;
//		//if(object instanceof MgAttribute) return ATTRIBUTE;
//		//if(object instanceof MgClass) return CLASS;
//		//if(object instanceof MgCommand) return COMMAND;
//		//if(object instanceof MgComment) return COMMENT;
//		//if(object instanceof MgEnum) return ENUM;
//		//if(object instanceof MgException) return EXCEPTION;
//		//if(object instanceof MgFunction) return FUNCTION;
//		//if(object instanceof MgVariableGroup) return GROUP;
//		//if(object instanceof ???) return LITERAL;
//		//if(object instanceof MgModule) return MODULE;
//		//if(object instanceof MgOperator) return OPERATOR;
//		//if(object instanceof MgTemplate) return TEMPLATE;
//		//if(object instanceof MgType) return TYPE;
//		//if(object instanceof MgTypename) return TYPENAME;
//		//if(object instanceof MgVariable) return VARIABLE;
//		//if(object instanceof MgObject) return OBJECT;
//		throw new RuntimeException();
//	}
    
    public static Icon getIcon(TreeNode node){
        if(!loaded) load();
        
        if(node instanceof Book) return BOOK;
        if(node instanceof Page) return PAGE;
        if(node instanceof Line) return LINE;
        if(node instanceof Word) return WORD;
        
        if(node instanceof ExternalFile || node instanceof InternalFile) return FILE;
        if(node instanceof Resource) return RESOURCE;
        
        if(node instanceof TaskError) return ERROR;
        if(node instanceof Task) return TASK;
        if(node instanceof Entity) return ENTITY;
        
        return UNKNOWN;
    }
	
	private static void load(){
		try {
			loaded = true;
//			ACCESS = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("access.png")));
//			ATTRIBUTE = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("attribute.png")));
//			CLASS = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("class.png")));
//			COMMAND = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("command.png")));
//			COMMENT = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("comment.png")));
//			ENUM = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("enum.png")));
//			EXCEPTION = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("exception.png")));
//			FUNCTION = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("function.png")));
//			GROUP = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("group.png")));
//			LITERAL = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("literal.png")));
//			MODULE = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("module.png")));
//			OBJECT = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("object.png")));
//			OPERATOR = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("operator.png")));
//			TEMPLATE = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("template.png")));
//			TYPE = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("type.png")));
//			TYPENAME = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("typename.png")));
//			VARIABLE = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("variable.png")));

			ERROR = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("generic/error.png")));
			LINK = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("generic/link.png")));
            ENTITY = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("generic/entity.png")));
            TASK = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("generic/task.png")));
            UNKNOWN = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("generic/unknown.png")));
            
            RESOURCE = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("resources/resource.png")));
            FILE = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("resources/file.png")));
            
            BOOK = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("segmentation/book.png")));
            PAGE = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("segmentation/page.png")));
            LINE = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("segmentation/line.png")));
            WORD = new BufferedImageIcon(ImageIO.read(IconsLocation.class.getResourceAsStream("segmentation/word.png")));
		} catch (IOException ex) {
			throw new RuntimeException();
		}
	}
}
