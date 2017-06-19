package Utils;

import java.net.URL;

import javax.swing.ImageIcon;

public class  Utils {

public static  String getExtension(String fileName)
{
	
	int lastPeriodIndex= fileName.lastIndexOf(".");
	
	if(lastPeriodIndex == -1) return null;
	if(lastPeriodIndex== fileName.length()-1)return null;
			
	return fileName.substring(lastPeriodIndex, fileName.length());

}

public  static ImageIcon creatImageIcon (String path)
{
	
	URL url;
	ImageIcon icon=null;
	
	try {
		url= System.class.getResource(path);
		 icon = new ImageIcon (url) ;
	} catch (Exception e) {
		System.err.println("couldn't load icon ...\n"+e.getMessage());
	}
		
	return icon ;
}
}
	