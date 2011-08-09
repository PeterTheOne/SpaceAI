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
package event;

/**
 *
 * This SimpleEvent Class is based on cogaen3 by Roman Divotkey
 * @link http://www.cogaen.org
 * @link http://code.google.com/p/cogaen3-java/
 * 
 * @author Roman Divotkey
 * @author PeterTheOne
 */
public class EventType {

    // Members
    private int hashCode;
    private String name;

    // Methods
    public EventType(String name) {
        if (name == null) {
            throw new NullPointerException("name of entity type must not be NULL");
        }

        this.name = name;

        // use only lower case letters to generate hashcode to avoid misspelled entity type names
        this.hashCode = name.toLowerCase().hashCode();
    }

    public int getId() {
        return this.hashCode;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        EventType other = (EventType) obj;
        if (this.hashCode != other.hashCode()) {
            return false;
        }

        // also very unlikely equal strings could result in the same hash code,
        // let's test this only in 'debug' mode
        assert (equalNames(other));

        return true;
    }

    private boolean equalNames(EventType other) {
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        }

        return this.name.equals(other.name);
    }
}
