package model;

import controller.SoundListener;

public interface SoundSubject {
    void addListener(SoundListener soundListener);
    void removeListener(SoundListener soundListener);
    void notifyListener();
}
