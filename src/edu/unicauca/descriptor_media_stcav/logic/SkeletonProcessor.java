package edu.unicauca.descriptor_media_stcav.logic;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.management.MBeanAttributeInfo;

import com.mongodb.BasicDBObject;

import edu.unicauca.descriptor_media_stcav.model.mbean.MyMBeanAttributeInfo;
import edu.unicauca.descriptor_media_stcav.model.mbean.MyMBeanInfo;
import edu.unicauca.descriptor_media_stcav.model.mbean.MyMBeanNotificationInfo;
import edu.unicauca.descriptor_media_stcav.model.mbean.MyManRes;
import edu.unicauca.descriptor_media_stcav.model.mongoskeleton.Attr;
import edu.unicauca.descriptor_media_stcav.model.mongoskeleton.ManRes;
import edu.unicauca.descriptor_media_stcav.model.mongoskeleton.Mcratr;
import edu.unicauca.descriptor_media_stcav.model.mongoskeleton.Notif;


public class SkeletonProcessor {

	public static Map<String, Object> introspect(Object obj) throws Exception {
	    Map<String, Object> result = new HashMap<String, Object>();
	    BeanInfo info = Introspector.getBeanInfo(obj.getClass());
	    for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
	        Method reader = pd.getReadMethod();
	        if (reader != null)
	            result.put(pd.getName(), reader.invoke(obj));
	    }
	    return result;
	}
	
	public static List<MyMBeanInfo> do_object_to_jmx(ManRes mr){
		List<MyMBeanInfo> mmbis = new ArrayList<MyMBeanInfo>(); 
		for (Mcratr mcattr : mr.getMcratr()) {
			mmbis.add(do_mcattr_to_mbeaninfo(mcattr));
		}
		return mmbis;	
	}
	
	public static MyManRes do_ManRes_to_myManRes(ManRes mr){
		MyManRes mmr = new MyManRes(mr.getName(), mr.getDomain(), mr.getDescr(), mr.getRefProt(), null);
		
		List<MyMBeanInfo> mmbis = new ArrayList<MyMBeanInfo>();
		MyMBeanInfo mmbi;
		
		for (Mcratr mcatr : mr.getMcratr()) {
			mmbi=do_mcattr_to_mbeaninfo(mcatr);
			mmbis.add(mmbi);
		}
		mmr.setMacroAttributes(mmbis.toArray(new MyMBeanInfo[mmbis.size()]));
		return mmr;
	} 

	private static MyMBeanInfo do_mcattr_to_mbeaninfo(Mcratr mcattr) {
		MyMBeanInfo mmbi = new MyMBeanInfo();
				
		mmbi.setClassName("mbean.MyDynamicMBean");
		mmbi.setDescription(mcattr.getDescr());
		mmbi.setName(mcattr.getName());
		mmbi.setReferenceProtocol(mcattr.getRefProt());
		mmbi.setType(mcattr.getType());
		
		List<MyMBeanAttributeInfo> mmbais = new ArrayList<MyMBeanAttributeInfo>();
		List<MyMBeanNotificationInfo> mmbnis = new ArrayList<MyMBeanNotificationInfo>();
		
		MyMBeanAttributeInfo mmbai = new MyMBeanAttributeInfo();
		MyMBeanNotificationInfo mmbni = new MyMBeanNotificationInfo();
		
		for (Attr attr : mcattr.getAttr()) {
			mmbai = new MyMBeanAttributeInfo();
			mmbai.setName(attr.getName());
			mmbai.setType(attr.getDataType());
			mmbai.setDescription(attr.getDescr());
			mmbai.setReadable(true);
			mmbai.setWritable(false);
			mmbai.setIs(false);
			mmbai.setValue("0");
			mmbai.setReferenceProtocol(attr.getRefProt());
			mmbais.add(mmbai);
		}
		
		for (Notif notif : mcattr.getNotif()) {
			mmbni = new MyMBeanNotificationInfo();
			mmbni.setName(notif.getName());
			mmbni.setDescription(notif.getDesc());
			mmbni.setNotifTypes(notif.getType().split(" "));
			mmbnis.add(mmbni);
		}
		
		mmbi.setAttributes(mmbais.toArray(new MyMBeanAttributeInfo[mmbais.size()]));
		mmbi.setNotifications(mmbnis.toArray(new MyMBeanNotificationInfo[mmbnis.size()]));
		
		return mmbi;
	}
}
