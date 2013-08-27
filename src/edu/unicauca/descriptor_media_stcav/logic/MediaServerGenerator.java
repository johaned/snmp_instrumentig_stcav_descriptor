package edu.unicauca.descriptor_media_stcav.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import edu.unicauca.descriptor_media_stcav.model.mbean.MyMBeanNotificationInfo;
import edu.unicauca.descriptor_media_stcav.model.mongoskeleton.Attr;
import edu.unicauca.descriptor_media_stcav.model.mongoskeleton.ManRes;
import edu.unicauca.descriptor_media_stcav.model.mongoskeleton.Mcratr;
import edu.unicauca.descriptor_media_stcav.model.mongoskeleton.Notif;

public class MediaServerGenerator {

	public static ManRes do_create_man_res() {
		ManRes mr = new ManRes();
		List<Mcratr> mas = new ArrayList<Mcratr>();
		Attr a = new Attr();
		Notif n = new Notif();
		mr.setDomain("SNMPInstrumentingServer");
		mr.setLyr("Servicios");
		mr.setName("MediaServer");
		mr.setRefProt("127.0.0.1/161");
		mr.setImpact("1");
		mr.setDescr("Recurso gestionable encargado de gestionar el comportamiento fisico del Media Server");
		mr.setRegDate("190213");
		mr.setAlertable(true);
		mr.setMngable(true);
		mr.setMother(0L);
		mr.setMcratr(do_create_mcattrs(mr.getRefProt()));

		return mr;
	}

	private static List<Mcratr> do_create_mcattrs(String refprot) {
		List<Mcratr> mas = new ArrayList<Mcratr>();
		Mcratr ma = new Mcratr();

		// MacroAttribute ReferenceSession
		ma.setId(1);
		ma.setName("Test");
		ma.setType("simple");
		ma.setDescr("Test Grouping to SNMP Instrumentig");
		ma.setAttr(do_create_attrs_test(ma.getRefProt()));
		ma.setRefProt("none");
		ma.setNotif(new ArrayList<Notif>());
		mas.add(ma);

		return mas;
	}

	private static List<Attr> do_create_attrs_test(String refprot) {
		List<Attr> as = new ArrayList<Attr>();
		Attr attr = new Attr();
		// SysUpTime
		attr.setId(1);
		attr.setName("sysUpTime");
		attr.setDataType("java.lang.String");
		attr.setDescr("The time (in hundredths of a second) since the network management portion of the system was last re-initialized.");
		attr.setRefProt(".1.3.6.1.2.1.1.3.0");
		as.add(attr);
		
		attr = new Attr();
		// ipInRceives
		attr.setId(2);
		attr.setName("sysServices");
		attr.setDataType("java.lang.Long");
		attr.setDescr("A value which indicates the set of services that this entity primarily offers. The value is a sum.  This sum initially takes the value zero, Then, for each layer, L, in the range 1 through 7, that this node performs transactions for, 2 raised to (L - 1) is added to the sum.  For example, a node which performs primarily routing functions would have a value of 4 (2^(3-1)).  In contrast, a node which is a host offering application services would have a value of 72 (2^(4-1) + 2^(7-1)).  Note that in the context of the Internet suite of protocols, values should be calculated accordingly: layer  functionality 1  physical (e.g., repeaters) 2  datalink/subnetwork (e.g., bridges) 3  internet (e.g., IP gateways) 4  end-to-end  (e.g., IP hosts) 7  applications (e.g., mail relays) For systems including OSI protocols, layers 5 and 6 may also be counted.");
		attr.setRefProt(".1.3.6.1.2.1.1.7.0");
		as.add(attr);
		
		attr = new Attr();
		// tcpInSegs
		attr.setId(3);
		attr.setName("hrSystemProcesses");
		attr.setDataType("java.lang.Long");
		attr.setDescr("The number of process contexts currently loaded or running on this system.");
		attr.setRefProt(".1.3.6.1.2.1.25.1.6.0");
		as.add(attr);
		return as;
		
	}

}
