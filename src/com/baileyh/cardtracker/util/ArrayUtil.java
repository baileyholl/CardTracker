package com.baileyh.cardtracker.util;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by Bailey Hollingsworth on 7/24/17.
 */
public final class ArrayUtil {

    public static String[] appendStringArray(String[] array, String string){
        String[] list = new String[array.length];
        int count = 0;
        for(String s : array){
            list[count] = s + string;
            count++;
        }
        return list;
    }

    public static Text[] stringsToText(String[] array, Font font, Color color){
        Text[] list = new Text[array.length];
        int count = 0;
        for(String s : array){
            Text text = new Text(s);
            text.setFont(font);
            text.setFill(color);
            list[count] = text;
            count++;
        }
        return list;
    }


    public static Label[] stringsToLabels(String[] array, Font font){
        Label[] list = new Label[array.length];
        int count = 0;
        for(String s : array){
            Label label = new Label(s);
            label.setFont(font);
            list[count] = label;
            count++;
        }
        return list;
    }
    public static Label[] setColor(Label[] labels, Color color){
        for(Label t : labels){
            t.setTextFill(color);
        }
        return labels;
    }
}
