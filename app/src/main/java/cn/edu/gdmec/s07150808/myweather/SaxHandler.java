package cn.edu.gdmec.s07150808.myweather;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chen on 2016/12/11.
 */
public class SaxHandler extends DefaultHandler{
    /*保存省份与响应的城市，key为省份，value为相应的所有城市*/
    private Map<String,List<String>> cityMap=new HashMap<String,List<String>>();
    /*临时保存读取的省份与城市名称*/
    String cityName="";
    String provinceName="";
    public Map<String ,List<String>> getCityMap(){
        return cityMap;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if("Province".equals(qName)){//读取省份信息
            provinceName=attributes.getValue("name");
            /*每读取一个城市节点，就将省份名称保存在ap中*/
            cityMap.put(provinceName,new ArrayList<String>());

        }else if("City".equals(qName)){//读取城市信息
            cityName=attributes.getValue("name");
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
       if("city".equals(qName)){
           /*读取到相应城市节点的结束标签后，将城市信息保存到相应的省份list中*/
           cityMap.get(provinceName).add(cityName);
       }
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
