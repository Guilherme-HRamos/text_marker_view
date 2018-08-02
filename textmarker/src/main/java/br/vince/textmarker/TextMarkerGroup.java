package br.vince.textmarker;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TextMarkerGroup {

    @NonNull
    private final List<TextMarker> mGroup;
    @NonNull
    private AnimateType mType;

    public TextMarkerGroup(@NonNull AnimateType type, @NonNull List<TextMarker> group) {
        mGroup = group;
        mType = type;

        for (TextMarker textMarker : mGroup) {
            textMarker.setGroup(mGroup);
            textMarker.setType(type);
        }
    }

    public TextMarkerGroup(@NonNull AnimateType type, @NonNull TextMarker... group) {
        mGroup = new ArrayList<>(Arrays.asList(group));
        mType = type;

        for (TextMarker textMarker : mGroup) {
            textMarker.setGroup(mGroup);
            textMarker.setType(type);
        }
    }

    public void setType(AnimateType type) {
        mType = type;
        for (TextMarker textMarker : mGroup) {
            textMarker.setType(type);
        }
    }

    public void addItem(TextMarker item) {
        mGroup.add(item);
        item.setGroup(mGroup);
        item.setType(mType);
    }

    public void removeItem(TextMarker item) {
        mGroup.remove(item);
        item.setGroup(new ArrayList<TextMarker>());
    }

    public enum AnimateType {
        CHECKBOX, RADIOBUTTON
    }

}
