package com.gpzuestc.media;

import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.DataSink;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoDataSinkException;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.Processor;
import javax.media.control.StreamWriterControl;
import javax.media.format.AudioFormat;
import javax.media.protocol.DataSource;
import javax.media.protocol.FileTypeDescriptor;

import jmapps.util.StateHelper;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2012-11-29
 * @todo:
 * 
 */
public class CaptureAV {

	private static final String DEST = "file:///Users/gpzuestc/Desktop/foo.wav";

	/**
	 * @param args
	 *            the command line arguments
	 */
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		CaptureDeviceInfo di = null;
		Processor p = null;
		Player player = null;
		StateHelper sh = null;
		Vector deviceList = CaptureDeviceManager.getDeviceList(new AudioFormat(
				AudioFormat.LINEAR, 44100, 16, 2));
		if (deviceList.size() > 0) {
			di = (CaptureDeviceInfo) deviceList.firstElement();
		} else
		// Exit if we can't find a device that does linear, 44100Hz, 16
		// bit,stereo audio.
		{
			System.exit(-1);
		}
		try {
			p = Manager.createProcessor(di.getLocator());
			player = Manager.createPlayer(di.getLocator());
			sh = new StateHelper(p);
		} catch (NoPlayerException ex) {
			Logger.getLogger(CaptureAV.class.getName()).log(Level.SEVERE, null,
					ex);
		} catch (IOException e) {
			System.exit(-1);
		}
		// Configure the processor
		if (!sh.configure(10000)) {
			System.exit(-1);
		}
		// Set the output content type and realize the processor
		p.setContentDescriptor(new FileTypeDescriptor(FileTypeDescriptor.WAVE));
		if (!sh.realize(10000)) {
			System.exit(-1);
		}
		// get the output of the processor
		DataSource source = (DataSource) p.getDataOutput();
		// create a File protocol MediaLocator with the location of the
		// file to which the data is to be written
		MediaLocator dest = new MediaLocator(DEST);
		// create a datasink to do the file writing & open the sink to
		// make sure we can write to it.
		DataSink filewriter = null;
		try {
			filewriter = Manager.createDataSink(source, dest);
			filewriter.open();
		} catch (NoDataSinkException e) {
			System.exit(-1);
		} catch (IOException e) {
			System.exit(-1);
		} catch (SecurityException e) {
			System.exit(-1);
		}

		// if the Processor implements StreamWriterControl, we can call
		// setStreamSizeLimit
		// to set a limit on the size of the file that is written.
		StreamWriterControl swc = (StreamWriterControl) p
				.getControl("javax.media.control.StreamWriterControl");
		// set limit to 5MB
		if (swc != null) {
			swc.setStreamSizeLimit(5000000);
		}
		player.start();
		// now start the filewriter and processor
		try {
			filewriter.start();
		} catch (IOException e) {
			System.exit(-1);
		}
		// Capture for 5 seconds
		sh.playToEndOfMedia(5000);
		sh.close();
		// Wait for an EndOfStream from the DataSink and close it...
		filewriter.close();
		player.stop();
	}

}
