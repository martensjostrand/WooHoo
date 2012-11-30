package me.marten.woohoo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SoundPlayerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 7354267438355148076L;
	private SoundPlayer soundPlayer;
	private static final String relativePathToSound = "/WEB-INF/classes/woohoo.wav"; 
	
	public void init(){
		soundPlayer = SoundPlayer.getInstance();
	}

	public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	            throws ServletException, IOException {
	    final String soundFile = getServletContext().getRealPath(relativePathToSound);
		soundPlayer.play(soundFile);
	}

	public void destroy() {
	      // do nothing.
	}
}