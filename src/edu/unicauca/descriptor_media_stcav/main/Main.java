package edu.unicauca.descriptor_media_stcav.main;

import java.util.ArrayList;
import java.util.List;

import edu.unicauca.descriptor_media_stcav.logic.MediaServerGenerator;
import edu.unicauca.descriptor_media_stcav.logic.ParserXML;
import edu.unicauca.descriptor_media_stcav.logic.SkeletonProcessor;
import edu.unicauca.descriptor_media_stcav.miscellaneus.Log;
import edu.unicauca.descriptor_media_stcav.miscellaneus.TimeOfLife;
import edu.unicauca.descriptor_media_stcav.model.mbean.MyManRes;
import edu.unicauca.descriptor_media_stcav.model.mongoskeleton.ManRes;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TimeOfLife tol = new TimeOfLife();
		tol.set_home_time(System.currentTimeMillis());
		ManRes contentProcessorServer = MediaServerGenerator.do_create_man_res();
		List<MyManRes> mmrs = new ArrayList<MyManRes>();
		mmrs.add(SkeletonProcessor.do_ManRes_to_myManRes(contentProcessorServer));
		ParserXML.document_to_single_xml_myMBeanInfo(mmrs);
		tol.set_end_time(System.currentTimeMillis());
		Log.print("Tiempo de proceso: "+tol.get_tot_());
	}

}
