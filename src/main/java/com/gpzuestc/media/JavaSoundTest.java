package com.gpzuestc.media;

import java.io.File;

//import javax.media.j3d.PointSound;
//import javax.media.j3d.SceneGraphObject;
//import javax.media.j3d.Sound;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;

import org.junit.Test;

/**
 * Description:  
 * @author: guopengzhang@sohu-inc.com 
 * @date: Dec 9, 2012
 * 
 */
public class JavaSoundTest {
	
	@Test
	public void testGetInfo()throws Exception{
		File file = new File("E:\\entertainment\\music\\QQsound\\QQ1\\call.wav");
		AudioFileFormat audioFileFormat = AudioSystem.getAudioFileFormat(file);
		System.out.println(audioFileFormat.getFormat());
		System.out.println(audioFileFormat.getFrameLength());
		System.out.println(audioFileFormat.getByteLength());
	}
	
	@Test
	public void testSound(){
//		Sound sound = new PointSound();
//		SceneGraphObject sceneGraphObject = (SceneGraphObject)sound.getUserData();
	}
}
