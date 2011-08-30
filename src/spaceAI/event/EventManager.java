/* 
 -----------------------------------------------------------------------------
                   Cogaen - Component-based Game Engine (v3)
 -----------------------------------------------------------------------------
 This software is developed by the Cogaen Development Team. Please have a 
 look at our project home page for further details: http://www.cogaen.org
    
 - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 Copyright (c) Roman Divotkey, 2010-2011

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
*/
package spaceAI.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * This SimpleEvent Class is based on cogaen3 by Roman Divotkey
 * @link http://www.cogaen.org
 * @link http://code.google.com/p/cogaen3-java/
 * 
 * @author Roman Divotkey
 * @author PeterTheOne
 */
public class EventManager {

    public static final boolean DEFAULT_FAST_EVENT_DISPATCH = true;
    private Map<EventType, ArrayList<EventListener>> listenerMap = new HashMap<EventType, ArrayList<EventListener>>();
    private ArrayList<EventListener> handledListeners = new ArrayList<EventListener>();
    private ArrayList<Event> events1 = new ArrayList<Event>();
    private ArrayList<Event> events2 = new ArrayList<Event>();
    private ArrayList<Event> currentEventList = events1;
    private EventType currentEventType = null;
    private boolean fastEventDispatch;

    public EventManager(boolean fastEventDispatch) {
        this.fastEventDispatch = fastEventDispatch;
    }

    public EventManager() {
        this(DEFAULT_FAST_EVENT_DISPATCH);
    }

    public void setFastEventDispatch(boolean value) {
        this.fastEventDispatch = value;
    }

    public boolean isFastEventDispatch() {
        return this.fastEventDispatch;
    }

    public boolean hasListener(EventListener listener, EventType eventType) {
        ArrayList<EventListener> listeners = this.listenerMap.get(eventType);

        return listeners == null ? false : listeners.contains(listener);
    }

    public void addListener(EventListener listener, EventType eventType) {
        ArrayList<EventListener> listeners = this.listenerMap.get(eventType);

        if (listeners == null) {
            listeners = new ArrayList<EventListener>();
            this.listenerMap.put(eventType, listeners);
            listeners.add(listener);
        } else if (!listeners.contains(listener)
                && !(eventType.equals(this.currentEventType) && this.handledListeners.contains(listener))) {
            listeners.add(listener);
        } else {
            //TODO: output error: "attempt to add listener twice"
        }
    }

    public void removeListener(EventListener listener, EventType eventType) {
        ArrayList<EventListener> listeners = this.listenerMap.get(eventType);

        boolean removed = false;
        if (listeners != null) {
            removed = listeners.remove(listener);
        }

        if (!removed && eventType.equals(this.currentEventType)) {
            removed = this.handledListeners.remove(listener);
        }

        if (!removed) {
            //TODO: output error: "attempt to remove unregistered listener"
        }
    }

    public void removeListener(EventListener listener) {
        Collection<ArrayList<EventListener>> listeners = this.listenerMap.values();

        for (Iterator<ArrayList<EventListener>> it = listeners.iterator(); it.hasNext();) {
            it.next().remove(listener);
        }

        this.handledListeners.remove(listener);
    }

    public void enqueueEvent(Event event) {
        this.currentEventList.add(event);
    }

    private void fireEvent(Event event) {
        ArrayList<EventListener> listeners = this.listenerMap.get(event.getType());

        if (listeners == null) {
            // no listeners for this event
            return;
        }

        this.currentEventType = event.getType();
        while (!listeners.isEmpty()) {
            EventListener listener = listeners.get(listeners.size() - 1);
            this.handledListeners.add(listener);
            listeners.remove(listeners.size() - 1);
            listener.handleEvent(event);
        }
        this.currentEventType = null;

        listeners.addAll(this.handledListeners);
        this.handledListeners.clear();
    }

    private void swapEventList() {
        if (this.currentEventList == events1) {
            this.currentEventList = events2;
        } else {
            this.currentEventList = events1;
        }
    }

    public void update() {
        do {
            List<Event> events = this.currentEventList;
            swapEventList();
            for (Event event : events) {
                fireEvent(event);
            }
            events.clear();
        } while (this.fastEventDispatch && !this.currentEventList.isEmpty());
    }
}
