package org.apidesign.samples;

// BEGIN: xmms.java.api.ext

import org.apidesign.samples.XMMS.Playback.Volume;

// BEGIN: xmms.java.api
public final class XMMS {
    public void play() { 
        doPlay();
    }
    public void pause() { 
        doPause();
    }
    public void addToPlaylist(String file) { 
        doAddToPlaylist(file);
    }
// FINISH: xmms.java.api
    /** @since 2.0 we can also control volume */
    public void setVolume(int volume) { 
        doSetVolume(volume);
    }
// FINISH: xmms.java.api.ext
    
    // BEGIN: xmms.java.spi.ext
    // BEGIN: xmms.java.spi
    interface Playback {
        public void playback(byte[] data);
    // FINISH: xmms.java.spi
        /** @since 2.0 we can also control volume */
        interface Volume extends Playback {
            public void setVolume(int volume);
        }
    }
    // END: xmms.java.spi.ext
    
    public void registerPlayback(Playback callback) {
        this.callback = callback;
    }
    private Playback callback;
    
    private void doAddToPlaylist(String file) {
    }

    private void doPause() {
    }

    private void doPlay() {
        callback.playback("Play!".getBytes());
    }
    // BEGIN: xmms.java.spi.instanceof
    private void doSetVolume(int volume) {
        if (callback instanceof Volume) {
            Volume pv = (Volume) callback;
            pv.setVolume(volume);
        }
    }
    // END: xmms.java.spi.instanceof
    
    
    
    //
    // Usage
    // 
    
    public static void main(String[] args) {
        XMMS xmms = new XMMS();
        
        // BEGIN: xmms.java.use
        class MyCallbackPrints implements XMMS.Playback {
            public void playback(byte[] data) {
                System.out.println(new String(data));
            }
        }
        xmms.registerPlayback(new MyCallbackPrints());
        // END: xmms.java.use
    }

}
