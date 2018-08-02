package br.vince.textmarker;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TextMarkerGroup {

    @NonNull
    private final List<TextMarkerView> mGroup;
    @NonNull
    private AnimateType mType;

    public TextMarkerGroup(@NonNull AnimateType type, @NonNull List<TextMarkerView> group) {
        mGroup = group;
        mType = type;

        for (TextMarkerView textMarkerView : mGroup) {
            textMarkerView.setGroup(mGroup);
            textMarkerView.setType(type);
        }
    }

    public TextMarkerGroup(@NonNull AnimateType type, @NonNull TextMarkerView... group) {
        mGroup = new ArrayList<>(Arrays.asList(group));
        mType = type;

        for (TextMarkerView textMarkerView : mGroup) {
            textMarkerView.setGroup(mGroup);
            textMarkerView.setType(type);
        }
    }

    public void setType(AnimateType type) {
        mType = type;
        for (TextMarkerView textMarkerView : mGroup) {
            textMarkerView.setType(type);
        }
    }

    public void addItem(TextMarkerView item) {
        mGroup.add(item);
        item.setGroup(mGroup);
        item.setType(mType);
    }

    public void removeItem(TextMarkerView item) {
        mGroup.remove(item);
        item.setGroup(new ArrayList<TextMarkerView>());
    }

    public enum AnimateType {
        CHECKBOX, RADIOBUTTON
    }

}
