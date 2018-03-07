package org.com.dm.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MultiHashMap;

import com.google.gson.internal.LinkedTreeMap;

public class TreeMain<N extends Serializable> implements MutableTree<N> {
	ArrayList<LinkedTreeMap> list=new ArrayList<LinkedTreeMap>();
	Map values = new HashMap();

    public static void main(String[] args) {

        MutableTree<String> tree = new TreeMain<String>();
        ArrayList<LinkedTreeMap> data =((TreeMain)tree).getData();
        ((TreeMain)tree).init(tree,data);
        
    }
	
	/*public static void main(String[] args) {
		  MultiHashMap mp=new MultiHashMap();
	      mp.put("a", 10);
	      mp.put("a", 11);
	      mp.put("a", 12);
	      mp.put("b", 13);
	      mp.put("c", 14);
	      mp.put("e", 15);
	      List list = null;

	      Set set = mp.entrySet();
	      Iterator i = set.iterator();
	      while(i.hasNext()) {
	         Map.Entry me = (Map.Entry)i.next();
	         list=(List)mp.get(me.getKey());

	         for(int j=0;j<list.size();j++)
	         {
	          System.out.println(me.getKey()+": value :"+list.get(j));
	         }
	      }
	   }*/
    
    public ArrayList<LinkedTreeMap>  init(MutableTree<String> tree, ArrayList<LinkedTreeMap> data){
    	data=preprocesarData(data);
    	for (LinkedTreeMap linkedTreeMap : data) {
        	tree.add(linkedTreeMap.get("PADRE")==null?"":linkedTreeMap.get("PADRE").toString(), linkedTreeMap.get("HIJO").toString());
        	((TreeMain)tree).getValues().put(linkedTreeMap.get("HIJO").toString(), linkedTreeMap.get("VALOR").toString());
		}
        System.out.println(tree);
        return ((TreeMain)tree).getList();
    }
    
    public ArrayList<LinkedTreeMap> preprocesarData(ArrayList<LinkedTreeMap> data){
    	int secuencia=1;
    	for (LinkedTreeMap linkedTreeMap : data) {
    		boolean duplicado=verificaDuplicado(linkedTreeMap.get("HIJO").toString(),data);
    		if(duplicado){
    			linkedTreeMap.put("HIJO", linkedTreeMap.get("HIJO").toString()+secuencia);
    			secuencia=secuencia+1;
    		}
    	}
    	return data;
    }
    
    public boolean verificaDuplicado(String hijo, ArrayList<LinkedTreeMap> data){
    	int contador=0;
    	for (LinkedTreeMap linkedTreeMap : data) {
    		if(linkedTreeMap.get("HIJO").toString().equals(hijo)){
    			contador=contador+1;
    		}
    	}
    	if(contador>1){
    		return true;
    	}else{
    		return false;
    	}
    	
    }
    
    public ArrayList<LinkedTreeMap> getData(){
    	ArrayList<LinkedTreeMap> data = new ArrayList<LinkedTreeMap>();
        LinkedTreeMap linked = null;
        
        linked = new LinkedTreeMap<>();
        linked.put("PADRE", "2");
        linked.put("HIJO", "3");
        linked.put("VALOR", "DATA21");
        data.add(linked);
        linked = new LinkedTreeMap<>();
        linked.put("PADRE", "2");
        linked.put("HIJO", "3");
        linked.put("VALOR", "DATA21");
        data.add(linked);
        linked = new LinkedTreeMap<>();
        linked.put("PADRE", "2");
        linked.put("HIJO", "3");
        linked.put("VALOR", "DATA21");
        data.add(linked);
        linked = new LinkedTreeMap<>();
        linked.put("PADRE", "2");
        linked.put("HIJO", "3");
        linked.put("VALOR", "DATA21");
        data.add(linked);
        linked = new LinkedTreeMap<>();
        linked.put("PADRE", "");
        linked.put("HIJO", "1");
        linked.put("VALOR", "DATA1");
        data.add(linked);
        linked = new LinkedTreeMap<>();
        linked.put("PADRE", "1");
        linked.put("HIJO", "2");
        linked.put("VALOR", "DATA2");
        data.add(linked);
        return data;
    }
    
    private ArrayList getList(){
    	return list;
    }

//    private final MultiHashMap nodeParent = new MultiHashMap();
    private final Map<N, N> nodeParent = new HashMap<N, N>();
    private final LinkedHashSet<N> nodeList = new LinkedHashSet<N>();

    private void checkNotNull(N node, String parameterName) {
        if (node == null)
            throw new IllegalArgumentException(parameterName + " must not be null");
    }

    @Override
    public boolean add(N parent, N node) {
        checkNotNull(parent, "parent");
        checkNotNull(node, "node");

        // check for cycles
        N current = parent;
        do {
            if (node.equals(current)) {
                throw new IllegalArgumentException(" node must not be the same or an ancestor of the parent");
            }
        } while ((current = getParent(current)) != null);

        boolean added = nodeList.add(node);
        nodeList.add(parent);
        nodeParent.put(node, parent);
        return added;
    }

    @Override
    public boolean remove(N node, boolean cascade) {
        checkNotNull(node, "node");

        if (!nodeList.contains(node)) {
            return false;
        }
        if (cascade) {
            for (N child : getChildren(node)) {
                remove(child, true);
            }
        } else {
            for (N child : getChildren(node)) {
                nodeParent.remove(child);
            }
        }
        nodeList.remove(node);
        return true;
    }

    @Override
    public List<N> getRoots() {
        return getChildren(null);
    }

    @Override
    public N getParent(N node) {
        checkNotNull(node, "node");
        return (N)nodeParent.get(node);
    }

    @Override
    public List<N> getChildren(N node) {
        List<N> children = new LinkedList<N>();
        for (N n : nodeList) {
            N parent = (N)nodeParent.get(n);
            if (node == null && parent == null) {
                children.add(n);
            } else if (node != null && parent != null && parent.equals(node)) {
                children.add(n);
            }
        }
        return children;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        dumpNodeStructure(builder, null,null, "- ");
        return builder.toString();
    }

    private void dumpNodeStructure(StringBuilder builder, N node,N padre, String prefix) {
        if (node != null && !node.equals("")) {
            builder.append(prefix);
            builder.append(node.toString());
            builder.append('\t');
            builder.append(padre.toString());
            builder.append('\t');
            builder.append(values.get(node));
            builder.append('\n');
            
            LinkedTreeMap linked = null;
            linked = new LinkedTreeMap<>();
            linked.put("PADRE", padre);
            linked.put("HIJO", node);
            linked.put("VALOR", values.get(node));
//            if(!node.equals(""))
            list.add(linked);
            prefix = "    " + prefix;
        }
        for (N child : getChildren(node)) {
            dumpNodeStructure(builder, child,node, prefix);
        }
    }


	public Map getValues() {
		return values;
	}


	public void setValues(Map values) {
		this.values = values;
	}
    
    
    
}