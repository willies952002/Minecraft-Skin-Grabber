package com.willies952002.MCSkinGrabber;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;

public class SkinViewPage {
	
	File page;
	
	public SkinViewPage(String page, String name) throws Exception {
		this.page = new File(page);
		this.page.deleteOnExit();
		FileWriter fw_page = new FileWriter(this.page);
		fw_page.append("<html><body><img src=\"" + name + "\" alt=\"skin\"/></body></html>");
		fw_page.close();
	}

	public URL getPage() throws Exception {
		return new URL("file:///" + page.getAbsolutePath());
	}

}
