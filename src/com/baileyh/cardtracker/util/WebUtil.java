package com.baileyh.cardtracker.util;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by Bailey Hollingsworth on 7/27/17.
 */
public final class WebUtil {
    public static void openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void openWebpage(URL url) {
        try {
            openWebpage(url.toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void openWebpage(String url){
        try {
            openWebpage(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
