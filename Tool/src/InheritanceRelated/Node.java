package InheritanceRelated;

import java.util.List;
import java.util.ArrayList;

public class Node<T> {
	 
	 private T data = null;
	 
	 private ArrayList<Node<T>> children = new ArrayList<>();
	 
	 public Node<T> parent = null;
	 public int depth = 0;
	 
	 public Node(T data, Node<T> parent) {
		 this.data = data;
		 if(parent != null) {
			 this.depth = parent.depth + 1;
			 this.parent = parent;
		 }
		 
	 }
	 
	 public Node<T> addChild(Node<T> child) {
		 child.parent = this;
		 this.children.add(child);
		 return child;
	 }
	 
	 
	 public List<Node<T>> getChildren() {
		 return children;
	 }
	 
	 public T getData() {
		 return data;
	 }
	 
	 public Node<T> getParent() {
		 return parent;
	 }
	 
	 
	}