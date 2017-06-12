package uk.ac.swanseacoventry.cmt.ontrack.diagram.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;
import org.zeroturnaround.zip.ZipUtil;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class RemoteFDR3Helper implements Runnable {

	
	public ArrayList<String> modelFolders = new ArrayList<String>();
	public ArrayList<TableItem> tableItems = new ArrayList<TableItem>();
	public Display display;
	
	void zipFolder(String input){
		ZipUtil.pack(new File(input), new File(input + ".zip"));
	}
	
	void sshUpload(String zipFile, TableItem item) {
		try {
			JSch jsch = new JSch();

			String host = "csverify.swansea.ac.uk";
			String user = "csnga";
			String phrase = "plmokn12";
			
			Session session=jsch.getSession(user, host, 22);
			session.setPassword(phrase);
			
			String command = "ls";
			
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy_kk.mm.ss");
			String root = "ontrack";
			
			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp sftp = (ChannelSftp) channel;
			
			try{
				sftp.mkdir(root);
			} catch (Exception e) {
				// probably folder existed already
			}
			
			sftp.cd(root);
			sftp.mkdir(sdf.format(date));
			sftp.cd(sdf.format(date));
			
			sftp.put(zipFile, zipFile.substring(zipFile.lastIndexOf(File.separator)+1));
			
			channel.disconnect();
			session.disconnect();
			
			display.syncExec(new TableItemUpdater(item, 8, "uploaded"));

		} catch (Exception e) {
			System.out.println(e);
		}				

	}
	
	@Override
	public void run() {
		
		
		int i = 0;
		for(String inFolder : modelFolders){
			zipFolder(inFolder);
			sshUpload(inFolder + ".zip", tableItems.get(i++));
			// sshUnZip(c, inFolder + ".zip");
			// sshExec(c, "refines " + inFolder + "/Railway.csp");
		}

	}

}
