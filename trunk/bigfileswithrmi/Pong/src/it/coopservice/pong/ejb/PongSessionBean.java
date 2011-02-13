package it.coopservice.pong.ejb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(PongSession.class)
public class PongSessionBean implements PongSession {

	public void receiveFile(byte[] bytes, String fileName) {
		System.out.println("inizio a scrivere: " + fileName);
		System.out.println("bytes: " + bytes.length);
		File temp = new File(System.getProperty("java.io.tmpdir") + "/",
				fileName);
		// Write bbuf to filename
		ByteBuffer bbuf = ByteBuffer.wrap(bytes);
		// Set to true if the bytes should be appended to the file;
		// set to false if the bytes should replace current bytes
		// (if the file exists)
		boolean append = false;
		try {
			// Create a writable file channel
			FileChannel wChannel = new FileOutputStream(temp, append)
					.getChannel();
			// Write the ByteBuffer contents; the bytes between the ByteBuffer's
			// position and the limit is written to the file
			wChannel.write(bbuf);
			// Close the file
			wChannel.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("finisco di scrivere:" + temp.getAbsolutePath());
	}

}
