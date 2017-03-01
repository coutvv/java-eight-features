package ru.coutvv.concurrency.fundamental.composingobj;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class VisualComponent {
    private final List<KeyListener> keyListenerList = new CopyOnWriteArrayList<>();
    private final List<MouseListener> mouseListeners = new CopyOnWriteArrayList<>();

    public void addKeyListener(KeyListener listener) {
        keyListenerList.add(listener);
    }

    public void addMouseListener(MouseListener listener) {
        mouseListeners.add(listener);
    }

    public void removeKeyListener(KeyListener listener) {
        keyListenerList.remove(listener);
    }

    public void removeMouseListener(MouseListener listener) {
        mouseListeners.remove(listener);
    }
}
