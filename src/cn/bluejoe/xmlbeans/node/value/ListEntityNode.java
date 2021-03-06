package cn.bluejoe.xmlbeans.node.value;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;

/**
 * @author bluejoe2008@gmail.com
 */
public class ListEntityNode extends AbstractXmlNode implements ValueNode, EntityNode<List<?>>
{
	String _nodeId;

	private List<?> _value;

	List<ValueNode> _valueNodes = new ArrayList<ValueNode>();

	public void add(ValueNode vn)
	{
		_valueNodes.add(vn);
	}

	public String getNodeId()
	{
		return _nodeId;
	}

	public List<?> getValue()
	{
		return _value;
	}

	public void setNodeId(String nodeId)
	{
		_nodeId = nodeId;
	}

	public void setValue(List<?> bean)
	{
		_value = bean;
	}

	public void writeTo(Element parentElement)
	{
		Element valueElement;

		if (_nodeId != null)
		{
			valueElement = parentElement.addElement(new QName("list", new Namespace("util",
					"http://www.springframework.org/schema/util")));

			valueElement.addAttribute("id", _nodeId);
			String className = _value.getClass().getName();
			if (!className.startsWith("java.util."))
			{
				valueElement.addAttribute("list-class", className);
			}
		}
		else
		{
			valueElement = parentElement.addElement("list");
		}

		for (ValueNode vn : _valueNodes)
		{
			vn.writeTo(valueElement);
		}
	}
}
