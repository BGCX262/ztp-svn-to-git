/* Copyright (c) 2007, Sun Microsystems, Inc.
All rights reserved.

Redistribution and use in source and binary forms, with or without 
modification, are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, 
   this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright 
   notice, this list of conditions and the following disclaimer in 
   the documentation and/or other materials provided with the distribution.
 * Neither the name of Sun Microsystems, Inc. nor the names of its 
   contributors may be used to endorse or promote products derived 
   from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT 
OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED 
TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */     

package ztpviewer;

import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

public class ZTPViewerApp extends SingleFrameApplication {

    @Override protected void startup() {
        String imageDir = "http://ztp.googlecode.com/svn/zdjecia/test/";
	String[] imageNames = {
                "test1.bmp", "test1.gif", "test1.jp2", "test1.jpg", 
                "test1.pcx", "test1.png", "test1.tif", 
                "test2.bmp", "test2.gif", "test2.jp2", "test2.jpg", 
                "test2.pcx", "test2.png", "test2.tif",
	    "jeden.jp2", "Zd15.jpeg", "Zima.JPG", "szeliga.png",
	    "oczy.jp2", "Zd14.jpg", "malysz.jp2", "taliban.jp2", 
	    "obraz.jp2", "efekt.jp2", "1.jp2", "test.jp2", "sdsds"
	};
	List<URL> imageLocations = new ArrayList<URL>(imageNames.length);
	for(String imageName : imageNames) {
            String path = imageDir + imageName;
            try {
                URL url = new URL(path);
                imageLocations.add(url);
            }
            catch (MalformedURLException e) {
                Logger.getLogger(ZTPViewerApp.class.getName()).log(Level.WARNING, "Zly URL obrazka " + path, e);
            }
        }
        show(new ZTPViewerView(this, imageLocations));
    }

    @Override protected void ready() {
        Action refreshAction = getContext().getActionMap(
                ZTPViewerView.class, getMainView())
                .get("refreshImage");
        refreshAction.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));
    }

    @Override protected void configureWindow(java.awt.Window root) {
    }

    public static ZTPViewerApp getApplication() {
        return Application.getInstance(ZTPViewerApp.class);
    }

    public static void main(String[] args) {
        launch(ZTPViewerApp.class, args);
    }
}
