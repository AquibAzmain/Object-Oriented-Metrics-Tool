package compositePack;

import java.util.ArrayList;
import java.util.List;

import shapePack.IShape;

public abstract class CompositeShape {
	public List <IShape> shapeList = new ArrayList<>();
	
	public abstract void prepare();
	
}
